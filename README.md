<p align="center">
  <img src="https://cdn.cankaojia.cn/matecloud.jpg" width="300">
</p>
<p align="center">
  <img src='https://img.shields.io/github/license/matevip/matecloud' alt='License'/>
  <img src="https://img.shields.io/github/stars/matevip/matecloud" alt="Stars"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-2.3.1.RELEASE-green" alt="SpringBoot"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR5-blue" alt="SpringCloud"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2.2.1.RELEASE-brightgreen" alt="Spring Cloud Alibaba"/>
</p>

## 如果您觉得有帮助，请点右上角 "Star" 支持一下谢谢

MateCloud是一款基于Spring Cloud Alibaba的微服务架构。旨在为大家提供技术框架的基础能力的封装，减少开发工作，让您只关注业务。

### 功能特点
1.　采用最新的Spring Cloud Alibaba封装;

2.　Spring Cloud Alibaba + Dubbo对Feign进行RPC改造，同时支持Feign和Dubbo调用;

3.　支持多数据库;

4.　集成消息中间件RocketMQ，使用异步处理逻辑;

5.　前端采用vue element ui;

6.　支持报文加密和验证机制（待开发）。

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
演示地址：http://cloud.mate.vip

账号密码：admin/123456

GITHUB地址： https://github.com/matevip/matecloud

GITEE地址：https://gitee.com/matevip/matecloud

### 前端代码
https://github.com/matevip/artemis 