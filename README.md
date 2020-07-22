<p align="center">
  <img src="https://cdn.cankaojia.cn/matecloud.jpg" width="300">
</p>
<p align="center">
  <img src='https://img.shields.io/github/license/matevip/matecloud' alt='License'/>
  <img src="https://img.shields.io/github/stars/matevip/matecloud" alt="Stars"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-2.3.1.RELEASE-green" alt="SpringBoot"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR6-blue" alt="SpringCloud"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2.2.1.RELEASE-brightgreen" alt="Spring Cloud Alibaba"/>
</p>

## 如果您觉得有帮助，请点右上角 "Star" 支持一下谢谢

MateCloud是一款基于Spring Cloud Alibaba的微服务架构。旨在为大家提供技术框架的基础能力的封装，减少开发工作，让您只关注业务。

### 技术架构
<p align="center"> 
    <img src="https://cdn.ckjia.com/MateCloud%E5%BE%AE%E6%9C%8D%E5%8A%A1%E6%9E%B6%E6%9E%84%E4%BD%93%E7%B3%BB.png" />
</p>

### 功能特点
- 主体框架：采用最新的Spring Cloud Hoxton SR6, Spring Boot 2.3.1, Spring Cloud Alibaba 2.2.1.RELEASE版本进行系统设计；

- 统一注册：支持nacos作为注册中心，实现多配置、分群组、分命名空间、多业务模块的注册和发现功能；

- 统一认证：统一Oauth2认证协议，采用jwt的方式，实现统一认证，并支持自定义grant_type实现手机号码登录，第三方登录正在开发中；

- 业务监控：利用Spring Boot Admin 来监控各个独立Service的运行状态；利用Hystrix Dashboard来实时查看接口的运行状态和调用频率等。

- 内部调用：集成了feign和dubbo两种模式支持内部调用，并且可以实现无缝切换，适合新老程序员，快速熟悉项目；

- 业务熔断：采用Sentinel实现业务熔断处理，避免服务之间出现雪崩;

- 身份注入：通过注解的方式，实现用户登录信息的快速注入；

- 在线文档：通过接入knife4j，实现在线API文档的查看与调试;

- 代码生成：基于Mybatis-plus-generator自动生成代码，提升开发效率，生成模式不断优化中，暂不支持前端代码生成；

- 消息中心：集成消息中间件RocketMQ，对业务进行异步处理;

- 业务分离：采用前后端分离的框架设计，前端采用vue-element-admin
  

### 文件结构
```lua
matecloud -- 父项目,各模块分离，方便集成和微服务
│  ├─mate-core -- 核心通用模块，主模块
│  │  ├─mate-starter-common -- 封装通用模块
│  │  ├─mate-starter-auth -- 封装token认证模块
│  │  ├─mate-starter-security -- 封装OAuth2基础模块
│  │  ├─mate-starter-web -- 封装WEB服务基础模块
│  │  ├─mate-starter-database -- 封装Mybatis及数据库基础模块
│  │  ├─mate-starter-dependencies -- 封装所有依赖模块，可作为父项目独立引用
│  │  ├─mate-starter-dubbo -- 封装dubbo基础模块
│  │  ├─mate-starter-feign -- 封装feign基础模块
│  │  ├─mate-starter-jetcache -- 封装JetCache阿里缓存基础模块
│  │  ├─mate-starter-rocketmq -- 封装RocketMQ基础模块
│  │─mate-gateway -- 统一网关模块 [10001]
│  │─mate-uaa -- 统一认证中心模块 [20001]
│  │─mate-platform -- 平台模块项目，目前包含系统子模块
│  │  ├─mate-system-api -- 系统模块的通用模块，供其他模块引用
│  │  ├─mate-system -- 系统模块核心功能 [20002]
│  │─mate-support -- 支持中心项目，目前包括代码生成、admin模块
│  │  ├─mate-code -- 封装代码生成逻辑 [30002]
│  │  ├─mate-admin -- 封装spring-boot-admin逻辑 [30001]
```
### 技术交流
QQ群：2003638

### 系统演示
- 演示地址：http://cloud.mate.vip

- 账号密码：admin/matecloud

如果需要验证手机号码登录，手机号码采用页面默认号码，点击获取验证码，输入1188，即可登录。

## 项目源码
|     |   MateCloud后端源码  |   Artemis前端源码  |
|---  |--- | --- |
|  github   |  https://github.com/matevip/matecloud   |  https://github.com/matevip/artemis   |
|  码云   |  https://gitee.com/matevip/matecloud   |  https://gitee.com/matevip/artemis   |
