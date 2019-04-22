CREATE TABLE `sso_user` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `username` varchar(100) DEFAULT NULL COMMENT '登录账号',
  `realname` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT 'md5密码盐',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `mobile` varchar(45) DEFAULT NULL COMMENT '电话',
  `is_enabled` tinyint(4) DEFAULT '1' COMMENT '状态(1：正常  2：冻结 ）',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除状态（0，正常，1已删除）',
  `supper_flag` tinyint(4) DEFAULT '0' COMMENT '超级管理员标志 1: 超级管理员',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_user_name` (`username`) USING BTREE,
  KEY `index_user_status` (`is_enabled`) USING BTREE,
  KEY `index_user_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `sso_role` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `role_name` varchar(200) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(100) DEFAULT NULL COMMENT '角色编码',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';


CREATE TABLE `sso_permission` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父id',
  `name` varchar(100) DEFAULT NULL COMMENT '菜单标题',
  `url` varchar(255) DEFAULT NULL COMMENT '路径',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `component_name` varchar(100) DEFAULT NULL COMMENT '组件名字',
  `redirect` varchar(255) DEFAULT NULL COMMENT '一级菜单跳转地址',
  `menu_type` int(11) DEFAULT NULL COMMENT '菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)',
  `perms` varchar(255) DEFAULT NULL COMMENT '菜单权限编码',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `always_show` tinyint(1) DEFAULT NULL COMMENT '聚合子路由: 1是0否',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `is_route` tinyint(1) DEFAULT '1' COMMENT '是否路由菜单: 0:不是  1:是（默认值1）',
  `is_leaf` tinyint(1) DEFAULT NULL COMMENT '是否叶子节点:    1:是   0:不是',
  `hidden` int(2) DEFAULT '0' COMMENT '是否隐藏路由: 0否,1是',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除状态（0，正常，1已删除）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `index_prem_pid` (`parent_id`) USING BTREE,
  KEY `index_prem_is_route` (`is_route`) USING BTREE,
  KEY `index_prem_is_leaf` (`is_leaf`) USING BTREE,
  KEY `index_prem_sort_no` (`order_num`) USING BTREE,
  KEY `index_prem_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限';


create table `sso_depart` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父机构ID',
  `depart_name` varchar(100) NOT NULL COMMENT '机构/部门名称',
  `depart_name_en` varchar(500) DEFAULT NULL COMMENT '英文名',
  `depart_name_abbr` varchar(500) DEFAULT NULL COMMENT '缩写',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `description` text COMMENT '描述',
  `org_type` varchar(10) DEFAULT NULL COMMENT '机构类型',
  `org_code` varchar(64) NOT NULL COMMENT '机构编码',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `fax` varchar(32) DEFAULT NULL COMMENT '传真',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `memo` varchar(500) DEFAULT NULL COMMENT '备注',
  `is_enabled` tinyint(4) DEFAULT '1' COMMENT '状态(1：正常  0：禁用 ）',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除状态（0，正常，1已删除）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `index_depart_parent_id` (`parent_id`) USING BTREE,
  KEY `index_depart_depart_order` (`order_num`) USING BTREE,
  KEY `index_depart_org_code` (`org_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织机构表';

CREATE TABLE `sso_role_permission` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) default NULL COMMENT '角色id',
  `permission_id` varchar(32) default NULL COMMENT '权限id',
  `data_rule_ids` varchar(1000) default NULL,
  PRIMARY KEY  (`id`),
  KEY `index_group_role_per_id` USING BTREE (`role_id`,`permission_id`),
  KEY `index_group_role_id` USING BTREE (`role_id`),
  KEY `index_group_per_id` USING BTREE (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

CREATE TABLE `sso_user_depart` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `user_id` varchar(32) default NULL COMMENT '用户id',
  `dep_id` varchar(32) default NULL COMMENT '部门id',
  PRIMARY KEY  (`ID`),
  KEY `index_depart_groupk_userid` USING BTREE (`user_id`),
  KEY `index_depart_groupkorgid` USING BTREE (`dep_id`),
  KEY `index_depart_groupk_uidanddid` USING BTREE (`user_id`,`dep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `sso_user_role` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `user_id` varchar(32) default NULL COMMENT '用户id',
  `role_id` varchar(32) default NULL COMMENT '角色id',
  PRIMARY KEY  (`id`),
  KEY `index2_groupuu_user_id` USING BTREE (`user_id`),
  KEY `index2_groupuu_ole_id` USING BTREE (`role_id`),
  KEY `index2_groupuu_useridandroleid` USING BTREE (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

CREATE TABLE `sso_permission_data_rule` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `permission_id` varchar(32) default NULL COMMENT '菜单ID',
  `rule_name` varchar(50) default NULL COMMENT '规则名称',
  `rule_column` varchar(50) default NULL COMMENT '字段',
  `rule_conditions` varchar(50) default NULL COMMENT '条件',
  `rule_value` varchar(300) default NULL COMMENT '规则值',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除状态（0，正常，1已删除）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY  (`id`),
  KEY `index_fucntionid` USING BTREE (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `sso_dict` (
  `id` varchar(32) NOT NULL,
  `dict_name` varchar(100) default NULL COMMENT '字典名称',
  `dict_code` varchar(100) default NULL COMMENT '字典编码',
  `description` varchar(255) default NULL COMMENT '描述',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除状态（0，正常，1已删除）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `value_type` int(1) unsigned zerofill default '0' COMMENT '字典类型0为string,1为number,2布尔值',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `indextable_dict_code` USING BTREE (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '数据字典';

CREATE TABLE `sso_dict_item` (
  `id` varchar(32) NOT NULL,
  `dict_id` varchar(32) default NULL COMMENT '字典id',
  `item_text` varchar(100) default NULL COMMENT '字典项文本',
  `item_value` varchar(100) default NULL COMMENT '字典项值',
  `description` varchar(255) default NULL COMMENT '描述',
  `order_num` int(10) default NULL COMMENT '排序',
  `is_enabled` tinyint(4) DEFAULT '1' COMMENT '状态(1：正常  0：未启用 ）',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除状态（0，正常，1已删除）',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY  (`id`),
  KEY `index_table_dict_id` USING BTREE (`dict_id`),
  KEY `index_table_sort_order` USING BTREE (`order_num`),
  KEY `index_table_dict_status` USING BTREE (`is_enabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `sso_user_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `terminal` tinyint(4) NOT NULL DEFAULT '1' COMMENT '终端类型: 1 PC 2 H5',
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `utoken` (`token`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='SSO用户Token';









