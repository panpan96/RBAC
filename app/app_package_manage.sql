/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : app_package_manage

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-03-27 17:14:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `code` varchar(20) DEFAULT '' COMMENT '权限码',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
  `acl_module_id` int(11) NOT NULL DEFAULT '0' COMMENT '权限所在的权限模块id',
  `url` varchar(100) NOT NULL DEFAULT '' COMMENT '请求的url, 可以填正则表达式',
  `type` int(11) NOT NULL DEFAULT '3' COMMENT '类型，1：菜单，2：按钮，3：其他',
  `create_time` timestamp NULL DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，0：冻结',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '权限在当前模块下的顺序，由小到大',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) DEFAULT '' COMMENT '操作者',
  `operate_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) DEFAULT '' COMMENT '最后一个更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_acl
-- ----------------------------
INSERT INTO `sys_acl` VALUES ('1', '20171015095130_26', '进入产品管理界面', '1', '/sys/product/product.page', '1', null, '1', '1', '', 'Admin', '2017-10-15 09:51:30', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('2', '20171015095322_14', '查询产品列表', '1', '/sys/product/page.json', '2', null, '1', '2', '', 'Admin', '2017-10-15 09:53:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('3', '20171015095350_69', '产品上架', '1', '/sys/product/online.json', '2', null, '1', '3', '', 'Admin', '2017-10-15 09:53:51', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('4', '20171015095420_7', '产品下架', '1', '/sys/product/offline.json', '2', null, '1', '4', '', 'Admin', '2017-10-15 10:11:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('5', '20171015212626_63', '进入订单页', '2', '/sys/order/order.page', '1', null, '1', '1', '', 'Admin', '2017-10-15 21:26:27', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('6', '20171015212657_12', '查询订单列表', '2', '/sys/order/list.json', '2', null, '1', '2', '', 'Admin', '2017-10-15 21:26:57', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('7', '20171015212907_36', '进入权限管理页', '7', '/sys/aclModule/acl.page', '1', null, '1', '1', '', 'Admin', '2017-10-15 21:29:07', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('8', '20171015212938_27', '进入角色管理页', '8', '/sys/role/role.page', '1', null, '1', '1', '', 'Admin', '2017-10-16 17:49:38', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('9', '20171015213009_0', '进入用户管理页', '9', '/sys/dept/dept.page', '1', null, '1', '1', '', 'Admin', '2017-10-15 21:30:09', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('10', '20171016230429_8', '进入权限更新记录页面', '11', '/sys/log/log.page', '1', null, '1', '1', '', 'Admin', '2017-10-16 23:04:49', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_acl_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl_module`;
CREATE TABLE `sys_acl_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限模块id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限模块名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级权限模块id',
  `level` varchar(200) NOT NULL DEFAULT '' COMMENT '权限模块层级',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '权限模块在当前层级下的顺序，由小到大',
  `create_time` timestamp NULL DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，0：冻结',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次操作时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新操作者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_acl_module
-- ----------------------------
INSERT INTO `sys_acl_module` VALUES ('1', '产品管理', '0', '0', '1', null, '1', 'product', 'Admin', '2017-10-14 21:13:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES ('2', '订单管理', '0', '0', '2', null, '1', '', 'Admin', '2017-10-14 20:17:11', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES ('3', '公告管理', '0', '0', '3', null, '1', '', 'Admin', '2017-10-14 20:17:21', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES ('4', '出售中产品管理', '1', '0.1', '1', null, '1', '', 'Admin', '2017-10-14 21:13:39', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES ('5', '下架产品管理', '1', '0.1', '2', null, '1', '', 'Admin', '2017-10-14 20:18:02', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES ('6', '权限管理', '0', '0', '4', null, '1', '', 'Admin', '2017-10-15 21:27:37', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES ('7', '权限管理', '6', '0.6', '1', null, '1', '', 'Admin', '2017-10-15 21:27:57', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES ('8', '角色管理', '6', '0.6', '2', null, '1', '', 'Admin', '2017-10-15 21:28:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES ('9', '用户管理', '6', '0.6', '2', null, '1', '', 'Admin', '2017-10-15 21:28:36', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES ('10', '运维管理', '0', '0', '6', null, '1', '', 'Admin', '2017-10-16 23:03:37', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES ('11', '权限更新记录管理', '6', '0.6', '4', null, '1', '', 'Admin', '2017-10-16 23:04:07', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(20) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '角色的类型，1：管理员角色，2：其他',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：可用，0：冻结',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '产品管理员', '1', '1', '', 'Admin', '2017-10-15 12:42:47', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES ('2', '订单管理员', '1', '1', '', 'Admin', '2017-10-15 12:18:59', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES ('3', '公告管理员', '1', '1', '', 'Admin', '2017-10-15 12:19:10', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES ('4', '权限管理员', '1', '1', '', 'Admin', '2017-10-15 21:30:36', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES ('5', '运维管理员', '1', '1', '运维', 'Admin', '2017-10-17 00:23:28', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `acl_id` int(11) NOT NULL COMMENT '权限id',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(200) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_acl
-- ----------------------------
INSERT INTO `sys_role_acl` VALUES ('9', '4', '7', 'Admin', '2017-10-16 23:34:39', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES ('10', '4', '8', 'Admin', '2017-10-16 23:34:39', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES ('11', '4', '9', 'Admin', '2017-10-16 23:34:39', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES ('12', '4', '10', 'Admin', '2017-10-16 23:34:39', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('16', '4', '1', 'Admin', '2017-10-17 00:24:04', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('17', '4', '4', 'Admin', '2017-10-17 00:24:04', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名称',
  `telephone` varchar(13) DEFAULT '' COMMENT '手机号',
  `mail` varchar(20) DEFAULT '' COMMENT '邮箱',
  `password` varchar(40) NOT NULL DEFAULT '' COMMENT '加密后的密码',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，0：删除  2：冻结状态，',
  `create_time` timestamp NULL DEFAULT NULL,
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) DEFAULT '' COMMENT '操作者',
  `operate_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'Admin', '18612344321', 'admin@qq.com', '25D55AD283AA400AF464C76D713C07AD', '1', '2018-02-06 15:11:07', 'admin', 'system', '2018-02-27 15:11:08', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('2', 'Jimin', '13188889999', 'jimin@qq.com', '25D55AD283AA400AF464C76D713C07AD', '1', '2018-02-06 15:11:11', 'jimin.zheng', 'Admin', '2018-02-27 15:11:11', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('3', 'Jimmy', '13812344311', 'jimmy@qq.com', '25D55AD283AA400AF464C76D713C07AD', '1', '2018-02-12 15:11:16', '', 'Admin', '2018-02-27 15:11:17', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES ('4', 'Kate', '13144445555', 'kate@qq.com', '25D55AD283AA400AF464C76D713C07AD', '2', '2018-02-26 14:22:31', 'sss', 'Admin', '2017-10-16 23:02:51', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES ('5', '服务员A', '18677778888', 'service@qq.com', '25D55AD283AA400AF464C76D713C07AD', '0', '2018-02-26 14:22:27', '', 'Admin', '2017-10-17 00:22:15', '0:0:0:0:0:0:0:1');
