<p align="center">
  <img src="https://cdn.mate.vip/matecloud.svg" width="260">
</p>
<p align="center">
  <img src='https://img.shields.io/github/license/matevip/matecloud' alt='License'/>
  <img src="https://img.shields.io/github/stars/matevip/matecloud" alt="Stars"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-2.3.9.RELEASE-green" alt="SpringBoot"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR10-blue" alt="SpringCloud"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2.2.5.RELEASE-brightgreen" alt="Spring Cloud Alibaba"/>
</p>

## 如果您觉得有帮助，请点右上角 "Star" 支持一下谢谢

MateCloud是一款基于Spring Cloud Alibaba的微服务架构。旨在为大家提供技术框架的基础能力的封装，减少开发工作，让您只关注业务。

### 系统演示
#### 演示地址：http://cloud.mate.vip

账号 | 密码| 操作权限
---|---|---
admin | matecloud| mate-system模块不能执行增删改请求

如果需要验证手机号码登录，手机号码采用页面默认号码，点击获取验证码，输入1188，即可登录。

### 官方文档
#### 文档地址：https://mate.vip/#/docs

### 技术交流
<p align="center"> 
    <img src="https://cdn.mate.vip/matecloud_social2.jpg" />
</p>
QQ群：2003638

### 技术架构
<p align="center"> 
    <img src="https://cdn.mate.vip/matecloud-framework.jpg" />
</p>
git 
### 功能特点
- 主体框架：采用最新的Spring Cloud Hoxton SR10, Spring Boot 2.3.9.RELEASE, Spring Cloud Alibaba 2.2.5.RELEASE版本进行系统设计；

- 统一注册：支持Nacos作为注册中心，实现多配置、分群组、分命名空间、多业务模块的注册和发现功能；

- 统一认证：统一Oauth2认证协议，采用jwt的方式，实现统一认证，并支持自定义grant_type实现手机号码登录，第三方登录正在开发中；

- 业务监控：利用Spring Boot Admin 来监控各个独立Service的运行状态；利用Hystrix Dashboard来实时查看接口的运行状态和调用频率等。

- 内部调用：集成了Feign和Dubbo两种模式支持内部调用，并且可以实现无缝切换，适合新老程序员，快速熟悉项目；

- 业务熔断：采用Sentinel实现业务熔断处理，避免服务之间出现雪崩;

- 身份注入：通过注解的方式，实现用户登录信息的快速注入；

- 在线文档：通过接入Knife4j，实现在线API文档的查看与调试;

- 代码生成：基于Mybatis-plus-generator自动生成代码，提升开发效率，生成模式不断优化中，暂不支持前端代码生成；

- 消息中心：集成消息中间件RocketMQ，对业务进行异步处理;

- 业务分离：采用前后端分离的框架设计，前端采用vue-element-admin
  
- 链路追踪：自定义traceId的方式，实现简单的链路追踪功能

- 多租户功能：集成Mybatis Plus,实现saas多租户功能

### 文件结构
```lua
matecloud -- 父项目,各模块分离，方便集成和微服务
│  ├─mate-core -- 核心通用模块，主模块
│  │  ├─mate-starter-common -- 封装通用模块
│  │  ├─mate-starter-cloud -- 封装微服务模块
│  │  ├─mate-starter-auth -- 封装token认证模块
│  │  ├─mate-starter-security -- 封装OAuth2基础模块
│  │  ├─mate-starter-web -- 封装WEB服务基础模块
│  │  ├─mate-starter-database -- 封装Mybatis及数据库基础模块
│  │  ├─mate-starter-dependencies -- 封装所有依赖模块，可作为父项目独立引用
│  │  ├─mate-starter-dubbo -- 封装dubbo基础模块
│  │  ├─mate-starter-feign -- 封装feign基础模块
│  │  ├─mate-starter-jetcache -- 封装JetCache阿里缓存基础模块
│  │  ├─mate-starter-rocketmq -- 封装RocketMQ基础模块
│  │  ├─mate-starter-gray -- 封装灰度发布基础模块
│  │  ├─mate-starter-elasticsearch -- 封装ElasticSearch模块
│  │  ├─mate-starter-oss -- 封装oss存储基础模块,支持阿里云、七牛云、minio等
│  │  ├─mate-starter-log -- 封装日志基础模块
│  │  ├─mate-starter-sharding -- 封装多数据库基础模块
│  │  ├─mate-starter-sms -- 封装短信基础模块
│  │  ├─mate-starter-mail -- 封装邮件模块
│  │  ├─mate-starter-kafka -- 封装kafka基础模块
│  │  ├─mate-starter-rule -- 封装黑名单基础模块
│  │  ├─mate-starter-idempotent -- 封装幂等基础模块
│  │  ├─mate-starter-lock -- 封装分布式锁基础模块
│  │  ├─mate-starter-encrypt -- 封装报文加密模块，支持AES和RSA
│  │  ├─mate-starter-mongodb -- 封装mongodb数据库模块
│  │  ├─mate-starter-strategy -- 封装策略模块
│  │  ├─mate-starter-job -- 封装定时任务基础模块
│  │─mate-gateway -- 统一网关模块 [10001]
│  │─mate-uaa -- 统一认证中心模块 [20001]
│  │─mate-platform -- 平台模块项目，目前包含系统子模块
│  │  ├─mate-system-api -- 系统模块的通用模块，供其他模块引用
│  │  ├─mate-system -- 系统模块核心功能 [20002]
│  │  ├─mate-component-api -- 组件模块核心功能，供其他模块引用
│  │  ├─mate-component -- 组件模块核心功能 [20003]
│  │─mate-support -- 支持中心项目，目前包括代码生成、admin模块
│  │  ├─mate-code -- 封装代码生成逻辑 [30002]
│  │  ├─mate-admin -- 封装spring-boot-admin逻辑 [30001]
│  │─mate-mq -- 消息中心项目，支持kafka、RocketMQ等多种消息中间件
│  │  ├─mate-log-producer -- 日志消息生产者，集成kafka [40001]
│  │  ├─mate-message-consumer -- 消息服务消费者 [40002]
│  │  ├─mate-message-producer -- 消息服务生产者 [40003] 
```
### 核心模块提交至中央仓库
如何引入依赖
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>vip.mate</groupId>
            <artifactId>mate-starter-dependencies</artifactId>
            <version>2.3.8</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
然后在 dependencies 中添加自己所需使用的依赖即可使用。

## 项目源码
|  项目   |   GITHUB  |   码云   |
|---  |--- | --- |
|  MateCloud后端源码   |  https://github.com/matevip/matecloud   |  https://gitee.com/matevip/matecloud   |
|  Artemis前端源码   |  https://github.com/matevip/artemis   |  https://gitee.com/matevip/artemis   |
|  MateBoot后端源码   |  https://github.com/matevip/mateboot   |  https://gitee.com/matevip/mateboot   |

## 特别鸣谢
特别感谢卢神对MateCloud项目提供的技术支持！
- 卢春梦: mica  https://gitee.com/596392912/mica
- chuzhixin: vue-admin-beautiful https://github.com/chuzhixin/vue-admin-beautiful

