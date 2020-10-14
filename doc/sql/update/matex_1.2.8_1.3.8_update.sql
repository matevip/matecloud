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

SET FOREIGN_KEY_CHECKS = 1;