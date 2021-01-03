/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50724
Source Host           : 106.54.174.38:3310
Source Database       : cheat

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2021-01-02 11:14:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_time` datetime NOT NULL,
  `notice_context` varchar(255) DEFAULT NULL,
  `notice_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('14', '2021-01-01 06:12:54', '看得到吗', '摩西摩西');
