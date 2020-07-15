#
# SQL Export
# Created by Querious (201067)
# Created: July 15, 2020 at 4:46:40 PM GMT+8
# Encoding: Unicode (UTF-8)
#


SET @PREVIOUS_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `mate_sys_user`;
DROP TABLE IF EXISTS `mate_sys_tenant`;
DROP TABLE IF EXISTS `mate_sys_role_permission`;
DROP TABLE IF EXISTS `mate_sys_role`;
DROP TABLE IF EXISTS `mate_sys_menu`;
DROP TABLE IF EXISTS `mate_sys_log`;
DROP TABLE IF EXISTS `mate_sys_dict`;
DROP TABLE IF EXISTS `mate_sys_depart`;
DROP TABLE IF EXISTS `mate_sys_data_source`;
DROP TABLE IF EXISTS `mate_sys_client`;


CREATE TABLE `mate_sys_client` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
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
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '状态',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户端表';


CREATE TABLE `mate_sys_data_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `db_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '数据库类型',
  `driver_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '驱动类型',
  `url` varchar(500) DEFAULT NULL COMMENT '连接地址',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据源表';


CREATE TABLE `mate_sys_depart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '删除标识',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '上级ID',
  `tenant_id` bigint(20) DEFAULT '0' COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='组织机构表';


CREATE TABLE `mate_sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
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
  `is_deleted` int(2) DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典表';


CREATE TABLE `mate_sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `trace_id` varchar(64) DEFAULT NULL COMMENT '跟踪ID',
  `title` varchar(64) DEFAULT NULL COMMENT '日志标题',
  `operation` text COMMENT '操作内容',
  `method` varchar(64) DEFAULT NULL COMMENT '执行方法',
  `params` text COMMENT '参数',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求路径',
  `ip` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `execute_time` decimal(11,0) DEFAULT NULL COMMENT '耗时',
  `location` varchar(64) DEFAULT NULL COMMENT '地区',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '删除标识',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统日志表';


CREATE TABLE `mate_sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(32) DEFAULT NULL COMMENT '菜单标题',
  `permission` varchar(32) DEFAULT NULL COMMENT '菜单权限',
  `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路径',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `keep_alive` char(1) DEFAULT '0' COMMENT '是否缓存该页面: 1:是  0:不是',
  `type` char(1) DEFAULT '0' COMMENT '菜单类型',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '状态',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '删除标识',
  `tenant_id` bigint(20) unsigned DEFAULT '0' COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2032 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';


CREATE TABLE `mate_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(64) DEFAULT NULL COMMENT '角色编码',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '删除标识',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';


CREATE TABLE `mate_sys_role_permission` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint(64) DEFAULT NULL COMMENT '菜单id',
  `role_id` bigint(64) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1452 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限表';


CREATE TABLE `mate_sys_tenant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '租户id',
  `name` varchar(255) DEFAULT NULL COMMENT '租户名称',
  `code` varchar(64) DEFAULT NULL COMMENT '租户编码',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `status` char(1) DEFAULT '0' COMMENT '状态',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租户表';


CREATE TABLE `mate_sys_user` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id` varchar(12) DEFAULT '100000' COMMENT '租户ID',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
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
  `is_deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';




SET FOREIGN_KEY_CHECKS = @PREVIOUS_FOREIGN_KEY_CHECKS;


SET @PREVIOUS_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS = 0;


LOCK TABLES `mate_sys_client` WRITE;
ALTER TABLE `mate_sys_client` DISABLE KEYS;
INSERT INTO `mate_sys_client` (`id`, `client_id`, `client_secret`, `resource_ids`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_by`, `update_by`, `create_time`, `update_time`, `status`, `is_deleted`) VALUES 
	(1,'mate','mate_secret',NULL,'all','refresh_token,password,authorization_code,captcha','http://localhost:10001',NULL,3600,3600,NULL,NULL,NULL,NULL,'2020-07-12 22:49:23','2020-07-13 08:20:13','0',0);
ALTER TABLE `mate_sys_client` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `mate_sys_data_source` WRITE;
ALTER TABLE `mate_sys_data_source` DISABLE KEYS;
INSERT INTO `mate_sys_data_source` (`id`, `name`, `db_type`, `driver_class`, `url`, `username`, `password`, `remark`, `status`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`) VALUES 
	(1,'mysql','mysql','com.mysql.cj.jdbc.Driver','jdbc:mysql://localhost:3306/matex?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true','root','root','1',0,NULL,NULL,'2020-07-05 03:26:34','2020-07-11 08:44:59',0),
	(8,'MYSQL_MDC','mysql','com.mysql.cj.jdbc.Driver','jdbc:mysql://localhost:3306/pangu_mdx?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true&allowPublicKeyRetrieval=true','root','root',NULL,NULL,NULL,NULL,'2020-07-11 10:08:11',NULL,0);
ALTER TABLE `mate_sys_data_source` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `mate_sys_depart` WRITE;
ALTER TABLE `mate_sys_depart` DISABLE KEYS;
INSERT INTO `mate_sys_depart` (`id`, `name`, `sort`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`, `parent_id`, `tenant_id`) VALUES 
	(1,'开发部',0,NULL,NULL,'2020-06-27 23:30:50','2020-07-02 04:49:08','0',-1,0),
	(2,'开发分部',0,NULL,NULL,'2020-06-29 11:14:37',NULL,'0',1,0),
	(3,'开发二部',1,NULL,NULL,'2020-06-29 15:54:27',NULL,'0',1,0),
	(4,'产品部',1,NULL,NULL,'2020-06-29 15:58:54',NULL,'0',-1,0),
	(5,'产品一部',1,NULL,NULL,'2020-06-29 15:59:14',NULL,'0',4,0);
ALTER TABLE `mate_sys_depart` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `mate_sys_dict` WRITE;
ALTER TABLE `mate_sys_dict` DISABLE KEYS;
INSERT INTO `mate_sys_dict` (`id`, `parent_id`, `code`, `dict_key`, `dict_value`, `sort`, `remark`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`) VALUES 
	(1,0,'status','-1','状态',1,'',NULL,NULL,'2020-07-01 09:59:15','2020-07-01 10:02:00',0),
	(2,1,'status','0','启用',1,NULL,NULL,NULL,'2020-07-01 10:02:23','2020-07-01 10:02:59',0),
	(3,1,'status','1','禁用',2,NULL,NULL,NULL,'2020-07-01 10:02:34','2020-07-01 10:02:59',0),
	(4,0,'dbtype','-1','数据库类型',1,NULL,NULL,NULL,'2020-07-11 08:47:02',NULL,0),
	(5,4,'dbtype','mysql','com.mysql.cj.jdbc.Driver',1,NULL,NULL,NULL,'2020-07-11 08:47:44','2020-07-11 08:53:11',0),
	(6,4,'dbtype','oracle','oracle.jdbc.OracleDriver',2,NULL,NULL,NULL,'2020-07-11 08:48:00','2020-07-11 08:54:05',0),
	(7,4,'dbtype','oracle12c','oracle.jdbc.OracleDriver',3,NULL,NULL,NULL,'2020-07-11 08:49:10','2020-07-11 08:54:12',0);
ALTER TABLE `mate_sys_dict` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `mate_sys_log` WRITE;
ALTER TABLE `mate_sys_log` DISABLE KEYS;
INSERT INTO `mate_sys_log` (`id`, `type`, `trace_id`, `title`, `operation`, `method`, `params`, `url`, `ip`, `execute_time`, `location`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`, `tenant_id`) VALUES 
	(1,'1','465efcd80d384bc89d52f4424b445862','获取验证码','{"msg":"操作成功","code":200,"data":{"codeUrl":"data:image/png;base64,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","key":"72ca568d6dd443d789edc915b81ad0f4"},"time":1594801305706}','GET','{}','/auth/code','127.0.0.1',1386,'本地',NULL,NULL,'2020-07-15 08:21:45',NULL,'0',NULL),
	(2,'1','2a277270f86f4e1ba3307587c6b3d12c','用户登录','{"msg":"操作成功","code":200,"data":{"avatar":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg","userName":"admin","userId":"2","roleId":"1","jti":"2d5c723f-6206-4c59-8158-6ef8f73fc8e8","accessToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInJvbGVJZCI6IjEiLCJzY29wZSI6WyJhbGwiXSwiYXZhdGFyIjoiaHR0cHM6Ly9zczAuYmRzdGF0aWMuY29tLzcwY0Z2SFNoX1ExWW54R2twb1dLMUhGNmhoeS9pdC91PTIwODQxMTgxMjgsMjUxODcxMTAzNCZmbT0yNiZncD0wLmpwZyIsImV4cCI6MTU5NDgwNDkxMiwidXNlck5hbWUiOiJhZG1pbiIsInVzZXJJZCI6IjIiLCJhdXRob3JpdGllcyI6WyJzeXM6ZGF0YXNvdXJjZTpleHBvcnQiLCJzeXM6ZGVwYXJ0OmV4cG9ydCIsInN5czptZW51OmRlbGV0ZSIsInN5czpjbGllbnQ6c3RhdHVzIiwic3lzOmRhdGFzb3VyY2U6ZGVsZXRlIiwic3lzOmRlcGFydDplZGl0Iiwic3lzOm1lbnU6YWRkIiwic3lzOnVzZXI6YWRkIiwic3lzOnVzZXI6ZGlzYWJsZSIsInN5czp1c2VyOmV4cG9ydCIsInN5czpkYXRhc291cmNlOmFkZCIsInN5czpyb2xlOmVkaXQiLCJzeXM6ZGVwYXJ0OmFkZCIsInN5czpkZXBhcnQ6ZGVsZXRlIiwiZGV2b3BzOmdlbiIsInN5czp1c2VyOmRlbGV0ZSIsInN5czpkYXRhc291cmNlOmVkaXQiLCJzeXM6cm9sZTphZGQiLCJzeXM6Y2xpZW50OmV4cG9ydCIsInN5czpjbGllbnQ6YWRkIiwic3lzOnJvbGU6cGVybSIsInN5czp1c2VyOmVuYWJsZSIsInN5czpyb2xlOmV4cG9ydCIsInN5czp1c2VyOmVkaXQiLCJzeXM6cm9sZTpkZWxldGUiLCJzeXM6Y2xpZW50OmRlbGV0ZSIsInN5czptZW51OmRpc2FibGUiLCJzeXM6bWVudTplbmFibGUiLCJzeXM6Y2xpZW50OmVkaXQiLCJzeXM6bWVudTplZGl0Iiwic3lzOm1lbnU6ZXhwb3J0Il0sImp0aSI6IjJkNWM3MjNmLTYyMDYtNGM1OS04MTU4LTZlZjhmNzNmYzhlOCIsImNsaWVudF9pZCI6Im1hdGUifQ.utqy8lHM8yLVmo3iEBJ0V0OLpp4fZmMxI58SdtO2PIY","refreshToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInJvbGVJZCI6IjEiLCJzY29wZSI6WyJhbGwiXSwiYXRpIjoiMmQ1YzcyM2YtNjIwNi00YzU5LTgxNTgtNmVmOGY3M2ZjOGU4IiwiYXZhdGFyIjoiaHR0cHM6Ly9zczAuYmRzdGF0aWMuY29tLzcwY0Z2SFNoX1ExWW54R2twb1dLMUhGNmhoeS9pdC91PTIwODQxMTgxMjgsMjUxODcxMTAzNCZmbT0yNiZncD0wLmpwZyIsImV4cCI6MTU5NDgwNDkxMiwidXNlck5hbWUiOiJhZG1pbiIsInVzZXJJZCI6IjIiLCJhdXRob3JpdGllcyI6WyJzeXM6ZGF0YXNvdXJjZTpleHBvcnQiLCJzeXM6ZGVwYXJ0OmV4cG9ydCIsInN5czptZW51OmRlbGV0ZSIsInN5czpjbGllbnQ6c3RhdHVzIiwic3lzOmRhdGFzb3VyY2U6ZGVsZXRlIiwic3lzOmRlcGFydDplZGl0Iiwic3lzOm1lbnU6YWRkIiwic3lzOnVzZXI6YWRkIiwic3lzOnVzZXI6ZGlzYWJsZSIsInN5czp1c2VyOmV4cG9ydCIsInN5czpkYXRhc291cmNlOmFkZCIsInN5czpyb2xlOmVkaXQiLCJzeXM6ZGVwYXJ0OmFkZCIsInN5czpkZXBhcnQ6ZGVsZXRlIiwiZGV2b3BzOmdlbiIsInN5czp1c2VyOmRlbGV0ZSIsInN5czpkYXRhc291cmNlOmVkaXQiLCJzeXM6cm9sZTphZGQiLCJzeXM6Y2xpZW50OmV4cG9ydCIsInN5czpjbGllbnQ6YWRkIiwic3lzOnJvbGU6cGVybSIsInN5czp1c2VyOmVuYWJsZSIsInN5czpyb2xlOmV4cG9ydCIsInN5czp1c2VyOmVkaXQiLCJzeXM6cm9sZTpkZWxldGUiLCJzeXM6Y2xpZW50OmRlbGV0ZSIsInN5czptZW51OmRpc2FibGUiLCJzeXM6bWVudTplbmFibGUiLCJzeXM6Y2xpZW50OmVkaXQiLCJzeXM6bWVudTplZGl0Iiwic3lzOm1lbnU6ZXhwb3J0Il0sImp0aSI6ImQwNWY1ODQ5LTExNjEtNDNhZS05NTYzLWVhMmI0MGY1YzQ2MCIsImNsaWVudF9pZCI6Im1hdGUifQ.nNueIoRzCzmXvljCgzQZCwe0HgEQq6YV_SUlKTAQuUk"},"time":1594801312115}','POST','{"principal":{"authenticated":true,"authorities":[],"details":{"remoteAddress":"169.254.88.126"},"name":"mate","principal":{"accountNonExpired":true,"accountNonLocked":true,"authorities":[],"credentialsNonExpired":true,"enabled":true,"username":"mate"}},"":{"password":"e10adc3949ba59abbe56e057f20f883e","grant_type":"password","scope":"all","username":"admin"}}','/oauth/token','127.0.0.1',1442,'本地',NULL,NULL,'2020-07-15 08:21:52',NULL,'0',NULL),
	(3,'1','9bceac084bb24ea5be4d88a27a3aed42','获取用户信息给VUE','{"msg":"操作成功","code":200,"data":{"departId":1,"avatar":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg","userName":"admin","roleId":1},"time":1594801313019}','GET','{}','/auth/userInfo','127.0.0.1',861,'本地','admin',NULL,'2020-07-15 08:21:53',NULL,'0',NULL),
	(4,'1','5c47fc781d974c5d844eddd6f8af4c68','根据RoleId查询routes列表','{"msg":"操作成功","code":200,"data":[{"redirect":"noRedirect","hidden":false,"icon":"imac","typeName":"目录","sort":1,"type":"0","parentId":-1,"path":"/system","component":"Layout","children":[{"hidden":false,"icon":"user","typeName":"菜单","sort":1,"type":"1","parentId":1000,"path":"/system/user","component":"/system/user","meta":{"breadcrumb":true,"icon":"user","title":"用户管理"},"name":"用户管理","id":1100,"alwaysShow":false},{"hidden":false,"icon":"repair","typeName":"菜单","sort":1,"type":"1","parentId":1000,"path":"/system/role","component":"/system/role","meta":{"breadcrumb":true,"icon":"repair","title":"角色管理"},"name":"角色管理","id":1200,"alwaysShow":false},{"hidden":false,"icon":"tree","typeName":"菜单","sort":1,"type":"1","parentId":1000,"path":"/system/menu","component":"/system/menu","meta":{"breadcrumb":true,"icon":"tree","title":"菜单管理"},"name":"菜单管理","id":1300,"alwaysShow":false},{"hidden":false,"icon":"table2","typeName":"菜单","sort":1,"type":"1","parentId":1000,"path":"/system/depart","component":"/system/depart","meta":{"breadcrumb":true,"icon":"table2","title":"部门管理"},"name":"部门管理","id":1400,"alwaysShow":false},{"hidden":false,"icon":"iPhone","typeName":"菜单","sort":1,"type":"1","parentId":1000,"path":"/system/client","component":"/system/client","meta":{"breadcrumb":true,"icon":"iPhone","title":"客户端管理"},"name":"客户端管理","id":2026,"alwaysShow":false}],"meta":{"breadcrumb":true,"icon":"imac","title":"系统管理"},"name":"系统管理","id":1000,"alwaysShow":true},{"redirect":"noRedirect","hidden":false,"icon":"settings","typeName":"目录","sort":1,"type":"0","parentId":-1,"path":"/devops","component":"Layout","children":[{"hidden":false,"icon":"table","typeName":"菜单","permission":"","sort":1,"type":"1","parentId":2015,"path":"/devops/datasource","component":"/devops/datasource","meta":{"breadcrumb":true,"icon":"table","title":"数据源管理"},"name":"数据源管理","id":2016,"alwaysShow":false},{"hidden":false,"icon":"download","typeName":"菜单","sort":1,"type":"1","parentId":2015,"path":"/devops/generator","component":"/devops/generator","meta":{"breadcrumb":true,"icon":"download","title":"代码生成"},"name":"代码生成","id":2021,"alwaysShow":false},{"hidden":false,"icon":"validCode","typeName":"菜单","sort":1,"type":"1","parentId":2015,"path":"/devops/monitor","component":"/devops/monitor","meta":{"breadcrumb":true,"icon":"validCode","title":"监控配置中心"},"name":"监控配置中心","id":2023,"alwaysShow":false}],"meta":{"breadcrumb":true,"icon":"settings","title":"开发运维"},"name":"开发运维","id":2015,"alwaysShow":true}],"time":1594801313836}','GET','{"user":{"account":"admin","roleId":"1","userId":"2"}}','/sys-menu/routes','127.0.0.1',773,'本地','admin',NULL,'2020-07-15 08:21:53',NULL,'0',NULL),
	(5,'1','15af8fac1df74787a831402d62a30253','获取角色接口列表','{"msg":"操作成功","code":200,"data":[{"isDeleted":"0","createTime":"2020-06-28T15:02:16","roleCode":"admin","roleName":"管理员","description":"管理员组","id":1},{"isDeleted":"0","createTime":"2020-06-28T07:02:36","roleCode":"demo2","roleName":"演示会员","description":"演示会员组","updateTime":"2020-06-28T07:02:58","id":2}],"time":1594801316397}','GET','{"":{}}','/sys-role/list','127.0.0.1',2241,'本地','admin',NULL,'2020-07-15 08:21:56',NULL,'0',NULL),
	(6,'1','bdb0b785f7da40fdae1eccaccd4d1379','查询所有系统菜单资源列表','{"msg":"操作成功","code":200,"data":[{"label":"系统管理","parentId":-1,"children":[{"label":"用户管理","parentId":1000,"children":[{"label":"用户新增","parentId":1100,"children":[],"id":1101},{"label":"用户修改","parentId":1100,"children":[],"id":1102},{"label":"用户删除","parentId":1100,"children":[],"id":1103},{"label":"用户启用","parentId":1100,"children":[],"id":1104},{"label":"用户禁用","parentId":1100,"children":[],"id":1105},{"label":"用户导出","parentId":1100,"children":[],"id":1106}],"id":1100},{"label":"角色管理","parentId":1000,"children":[{"label":"角色新增","parentId":1200,"children":[],"id":1201},{"label":"角色修改","parentId":1200,"children":[],"id":1202},{"label":"角色删除","parentId":1200,"children":[],"id":1203},{"label":"角色导出","parentId":1200,"children":[],"id":1204},{"label":"角色权限","parentId":1200,"children":[],"id":1205}],"id":1200},{"label":"菜单管理","parentId":1000,"children":[{"label":"菜单新增","parentId":1300,"children":[],"id":1301},{"label":"菜单修改","parentId":1300,"children":[],"id":1302},{"label":"菜单删除","parentId":1300,"children":[],"id":1303},{"label":"菜单启用","parentId":1300,"children":[],"id":1304},{"label":"菜单禁用","parentId":1300,"children":[],"id":1305},{"label":"菜单导出","parentId":1300,"children":[],"id":1306}],"id":1300},{"label":"部门管理","parentId":1000,"children":[{"label":"部门新增","parentId":1400,"children":[],"id":1401},{"label":"部门修改","parentId":1400,"children":[],"id":1402},{"label":"部门删除","parentId":1400,"children":[],"id":1403},{"label":"部门导出","parentId":1400,"children":[],"id":1404}],"id":1400},{"label":"客户端管理","parentId":1000,"children":[{"label":"新增客户端","parentId":2026,"children":[],"id":2027},{"label":"修改客户端","parentId":2026,"children":[],"id":2028},{"label":"删除客户端","parentId":2026,"children":[],"id":2029},{"label":"导出客户端","parentId":2026,"children":[],"id":2030},{"label":"启禁客户端","parentId":2026,"children":[],"id":2031}],"id":2026}],"id":1000},{"label":"监控管理","parentId":-1,"children":[],"id":2009},{"label":"开发运维","parentId":-1,"children":[{"label":"数据源管理","parentId":2015,"children":[{"label":"数据源新增","parentId":2016,"children":[],"id":2017},{"label":"数据源修改","parentId":2016,"children":[],"id":2018},{"label":"数据源删除","parentId":2016,"children":[],"id":2019},{"label":"数据源导出","parentId":2016,"children":[],"id":2020}],"id":2016},{"label":"代码生成","parentId":2015,"children":[{"label":"代码生成","parentId":2021,"children":[],"id":2022}],"id":2021},{"label":"监控配置中心","parentId":2015,"children":[],"id":2023}],"id":2015}],"time":1594801316394}','GET','{}','/sys-menu/asyncList','127.0.0.1',2233,'本地','admin',NULL,'2020-07-15 08:21:56',NULL,'0',NULL),
	(7,'1','fbb9d0316e2f49b6b51d92bdf45c59aa','获取菜单接口列表','{"msg":"操作成功","code":200,"data":[{"redirect":"noRedirect","hidden":false,"icon":"imac","typeName":"目录","sort":1,"type":"0","parentId":-1,"path":"/system","component":"Layout","children":[{"hidden":false,"icon":"user","typeName":"菜单","sort":1,"type":"1","parentId":1000,"path":"/system/user","component":"/system/user","children":[{"hidden":false,"typeName":"按钮","permission":"sys:user:add","sort":1,"type":"2","parentId":1100,"path":"","component":"","meta":{"breadcrumb":true,"title":"用户新增"},"name":"用户新增","id":1101,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:user:edit","sort":1,"type":"2","parentId":1100,"meta":{"breadcrumb":true,"title":"用户修改"},"name":"用户修改","id":1102,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:user:delete","sort":1,"type":"2","parentId":1100,"meta":{"breadcrumb":true,"title":"用户删除"},"name":"用户删除","id":1103,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:user:enable","sort":1,"type":"2","parentId":1100,"meta":{"breadcrumb":true,"title":"用户启用"},"name":"用户启用","id":1104,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:user:disable","sort":1,"type":"2","parentId":1100,"meta":{"breadcrumb":true,"title":"用户禁用"},"name":"用户禁用","id":1105,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:user:export","sort":1,"type":"2","parentId":1100,"meta":{"breadcrumb":true,"title":"用户导出"},"name":"用户导出","id":1106,"alwaysShow":false}],"meta":{"breadcrumb":true,"icon":"user","title":"用户管理"},"name":"用户管理","id":1100,"alwaysShow":false},{"hidden":false,"icon":"repair","typeName":"菜单","sort":1,"type":"1","parentId":1000,"path":"/system/role","component":"/system/role","children":[{"hidden":false,"typeName":"按钮","permission":"sys:role:add","sort":1,"type":"2","parentId":1200,"meta":{"breadcrumb":true,"title":"角色新增"},"name":"角色新增","id":1201,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:role:edit","sort":1,"type":"2","parentId":1200,"meta":{"breadcrumb":true,"title":"角色修改"},"name":"角色修改","id":1202,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:role:delete","sort":1,"type":"2","parentId":1200,"meta":{"breadcrumb":true,"title":"角色删除"},"name":"角色删除","id":1203,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:role:export","sort":1,"type":"2","parentId":1200,"meta":{"breadcrumb":true,"title":"角色导出"},"name":"角色导出","id":1204,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:role:perm","sort":1,"type":"2","parentId":1200,"meta":{"breadcrumb":true,"title":"角色权限"},"name":"角色权限","id":1205,"alwaysShow":false}],"meta":{"breadcrumb":true,"icon":"repair","title":"角色管理"},"name":"角色管理","id":1200,"alwaysShow":false},{"hidden":false,"icon":"tree","typeName":"菜单","sort":1,"type":"1","parentId":1000,"path":"/system/menu","component":"/system/menu","children":[{"hidden":false,"typeName":"按钮","permission":"sys:menu:add","sort":1,"type":"2","parentId":1300,"meta":{"breadcrumb":true,"title":"菜单新增"},"name":"菜单新增","id":1301,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:menu:edit","sort":1,"type":"2","parentId":1300,"meta":{"breadcrumb":true,"title":"菜单修改"},"name":"菜单修改","id":1302,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:menu:delete","sort":1,"type":"2","parentId":1300,"meta":{"breadcrumb":true,"title":"菜单删除"},"name":"菜单删除","id":1303,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:menu:enable","sort":1,"type":"2","parentId":1300,"meta":{"breadcrumb":true,"title":"菜单启用"},"name":"菜单启用","id":1304,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:menu:disable","sort":1,"type":"2","parentId":1300,"meta":{"breadcrumb":true,"title":"菜单禁用"},"name":"菜单禁用","id":1305,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:menu:export","sort":1,"type":"2","parentId":1300,"meta":{"breadcrumb":true,"title":"菜单导出"},"name":"菜单导出","id":1306,"alwaysShow":false}],"meta":{"breadcrumb":true,"icon":"tree","title":"菜单管理"},"name":"菜单管理","id":1300,"alwaysShow":false},{"hidden":false,"icon":"table2","typeName":"菜单","sort":1,"type":"1","parentId":1000,"path":"/system/depart","component":"/system/depart","children":[{"hidden":false,"typeName":"按钮","permission":"sys:depart:add","sort":1,"type":"2","parentId":1400,"meta":{"breadcrumb":true,"title":"部门新增"},"name":"部门新增","id":1401,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:depart:edit","sort":1,"type":"2","parentId":1400,"meta":{"breadcrumb":true,"title":"部门修改"},"name":"部门修改","id":1402,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:depart:delete","sort":1,"type":"2","parentId":1400,"meta":{"breadcrumb":true,"title":"部门删除"},"name":"部门删除","id":1403,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:depart:export","sort":1,"type":"2","parentId":1400,"meta":{"breadcrumb":true,"title":"部门导出"},"name":"部门导出","id":1404,"alwaysShow":false}],"meta":{"breadcrumb":true,"icon":"table2","title":"部门管理"},"name":"部门管理","id":1400,"alwaysShow":false},{"hidden":false,"icon":"iPhone","typeName":"菜单","sort":1,"type":"1","parentId":1000,"path":"/system/client","component":"/system/client","children":[{"hidden":false,"typeName":"按钮","permission":"sys:client:add","sort":1,"type":"2","parentId":2026,"meta":{"breadcrumb":true,"title":"新增客户端"},"name":"新增客户端","id":2027,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:client:edit","sort":1,"type":"2","parentId":2026,"meta":{"breadcrumb":true,"title":"修改客户端"},"name":"修改客户端","id":2028,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:client:delete","sort":1,"type":"2","parentId":2026,"meta":{"breadcrumb":true,"title":"删除客户端"},"name":"删除客户端","id":2029,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:client:export","sort":1,"type":"2","parentId":2026,"meta":{"breadcrumb":true,"title":"导出客户端"},"name":"导出客户端","id":2030,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:client:status","sort":1,"type":"2","parentId":2026,"meta":{"breadcrumb":true,"title":"启禁客户端"},"name":"启禁客户端","id":2031,"alwaysShow":false}],"meta":{"breadcrumb":true,"icon":"iPhone","title":"客户端管理"},"name":"客户端管理","id":2026,"alwaysShow":false}],"meta":{"breadcrumb":true,"icon":"imac","title":"系统管理"},"name":"系统管理","id":1000,"alwaysShow":true},{"redirect":"noRedirect","hidden":false,"icon":"Galaxee","typeName":"目录","sort":1,"type":"0","parentId":-1,"path":"/monitor","component":"Layout","meta":{"breadcrumb":true,"icon":"Galaxee","title":"监控管理"},"name":"监控管理","id":2009,"alwaysShow":true},{"redirect":"noRedirect","hidden":false,"icon":"settings","typeName":"目录","sort":1,"type":"0","parentId":-1,"path":"/devops","component":"Layout","children":[{"hidden":false,"icon":"table","typeName":"菜单","permission":"","sort":1,"type":"1","parentId":2015,"path":"/devops/datasource","component":"/devops/datasource","children":[{"hidden":false,"typeName":"按钮","permission":"sys:datasource:add","sort":1,"type":"2","parentId":2016,"meta":{"breadcrumb":true,"title":"数据源新增"},"name":"数据源新增","id":2017,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:datasource:edit","sort":1,"type":"2","parentId":2016,"meta":{"breadcrumb":true,"title":"数据源修改"},"name":"数据源修改","id":2018,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:datasource:delete","sort":1,"type":"2","parentId":2016,"meta":{"breadcrumb":true,"title":"数据源删除"},"name":"数据源删除","id":2019,"alwaysShow":false},{"hidden":false,"typeName":"按钮","permission":"sys:datasource:export","sort":1,"type":"2","parentId":2016,"meta":{"breadcrumb":true,"title":"数据源导出"},"name":"数据源导出","id":2020,"alwaysShow":false}],"meta":{"breadcrumb":true,"icon":"table","title":"数据源管理"},"name":"数据源管理","id":2016,"alwaysShow":false},{"hidden":false,"icon":"download","typeName":"菜单","sort":1,"type":"1","parentId":2015,"path":"/devops/generator","component":"/devops/generator","children":[{"hidden":false,"typeName":"按钮","permission":"devops:gen","sort":1,"type":"2","parentId":2021,"meta":{"breadcrumb":true,"title":"代码生成"},"name":"代码生成","id":2022,"alwaysShow":false}],"meta":{"breadcrumb":true,"icon":"download","title":"代码生成"},"name":"代码生成","id":2021,"alwaysShow":false},{"hidden":false,"icon":"validCode","typeName":"菜单","sort":1,"type":"1","parentId":2015,"path":"/devops/monitor","component":"/devops/monitor","meta":{"breadcrumb":true,"icon":"validCode","title":"监控配置中心"},"name":"监控配置中心","id":2023,"alwaysShow":false}],"meta":{"breadcrumb":true,"icon":"settings","title":"开发运维"},"name":"开发运维","id":2015,"alwaysShow":true}],"time":1594801326949}','GET','{"":{}}','/sys-menu/list','127.0.0.1',532,'本地','admin',NULL,'2020-07-15 08:22:06',NULL,'0',NULL),
	(8,'1','62ec9041d57f47d597e7ec6119594589','获取系统部门资源列表','{"msg":"操作成功","code":200,"data":[{"isDeleted":"0","children":[{"isDeleted":"0","children":[],"createTime":"2020-06-29T11:14:37","hasChildren":false,"name":"开发分部","tenantId":0,"id":2,"sort":0,"parentId":1},{"isDeleted":"0","children":[],"createTime":"2020-06-29T15:54:27","hasChildren":false,"name":"开发二部","tenantId":0,"id":3,"sort":1,"parentId":1}],"createTime":"2020-06-27T23:30:50","hasChildren":false,"name":"开发部","tenantId":0,"updateTime":"2020-07-02T04:49:08","id":1,"sort":0,"parentId":-1},{"isDeleted":"0","children":[{"isDeleted":"0","children":[],"createTime":"2020-06-29T15:59:14","hasChildren":false,"name":"产品一部","tenantId":0,"id":5,"sort":1,"parentId":4}],"createTime":"2020-06-29T15:58:54","hasChildren":false,"name":"产品部","tenantId":0,"id":4,"sort":1,"parentId":-1}],"time":1594801329481}','GET','{"":{}}','/sys-depart/list','127.0.0.1',629,'本地','admin',NULL,'2020-07-15 08:22:09',NULL,'0',NULL),
	(9,'1','4832c18eaf544e3aaa096d0a36911112','用户列表','{"msg":"操作成功","code":200,"data":{"current":1,"total":11,"hitCount":false,"pages":2,"size":10,"records":[{"roleId":1,"sex":1,"telephone":"18910001000","updateTime":"2020-07-04T10:23:13","avatar":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg","realName":"mate","password":"{bcrypt}$2a$10$jddK.xwTn99XM9ggy64Zgu.eBK2GBiyk9RmtQEjg99S2F8otQ8ieq","isDeleted":"0","createTime":"2020-07-02T15:29:12","name":"admin","roleName":"管理员","statusName":"启用","tenantId":"100000","departId":1,"id":2,"account":"admin","email":"mate@mate.vip","departName":"开发部","status":"0"},{"roleId":2,"sex":1,"telephone":"18910001000","updateTime":"2020-07-02T05:38:59","avatar":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg","realName":"mate","password":"{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia","isDeleted":"0","createTime":"2020-06-16T20:05:43","name":"admin2","roleName":"演示会员","statusName":"启用","tenantId":"100000","departId":4,"id":3,"account":"admin2","email":"mate@mate.vip","departName":"产品部","status":"0"},{"roleId":2,"sex":1,"telephone":"18910001000","updateTime":"2020-07-02T05:39:00","avatar":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg","realName":"mate","password":"{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia","isDeleted":"0","createTime":"2020-06-17T04:05:43","name":"admin4","roleName":"演示会员","statusName":"启用","tenantId":"100000","departId":1,"id":4,"account":"admin4","email":"mate@mate.vip","departName":"开发部","status":"0"},{"roleId":1,"sex":1,"telephone":"18910001000","updateTime":"2020-07-02T05:39:01","avatar":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg","realName":"mate","password":"{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia","isDeleted":"0","createTime":"2020-06-17T04:05:43","name":"admin5","roleName":"管理员","statusName":"启用","tenantId":"100000","departId":1,"id":5,"account":"admin5","email":"mate@mate.vip","departName":"开发部","status":"0"},{"roleId":1,"sex":1,"telephone":"18910001000","updateTime":"2020-07-02T13:39:01","avatar":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg","realName":"mate","password":"{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia","isDeleted":"0","createTime":"2020-06-17T12:05:43","name":"admin6","roleName":"管理员","statusName":"启用","tenantId":"100000","departId":1,"id":6,"account":"admin6","email":"mate@mate.vip","departName":"开发部","status":"0"},{"roleId":1,"sex":1,"telephone":"18910001000","updateTime":"2020-06-30T12:55:52","avatar":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg","realName":"mate","password":"{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia","isDeleted":"0","createTime":"2020-06-16T12:05:43","name":"admin6","roleName":"管理员","statusName":"启用","tenantId":"100000","departId":1,"id":7,"account":"admin7","email":"mate@mate.vip","departName":"开发部","status":"0"},{"roleId":2,"sex":1,"telephone":"18910001000","updateTime":"2020-07-15T04:31:24","avatar":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg","realName":"mate","password":"{bcrypt}$2a$10$6YPJ0dkaqh9x3zwTfidRBeT0U5vJ7Si7wXFx9Gc/K3pMxz8J18TrW","isDeleted":"0","createTime":"2020-06-16T20:05:43","name":"admin6","roleName":"演示会员","statusName":"启用","tenantId":"100000","departId":1,"id":8,"account":"admin8","email":"mate@mate.vip","departName":"开发部","status":"0"},{"roleId":1,"sex":1,"telephone":"18910001000","updateTime":"2020-07-01T21:39:03","avatar":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg","realName":"mate","password":"{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia","isDeleted":"0","createTime":"2020-06-16T20:05:43","name":"admin6","roleName":"管理员","statusName":"启用","tenantId":"100000","departId":1,"id":9,"account":"admin9","email":"mate@mate.vip","departName":"开发部","status":"1"},{"roleId":1,"sex":1,"telephone":"18910001000","updateTime":"2020-07-15T03:58:00","avatar":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg","realName":"mate","password":"{bcrypt}$2a$10$A5cfRbFDCsOg.1UTlWyEZu8DJHSr9GnANXQMsRSIDAtZHuiQicr0K","isDeleted":"0","createTime":"2020-06-15T12:05:43","name":"admin6","roleName":"管理员","statusName":"启用","tenantId":"100000","departId":1,"id":10,"account":"admin10","email":"mate@mate.vip","departName":"开发部","status":"1"},{"birthday":"2020-06-29T08:00:00","roleId":1,"sex":0,"updateTime":"2020-07-07T11:41:34","password":"{bcrypt}$2a$10$Yg737tR5BkroA0dJs8H2JureZhMPT3InoSJqGkjhvZ9JvqrUM.yR.","isDeleted":"0","createTime":"2020-07-03T01:41:45","name":"pp","roleName":"管理员","statusName":"启用","tenantId":"100000","departId":1,"id":20,"account":"pp","departName":"开发部","status":"1"}],"searchCount":true,"orders":[]},"time":1594801332571}','GET','{"":{"current":"1","size":"10"}}','/sys-user/list','127.0.0.1',882,'本地','admin',NULL,'2020-07-15 08:22:12',NULL,'0',NULL),
	(10,'1','944179c385e248bb91bc3d42ed595887','退出登录并删除TOKEN','{"msg":"操作成功","code":200,"time":1594801335977}','POST','{}','/auth/logout','127.0.0.1',991,'本地','admin',NULL,'2020-07-15 08:22:16',NULL,'0',NULL),
	(11,'1','cdfbef248c8a4fa3a030ff7ef461550b','获取验证码','{"msg":"操作成功","code":200,"data":{"codeUrl":"data:image/png;base64,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","key":"bc531e5b085f4246b868dfbc4280be93"},"time":1594801337139}','GET','{}','/auth/code','127.0.0.1',1025,'本地',NULL,NULL,'2020-07-15 08:22:17',NULL,'0',NULL);
ALTER TABLE `mate_sys_log` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `mate_sys_menu` WRITE;
ALTER TABLE `mate_sys_menu` DISABLE KEYS;
INSERT INTO `mate_sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `sort`, `keep_alive`, `type`, `create_by`, `update_by`, `create_time`, `update_time`, `status`, `is_deleted`, `tenant_id`) VALUES 
	(1000,'系统管理',NULL,'/system',-1,'imac',1,'0','0',NULL,NULL,'2020-06-17 14:21:45','2020-06-24 11:19:19','0','0',0),
	(1100,'用户管理',NULL,'/system/user',1000,'user',1,'0','1',NULL,NULL,'2020-06-18 14:28:36','2020-06-25 11:19:20','0','0',0),
	(1101,'用户新增','sys:user:add','',1100,NULL,1,'0','2',NULL,NULL,'2020-06-17 14:32:51','2020-07-03 08:51:48','0','0',0),
	(1102,'用户修改','sys:user:edit',NULL,1100,NULL,1,'0','2',NULL,NULL,'2020-06-20 00:27:40','2020-07-03 08:51:50','0','0',0),
	(1103,'用户删除','sys:user:delete',NULL,1100,NULL,1,'0','2',NULL,NULL,'2020-06-20 00:27:56','2020-07-03 08:51:52','0','0',0),
	(1104,'用户启用','sys:user:enable',NULL,1100,NULL,1,'0','2',NULL,NULL,'2020-07-03 08:49:47','2020-07-03 08:55:39','0','0',0),
	(1105,'用户禁用','sys:user:disable',NULL,1100,NULL,1,'0','2',NULL,NULL,'2020-07-03 08:50:16','2020-07-03 08:55:40','0','0',0),
	(1106,'用户导出','sys:user:export',NULL,1100,NULL,1,'0','2',NULL,NULL,'2020-07-03 08:50:58','2020-07-03 08:55:42','0','0',0),
	(1200,'角色管理',NULL,'/system/role',1000,'repair',1,'0','1',NULL,NULL,'2020-06-19 16:36:01','2020-06-25 03:19:23','0','0',0),
	(1201,'角色新增','sys:role:add',NULL,1200,NULL,1,'0','2',NULL,NULL,'2020-06-20 00:37:12','2020-07-03 14:00:56','0','0',0),
	(1202,'角色修改','sys:role:edit',NULL,1200,NULL,1,'0','2',NULL,NULL,'2020-06-20 00:38:23','2020-07-03 14:01:06','0','0',0),
	(1203,'角色删除','sys:role:delete',NULL,1200,NULL,1,'0','2',NULL,NULL,'2020-06-20 00:38:53','2020-07-03 14:01:14','0','0',0),
	(1204,'角色导出','sys:role:export',NULL,1200,NULL,1,'0','2',NULL,NULL,'2020-07-03 14:02:37','2020-07-03 14:02:50','0','0',0),
	(1205,'角色权限','sys:role:perm',NULL,1200,NULL,1,'0','2',NULL,NULL,'2020-07-03 14:03:32',NULL,'0','0',0),
	(1300,'菜单管理',NULL,'/system/menu',1000,'tree',1,'0','1',NULL,NULL,'2020-06-19 16:39:07','2020-06-25 03:19:45','0','0',0),
	(1301,'菜单新增','sys:menu:add',NULL,1300,NULL,1,'0','2',NULL,NULL,'2020-06-20 00:39:48','2020-07-03 14:11:59','0','0',0),
	(1302,'菜单修改','sys:menu:edit',NULL,1300,NULL,1,'0','2',NULL,NULL,'2020-06-20 00:40:21','2020-07-03 14:12:15','0','0',0),
	(1303,'菜单删除','sys:menu:delete',NULL,1300,NULL,1,'0','2',NULL,NULL,'2020-06-20 00:40:42','2020-07-03 14:12:23','0','0',0),
	(1304,'菜单启用','sys:menu:enable',NULL,1300,NULL,1,'0','2',NULL,NULL,'2020-07-03 14:12:59','2020-07-03 14:13:14','0','0',0),
	(1305,'菜单禁用','sys:menu:disable',NULL,1300,NULL,1,'0','2',NULL,NULL,'2020-07-03 14:13:34','2020-07-03 14:13:57','0','0',0),
	(1306,'菜单导出','sys:menu:export',NULL,1300,NULL,1,'0','2',NULL,NULL,'2020-07-03 14:14:32',NULL,'0','0',0),
	(1400,'部门管理',NULL,'/system/depart',1000,'table2',1,'0','1',NULL,NULL,'2020-06-26 22:52:41','2020-07-03 14:25:56','0','0',0),
	(1401,'部门新增','sys:depart:add',NULL,1400,NULL,1,'0','2',NULL,NULL,'2020-06-27 14:53:37','2020-07-03 14:26:12','0','0',0),
	(1402,'部门修改','sys:depart:edit',NULL,1400,NULL,1,'0','2',NULL,NULL,'2020-06-27 14:54:47','2020-07-03 14:26:14','0','0',0),
	(1403,'部门删除','sys:depart:delete',NULL,1400,NULL,1,'0','2',NULL,NULL,'2020-06-27 14:55:15','2020-07-03 14:26:17','0','0',0),
	(1404,'部门导出','sys:depart:export',NULL,1400,NULL,1,'0','2',NULL,NULL,'2020-07-03 14:27:26','2020-07-03 14:27:45','0','0',0),
	(2009,'监控管理',NULL,'/monitor',-1,'Galaxee',1,'0','0',NULL,NULL,'2020-06-26 16:00:35',NULL,'0','0',0),
	(2015,'开发运维',NULL,'/devops',-1,'settings',1,'0','0',NULL,NULL,'2020-07-05 19:20:31','2020-07-06 03:20:52','0','0',0),
	(2016,'数据源管理','','/devops/datasource',2015,'table',1,'0','1',NULL,NULL,'2020-07-06 19:21:58','2020-07-09 06:50:01','0','0',0),
	(2017,'数据源新增','sys:datasource:add',NULL,2016,NULL,1,'0','2',NULL,NULL,'2020-07-07 04:08:11',NULL,'0','0',0),
	(2018,'数据源修改','sys:datasource:edit',NULL,2016,NULL,1,'0','2',NULL,NULL,'2020-07-07 04:08:40',NULL,'0','0',0),
	(2019,'数据源删除','sys:datasource:delete',NULL,2016,NULL,1,'0','2',NULL,NULL,'2020-07-07 04:09:05',NULL,'0','0',0),
	(2020,'数据源导出','sys:datasource:export',NULL,2016,NULL,1,'0','2',NULL,NULL,'2020-07-07 04:09:25',NULL,'0','0',0),
	(2021,'代码生成',NULL,'/devops/generator',2015,'download',1,'0','1',NULL,NULL,'2020-07-09 07:08:50',NULL,'0','0',0),
	(2022,'代码生成','devops:gen',NULL,2021,NULL,1,'0','2',NULL,NULL,'2020-07-08 23:09:45','2020-07-13 14:35:14','0','0',0),
	(2023,'监控配置中心',NULL,'/devops/monitor',2015,'validCode',1,'0','1',NULL,NULL,'2020-07-10 20:23:07','2020-07-11 04:39:40','0','0',0),
	(2026,'客户端管理',NULL,'/system/client',1000,'iPhone',1,'0','1',NULL,NULL,'2020-07-13 22:47:20',NULL,'0','0',0),
	(2027,'新增客户端','sys:client:add',NULL,2026,NULL,1,'0','2',NULL,NULL,'2020-07-13 22:47:44',NULL,'0','0',0),
	(2028,'修改客户端','sys:client:edit',NULL,2026,NULL,1,'0','2',NULL,NULL,'2020-07-13 23:47:37',NULL,'0','0',0),
	(2029,'删除客户端','sys:client:delete',NULL,2026,NULL,1,'0','2',NULL,NULL,'2020-07-13 23:48:11',NULL,'0','0',0),
	(2030,'导出客户端','sys:client:export',NULL,2026,NULL,1,'0','2',NULL,NULL,'2020-07-13 23:48:28',NULL,'0','0',0),
	(2031,'启禁客户端','sys:client:status',NULL,2026,NULL,1,'0','2',NULL,NULL,'2020-07-13 23:49:22',NULL,'0','0',0);
ALTER TABLE `mate_sys_menu` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `mate_sys_role` WRITE;
ALTER TABLE `mate_sys_role` DISABLE KEYS;
INSERT INTO `mate_sys_role` (`id`, `role_name`, `role_code`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`, `tenant_id`) VALUES 
	(1,'管理员','admin','管理员组',NULL,NULL,'2020-06-28 15:02:16',NULL,'0',NULL),
	(2,'演示会员','demo2','演示会员组',NULL,NULL,'2020-06-28 07:02:36','2020-06-28 07:02:58','0',NULL);
ALTER TABLE `mate_sys_role` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `mate_sys_role_permission` WRITE;
ALTER TABLE `mate_sys_role_permission` DISABLE KEYS;
INSERT INTO `mate_sys_role_permission` (`id`, `menu_id`, `role_id`) VALUES 
	(977,1000,2),
	(978,1300,2),
	(979,1301,2),
	(980,1302,2),
	(981,1303,2),
	(982,2009,2),
	(1411,1000,1),
	(1412,1100,1),
	(1413,1101,1),
	(1414,1102,1),
	(1415,1103,1),
	(1416,1104,1),
	(1417,1105,1),
	(1418,1106,1),
	(1419,1200,1),
	(1420,1201,1),
	(1421,1202,1),
	(1422,1203,1),
	(1423,1204,1),
	(1424,1205,1),
	(1425,1300,1),
	(1426,1301,1),
	(1427,1302,1),
	(1428,1303,1),
	(1429,1304,1),
	(1430,1305,1),
	(1431,1306,1),
	(1432,1400,1),
	(1433,1401,1),
	(1434,1402,1),
	(1435,1403,1),
	(1436,1404,1),
	(1437,2026,1),
	(1438,2027,1),
	(1439,2028,1),
	(1440,2029,1),
	(1441,2030,1),
	(1442,2031,1),
	(1443,2015,1),
	(1444,2016,1),
	(1445,2017,1),
	(1446,2018,1),
	(1447,2019,1),
	(1448,2020,1),
	(1449,2021,1),
	(1450,2022,1),
	(1451,2023,1);
ALTER TABLE `mate_sys_role_permission` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `mate_sys_tenant` WRITE;
ALTER TABLE `mate_sys_tenant` DISABLE KEYS;
ALTER TABLE `mate_sys_tenant` ENABLE KEYS;
UNLOCK TABLES;


LOCK TABLES `mate_sys_user` WRITE;
ALTER TABLE `mate_sys_user` DISABLE KEYS;
INSERT INTO `mate_sys_user` (`id`, `tenant_id`, `account`, `password`, `name`, `real_name`, `avatar`, `email`, `telephone`, `birthday`, `sex`, `role_id`, `depart_id`, `status`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`) VALUES 
	(2,'100000','admin','{bcrypt}$2a$10$jddK.xwTn99XM9ggy64Zgu.eBK2GBiyk9RmtQEjg99S2F8otQ8ieq','admin','mate','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg','mate@mate.vip','18910001000',NULL,1,1,1,'0',NULL,NULL,'2020-07-02 15:29:12','2020-07-04 10:23:13','0'),
	(3,'100000','admin2','{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia','admin2','mate','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg','mate@mate.vip','18910001000',NULL,1,2,4,'0',NULL,NULL,'2020-06-16 20:05:43','2020-07-02 05:38:59','0'),
	(4,'100000','admin4','{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia','admin4','mate','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg','mate@mate.vip','18910001000',NULL,1,2,1,'0',NULL,NULL,'2020-06-17 04:05:43','2020-07-02 05:39:00','0'),
	(5,'100000','admin5','{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia','admin5','mate','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg','mate@mate.vip','18910001000',NULL,1,1,1,'0',NULL,NULL,'2020-06-17 04:05:43','2020-07-02 05:39:01','0'),
	(6,'100000','admin6','{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia','admin6','mate','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg','mate@mate.vip','18910001000',NULL,1,1,1,'0',NULL,NULL,'2020-06-17 12:05:43','2020-07-02 13:39:01','0'),
	(7,'100000','admin7','{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia','admin6','mate','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg','mate@mate.vip','18910001000',NULL,1,1,1,'0',NULL,NULL,'2020-06-16 12:05:43','2020-06-30 12:55:52','0'),
	(8,'100000','admin8','{bcrypt}$2a$10$6YPJ0dkaqh9x3zwTfidRBeT0U5vJ7Si7wXFx9Gc/K3pMxz8J18TrW','admin6','mate','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg','mate@mate.vip','18910001000',NULL,1,2,1,'0',NULL,NULL,'2020-06-16 20:05:43','2020-07-15 04:31:24','0'),
	(9,'100000','admin9','{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia','admin6','mate','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg','mate@mate.vip','18910001000',NULL,1,1,1,'1',NULL,NULL,'2020-06-16 20:05:43','2020-07-01 21:39:03','0'),
	(10,'100000','admin10','{bcrypt}$2a$10$A5cfRbFDCsOg.1UTlWyEZu8DJHSr9GnANXQMsRSIDAtZHuiQicr0K','admin6','mate','https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg','mate@mate.vip','18910001000',NULL,1,1,1,'1',NULL,NULL,'2020-06-15 12:05:43','2020-07-15 03:58:00','0'),
	(20,'100000','pp','{bcrypt}$2a$10$Yg737tR5BkroA0dJs8H2JureZhMPT3InoSJqGkjhvZ9JvqrUM.yR.','pp',NULL,NULL,NULL,NULL,'2020-06-29 08:00:00',0,1,1,'1',NULL,NULL,'2020-07-03 01:41:45','2020-07-07 11:41:34','0'),
	(22,'100000','pp1','{bcrypt}$2a$10$jddK.xwTn99XM9ggy64Zgu.eBK2GBiyk9RmtQEjg99S2F8otQ8ieq','pp1',NULL,'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg',NULL,'1899','2020-06-29 00:00:00',0,2,1,'0',NULL,NULL,'2020-07-02 17:57:13','2020-07-03 03:06:46','0');
ALTER TABLE `mate_sys_user` ENABLE KEYS;
UNLOCK TABLES;




SET FOREIGN_KEY_CHECKS = @PREVIOUS_FOREIGN_KEY_CHECKS;


