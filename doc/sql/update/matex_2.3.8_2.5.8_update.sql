-- ----------------------------
-- 角色和部门关联表  角色1-N部门
-- ----------------------------
create table mate_sys_role_depart
(
    `id`      bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
    role_id   bigint(20) not null comment '角色ID',
    depart_id bigint(20) not null comment '部门ID',
    primary key (id)
) engine = innodb
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 comment = '角色和部门关联表';