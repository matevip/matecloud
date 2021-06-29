# Seata

## 部署安装

- 集成nacos配置中心

  下载 config ` https://github.com/seata/seata/blob/develop/script/config-center/config.txt`

  下载 sh脚本 `https://github.com/seata/seata/blob/develop/script/config-center/nacos/nacos-config.sh`

  本地修改config.txt 相关参数，修改nacos-config.sh 相关配置，并执行脚本，将配置推送到nacos

- Docker部署(需要指定当前宿主机IP)

  ```sh
  docker run --name seata-server \
          -p 8091:8091 \
          -e SEATA_IP=10.113.206.85 \
          -e SEATA_PORT=8091 \
          seataio/seata-server
  ```

  进入容器中，修改registry.conf 配置Nacos相关参数

  ```sh
  registry {
    # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
    type = "nacos"
    loadBalance = "RandomLoadBalance"
    loadBalanceVirtualNodes = 10
  
    nacos {
      application = "serverAddr"
      serverAddr = "10.113.206.34:8848"
      group = "DEFAULT_GROUP"
      namespace = ""
      cluster = "default"
      username = ""
      password = ""
    }
  }
  
  config {
    # file、nacos 、apollo、zk、consul、etcd3
    type = "nacos"
  
    nacos {
      serverAddr = "10.113.206.34:8848"
      namespace = ""
      group = "SEATA_GROUP"
      username = ""
      password = ""
     # dataId = "seataServer.properties"
    }
  }
  ```



## 注入原理

- 添加注解 @EnableAutoDataSourceProxy 配置自动代理数据源

  ```java
  @Target({ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Import({AutoDataSourceProxyRegistrar.class})
  @Documented
  public @interface EnableAutoDataSourceProxy {
      boolean useJdkProxy() default false;
  
      String[] excludes() default {};
  
  		//设置工作模式 默认AT模式，可以选择TCC、XA、SAGA
      String dataSourceProxyMode() default "AT";
  }
  ```

  @Import 引入配置类 AutoDataSourceProxyRegistrar.class  配置了代理数据源对象

  ```java
  
  ```

- 由于maven依赖了

  ```java
   			<dependency>
              <groupId>io.seata</groupId>
              <artifactId>seata-spring-boot-starter</artifactId>
              <version>1.4.1</version>
         </dependency>
  ```

  1:由spring.factories 配置加载配置类 加载bean GlobalTransactionScanner

  ```java
  @Configuration
  @EnableConfigurationProperties({SeataProperties.class})
  public class SeataAutoConfiguration {
  	.
    .
    .
      @Bean
      @DependsOn({"springApplicationContextProvider", "failureHandler"})
      @ConditionalOnMissingBean({GlobalTransactionScanner.class})
      public GlobalTransactionScanner globalTransactionScanner(SeataProperties seataProperties, FailureHandler failureHandler) {
          if (LOGGER.isInfoEnabled()) {
              LOGGER.info("Automatically configure Seata");
          }
  
          return new GlobalTransactionScanner(seataProperties.getApplicationId(), 		   seataProperties.getTxServiceGroup(), failureHandler);
      }
  
  	.
    .
    .
  }
  ```

  GlobalTransactionScanner 是个spring bean处理器，主要继承以及实现如下

  ```java
  public class GlobalTransactionScanner extends AbstractAutoProxyCreator implements ConfigurationChangeListener, InitializingBean, ApplicationContextAware, DisposableBean {
    
  }
  
  public abstract class AbstractAutoProxyCreator extends ProxyProcessorSupport
  		implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware {
    
  }
  ```

  由这部分判断是否有注解@GlobalTransactional 并创建代理对象，增强方法主要在有该注解的方法前，生成XID，在TC注册该全局事务；

  ```java
  	public Object postProcessAfterInitialization(@Nullable Object bean, String beanName) {
  		if (bean != null) {
  			Object cacheKey = getCacheKey(bean.getClass(), beanName);
  			if (this.earlyProxyReferences.remove(cacheKey) != bean) {
  				return wrapIfNecessary(bean, beanName, cacheKey);
  			}
  		}
  		return bean;
  	}
  ```

  GlobalTransactionalInterceptor 判断 是否有@GlobalTransactional注解

  ```java
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
          Class<?> targetClass = methodInvocation.getThis() != null ? AopUtils.getTargetClass(methodInvocation.getThis()) : null;
          Method specificMethod = ClassUtils.getMostSpecificMethod(methodInvocation.getMethod(), targetClass);
          if (specificMethod != null && !specificMethod.getDeclaringClass().equals(Object.class)) {
              Method method = BridgeMethodResolver.findBridgedMethod(specificMethod);
              GlobalTransactional globalTransactionalAnnotation = (GlobalTransactional)this.getAnnotation(method, targetClass, GlobalTransactional.class);
              GlobalLock globalLockAnnotation = (GlobalLock)this.getAnnotation(method, targetClass, GlobalLock.class);
              boolean localDisable = this.disable || degradeCheck && degradeNum >= degradeCheckAllowTimes;
              if (!localDisable) {
                  if (globalTransactionalAnnotation != null) {
                      return this.handleGlobalTransaction(methodInvocation, globalTransactionalAnnotation);
                  }
  
                  if (globalLockAnnotation != null) {
                      return this.handleGlobalLock(methodInvocation, globalLockAnnotation);
                  }
              }
          }
  
          return methodInvocation.proceed();
      }
    
  ```

  AT的增强类：SeataAutoDataSourceProxyAdvice

  ```java
  public class SeataAutoDataSourceProxyAdvice implements MethodInterceptor, IntroductionInfo {
  
      public Object invoke(MethodInvocation invocation) throws Throwable {
          if (!RootContext.requireGlobalLock() && this.dataSourceProxyMode != RootContext.getBranchType()) {
              return invocation.proceed();
          } else {
              Method method = invocation.getMethod();
              Object[] args = invocation.getArguments();
              Method m = BeanUtils.findDeclaredMethod(this.dataSourceProxyClazz, method.getName(), method.getParameterTypes());
              if (m != null) {
                  SeataDataSourceProxy dataSourceProxy = DataSourceProxyHolder.get().putDataSource((DataSource)invocation.getThis(), this.dataSourceProxyMode);
                  return m.invoke(dataSourceProxy, args);
              } else {
                  return invocation.proceed();
              }
          }
      }
  
  }
  ```

  其中 RootContext.requireGlobalLock() 先判断了当前线程的该ThreadLocal中是否有 TX_LOCK ,如果有，执行代理方法

  ```
     public static boolean requireGlobalLock() {
          return CONTEXT_HOLDER.get("TX_LOCK") != null;
      }
  ```

  2:该spring.factories 配置也同时加载配置类 加载bean HttpAutoConfiguration

  ```java
  @Configuration
  @ConditionalOnWebApplication
  public class HttpAutoConfiguration extends WebMvcConfigurerAdapter {
      public HttpAutoConfiguration() {
      }
  
      public void addInterceptors(InterceptorRegistry registry) {
          registry.addInterceptor(new TransactionPropagationInterceptor());
      }
  
      public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
          exceptionResolvers.add(new HttpHandlerExceptionResolver());
      }
  }
  
  
  ```

  新增了拦截器，用于拦截请求，构造上面的 线程ThreadLocal 变量，为之后数据源代理对象，判断是否是分布式事务

  ```java
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
          String xid = RootContext.getXID();
          String rpcXid = request.getHeader("TX_XID");
          if (LOGGER.isDebugEnabled()) {
              LOGGER.debug("xid in RootContext[{}] xid in HttpContext[{}]", xid, rpcXid);
          }
  
          if (xid == null && rpcXid != null) {
              RootContext.bind(rpcXid);
              if (LOGGER.isDebugEnabled()) {
                  LOGGER.debug("bind[{}] to RootContext", rpcXid);
              }
          }
  
          return true;
      }
  
      public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
          if (RootContext.inGlobalTransaction()) {
              XidResource.cleanXid(request.getHeader("TX_XID"));
          }
  
      }
  ```

- 未发现用feign拦截器进行request插入参数 XID的配置；对于请求的XID如何传递，暂时未看到源码。