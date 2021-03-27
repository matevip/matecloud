## mate-starter-idempotent模块说明

- 幂等验证模块，防止重复提交
- 为了解决在分布式系统中同次请求被多次调用时数据一致性的问题

## 启用版本

2.5.8

## 使用场景

需要同次请求被多次调用时数据一致

## 原理
使用aop拦截请求，如果方法上有@Ide 取key, keyFrom 如果没有设置采用默认RID(header)中。 如果设置了key,从request中取到相应的key组成一个业务唯一标识

## 使用方法
方法级别的注解，按需引入即可。
### KEY的方式
````java
@Ide(perFix = "TEST_", key = "key", ideTypeEnum = IdeTypeEnum.KEY)
````
### Header的方式
````java
@Ide(perFix = "TEST_", key = "key", ideTypeEnum = IdeTypeEnum.RID)
````
### KEY+HEADER的方
````java
@Ide(perFix = "TEST_", key = "key", ideTypeEnum = IdeTypeEnum.ALL)
````


