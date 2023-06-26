/*
Navicat MySQL Data Transfer

Source Server         : NetData
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : exam_apply

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2023-06-26 10:16:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `questionId` int(11) NOT NULL AUTO_INCREMENT,
  `material` text COLLATE utf8_unicode_ci,
  `question` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `answer` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `score` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `itemA` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `itemB` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `itemC` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `itemD` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `paperId` int(11) DEFAULT NULL,
  PRIMARY KEY (`questionId`),
  KEY `paperId` (`paperId`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', 'Who is your favorite teacher?', '你最喜欢的老师是？', '1', 'A', '2', '宇轩老师', '志强老师', '玉柱老师', '起成老师', null);
