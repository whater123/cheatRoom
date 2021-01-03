/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50724
Source Host           : 106.54.174.38:3310
Source Database       : cheat

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2021-01-02 11:14:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(60) NOT NULL,
  `user_password` varchar(60) NOT NULL,
  `user_role` varchar(10) NOT NULL,
  `user_head_name` varchar(60) NOT NULL COMMENT '用户头像名',
  `user_introduce` varchar(255) DEFAULT NULL COMMENT '用户个人简介',
  `user_is_vip` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户是否为vip，0为否，1为是',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'wang', '11', 'admin', 'default.jpg', '该用户很懒，啥也没写~', '0');
INSERT INTO `user` VALUES ('3', '11', '11', 'user', '11.jpg', '你看你妈呢？？', '0');
INSERT INTO `user` VALUES ('4', '', '', 'user', '.jpg', '该用户很懒，啥也没写~', '0');
INSERT INTO `user` VALUES ('5', '搜搜', '123', 'user', 'default.jpg', '该用户很懒，啥也没写~', '0');
INSERT INTO `user` VALUES ('6', '123', '123', 'user', 'default.jpg', '该用户很懒，啥也没写~', '0');
INSERT INTO `user` VALUES ('7', '1234', '1234', 'user', '1234.jpg', '该用户很懒，啥也没写~', '0');
INSERT INTO `user` VALUES ('8', '123456', '123456', 'user', '123456.jpg', '该用户很懒，啥也没写~', '0');
INSERT INTO `user` VALUES ('9', 'fatWater', '916247020', 'user', 'fatWater.jpg', '该用户很懒，啥也没写~', '0');
INSERT INTO `user` VALUES ('10', '111', '11', 'user', '111.jpg', '该用户很懒，啥也没写~', '0');
INSERT INTO `user` VALUES ('11', '罗某', '123456789', 'user', '罗某.jpg', '该用户很懒，啥也没写~', '0');
INSERT INTO `user` VALUES ('13', '1', '1', 'user', '1.jpg', '该用户很懒，啥也没写~', '0');
INSERT INTO `user` VALUES ('14', '12', '12', 'user', 'default.jpg', '该用户很懒，啥也没写~', '0');
INSERT INTO `user` VALUES ('15', '333', '333', 'user', '333.jpg', null, '0');
INSERT INTO `user` VALUES ('16', '2', '2', 'user', 'default.jpg', null, '0');
INSERT INTO `user` VALUES ('17', '3', '3', 'user', '3.jpg', null, '0');
INSERT INTO `user` VALUES ('18', '245', '1232', 'user', '245.jpg', null, '0');
