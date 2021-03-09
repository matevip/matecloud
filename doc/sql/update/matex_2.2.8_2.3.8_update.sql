ALTER TABLE mate_sys_role ADD COLUMN `data_type` tinyint(2) CHARACTER SET utf8mb4 DEFAULT '0'  COMMENT '数据权限类型';
ALTER TABLE mate_sys_role ADD COLUMN `data_scope` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '数据权限范围 1 全部 2 本级 3 本级以及子级 4 自定义';
