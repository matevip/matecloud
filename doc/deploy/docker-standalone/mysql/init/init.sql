-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` VALUES (1, 'mate.yaml', 'DEFAULT_GROUP', '#服务器配置\nserver:\n  undertow:\n    # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程\n    io-threads: 4\n    # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载\n    worker-threads: 20\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    buffer-size: 1024\n    # 是否分配的直接内存\n    direct-buffers: true\n\n#spring配置\nspring:\n  devtools:\n    restart:\n      log-condition-evaluation-delta: false\n    livereload:\n      port: 23333\n  main:\n    allow-bean-definition-overriding: true\n  #修改文件上传限制\n  servlet:\n    multipart:\n      # 文件最大限制\n      max-file-size: 1024MB\n      # 请求最大限制\n      max-request-size: 1024MB\n      enabled: true\n      # 设置文件缓存的临界点,超过则先保存到临时目录,默认为0,所有文件都会进行缓存\n      file-size-threshold: 0\n\n#feign配置\nfeign:\n  hystrix:\n    enabled: true\n    #sentinel:\n    #enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n#对外暴露端口\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: always\n#knife4j 2.0.6+配置参数\nknife4j:\n  enable: true\n  setting:\n    enableReloadCacheParameter: true', 'd6e93e6ffb1f8fa3e65661d817300b9d', '2021-04-12 02:24:06', '2021-04-12 02:24:06', NULL, '172.18.0.1', '', '', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (2, 'mate-local.yaml', 'DEFAULT_GROUP', '#spring配置\nspring:\n  redis:\n    #redis 单机环境配置\n    host: 127.0.0.1\n    port: 6379\n    password:\n    database: 0\n    ssl: false\n    #redis 集群环境配置\n    #cluster:\n    #  nodes: 127.0.0.1:7001,127.0.0.1:7002,127.0.0.1:7003\n    #  commandTimeout: 5000\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    druid:\n      # MySql校验\n      validation-query: select 1\n\nmate:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/matex?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull\n    username: nacos\n    password: nacos\n  # public-key: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJLLURUH8XNPkX9MME3mQrog3mpkOVYwnYrUqLbSN+Wi6IcmWg1YywHi/kKGUU1MTLjv3C406f1IYu+XWJ3XmX8CAwEAAQ==\n  # 预览模式开关\n  preview:\n    enable: false\n  # 租户开关\n  tenant:\n    enable: false\n  # 网关认证开关\n  uaa:\n    enable: false\n    # 开关：同应用账号互踢\n    isSingleLogin: false\n    ignore-url:\n      - /auth/login/**\n      - /auth/callback/**\n      - /auth/sms-code\n  # Swagger文档开关\n  swagger:\n    enable: true\n', '263f7d2c658daa294c64045db191db39', '2021-04-12 02:24:06', '2021-04-12 02:24:36', NULL, '172.18.0.1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (3, 'mate-dynamic-routes.yaml', 'DEFAULT_GROUP', 'routes:\n  # mate-uaa\n  - id: mate-uaa\n    predicates:\n      - name: Path\n        args:\n          _genkey_0: /mate-uaa/**\n    filters: []\n    uri: lb://mate-uaa\n    order: 0\n  # mate-system\n  - id: mate-system\n    predicates:\n      - name: Path\n        args:\n          _genkey_0: /mate-system/**\n    filters:\n      - name: RequestRateLimiter\n        args:\n          # 限流策略\n          key-resolver: \'#{@remoteAddrKeyResolver}\'\n          # 令牌桶每秒填充率\n          redis-rate-limiter.burstCapacity: 20\n          # 令牌桶容量\n          redis-rate-limiter.replenishRate: 20\n    uri: lb://mate-system\n    order: 0\n  # mate-component\n  - id: mate-component\n    predicates:\n      - name: Path\n        args:\n          _genkey_0: /mate-component/**\n    filters: []\n    uri: lb://mate-component\n    order: 0\n  # mate-code\n  - id: mate-code\n    predicates:\n      - name: Path\n        args:\n          _genkey_0: /mate-code/**\n    filters: []\n    uri: lb://mate-code\n    order: 0', '9684dca44db931eff2a075958f32e27a', '2021-04-12 02:24:06', '2021-04-12 02:24:06', NULL, '172.18.0.1', '', '', NULL, NULL, NULL, 'yaml', NULL);
COMMIT;

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint unsigned NOT NULL,
  `nid` bigint unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
BEGIN;
INSERT INTO `his_config_info` VALUES (0, 1, 'mate.yaml', 'DEFAULT_GROUP', '', '#服务器配置\nserver:\n  undertow:\n    # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程\n    io-threads: 4\n    # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载\n    worker-threads: 20\n    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理\n    buffer-size: 1024\n    # 是否分配的直接内存\n    direct-buffers: true\n\n#spring配置\nspring:\n  devtools:\n    restart:\n      log-condition-evaluation-delta: false\n    livereload:\n      port: 23333\n  main:\n    allow-bean-definition-overriding: true\n  #修改文件上传限制\n  servlet:\n    multipart:\n      # 文件最大限制\n      max-file-size: 1024MB\n      # 请求最大限制\n      max-request-size: 1024MB\n      enabled: true\n      # 设置文件缓存的临界点,超过则先保存到临时目录,默认为0,所有文件都会进行缓存\n      file-size-threshold: 0\n\n#feign配置\nfeign:\n  hystrix:\n    enabled: true\n    #sentinel:\n    #enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n#对外暴露端口\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \"*\"\n  endpoint:\n    health:\n      show-details: always\n#knife4j 2.0.6+配置参数\nknife4j:\n  enable: true\n  setting:\n    enableReloadCacheParameter: true', 'd6e93e6ffb1f8fa3e65661d817300b9d', '2021-04-12 02:24:06', '2021-04-12 02:24:06', NULL, '172.18.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 2, 'mate-local.yaml', 'DEFAULT_GROUP', '', '#spring配置\nspring:\n  redis:\n    #redis 单机环境配置\n    host: 127.0.0.1\n    port: 6379\n    password:\n    database: 0\n    ssl: false\n    #redis 集群环境配置\n    #cluster:\n    #  nodes: 127.0.0.1:7001,127.0.0.1:7002,127.0.0.1:7003\n    #  commandTimeout: 5000\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    druid:\n      # MySql校验\n      validation-query: select 1\n\nmate:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/matex?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull\n    username: root\n    password: 123456\n  # public-key: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJLLURUH8XNPkX9MME3mQrog3mpkOVYwnYrUqLbSN+Wi6IcmWg1YywHi/kKGUU1MTLjv3C406f1IYu+XWJ3XmX8CAwEAAQ==\n  # 预览模式开关\n  preview:\n    enable: false\n  # 租户开关\n  tenant:\n    enable: false\n  # 网关认证开关\n  uaa:\n    enable: false\n    # 开关：同应用账号互踢\n    isSingleLogin: false\n    ignore-url:\n      - /auth/login/**\n      - /auth/callback/**\n      - /auth/sms-code\n  # Swagger文档开关\n  swagger:\n    enable: true\n', '68d2c99558c8573ccc8af4d1ba6c7f82', '2021-04-12 02:24:06', '2021-04-12 02:24:06', NULL, '172.18.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 3, 'mate-dynamic-routes.yaml', 'DEFAULT_GROUP', '', 'routes:\n  # mate-uaa\n  - id: mate-uaa\n    predicates:\n      - name: Path\n        args:\n          _genkey_0: /mate-uaa/**\n    filters: []\n    uri: lb://mate-uaa\n    order: 0\n  # mate-system\n  - id: mate-system\n    predicates:\n      - name: Path\n        args:\n          _genkey_0: /mate-system/**\n    filters:\n      - name: RequestRateLimiter\n        args:\n          # 限流策略\n          key-resolver: \'#{@remoteAddrKeyResolver}\'\n          # 令牌桶每秒填充率\n          redis-rate-limiter.burstCapacity: 20\n          # 令牌桶容量\n          redis-rate-limiter.replenishRate: 20\n    uri: lb://mate-system\n    order: 0\n  # mate-component\n  - id: mate-component\n    predicates:\n      - name: Path\n        args:\n          _genkey_0: /mate-component/**\n    filters: []\n    uri: lb://mate-component\n    order: 0\n  # mate-code\n  - id: mate-code\n    predicates:\n      - name: Path\n        args:\n          _genkey_0: /mate-code/**\n    filters: []\n    uri: lb://mate-code\n    order: 0', '9684dca44db931eff2a075958f32e27a', '2021-04-12 02:24:06', '2021-04-12 02:24:06', NULL, '172.18.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (2, 4, 'mate-local.yaml', 'DEFAULT_GROUP', '', '#spring配置\nspring:\n  redis:\n    #redis 单机环境配置\n    host: 127.0.0.1\n    port: 6379\n    password:\n    database: 0\n    ssl: false\n    #redis 集群环境配置\n    #cluster:\n    #  nodes: 127.0.0.1:7001,127.0.0.1:7002,127.0.0.1:7003\n    #  commandTimeout: 5000\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    druid:\n      # MySql校验\n      validation-query: select 1\n\nmate:\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/matex?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull\n    username: root\n    password: 123456\n  # public-key: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJLLURUH8XNPkX9MME3mQrog3mpkOVYwnYrUqLbSN+Wi6IcmWg1YywHi/kKGUU1MTLjv3C406f1IYu+XWJ3XmX8CAwEAAQ==\n  # 预览模式开关\n  preview:\n    enable: false\n  # 租户开关\n  tenant:\n    enable: false\n  # 网关认证开关\n  uaa:\n    enable: false\n    # 开关：同应用账号互踢\n    isSingleLogin: false\n    ignore-url:\n      - /auth/login/**\n      - /auth/callback/**\n      - /auth/sms-code\n  # Swagger文档开关\n  swagger:\n    enable: true\n', '68d2c99558c8573ccc8af4d1ba6c7f82', '2021-04-12 02:24:36', '2021-04-12 02:24:36', NULL, '172.18.0.1', 'U', '');
COMMIT;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
COMMIT;
