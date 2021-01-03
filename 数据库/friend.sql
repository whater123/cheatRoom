/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50724
Source Host           : 106.54.174.38:3310
Source Database       : cheat

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2021-01-02 11:15:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `user_id1` int(11) DEFAULT NULL,
  `user_id2` int(11) DEFAULT NULL,
  `friend_time` datetime NOT NULL COMMENT '加好友时间',
  KEY `user_id1` (`user_id1`),
  KEY `user_id2` (`user_id2`),
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`user_id1`) REFERENCES `user` (`user_id`),
  CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`user_id2`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('15', '3', '2020-12-30 15:27:05');
INSERT INTO `friend` VALUES ('16', '3', '2020-12-31 03:47:57');
INSERT INTO `friend` VALUES ('16', '10', '2020-12-31 03:48:11');
INSERT INTO `friend` VALUES ('3', '17', '2020-12-31 06:26:18');
INSERT INTO `friend` VALUES ('10', '3', '2020-12-31 07:35:40');
INSERT INTO `friend` VALUES ('4', '3', '2020-12-31 08:51:45');
INSERT INTO `friend` VALUES ('3', '18', '2021-01-01 06:06:04');
