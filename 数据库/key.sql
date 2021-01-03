/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50724
Source Host           : 106.54.174.38:3310
Source Database       : cheat

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2021-01-02 11:14:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for key
-- ----------------------------
DROP TABLE IF EXISTS `key`;
CREATE TABLE `key` (
  `key_id` int(11) NOT NULL AUTO_INCREMENT,
  `key_create_time` datetime DEFAULT NULL,
  `key_use_time` datetime DEFAULT NULL,
  `key_context` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`key_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of key
-- ----------------------------
INSERT INTO `key` VALUES ('15', '2021-01-01 05:45:49', null, '7d4701ed-b22f-4b53-9db1-184e8f2df387', '0');
INSERT INTO `key` VALUES ('16', '2021-01-01 05:45:50', null, 'd75f2200-1279-49bf-b19d-cde5e209e842', '0');
INSERT INTO `key` VALUES ('17', '2021-01-01 05:45:50', null, 'd94bebc6-786c-4269-881d-2d6d371b5b50', '0');
INSERT INTO `key` VALUES ('18', '2021-01-01 05:45:50', null, '2bfb96d0-9993-4bcb-8238-56baf8dbc60b', '0');
