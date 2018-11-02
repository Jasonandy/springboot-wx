------------------------------------- 项目数据库DDL相关数据  by Jason 2018 ------------------------------------------

-- 格式实例[规范化 方便以后的开发迭代 ]
-- 1.初始实例
-- 2.update更新等操作

-- example:
	-- describe + by author + modifited time 

-----------------------------------------------init Data Start add  by Jason 2018 ----------------------------------


-----------------------------------------------init Data end -----------------------------------------------------------

------------------------------------------------Update/Modifited something by @Author nowDate---------------------------


------------------------------------------------Update/Modifited end  --------------------------------------------------

----------------------------------pms权限相关 start add by Jason 2018-----------------------------------------------

CREATE TABLE `pms_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `creater` varchar(50) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `is_leaf` varchar(20) DEFAULT NULL,
  `level` smallint(6) DEFAULT NULL,
  `parent_id` bigint(20) NOT NULL,
  `target_name` varchar(100) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='菜单表';


CREATE TABLE `pms_menu_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ak_key_2` (`role_id`,`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1062 DEFAULT CHARSET=utf8 COMMENT='权限与角色关联表';

CREATE TABLE `pms_operator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) NOT NULL,
  `creater` varchar(50) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `real_name` varchar(50) NOT NULL,
  `mobile_no` varchar(15) NOT NULL,
  `login_name` varchar(50) NOT NULL,
  `login_pwd` varchar(256) NOT NULL,
  `type` varchar(20) NOT NULL,
  `salt` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ak_key_2` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='操作员表';


CREATE TABLE `pms_operator_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) NOT NULL,
  `creater` varchar(50) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `operator_id` bigint(20) NOT NULL,
  `operator_name` varchar(50) NOT NULL,
  `operate_type` varchar(50) NOT NULL COMMENT '操作类型（1:增加，2:修改，3:删除，4:查询）',
  `ip` varchar(100) NOT NULL,
  `content` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='权限_操作员操作日志表';

CREATE TABLE `pms_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) NOT NULL,
  `creater` varchar(50) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `permission_name` varchar(100) NOT NULL,
  `permission` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ak_key_2` (`permission`),
  KEY `ak_key_3` (`permission_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='权限表';

CREATE TABLE `pms_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `role_code` varchar(20) NOT NULL COMMENT '角色类型（1:超级管理员角色，0:普通操作员角色）',
  `role_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ak_key_2` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `pms_role_operator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) NOT NULL,
  `creater` varchar(50) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) NOT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  `operator_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ak_key_2` (`role_id`,`operator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='操作员与角色关联表';

CREATE TABLE `pms_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` bigint(20) DEFAULT NULL,
  `creater` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(20) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ak_key_2` (`role_id`,`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1080 DEFAULT CHARSET=utf8 COMMENT='权限与角色关联表';

-----------------------------------pms权限相关 end  by Jason 2018 -------------------------------------------------------

