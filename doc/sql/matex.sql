-- ----------------------------
-- 1、菜单权限表 mate_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_menu`;
CREATE TABLE `mate_sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
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
  `tenant_id` bigint(20) unsigned DEFAULT '0' COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2063 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';

-- ----------------------------
-- 初始化-菜单权限表数据
-- ----------------------------
INSERT INTO `mate_sys_menu` VALUES (1000, '系统管理', NULL, '/system', -1, 'imac', 1, '0', '0', '0', '0', NULL, NULL, '2020-06-17 14:21:45', '2020-08-11 03:41:11', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1100, '用户管理', NULL, '/system/user', 1000, 'user', 2, '0', '1', '0', '0', NULL, NULL, '2020-06-18 14:28:36', '2021-04-08 14:15:40', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1101, '用户新增', 'sys:user:add', '', 1100, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-17 14:32:51', '2020-07-03 08:51:48', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1102, '用户修改', 'sys:user:edit', NULL, 1100, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:27:40', '2020-07-03 08:51:50', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1103, '用户删除', 'sys:user:delete', NULL, 1100, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:27:56', '2020-07-03 08:51:52', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1104, '用户启用', 'sys:user:enable', NULL, 1100, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 08:49:47', '2020-07-03 08:55:39', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1105, '用户禁用', 'sys:user:disable', NULL, 1100, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 08:50:16', '2020-07-03 08:55:40', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1106, '用户导出', 'sys:user:export', NULL, 1100, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 08:50:58', '2020-07-03 08:55:42', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1200, '角色管理', NULL, '/system/role', 1000, 'repair', 4, '0', '1', '0', '0', NULL, NULL, '2020-06-19 16:36:01', '2021-04-08 14:16:36', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1201, '角色新增', 'sys:role:add', NULL, 1200, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:37:12', '2020-07-03 14:00:56', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1202, '角色修改', 'sys:role:edit', NULL, 1200, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:38:23', '2020-07-03 14:01:06', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1203, '角色删除', 'sys:role:delete', NULL, 1200, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:38:53', '2020-07-03 14:01:14', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1204, '角色导出', 'sys:role:export', NULL, 1200, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 14:02:37', '2020-07-03 14:02:50', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1205, '角色权限', 'sys:role:perm', NULL, 1200, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 14:03:32', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1300, '菜单管理', NULL, '/system/menu', 1000, 'tree', 1, '0', '1', '0', '0', NULL, NULL, '2020-06-19 16:39:07', '2021-04-08 14:16:05', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1301, '菜单新增', 'sys:menu:add', NULL, 1300, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:39:48', '2020-07-03 14:11:59', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1302, '菜单修改', 'sys:menu:edit', NULL, 1300, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:40:21', '2020-07-03 14:12:15', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1303, '菜单删除', 'sys:menu:delete', NULL, 1300, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-20 00:40:42', '2020-07-03 14:12:23', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1304, '菜单启用', 'sys:menu:enable', NULL, 1300, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 14:12:59', '2020-07-03 14:13:14', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1305, '菜单禁用', 'sys:menu:disable', NULL, 1300, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 14:13:34', '2020-07-03 14:13:57', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1306, '菜单导出', 'sys:menu:export', NULL, 1300, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 14:14:32', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1400, '部门管理', NULL, '/system/depart', 1000, 'table2', 3, '0', '1', '0', '0', NULL, NULL, '2020-06-26 22:52:41', '2021-04-08 14:16:25', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1401, '部门新增', 'sys:depart:add', NULL, 1400, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-27 14:53:37', '2020-07-03 14:26:12', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1402, '部门修改', 'sys:depart:edit', NULL, 1400, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-27 14:54:47', '2020-07-03 14:26:14', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1403, '部门删除', 'sys:depart:delete', NULL, 1400, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-06-27 14:55:15', '2020-07-03 14:26:17', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1404, '部门导出', 'sys:depart:export', NULL, 1400, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-03 14:27:26', '2020-07-03 14:27:45', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2015, '开发运维', NULL, '/devops', -1, 'settings', 3, '0', '0', '0', '0', NULL, NULL, '2020-07-05 11:20:31', '2020-10-19 22:21:49', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2016, '数据源管理', '', '/devops/datasource', 2015, 'table', 1, '0', '1', '0', '0', NULL, NULL, '2020-07-06 19:21:58', '2020-07-09 06:50:01', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2017, '数据源新增', 'sys:datasource:add', NULL, 2016, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-07 04:08:11', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2018, '数据源修改', 'sys:datasource:edit', NULL, 2016, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-07 04:08:40', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2019, '数据源删除', 'sys:datasource:delete', NULL, 2016, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-07 04:09:05', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2020, '数据源导出', 'sys:datasource:export', NULL, 2016, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-07 04:09:25', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2021, '代码生成', NULL, '/devops/generator', 2015, 'download', 1, '0', '1', '0', '0', NULL, NULL, '2020-07-09 07:08:50', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2022, '代码生成', 'devops:gen', NULL, 2021, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-08 23:09:45', '2020-07-13 14:35:14', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2023, '监控配置中心', NULL, '/devops/monitor', 2015, 'validCode', 1, '0', '1', '0', '0', NULL, NULL, '2020-07-10 20:23:07', '2020-07-11 04:39:40', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2026, '客户端管理', NULL, '/system/client', 1000, 'iPhone', 7, '0', '1', '0', '0', NULL, NULL, '2020-07-13 22:47:20', '2021-04-08 14:17:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2027, '新增客户端', 'sys:client:add', NULL, 2026, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-13 22:47:44', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2028, '修改客户端', 'sys:client:edit', NULL, 2026, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-13 23:47:37', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2029, '删除客户端', 'sys:client:delete', NULL, 2026, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-13 23:48:11', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2030, '导出客户端', 'sys:client:export', NULL, 2026, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-13 23:48:28', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2031, '启禁客户端', 'sys:client:status', NULL, 2026, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-13 23:49:22', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2032, '操作日志', NULL, '/system/log', 1000, 'comment', 8, '0', '1', '0', '0', NULL, NULL, '2020-07-15 05:11:09', '2021-04-08 14:26:14', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2033, '详细日志', 'sys:log:detail', NULL, 2032, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-16 04:05:48', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2034, '日志删除', 'sys:log:delete', NULL, 2032, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-16 04:06:16', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2035, '字典管理', NULL, '/system/dict', 1000, 'add', 6, '0', '1', '0', '0', NULL, NULL, '2020-07-17 09:29:31', '2021-04-08 14:17:17', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2036, '新增字典', 'sys:dict:add', NULL, 2035, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-20 02:48:01', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2037, '修改字典', 'sys:dict:edit', NULL, 2035, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-20 02:48:20', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2038, '删除字典', 'sys:dict:delete', NULL, 2035, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-07-20 02:48:39', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2039, '组件管理', NULL, '/system/component', 1000, 'nested', 5, '0', '1', '0', '0', NULL, NULL, '2020-08-08 05:35:05', '2021-04-08 14:17:09', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2040, '内容管理', NULL, '/content', -1, 'content', 4, '0', '0', '0', '0', NULL, NULL, '2020-08-09 00:21:42', '2020-08-29 03:12:22', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2041, '文件管理', NULL, '/content/attachment', 2040, 'folder', 1, '0', '1', '0', '0', NULL, NULL, '2020-08-09 00:27:06', '2020-08-10 03:31:49', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2042, '修改组件', 'sys:comp:edit', NULL, 2039, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-10 00:42:28', '2020-08-10 08:42:57', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2043, '上传文件', 'sys:attach:add', NULL, 2041, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-10 08:43:52', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2044, '删除文件', 'sys:attach:delete', NULL, 2041, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-10 08:44:29', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2045, '网关中心', NULL, '/gateway', -1, 'gateway', 2, '0', '0', '0', '0', NULL, NULL, '2020-08-28 19:12:00', '2020-10-19 22:21:52', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2047, '黑名单管理', NULL, '/gateway/blacklist', 2045, 'blacklist', 3, '0', '1', '0', '0', NULL, NULL, '2020-08-29 03:15:34', '2020-10-19 22:21:40', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2048, '新增黑名单', 'gw:bl:add', NULL, 2047, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-29 09:38:52', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2049, '修改黑名单', 'gw:bl:edit', NULL, 2047, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-29 09:39:27', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2050, '删除黑名单', 'gw:bl:del', NULL, 2047, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-29 09:39:51', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2051, '黑名单状态', 'gw:bl:status', NULL, 2047, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-08-29 09:44:20', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2053, '文档管理', NULL, '/content/doc', 2040, 'comment', 1, '0', '1', '0', '0', NULL, NULL, '2020-09-07 12:16:38', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2055, 'API管理', NULL, '/gateway/api', 2045, 'discovery', 2, '0', '1', '0', '0', NULL, NULL, '2020-10-14 14:00:06', '2020-10-17 12:53:38', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2056, '微服务管理', NULL, '/gateway/route', 2045, 'share3', 1, '0', '1', '0', '0', NULL, NULL, '2020-10-17 12:53:27', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2057, '同步API', 'gw:api:sync', NULL, 2055, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-10-17 14:16:06', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2058, '删除API', 'gw:api:del', NULL, 2055, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-10-17 14:17:25', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2059, '修改API', 'gw:api:edit', NULL, 2055, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-10-17 14:17:58', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2060, '新增微服务', 'gw:route:add', NULL, 2056, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-10-19 05:19:45', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2061, '修改微服务', 'gw:route:edit', NULL, 2056, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-10-19 05:20:49', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2062, '删除微服务', 'gw:route:del', NULL, 2056, NULL, 1, '0', '2', '0', '0', NULL, NULL, '2020-10-19 05:21:03', NULL, '0', '0', 0);

-- ----------------------------
-- 2、用户表 mate_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_user`;
CREATE TABLE `mate_sys_user` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
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
  `is_deleted` char(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- 初始化-用户表数据
-- ----------------------------
INSERT INTO `mate_sys_user` VALUES (2, '100000', 'admin', '{bcrypt}$2a$10$IhNoDpkdJ1VzbnfX1pH35.S25n2tHaxU4hSltf7An.wdiXAsYe2Jm', 'admin', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18810001000', NULL, 1, 1, 1, '0', NULL, NULL, '2020-07-02 07:29:12', '2020-08-09 17:29:15', '0');
INSERT INTO `mate_sys_user` VALUES (3, '100000', 'admin2', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin2', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 2, 4, '0', NULL, NULL, '2020-06-16 20:05:43', '2020-07-02 05:38:59', '0');
INSERT INTO `mate_sys_user` VALUES (4, '100000', 'admin4', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin4', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 2, 1, '0', NULL, NULL, '2020-06-17 04:05:43', '2020-07-02 05:39:00', '0');
INSERT INTO `mate_sys_user` VALUES (5, '100000', 'admin5', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin5', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '0', NULL, NULL, '2020-06-17 04:05:43', '2020-07-02 05:39:01', '0');
INSERT INTO `mate_sys_user` VALUES (6, '100000', 'admin6', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '0', NULL, NULL, '2020-06-17 12:05:43', '2020-07-02 13:39:01', '0');
INSERT INTO `mate_sys_user` VALUES (7, '100000', 'admin7', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '0', NULL, NULL, '2020-06-16 12:05:43', '2020-06-30 12:55:52', '0');
INSERT INTO `mate_sys_user` VALUES (8, '100000', 'admin8', '{bcrypt}$2a$10$6YPJ0dkaqh9x3zwTfidRBeT0U5vJ7Si7wXFx9Gc/K3pMxz8J18TrW', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 2, 1, '0', NULL, NULL, '2020-06-16 20:05:43', '2020-07-15 04:31:24', '0');
INSERT INTO `mate_sys_user` VALUES (9, '100000', 'admin9', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '1', NULL, NULL, '2020-06-16 20:05:43', '2020-07-01 21:39:03', '0');
INSERT INTO `mate_sys_user` VALUES (10, '100000', 'admin10', '{bcrypt}$2a$10$A5cfRbFDCsOg.1UTlWyEZu8DJHSr9GnANXQMsRSIDAtZHuiQicr0K', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '1', NULL, NULL, '2020-06-15 12:05:43', '2020-07-15 03:58:00', '0');
INSERT INTO `mate_sys_user` VALUES (22, '100000', 'pp1', '{bcrypt}$2a$10$jddK.xwTn99XM9ggy64Zgu.eBK2GBiyk9RmtQEjg99S2F8otQ8ieq', 'pp1', NULL, 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', NULL, '1899', '2020-06-26 16:00:00', 0, 2, 1, '0', NULL, NULL, '2020-06-30 09:57:13', '2020-06-30 19:06:46', '0');

-- ----------------------------
-- 3、组织机构表 mate_sys_depart
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_depart`;
CREATE TABLE `mate_sys_depart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '上级ID',
  `tenant_id` bigint(20) DEFAULT '0' COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='组织机构表';

-- ----------------------------
-- 初始化-组织机构表数据
-- ----------------------------
INSERT INTO `mate_sys_depart` VALUES (1, '开发部', 0, NULL, NULL, '2020-06-27 15:30:50', '2020-07-01 20:49:08', '0', -1, 0);
INSERT INTO `mate_sys_depart` VALUES (2, '开发分部', 0, NULL, NULL, '2020-06-29 11:14:37', NULL, '0', 1, 0);
INSERT INTO `mate_sys_depart` VALUES (3, '开发二部', 1, NULL, NULL, '2020-06-29 15:54:27', NULL, '0', 1, 0);
INSERT INTO `mate_sys_depart` VALUES (4, '产品部', 1, NULL, NULL, '2020-06-29 07:58:54', '2020-08-17 06:53:59', '0', -1, 0);
INSERT INTO `mate_sys_depart` VALUES (5, '产品一部', 1, NULL, NULL, '2020-06-29 15:59:14', NULL, '0', 4, 0);

-- ----------------------------
-- 4、角色表 for mate_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_role`;
CREATE TABLE `mate_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(64) DEFAULT NULL COMMENT '角色编码',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- 初始化-角色表数据
-- ----------------------------
INSERT INTO `mate_sys_role` VALUES (1, '管理员', 'admin', '管理员组', NULL, NULL, '2020-06-28 15:02:16', NULL, '0', NULL);
INSERT INTO `mate_sys_role` VALUES (2, '演示会员', 'demo2', '演示会员组', NULL, NULL, '2020-06-28 07:02:36', '2020-06-28 07:02:58', '0', NULL);

-- ----------------------------
-- 5、字典表 mate_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_dict`;
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

-- ----------------------------
-- 初始化-字典表数据
-- ----------------------------
INSERT INTO `mate_sys_dict` VALUES (1, 0, 'status', '-1', '状态', 1, '', NULL, NULL, '2020-07-01 09:59:15', '2020-07-01 10:02:00', 0);
INSERT INTO `mate_sys_dict` VALUES (2, 1, 'status', '0', '启用', 1, NULL, NULL, NULL, '2020-07-01 10:02:23', '2020-07-01 10:02:59', 0);
INSERT INTO `mate_sys_dict` VALUES (3, 1, 'status', '1', '禁用', 2, NULL, NULL, NULL, '2020-07-01 10:02:34', '2020-07-01 10:02:59', 0);
INSERT INTO `mate_sys_dict` VALUES (4, 0, 'dbtype', '-1', '数据库类型', 1, NULL, NULL, NULL, '2020-07-11 08:47:02', NULL, 0);
INSERT INTO `mate_sys_dict` VALUES (5, 4, 'dbtype', 'mysql', 'com.mysql.cj.jdbc.Driver', 1, NULL, NULL, NULL, '2020-07-11 08:47:44', '2020-07-11 08:53:11', 0);
INSERT INTO `mate_sys_dict` VALUES (6, 4, 'dbtype', 'oracle', 'oracle.jdbc.OracleDriver', 2, NULL, NULL, NULL, '2020-07-11 08:48:00', '2020-07-11 08:54:05', 0);
INSERT INTO `mate_sys_dict` VALUES (7, 4, 'dbtype', 'oracle12c', 'oracle.jdbc.OracleDriver', 3, NULL, NULL, NULL, '2020-07-11 08:49:10', '2020-07-11 08:54:12', 0);
INSERT INTO `mate_sys_dict` VALUES (24, 0, 'ok', '-1', '确认', NULL, NULL, NULL, NULL, '2020-07-19 13:31:16', '2020-07-19 21:31:28', 0);
INSERT INTO `mate_sys_dict` VALUES (25, 24, 'ok', 'yes', '是', NULL, NULL, NULL, NULL, '2020-07-19 21:31:40', '2020-07-20 05:32:12', 0);
INSERT INTO `mate_sys_dict` VALUES (26, 24, 'ok', 'no', '否', NULL, NULL, NULL, NULL, '2020-07-20 05:32:06', NULL, 0);

-- ----------------------------
-- 6、客户端表 mate_sys_client
-- ----------------------------
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
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='客户端表';

-- ----------------------------
-- 初始化-客户端表数据
-- ----------------------------
INSERT INTO `mate_sys_client` VALUES (1, 'mate', 'mate_secret', NULL, 'all', 'refresh_token,password,authorization_code,captcha,sms,social', 'http://localhost:10001', NULL, 3600, 3600, NULL, NULL, NULL, NULL, '2020-07-12 14:49:23', '2020-07-28 04:22:54', '0', 0);

-- ----------------------------
-- 7、配置表 mate_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_config`;
CREATE TABLE `mate_sys_config` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
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
  `is_deleted` int(2) DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='配置表';

-- ----------------------------
-- 初始化-配置表数据
-- ----------------------------
INSERT INTO `mate_sys_config` VALUES (1, 0, 'oss', 'default', 'qiniuoss', 0, '默认OSS配置', NULL, NULL, '2020-08-08 01:44:31', '2020-12-16 09:37:21', 1, 0);
INSERT INTO `mate_sys_config` VALUES (2, 1, 'alioss', 'endpoint', 'oss-cn-beijing.aliyuncs.com', 1, '对象存储服务的URL', NULL, NULL, '2020-08-08 01:46:10', '2020-08-09 16:14:15', 1, 0);
INSERT INTO `mate_sys_config` VALUES (3, 1, 'alioss', 'customDomain', 'mall-zaitong.oss-cn-beijing.aliyuncs.com', 2, '自定义域名', NULL, NULL, '2020-08-08 01:46:32', '2020-08-09 16:14:08', 1, 0);
INSERT INTO `mate_sys_config` VALUES (4, 1, 'alioss', 'pathStyleAccess', 'false', 3, 'pathStyleAccess', NULL, NULL, '2020-08-08 01:47:21', '2020-08-08 01:47:35', 1, 0);
INSERT INTO `mate_sys_config` VALUES (5, 1, 'alioss', 'accessKey', 'LTA******rzjrV', 4, 'Access Key', NULL, NULL, '2020-08-08 01:47:40', '2020-08-09 07:53:48', 1, 0);
INSERT INTO `mate_sys_config` VALUES (6, 1, 'alioss', 'secretKey', '9H6Bxg**************bfNoy4E', 5, 'Access Secret', NULL, NULL, '2020-08-08 01:53:13', '2020-08-10 01:31:53', 1, 0);
INSERT INTO `mate_sys_config` VALUES (7, 1, 'alioss', 'bucketName', 'm********g', 6, '空间名', NULL, NULL, '2020-08-08 01:53:14', '2020-08-09 16:13:15', 1, 0);
INSERT INTO `mate_sys_config` VALUES (8, 1, 'qiniuoss', 'endpoint', 's3-cn-south-1.qiniucs.com', 1, '对象存储服务的URL', NULL, NULL, '2020-08-08 01:46:10', '2020-08-10 01:33:31', 1, 0);
INSERT INTO `mate_sys_config` VALUES (9, 1, 'qiniuoss', 'customDomain', 'cd**********com8878556757657', 2, '自定义域名', NULL, NULL, '2020-08-08 01:46:32', '2020-11-15 20:02:32', 1, 0);
INSERT INTO `mate_sys_config` VALUES (10, 1, 'qiniuoss', 'pathStyleAccess', 'false', 3, 'pathStyleAccess', NULL, NULL, '2020-08-08 01:47:21', '2020-08-08 01:47:35', 1, 0);
INSERT INTO `mate_sys_config` VALUES (11, 1, 'qiniuoss', 'accessKey', 'pj2M-4k_*********************dQpjb1L', 4, 'Access Key', NULL, NULL, '2020-08-08 01:47:40', '2020-08-10 01:33:31', 1, 0);
INSERT INTO `mate_sys_config` VALUES (12, 1, 'qiniuoss', 'secretKey', 'Dx17O-dbR*******************Mxlc4bb', 5, 'Access Secret', NULL, NULL, '2020-08-08 01:53:13', '2020-08-10 01:33:31', 1, 0);
INSERT INTO `mate_sys_config` VALUES (13, 1, 'qiniuoss', 'bucketName', 'ckjia', 6, '空间名', NULL, NULL, '2020-08-08 01:53:14', '2020-08-10 01:33:31', 1, 0);
INSERT INTO `mate_sys_config` VALUES (14, 1, 'miniooss', 'endpoint', '66666', 1, '对象存储服务的URL', NULL, NULL, '2020-08-08 01:46:10', '2020-08-09 02:03:52', 1, 0);
INSERT INTO `mate_sys_config` VALUES (15, 1, 'miniooss', 'customDomain', '2222', 2, '自定义域名', NULL, NULL, '2020-08-08 01:46:32', '2020-08-08 16:55:54', 1, 0);
INSERT INTO `mate_sys_config` VALUES (16, 1, 'miniooss', 'pathStyleAccess', 'false', 3, 'pathStyleAccess', NULL, NULL, '2020-08-08 01:47:21', '2020-08-08 01:47:35', 1, 0);
INSERT INTO `mate_sys_config` VALUES (17, 1, 'miniooss', 'accessKey', '3333', 4, 'Access Key', NULL, NULL, '2020-08-08 01:47:40', '2020-08-08 16:55:58', 1, 0);
INSERT INTO `mate_sys_config` VALUES (18, 1, 'miniooss', 'secretKey', '4444', 5, 'Access Secret', NULL, NULL, '2020-08-08 01:53:13', '2020-08-08 16:56:02', 1, 0);
INSERT INTO `mate_sys_config` VALUES (19, 1, 'miniooss', 'bucketName', '5555', 6, '空间名', NULL, NULL, '2020-08-08 01:53:14', '2020-08-08 16:56:06', 1, 0);

-- ----------------------------
-- 8、系统日志表 mate_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_log`;
CREATE TABLE `mate_sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
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
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';

-- ----------------------------
-- 初始化-系统日志表数据
-- ----------------------------

-- ----------------------------
-- 9、角色和部门关联表 mate_sys_role_depart
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_role_depart`;
CREATE TABLE `mate_sys_role_depart` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `depart_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='角色和部门关联表';

-- ----------------------------
-- 初始化-角色和部门关联表数据
-- ----------------------------

-- ----------------------------
-- 10、角色和部门关联表 mate_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_role_permission`;
CREATE TABLE `mate_sys_role_permission` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint(64) DEFAULT NULL COMMENT '菜单id',
  `role_id` bigint(64) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2173 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- 初始化-角色权限表数据
-- ----------------------------
INSERT INTO `mate_sys_role_permission` VALUES (977, 1000, 2);
INSERT INTO `mate_sys_role_permission` VALUES (978, 1300, 2);
INSERT INTO `mate_sys_role_permission` VALUES (979, 1301, 2);
INSERT INTO `mate_sys_role_permission` VALUES (980, 1302, 2);
INSERT INTO `mate_sys_role_permission` VALUES (981, 1303, 2);
INSERT INTO `mate_sys_role_permission` VALUES (982, 2009, 2);
INSERT INTO `mate_sys_role_permission` VALUES (2103, 1000, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2104, 1300, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2105, 1301, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2106, 1302, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2107, 1303, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2108, 1304, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2109, 1305, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2110, 1306, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2111, 1100, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2112, 1101, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2113, 1102, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2114, 1103, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2115, 1104, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2116, 1105, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2117, 1106, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2118, 1400, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2119, 1401, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2120, 1402, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2121, 1403, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2122, 1404, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2123, 1200, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2124, 1201, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2125, 1202, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2126, 1203, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2127, 1204, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2128, 1205, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2129, 2026, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2130, 2027, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2131, 2028, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2132, 2029, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2133, 2030, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2134, 2031, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2135, 2035, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2136, 2036, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2137, 2037, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2138, 2038, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2139, 2039, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2140, 2042, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2141, 2032, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2142, 2033, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2143, 2034, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2144, 2045, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2145, 2056, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2146, 2060, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2147, 2061, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2148, 2062, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2149, 2055, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2150, 2057, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2151, 2058, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2152, 2059, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2153, 2047, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2154, 2048, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2155, 2049, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2156, 2050, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2157, 2051, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2158, 2015, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2159, 2016, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2160, 2017, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2161, 2018, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2162, 2019, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2163, 2020, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2164, 2021, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2165, 2022, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2166, 2023, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2167, 2040, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2168, 2041, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2169, 2043, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2170, 2044, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2171, NULL, NULL);
INSERT INTO `mate_sys_role_permission` VALUES (2172, 2053, 1);

-- ----------------------------
-- 11、系统接口表 mate_sys_api
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
  `status` char(1) DEFAULT '1' COMMENT 'API状态:0:禁用 1:启用',
  `auth` char(1) DEFAULT '0' COMMENT '是否认证:0:不认证 1:认证',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) DEFAULT '0' COMMENT '删除标识',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COMMENT='系统接口表';


-- ----------------------------
-- 初始化-系统接口表数据
-- ----------------------------
INSERT INTO `mate_sys_api` VALUES (1, '4d1d7152e5ba14f3b17b51aa6f5c3fe8', '数据源项列表', '数据源项列表', 'GET', 'vip.mate.code.controller.SysDataSourceController', 'optionList', '/data-source/option-list', '', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (2, '0cd169937833856e39b1781b4576aa0c', '数据源删除', '数据源删除', 'POST', 'vip.mate.code.controller.SysDataSourceController', 'del', '/data-source/del', '', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (3, 'af13b998a6fd5d4b4890502ea09e757b', '数据源分页', '数据源分页', 'GET', 'vip.mate.code.controller.SysDataSourceController', 'page', '/data-source/page', '', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (4, 'ff77ff46d88dc18d1db143e507a8b9ec', '数据源信息', '数据源信息,根据ID查询', 'GET', 'vip.mate.code.controller.SysDataSourceController', 'get', '/data-source/get', '', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (5, 'f0971e6df527eede4f0cf2a9a6554a4b', '数据源设置', '数据源设置,支持新增或修改', 'POST', 'vip.mate.code.controller.SysDataSourceController', 'set', '/data-source/set', 'application/json;charset=UTF-8', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (6, '6a3bbd4c88d34f7999494f4d1cdb68bd', '数据库表信息', '数据库表信息', 'POST', 'vip.mate.code.controller.SysCodeController', 'tableList', '/code/table-list', '', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (7, 'eaed145ea5bc1a75389e29eb8862f46d', '代码生成', '代码生成', 'POST', 'vip.mate.code.controller.SysCodeController', 'execute', '/code/generator-code', '', 'mate-code', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (8, '1c01329d9081dd24f43521a7355c3131', '用户登录Post', '用户登录Post', 'POST', 'vip.mate.uaa.controller.OauthController', 'postAccessToken', '/oauth/token', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', '2020-10-20 09:12:55', '0', NULL);
INSERT INTO `mate_sys_api` VALUES (9, 'aba8bdcbcb92e87e8dd3b1eca4581eb1', '用户信息', '用户信息', 'GET', 'vip.mate.uaa.controller.AuthController', 'getUser', '/auth/get/user', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (10, '6ae701fa710ee54285a2a874943929de', '第三方登录回调', '第三方登录回调', 'GET', 'vip.mate.uaa.controller.AuthController', 'callback', '/auth/callback/{oauthType}', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (11, '44a84ebcca70e4d399cc7329ae53a294', '第三方登录', '第三方登录', 'POST', 'vip.mate.uaa.controller.AuthController', 'login', '/auth/login/{oauthType}', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (12, 'c3e89549d2cd6a550673a5f0f0999588', '退出登录', '退出登录', 'POST', 'vip.mate.uaa.controller.AuthController', 'logout', '/auth/logout', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (13, 'fd26ebbb42eeafa6bc1a5551153a2290', '验证码获取', '验证码获取', 'GET', 'vip.mate.uaa.controller.AuthController', 'authCode', '/auth/code', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (14, '240d2897eb2d2ed4d15ddfdd939232b6', '手机验证码下发', '手机验证码下发', 'GET', 'vip.mate.uaa.controller.AuthController', 'smsCode', '/auth/sms-code', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (15, '0f31df9a8db1df105508afa93b074930', '登录类型', '登录类型', 'GET', 'vip.mate.uaa.controller.AuthController', 'loginType', '/auth/list', '', 'mate-uaa', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (16, '87f2b83c8a49fbe8166e78a49353ba7f', '获取菜单列表', '根据角色ID获取菜单列表', 'GET', 'vip.mate.system.feign.SysRolePermissionProvider', 'getMenuIdByRoleId', '/provider/role-permission/permission', '', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (17, 'cea9d573e219b4c15dcac02c6c84acae', '用户ID查询', '用户ID查询', 'GET', 'vip.mate.system.feign.SysUserProvider', 'getUserById', '/provider/user/id', '', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (18, 'da6b14e72532f46b12fc67b45dad208f', '用户用户名查询', '用户用户名查询', 'GET', 'vip.mate.system.feign.SysUserProvider', 'getUserByUserName', '/provider/user/username', '', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (19, 'd2affc1b35b2baec49813f0b20743d9f', '用户手机号查询', '用户手机号查询', 'GET', 'vip.mate.system.feign.SysUserProvider', 'getUserByMobile', '/provider/user/mobile', '', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (20, 'ce1be04ba688df4c1ad7d156116f96c1', '字典列表', '根据code获取字典列表', 'GET', 'vip.mate.system.feign.SysDictProvider', 'getList', '/provider/dict/list', '', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (21, '229054689b7b9bff20a5128b36c5d9bf', '字典值', '根据code和dictKey获取值', 'GET', 'vip.mate.system.feign.SysDictProvider', 'getValue', '/provider/dict/value', '', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (22, 'cf5af93cd67ee6d74fcdae3216990a71', '日志设置', '日志设置', 'POST', 'vip.mate.system.feign.SysLogProvider', 'set', '/provider/log/set', 'application/json;charset=UTF-8', 'mate-system', '1', '0', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (23, '61970f669724bb3e016e99846621dcaf', 'API状态', '状态包括：启用、禁用', 'POST', 'vip.mate.system.controller.SysApiController', 'setStatus', '/api/set-status', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (24, '558e6b243aed6ea2cb7fa49e5f2cd83d', 'API删除', 'API删除', 'POST', 'vip.mate.system.controller.SysApiController', 'del', '/api/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (25, 'cedf94b1c53a31bd9c4e550e670ec382', 'API列表', '分页查询', 'GET', 'vip.mate.system.controller.SysApiController', 'page', '/api/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (26, 'bfebca06819591fb729c2dd004a5dae7', 'API信息', '根据ID查询', 'GET', 'vip.mate.system.controller.SysApiController', 'get', '/api/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (27, 'b15e96c84dbc6e4e266426c7f370c64d', 'API设置', 'API设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysApiController', 'set', '/api/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (28, 'fb508556e5851c2dd4d3cb5adb9a3ebe', 'API同步', 'API同步', 'POST', 'vip.mate.system.controller.SysApiController', 'sync', '/api/sync', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (29, '15cc4660be1df040172b21a7cfb6bfb0', '角色权限设置', '角色权限设置', 'POST', 'vip.mate.system.controller.SysRoleController', 'savePermission', '/role/set-permission', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (30, '3c4766f920f2b2762796ab1f23dc3747', '导出角色', '导出角色', 'POST', 'vip.mate.system.controller.SysRoleController', 'export', '/role/export', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (31, '18f524eabfeee4a4f607502df1772bf0', '角色信息', '根据ID查询', 'GET', 'vip.mate.system.controller.SysRoleController', 'get', '/role/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (32, '30f37312c4f39bf139eae532d8718ddb', '角色删除', '角色删除，支持批量操作', 'POST', 'vip.mate.system.controller.SysRoleController', 'delete', '/role/delete', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (33, 'f8a3cd040b7505082ff0761fc0ec57d1', '角色列表', '角色列表，根据query查询', 'GET', 'vip.mate.system.controller.SysRoleController', 'list', '/role/list', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (34, 'd7dc5460ad815d10cf593357ef9035a4', '角色设置', '角色设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysRoleController', 'set', '/role/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (35, 'cc040f3ad2c2bbd333f85dac3290707f', '角色权限列表', '角色权限列表', 'GET', 'vip.mate.system.controller.SysRoleController', 'getPermission', '/role/get-permission', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (36, '3132721d01d9dfdeb85ce5be59f47cd0', '角色树', '角色树', 'GET', 'vip.mate.system.controller.SysRoleController', 'tree', '/role/tree', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (37, '6b2c4db232b4d7432f74e48b40ee2ec6', '字典删除', '字典删除', 'POST', 'vip.mate.system.controller.SysDictController', 'del', '/dict/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (38, '8361e6eab1ed2bbbb0eccf086182376d', '字典分页', '字典分页', 'GET', 'vip.mate.system.controller.SysDictController', 'page', '/dict/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (39, '71ac4de98d9455136ef10e7d0a388745', '字典列表key查询', '字典列表key查询', 'GET', 'vip.mate.system.controller.SysDictController', 'getDictValue', '/dict/get-dict-value', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (40, '2116da876073f1ad71e677f010195b56', '字典项列表', '字典项列表', 'GET', 'vip.mate.system.controller.SysDictController', 'listValue', '/dict/list-value', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (41, 'eb427a14fa30167d708a20a1ecd9f699', '字典列表code查询', '字典列表code查询', 'GET', 'vip.mate.system.controller.SysDictController', 'listCode', '/dict/list-code', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (42, '2efa1e14facd02de585f755732c3025d', '字典信息', '根据ID查询', 'GET', 'vip.mate.system.controller.SysDictController', 'get', '/dict/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (43, 'c8629e5d870b295f5112fb3b2292bb5e', '字典设置', '字典设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysDictController', 'set', '/dict/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (44, 'a53752c2608e83164dbbaaab4fe94b32', '系统路由表删除', '系统路由表删除', 'POST', 'vip.mate.system.controller.SysRouteController', 'del', '/route/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (45, '237c64ae6e11b73b17d163fd34dfacae', '系统路由分页', '分页查询', 'GET', 'vip.mate.system.controller.SysRouteController', 'page', '/route/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (46, 'f97861d8a8ab59700d0b1e6fdfa170a6', '系统路由列表', '系统路由列表', 'GET', 'vip.mate.system.controller.SysRouteController', 'listItem', '/route/list-item', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (47, '292ec619e796b2ba7fca2787ad626ef5', '系统路由表信息', '根据ID查询', 'GET', 'vip.mate.system.controller.SysRouteController', 'get', '/route/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (48, '2597ff33c4eb041ceb3eb380dfb568b7', '系统路由表设置', '系统路由表设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysRouteController', 'set', '/route/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (49, '37dbe097797343d2dd73421fedc28d13', '客户端状态', '客户端状态：启用、禁用', 'POST', 'vip.mate.system.controller.SysClientController', 'setStatus', '/client/set-status', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (50, 'a0698ba9c55b8b34eb1d18601b6404b0', '客户端删除', '客户端删除', 'POST', 'vip.mate.system.controller.SysClientController', 'del', '/client/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (51, '808c8606e1a04378e6fe484bfa8e96d9', '客户端分页', '客户端分页', 'GET', 'vip.mate.system.controller.SysClientController', 'page', '/client/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (52, '791b3d641cbcd8f8e3d797d34004fefa', '客户端导出', '客户端导出', 'POST', 'vip.mate.system.controller.SysClientController', 'export', '/client/export', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (53, '607700fe45709b39c134974ee9132c23', '客户端信息', '客户端信息,根据ID查询', 'GET', 'vip.mate.system.controller.SysClientController', 'get', '/client/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (54, '8cbd4b8b5cab0cadbac7e5161ba32856', '客户端设置', '客户端设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysClientController', 'set', '/client/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (55, 'affc77e87638cec289664a87e5b23803', '部门删除', '部门删除', 'POST', 'vip.mate.system.controller.SysDepartController', 'del', '/depart/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (56, 'ad3acb2d8117fedb59535776c313279a', '部门导出', '部门导出', 'POST', 'vip.mate.system.controller.SysDepartController', 'export', '/depart/export', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (57, '3ef33db333255aee73f513cccc074175', '部门信息', '部门信息,根据ID查询', 'GET', 'vip.mate.system.controller.SysDepartController', 'get', '/depart/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (58, '46532d6cd52cdbe360dcea06573c4306', '部门列表', '部门列表，根据search查询', 'GET', 'vip.mate.system.controller.SysDepartController', 'list', '/depart/list', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (59, 'a1a4b644344f1c90b800a2744def39b9', '部门设置', '部门设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysDepartController', 'set', '/depart/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (60, 'a3e2e3de4432bd2b2759b3f079d0b8a6', '部门树', '部门树', 'GET', 'vip.mate.system.controller.SysDepartController', 'tree', '/depart/tree', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (61, 'bf66729eade77ec366107b564bc8d1f8', '用户状态', '状态包括：启用、禁用', 'POST', 'vip.mate.system.controller.SysUserController', 'setStatus', '/user/set-status', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (62, 'f5a57d978adf0c58177076d8f91e62f1', '用户删除', '用户删除', 'POST', 'vip.mate.system.controller.SysUserController', 'del', '/user/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (63, '3965fba22f3628b7846cb55183619259', '用户密码设置', '用户密码设置', 'POST', 'vip.mate.system.controller.SysUserController', 'setPassword', '/user/set-password', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (64, 'da038ce4f08b5bfa76c45487fa995131', '用户列表', '分页查询', 'GET', 'vip.mate.system.controller.SysUserController', 'page', '/user/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (65, '9f55dcda54dcb16515fed6bfa5d743a7', '导出用户', '导出用户', 'POST', 'vip.mate.system.controller.SysUserController', 'export', '/user/export', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (66, '5de06489c809b27b9f50a696538e0372', '用户信息', '根据ID查询', 'GET', 'vip.mate.system.controller.SysUserController', 'get', '/user/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (67, '43bab7218f6a3c27943097e24badf382', '设置用户', '新增或修改用户', 'POST', 'vip.mate.system.controller.SysUserController', 'set', '/user/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (68, 'e210e97e9cf161930609358a28f5835f', '黑名单信息', '黑名单信息,根据ID查询', 'GET', 'vip.mate.system.controller.SysBlacklistController', 'info', '/blacklist/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (69, '4798eb12924d6f9c9883ba3c160df2ce', '黑名单状态', '黑名单状态,状态包括：启用、禁用', 'POST', 'vip.mate.system.controller.SysBlacklistController', 'setStatus', '/blacklist/set-status', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (70, '2efb64abd7c136ca5bcd02b2d7ab4e08', '黑名单删除', '黑名单删除', 'POST', 'vip.mate.system.controller.SysBlacklistController', 'del', '/blacklist/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (71, '862e1ce03525e331e9f5fae5d949cb36', '黑名单分页', '黑名单分页', 'GET', 'vip.mate.system.controller.SysBlacklistController', 'page', '/blacklist/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (72, '89fbcdc911d0348d6c02560146c1c3b5', '黑名单设置', '黑名单设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysBlacklistController', 'set', '/blacklist/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (73, '1203d0422fc2937053fae33b2422b851', '日志删除', '日志删除', 'POST', 'vip.mate.system.controller.SysLogController', 'del', '/log/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (74, 'd8a6c8fc8ce040d7241a993fc5205513', '日志列表', '日志列表', 'GET', 'vip.mate.system.controller.SysLogController', 'page', '/log/page', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (75, 'ddd1be208bfa568c71c70a6e915ad11a', '菜单状态', '状态包括：启用、禁用', 'POST', 'vip.mate.system.controller.SysMenuController', 'setStatus', '/menu/set-status', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (76, '8d1543ef715f259ade524132e9d1dad3', '菜单分级列表', '菜单分级列表', 'GET', 'vip.mate.system.controller.SysMenuController', 'grade', '/menu/grade', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (77, '2d8d4ea69a18818a1735275911a47e8e', '菜单删除', '菜单删除', 'POST', 'vip.mate.system.controller.SysMenuController', 'del', '/menu/del', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (78, '0eef1aea5ea45364e8d9d8633408eca2', '菜单导出', '菜单导出', 'POST', 'vip.mate.system.controller.SysMenuController', 'export', '/menu/export', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (79, '42b6efbc0201b39d0961009d0dde763f', '菜单信息', '根据ID查询', 'GET', 'vip.mate.system.controller.SysMenuController', 'get', '/menu/get', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (80, '09b744aa117856131ccebc125ea3f668', '菜单列表', '菜单列表，根据关键词查询', 'GET', 'vip.mate.system.controller.SysMenuController', 'list', '/menu/list', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (81, 'b4fbfbea29741f7c7d6f1845a3529263', '菜单设置', '菜单设置,支持新增或修改', 'POST', 'vip.mate.system.controller.SysMenuController', 'set', '/menu/set', 'application/json;charset=UTF-8', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (82, 'f339fb5f7850d2ce02af0c2bd7b5864c', '菜单树', '根据roleId查询菜单树', 'GET', 'vip.mate.system.controller.SysMenuController', 'tree', '/menu/tree', '', 'mate-system', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (83, '581fcf1e512f78737b59d5ef46b00e68', '查询OSS配置', '查询OSS配置', 'GET', 'vip.mate.component.controller.SysConfigController', 'getConfigByCode', '/config/get-config-by-code', '', 'mate-component', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (84, '67e760391dc41cfce8f1eb490cf25590', '默认配置', '默认配置', 'GET', 'vip.mate.component.controller.SysConfigController', 'defaultOss', '/config/default-oss', '', 'mate-component', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (85, '3a5d36e02b2f335c05f29447dd250193', '保存默认配置', '保存默认配置', 'POST', 'vip.mate.component.controller.SysConfigController', 'saveDefaultOss', '/config/save-default-oss', '', 'mate-component', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (86, '9cd9e1e794871d16e6979c9d54f456c8', '保存OSS配置', '保存OSS配置', 'POST', 'vip.mate.component.controller.SysConfigController', 'saveConfigOss', '/config/save-config-oss', 'application/json;charset=UTF-8', 'mate-component', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (87, '5588f0697f631f1b95616035f2bd8831', '删除文件', '删除文件', 'POST', 'vip.mate.component.controller.SysAttachmentController', 'del', '/attachment/del', '', 'mate-component', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);
INSERT INTO `mate_sys_api` VALUES (88, 'fb2da47cf384518853d9f908de4a8ad6', '附件分页', '附件分页，根据query查询', 'GET', 'vip.mate.component.controller.SysAttachmentController', 'page', '/attachment/page', '', 'mate-component', '1', '1', NULL, NULL, '2020-10-19 22:22:25', NULL, '0', NULL);

-- ----------------------------
-- 12、系统路由表 mate_sys_route
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_route`;
CREATE TABLE `mate_sys_route` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
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
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='系统路由表';

-- ----------------------------
-- 初始化-系统路由表数据
-- ----------------------------
INSERT INTO `mate_sys_route` VALUES (1, '系统服务', '/mate-system/**', NULL, 'mate-system', '1', NULL, NULL, '2020-10-18 22:59:02', '2020-10-18 23:03:42', '0', NULL);
INSERT INTO `mate_sys_route` VALUES (2, '认证服务', '/mate-uaa/**', NULL, 'mate-uaa', '1', NULL, NULL, '2020-10-18 15:14:13', '2020-10-18 23:14:24', '0', NULL);
INSERT INTO `mate_sys_route` VALUES (3, '代码服务', '/mate-code/**', NULL, 'mate-code', '1', NULL, NULL, '2020-10-18 20:21:25', '2020-10-20 09:13:45', '0', NULL);
INSERT INTO `mate_sys_route` VALUES (4, '组件服务', '/mate-component/**', NULL, 'mate-component', '1', NULL, NULL, '2020-10-18 20:22:42', '2020-10-20 09:13:14', '0', NULL);

-- ----------------------------
-- 13、系统黑名单表 mate_sys_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_blacklist`;
CREATE TABLE `mate_sys_blacklist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `request_uri` varchar(100) DEFAULT NULL COMMENT '请求地址',
  `request_method` varchar(10) DEFAULT NULL COMMENT '请求方法',
  `start_time` varchar(32) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(32) DEFAULT NULL COMMENT '结束时间',
  `status` char(1) DEFAULT '0' COMMENT '状态：0:关闭 1:开启',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统黑名单表';

-- ----------------------------
-- 初始化-系统黑名单表
-- ----------------------------
BEGIN;
INSERT INTO `mate_sys_blacklist` VALUES (1, NULL, '/**/actuator/**', 'ALL', NULL, NULL, '1', NULL, NULL, '2020-08-22 01:40:16', '2020-08-22 01:40:34');
COMMIT;

-- ----------------------------
-- 14、系统数据源表 mate_sys_data_source
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_data_source`;
CREATE TABLE `mate_sys_data_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
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
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='数据源表';

-- ----------------------------
-- 初始化-数据源表数据
-- ----------------------------

INSERT INTO `mate_sys_data_source` VALUES (1, 'ldx', 'mysql', 'com.mysql.cj.jdbc.Driver', 'localhost:3306', 'root', 'root', NULL, NULL, NULL, NULL, '2020-12-08 15:17:40', NULL, 0);

-- ----------------------------
-- 15、系统附件表 mate_sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_attachment`;
CREATE TABLE `mate_sys_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '附件ID',
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
  `is_recycle` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否加入回收站 0.否|1.是',
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COMMENT='附件表';

-- ----------------------------
-- 初始化-附件表数据
-- ----------------------------
INSERT INTO `mate_sys_attachment` VALUES (13, 0, 0, 'timg (3).jpeg', 26516, 'https://cdn.ckjia.com/10e258da699b4c318ff59e24f2599420.jpeg', '10e258da699b4c318ff59e24f2599420.jpeg', '', 1, NULL, NULL, '2020-08-10 03:29:26', NULL, '0', 0);
INSERT INTO `mate_sys_attachment` VALUES (16, 0, 0, 'background.jpg', 261548, 'https://cdn.ckjia.com/3b8f8e2b5ffb43b0905397b82a6b3ec6.jpg', '3b8f8e2b5ffb43b0905397b82a6b3ec6.jpg', '', 1, NULL, NULL, '2020-08-10 11:55:53', NULL, '0', 0);
INSERT INTO `mate_sys_attachment` VALUES (17, 0, 0, 'nav-icon-new.active.png', 3036, 'https://cdn.ckjia.com/5efe50fcd0e744eaa7a2e7c6d851dd82.png', '5efe50fcd0e744eaa7a2e7c6d851dd82.png', '', 1, NULL, NULL, '2020-08-10 13:39:06', NULL, '0', 0);
INSERT INTO `mate_sys_attachment` VALUES (18, 0, 0, 'nav-icon-user.active.png', 2150, 'https://cdn.ckjia.com/90cef6a278b34c1690af938193e2d813.png', '90cef6a278b34c1690af938193e2d813.png', '', 1, NULL, NULL, '2020-08-10 13:40:56', NULL, '0', 0);
INSERT INTO `mate_sys_attachment` VALUES (19, 0, 0, 'nav-icon-cat.active.png', 3338, 'https://cdn.ckjia.com/8ffa2bf6e6e7491b8460bf308abd30de.png', '8ffa2bf6e6e7491b8460bf308abd30de.png', '', 1, NULL, NULL, '2020-08-10 13:41:49', NULL, '0', 0);
INSERT INTO `mate_sys_attachment` VALUES (20, 0, 0, 'nav-icon-index.active.png', 2754, 'https://cdn.ckjia.com/478acfc9aeb140a4b79c6402ba3bd021.png', '478acfc9aeb140a4b79c6402ba3bd021.png', '', 1, NULL, NULL, '2020-08-10 13:54:53', NULL, '0', 0);
INSERT INTO `mate_sys_attachment` VALUES (21, 0, 0, 'baiduzhifu2x.png', 19415, 'https://cdn.ckjia.com/5ba794ec8d054ce995d37d364c5a9836.png', '5ba794ec8d054ce995d37d364c5a9836.png', '', 1, NULL, NULL, '2020-08-10 13:56:10', NULL, '0', 0);
INSERT INTO `mate_sys_attachment` VALUES (22, 0, 0, 'h_seckill.png', 6008, 'https://cdn.ckjia.com/897d70b0635f48999baa635d6debbbee.png', '897d70b0635f48999baa635d6debbbee.png', '', 1, NULL, NULL, '2020-08-10 13:59:47', NULL, '0', 0);
INSERT INTO `mate_sys_attachment` VALUES (24, 0, 0, 'mate-bg2.jpeg', 79028, 'https://cdn.ckjia.com/7667a4086d3c40948207dc8e02b52ff9.jpeg', '7667a4086d3c40948207dc8e02b52ff9.jpeg', '', 1, NULL, NULL, '2020-08-11 14:19:53', NULL, '0', 0);

-- ----------------------------
-- 16、系统租户表 mate_sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_tenant`;
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='租户表';

-- ----------------------------
-- 初始化-租户表数据
-- ----------------------------
