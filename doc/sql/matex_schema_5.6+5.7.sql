SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `matex`
--

-- --------------------------------------------------------

--
-- 表的结构 `mate_data_source`
--

CREATE TABLE IF NOT EXISTS `mate_data_source` (
  `id` int(11) NOT NULL COMMENT '自增id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `driver_class` varchar(100) DEFAULT NULL COMMENT '驱动类',
  `url` varchar(500) DEFAULT NULL COMMENT '连接地址',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='数据源表';

--
-- 转存表中的数据 `mate_data_source`
--

INSERT INTO `mate_data_source` (`id`, `name`, `driver_class`, `url`, `username`, `password`, `remark`, `status`, `created_at`, `updated_at`, `deleted_at`, `is_deleted`) VALUES
(1, '1', '1', '1', '1', '1', '1', 1, '2020-04-20 02:59:22', NULL, NULL, 0),
(2, '2', '2', '2', '2', '2', '2', 2, '2020-04-20 03:03:34', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- 表的结构 `mate_duben_doc`
--

CREATE TABLE IF NOT EXISTS `mate_duben_doc` (
  `id` bigint(20) NOT NULL COMMENT '文档id',
  `set_id` bigint(20) DEFAULT '0',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父文档id',
  `title` varchar(255) DEFAULT NULL COMMENT '文档标题',
  `price` decimal(10,2) DEFAULT NULL COMMENT '文档价格',
  `origin_price` decimal(10,2) DEFAULT NULL COMMENT '原始价格',
  `doc_context` text COMMENT '文档内容',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='读本文档';

--
-- 转存表中的数据 `mate_duben_doc`
--

INSERT INTO `mate_duben_doc` (`id`, `set_id`, `parent_id`, `title`, `price`, `origin_price`, `doc_context`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES
(1, 1, 0, '快速入门', NULL, NULL, NULL, '0', NULL, NULL, '2020-11-02 07:29:08', '2020-11-02 07:32:17'),
(2, 1, 0, '开发初探', NULL, NULL, NULL, '0', NULL, NULL, '2020-11-02 10:04:25', '2020-11-02 10:04:25'),
(6, 1, 1, '产品介绍', NULL, NULL, '![matecloud](https://cdn.cankaojia.cn/matecloud.jpg)\n\n\n![](https://img.shields.io/github/license/matevip/matecloud)\n![](https://img.shields.io/badge/Spring%20Boot-2.3.4.RELEASE-green)\n![](https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR8-blue)\n![](https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2.2.3.RELEASE-brightgreen)\n\n## 如果您觉得有帮助，请点右上角 "Star" 支持一下谢谢\n\nMateCloud是一款基于Spring Cloud Alibaba的微服务架构。旨在为大家提供技术框架的基础能力的封装，减少开发工作，让您只关注业务。\n\n### 系统演示\n#### 演示地址：http://cloud.mate.vip\n\n账号 | 密码| 操作权限\n---|---|---\nadmin | matecloud| mate-system模块不能执行增删改请求\n\n如果需要验证手机号码登录，手机号码采用页面默认号码，点击获取验证码，输入1188，即可登录。\n\n### 技术交流\n\n![](https://cdn.ckjia.com/matecloud_social2.jpg)\n\nQQ群：2003638\n\n### 技术架构\n\n![](https://cdn.ckjia.com/matecloud-framework.jpg)\n\n### 功能特点\n- 主体框架：采用最新的Spring Cloud Hoxton SR8, Spring Boot 2.3.4.RELEASE, Spring Cloud Alibaba 2.2.3.RELEASE版本进行系统设计；\n\n- 统一注册：支持nacos作为注册中心，实现多配置、分群组、分命名空间、多业务模块的注册和发现功能；\n\n- 统一认证：统一Oauth2认证协议，采用jwt的方式，实现统一认证，并支持自定义grant_type实现手机号码登录，第三方登录正在开发中；\n\n- 业务监控：利用Spring Boot Admin 来监控各个独立Service的运行状态；利用Hystrix Dashboard来实时查看接口的运行状态和调用频率等。\n\n- 内部调用：集成了feign和dubbo两种模式支持内部调用，并且可以实现无缝切换，适合新老程序员，快速熟悉项目；\n\n- 业务熔断：采用Sentinel实现业务熔断处理，避免服务之间出现雪崩;\n\n- 身份注入：通过注解的方式，实现用户登录信息的快速注入；\n\n- 在线文档：通过接入knife4j，实现在线API文档的查看与调试;\n\n- 代码生成：基于Mybatis-plus-generator自动生成代码，提升开发效率，生成模式不断优化中，暂不支持前端代码生成；\n\n- 消息中心：集成消息中间件RocketMQ，对业务进行异步处理;\n\n- 业务分离：采用前后端分离的框架设计，前端采用vue-element-admin\n  \n- 链路追踪：自定义traceId的方式，实现简单的链路追踪功能\n\n- 多租户功能：集成Mybatis Plus,实现saas多租户功能\n\n### 文件结构\n```lua\nmatecloud -- 父项目,各模块分离，方便集成和微服务\n│  ├─mate-core -- 核心通用模块，主模块\n│  │  ├─mate-starter-common -- 封装通用模块\n│  │  ├─mate-starter-cloud -- 封装微服务模块\n│  │  ├─mate-starter-auth -- 封装token认证模块\n│  │  ├─mate-starter-security -- 封装OAuth2基础模块\n│  │  ├─mate-starter-web -- 封装WEB服务基础模块\n│  │  ├─mate-starter-database -- 封装Mybatis及数据库基础模块\n│  │  ├─mate-starter-dependencies -- 封装所有依赖模块，可作为父项目独立引用\n│  │  ├─mate-starter-dubbo -- 封装dubbo基础模块\n│  │  ├─mate-starter-feign -- 封装feign基础模块\n│  │  ├─mate-starter-jetcache -- 封装JetCache阿里缓存基础模块\n│  │  ├─mate-starter-rocketmq -- 封装RocketMQ基础模块\n│  │  ├─mate-starter-gray -- 封装灰度发布基础模块\n│  │  ├─mate-starter-elasticsearch -- 封装ElasticSearch模块\n│  │  ├─mate-starter-oss -- 封装oss存储基础模块,支持阿里云、七牛云、minio等\n│  │  ├─mate-starter-log -- 封装日志基础模块\n│  │  ├─mate-starter-sharding -- 封装多数据库基础模块\n│  │  ├─mate-starter-sms -- 封装短信基础模块\n│  │  ├─mate-starter-kafka -- 封装kafka基础模块\n│  │─mate-gateway -- 统一网关模块 [10001]\n│  │─mate-uaa -- 统一认证中心模块 [20001]\n│  │─mate-platform -- 平台模块项目，目前包含系统子模块\n│  │  ├─mate-system-api -- 系统模块的通用模块，供其他模块引用\n│  │  ├─mate-system -- 系统模块核心功能 [20002]\n│  │  ├─mate-component-api -- 组件模块核心功能，供其他模块引用\n│  │  ├─mate-component -- 组件模块核心功能 [20003]\n│  │─mate-support -- 支持中心项目，目前包括代码生成、admin模块\n│  │  ├─mate-code -- 封装代码生成逻辑 [30002]\n│  │  ├─mate-admin -- 封装spring-boot-admin逻辑 [30001]\n│  │─mate-mq -- 消息中心项目，支持kafka、RocketMQ等多种消息中间件\n│  │  ├─mate-log-producer -- 日志消息生产者，集成kafka [40001]\n│  │  ├─mate-message-consumer -- 消息服务消费者 [40002]\n│  │  ├─mate-message-producer -- 消息服务生产者 [40003] \n```\n\n## 项目源码\n|  项目   |   GITHUB  |   码云   |\n|---  |--- | --- |\n|  MateCloud后端源码   |  https://github.com/matevip/matecloud   |  https://gitee.com/matevip/matecloud   |\n|  Artemis前端源码   |  https://github.com/matevip/artemis   |  https://gitee.com/matevip/artemis   |\n|  MateBoot后端源码   |  https://github.com/matevip/mateboot   |  https://gitee.com/matevip/mateboot   |', '0', NULL, NULL, '2020-10-26 09:18:21', '2020-11-02 07:29:19'),
(7, 1, 1, '快速开始', NULL, NULL, '# 快速开始\n## 说明\n我们为用户准备了可以快速部署 MateCloud 所有组件及所需的中间件的Docker安装脚本, 你可以通过该脚本部署并体验 MateCloud 所提供的功能\n\n## 部署服务器要求\n>- 操作系统: 任何支持 Docker 的 Linux x64、Windows、MACOS\n>- CPU/内存: 2核8G（最小）\n>- 磁盘空间: 20G\n>- 可访问互联网\n\n## 推荐开发工具\n### 后端开发工具\n- IDEA: https://www.jetbrains.com/\n#### 插件\n- Lombok Plugin（必装）\n- MybatisX Plugin (选装)\n\n### 前端开发工具\n- VSCode: https://code.visualstudio.com/\n\n## 安装常用组件手动模式\n### 1.安装JDK 1.8+\n网上搜索对应环境的JDK\nJDK官网下载地址：[https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)\n\n### 2.安装MySQL 5.6+\n网上搜索对应环境的MySQL\nMySQL官网地址：[https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloadssqlma/)\n\n### 3.安装Redis 4.0+\n网上搜索对应环境的Redis\nRedis官网地址：[https://redis.io/download](https://redis.io/download)\n\n### 4.安装Nacos 1.3.1+\nNacos官网地址：[https://nacos.io/zh-cn/docs/what-is-nacos.html](https://nacos.io/zh-cn/docs/what-is-nacos.html)\n\n### 5.安装Sentinel 1.6.0+ [可选]\nSentinel官网地址：[https://sentinelguard.io/zh-cn/docs/quick-start.html](https://sentinelguard.io/zh-cn/docs/quick-start.html)\n\n## 基础配置\n\n### 1. 导入SQL文件\n文件路径项目根目录下：\n```bash\n/doc/sql/matex_schema.sql\n```\n导入可以借助navicat或其他mysql工具。\n\n### 2.导入Nacos配置\n文件路径项目根目录下：\n```bash\n/doc/nacos/mate.yaml\n/doc/nacos/mate-local.yaml\n```\n> 注意：\n导入nacos时，文件名必须以yaml结尾，命名为mate或mate.yml均无法获取配置文件。\n\n### 3.修改mate-local.yaml配置\n修改数据库和redis为本地参数\n- 修改redis参数\n```yaml\nspring:\n  redis:\n    #redis 单机环境配置\n    host: 127.0.0.1\n    port: 6379\n    password:\n    database: 0\n    ssl: false\n    #redis 集群环境配置\n    #cluster:\n    #  nodes: 127.0.0.1:7001,127.0.0.1:7002,127.0.0.1:7003\n    #  commandTimeout: 5000\n```\n- 修改mysql参数\n```yaml\nmate:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/matex?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull\n    username: root\n    password: root\n```\n\n## 必要启动模块\n\n### 启动认证模块（mate-uaa）\n> 运行vip.mate.uaa.MateUaaServer\n\n### 启动系统模块（mate-system）\n> 运行vip.mate.system.MateSystemServer\n\n### 启动代码模块（mate-code）［可选］\n> 运行vip.mate.code.MateCodeServer\n\n### 启动网关模块（mate-gateway）\n> 运行vip.mate.gateway.MateGatewayServer\n\n\n', '0', NULL, NULL, '2020-10-26 11:03:18', '2020-11-21 19:48:44'),
(8, 1, 1, '系统架构', NULL, NULL, '# 系统架构\n## 整体架构\n![](https://cdn.ckjia.com/matecloud_architecture_1.jpg)\n> 组件说明\n\n- Artemis: 前端工程，基于vue开发，采用element admin模板开发\n- MateCloud: 后端工程，基于Spring Cloud Alibaba 2.2.3.Release开发的微服务项目\n- mate-gateway: 后端网关模块，全局的互联网入口\n- mate-uaa: 统一的认证模块，token生成\n- mate-system: 系统服务模块，主要负责管理后台主要功能模块\n- mate-component: 组件模块，主要负责oss存储服务、短信、邮件等配置模块\n- mate-code: 代码模块，主要负责自动生成代码，减少开发量\n- mate-admin: 服务监控模块，主要负责微服务的各服务模块的监控\n- mate-job: 定时任务模块，集成xxl-job,负责定时任务处理\n- MySQL: 所有关系型数据均存储于MySQL\n- RocketMQ: 消息中心采用RocketMQ来实现消息管理，同时支持事务性消息\n- Redis: key-value型存储系统\n- Nacos: 统一的配置中心，实现微服务的配置和注册服务\n- Sentinel: 熔断和限流组件\n\n## 基础依赖组件\n![](https://cdn.ckjia.com/mate-core.png)', '0', NULL, NULL, '2020-10-27 22:29:54', '2020-11-05 07:41:45'),
(9, 1, 2, '一篇搞定认证', NULL, NULL, '## 认证简述\nMateCloud采用OAuth2协议，实现统一认证，官方文档：https://oauth.net/2/\n\nOAuth（开放授权）是一个开放标准，允许用户授权第三方移动应用访问他们存储在另外的服务提供者上的信息，而不需要将用户名和密码提供给第三方移动应用或分享他们数据的所有内容，OAuth2.0是OAuth协议的延续版本，但不向后兼容OAuth 1.0即完全废止了OAuth1.0。\n\n### 引用依赖\n```xml\n<dependency>\n    <groupId>org.springframework.cloud</groupId>\n    <artifactId>spring-cloud-starter-oauth2</artifactId>\n</dependency>\n```\n\n### 开发版本\n目前文档编写时基于当前最新版本，Spring Boot 2.3.5.RELEASE、Spring Security 5.3.5.RELEASE\n![](https://cdn.ckjia.com/20201103oauth2/%2A%2AWX20201103-210700%402x.png)\n\n## 认证模式\n客户端必须得到用户的授权（authorization grant），才能获得令牌（access token）。oAuth 2.0 定义了四种授权方式。\n\n- implicit：简化模式，不推荐使用\n- authorization code：授权码模式\n- resource owner password credentials：密码模式\n- client credentials：客户端模式\n![](https://cdn.ckjia.com/20201103oauth2/3d7bf0378d808ad1a1a3.png)\n> 具体四种认证模式请自行百度科普\n\n## 项目实战\n下面就以swagger的方式实战演示密码登录和短信验证码登录两种方式获取token。\n\n文档地址：https://gateway.mate.vip/doc.html\n### 1.密码方式登录\n选择mate-uaa文档->找到Oauth2管理->用户登录Post\n\n选择调试模式，并参考图例填写信息：\n![](https://cdn.ckjia.com/20201103oauth2/WX20201103-220225%402x.png)\n\n在请求头部，Authorization项填写：Basic bWF0ZTptYXRlX3NlY3JldA==,如下图所示：\n![](https://cdn.ckjia.com/20201103oauth2/WX20201103-220350%402x.png)\n填写完这两项，点击发送按钮，则成功从后台获取token。\n![](https://cdn.ckjia.com/20201103oauth2/WX20201103-220557%402x.png)\n\n### 2.短信验证码方式登录\n\n\n\n#### 2.1 下发验证码\n\nmate-uaa模块认证管理->手机验证码下发\n\n![](https://cdn.mate.vip/20201105/WX20201105-073448%402x.png)\n\n点击发送按钮后，提示发送成功。\n\n现代码里写成固定值：1188\n\n#### 2.2 获取Token\n\n设置grant_type为sms,则为短信验证码方式登录。\n\n![](https://cdn.mate.vip/20201105/WX20201105-073734%402x.png)\n\n点击发送按钮，获取token如下\n\n![](https://cdn.mate.vip/20201105/WX20201105-073901%402x.png)', '0', NULL, NULL, '2020-11-02 10:05:20', '2020-11-05 07:42:19'),
(10, 1688, 0, '快速开始', NULL, NULL, NULL, '0', NULL, NULL, '2020-11-04 05:56:45', '2020-11-04 05:56:45'),
(11, 1688, 10, 'Spring Cloud Alibaba简介', NULL, NULL, '## 1.概述\r\n2018 年 10 月 31 日的凌晨，这个伟大的日子里，Spring Cloud Alibaba 正式入驻了 Spring Cloud 官方孵化器，并在 Maven 中央库发布了第一个版本。\r\n\r\n[Spring Cloud for Alibaba 0.2.0 released](https://spring.io/blog/2018/10/30/spring-cloud-for-alibaba-0-2-0-released)\r\n>The Spring Cloud Alibaba project, consisting of Alibaba’s open-source components and several Alibaba Cloud products, aims to implement and expose well known Spring Framework patterns and abstractions to bring the benefits of Spring Boot and Spring Cloud to Java developers using Alibaba products.\r\n\r\n>Spring Cloud for Alibaba，它是由一些阿里巴巴的开源组件和云产品组成的。这个项目的目的是为了让大家所熟知的 Spring 框架，其优秀的设计模式和抽象理念，以给使用阿里巴巴产品的 Java 开发者带来使用 Spring Boot 和 Spring Cloud 的更多便利。\r\n\r\nSpringCloud Alibaba是阿里巴巴集团开源的一套微服务架构解决方案。\r\n\r\n微服务架构是为了更好的分布式系统开发，将一个应用拆分成多个子应用，每一个服务都是可以独立运行的子工程。其中涵盖了非常多的内容，包括：服务治理、配置管理、限流降级以及对阿里开源生态（Dubbo、RocketMQ等）支持的N多组件。\r\n\r\n### 官方地址\r\n[Spring Cloud Alibaba 官方地址](https://github.com/alibaba/spring-cloud-alibaba)\r\n\r\n## 2.相关组件\r\n- Sentinel：阿里巴巴开源产品，把流量作为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。\r\n- Nacos：阿里巴巴开源产品，一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。\r\n- RocketMQ：Apache RocketMQ™ 基于 Java 的高性能、高吞吐量的分布式消息和流计算平台。\r\n- Dubbo：Apache Dubbo™ 是一款高性能 Java RPC 框架。\r\n- Seata：阿里巴巴开源产品，一个易于使用的高性能微服务分布式事务解决方案。\r\n- Alibaba Cloud ACM：一款在分布式架构环境中对应用配置进行集中管理和推送的应用配置中心产品。\r\n- Alibaba Cloud OSS: 阿里云对象存储服务（Object Storage Service，简称 OSS），是阿里云提供的海量、安全、低成本、高可靠的云存储服务。您可以在任何应用、任何时间、任何地点存储和访问任意类型的数据。\r\n- Alibaba Cloud SchedulerX: 阿里中间件团队开发的一款分布式任务调度产品，提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。\r\n- Alibaba Cloud SMS: 覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。\r\n\r\n## 3.主要功能\r\n- 服务限流降级：默认支持 WebServlet、WebFlux, OpenFeign、RestTemplate、Spring Cloud Gateway, Zuul, Dubbo 和 RocketMQ 限流降级功能的接入，可以在运行时通过控制台实时修改限流降级规则，还支持查看限流降级 Metrics 监控。\r\n- 服务注册与发现：适配 Spring Cloud 服务注册与发现标准，默认集成了 Ribbon 的支持。\r\n- 分布式配置管理：支持分布式系统中的外部化配置，配置更改时自动刷新。\r\n- 消息驱动能力：基于 Spring Cloud Stream 为微服务应用构建消息驱动能力。\r\n- 分布式事务：使用 @GlobalTransactional 注解， 高效并且对业务零侵入地解决分布式事务问题。。\r\n- 阿里云对象存储：阿里云提供的海量、安全、低成本、高可靠的云存储服务。支持在任何应用、任何时间、任何地点存储和访问任意类型的数据。\r\n- 分布式任务调度：提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。同时提供分布式的任务执行模型，如网格任务。网格任务支持海量子任务均匀分配到所有 Worker（schedulerx-client）上执行。\r\n- 阿里云短信服务：覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。\r\n\r\n## 4.版本说明\r\n```xml\r\n<dependencyManagement>\r\n    <dependencies>\r\n        <dependency>\r\n            <groupId>com.alibaba.cloud</groupId>\r\n            <artifactId>spring-cloud-alibaba-dependencies</artifactId>\r\n            <version>2.2.3.RELEASE</version>\r\n            <type>pom</type>\r\n            <scope>import</scope>\r\n        </dependency>\r\n    </dependencies>\r\n</dependencyManagement>\r\n```\r\n项目的版本号格式为 x.x.x 的形式，其中 x 的数值类型为数字，从 0 开始取值，且不限于 0~9 这个范围。项目处于孵化器阶段时，第一位版本号固定使用 0，即版本号为 0.x.x 的格式。\r\n\r\n由于 Spring Boot 1 和 Spring Boot 2 在 Actuator 模块的接口和注解有很大的变更，且 spring-cloud-commons 从 1.x.x 版本升级到 2.0.0 版本也有较大的变更，因此我们采取跟 SpringBoot 版本号一致的版本:\r\n\r\n- 1.5.x 版本适用于 Spring Boot 1.5.x\r\n- 2.0.x 版本适用于 Spring Boot 2.0.x\r\n- 2.1.x 版本适用于 Spring Boot 2.1.x\r\n- 2.2.x 版本适用于 Spring Boot 2.2.x\r\n', '0', NULL, NULL, '2020-11-04 06:14:17', '2020-11-04 06:14:17'),
(12, 1688, 10, 'Spring Cloud Alibaba项目初始化', NULL, NULL, '## 统一依赖\n\nMateCloud对项目依赖进行了统一的封装，引用此依赖，就可以实现微服务基础依赖的一键引入\n\n### POM项目路径：\nhttps://gitee.com/matevip/matecloud/blob/master/mate-core/mate-starter-dependencies/pom.xml\n\n### POM文件内容\n```xml\n<?xml version="1.0" encoding="UTF-8"?>\n<project xmlns="http://maven.apache.org/POM/4.0.0"\n         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"\n         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">\n    <parent>\n        <groupId>org.springframework.boot</groupId>\n        <artifactId>spring-boot-starter-parent</artifactId>\n        <version>2.3.5.RELEASE</version>\n        <relativePath />\n    </parent>\n    <modelVersion>4.0.0</modelVersion>\n\n    <groupId>vip.mate</groupId>\n    <artifactId>mate-starter-dependencies</artifactId>\n    <version>1.5.8</version>\n    <packaging>pom</packaging>\n    <description>MateCloud统一版本依赖</description>\n\n    <properties>\n        <matecloud.core.version>1.5.8</matecloud.core.version>\n        <maven.compiler.encoding>UTF-8</maven.compiler.encoding>\n\n        <spring.boot.version>2.3.5.RELEASE</spring.boot.version>\n        <spring.cloud.version>Hoxton.SR8</spring.cloud.version>\n        <alibaba.cloud.version>2.2.3.RELEASE</alibaba.cloud.version>\n\n        <maven-compiler-plugin.version>3.6.2</maven-compiler-plugin.version>\n        <maven-resources-plugin.version>3.1.0</maven-resources-plugin.version>\n        <maven-source-plugin.version>3.0.1</maven-source-plugin.version>\n        <maven-surefire-plugin.version>2.22.0</maven-surefire-plugin.version>\n        <maven-assembly-plugin.version>3.1.0</maven-assembly-plugin.version>\n        <maven-deploy-plugin.version>2.8.2</maven-deploy-plugin.version>\n        <maven-dockerfile-plugin.version>1.4.10</maven-dockerfile-plugin.version>\n\n        <mysql.connector.version>8.0.21</mysql.connector.version>\n        <oracle.connector.version>12.2.0.1</oracle.connector.version>\n        <postgresql.connector.version>42.2.6</postgresql.connector.version>\n        <druid.version>1.2.1</druid.version>\n        <mybatis.plus.version>3.4.0</mybatis.plus.version>\n        <velocity.version>1.7</velocity.version>\n\n        <swagger.version>2.9.2</swagger.version>\n        <swagger.models.version>1.6.2</swagger.models.version>\n        <knife4j.version>2.0.5</knife4j.version>\n        <jjwt.version>0.9.1</jjwt.version>\n        <easy-captcha.version>1.6.2</easy-captcha.version>\n        <jakarta.validation-api.version>2.0.2</jakarta.validation-api.version>\n        <jetcache.version>2.6.0</jetcache.version>\n\n        <!-- Apache Dubbo -->\n        <dubbo.version>2.7.8</dubbo.version>\n        <fastjson.version>1.2.74</fastjson.version>\n\n        <spring-boot-admin.version>2.3.0</spring-boot-admin.version>\n        <poi.version>4.1.2</poi.version>\n        <nacos.client.version>1.3.2</nacos.client.version>\n        <easypoi.version>4.2.0</easypoi.version>\n        <ip2region.version>1.7.2</ip2region.version>\n        <justauth.version>1.15.6</justauth.version>\n        <justauth.springboot.version>1.3.3</justauth.springboot.version>\n        <okhttp.version>4.9.0</okhttp.version>\n\n        <servlet.version>2.5</servlet.version>\n        <sharding-jdbc.version>4.1.1</sharding-jdbc.version>\n\n        <elastic.version>7.8.0</elastic.version>\n        <commons-collections.version>3.2.2</commons-collections.version>\n\n        <dozer.version>6.5.0</dozer.version>\n        <redisson.version>3.13.6</redisson.version>\n        <lettuce.version>5.3.3.RELEASE</lettuce.version>\n        <sentinel.version>1.8.0</sentinel.version>\n        <transmittable.version>2.11.5</transmittable.version>\n        <commons-io.version>2.8.0</commons-io.version>\n        <rocketmq.version>4.7.1</rocketmq.version>\n        <prometheus.version>1.5.5</prometheus.version>\n\n        <jasypt.version>3.0.3</jasypt.version>\n\n        <retrofit.version>2.1.8</retrofit.version>\n        <j2cache.version>2.8.2-release</j2cache.version>\n    </properties>\n\n    <dependencyManagement>\n        <dependencies>\n            <!-- MateCloud Core -->\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-common</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-cloud</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-database</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-dubbo</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-security</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-feign</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-web</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-auth</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-dependencies</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-jetcache</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-log</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-gray</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-redis</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-oss</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-rocketmq</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-kafka</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-file</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-dozer</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-rule</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-sentinel</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-prometheus</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-job</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-starter-mail</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-system-api</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>${project.groupId}</groupId>\n                <artifactId>mate-component-api</artifactId>\n                <version>${matecloud.core.version}</version>\n            </dependency>\n            <!-- 微服务基础包 -->\n            <dependency>\n                <groupId>org.springframework.boot</groupId>\n                <artifactId>spring-boot-dependencies</artifactId>\n                <version>${spring.boot.version}</version>\n                <type>pom</type>\n                <scope>import</scope>\n            </dependency>\n            <dependency>\n                <groupId>org.springframework.cloud</groupId>\n                <artifactId>spring-cloud-dependencies</artifactId>\n                <version>${spring.cloud.version}</version>\n                <type>pom</type>\n                <scope>import</scope>\n            </dependency>\n            <dependency>\n                <groupId>com.alibaba.cloud</groupId>\n                <artifactId>spring-cloud-alibaba-dependencies</artifactId>\n                <version>${alibaba.cloud.version}</version>\n                <type>pom</type>\n                <scope>import</scope>\n            </dependency>\n            <!-- Druid -->\n            <dependency>\n                <groupId>com.alibaba</groupId>\n                <artifactId>druid-spring-boot-starter</artifactId>\n                <version>${druid.version}</version>\n            </dependency>\n            <!-- MySql -->\n            <dependency>\n                <groupId>mysql</groupId>\n                <artifactId>mysql-connector-java</artifactId>\n                <version>${mysql.connector.version}</version>\n            </dependency>\n            <!-- Oracle -->\n            <dependency>\n                <groupId>com.oracle</groupId>\n                <artifactId>ojdbc7</artifactId>\n                <version>${oracle.connector.version}</version>\n            </dependency>\n            <!-- PostgreSql -->\n            <dependency>\n                <groupId>org.postgresql</groupId>\n                <artifactId>postgresql</artifactId>\n                <version>${postgresql.connector.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.baomidou</groupId>\n                <artifactId>mybatis-plus-boot-starter</artifactId>\n                <version>${mybatis.plus.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.baomidou</groupId>\n                <artifactId>mybatis-plus-annotation</artifactId>\n                <version>${mybatis.plus.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.baomidou</groupId>\n                <artifactId>mybatis-plus-extension</artifactId>\n                <version>${mybatis.plus.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.baomidou</groupId>\n                <artifactId>mybatis-plus-generator</artifactId>\n                <version>${mybatis.plus.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>org.apache.velocity</groupId>\n                <artifactId>velocity</artifactId>\n                <version>${velocity.version}</version>\n            </dependency>\n            <!--Swagger-->\n            <dependency>\n                <groupId>io.springfox</groupId>\n                <artifactId>springfox-swagger2</artifactId>\n                <version>${swagger.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>io.swagger</groupId>\n                <artifactId>swagger-models</artifactId>\n                <version>${swagger.models.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.github.xiaoymin</groupId>\n                <artifactId>knife4j-micro-spring-boot-starter</artifactId>\n                <version>${knife4j.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.github.xiaoymin</groupId>\n                <artifactId>knife4j-spring-ui</artifactId>\n                <version>${knife4j.version}</version>\n            </dependency>\n            <!-- JWT -->\n            <dependency>\n                <groupId>io.jsonwebtoken</groupId>\n                <artifactId>jjwt</artifactId>\n                <version>${jjwt.version}</version>\n            </dependency>\n            <!-- captcha -->\n            <dependency>\n                <groupId>com.github.whvcse</groupId>\n                <artifactId>easy-captcha</artifactId>\n                <version>${easy-captcha.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>jakarta.validation</groupId>\n                <artifactId>jakarta.validation-api</artifactId>\n                <version>${jakarta.validation-api.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.alibaba</groupId>\n                <artifactId>fastjson</artifactId>\n                <version>${fastjson.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.alicp.jetcache</groupId>\n                <artifactId>jetcache-starter-redis</artifactId>\n                <version>${jetcache.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.alicp.jetcache</groupId>\n                <artifactId>jetcache-starter-redis-lettuce</artifactId>\n                <version>${jetcache.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>de.codecentric</groupId>\n                <artifactId>spring-boot-admin-starter-server</artifactId>\n                <version>${spring-boot-admin.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>de.codecentric</groupId>\n                <artifactId>spring-boot-admin-server-ui</artifactId>\n                <version>${spring-boot-admin.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>de.codecentric</groupId>\n                <artifactId>spring-boot-admin-starter-client</artifactId>\n                <version>${spring-boot-admin.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>org.apache.poi</groupId>\n                <artifactId>poi</artifactId>\n                <version>${poi.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.alibaba.nacos</groupId>\n                <artifactId>nacos-client</artifactId>\n                <version>${nacos.client.version}</version>\n            </dependency>\n            <!-- easypoi -->\n            <dependency>\n                <groupId>cn.afterturn</groupId>\n                <artifactId>easypoi-base</artifactId>\n                <version>${easypoi.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>cn.afterturn</groupId>\n                <artifactId>easypoi-web</artifactId>\n                <version>${easypoi.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>cn.afterturn</groupId>\n                <artifactId>easypoi-annotation</artifactId>\n                <version>${easypoi.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>org.apache.dubbo</groupId>\n                <artifactId>dubbo</artifactId>\n                <version>${dubbo.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>org.apache.dubbo</groupId>\n                <artifactId>dubbo-spring-boot-starter</artifactId>\n                <version>${dubbo.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>org.lionsoul</groupId>\n                <artifactId>ip2region</artifactId>\n                <version>${ip2region.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>me.zhyd.oauth</groupId>\n                <artifactId>JustAuth</artifactId>\n                <version>${justauth.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.xkcoding.justauth</groupId>\n                <artifactId>justauth-spring-boot-starter</artifactId>\n                <version>${justauth.springboot.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.squareup.okhttp3</groupId>\n                <artifactId>okhttp</artifactId>\n                <version>${okhttp.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>javax.servlet</groupId>\n                <artifactId>servlet-api</artifactId>\n                <version>${servlet.version}</version>\n            </dependency>\n\n            <!--shardingsphere的springboot依赖jar-->\n            <dependency>\n                <groupId>org.apache.shardingsphere</groupId>\n                <artifactId>sharding-jdbc-spring-boot-starter</artifactId>\n                <version>${sharding-jdbc.version}</version>\n            </dependency>\n            <!-- ElasticSearch依赖包-->\n            <dependency>\n                <groupId>org.elasticsearch.client</groupId>\n                <artifactId>elasticsearch-rest-high-level-client</artifactId>\n                <version>${elastic.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>org.elasticsearch</groupId>\n                <artifactId>elasticsearch</artifactId>\n                <version>${elastic.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>commons-collections</groupId>\n                <artifactId>commons-collections</artifactId>\n                <version>${commons-collections.version}</version>\n            </dependency>\n\n            <!-- 对象转换 -->\n            <dependency>\n                <groupId>com.github.dozermapper</groupId>\n                <artifactId>dozer-core</artifactId>\n                <version>${dozer.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.github.dozermapper</groupId>\n                <artifactId>dozer-spring4</artifactId>\n                <version>${dozer.version}</version>\n            </dependency>\n            <!-- redisson -->\n            <dependency>\n                <groupId>org.redisson</groupId>\n                <artifactId>redisson</artifactId>\n                <version>${redisson.version}</version>\n            </dependency>\n            <!-- lettuce-core -->\n            <dependency>\n                <groupId>io.lettuce</groupId>\n                <artifactId>lettuce-core</artifactId>\n                <version>${lettuce.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.alibaba</groupId>\n                <artifactId>transmittable-thread-local</artifactId>\n                <version>${transmittable.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>commons-io</groupId>\n                <artifactId>commons-io</artifactId>\n                <version>${commons-io.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>org.apache.rocketmq</groupId>\n                <artifactId>rocketmq-client</artifactId>\n                <version>${rocketmq.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>org.apache.rocketmq</groupId>\n                <artifactId>rocketmq-acl</artifactId>\n                <version>${rocketmq.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>io.micrometer</groupId>\n                <artifactId>micrometer-registry-prometheus</artifactId>\n                <version>${prometheus.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.github.ulisesbocchio</groupId>\n                <artifactId>jasypt-spring-boot-starter</artifactId>\n                <version>${jasypt.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>com.github.lianjiatech</groupId>\n                <artifactId>retrofit-spring-boot-starter</artifactId>\n                <version>${retrofit.version}</version>\n            </dependency>\n            <dependency>\n                <groupId>net.oschina.j2cache</groupId>\n                <artifactId>j2cache-core</artifactId>\n                <version>${j2cache.version}</version>\n            </dependency>\n        </dependencies>\n    </dependencyManagement>\n\n    <!-- 发布项目到 Nexus -->\n    <distributionManagement>\n        <repository>\n            <id>releases</id>\n            <name>Release Repository</name>\n            <url>http://nexus.mate.vip/repository/maven-releases/</url>\n        </repository>\n        <snapshotRepository>\n            <id>nexus-snapshots</id>\n            <url>http://nexus.mate.vip/repository/maven-snapshots/</url>\n        </snapshotRepository>\n    </distributionManagement>\n\n</project>\n````\n\n## 核心依赖包\n```xml\n<dependency>\n    <groupId>org.springframework.boot</groupId>\n    <artifactId>spring-boot-dependencies</artifactId>\n    <version>2.3.5.RELEASE</version>\n    <type>pom</type>\n    <scope>import</scope>\n</dependency>\n<dependency>\n    <groupId>org.springframework.cloud</groupId>\n    <artifactId>spring-cloud-dependencies</artifactId>\n    <version>Hoxton.SR8</version>\n    <type>pom</type>\n    <scope>import</scope>\n</dependency>\n<dependency>\n    <groupId>com.alibaba.cloud</groupId>\n    <artifactId>spring-cloud-alibaba-dependencies</artifactId>\n    <version>2.2.3.RELEASE</version>\n    <type>pom</type>\n    <scope>import</scope>\n</dependency>\n```', '0', NULL, NULL, '2020-11-04 06:23:54', '2020-11-04 06:24:36'),
(13, 1688, 0, '注册配置', NULL, NULL, NULL, '0', NULL, NULL, '2020-11-04 06:27:14', '2020-11-04 06:30:13'),
(15, 1688, 13, 'Nacos注册配置中心', NULL, NULL, '## 概述\nNacos 致力于帮助您发现、配置和管理微服务。Nacos 提供了一组简单易用的特性集，帮助您快速实现动态服务发现、服务配置、服务元数据及流量管理。\n\nNacos 帮助您更敏捷和容易地构建、交付和管理微服务平台。 Nacos 是构建以“服务”为中心的现代应用架构 (例如微服务范式、云原生范式) 的服务基础设施。\n\n### Nacos官网\nhttps://nacos.io/zh-cn/index.html\n\n## 关键特性\n服务（Service）是 Nacos 世界的一等公民。Nacos 支持几乎所有主流类型的“服务”的发现、配置和管理：\n\n[Kubernetes Service](https://kubernetes.io/docs/concepts/services-networking/service/)\n\n[gRPC](https://grpc.io/docs/what-is-grpc/core-concepts/#service-definition) & [Dubbo RPC Service](https://dubbo.incubator.apache.org/en-us/)\n\n[Spring Cloud RESTful Service](https://spring.io/understanding/REST)\n- 服务发现和服务健康监测\n\nNacos 支持基于 DNS 和基于 RPC 的服务发现。服务提供者使用 原生SDK、OpenAPI、或一个独立的Agent TODO注册 Service 后，服务消费者可以使用DNS TODO 或HTTP&API查找和发现服务。\n\nNacos 提供对服务的实时的健康检查，阻止向不健康的主机或服务实例发送请求。Nacos 支持传输层 (PING 或 TCP)和应用层 (如 HTTP、MySQL、用户自定义）的健康检查。 对于复杂的云环境和网络拓扑环境中（如 VPC、边缘网络等）服务的健康检查，Nacos 提供了 agent 上报模式和服务端主动检测2种健康检查模式。Nacos 还提供了统一的健康检查仪表盘，帮助您根据健康状态管理服务的可用性及流量。\n\n- 动态配置服务\n\n动态配置服务可以让您以中心化、外部化和动态化的方式管理所有环境的应用配置和服务配置。\n\n动态配置消除了配置变更时重新部署应用和服务的需要，让配置管理变得更加高效和敏捷。\n\n配置中心化管理让实现无状态服务变得更简单，让服务按需弹性扩展变得更容易。\n\nNacos 提供了一个简洁易用的UI ([控制台样例 Demo](http://console.nacos.io/nacos/index.html)) 帮助您管理所有的服务和应用的配置。Nacos 还提供包括配置版本跟踪、金丝雀发布、一键回滚配置以及客户端配置更新状态跟踪在内的一系列开箱即用的配置管理特性，帮助您更安全地在生产环境中管理配置变更和降低配置变更带来的风险。\n\n- 动态 DNS 服务\n\n动态 DNS 服务支持权重路由，让您更容易地实现中间层负载均衡、更灵活的路由策略、流量控制以及数据中心内网的简单DNS解析服务。动态DNS服务还能让您更容易地实现以 DNS 协议为基础的服务发现，以帮助您消除耦合到厂商私有服务发现 API 上的风险。\n\nNacos 提供了一些简单的 DNS APIs TODO 帮助您管理服务的关联域名和可用的 IP:PORT 列表.\n\n- 服务及其元数据管理\n\nNacos 能让您从微服务平台建设的视角管理数据中心的所有服务及元数据，包括管理服务的描述、生命周期、服务的静态依赖分析、服务的健康状态、服务的流量管理、路由及安全策略、服务的 SLA 以及最首要的 metrics 统计数据。\n\n## Nacos地图\n![](https://nacos.io/img/nacosMap.jpg)\n\n- 特性大图：要从功能特性，非功能特性，全面介绍我们要解的问题域的特性诉求\n- 架构大图：通过清晰架构，让您快速进入 Nacos 世界\n- 业务大图：利用当前特性可以支持的业务场景，及其最佳实践\n- 生态大图：系统梳理 Nacos 和主流技术生态的关系\n- 优势大图：展示 Nacos 核心竞争力\n- 战略大图：要从战略到战术层面讲 Nacos 的宏观优势\n\n## Nacos生态图\n\n![](http://cdn.mate.vip/20201104/1533045871534-e64b8031-008c-4dfc-b6e8-12a597a003fb.png)\n\n## 基本架构及概念\n\n![](http://cdn.mate.vip/20201104/1561217892717-1418fb9b-7faa-4324-87b9-f1740329f564.jpeg)\n\n### 服务 (Service)\n服务是指一个或一组软件功能（例如特定信息的检索或一组操作的执行），其目的是不同的客户端可以为不同的目的重用（例如通过跨进程的网络调用）。Nacos 支持主流的服务生态，如 Kubernetes Service、gRPC|Dubbo RPC Service 或者 Spring Cloud RESTful Service.\n\n### 服务注册中心 (Service Registry)\n服务注册中心，它是服务，其实例及元数据的数据库。服务实例在启动时注册到服务注册表，并在关闭时注销。服务和路由器的客户端查询服务注册表以查找服务的可用实例。服务注册中心可能会调用服务实例的健康检查 API 来验证它是否能够处理请求。\n\n### 服务元数据 (Service Metadata)\n服务元数据是指包括服务端点(endpoints)、服务标签、服务版本号、服务实例权重、路由规则、安全策略等描述服务的数据\n\n### 服务提供方 (Service Provider)\n是指提供可复用和可调用服务的应用方\n\n### 服务消费方 (Service Consumer)\n是指会发起对某个服务调用的应用方\n\n### 配置 (Configuration)\n在系统开发过程中通常会将一些需要变更的参数、变量等从代码中分离出来独立管理，以独立的配置文件的形式存在。目的是让静态的系统工件或者交付物（如 WAR，JAR 包等）更好地和实际的物理运行环境进行适配。配置管理一般包含在系统部署的过程中，由系统管理员或者运维人员完成这个步骤。配置变更是调整系统运行时的行为的有效手段之一。\n\n### 配置管理 (Configuration Management)\n在数据中心中，系统中所有配置的编辑、存储、分发、变更管理、历史版本管理、变更审计等所有与配置相关的活动统称为配置管理。\n\n### 名字服务 (Naming Service)\n提供分布式系统中所有对象(Object)、实体(Entity)的“名字”到关联的元数据之间的映射管理服务，例如 ServiceName -> Endpoints Info, Distributed Lock Name -> Lock Owner/Status Info, DNS Domain Name -> IP List, 服务发现和 DNS 就是名字服务的2大场景。\n\n### 配置服务 (Configuration Service)\n在服务或者应用运行过程中，提供动态配置或者元数据以及配置管理的服务提供者。\n\n## 逻辑架构及组件介绍\n![](http://cdn.mate.vip/1561217775318-6e408805-18bb-4242-b4e9-83c5b929b469.png)\n\n- 服务管理：实现服务CRUD，域名CRUD，服务健康状态检查，服务权重管理等功能\n- 配置管理：实现配置管CRUD，版本管理，灰度管理，监听管理，推送轨迹，聚合数据等功能\n- 元数据管理：提供元数据CURD 和打标能力\n- 插件机制：实现三个模块可分可合能力，实现扩展点SPI机制\n- 事件机制：实现异步化事件通知，sdk数据变化异步通知等逻辑\n- 日志模块：管理日志分类，日志级别，日志可移植性（尤其避免冲突），日志格式，异常码+帮助文档\n- 回调机制：sdk通知数据，通过统一的模式回调用户处理。接口和数据结构需要具备可扩展性\n- 寻址模式：解决ip，域名，nameserver、广播等多种寻址模式，需要可扩展\n- 推送通道：解决server与存储、server间、server与sdk间推送性能问题\n- 容量管理：管理每个租户，分组下的容量，防止存储被写爆，影响服务可用性\n- 流量管理：按照租户，分组等多个维度对请求频率，长链接个数，报文大小，请求流控进行控制\n- 缓存机制：容灾目录，本地缓存，server缓存机制。容灾目录使用需要工具\n- 启动模式：按照单机模式，配置模式，服务模式，dns模式，或者all模式，启动不同的程序+UI\n- 一致性协议：解决不同数据，不同一致性要求情况下，不同一致性机制\n- 存储模块：解决数据持久化、非持久化存储，解决数据分片问题\n- Nameserver：解决namespace到clusterid的路由问题，解决用户环境与nacos物理环境映射问题\n- CMDB：解决元数据存储，与三方cmdb系统对接问题，解决应用，人，资源关系\n- Metrics：暴露标准metrics数据，方便与三方监控系统打通\n- Trace：暴露标准trace，方便与SLA系统打通，日志白平化，推送轨迹等能力，并且可以和计量计费系统打通\n- 接入管理：相当于阿里云开通服务，分配身份、容量、权限过程\n- 用户管理：解决用户管理，登录，sso等问题\n- 权限管理：解决身份识别，访问控制，角色管理等问题\n- 审计系统：扩展接口方便与不同公司审计系统打通\n- 通知系统：核心数据变更，或者操作，方便通过SMS系统打通，通知到对应人数据变更\n- OpenAPI：暴露标准Rest风格HTTP接口，简单易用，方便多语言集成\n- Console：易用控制台，做服务管理、配置管理等操作\n- SDK：多语言sdk\n- Agent：dns-f类似模式，或者与mesh等方案集成\n- CLI：命令行对产品进行轻量化管理，像git一样好用\n\n## 领域模型\n### 数据模型\n\nNacos 数据模型 Key 由三元组唯一确定, Namespace默认是空串，公共命名空间（public），分组默认是 DEFAULT_GROUP。\n\n![](http://cdn.mate.vip/20201103/1561217857314-95ab332c-acfb-40b2-957a-aae26c2b5d71.jpeg)\n\n### 服务领域模型\n\n![](http://cdn.mate.vip/20201104/1561217924697-ba504a35-129f-4fc6-b0df-1130b995375a.jpeg)\n\n### 配置领域模型\n\n围绕配置，主要有两个关联的实体，一个是配置变更历史，一个是服务标签（用于打标分类，方便索引），由 ID 关联。\n\n![](http://cdn.mate.vip/20201104/1561217958896-4465757f-f588-4797-9c90-a76e604fabb4.jpeg)\n\n## 类视图\n### Nacos-SDK 类视图\n\n![](http://cdn.mate.vip/1561218077514-bfa98d03-88a1-43b9-b014-1491406e3db7.jpeg)\n\n## 构建物、部署及启动模式\n![](http://cdn.mate.vip/20201104/1531730742844-e8325932-258b-49b2-9473-8d1199efe20d.png)', '0', NULL, NULL, '2020-11-04 06:52:26', '2020-11-04 06:53:30'),
(16, 1688, 13, 'Nacos安装与部署', NULL, NULL, '## 概述\r\n本文将以本地安装和docker安装两种方式介绍。并简单介绍集成MateCloud的配置文件的方式。\r\n\r\n## 1.本地安装\r\n### 下载源码包安装\r\n参见：https://nacos.io/zh-cn/docs/quick-start.html\r\n\r\n### 下载编译后的压缩包\r\n从官方下载最新稳定包的tar.gz和zip包:\r\nhttps://github.com/alibaba/nacos/releases\r\n\r\n如：nacos-server-1.4.0.tar.gz　或者　nacos-server-1.4.0.zip\r\n\r\n下载完成执行如下操作，如果是windows直接用，如果是linux或者mac,则执行如下操作：\r\n```bash\r\nunzip nacos-server-$version.zip 或者 tar -xvf nacos-server-$version.tar.gz\r\n\r\ncd nacos/bin\r\n```\r\n\r\n### 启动服务器\r\n#### Linux/Unix/Mac\r\n\r\n>启动命令(standalone代表着单机模式运行，非集群模式):\r\n\r\n```bash\r\nsh startup.sh -m standalone\r\n```\r\n\r\n>如果您使用的是ubuntu系统，或者运行脚本报错提示[[符号找不到，可尝试如下运行：\r\n\r\n```bash\r\nbash startup.sh -m standalone\r\n```\r\n\r\n#### Windows\r\n>启动命令(standalone代表着单机模式运行，非集群模式):\r\n\r\n```bash\r\ncmd startup.cmd -m standalone\r\n```\r\n\r\n### 关闭服务器\r\n\r\n>Linux/Unix/Mac\r\n\r\n```bash\r\nsh shutdown.sh\r\n```\r\n\r\n>Windows\r\n\r\n```bash\r\ncmd shutdown.cmd\r\n```\r\n\r\n或者双击shutdown.cmd运行文件。\r\n\r\n## 2.Docker方式安装\r\n\r\n### 下载docker安装包\r\n\r\n```bash\r\ngit clone https://github.com/nacos-group/nacos-docker.git\r\n\r\ncd nacos-docker\r\n```\r\n\r\n### 单机模式\r\n\r\n```bash\r\ndocker-compose example/standalone-mysql-5.7.yaml up -d\r\n```\r\n\r\n### 查看日志\r\n```bash\r\ndocker-compose -f example/standalone-mysql.yaml logs -f\r\n```\r\n\r\n## Nacos控制台\r\n\r\n本地登录地址：\r\n\r\nhttp://localhost:8848/nacos\r\n\r\n### 登录界面\r\n\r\nhttp://gateway.mate.vip:8848/\r\n\r\n### 默认账号密码\r\n\r\n- nacos/nacos', '0', NULL, NULL, '2020-11-04 18:33:25', '2020-11-04 18:33:25'),
(17, 1, 0, '增强功能', NULL, NULL, NULL, '0', NULL, NULL, '2020-11-21 19:14:07', '2020-11-21 19:14:07'),
(18, 1, 17, '基于Druid开启对数据库密码进行加密', NULL, NULL, '## 使用背景\n\n由于数据库的密码在配置文件中，为了加强安全性，需要对数据库的密码进行加密存储，以防密码泄漏，增加了密码被破解的难度。\n\n## 如何启用加密\n\n### 查看文件\n```bash\nmatecloud/mate-core/mate-starter-database/src/main/resources/mate-decrypt.properties\n```\n\n### 配置项\n\n```bash\n# 配置 connection-properties，启用加密，配置公钥。\nspring.datasource.druid.connection-properties=config.decrypt=true;config.decrypt.key=${mate.public-key}\n# 启动ConfigFilter\nspring.datasource.druid.filter.config.enabled=true\n```\n\n其中mate.public-key这个参数配置在mate-local.yaml文件,由于密码与公钥如果放在一起，破解系数也降低了，也可以放在其他配置文件中，并存储在nacos中。\n\n## 如何对密码进行加密\n\n先搜索PasswordGenerator.java类，全路径如下：\n```bash\nmatecloud/mate-core/mate-starter-database/src/test/java/vip/mate/core/database/test/PasswordGenerator.java\n```\n\n核心代码如下：\n```java\n@Slf4j\npublic class PasswordGenerator {\n\n public static void main(String[] args) throws Exception {\n\n   String password = "root";\n   String[] arr = ConfigTools.genKeyPair(512);\n   log.info("privateKey: {}", arr[0]);\n   log.info("publicKey: {}", arr[1]);\n    log.info("password: {}", ConfigTools.encrypt(arr[0], password));\n  }\n}\n```\n将password的变量修改为你当前的密码，然后点击生成，然后取publicKey和password,修改nacos中的mate-local.yaml文件。\n\n```yml\nmate:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/matex?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull\n    username: root\n    password: RwEWGkJYrm11nKDIBq6TcN1IcVlAJ2WB4RhY4AoD86ZWyfbaEKObPTGArDErCpkaiavXMqOnn7sSIbq7kwe4/w==\n  public-key: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJLLURUH8XNPkX9MME3mQrog3mpkOVYwnYrUqLbSN+Wi6IcmWg1YywHi/kKGUU1MTLjv3C406f1IYu+XWJ3XmX8CAwEAAQ==\n\n```\n修改完成后，重新启动应用即可生效\n\n## 官方文档\n\n### 使用ConfigFilter\nhttps://github.com/alibaba/druid/wiki/%E4%BD%BF%E7%94%A8ConfigFilter\n\n### 如何在Spring Boot中配置数据库密码加密？\nhttps://github.com/alibaba/druid/wiki/%E5%A6%82%E4%BD%95%E5%9C%A8Spring-Boot%E4%B8%AD%E9%85%8D%E7%BD%AE%E6%95%B0%E6%8D%AE%E5%BA%93%E5%AF%86%E7%A0%81%E5%8A%A0%E5%AF%86%EF%BC%9F\n', '0', NULL, NULL, '2020-11-21 19:14:42', '2020-11-21 19:15:44');

-- --------------------------------------------------------

--
-- 表的结构 `mate_duben_set`
--

CREATE TABLE IF NOT EXISTS `mate_duben_set` (
  `id` bigint(20) NOT NULL COMMENT '文档集id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `sub_title` varchar(255) DEFAULT NULL COMMENT '子标题',
  `price` decimal(10,2) DEFAULT NULL COMMENT '文档价格',
  `origin_price` decimal(10,2) DEFAULT NULL COMMENT '原始价格',
  `image` varchar(255) DEFAULT NULL COMMENT '集照片',
  `summary` varchar(255) DEFAULT NULL COMMENT '集摘要',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=1689 DEFAULT CHARSET=utf8mb4 COMMENT='读本集';

--
-- 转存表中的数据 `mate_duben_set`
--

INSERT INTO `mate_duben_set` (`id`, `title`, `sub_title`, `price`, `origin_price`, `image`, `summary`, `del_flag`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES
(1, 'MATECLOUD开源项目文档', 'MATECLOUD开发部署文档', NULL, NULL, 'https://cdn.ckjia.com/matecloud_doc1.jpg', 'MateCloud是一款基于Spring Cloud Alibaba的微服务架构。目前已经整合Spring Cloud Alibaba、Spring Security Oauth2、Feign、Dubbo、JetCache、RocketMQ等服务套件，为您的开发保驾护航', '0', NULL, NULL, '2020-10-25 08:10:18', '2020-10-26 13:31:07'),
(1688, '微服务架构之阿里巴巴篇', 'Spring Cloud Alibaba技术体系介绍', NULL, NULL, 'https://cdn.mate.vip/alibaba_dbbb20be7e97100_14586.jpg', '包括阿里巴巴套件之nacos、sentinel、seata等产品介绍', '0', NULL, NULL, '2020-11-04 05:54:17', '2020-11-04 05:55:40');

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_api`
--

CREATE TABLE IF NOT EXISTS `mate_sys_api` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `code` varchar(255) NOT NULL COMMENT '接口编码',
  `name` varchar(100) DEFAULT NULL COMMENT '接口名称',
  `notes` varchar(200) DEFAULT NULL COMMENT '接口描述',
  `method` varchar(20) DEFAULT NULL COMMENT '请求方法',
  `class_name` varchar(255) DEFAULT NULL COMMENT '类名',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `path` varchar(255) DEFAULT NULL COMMENT '请求路径',
  `content_type` varchar(100) DEFAULT NULL COMMENT '响应类型',
  `service_id` varchar(100) DEFAULT NULL COMMENT '服务ID',
  `status` char(1) DEFAULT '1' COMMENT 'API状态:0:禁用 1:启用',
  `auth` char(1) DEFAULT '0' COMMENT '是否认证:0:不认证 1:认证',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID'
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COMMENT='系统接口表';

--
-- 转存表中的数据 `mate_sys_api`
--

INSERT INTO `mate_sys_api` (`id`, `code`, `name`, `notes`, `method`, `class_name`, `method_name`, `path`, `content_type`, `service_id`, `status`, `auth`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`, `tenant_id`) VALUES
(1, '4d1d7152e5ba14f3b17b51aa6f5c3fe8', '数据源项列表', '数据源项列表', 'GET', 'vip.mate.code.controller.SysDataSourceController', 'optionList', '/data-source/option-list', '', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(2, '0cd169937833856e39b1781b4576aa0c', '数据源删除', '数据源删除', 'POST', 'vip.mate.code.controller.SysDataSourceController', 'del', '/data-source/del', '', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(3, 'af13b998a6fd5d4b4890502ea09e757b', '数据源分页', '数据源分页', 'GET', 'vip.mate.code.controller.SysDataSourceController', 'page', '/data-source/page', '', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(4, 'ff77ff46d88dc18d1db143e507a8b9ec', '数据源信息', '数据源信息,根据ID查询', 'GET', 'vip.mate.code.controller.SysDataSourceController', 'get', '/data-source/get', '', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(5, 'f0971e6df527eede4f0cf2a9a6554a4b', '数据源设置', '数据源设置,支持新增或修改', 'POST', 'vip.mate.code.controller.SysDataSourceController', 'set', '/data-source/set', 'application/json;charset=UTF-8', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(6, '6a3bbd4c88d34f7999494f4d1cdb68bd', '数据库表信息', '数据库表信息', 'POST', 'vip.mate.code.controller.SysCodeController', 'tableList', '/code/table-list', '', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(7, 'eaed145ea5bc1a75389e29eb8862f46d', '代码生成', '代码生成', 'POST', 'vip.mate.code.controller.SysCodeController', 'execute', '/code/generator-code', '', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(8, '1c01329d9081dd24f43521a7355c3131', '用户登录Post', '用户登录Post', 'POST', 'vip.mate.uaa.controller.OauthController', 'postAccessToken', '/oauth/token', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', '2020-10-20 09:12:55', '0', NULL),
(9, 'aba8bdcbcb92e87e8dd3b1eca4581eb1', '用户信息', '用户信息', 'GET', 'vip.mate.uaa.controller.AuthController', 'getUser', '/auth/get/user', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(10, '6ae701fa710ee54285a2a874943929de', '第三方登录回调', '第三方登录回调', 'GET', 'vip.mate.uaa.controller.AuthController', 'callback', '/auth/callback/{oauthType}', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(11, '44a84ebcca70e4d399cc7329ae53a294', '第三方登录', '第三方登录', 'POST', 'vip.mate.uaa.controller.AuthController', 'login', '/auth/login/{oauthType}', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(12, 'c3e89549d2cd6a550673a5f0f0999588', '退出登录', '退出登录', 'POST', 'vip.mate.uaa.controller.AuthController', 'logout', '/auth/logout', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(13, 'fd26ebbb42eeafa6bc1a5551153a2290', '验证码获取', '验证码获取', 'GET', 'vip.mate.uaa.controller.AuthController', 'authCode', '/auth/code', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(14, '240d2897eb2d2ed4d15ddfdd939232b6', '手机验证码下发', '手机验证码下发', 'GET', 'vip.mate.uaa.controller.AuthController', 'smsCode', '/auth/sms-code', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(15, '0f31df9a8db1df105508afa93b074930', '登录类型', '登录类型', 'GET', 'vip.mate.uaa.controller.AuthController', 'loginType', '/auth/list', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(16, '87f2b83c8a49fbe8166e78a49353ba7f', '获取菜单列表', '根据角色ID获取菜单列表', 'GET', 'vip.mate.system.feign.SysRolePermissionProvider', 'getMenuIdByRoleId', '/provider/role-permission/permission', '', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(17, 'cea9d573e219b4c15dcac02c6c84acae', '用户ID查询', '用户ID查询', 'GET', 'vip.mate.system.feign.SysUserProvider', 'getUserById', '/provider/user/id', '', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(18, 'da6b14e72532f46b12fc67b45dad208f', '用户用户名查询', '用户用户名查询', 'GET', 'vip.mate.system.feign.SysUserProvider', 'getUserByUserName', '/provider/user/username', '', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(19, 'd2affc1b35b2baec49813f0b20743d9f', '用户手机号查询', '用户手机号查询', 'GET', 'vip.mate.system.feign.SysUserProvider', 'getUserByMobile', '/provider/user/mobile', '', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(20, 'ce1be04ba688df4c1ad7d156116f96c1', '字典列表', '根据code获取字典列表', 'GET', 'vip.mate.system.feign.SysDictProvider', 'getList', '/provider/dict/list', '', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(21, '229054689b7b9bff20a5128b36c5d9bf', '字典值', '根据code和dictKey获取值', 'GET', 'vip.mate.system.feign.SysDictProvider', 'getValue', '/provider/dict/value', '', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(22, 'cf5af93cd67ee6d74fcdae3216990a71', '日志设置', '日志设置', 'POST', 'vip.mate.system.feign.SysLogProvider', 'set', '/provider/log/set', 'application/json;charset=UTF-8', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(23, '61970f669724bb3e016e99846621dcaf', 'API状态', '状态包括：启用、禁用', 'POST', 'vip.mate.system.controller.SysApiController', 'setStatus', '/api/set-status', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(24, '558e6b243aed6ea2cb7fa49e5f2cd83d', 'API删除', 'API删除', 'POST', 'vip.mate.system.controller.SysApiController', 'del', '/api/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(25, 'cedf94b1c53a31bd9c4e550e670ec382', 'API列表', '分页查询', 'GET', 'vip.mate.system.controller.SysApiController', 'page', '/api/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(26, 'bfebca06819591fb729c2dd004a5dae7', 'API信息', '根据ID查询', 'GET', 'vip.mate.system.controller.SysApiController', 'get', '/api/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(27, 'b15e96c84dbc6e4e266426c7f370c64d', 'API设置', 'API设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysApiController', 'set', '/api/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(28, 'fb508556e5851c2dd4d3cb5adb9a3ebe', 'API同步', 'API同步', 'POST', 'vip.mate.system.controller.SysApiController', 'sync', '/api/sync', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(29, '15cc4660be1df040172b21a7cfb6bfb0', '角色权限设置', '角色权限设置', 'POST', 'vip.mate.system.controller.SysRoleController', 'savePermission', '/role/set-permission', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(30, '3c4766f920f2b2762796ab1f23dc3747', '导出角色', '导出角色', 'POST', 'vip.mate.system.controller.SysRoleController', 'export', '/role/export', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(31, '18f524eabfeee4a4f607502df1772bf0', '角色信息', '根据ID查询', 'GET', 'vip.mate.system.controller.SysRoleController', 'get', '/role/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(32, '30f37312c4f39bf139eae532d8718ddb', '角色删除', '角色删除，支持批量操作', 'POST', 'vip.mate.system.controller.SysRoleController', 'delete', '/role/delete', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(33, 'f8a3cd040b7505082ff0761fc0ec57d1', '角色列表', '角色列表，根据query查询', 'GET', 'vip.mate.system.controller.SysRoleController', 'list', '/role/list', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(34, 'd7dc5460ad815d10cf593357ef9035a4', '角色设置', '角色设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysRoleController', 'set', '/role/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(35, 'cc040f3ad2c2bbd333f85dac3290707f', '角色权限列表', '角色权限列表', 'GET', 'vip.mate.system.controller.SysRoleController', 'getPermission', '/role/get-permission', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(36, '3132721d01d9dfdeb85ce5be59f47cd0', '角色树', '角色树', 'GET', 'vip.mate.system.controller.SysRoleController', 'tree', '/role/tree', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(37, '6b2c4db232b4d7432f74e48b40ee2ec6', '字典删除', '字典删除', 'POST', 'vip.mate.system.controller.SysDictController', 'del', '/dict/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(38, '8361e6eab1ed2bbbb0eccf086182376d', '字典分页', '字典分页', 'GET', 'vip.mate.system.controller.SysDictController', 'page', '/dict/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(39, '71ac4de98d9455136ef10e7d0a388745', '字典列表key查询', '字典列表key查询', 'GET', 'vip.mate.system.controller.SysDictController', 'getDictValue', '/dict/get-dict-value', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(40, '2116da876073f1ad71e677f010195b56', '字典项列表', '字典项列表', 'GET', 'vip.mate.system.controller.SysDictController', 'listValue', '/dict/list-value', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(41, 'eb427a14fa30167d708a20a1ecd9f699', '字典列表code查询', '字典列表code查询', 'GET', 'vip.mate.system.controller.SysDictController', 'listCode', '/dict/list-code', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(42, '2efa1e14facd02de585f755732c3025d', '字典信息', '根据ID查询', 'GET', 'vip.mate.system.controller.SysDictController', 'get', '/dict/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(43, 'c8629e5d870b295f5112fb3b2292bb5e', '字典设置', '字典设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysDictController', 'set', '/dict/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(44, 'a53752c2608e83164dbbaaab4fe94b32', '系统路由表删除', '系统路由表删除', 'POST', 'vip.mate.system.controller.SysRouteController', 'del', '/route/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(45, '237c64ae6e11b73b17d163fd34dfacae', '系统路由分页', '分页查询', 'GET', 'vip.mate.system.controller.SysRouteController', 'page', '/route/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(46, 'f97861d8a8ab59700d0b1e6fdfa170a6', '系统路由列表', '系统路由列表', 'GET', 'vip.mate.system.controller.SysRouteController', 'listItem', '/route/list-item', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(47, '292ec619e796b2ba7fca2787ad626ef5', '系统路由表信息', '根据ID查询', 'GET', 'vip.mate.system.controller.SysRouteController', 'get', '/route/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(48, '2597ff33c4eb041ceb3eb380dfb568b7', '系统路由表设置', '系统路由表设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysRouteController', 'set', '/route/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(49, '37dbe097797343d2dd73421fedc28d13', '客户端状态', '客户端状态：启用、禁用', 'POST', 'vip.mate.system.controller.SysClientController', 'setStatus', '/client/set-status', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(50, 'a0698ba9c55b8b34eb1d18601b6404b0', '客户端删除', '客户端删除', 'POST', 'vip.mate.system.controller.SysClientController', 'del', '/client/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(51, '808c8606e1a04378e6fe484bfa8e96d9', '客户端分页', '客户端分页', 'GET', 'vip.mate.system.controller.SysClientController', 'page', '/client/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(52, '791b3d641cbcd8f8e3d797d34004fefa', '客户端导出', '客户端导出', 'POST', 'vip.mate.system.controller.SysClientController', 'export', '/client/export', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(53, '607700fe45709b39c134974ee9132c23', '客户端信息', '客户端信息,根据ID查询', 'GET', 'vip.mate.system.controller.SysClientController', 'get', '/client/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(54, '8cbd4b8b5cab0cadbac7e5161ba32856', '客户端设置', '客户端设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysClientController', 'set', '/client/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(55, 'affc77e87638cec289664a87e5b23803', '部门删除', '部门删除', 'POST', 'vip.mate.system.controller.SysDepartController', 'del', '/depart/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(56, 'ad3acb2d8117fedb59535776c313279a', '部门导出', '部门导出', 'POST', 'vip.mate.system.controller.SysDepartController', 'export', '/depart/export', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(57, '3ef33db333255aee73f513cccc074175', '部门信息', '部门信息,根据ID查询', 'GET', 'vip.mate.system.controller.SysDepartController', 'get', '/depart/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(58, '46532d6cd52cdbe360dcea06573c4306', '部门列表', '部门列表，根据search查询', 'GET', 'vip.mate.system.controller.SysDepartController', 'list', '/depart/list', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(59, 'a1a4b644344f1c90b800a2744def39b9', '部门设置', '部门设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysDepartController', 'set', '/depart/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(60, 'a3e2e3de4432bd2b2759b3f079d0b8a6', '部门树', '部门树', 'GET', 'vip.mate.system.controller.SysDepartController', 'tree', '/depart/tree', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(61, 'bf66729eade77ec366107b564bc8d1f8', '用户状态', '状态包括：启用、禁用', 'POST', 'vip.mate.system.controller.SysUserController', 'setStatus', '/user/set-status', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(62, 'f5a57d978adf0c58177076d8f91e62f1', '用户删除', '用户删除', 'POST', 'vip.mate.system.controller.SysUserController', 'del', '/user/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(63, '3965fba22f3628b7846cb55183619259', '用户密码设置', '用户密码设置', 'POST', 'vip.mate.system.controller.SysUserController', 'setPassword', '/user/set-password', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(64, 'da038ce4f08b5bfa76c45487fa995131', '用户列表', '分页查询', 'GET', 'vip.mate.system.controller.SysUserController', 'page', '/user/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(65, '9f55dcda54dcb16515fed6bfa5d743a7', '导出用户', '导出用户', 'POST', 'vip.mate.system.controller.SysUserController', 'export', '/user/export', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(66, '5de06489c809b27b9f50a696538e0372', '用户信息', '根据ID查询', 'GET', 'vip.mate.system.controller.SysUserController', 'get', '/user/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(67, '43bab7218f6a3c27943097e24badf382', '设置用户', '新增或修改用户', 'POST', 'vip.mate.system.controller.SysUserController', 'set', '/user/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(68, 'e210e97e9cf161930609358a28f5835f', '黑名单信息', '黑名单信息,根据ID查询', 'GET', 'vip.mate.system.controller.SysBlacklistController', 'info', '/blacklist/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(69, '4798eb12924d6f9c9883ba3c160df2ce', '黑名单状态', '黑名单状态,状态包括：启用、禁用', 'POST', 'vip.mate.system.controller.SysBlacklistController', 'setStatus', '/blacklist/set-status', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(70, '2efb64abd7c136ca5bcd02b2d7ab4e08', '黑名单删除', '黑名单删除', 'POST', 'vip.mate.system.controller.SysBlacklistController', 'del', '/blacklist/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(71, '862e1ce03525e331e9f5fae5d949cb36', '黑名单分页', '黑名单分页', 'GET', 'vip.mate.system.controller.SysBlacklistController', 'page', '/blacklist/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(72, '89fbcdc911d0348d6c02560146c1c3b5', '黑名单设置', '黑名单设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysBlacklistController', 'set', '/blacklist/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(73, '1203d0422fc2937053fae33b2422b851', '日志删除', '日志删除', 'POST', 'vip.mate.system.controller.SysLogController', 'del', '/log/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(74, 'd8a6c8fc8ce040d7241a993fc5205513', '日志列表', '日志列表', 'GET', 'vip.mate.system.controller.SysLogController', 'page', '/log/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(75, 'ddd1be208bfa568c71c70a6e915ad11a', '菜单状态', '状态包括：启用、禁用', 'POST', 'vip.mate.system.controller.SysMenuController', 'setStatus', '/menu/set-status', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(76, '8d1543ef715f259ade524132e9d1dad3', '菜单分级列表', '菜单分级列表', 'GET', 'vip.mate.system.controller.SysMenuController', 'grade', '/menu/grade', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(77, '2d8d4ea69a18818a1735275911a47e8e', '菜单删除', '菜单删除', 'POST', 'vip.mate.system.controller.SysMenuController', 'del', '/menu/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(78, '0eef1aea5ea45364e8d9d8633408eca2', '菜单导出', '菜单导出', 'POST', 'vip.mate.system.controller.SysMenuController', 'export', '/menu/export', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(79, '42b6efbc0201b39d0961009d0dde763f', '菜单信息', '根据ID查询', 'GET', 'vip.mate.system.controller.SysMenuController', 'get', '/menu/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(80, '09b744aa117856131ccebc125ea3f668', '菜单列表', '菜单列表，根据关键词查询', 'GET', 'vip.mate.system.controller.SysMenuController', 'list', '/menu/list', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(81, 'b4fbfbea29741f7c7d6f1845a3529263', '菜单设置', '菜单设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysMenuController', 'set', '/menu/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(82, 'f339fb5f7850d2ce02af0c2bd7b5864c', '菜单树', '根据roleId查询菜单树', 'GET', 'vip.mate.system.controller.SysMenuController', 'tree', '/menu/tree', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(83, '581fcf1e512f78737b59d5ef46b00e68', '查询OSS配置', '查询OSS配置', 'GET', 'vip.mate.component.controller.SysConfigController', 'getConfigByCode', '/config/get-config-by-code', '', 'mate-component', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(84, '67e760391dc41cfce8f1eb490cf25590', '默认配置', '默认配置', 'GET', 'vip.mate.component.controller.SysConfigController', 'defaultOss', '/config/default-oss', '', 'mate-component', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(85, '3a5d36e02b2f335c05f29447dd250193', '保存默认配置', '保存默认配置', 'POST', 'vip.mate.component.controller.SysConfigController', 'saveDefaultOss', '/config/save-default-oss', '', 'mate-component', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(86, '9cd9e1e794871d16e6979c9d54f456c8', '保存OSS配置', '保存OSS配置', 'POST', 'vip.mate.component.controller.SysConfigController', 'saveConfigOss', '/config/save-config-oss', 'application/json;charset=UTF-8', 'mate-component', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(87, '5588f0697f631f1b95616035f2bd8831', '删除文件', '删除文件', 'POST', 'vip.mate.component.controller.SysAttachmentController', 'del', '/attachment/del', '', 'mate-component', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL),
(88, 'fb2da47cf384518853d9f908de4a8ad6', '附件分页', '附件分页，根据query查询', 'GET', 'vip.mate.component.controller.SysAttachmentController', 'page', '/attachment/page', '', 'mate-component', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_attachment`
--

CREATE TABLE IF NOT EXISTS `mate_sys_attachment` (
  `id` bigint(20) NOT NULL COMMENT '附件ID',
  `storage_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '存储ID',
  `attachment_group_id` int(11) NOT NULL DEFAULT '0' COMMENT '组ID',
  `name` varchar(128) NOT NULL COMMENT '文件名称',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `url` varchar(2080) NOT NULL COMMENT '文件地址',
  `file_name` varchar(200) DEFAULT NULL COMMENT '上传文件名',
  `thumb_url` varchar(2080) NOT NULL DEFAULT '' COMMENT '缩略图地址',
  `type` tinyint(2) NOT NULL COMMENT '类型',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `is_recycle` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否加入回收站 0.否|1.是'
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COMMENT='附件表';

--
-- 转存表中的数据 `mate_sys_attachment`
--

INSERT INTO `mate_sys_attachment` (`id`, `storage_id`, `attachment_group_id`, `name`, `size`, `url`, `file_name`, `thumb_url`, `type`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`, `is_recycle`) VALUES
(13, 0, 0, 'timg (3).jpeg', 26516, 'https://cdn.ckjia.com/10e258da699b4c318ff59e24f2599420.jpeg', '10e258da699b4c318ff59e24f2599420.jpeg', '', 1, NULL, NULL, '2020-08-10 03:29:26', NULL, '0', 0),
(16, 0, 0, 'background.jpg', 261548, 'https://cdn.ckjia.com/3b8f8e2b5ffb43b0905397b82a6b3ec6.jpg', '3b8f8e2b5ffb43b0905397b82a6b3ec6.jpg', '', 1, NULL, NULL, '2020-08-10 11:55:53', NULL, '0', 0),
(17, 0, 0, 'nav-icon-new.active.png', 3036, 'https://cdn.ckjia.com/5efe50fcd0e744eaa7a2e7c6d851dd82.png', '5efe50fcd0e744eaa7a2e7c6d851dd82.png', '', 1, NULL, NULL, '2020-08-10 13:39:06', NULL, '0', 0),
(18, 0, 0, 'nav-icon-user.active.png', 2150, 'https://cdn.ckjia.com/90cef6a278b34c1690af938193e2d813.png', '90cef6a278b34c1690af938193e2d813.png', '', 1, NULL, NULL, '2020-08-10 13:40:56', NULL, '0', 0),
(19, 0, 0, 'nav-icon-cat.active.png', 3338, 'https://cdn.ckjia.com/8ffa2bf6e6e7491b8460bf308abd30de.png', '8ffa2bf6e6e7491b8460bf308abd30de.png', '', 1, NULL, NULL, '2020-08-10 13:41:49', NULL, '0', 0),
(20, 0, 0, 'nav-icon-index.active.png', 2754, 'https://cdn.ckjia.com/478acfc9aeb140a4b79c6402ba3bd021.png', '478acfc9aeb140a4b79c6402ba3bd021.png', '', 1, NULL, NULL, '2020-08-10 13:54:53', NULL, '0', 0),
(21, 0, 0, 'baiduzhifu2x.png', 19415, 'https://cdn.ckjia.com/5ba794ec8d054ce995d37d364c5a9836.png', '5ba794ec8d054ce995d37d364c5a9836.png', '', 1, NULL, NULL, '2020-08-10 13:56:10', NULL, '0', 0),
(22, 0, 0, 'h_seckill.png', 6008, 'https://cdn.ckjia.com/897d70b0635f48999baa635d6debbbee.png', '897d70b0635f48999baa635d6debbbee.png', '', 1, NULL, NULL, '2020-08-10 13:59:47', NULL, '0', 0),
(24, 0, 0, 'mate-bg2.jpeg', 79028, 'https://cdn.ckjia.com/7667a4086d3c40948207dc8e02b52ff9.jpeg', '7667a4086d3c40948207dc8e02b52ff9.jpeg', '', 1, NULL, NULL, '2020-08-11 14:19:53', NULL, '0', 0);

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_blacklist`
--

CREATE TABLE IF NOT EXISTS `mate_sys_blacklist` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `request_uri` varchar(100) DEFAULT NULL COMMENT '请求地址',
  `request_method` varchar(10) DEFAULT NULL COMMENT '请求方法',
  `start_time` varchar(32) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(32) DEFAULT NULL COMMENT '结束时间',
  `status` char(1) DEFAULT '0' COMMENT '状态：0:关闭 1:开启',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统黑名单表';

--
-- 转存表中的数据 `mate_sys_blacklist`
--

INSERT INTO `mate_sys_blacklist` (`id`, `ip`, `request_uri`, `request_method`, `start_time`, `end_time`, `status`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES
(1, NULL, '/**/actuator/**', 'ALL', NULL, NULL, '1', NULL, NULL, '2020-08-22 01:40:16', '2020-08-22 01:40:34');

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_client`
--

CREATE TABLE IF NOT EXISTS `mate_sys_client` (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `client_id` varchar(48) NOT NULL COMMENT '客户端id',
  `client_secret` varchar(256) NOT NULL COMMENT '客户端密钥',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '资源集合',
  `scope` varchar(256) NOT NULL COMMENT '授权范围',
  `authorized_grant_types` varchar(256) NOT NULL COMMENT '授权类型',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '回调地址',
  `authorities` varchar(256) DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) NOT NULL COMMENT '令牌过期秒数',
  `refresh_token_validity` int(11) NOT NULL COMMENT '刷新令牌过期秒数',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '附加说明',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '自动授权',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '是否已删除'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='客户端表';

--
-- 转存表中的数据 `mate_sys_client`
--

INSERT INTO `mate_sys_client` (`id`, `client_id`, `client_secret`, `resource_ids`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_by`, `update_by`, `create_time`, `update_time`, `status`, `is_deleted`) VALUES
(1, 'mate', 'mate_secret', NULL, 'all', 'refresh_token,password,authorization_code,captcha,sms,social', 'http://localhost:10001', NULL, 3600, 3600, NULL, NULL, NULL, NULL, '2020-07-12 14:49:23', '2020-07-28 04:22:54', '0', 0);

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_config`
--

CREATE TABLE IF NOT EXISTS `mate_sys_config` (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `parent_id` bigint(64) DEFAULT '0' COMMENT '父主键',
  `code` varchar(255) DEFAULT NULL COMMENT '码',
  `c_key` varchar(255) DEFAULT NULL COMMENT '值',
  `value` varchar(255) DEFAULT NULL COMMENT '名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `tenant_id` int(11) DEFAULT '1',
  `is_deleted` int(2) DEFAULT '0' COMMENT '是否已删除'
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='配置表';

--
-- 转存表中的数据 `mate_sys_config`
--

INSERT INTO `mate_sys_config` (`id`, `parent_id`, `code`, `c_key`, `value`, `sort`, `remark`, `create_by`, `update_by`, `create_time`, `update_time`, `tenant_id`, `is_deleted`) VALUES
(1, 0, 'oss', 'default', 'qiniuoss', 0, '默认OSS配置', NULL, NULL, '2020-08-08 01:44:31', '2020-12-16 09:37:21', 1, 0),
(2, 1, 'alioss', 'endpoint', 'oss-cn-beijing.aliyuncs.com', 1, '对象存储服务的URL', NULL, NULL, '2020-08-08 01:46:10', '2020-08-09 16:14:15', 1, 0),
(3, 1, 'alioss', 'customDomain', 'mall-zaitong.oss-cn-beijing.aliyuncs.com', 2, '自定义域名', NULL, NULL, '2020-08-08 01:46:32', '2020-08-09 16:14:08', 1, 0),
(4, 1, 'alioss', 'pathStyleAccess', 'false', 3, 'pathStyleAccess', NULL, NULL, '2020-08-08 01:47:21', '2020-08-08 01:47:35', 1, 0),
(5, 1, 'alioss', 'accessKey', 'LTA******rzjrV', 4, 'Access Key', NULL, NULL, '2020-08-08 01:47:40', '2020-08-09 07:53:48', 1, 0),
(6, 1, 'alioss', 'secretKey', '9H6Bxg**************bfNoy4E', 5, 'Access Secret', NULL, NULL, '2020-08-08 01:53:13', '2020-08-10 01:31:53', 1, 0),
(7, 1, 'alioss', 'bucketName', 'm********g', 6, '空间名', NULL, NULL, '2020-08-08 01:53:14', '2020-08-09 16:13:15', 1, 0),
(8, 1, 'qiniuoss', 'endpoint', 's3-cn-south-1.qiniucs.com', 1, '对象存储服务的URL', NULL, NULL, '2020-08-08 01:46:10', '2020-08-10 01:33:31', 1, 0),
(9, 1, 'qiniuoss', 'customDomain', 'cd**********com8878556757657', 2, '自定义域名', NULL, NULL, '2020-08-08 01:46:32', '2020-11-15 20:02:32', 1, 0),
(10, 1, 'qiniuoss', 'pathStyleAccess', 'false', 3, 'pathStyleAccess', NULL, NULL, '2020-08-08 01:47:21', '2020-08-08 01:47:35', 1, 0),
(11, 1, 'qiniuoss', 'accessKey', 'pj2M-4k_*********************dQpjb1L', 4, 'Access Key', NULL, NULL, '2020-08-08 01:47:40', '2020-08-10 01:33:31', 1, 0),
(12, 1, 'qiniuoss', 'secretKey', 'Dx17O-dbR*******************Mxlc4bb', 5, 'Access Secret', NULL, NULL, '2020-08-08 01:53:13', '2020-08-10 01:33:31', 1, 0),
(13, 1, 'qiniuoss', 'bucketName', 'ckjia', 6, '空间名', NULL, NULL, '2020-08-08 01:53:14', '2020-08-10 01:33:31', 1, 0),
(14, 1, 'miniooss', 'endpoint', '66666', 1, '对象存储服务的URL', NULL, NULL, '2020-08-08 01:46:10', '2020-08-09 02:03:52', 1, 0),
(15, 1, 'miniooss', 'customDomain', '2222', 2, '自定义域名', NULL, NULL, '2020-08-08 01:46:32', '2020-08-08 16:55:54', 1, 0),
(16, 1, 'miniooss', 'pathStyleAccess', 'false', 3, 'pathStyleAccess', NULL, NULL, '2020-08-08 01:47:21', '2020-08-08 01:47:35', 1, 0),
(17, 1, 'miniooss', 'accessKey', '3333', 4, 'Access Key', NULL, NULL, '2020-08-08 01:47:40', '2020-08-08 16:55:58', 1, 0),
(18, 1, 'miniooss', 'secretKey', '4444', 5, 'Access Secret', NULL, NULL, '2020-08-08 01:53:13', '2020-08-08 16:56:02', 1, 0),
(19, 1, 'miniooss', 'bucketName', '5555', 6, '空间名', NULL, NULL, '2020-08-08 01:53:14', '2020-08-08 16:56:06', 1, 0);

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_data_source`
--

CREATE TABLE IF NOT EXISTS `mate_sys_data_source` (
  `id` int(11) NOT NULL COMMENT '自增id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `db_type` varchar(50) NOT NULL DEFAULT '' COMMENT '数据库类型',
  `driver_class` varchar(100) DEFAULT NULL COMMENT '驱动类型',
  `url` varchar(500) DEFAULT NULL COMMENT '连接地址',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='数据源表';

--
-- 转存表中的数据 `mate_sys_data_source`
--

INSERT INTO `mate_sys_data_source` (`id`, `name`, `db_type`, `driver_class`, `url`, `username`, `password`, `remark`, `status`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`) VALUES
(1, 'ldx', 'mysql', 'com.mysql.cj.jdbc.Driver', 'localhost:3306', 'root', 'root', NULL, NULL, NULL, NULL, '2020-12-08 15:17:40', NULL, 0);

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_depart`
--

CREATE TABLE IF NOT EXISTS `mate_sys_depart` (
  `id` bigint(20) NOT NULL COMMENT '部门ID',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '上级ID',
  `tenant_id` bigint(20) DEFAULT '0' COMMENT '租户ID'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='组织机构表';

--
-- 转存表中的数据 `mate_sys_depart`
--

INSERT INTO `mate_sys_depart` (`id`, `name`, `sort`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`, `parent_id`, `tenant_id`) VALUES
(1, '开发部', 0, NULL, NULL, '2020-06-27 15:30:50', '2020-07-01 20:49:08', '0', -1, 0),
(2, '开发分部', 0, NULL, NULL, '2020-06-29 11:14:37', NULL, '0', 1, 0),
(3, '开发二部', 1, NULL, NULL, '2020-06-29 15:54:27', NULL, '0', 1, 0),
(4, '产品部', 1, NULL, NULL, '2020-06-29 07:58:54', '2020-08-17 06:53:59', '0', -1, 0),
(5, '产品一部', 1, NULL, NULL, '2020-06-29 15:59:14', NULL, '0', 4, 0);

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_dict`
--

CREATE TABLE IF NOT EXISTS `mate_sys_dict` (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `parent_id` bigint(64) DEFAULT '0' COMMENT '父主键',
  `code` varchar(255) DEFAULT NULL COMMENT '字典码',
  `dict_key` varchar(255) DEFAULT NULL COMMENT '字典值',
  `dict_value` varchar(255) DEFAULT NULL COMMENT '字典名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '字典备注',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` int(2) DEFAULT '0' COMMENT '是否已删除'
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

--
-- 转存表中的数据 `mate_sys_dict`
--

INSERT INTO `mate_sys_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `sort`, `remark`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`) VALUES
(1, 0, 'status', '-1', '状态', 1, '', NULL, NULL, '2020-07-01 09:59:15', '2020-07-01 10:02:00', 0),
(2, 1, 'status', '0', '启用', 1, NULL, NULL, NULL, '2020-07-01 10:02:23', '2020-07-01 10:02:59', 0),
(3, 1, 'status', '1', '禁用', 2, NULL, NULL, NULL, '2020-07-01 10:02:34', '2020-07-01 10:02:59', 0),
(4, 0, 'dbtype', '-1', '数据库类型', 1, NULL, NULL, NULL, '2020-07-11 08:47:02', NULL, 0),
(5, 4, 'dbtype', 'mysql', 'com.mysql.cj.jdbc.Driver', 1, NULL, NULL, NULL, '2020-07-11 08:47:44', '2020-07-11 08:53:11', 0),
(6, 4, 'dbtype', 'oracle', 'oracle.jdbc.OracleDriver', 2, NULL, NULL, NULL, '2020-07-11 08:48:00', '2020-07-11 08:54:05', 0),
(7, 4, 'dbtype', 'oracle12c', 'oracle.jdbc.OracleDriver', 3, NULL, NULL, NULL, '2020-07-11 08:49:10', '2020-07-11 08:54:12', 0),
(24, 0, 'ok', '-1', '确认', NULL, NULL, NULL, NULL, '2020-07-19 13:31:16', '2020-07-19 21:31:28', 0),
(25, 24, 'ok', 'yes', '是', NULL, NULL, NULL, NULL, '2020-07-19 21:31:40', '2020-07-20 05:32:12', 0),
(26, 24, 'ok', 'no', '否', NULL, NULL, NULL, NULL, '2020-07-20 05:32:06', NULL, 0);

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_log`
--

CREATE TABLE IF NOT EXISTS `mate_sys_log` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `trace_id` varchar(64) DEFAULT NULL COMMENT '跟踪ID',
  `title` varchar(64) DEFAULT NULL COMMENT '日志标题',
  `operation` text COMMENT '操作内容',
  `method` varchar(64) DEFAULT NULL COMMENT '执行方法',
  `params` text COMMENT '参数',
  `url` varchar(128) DEFAULT NULL COMMENT '请求路径',
  `ip` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `exception` text,
  `execute_time` decimal(11,0) DEFAULT NULL COMMENT '耗时',
  `location` varchar(64) DEFAULT NULL COMMENT '地区',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID'
) ENGINE=InnoDB AUTO_INCREMENT=115197 DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';


-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_menu`
--

CREATE TABLE IF NOT EXISTS `mate_sys_menu` (
  `id` bigint(20) NOT NULL COMMENT '菜单ID',
  `name` varchar(32) DEFAULT NULL COMMENT '菜单标题',
  `permission` varchar(32) DEFAULT NULL COMMENT '菜单权限',
  `path` varchar(128) DEFAULT NULL COMMENT '路径',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `keep_alive` char(1) DEFAULT '0' COMMENT '是否缓存该页面: 1:是  0:不是',
  `type` char(1) DEFAULT '0' COMMENT '菜单类型',
  `hidden` char(1) NOT NULL DEFAULT '0' COMMENT '是否显示',
  `target` char(1) NOT NULL DEFAULT '0' COMMENT '是否外链',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `tenant_id` bigint(20) unsigned DEFAULT '0' COMMENT '租户ID'
) ENGINE=InnoDB AUTO_INCREMENT=2063 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';

--
-- 转存表中的数据 `mate_sys_menu`
--

INSERT INTO `mate_sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `hidden`, `target`, `create_by`, `update_by`, `create_time`, `update_time`, `status`, `is_deleted`, `tenant_id`) VALUES
(1000, '系统管理', NULL, '/system', -1, 'imac', 1, '0', '0', '0', '0', NULL, NULL, '2020-06-17 14:21:45', '2020-08-11 03:41:11', '0', '0', 0),
(1100, '用户管理', NULL, '/system/user', 1000, 'user', 1, '0', '1', '0', '0', NULL, NULL, '2020-06-18 14:28:36', '2020-06-25 11:19:20', '0', '0', 0),
(1101, '用户新增', 'sys:user:add', '', 1100, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-17 14:32:51', '2020-07-03 08:51:48', '0', '0', 0),
(1102, '用户修改', 'sys:user:edit', NULL, 1100, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:27:40', '2020-07-03 08:51:50', '0', '0', 0),
(1103, '用户删除', 'sys:user:delete', NULL, 1100, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:27:56', '2020-07-03 08:51:52', '0', '0', 0),
(1104, '用户启用', 'sys:user:enable', NULL, 1100, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 08:49:47', '2020-07-03 08:55:39', '0', '0', 0),
(1105, '用户禁用', 'sys:user:disable', NULL, 1100, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 08:50:16', '2020-07-03 08:55:40', '0', '0', 0),
(1106, '用户导出', 'sys:user:export', NULL, 1100, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 08:50:58', '2020-07-03 08:55:42', '0', '0', 0),
(1200, '角色管理', NULL, '/system/role', 1000, 'repair', 1, '0', '1', '0', '0', NULL, NULL, '2020-06-19 16:36:01', '2020-06-25 03:19:23', '0', '0', 0),
(1201, '角色新增', 'sys:role:add', NULL, 1200, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:37:12', '2020-07-03 14:00:56', '0', '0', 0),
(1202, '角色修改', 'sys:role:edit', NULL, 1200, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:38:23', '2020-07-03 14:01:06', '0', '0', 0),
(1203, '角色删除', 'sys:role:delete', NULL, 1200, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:38:53', '2020-07-03 14:01:14', '0', '0', 0),
(1204, '角色导出', 'sys:role:export', NULL, 1200, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 14:02:37', '2020-07-03 14:02:50', '0', '0', 0),
(1205, '角色权限', 'sys:role:perm', NULL, 1200, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 14:03:32', NULL, '0', '0', 0),
(1300, '菜单管理', NULL, '/system/menu', 1000, 'tree', 1, '0', '1', '0', '0', NULL, NULL, '2020-06-19 16:39:07', '2020-06-25 03:19:45', '0', '0', 0),
(1301, '菜单新增', 'sys:menu:add', NULL, 1300, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:39:48', '2020-07-03 14:11:59', '0', '0', 0),
(1302, '菜单修改', 'sys:menu:edit', NULL, 1300, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:40:21', '2020-07-03 14:12:15', '0', '0', 0),
(1303, '菜单删除', 'sys:menu:delete', NULL, 1300, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:40:42', '2020-07-03 14:12:23', '0', '0', 0),
(1304, '菜单启用', 'sys:menu:enable', NULL, 1300, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 14:12:59', '2020-07-03 14:13:14', '0', '0', 0),
(1305, '菜单禁用', 'sys:menu:disable', NULL, 1300, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 14:13:34', '2020-07-03 14:13:57', '0', '0', 0),
(1306, '菜单导出', 'sys:menu:export', NULL, 1300, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 14:14:32', NULL, '0', '0', 0),
(1400, '部门管理', NULL, '/system/depart', 1000, 'table2', 1, '0', '1', '0', '0', NULL, NULL, '2020-06-26 22:52:41', '2020-07-03 14:25:56', '0', '0', 0),
(1401, '部门新增', 'sys:depart:add', NULL, 1400, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-27 14:53:37', '2020-07-03 14:26:12', '0', '0', 0),
(1402, '部门修改', 'sys:depart:edit', NULL, 1400, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-27 14:54:47', '2020-07-03 14:26:14', '0', '0', 0),
(1403, '部门删除', 'sys:depart:delete', NULL, 1400, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-27 14:55:15', '2020-07-03 14:26:17', '0', '0', 0),
(1404, '部门导出', 'sys:depart:export', NULL, 1400, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 14:27:26', '2020-07-03 14:27:45', '0', '0', 0),
(2015, '开发运维', NULL, '/devops', -1, 'settings', 3, '0', '0', '0', '0', NULL, NULL, '2020-07-05 11:20:31', '2020-10-19 22:21:49', '0', '0', 0),
(2016, '数据源管理', '', '/devops/datasource', 2015, 'table', 1, '0', '1', '0', '0', NULL, NULL, '2020-07-06 19:21:58', '2020-07-09 06:50:01', '0', '0', 0),
(2017, '数据源新增', 'sys:datasource:add', NULL, 2016, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-07 04:08:11', NULL, '0', '0', 0),
(2018, '数据源修改', 'sys:datasource:edit', NULL, 2016, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-07 04:08:40', NULL, '0', '0', 0),
(2019, '数据源删除', 'sys:datasource:delete', NULL, 2016, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-07 04:09:05', NULL, '0', '0', 0),
(2020, '数据源导出', 'sys:datasource:export', NULL, 2016, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-07 04:09:25', NULL, '0', '0', 0),
(2021, '代码生成', NULL, '/devops/generator', 2015, 'download', 1, '0', '1', '0', '0', NULL, NULL, '2020-07-09 07:08:50', NULL, '0', '0', 0),
(2022, '代码生成', 'devops:gen', NULL, 2021, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-08 23:09:45', '2020-07-13 14:35:14', '0', '0', 0),
(2023, '监控配置中心', NULL, '/devops/monitor', 2015, 'validCode', 1, '0', '1', '0', '0', NULL, NULL, '2020-07-10 20:23:07', '2020-07-11 04:39:40', '0', '0', 0),
(2026, '客户端管理', NULL, '/system/client', 1000, 'iPhone', 1, '0', '1', '0', '0', NULL, NULL, '2020-07-13 22:47:20', NULL, '0', '0', 0),
(2027, '新增客户端', 'sys:client:add', NULL, 2026, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-13 22:47:44', NULL, '0', '0', 0),
(2028, '修改客户端', 'sys:client:edit', NULL, 2026, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-13 23:47:37', NULL, '0', '0', 0),
(2029, '删除客户端', 'sys:client:delete', NULL, 2026, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-13 23:48:11', NULL, '0', '0', 0),
(2030, '导出客户端', 'sys:client:export', NULL, 2026, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-13 23:48:28', NULL, '0', '0', 0),
(2031, '启禁客户端', 'sys:client:status', NULL, 2026, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-13 23:49:22', NULL, '0', '0', 0),
(2032, '操作日志', NULL, '/system/log', 1000, 'comment', 2, '0', '1', '0', '0', NULL, NULL, '2020-07-15 05:11:09', '2020-08-11 03:41:43', '0', '0', 0),
(2033, '详细日志', 'sys:log:detail', NULL, 2032, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-16 04:05:48', NULL, '0', '0', 0),
(2034, '日志删除', 'sys:log:delete', NULL, 2032, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-16 04:06:16', NULL, '0', '0', 0),
(2035, '字典管理', NULL, '/system/dict', 1000, 'add', 1, '0', '1', '0', '0', NULL, NULL, '2020-07-17 09:29:31', NULL, '0', '0', 0),
(2036, '新增字典', 'sys:dict:add', NULL, 2035, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-20 02:48:01', NULL, '0', '0', 0),
(2037, '修改字典', 'sys:dict:edit', NULL, 2035, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-20 02:48:20', NULL, '0', '0', 0),
(2038, '删除字典', 'sys:dict:delete', NULL, 2035, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-20 02:48:39', NULL, '0', '0', 0),
(2039, '组件管理', NULL, '/system/component', 1000, 'nested', 1, '0', '1', '0', '0', NULL, NULL, '2020-08-08 05:35:05', NULL, '0', '0', 0),
(2040, '内容管理', NULL, '/content', -1, 'content', 4, '0', '0', '0', '0', NULL, NULL, '2020-08-09 00:21:42', '2020-08-29 03:12:22', '0', '0', 0),
(2041, '文件管理', NULL, '/content/attachment', 2040, 'folder', 1, '0', '1', '0', '0', NULL, NULL, '2020-08-09 00:27:06', '2020-08-10 03:31:49', '0', '0', 0),
(2042, '修改组件', 'sys:comp:edit', NULL, 2039, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-10 00:42:28', '2020-08-10 08:42:57', '0', '0', 0),
(2043, '上传文件', 'sys:attach:add', NULL, 2041, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-10 08:43:52', NULL, '0', '0', 0),
(2044, '删除文件', 'sys:attach:delete', NULL, 2041, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-10 08:44:29', NULL, '0', '0', 0),
(2045, '网关中心', NULL, '/gateway', -1, 'gateway', 2, '0', '0', '0', '0', NULL, NULL, '2020-08-28 19:12:00', '2020-10-19 22:21:52', '0', '0', 0),
(2047, '黑名单管理', NULL, '/gateway/blacklist', 2045, 'blacklist', 3, '0', '1', '0', '0', NULL, NULL, '2020-08-29 03:15:34', '2020-10-19 22:21:40', '0', '0', 0),
(2048, '新增黑名单', 'gw:bl:add', NULL, 2047, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-29 09:38:52', NULL, '0', '0', 0),
(2049, '修改黑名单', 'gw:bl:edit', NULL, 2047, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-29 09:39:27', NULL, '0', '0', 0),
(2050, '删除黑名单', 'gw:bl:del', NULL, 2047, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-29 09:39:51', NULL, '0', '0', 0),
(2051, '黑名单状态', 'gw:bl:status', NULL, 2047, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-29 09:44:20', NULL, '0', '0', 0),
(2053, '文档管理', NULL, '/content/doc', 2040, 'comment', 1, '0', '1', '0', '0', NULL, NULL, '2020-09-07 12:16:38', NULL, '0', '0', 0),
(2055, 'API管理', NULL, '/gateway/api', 2045, 'discovery', 2, '0', '1', '0', '0', NULL, NULL, '2020-10-14 14:00:06', '2020-10-17 12:53:38', '0', '0', 0),
(2056, '微服务管理', NULL, '/gateway/route', 2045, 'share3', 1, '0', '1', '0', '0', NULL, NULL, '2020-10-17 12:53:27', NULL, '0', '0', 0),
(2057, '同步API', 'gw:api:sync', NULL, 2055, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-10-17 14:16:06', NULL, '0', '0', 0),
(2058, '删除API', 'gw:api:del', NULL, 2055, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-10-17 14:17:25', NULL, '0', '0', 0),
(2059, '修改API', 'gw:api:edit', NULL, 2055, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-10-17 14:17:58', NULL, '0', '0', 0),
(2060, '新增微服务', 'gw:route:add', NULL, 2056, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-10-19 05:19:45', NULL, '0', '0', 0),
(2061, '修改微服务', 'gw:route:edit', NULL, 2056, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-10-19 05:20:49', NULL, '0', '0', 0),
(2062, '删除微服务', 'gw:route:del', NULL, 2056, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-10-19 05:21:03', NULL, '0', '0', 0);

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_role`
--

CREATE TABLE IF NOT EXISTS `mate_sys_role` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(64) DEFAULT NULL COMMENT '角色编码',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

--
-- 转存表中的数据 `mate_sys_role`
--

INSERT INTO `mate_sys_role` (`id`, `role_name`, `role_code`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`, `tenant_id`) VALUES
(1, '管理员', 'admin', '管理员组', NULL, NULL, '2020-06-28 15:02:16', NULL, '0', NULL),
(2, '演示会员', 'demo2', '演示会员组', NULL, NULL, '2020-06-28 07:02:36', '2020-06-28 07:02:58', '0', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_role_permission`
--

CREATE TABLE IF NOT EXISTS `mate_sys_role_permission` (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `menu_id` bigint(64) DEFAULT NULL COMMENT '菜单id',
  `role_id` bigint(64) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB AUTO_INCREMENT=2173 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

--
-- 转存表中的数据 `mate_sys_role_permission`
--

INSERT INTO `mate_sys_role_permission` (`id`, `menu_id`, `role_id`) VALUES
(977, 1000, 2),
(978, 1300, 2),
(979, 1301, 2),
(980, 1302, 2),
(981, 1303, 2),
(982, 2009, 2),
(2103, 1000, 1),
(2104, 1300, 1),
(2105, 1301, 1),
(2106, 1302, 1),
(2107, 1303, 1),
(2108, 1304, 1),
(2109, 1305, 1),
(2110, 1306, 1),
(2111, 1100, 1),
(2112, 1101, 1),
(2113, 1102, 1),
(2114, 1103, 1),
(2115, 1104, 1),
(2116, 1105, 1),
(2117, 1106, 1),
(2118, 1400, 1),
(2119, 1401, 1),
(2120, 1402, 1),
(2121, 1403, 1),
(2122, 1404, 1),
(2123, 1200, 1),
(2124, 1201, 1),
(2125, 1202, 1),
(2126, 1203, 1),
(2127, 1204, 1),
(2128, 1205, 1),
(2129, 2026, 1),
(2130, 2027, 1),
(2131, 2028, 1),
(2132, 2029, 1),
(2133, 2030, 1),
(2134, 2031, 1),
(2135, 2035, 1),
(2136, 2036, 1),
(2137, 2037, 1),
(2138, 2038, 1),
(2139, 2039, 1),
(2140, 2042, 1),
(2141, 2032, 1),
(2142, 2033, 1),
(2143, 2034, 1),
(2144, 2045, 1),
(2145, 2056, 1),
(2146, 2060, 1),
(2147, 2061, 1),
(2148, 2062, 1),
(2149, 2055, 1),
(2150, 2057, 1),
(2151, 2058, 1),
(2152, 2059, 1),
(2153, 2047, 1),
(2154, 2048, 1),
(2155, 2049, 1),
(2156, 2050, 1),
(2157, 2051, 1),
(2158, 2015, 1),
(2159, 2016, 1),
(2160, 2017, 1),
(2161, 2018, 1),
(2162, 2019, 1),
(2163, 2020, 1),
(2164, 2021, 1),
(2165, 2022, 1),
(2166, 2023, 1),
(2167, 2040, 1),
(2168, 2041, 1),
(2169, 2043, 1),
(2170, 2044, 1),
(2171, NULL, NULL),
(2172, 2053, 1);

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_route`
--

CREATE TABLE IF NOT EXISTS `mate_sys_route` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `name` varchar(100) DEFAULT NULL COMMENT '服务名称',
  `path` varchar(255) DEFAULT NULL COMMENT '服务前缀',
  `url` varchar(255) DEFAULT NULL COMMENT '地址',
  `service_id` varchar(100) DEFAULT NULL COMMENT '服务编码',
  `status` char(1) DEFAULT '1' COMMENT 'API状态:0:禁用 1:启用',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='系统路由表';

--
-- 转存表中的数据 `mate_sys_route`
--

INSERT INTO `mate_sys_route` (`id`, `name`, `path`, `url`, `service_id`, `status`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`, `tenant_id`) VALUES
(1, '系统服务', '/mate-system/**', NULL, 'mate-system', '1', NULL, NULL, '2020-10-18 22:59:02', '2020-10-18 23:03:42', '0', NULL),
(2, '认证服务', '/mate-uaa/**', NULL, 'mate-uaa', '1', NULL, NULL, '2020-10-18 15:14:13', '2020-10-18 23:14:24', '0', NULL),
(3, '代码服务', '/mate-code/**', NULL, 'mate-code', '1', NULL, NULL, '2020-10-18 20:21:25', '2020-10-20 09:13:45', '0', NULL),
(4, '组件服务', '/mate-component/**', NULL, 'mate-component', '1', NULL, NULL, '2020-10-18 20:22:42', '2020-10-20 09:13:14', '0', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_tenant`
--

CREATE TABLE IF NOT EXISTS `mate_sys_tenant` (
  `id` bigint(20) NOT NULL COMMENT '租户id',
  `name` varchar(255) DEFAULT NULL COMMENT '租户名称',
  `code` varchar(64) DEFAULT NULL COMMENT '租户编码',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='租户表';

-- --------------------------------------------------------

--
-- 表的结构 `mate_sys_user`
--

CREATE TABLE IF NOT EXISTS `mate_sys_user` (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `tenant_id` varchar(12) DEFAULT '100000' COMMENT '租户ID',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(10) DEFAULT NULL COMMENT '真名',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(45) DEFAULT NULL COMMENT '手机',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` smallint(6) DEFAULT NULL COMMENT '性别',
  `role_id` bigint(20) DEFAULT '0' COMMENT '角色id',
  `depart_id` bigint(20) DEFAULT '0' COMMENT '部门id',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除'
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

--
-- 转存表中的数据 `mate_sys_user`
--

INSERT INTO `mate_sys_user` (`id`, `tenant_id`, `account`, `password`, `name`, `real_name`, `avatar`, `email`, `telephone`, `birthday`, `sex`, `role_id`, `depart_id`, `status`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`) VALUES
(2, '100000', 'admin', '{bcrypt}$2a$10$IhNoDpkdJ1VzbnfX1pH35.S25n2tHaxU4hSltf7An.wdiXAsYe2Jm', 'admin', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18810001000', NULL, 1, 1, 1, '0', NULL, NULL, '2020-07-02 07:29:12', '2020-08-09 17:29:15', '0'),
(3, '100000', 'admin2', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin2', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 2, 4, '0', NULL, NULL, '2020-06-16 20:05:43', '2020-07-02 05:38:59', '0'),
(4, '100000', 'admin4', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin4', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 2, 1, '0', NULL, NULL, '2020-06-17 04:05:43', '2020-07-02 05:39:00', '0'),
(5, '100000', 'admin5', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin5', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '0', NULL, NULL, '2020-06-17 04:05:43', '2020-07-02 05:39:01', '0'),
(6, '100000', 'admin6', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '0', NULL, NULL, '2020-06-17 12:05:43', '2020-07-02 13:39:01', '0'),
(7, '100000', 'admin7', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '0', NULL, NULL, '2020-06-16 12:05:43', '2020-06-30 12:55:52', '0'),
(8, '100000', 'admin8', '{bcrypt}$2a$10$6YPJ0dkaqh9x3zwTfidRBeT0U5vJ7Si7wXFx9Gc/K3pMxz8J18TrW', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 2, 1, '0', NULL, NULL, '2020-06-16 20:05:43', '2020-07-15 04:31:24', '0'),
(9, '100000', 'admin9', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '1', NULL, NULL, '2020-06-16 20:05:43', '2020-07-01 21:39:03', '0'),
(10, '100000', 'admin10', '{bcrypt}$2a$10$A5cfRbFDCsOg.1UTlWyEZu8DJHSr9GnANXQMsRSIDAtZHuiQicr0K', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '1', NULL, NULL, '2020-06-15 12:05:43', '2020-07-15 03:58:00', '0'),
(22, '100000', 'pp1', '{bcrypt}$2a$10$jddK.xwTn99XM9ggy64Zgu.eBK2GBiyk9RmtQEjg99S2F8otQ8ieq', 'pp1', NULL, 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', NULL, '1899', '2020-06-26 16:00:00', 0, 2, 1, '0', NULL, NULL, '2020-06-30 09:57:13', '2020-06-30 19:06:46', '0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mate_data_source`
--
ALTER TABLE `mate_data_source`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mate_duben_doc`
--
ALTER TABLE `mate_duben_doc`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mate_duben_set`
--
ALTER TABLE `mate_duben_set`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mate_sys_api`
--
ALTER TABLE `mate_sys_api`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mate_sys_attachment`
--
ALTER TABLE `mate_sys_attachment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `type` (`type`);

--
-- Indexes for table `mate_sys_blacklist`
--
ALTER TABLE `mate_sys_blacklist`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mate_sys_client`
--
ALTER TABLE `mate_sys_client`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mate_sys_config`
--
ALTER TABLE `mate_sys_config`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mate_sys_data_source`
--
ALTER TABLE `mate_sys_data_source`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mate_sys_depart`
--
ALTER TABLE `mate_sys_depart`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mate_sys_dict`
--
ALTER TABLE `mate_sys_dict`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mate_sys_log`
--
ALTER TABLE `mate_sys_log`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mate_sys_menu`
--
ALTER TABLE `mate_sys_menu`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mate_sys_role`
--
ALTER TABLE `mate_sys_role`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `idx_role_role_code` (`role_code`) USING BTREE;

--
-- Indexes for table `mate_sys_role_permission`
--
ALTER TABLE `mate_sys_role_permission`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mate_sys_route`
--
ALTER TABLE `mate_sys_route`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mate_sys_tenant`
--
ALTER TABLE `mate_sys_tenant`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mate_sys_user`
--
ALTER TABLE `mate_sys_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mate_data_source`
--
ALTER TABLE `mate_data_source`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `mate_duben_doc`
--
ALTER TABLE `mate_duben_doc`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文档id',AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `mate_duben_set`
--
ALTER TABLE `mate_duben_set`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文档集id',AUTO_INCREMENT=1689;
--
-- AUTO_INCREMENT for table `mate_sys_api`
--
ALTER TABLE `mate_sys_api`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',AUTO_INCREMENT=89;
--
-- AUTO_INCREMENT for table `mate_sys_attachment`
--
ALTER TABLE `mate_sys_attachment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '附件ID',AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `mate_sys_blacklist`
--
ALTER TABLE `mate_sys_blacklist`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `mate_sys_client`
--
ALTER TABLE `mate_sys_client`
  MODIFY `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `mate_sys_config`
--
ALTER TABLE `mate_sys_config`
  MODIFY `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `mate_sys_data_source`
--
ALTER TABLE `mate_sys_data_source`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `mate_sys_depart`
--
ALTER TABLE `mate_sys_depart`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `mate_sys_dict`
--
ALTER TABLE `mate_sys_dict`
  MODIFY `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `mate_sys_log`
--
ALTER TABLE `mate_sys_log`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',AUTO_INCREMENT=115197;
--
-- AUTO_INCREMENT for table `mate_sys_menu`
--
ALTER TABLE `mate_sys_menu`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',AUTO_INCREMENT=2063;
--
-- AUTO_INCREMENT for table `mate_sys_role`
--
ALTER TABLE `mate_sys_role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `mate_sys_role_permission`
--
ALTER TABLE `mate_sys_role_permission`
  MODIFY `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',AUTO_INCREMENT=2173;
--
-- AUTO_INCREMENT for table `mate_sys_route`
--
ALTER TABLE `mate_sys_route`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `mate_sys_tenant`
--
ALTER TABLE `mate_sys_tenant`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '租户id';
--
-- AUTO_INCREMENT for table `mate_sys_user`
--
ALTER TABLE `mate_sys_user`
  MODIFY `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',AUTO_INCREMENT=23;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
