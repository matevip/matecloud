/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云mysql
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           :
 Source Schema         : mate

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 23/02/2021 17:24:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',
  `package_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (11, 'data_source', '数据源表', NULL, NULL, 'DataSource', 'crud', 'com.ruoyi.system1', 'system', 'source', '数据源', 'ruoyi', '1', '/111', '{\"parentMenuId\":\"1101\"}', 'admin', '2021-01-26 17:48:22', '', '2021-01-27 15:51:30', NULL);
INSERT INTO `gen_table` VALUES (13, 'duben_doc', '读本文档', NULL, NULL, 'DubenDoc', 'crud', 'com.ruoyi.system', 'system', 'doc', '读本文档', 'ruoyi', '0', '/', NULL, 'admin', '2021-01-27 11:41:56', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (14, 'duben_set', '读本集', NULL, NULL, 'DubenSet', 'crud', 'com.ruoyi.system', 'system', 'set', '读本集', 'ruoyi', '0', '/', NULL, 'admin', '2021-01-27 11:42:34', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (15, 'sys_api', '系统接口表', 'data_source', 'id', 'SysApi', 'sub', 'com.jeeos.test', 'system', 'api', '系统接口', 'ruoyi', '0', '/', '{}', 'admin', '2021-01-27 11:43:29', '', '2021-02-18 16:47:52', NULL);
INSERT INTO `gen_table` VALUES (16, 'sys_client', '客户端表', NULL, NULL, 'SysClient', 'crud', 'com.ruoyi.system', 'system', 'client', '客户端', 'ruoyi', '0', '/', NULL, 'admin', '2021-01-27 11:43:45', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (17, 'sys_config', '配置表', NULL, NULL, 'SysConfig', 'crud', 'com.ruoyi.system', 'system', 'config', '配置', 'ruoyi', '0', '/', NULL, 'admin', '2021-01-27 11:43:46', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (18, 'sys_data_source', '数据源表', NULL, NULL, 'SysDataSource', 'crud', 'com.ruoyi.system', 'system', 'source', '数据源', 'ruoyi', '0', '/', NULL, 'admin', '2021-01-27 11:43:47', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (19, 'sys_log', '系统日志表', NULL, NULL, 'SysLog', 'crud', 'com.ruoyi.system', 'system', 'log', '系统日志', 'ruoyi', '0', '/', NULL, 'admin', '2021-01-27 11:44:10', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (20, 'sys_attachment', '附件表', NULL, NULL, 'SysAttachment', 'crud', 'com.ruoyi.system', 'system', 'attachment', '附件', 'ruoyi', '0', '/', NULL, 'admin', '2021-01-27 16:17:51', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (21, 'sys_dept', '组织机构表', NULL, NULL, 'SysDept', 'tree', 'com.jeeos.test', 'system', 'dept', '组织机构', 'ruoyi', '0', '/', '{\"treeCode\":\"id\",\"treeName\":\"name\",\"treeParentCode\":\"parent_id\"}', 'admin', '2021-01-27 16:17:52', '', '2021-02-19 15:59:08', NULL);
INSERT INTO `gen_table` VALUES (22, 'sys_role', '角色表', NULL, NULL, 'SysRole', 'crud', 'com.ruoyi.system', 'system', 'role', '角色', 'ruoyi', '0', '/', NULL, 'admin', '2021-01-27 16:18:04', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (23, 'sys_role_dept', '角色和部门关联表', NULL, NULL, 'SysRoleDept', 'crud', 'com.ruoyi.system', 'system', 'dept', '角色和部门关联', 'ruoyi', '0', '/', NULL, 'admin', '2021-01-27 16:18:05', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (24, 'sys_role_permission', '角色权限表', NULL, NULL, 'SysRolePermission', 'crud', 'com.ruoyi.system', 'system', 'permission', '角色权限', 'ruoyi', '0', '/', NULL, 'admin', '2021-01-27 16:18:05', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (25, 'sys_menu', '菜单权限表', NULL, NULL, 'SysMenu', 'tree', 'generator.system', 'system', 'menu', '菜单权限', 'ruoyi', '0', '/', '{\"treeCode\":\"id\",\"treeName\":\"name\",\"treeParentCode\":\"parent_id\"}', 'admin', '2021-02-02 15:45:48', '', '2021-02-02 15:52:38', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 320 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (129, '11', 'id', '自增id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '4', 1, 'admin', '2021-01-26 17:48:22', '', '2021-01-27 15:51:30');
INSERT INTO `gen_table_column` VALUES (130, '11', 'name', '名称', 'varchar(100)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2021-01-26 17:48:23', '', '2021-01-27 15:51:30');
INSERT INTO `gen_table_column` VALUES (131, '11', 'driver_class', '驱动类', 'varchar(100)', 'String', 'driverClass', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-01-26 17:48:23', '', '2021-01-27 15:51:30');
INSERT INTO `gen_table_column` VALUES (132, '11', 'url', '连接地址', 'varchar(500)', 'String', 'url', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 4, 'admin', '2021-01-26 17:48:23', '', '2021-01-27 15:51:30');
INSERT INTO `gen_table_column` VALUES (133, '11', 'username', '用户名', 'varchar(50)', 'String', 'username', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2021-01-26 17:48:23', '', '2021-01-27 15:51:30');
INSERT INTO `gen_table_column` VALUES (134, '11', 'password', '密码', 'varchar(50)', 'String', 'password', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2021-01-26 17:48:23', '', '2021-01-27 15:51:30');
INSERT INTO `gen_table_column` VALUES (135, '11', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 7, 'admin', '2021-01-26 17:48:23', '', '2021-01-27 15:51:30');
INSERT INTO `gen_table_column` VALUES (136, '11', 'status', '状态', 'tinyint(1)', 'Integer', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 8, 'admin', '2021-01-26 17:48:23', '', '2021-01-27 15:51:31');
INSERT INTO `gen_table_column` VALUES (137, '11', 'created_at', '创建时间', 'datetime', 'Date', 'createdAt', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 9, 'admin', '2021-01-26 17:48:23', '', '2021-01-27 15:51:31');
INSERT INTO `gen_table_column` VALUES (138, '11', 'updated_at', '更新时间', 'datetime', 'Date', 'updatedAt', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 10, 'admin', '2021-01-26 17:48:24', '', '2021-01-27 15:51:31');
INSERT INTO `gen_table_column` VALUES (139, '11', 'deleted_at', '删除时间', 'datetime', 'Date', 'deletedAt', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 11, 'admin', '2021-01-26 17:48:24', '', '2021-01-27 15:51:31');
INSERT INTO `gen_table_column` VALUES (140, '11', 'is_deleted', '逻辑删除', 'tinyint(1)', 'Integer', 'isDeleted', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2021-01-26 17:48:24', '', '2021-01-27 15:51:31');
INSERT INTO `gen_table_column` VALUES (153, '13', 'id', '文档id', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-27 11:41:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (154, '13', 'set_id', NULL, 'bigint(20)', 'Long', 'setId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-01-27 11:41:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (155, '13', 'parent_id', '父文档id', 'bigint(20)', 'Long', 'parentId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-01-27 11:41:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (156, '13', 'title', '文档标题', 'varchar(255)', 'String', 'title', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-01-27 11:41:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (157, '13', 'price', '文档价格', 'decimal(10,2)', 'BigDecimal', 'price', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-01-27 11:41:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (158, '13', 'origin_price', '原始价格', 'decimal(10,2)', 'BigDecimal', 'originPrice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2021-01-27 11:41:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (159, '13', 'doc_context', '文档内容', 'text', 'String', 'docContext', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 7, 'admin', '2021-01-27 11:41:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (160, '13', 'del_flag', '删除标识', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2021-01-27 11:41:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (161, '13', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2021-01-27 11:41:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (162, '13', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2021-01-27 11:41:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (163, '13', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2021-01-27 11:41:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (164, '13', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2021-01-27 11:41:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (165, '14', 'id', '文档集id', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-27 11:42:34', '', NULL);
INSERT INTO `gen_table_column` VALUES (166, '14', 'title', '标题', 'varchar(255)', 'String', 'title', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-01-27 11:42:34', '', NULL);
INSERT INTO `gen_table_column` VALUES (167, '14', 'sub_title', '子标题', 'varchar(255)', 'String', 'subTitle', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-01-27 11:42:34', '', NULL);
INSERT INTO `gen_table_column` VALUES (168, '14', 'price', '文档价格', 'decimal(10,2)', 'BigDecimal', 'price', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-01-27 11:42:34', '', NULL);
INSERT INTO `gen_table_column` VALUES (169, '14', 'origin_price', '原始价格', 'decimal(10,2)', 'BigDecimal', 'originPrice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-01-27 11:42:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (170, '14', 'image', '集照片', 'varchar(255)', 'String', 'image', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'imageUpload', '', 6, 'admin', '2021-01-27 11:42:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (171, '14', 'summary', '集摘要', 'varchar(255)', 'String', 'summary', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2021-01-27 11:42:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (172, '14', 'del_flag', '删除标识', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2021-01-27 11:42:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (173, '14', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2021-01-27 11:42:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (174, '14', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2021-01-27 11:42:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (175, '14', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2021-01-27 11:42:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (176, '14', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2021-01-27 11:42:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (177, '15', 'id', '主键id', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-27 11:43:29', '', '2021-02-18 16:47:52');
INSERT INTO `gen_table_column` VALUES (178, '15', 'code', '接口编码', 'varchar(255)', 'String', 'code', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-01-27 11:43:29', '', '2021-02-18 16:47:52');
INSERT INTO `gen_table_column` VALUES (179, '15', 'name', '接口名称', 'varchar(100)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2021-01-27 11:43:29', '', '2021-02-18 16:47:52');
INSERT INTO `gen_table_column` VALUES (180, '15', 'notes', '接口描述', 'varchar(200)', 'String', 'notes', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-01-27 11:43:29', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (181, '15', 'method', '请求方法', 'varchar(20)', 'String', 'method', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-01-27 11:43:29', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (182, '15', 'class_name', '类名', 'varchar(255)', 'String', 'className', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 6, 'admin', '2021-01-27 11:43:29', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (183, '15', 'method_name', '方法名', 'varchar(100)', 'String', 'methodName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 7, 'admin', '2021-01-27 11:43:29', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (184, '15', 'path', '请求路径', 'varchar(255)', 'String', 'path', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2021-01-27 11:43:29', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (185, '15', 'content_type', '响应类型', 'varchar(100)', 'String', 'contentType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 9, 'admin', '2021-01-27 11:43:30', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (186, '15', 'service_id', '服务ID', 'varchar(100)', 'String', 'serviceId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2021-01-27 11:43:30', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (187, '15', 'status', 'API状态:0:禁用 1:启用', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 11, 'admin', '2021-01-27 11:43:30', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (188, '15', 'auth', '是否认证:0:不认证 1:认证', 'char(1)', 'String', 'auth', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2021-01-27 11:43:30', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (189, '15', 'permission', '菜单权限', 'varchar(32)', 'String', 'permission', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2021-01-27 11:43:30', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (190, '15', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 14, 'admin', '2021-01-27 11:43:30', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (191, '15', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2021-01-27 11:43:30', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (192, '15', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2021-01-27 11:43:30', '', '2021-02-18 16:47:53');
INSERT INTO `gen_table_column` VALUES (193, '15', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 17, 'admin', '2021-01-27 11:43:30', '', '2021-02-18 16:47:54');
INSERT INTO `gen_table_column` VALUES (194, '15', 'is_deleted', '删除标识', 'char(1)', 'String', 'isDeleted', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2021-01-27 11:43:30', '', '2021-02-18 16:47:54');
INSERT INTO `gen_table_column` VALUES (195, '15', 'tenant_id', '租户ID', 'int(11)', 'Long', 'tenantId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2021-01-27 11:43:30', '', '2021-02-18 16:47:54');
INSERT INTO `gen_table_column` VALUES (196, '16', 'id', '主键', 'bigint(64)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-27 11:43:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (197, '16', 'client_id', '客户端id', 'varchar(48)', 'String', 'clientId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-01-27 11:43:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (198, '16', 'client_secret', '客户端密钥', 'varchar(256)', 'String', 'clientSecret', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-01-27 11:43:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (199, '16', 'resource_ids', '资源集合', 'varchar(256)', 'String', 'resourceIds', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-01-27 11:43:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (200, '16', 'scope', '授权范围', 'varchar(256)', 'String', 'scope', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-01-27 11:43:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (201, '16', 'authorized_grant_types', '授权类型', 'varchar(256)', 'String', 'authorizedGrantTypes', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2021-01-27 11:43:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (202, '16', 'web_server_redirect_uri', '回调地址', 'varchar(256)', 'String', 'webServerRedirectUri', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2021-01-27 11:43:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (203, '16', 'authorities', '权限', 'varchar(256)', 'String', 'authorities', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2021-01-27 11:43:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (204, '16', 'access_token_validity', '令牌过期秒数', 'int(11)', 'Long', 'accessTokenValidity', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2021-01-27 11:43:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (205, '16', 'refresh_token_validity', '刷新令牌过期秒数', 'int(11)', 'Long', 'refreshTokenValidity', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2021-01-27 11:43:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (206, '16', 'additional_information', '附加说明', 'varchar(4096)', 'String', 'additionalInformation', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 11, 'admin', '2021-01-27 11:43:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (207, '16', 'autoapprove', '自动授权', 'varchar(256)', 'String', 'autoapprove', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2021-01-27 11:43:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (208, '16', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2021-01-27 11:43:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (209, '16', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 14, 'admin', '2021-01-27 11:43:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (210, '16', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 15, 'admin', '2021-01-27 11:43:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (211, '16', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2021-01-27 11:43:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (212, '16', 'status', '状态', 'char(1)', 'String', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 17, 'admin', '2021-01-27 11:43:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (213, '16', 'is_deleted', '是否已删除', 'int(2)', 'Integer', 'isDeleted', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2021-01-27 11:43:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (214, '17', 'id', '主键', 'bigint(64)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-27 11:43:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (215, '17', 'parent_id', '父主键', 'bigint(64)', 'Long', 'parentId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-01-27 11:43:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (216, '17', 'code', '码', 'varchar(255)', 'String', 'code', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-01-27 11:43:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (217, '17', 'c_key', '值', 'varchar(255)', 'String', 'cKey', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-01-27 11:43:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (218, '17', 'value', '名称', 'varchar(255)', 'String', 'value', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-01-27 11:43:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (219, '17', 'sort', '排序', 'int(11)', 'Long', 'sort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2021-01-27 11:43:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (220, '17', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 7, 'admin', '2021-01-27 11:43:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (221, '17', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2021-01-27 11:43:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (222, '17', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2021-01-27 11:43:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (223, '17', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2021-01-27 11:43:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (224, '17', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2021-01-27 11:43:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (225, '17', 'tenant_id', NULL, 'int(11)', 'Long', 'tenantId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2021-01-27 11:43:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (226, '17', 'is_deleted', '是否已删除', 'int(2)', 'Integer', 'isDeleted', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2021-01-27 11:43:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (227, '18', 'id', '自增id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-27 11:43:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (228, '18', 'name', '名称', 'varchar(100)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2021-01-27 11:43:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (229, '18', 'db_type', '数据库类型', 'varchar(50)', 'String', 'dbType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2021-01-27 11:43:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (230, '18', 'driver_class', '驱动类型', 'varchar(100)', 'String', 'driverClass', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-01-27 11:43:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (231, '18', 'url', '连接地址', 'varchar(500)', 'String', 'url', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2021-01-27 11:43:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (232, '18', 'username', '用户名', 'varchar(50)', 'String', 'username', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 6, 'admin', '2021-01-27 11:43:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (233, '18', 'password', '密码', 'varchar(50)', 'String', 'password', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2021-01-27 11:43:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (234, '18', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 8, 'admin', '2021-01-27 11:43:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (235, '18', 'status', '状态', 'tinyint(1)', 'Integer', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 9, 'admin', '2021-01-27 11:43:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (236, '18', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2021-01-27 11:43:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (237, '18', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2021-01-27 11:43:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (238, '18', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2021-01-27 11:43:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (239, '18', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 13, 'admin', '2021-01-27 11:43:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (240, '18', 'is_deleted', '逻辑删除', 'tinyint(1)', 'Integer', 'isDeleted', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2021-01-27 11:43:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (241, '19', 'id', '主键id', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-27 11:44:10', '', NULL);
INSERT INTO `gen_table_column` VALUES (242, '19', 'type', '日志类型', 'char(1)', 'String', 'type', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 2, 'admin', '2021-01-27 11:44:10', '', NULL);
INSERT INTO `gen_table_column` VALUES (243, '19', 'trace_id', '跟踪ID', 'varchar(64)', 'String', 'traceId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-01-27 11:44:10', '', NULL);
INSERT INTO `gen_table_column` VALUES (244, '19', 'title', '日志标题', 'varchar(64)', 'String', 'title', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-01-27 11:44:10', '', NULL);
INSERT INTO `gen_table_column` VALUES (245, '19', 'operation', '操作内容', 'text', 'String', 'operation', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2021-01-27 11:44:10', '', NULL);
INSERT INTO `gen_table_column` VALUES (246, '19', 'method', '执行方法', 'varchar(64)', 'String', 'method', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2021-01-27 11:44:10', '', NULL);
INSERT INTO `gen_table_column` VALUES (247, '19', 'params', '参数', 'text', 'String', 'params', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 7, 'admin', '2021-01-27 11:44:10', '', NULL);
INSERT INTO `gen_table_column` VALUES (248, '19', 'url', '请求路径', 'varchar(128)', 'String', 'url', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2021-01-27 11:44:10', '', NULL);
INSERT INTO `gen_table_column` VALUES (249, '19', 'ip', 'ip地址', 'varchar(64)', 'String', 'ip', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2021-01-27 11:44:10', '', NULL);
INSERT INTO `gen_table_column` VALUES (250, '19', 'exception', NULL, 'text', 'String', 'exception', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 10, 'admin', '2021-01-27 11:44:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (251, '19', 'execute_time', '耗时', 'decimal(11,0)', 'Long', 'executeTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2021-01-27 11:44:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (252, '19', 'location', '地区', 'varchar(64)', 'String', 'location', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2021-01-27 11:44:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (253, '19', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2021-01-27 11:44:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (254, '19', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 14, 'admin', '2021-01-27 11:44:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (255, '19', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 15, 'admin', '2021-01-27 11:44:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (256, '19', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2021-01-27 11:44:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (257, '19', 'is_deleted', '删除标识', 'char(1)', 'String', 'isDeleted', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2021-01-27 11:44:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (258, '19', 'tenant_id', '租户ID', 'int(11)', 'Long', 'tenantId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2021-01-27 11:44:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (259, '20', 'id', '附件ID', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-27 16:17:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (260, '20', 'storage_id', '存储ID', 'bigint(20)', 'Long', 'storageId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-01-27 16:17:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (261, '20', 'attachment_group_id', '组ID', 'int(11)', 'Long', 'attachmentGroupId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-01-27 16:17:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (262, '20', 'name', '文件名称', 'varchar(128)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2021-01-27 16:17:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (263, '20', 'size', '文件大小', 'int(11)', 'Long', 'size', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-01-27 16:17:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (264, '20', 'url', '文件地址', 'varchar(2080)', 'String', 'url', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 6, 'admin', '2021-01-27 16:17:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (265, '20', 'file_name', '上传文件名', 'varchar(200)', 'String', 'fileName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 7, 'admin', '2021-01-27 16:17:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (266, '20', 'thumb_url', '缩略图地址', 'varchar(2080)', 'String', 'thumbUrl', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 8, 'admin', '2021-01-27 16:17:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (267, '20', 'type', '类型', 'tinyint(2)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 9, 'admin', '2021-01-27 16:17:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (268, '20', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2021-01-27 16:17:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (269, '20', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2021-01-27 16:17:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (270, '20', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2021-01-27 16:17:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (271, '20', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 13, 'admin', '2021-01-27 16:17:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (272, '20', 'is_deleted', '删除标识', 'char(1)', 'String', 'isDeleted', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2021-01-27 16:17:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (273, '20', 'is_recycle', '是否加入回收站 0.否|1.是', 'tinyint(1)', 'Integer', 'isRecycle', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2021-01-27 16:17:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (274, '21', 'id', '部门ID', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-27 16:17:52', '', '2021-02-19 15:59:08');
INSERT INTO `gen_table_column` VALUES (275, '21', 'name', '部门名称', 'varchar(50)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2021-01-27 16:17:52', '', '2021-02-19 15:59:08');
INSERT INTO `gen_table_column` VALUES (276, '21', 'sort', '排序', 'int(11)', 'Long', 'sort', '0', '0', '1', '1', '1', '1', '1', 'GT', 'input', 'status', 3, 'admin', '2021-01-27 16:17:52', '', '2021-02-19 15:59:08');
INSERT INTO `gen_table_column` VALUES (277, '21', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2021-01-27 16:17:52', '', '2021-02-19 15:59:08');
INSERT INTO `gen_table_column` VALUES (278, '21', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2021-01-27 16:17:52', '', '2021-02-19 15:59:08');
INSERT INTO `gen_table_column` VALUES (279, '21', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2021-01-27 16:17:52', '', '2021-02-19 15:59:08');
INSERT INTO `gen_table_column` VALUES (280, '21', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2021-01-27 16:17:52', '', '2021-02-19 15:59:08');
INSERT INTO `gen_table_column` VALUES (281, '21', 'is_deleted', '删除标识', 'char(1)', 'String', 'isDeleted', '0', '0', NULL, '1', '1', '1', '1', 'BETWEEN', 'input', '', 8, 'admin', '2021-01-27 16:17:52', '', '2021-02-19 15:59:08');
INSERT INTO `gen_table_column` VALUES (282, '21', 'parent_id', '上级ID', 'bigint(20)', 'Long', 'parentId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2021-01-27 16:17:53', '', '2021-02-19 15:59:08');
INSERT INTO `gen_table_column` VALUES (283, '21', 'parent_ids', '上级ID集合', 'varchar(255)', 'String', 'parentIds', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2021-01-27 16:17:53', '', '2021-02-19 15:59:08');
INSERT INTO `gen_table_column` VALUES (284, '21', 'tenant_id', '租户ID', 'bigint(20)', 'Long', 'tenantId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2021-01-27 16:17:53', '', '2021-02-19 15:59:08');
INSERT INTO `gen_table_column` VALUES (285, '22', 'id', '主键id', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-27 16:18:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (286, '22', 'role_name', '角色名称', 'varchar(64)', 'String', 'roleName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2021-01-27 16:18:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (287, '22', 'role_code', '角色编码', 'varchar(64)', 'String', 'roleCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-01-27 16:18:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (288, '22', 'data_scope', '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限 5:仅限自己插入的添加的数据）', 'char(1)', 'String', 'dataScope', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-01-27 16:18:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (289, '22', 'description', '描述', 'varchar(255)', 'String', 'description', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-01-27 16:18:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (290, '22', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2021-01-27 16:18:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (291, '22', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2021-01-27 16:18:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (292, '22', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2021-01-27 16:18:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (293, '22', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2021-01-27 16:18:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (294, '22', 'is_deleted', '删除标识', 'char(1)', 'String', 'isDeleted', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2021-01-27 16:18:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (295, '22', 'tenant_id', '租户ID', 'int(11)', 'Long', 'tenantId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2021-01-27 16:18:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (296, '23', 'role_id', '角色ID', 'bigint(20)', 'Long', 'roleId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-27 16:18:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (297, '23', 'dept_id', '部门ID', 'bigint(20)', 'Long', 'deptId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 2, 'admin', '2021-01-27 16:18:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (298, '24', 'id', '主键', 'bigint(64)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-01-27 16:18:06', '', NULL);
INSERT INTO `gen_table_column` VALUES (299, '24', 'menu_id', '菜单id', 'bigint(64)', 'Long', 'menuId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2021-01-27 16:18:06', '', NULL);
INSERT INTO `gen_table_column` VALUES (300, '24', 'role_id', '角色id', 'bigint(64)', 'Long', 'roleId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-01-27 16:18:06', '', NULL);
INSERT INTO `gen_table_column` VALUES (301, '25', 'id', '菜单ID', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2021-02-02 15:45:48', '', '2021-02-02 15:52:38');
INSERT INTO `gen_table_column` VALUES (302, '25', 'name', '菜单标题', 'varchar(32)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2021-02-02 15:45:48', '', '2021-02-02 15:52:38');
INSERT INTO `gen_table_column` VALUES (303, '25', 'permission', '菜单权限', 'varchar(32)', 'String', 'permission', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2021-02-02 15:45:48', '', '2021-02-02 15:52:38');
INSERT INTO `gen_table_column` VALUES (304, '25', 'path', '路径', 'varchar(128)', 'String', 'path', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2021-02-02 15:45:48', '', '2021-02-02 15:52:38');
INSERT INTO `gen_table_column` VALUES (305, '25', 'parent_id', '父菜单ID', 'bigint(20)', 'Long', 'parentId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2021-02-02 15:45:49', '', '2021-02-02 15:52:38');
INSERT INTO `gen_table_column` VALUES (306, '25', 'parent_ids', '上级ID集合', 'varchar(255)', 'String', 'parentIds', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2021-02-02 15:45:49', '', '2021-02-02 15:52:38');
INSERT INTO `gen_table_column` VALUES (307, '25', 'icon', '菜单图标', 'varchar(32)', 'String', 'icon', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2021-02-02 15:45:49', '', '2021-02-02 15:52:38');
INSERT INTO `gen_table_column` VALUES (308, '25', 'sort', '排序值', 'int(11)', 'Long', 'sort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2021-02-02 15:45:49', '', '2021-02-02 15:52:38');
INSERT INTO `gen_table_column` VALUES (309, '25', 'keep_alive', '是否缓存该页面: 1:是  0:不是', 'char(1)', 'String', 'keepAlive', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2021-02-02 15:45:49', '', '2021-02-02 15:52:38');
INSERT INTO `gen_table_column` VALUES (310, '25', 'type', '菜单类型', 'char(1)', 'String', 'type', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 10, 'admin', '2021-02-02 15:45:49', '', '2021-02-02 15:52:39');
INSERT INTO `gen_table_column` VALUES (311, '25', 'hidden', '是否显示', 'char(1)', 'String', 'hidden', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2021-02-02 15:45:49', '', '2021-02-02 15:52:39');
INSERT INTO `gen_table_column` VALUES (312, '25', 'target', '是否外链', 'char(1)', 'String', 'target', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2021-02-02 15:45:49', '', '2021-02-02 15:52:39');
INSERT INTO `gen_table_column` VALUES (313, '25', 'create_by', '创建人', 'varchar(32)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2021-02-02 15:45:49', '', '2021-02-02 15:52:39');
INSERT INTO `gen_table_column` VALUES (314, '25', 'update_by', '更新人', 'varchar(32)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 14, 'admin', '2021-02-02 15:45:49', '', '2021-02-02 15:52:39');
INSERT INTO `gen_table_column` VALUES (315, '25', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 15, 'admin', '2021-02-02 15:45:49', '', '2021-02-02 15:52:39');
INSERT INTO `gen_table_column` VALUES (316, '25', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2021-02-02 15:45:49', '', '2021-02-02 15:52:39');
INSERT INTO `gen_table_column` VALUES (317, '25', 'status', '状态', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 17, 'admin', '2021-02-02 15:45:50', '', '2021-02-02 15:52:39');
INSERT INTO `gen_table_column` VALUES (318, '25', 'is_deleted', '删除标识', 'char(1)', 'String', 'isDeleted', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2021-02-02 15:45:50', '', '2021-02-02 15:52:39');
INSERT INTO `gen_table_column` VALUES (319, '25', 'tenant_id', '租户ID', 'bigint(20) unsigned', 'Long', 'tenantId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2021-02-02 15:45:50', '', '2021-02-02 15:52:39');

SET FOREIGN_KEY_CHECKS = 1;
