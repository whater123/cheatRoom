/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50724
Source Host           : 106.54.174.38:3306
Source Database       : cheat

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2020-11-11 00:09:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `head_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'wang', '11', 'admin', null);
INSERT INTO `user` VALUES ('2', 'update', '11', 'admin', null);
INSERT INTO `user` VALUES ('3', '11', '11', 'user', '11.jpg');
INSERT INTO `user` VALUES ('4', '111', '11', 'user', '111.png');
