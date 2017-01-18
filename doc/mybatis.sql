/*
MySQL Data Transfer
Source Host: localhost
Source Database: mybatis
Target Host: localhost
Target Database: mybatis
Date: 2016/8/31 15:50:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
CREATE TABLE `tb_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(32) NOT NULL COMMENT '商品名称',
  `item_price` float(6,1) NOT NULL COMMENT '商品价格',
  `item_detail` text COMMENT '商品描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
CREATE TABLE `tb_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `order_number` varchar(20) NOT NULL COMMENT '订单号',
  PRIMARY KEY (`id`),
  KEY `FK_orders_1` (`user_id`),
  CONSTRAINT `FK_orders_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_orderdetail
-- ----------------------------
CREATE TABLE `tb_orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(32) DEFAULT NULL COMMENT '订单号',
  `item_id` int(32) DEFAULT NULL COMMENT '商品id',
  `total_price` double(20,0) DEFAULT NULL COMMENT '商品总价',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `FK_orderdetail_1` (`order_id`),
  KEY `FK_orderdetail_2` (`item_id`),
  CONSTRAINT `FK_orderdetail_1` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`id`),
  CONSTRAINT `FK_orderdetail_2` FOREIGN KEY (`item_id`) REFERENCES `tb_item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `age` int(10) DEFAULT NULL COMMENT '年龄',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别，1男性，2女性',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `tb_item` VALUES ('1', 'iPhone 6', '5288.0', '苹果公司新发布的手机产品。');
INSERT INTO `tb_item` VALUES ('2', 'iPhone 6 plus', '6288.0', '苹果公司发布的新大屏手机。');
INSERT INTO `tb_order` VALUES ('1', '1', '20140921001');
INSERT INTO `tb_order` VALUES ('2', '2', '20140921002');
INSERT INTO `tb_order` VALUES ('3', '1', '20140921003');
INSERT INTO `tb_orderdetail` VALUES ('1', '1', '1', '5288', '1');
INSERT INTO `tb_orderdetail` VALUES ('2', '1', '2', '6288', '1');
INSERT INTO `tb_orderdetail` VALUES ('3', '2', '2', '6288', '1');
INSERT INTO `tb_orderdetail` VALUES ('4', '3', '1', '5288', '1');
INSERT INTO `tb_user` VALUES ('1', 'zhangsan', '123456', '张三', '30', '1', '1984-08-08', '2014-09-19 16:56:04', '2014-09-21 11:24:59');
INSERT INTO `tb_user` VALUES ('2', 'lisi', '123456', '李四', '21', '2', '1991-01-01', '2014-09-19 16:56:04', '2014-09-19 16:56:04');
INSERT INTO `tb_user` VALUES ('3', 'wangwu', '123456', '王五', '22', '2', '1989-01-01', '2014-09-19 16:56:04', '2014-09-19 16:56:04');
INSERT INTO `tb_user` VALUES ('4', 'zhangwei', '123456', '张伟', '20', '1', '1988-09-01', '2014-09-19 16:56:04', '2014-09-19 16:56:04');
INSERT INTO `tb_user` VALUES ('5', 'lina', '123456', '李娜', '28', '1', '1985-01-01', '2014-09-19 16:56:04', '2014-09-19 16:56:04');
INSERT INTO `tb_user` VALUES ('6', '二舅', '123456', '李磊', '23', '1', '1988-08-08', '2014-09-20 11:41:15', '2016-08-31 15:46:40');
INSERT INTO `tb_user` VALUES ('19', 'xxxx', '123456', 'xxx', '18', '1', '2016-08-24', '2016-08-24 11:46:52', '2016-08-24 11:46:52');
INSERT INTO `tb_user` VALUES ('22', '成龙', '123456', 'xxx', '18', '1', '2016-08-24', '2016-08-24 22:22:31', '2016-08-24 22:22:31');
INSERT INTO `tb_user` VALUES ('23', '成龙', '123456', 'xxx', '18', '1', '2016-08-25', '2016-08-25 11:00:20', '2016-08-25 11:00:20');
INSERT INTO `tb_user` VALUES ('24', '成龙', '123456', 'xxx', '18', '1', '2016-08-25', '2016-08-25 11:01:00', '2016-08-25 11:01:00');
INSERT INTO `tb_user` VALUES ('38', '周杰伦', null, '', null, null, null, '2016-08-26 11:30:53', '2016-08-26 11:37:36');
INSERT INTO `tb_user` VALUES ('40', '成龙', '123456', 'xxx', '18', '1', '2016-08-26', '2016-08-26 14:57:54', '2016-08-26 14:57:54');
INSERT INTO `tb_user` VALUES ('43', '周焕强', null, '周霸', null, null, null, '2016-08-26 15:29:15', '2016-08-26 15:29:15');
INSERT INTO `tb_user` VALUES ('44', '成龙', '123456', 'xxx', '18', '1', '2016-08-26', '2016-08-26 16:41:14', '2016-08-26 16:41:14');
INSERT INTO `tb_user` VALUES ('45', '成龙', '123456', 'xxx', '18', '1', '2016-08-26', '2016-08-26 16:41:25', '2016-08-26 16:41:25');
INSERT INTO `tb_user` VALUES ('46', '成龙', '123456', 'xxx', '18', '1', '2016-08-26', '2016-08-26 16:45:47', '2016-08-26 16:45:47');
INSERT INTO `tb_user` VALUES ('47', '成龙', '123456', 'xxx', '18', '1', '2016-08-31', '2016-08-31 15:34:50', '2016-08-31 15:34:50');
INSERT INTO `tb_user` VALUES ('48', '成龙', '123456', 'xxx', '18', '1', '2016-08-31', '2016-08-31 15:37:58', '2016-08-31 15:37:58');
INSERT INTO `tb_user` VALUES ('49', '成龙', '123456', 'xxx', '18', '1', '2016-08-31', '2016-08-31 15:46:40', '2016-08-31 15:46:40');
