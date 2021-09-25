-- ----------------------------
-- 1、菜单权限表 mate_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_menu`;
CREATE TABLE `mate_sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(32) DEFAULT NULL COMMENT '菜单标题',
  `permission` varchar(32) DEFAULT NULL COMMENT '菜单权限',
  `path` varchar(128) DEFAULT NULL COMMENT '路径',
  `component` varchar(128) DEFAULT NULL COMMENT '组件',
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
) ENGINE=InnoDB AUTO_INCREMENT=2069 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';

-- ----------------------------
-- 初始化-菜单权限表数据
-- ----------------------------
INSERT INTO `mate_sys_menu` VALUES (1000, '系统管理', NULL, '/system', 'Layout', -1, 'ant-design:appstore-outlined', 1, '0', '0', '0', '1', NULL, NULL, '2020-06-17 14:21:45', '2021-08-13 20:56:34', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1100, '用户管理', NULL, '/system/user', '/system/user/index', 1000, 'ant-design:user-outlined', 2, '0', '1', '0', '1', NULL, NULL, '2020-06-18 14:28:36', '2021-08-13 21:28:25', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1101, '用户新增', 'sys:user:add', '', NULL, 1100, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-06-17 14:32:51', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1102, '用户修改', 'sys:user:edit', NULL, NULL, 1100, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-06-20 00:27:40', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1103, '用户删除', 'sys:user:delete', NULL, NULL, 1100, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-06-20 00:27:56', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1104, '用户启用', 'sys:user:enable', NULL, NULL, 1100, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-03 08:49:47', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1105, '用户禁用', 'sys:user:disable', NULL, NULL, 1100, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-03 08:50:16', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1106, '用户导出', 'sys:user:export', NULL, NULL, 1100, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-03 08:50:58', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1200, '角色管理', NULL, '/system/role', '/system/role/index', 1000, 'ant-design:team-outlined', 4, '0', '1', '0', '1', NULL, NULL, '2020-06-19 16:36:01', '2021-08-14 07:17:58', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1201, '角色新增', 'sys:role:add', NULL, NULL, 1200, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-06-20 00:37:12', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1202, '角色修改', 'sys:role:edit', NULL, NULL, 1200, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-06-20 00:38:23', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1203, '角色删除', 'sys:role:delete', NULL, NULL, 1200, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-06-20 00:38:53', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1204, '角色导出', 'sys:role:export', NULL, NULL, 1200, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-03 14:02:37', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1205, '角色权限', 'sys:role:perm', NULL, NULL, 1200, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-03 14:03:32', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1300, '菜单管理', NULL, '/system/menu', '/system/menu/index', 1000, 'ant-design:menu-unfold-outlined', 1, '0', '1', '0', '1', NULL, NULL, '2020-06-19 16:39:07', '2021-08-13 21:03:17', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1301, '菜单新增', 'sys:menu:add', NULL, NULL, 1300, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-06-20 00:39:48', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1302, '菜单修改', 'sys:menu:edit', NULL, NULL, 1300, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-06-20 00:40:21', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1303, '菜单删除', 'sys:menu:delete', NULL, NULL, 1300, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-06-20 00:40:42', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1304, '菜单启用', 'sys:menu:enable', NULL, NULL, 1300, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-03 14:12:59', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1305, '菜单禁用', 'sys:menu:disable', NULL, NULL, 1300, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-03 14:13:34', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1306, '菜单导出', 'sys:menu:export', NULL, NULL, 1300, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-03 14:14:32', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1400, '部门管理', NULL, '/system/depart', '/system/depart/index', 1000, 'ant-design:apartment-outlined', 3, '0', '1', '0', '1', NULL, NULL, '2020-06-26 22:52:41', '2021-08-13 21:20:11', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1401, '部门新增', 'sys:depart:add', NULL, NULL, 1400, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-06-27 14:53:37', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1402, '部门修改', 'sys:depart:edit', NULL, NULL, 1400, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-06-27 14:54:47', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1403, '部门删除', 'sys:depart:delete', NULL, NULL, 1400, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-06-27 14:55:15', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (1404, '部门导出', 'sys:depart:export', NULL, NULL, 1400, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-03 14:27:26', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2015, '开发运维', NULL, '/devops', 'Layout', -1, 'ant-design:tool-outlined', 3, '0', '0', '0', '1', NULL, NULL, '2020-07-05 11:20:31', '2021-08-14 15:00:56', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2016, '数据源管理', '', '/devops/datasource', '/devops/datasource/index', 2015, 'ant-design:database-outlined', 1, '0', '1', '0', '1', NULL, NULL, '2020-07-06 19:21:58', '2021-08-16 23:06:52', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2017, '数据源新增', 'sys:datasource:add', NULL, NULL, 2016, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-07 04:08:11', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2018, '数据源修改', 'sys:datasource:edit', NULL, NULL, 2016, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-07 04:08:40', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2019, '数据源删除', 'sys:datasource:delete', NULL, NULL, 2016, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-07 04:09:05', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2020, '数据源导出', 'sys:datasource:export', NULL, NULL, 2016, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-07 04:09:25', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2021, '代码生成', NULL, '/devops/generator', '/devops/generator/index', 2015, 'ant-design:experiment-outlined', 2, '0', '1', '0', '1', NULL, NULL, '2020-07-09 07:08:50', '2021-09-06 14:39:11', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2022, '代码生成', 'devops:gen', NULL, NULL, 2021, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-08 23:09:45', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2023, '监控配置中心', NULL, '/devops/monitor', '/devops/monitor/index', 2015, 'ant-design:desktop-outlined', 3, '0', '1', '0', '1', NULL, NULL, '2020-07-10 20:23:07', '2021-09-06 14:38:58', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2026, '客户端管理', NULL, '/system/client', '/system/client/index', 1000, 'ant-design:mobile-outlined', 7, '0', '1', '0', '1', NULL, NULL, '2020-07-13 22:47:20', '2021-08-14 14:52:32', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2027, '新增客户端', 'sys:client:add', NULL, NULL, 2026, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-13 22:47:44', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2028, '修改客户端', 'sys:client:edit', NULL, NULL, 2026, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-13 23:47:37', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2029, '删除客户端', 'sys:client:delete', NULL, NULL, 2026, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-13 23:48:11', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2030, '导出客户端', 'sys:client:export', NULL, NULL, 2026, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-13 23:48:28', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2031, '启禁客户端', 'sys:client:status', NULL, NULL, 2026, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-13 23:49:22', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2032, '操作日志', NULL, '/system/log', '/system/log/index', 1000, 'ant-design:ordered-list-outlined', 8, '0', '1', '0', '1', NULL, NULL, '2020-07-15 05:11:09', '2021-08-14 14:55:52', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2033, '详细日志', 'sys:log:detail', NULL, NULL, 2032, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-16 04:05:48', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2034, '日志删除', 'sys:log:delete', NULL, NULL, 2032, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-16 04:06:16', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2035, '字典管理', NULL, '/system/dict', '/system/dict/index', 1000, 'ant-design:read-outlined', 6, '0', '1', '0', '1', NULL, NULL, '2020-07-17 09:29:31', '2021-08-14 14:36:16', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2036, '新增字典', 'sys:dict:add', NULL, NULL, 2035, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-20 02:48:01', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2037, '修改字典', 'sys:dict:edit', NULL, NULL, 2035, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-20 02:48:20', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2038, '删除字典', 'sys:dict:delete', NULL, NULL, 2035, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-07-20 02:48:39', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2039, '组件管理', NULL, '/content/component', '/content/component/index', 2040, 'ant-design:usb-outlined', 1, '0', '1', '0', '1', NULL, NULL, '2020-08-08 05:35:05', '2021-08-16 23:09:40', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2040, '内容管理', NULL, '/content', 'Layout', -1, 'ant-design:dribbble-outlined', 4, '0', '0', '0', '1', NULL, NULL, '2020-08-09 00:21:42', '2021-08-14 15:01:58', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2041, '文件管理', NULL, '/content/attachment', '/content/attachment/index', 2040, 'ant-design:folder-open-twotone', 2, '0', '1', '0', '1', NULL, NULL, '2020-08-09 00:27:06', '2021-08-16 23:10:21', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2042, '修改组件', 'sys:comp:edit', NULL, NULL, 2039, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-08-10 00:42:28', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2043, '上传文件', 'sys:attach:add', NULL, NULL, 2041, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-08-10 08:43:52', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2044, '删除文件', 'sys:attach:delete', NULL, NULL, 2041, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-08-10 08:44:29', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2045, '网关中心', NULL, '/gateway', 'Layout', -1, 'ant-design:instagram-outlined', 2, '0', '0', '0', '1', NULL, NULL, '2020-08-28 19:12:00', '2021-08-14 14:58:31', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2047, '黑名单管理', NULL, '/gateway/blacklist', '/gateway/blacklist/index', 2045, 'ant-design:codepen-square-filled', 3, '0', '1', '0', '1', NULL, NULL, '2020-08-29 03:15:34', '2021-08-16 23:03:31', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2048, '新增黑名单', 'gw:bl:add', NULL, NULL, 2047, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-08-29 09:38:52', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2049, '修改黑名单', 'gw:bl:edit', NULL, NULL, 2047, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-08-29 09:39:27', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2050, '删除黑名单', 'gw:bl:del', NULL, NULL, 2047, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-08-29 09:39:51', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2051, '黑名单状态', 'gw:bl:status', NULL, NULL, 2047, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-08-29 09:44:20', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2055, 'API管理', NULL, '/gateway/api', '/gateway/api/index', 2045, 'ant-design:sliders-outlined', 2, '0', '1', '0', '1', NULL, NULL, '2020-10-14 14:00:06', '2021-08-16 23:02:57', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2056, '微服务管理', NULL, '/gateway/route', '/gateway/route/index', 2045, 'ant-design:ungroup-outlined', 1, '0', '1', '0', '1', NULL, NULL, '2020-10-17 12:53:27', '2021-08-16 22:56:32', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2057, '同步API', 'gw:api:sync', NULL, NULL, 2055, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-10-17 14:16:06', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2058, '删除API', 'gw:api:del', NULL, NULL, 2055, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-10-17 14:17:25', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2059, '修改API', 'gw:api:edit', NULL, NULL, 2055, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-10-17 14:17:58', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2060, '新增微服务', 'gw:route:add', NULL, NULL, 2056, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-10-19 05:19:45', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2061, '修改微服务', 'gw:route:edit', NULL, NULL, 2056, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-10-19 05:20:49', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2062, '删除微服务', 'gw:route:del', NULL, NULL, 2056, NULL, 1, '0', '2', '0', '1', NULL, NULL, '2020-10-19 05:21:03', '2021-07-24 23:29:27', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2064, '工作台', NULL, '/dashboard', 'Layout', -1, 'ant-design:windows-outlined', 0, '0', '0', '0', '0', NULL, NULL, '2021-08-16 09:07:39', '2021-08-16 18:29:30', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2065, '分析页', NULL, '/dashboard', '/dashboard/analysis/index', 2064, 'ant-design:rise-outlined', 0, '0', '1', '0', '0', NULL, NULL, '2021-08-16 09:14:24', '2021-08-16 16:07:05', '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2068, '工作台', NULL, '/dashboard/workbench', '/dashboard/workbench/index', 2064, 'ant-design:block-outlined', 2, '0', '1', '0', '0', NULL, NULL, '2021-08-16 20:23:58', NULL, '0', '0', 0);
INSERT INTO `mate_sys_menu` VALUES (2069, '关于Artemis', NULL, '/about/index', '/sys/about/index', -1, 'ant-design:info-circle-outlined', 100, '0', '1', '0', '0', NULL, NULL, '2021-08-30 15:19:47', '2021-08-30 15:35:44', '0', '0', 0);

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
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
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
INSERT INTO `mate_sys_user` VALUES (2, '100000', 'admin', '{bcrypt}$2a$10$IhNoDpkdJ1VzbnfX1pH35.S25n2tHaxU4hSltf7An.wdiXAsYe2Jm', 'admin', '超级管理员', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18810001000', NULL, 1, 1, 1, '0', NULL, NULL, NULL, '2020-07-02 07:29:12', '2021-08-16 22:40:45', '0');
INSERT INTO `mate_sys_user` VALUES (3, '100000', 'admin2', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin2', '杨幂', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 2, 4, '0', NULL, NULL, NULL, '2020-06-16 20:05:43', '2021-08-14 07:08:24', '0');
INSERT INTO `mate_sys_user` VALUES (4, '100000', 'admin4', '{bcrypt}$2a$10$UIrEMyC0GUIUdJuGPYeGO.Nc8AZYlTiC8MUttPaYQ7P.V5q/cTAAG', 'admin4', '刘德华', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 2, 1, '0', NULL, NULL, NULL, '2020-06-17 04:05:43', '2021-08-23 07:28:50', '0');
INSERT INTO `mate_sys_user` VALUES (5, '100000', 'admin5', '{bcrypt}$2a$10$WF2dEcJetVqRFOn/AfrfZu4.Z5HcBFZ49/fL/KKOQ6b.xJtXZHoqe', 'admin5', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '0', NULL, NULL, NULL, '2020-06-17 04:05:43', '2021-08-23 07:25:50', '0');
INSERT INTO `mate_sys_user` VALUES (6, '100000', 'admin6', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '0', NULL, NULL, NULL, '2020-06-17 12:05:43', '2020-07-02 13:39:01', '0');
INSERT INTO `mate_sys_user` VALUES (7, '100000', 'admin7', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '0', NULL, NULL, NULL, '2020-06-16 12:05:43', '2020-06-30 12:55:52', '0');
INSERT INTO `mate_sys_user` VALUES (8, '100000', 'admin8', '{bcrypt}$2a$10$6YPJ0dkaqh9x3zwTfidRBeT0U5vJ7Si7wXFx9Gc/K3pMxz8J18TrW', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 2, 1, '0', NULL, NULL, NULL, '2020-06-16 20:05:43', '2020-07-15 04:31:24', '0');
INSERT INTO `mate_sys_user` VALUES (9, '100000', 'admin9', '{bcrypt}$2a$10$pDzXQpiSIl74Jekj9CxMWOPbEV9MHkjkZ7GXX/RomtIyXz8m6Ruia', 'admin6', 'mate', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '1', NULL, NULL, NULL, '2020-06-16 20:05:43', '2020-07-01 21:39:03', '0');
INSERT INTO `mate_sys_user` VALUES (10, '100000', 'admin10', '{bcrypt}$2a$10$A5cfRbFDCsOg.1UTlWyEZu8DJHSr9GnANXQMsRSIDAtZHuiQicr0K', 'admin6', '测试管理员', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'mate@mate.vip', '18910001000', NULL, 1, 1, 1, '1', '测试管理员', NULL, NULL, '2020-06-15 12:05:43', '2021-08-29 15:57:25', '0');
INSERT INTO `mate_sys_user` VALUES (22, '100000', 'pp1', '{bcrypt}$2a$10$jddK.xwTn99XM9ggy64Zgu.eBK2GBiyk9RmtQEjg99S2F8otQ8ieq', 'pp1', '11', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2084118128,2518711034&fm=26&gp=0.jpg', 'pp1@163.com', '1899', '2020-06-26 16:00:00', 2, 2, 1, '0', NULL, NULL, NULL, '2020-06-30 09:57:13', '2021-08-18 21:54:06', '0');

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
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `status` char(1) DEFAULT NULL COMMENT '状态',
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
INSERT INTO `mate_sys_role` VALUES (1, '管理员', 'admin', '管理员组', 0, '0', NULL, NULL, '2020-06-28 15:02:16', '2021-09-25 16:16:31', '0', NULL);
INSERT INTO `mate_sys_role` VALUES (2, '演示会员', 'demo2', '演示会员组', 0, '0', NULL, NULL, '2020-06-28 07:02:36', '2021-09-25 16:16:18', '0', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=2710 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- 初始化-角色权限表数据
-- ----------------------------
INSERT INTO `mate_sys_role_permission` VALUES (977, 1000, 2);
INSERT INTO `mate_sys_role_permission` VALUES (978, 1300, 2);
INSERT INTO `mate_sys_role_permission` VALUES (979, 1301, 2);
INSERT INTO `mate_sys_role_permission` VALUES (980, 1302, 2);
INSERT INTO `mate_sys_role_permission` VALUES (981, 1303, 2);
INSERT INTO `mate_sys_role_permission` VALUES (982, 2009, 2);
INSERT INTO `mate_sys_role_permission` VALUES (2171, NULL, NULL);
INSERT INTO `mate_sys_role_permission` VALUES (2370, 1000, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2371, 1300, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2372, 1301, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2373, 1302, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2374, 1303, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2375, 1304, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2376, 1305, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2377, 1306, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2378, 1100, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2379, 1101, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2380, 1102, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2381, 1103, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2382, 1104, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2383, 1105, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2384, 1106, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2385, 1400, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2386, 1401, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2387, 1402, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2388, 1403, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2389, 1404, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2390, 1200, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2391, 1201, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2392, 1202, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2393, 1203, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2394, 1204, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2395, 1205, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2396, 2039, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2397, 2042, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2398, 2035, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2399, 2036, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2400, 2037, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2401, 2038, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2402, 2026, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2403, 2027, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2404, 2028, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2405, 2029, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2406, 2030, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2407, 2031, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2408, 2032, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2409, 2033, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2410, 2034, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2411, 2045, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2412, 2056, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2413, 2060, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2414, 2061, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2415, 2062, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2416, 2055, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2417, 2057, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2418, 2058, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2419, 2059, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2420, 2047, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2421, 2048, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2422, 2049, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2423, 2050, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2424, 2051, 3);
INSERT INTO `mate_sys_role_permission` VALUES (2567, 2064, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2568, 2065, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2569, 1000, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2570, 1300, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2571, 1301, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2572, 1302, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2573, 1303, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2574, 1304, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2575, 1305, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2576, 1306, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2577, 1100, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2578, 1101, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2579, 1102, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2580, 1103, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2581, 1104, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2582, 1105, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2583, 1106, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2584, 1400, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2585, 1401, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2586, 1402, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2587, 1403, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2588, 1404, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2589, 1200, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2590, 1201, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2591, 1202, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2592, 1203, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2593, 1204, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2594, 1205, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2595, 2035, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2596, 2036, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2597, 2037, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2598, 2038, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2599, 2026, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2600, 2027, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2601, 2028, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2602, 2029, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2603, 2030, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2604, 2031, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2605, 2032, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2606, 2033, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2607, 2034, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2608, 2045, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2609, 2056, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2610, 2060, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2611, 2061, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2612, 2062, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2613, 2055, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2614, 2057, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2615, 2058, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2616, 2059, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2617, 2047, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2618, 2048, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2619, 2049, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2620, 2050, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2621, 2051, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2622, 2015, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2623, 2016, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2624, 2017, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2625, 2018, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2626, 2019, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2627, 2020, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2628, 2021, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2629, 2022, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2630, 2023, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2631, 2040, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2632, 2053, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2633, 2039, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2634, 2042, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2635, 2041, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2636, 2043, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2637, 2044, 4);
INSERT INTO `mate_sys_role_permission` VALUES (2638, 1000, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2639, 1300, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2640, 1301, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2641, 1302, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2642, 1303, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2643, 1304, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2644, 1305, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2645, 1306, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2646, 1100, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2647, 1101, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2648, 1102, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2649, 1103, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2650, 1104, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2651, 1105, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2652, 1106, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2653, 1400, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2654, 1401, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2655, 1402, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2656, 1403, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2657, 1404, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2658, 1200, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2659, 1201, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2660, 1202, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2661, 1203, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2662, 1204, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2663, 1205, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2664, 2035, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2665, 2036, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2666, 2037, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2667, 2038, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2668, 2026, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2669, 2027, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2670, 2028, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2671, 2029, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2672, 2030, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2673, 2031, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2674, 2032, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2675, 2033, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2676, 2034, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2677, 2039, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2678, 2042, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2679, 2040, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2680, 2053, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2681, 2041, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2682, 2043, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2683, 2044, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2684, 2045, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2685, 2056, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2686, 2060, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2687, 2061, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2688, 2062, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2689, 2055, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2690, 2057, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2691, 2058, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2692, 2059, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2693, 2047, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2694, 2048, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2695, 2049, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2696, 2050, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2697, 2051, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2698, 2015, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2699, 2016, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2700, 2017, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2701, 2018, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2702, 2019, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2703, 2020, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2704, 2021, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2705, 2022, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2706, 2023, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2707, 2065, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2708, 2068, 1);
INSERT INTO `mate_sys_role_permission` VALUES (2709, 2064, 1);

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
  `status` char(1) DEFAULT '0' COMMENT 'API状态:0:启用 1:禁用',
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
-- 12、系统路由表 mate_sys_route
-- ----------------------------
DROP TABLE IF EXISTS `mate_sys_route`;
CREATE TABLE `mate_sys_route` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(100) DEFAULT NULL COMMENT '服务名称',
  `path` varchar(255) DEFAULT NULL COMMENT '服务前缀',
  `url` varchar(255) DEFAULT NULL COMMENT '地址',
  `service_id` varchar(100) DEFAULT NULL COMMENT '服务编码',
  `status` char(1) DEFAULT '0' COMMENT 'API状态:0:启用 1:禁用',
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
INSERT INTO `mate_sys_route` VALUES (1, '系统服务', '/mate-system/**', 'mate-system', 'mate-system', '0', NULL, NULL, '2020-10-18 22:59:02', '2021-09-23 14:13:21', '0', NULL);
INSERT INTO `mate_sys_route` VALUES (2, '认证服务', '/mate-uaa/**', 'mate-uaa', 'mate-uaa', '0', NULL, NULL, '2020-10-18 15:14:13', '2021-09-23 14:13:36', '0', NULL);
INSERT INTO `mate_sys_route` VALUES (3, '代码服务', '/mate-code/**', 'mate-code', 'mate-code', '0', NULL, NULL, '2020-10-18 20:21:25', '2021-09-23 14:13:31', '0', NULL);
INSERT INTO `mate_sys_route` VALUES (4, '组件服务', '/mate-component/**', 'mate-component', 'mate-component', '0', NULL, NULL, '2020-10-18 20:22:42', '2021-09-23 14:13:27', '0', NULL);

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
