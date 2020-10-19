SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mate_sys_api
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_api`;
CREATE TABLE `mate_sys_api` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
      `code` varchar(255) NOT NULL COMMENT '接口编码',
      `name` varchar(100) DEFAULT NULL COMMENT '接口名称',
      `notes` varchar(200) DEFAULT NULL COMMENT '接口描述',
      `method` varchar(20) DEFAULT NULL COMMENT '请求方法',
      `class_name` varchar(255) DEFAULT NULL COMMENT '类名',
      `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
      `path` varchar(255) DEFAULT NULL COMMENT '请求路径',
      `content_type` varchar(100) DEFAULT NULL COMMENT '响应类型',
      `service_id` varchar(100) DEFAULT NULL COMMENT '服务ID',
      `status` char(1) CHARACTER SET utf8mb4 DEFAULT '1' COMMENT 'API状态:0:禁用 1:启用',
      `auth` char(1) CHARACTER SET utf8mb4 DEFAULT '0' COMMENT '是否认证:0:不认证 1:认证',
      `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
      `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
      `is_deleted` char(1) CHARACTER SET utf8mb4 DEFAULT '0' COMMENT '删除标识',
      `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='系统接口表';

-- ----------------------------
-- Table structure for mate_sys_route
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_route`;
CREATE TABLE `mate_sys_route` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
      `name` varchar(100) DEFAULT NULL COMMENT '服务名称',
      `path` varchar(255) DEFAULT NULL COMMENT '服务前缀',
      `url` varchar(255) DEFAULT NULL COMMENT '地址',
      `service_id` varchar(100) DEFAULT NULL COMMENT '服务编码',
      `status` char(1) CHARACTER SET utf8mb4 DEFAULT '1' COMMENT 'API状态:0:禁用 1:启用',
      `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
      `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
      `is_deleted` char(1) CHARACTER SET utf8mb4 DEFAULT '0' COMMENT '删除标识',
      `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='系统路由表';

LOCK TABLES `mate_sys_route` WRITE;
ALTER TABLE `mate_sys_route` DISABLE KEYS;
INSERT INTO `mate_sys_route` (`id`, `name`, `path`, `url`, `service_id`, `status`, `create_by`, `update_by`, `create_time`, `update_time`, `is_deleted`, `tenant_id`) VALUES
(1,'系统服务','/mate-system/**',NULL,'mate-system','1',NULL,NULL,'2020-10-18 22:59:02','2020-10-18 23:03:42','0',NULL),
(2,'认证服务','/mate-uaa/**',NULL,'mate-uaa','1',NULL,NULL,'2020-10-18 15:14:13','2020-10-18 23:14:24','0',NULL),
(3,'代码服务','/mate-code/**',NULL,'mate-code','1',NULL,NULL,'2020-10-19 09:21:25',NULL,'0',NULL),
(4,'组件服务','/mate-component/**',NULL,'mate-component','1',NULL,NULL,'2020-10-19 09:22:42',NULL,'0',NULL);
ALTER TABLE `mate_sys_route` ENABLE KEYS;
UNLOCK TABLES;

SET FOREIGN_KEY_CHECKS = 1;