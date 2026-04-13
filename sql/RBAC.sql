-- =============================================
-- 系统核心表结构（RBAC权限模型）
-- 包含：用户、角色、菜单、权限、字典、日志
-- =============================================

-- ----------------------------
-- 用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
                          user_id           bigint(20)      not null auto_increment    comment '用户ID',
                          user_name         varchar(30)     not null                   comment '用户账号',
                          nick_name         varchar(30)     not null                   comment '用户昵称',
                          user_type         varchar(2)      default '00'               comment '用户类型（00系统用户）',
                          email             varchar(50)     default ''                 comment '用户邮箱',
                          phone             varchar(11)     default ''                 comment '手机号码',
                          sex               char(1)         default '0'                comment '用户性别（1男 2女 3未知）',
                          avatar            varchar(100)    default ''                 comment '头像地址',
                          password          varchar(100)    default ''                 comment '密码',
                          status            char(1)         default '0'                comment '账号状态（0正常 1停用）',
                          del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
                          login_ip          varchar(128)    default ''                 comment '最后登录IP',
                          login_date        datetime                                   comment '最后登录时间',
                          pwd_update_date   datetime                                   comment '密码最后更新时间',
                          create_by         varchar(64)     default ''                 comment '创建者',
                          create_time       datetime                                   comment '创建时间',
                          update_by         varchar(64)     default ''                 comment '更新者',
                          update_time       datetime                                   comment '更新时间',
                          remark            varchar(500)    default null               comment '备注',
                          primary key (user_id) USING BTREE,
                          UNIQUE KEY `uk_username` (`user_name`)
) engine=innodb auto_increment=100 comment = '用户信息表';

-- ----------------------------
-- 初始化用户数据
-- ----------------------------

-- ----------------------------
-- 角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
                          role_id              bigint(20)      not null auto_increment    comment '角色ID',
                          role_name            varchar(30)     not null                   comment '角色名称',
                          role_key             varchar(100)    not null                   comment '角色权限字符串',
                          role_sort            int(4)          not null                   comment '显示顺序',
                          status               char(1)         not null                   comment '角色状态（0正常 1停用）',
                          del_flag             char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
                          create_by            varchar(64)     default ''                 comment '创建者',
                          create_time          datetime                                   comment '创建时间',
                          update_by            varchar(64)     default ''                 comment '更新者',
                          update_time          datetime                                   comment '更新时间',
                          remark               varchar(500)    default null               comment '备注',
                          primary key (role_id)
) engine=innodb auto_increment=100 comment = '角色信息表';

-- ----------------------------
-- 菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
                          menu_id           bigint(20)      not null auto_increment    comment '菜单ID',
                          menu_name         varchar(50)     not null                   comment '菜单名称',
                          parent_id         bigint(20)      default 0                  comment '父菜单ID',
                          menu_sort         int(4)          default 0                  comment '显示顺序',
                          path              varchar(200)    default ''                 comment '路由地址',
                          component         varchar(255)    default null               comment '组件路径',
                          query             varchar(255)    default null               comment '路由参数',
                          route_name        varchar(50)     default ''                 comment '路由名称',
                          is_frame          int(1)          default 1                  comment '是否为外链（0是 1否）',
                          is_cache          int(1)          default 0                  comment '是否缓存（0缓存 1不缓存）',
                          menu_type         char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
                          visible           char(1)         default 0                  comment '菜单状态（0显示 1隐藏）',
                          status            char(1)         default 0                  comment '菜单状态（0正常 1停用）',
                          perms             varchar(100)    default null               comment '权限标识',
                          icon              varchar(100)    default '#'                comment '菜单图标',
                          create_by         varchar(64)     default ''                 comment '创建者',
                          create_time       datetime                                   comment '创建时间',
                          update_by         varchar(64)     default ''                 comment '更新者',
                          update_time       datetime                                   comment '更新时间',
                          remark            varchar(500)    default ''                 comment '备注',
                          primary key (menu_id)
) engine=innodb auto_increment=2000 comment = '菜单权限表';


-- ----------------------------
-- 用户和角色关联表  用户多对一角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role (
                               user_id   bigint(20) not null comment '用户ID',
                               role_id   bigint(20) not null comment '角色ID',
                               primary key(user_id, role_id)
) engine=innodb comment = '用户和角色关联表';

-- ----------------------------
-- 角色和菜单关联表  角色一对多菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
                               role_id   bigint(20) not null comment '角色ID',
                               menu_id   bigint(20) not null comment '菜单ID',
                               primary key(role_id, menu_id)
) engine=innodb comment = '角色和菜单关联表';

