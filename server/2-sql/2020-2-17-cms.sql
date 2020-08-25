/*
Navicat MySQL Data Transfer

Source Server         : 203.195.246.58_root
Source Server Version : 50725
Source Host           : 203.195.246.58:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-02-17 20:29:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(245) DEFAULT NULL,
  `listStyle` varchar(45) DEFAULT NULL,
  `content` text,
  `source` text,
  `publishTime` varchar(45) DEFAULT NULL,
  `readTimes` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `thumbUp` int(11) DEFAULT NULL,
  `thumbDown` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `music` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_article_user_id_idx` (`user_id`),
  KEY `fk_article_category_id_idx` (`category_id`),
  CONSTRAINT `fk_article_category_id` FOREIGN KEY (`category_id`) REFERENCES `cms_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_article_user_id` FOREIGN KEY (`user_id`) REFERENCES `cms_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_article
-- ----------------------------
INSERT INTO `cms_article` VALUES ('1', '测试', 'style-one', '这是一篇好文章', null, '2018-09-18 21:49:35', '8', '已审核', '1', '1', '7', '1', '1', '1');
INSERT INTO `cms_article` VALUES ('170', 'test01', 'style-one', '111', null, '2020-02-13 16:01:18', '0', '未审核', '0', '0', null, '1', null, null);
INSERT INTO `cms_article` VALUES ('171', 'test02', 'style-one', '222', null, '2020-02-13 16:01:28', '0', '未审核', '0', '0', null, '165', null, null);
INSERT INTO `cms_article` VALUES ('172', 'test03', 'style-one', '333', null, '2020-02-13 16:01:38', '0', '未审核', '0', '0', null, '166', null, null);
INSERT INTO `cms_article` VALUES ('173', 'test04', 'style-two', '444', null, '2020-02-13 16:01:49', '0', '未审核', '0', '0', null, '1', null, null);
INSERT INTO `cms_article` VALUES ('175', 'test05', 'style-one', '555', null, '2020-02-13 16:02:40', '0', '未审核', '0', '0', null, '1', null, null);
INSERT INTO `cms_article` VALUES ('176', 'test06', 'style-one', '666', null, '2020-02-13 16:02:53', '0', '未审核', '0', '0', null, '165', null, null);
INSERT INTO `cms_article` VALUES ('177', 'test04(改)...', 'style-two', '444', null, null, null, null, null, null, null, '167', null, null);

-- ----------------------------
-- Table structure for cms_article_file
-- ----------------------------
DROP TABLE IF EXISTS `cms_article_file`;
CREATE TABLE `cms_article_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) DEFAULT NULL,
  `file_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ca_file_id_idx` (`file_id`),
  KEY `fk_ca_article_id_idx` (`article_id`),
  CONSTRAINT `fk_ca_article_id` FOREIGN KEY (`article_id`) REFERENCES `cms_article` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ca_file_id` FOREIGN KEY (`file_id`) REFERENCES `cms_file` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_article_file
-- ----------------------------
INSERT INTO `cms_article_file` VALUES ('1', '170', 'M00/00/02/rBApFluq-S2AYD1_AAgXWvl26mg018.jpg');

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_category_id_idx` (`parent_id`),
  CONSTRAINT `fk_category_category_id` FOREIGN KEY (`parent_id`) REFERENCES `cms_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_category
-- ----------------------------
INSERT INTO `cms_category` VALUES ('1', '推荐', '点击量高的文章', '1', '165');
INSERT INTO `cms_category` VALUES ('165', '美术', '美术是一门课程', '1', '1');
INSERT INTO `cms_category` VALUES ('166', '123', '321', '1', '1');
INSERT INTO `cms_category` VALUES ('167', 'www', '122', '1', '165');
INSERT INTO `cms_category` VALUES ('172', '111', '11', '1', '165');

-- ----------------------------
-- Table structure for cms_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text,
  `commentTime` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `customer_id` varchar(45) DEFAULT NULL,
  `123` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_article_id_idx` (`article_id`),
  CONSTRAINT `fk_comment_article_id` FOREIGN KEY (`article_id`) REFERENCES `cms_article` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_comment
-- ----------------------------
INSERT INTO `cms_comment` VALUES ('1', '这篇文章写得不错11', '2018-09-19 11:21:25', '通过', '171', '1', null);

-- ----------------------------
-- Table structure for cms_file
-- ----------------------------
DROP TABLE IF EXISTS `cms_file`;
CREATE TABLE `cms_file` (
  `id` varchar(256) NOT NULL,
  `groupName` varchar(256) DEFAULT NULL,
  `uploadTime` varchar(45) DEFAULT NULL,
  `fileSize` bigint(20) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_file
-- ----------------------------
INSERT INTO `cms_file` VALUES ('M00/00/02/rBApFluq-S2AYD1_AAgXWvl26mg018.jpg', 'group1', '2018-09-26 11:12:45', '530266', '018.jpg');
INSERT INTO `cms_file` VALUES ('M00/00/2D/rBAACV5KTQuAblhXAAAdqc_OKKk776.png', 'group1', '2020-02-17 16:21:30', '7593', '1581558576(1).png');
INSERT INTO `cms_file` VALUES ('M00/00/2D/rBAACV5KU5qAT8P8AAGFXo9N_z4604.jpg', 'group1', '2020-02-17 16:49:30', '99678', 'app.jpg');
INSERT INTO `cms_file` VALUES ('M00/00/2D/rBAACV5KUCOAZRBBAAAdqc_OKKk305.png', 'group1', '2020-02-17 16:34:43', '7593', '1581558576(1).png');

-- ----------------------------
-- Table structure for cms_roles
-- ----------------------------
DROP TABLE IF EXISTS `cms_roles`;
CREATE TABLE `cms_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_roles
-- ----------------------------
INSERT INTO `cms_roles` VALUES ('1', '超级管理员');
INSERT INTO `cms_roles` VALUES ('2', '普通用户');

-- ----------------------------
-- Table structure for cms_roles_user
-- ----------------------------
DROP TABLE IF EXISTS `cms_roles_user`;
CREATE TABLE `cms_roles_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT '2',
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  KEY `roles_user_ibfk_2` (`uid`),
  CONSTRAINT `roles_user_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `cms_roles` (`id`),
  CONSTRAINT `roles_user_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `cms_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_roles_user
-- ----------------------------
INSERT INTO `cms_roles_user` VALUES ('8', '2', '7');
INSERT INTO `cms_roles_user` VALUES ('9', '1', '7');
INSERT INTO `cms_roles_user` VALUES ('17', '2', '7');

-- ----------------------------
-- Table structure for cms_user
-- ----------------------------
DROP TABLE IF EXISTS `cms_user`;
CREATE TABLE `cms_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `nickname` varchar(64) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `email` varchar(64) DEFAULT NULL,
  `userface` varchar(255) DEFAULT NULL,
  `regTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_user
-- ----------------------------
INSERT INTO `cms_user` VALUES ('7', 'sang', '江南一点雨', '202cb962ac59075b964b07152d234b70', '1', 'sang123@qq.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', '2017-12-21 13:30:29');
INSERT INTO `cms_user` VALUES ('52', 'yangff', 'yff', 'wodemiam', '1', '2147@qq.com', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4090061760,3566002114&fm=26&gp=0.jpg', '2017-12-21 13:30:29');
INSERT INTO `cms_user` VALUES ('76', 'xuge', 'geer', 'xugedemima', '0', '1234@qq.com', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4090061760,3566002114&fm=26&gp=0.jpg', '2017-12-21 13:30:29');
INSERT INTO `cms_user` VALUES ('77', '111', '11', '123', '0', '111', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4090061760,3566002114&fm=26&gp=0.jpg', '2017-12-21 13:30:29');
INSERT INTO `cms_user` VALUES ('78', '111', '11', '123', '1', '111', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4090061760,3566002114&fm=26&gp=0.jpg', '2017-12-21 13:30:29');
INSERT INTO `cms_user` VALUES ('79', '111', '11', '123', '1', '111', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4090061760,3566002114&fm=26&gp=0.jpg', '2017-12-21 13:30:29');
INSERT INTO `cms_user` VALUES ('80', '111', '11', '123', '0', '111', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4090061760,3566002114&fm=26&gp=0.jpg', '2017-12-21 13:30:29');
INSERT INTO `cms_user` VALUES ('82', '111', '11', '123', '0', '111', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4090061760,3566002114&fm=26&gp=0.jpg', '2017-12-21 13:30:29');
INSERT INTO `cms_user` VALUES ('83', '111', '11', '123', '0', '111', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4090061760,3566002114&fm=26&gp=0.jpg', '2017-12-21 13:30:29');
INSERT INTO `cms_user` VALUES ('84', '111', '11', '111', '0', '111', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4090061760,3566002114&fm=26&gp=0.jpg', '2017-12-21 13:30:29');
INSERT INTO `cms_user` VALUES ('85', '程雅琳', '程程', '123456', '0', '448824416@qq.com', null, '2020-02-10 14:38:25');
INSERT INTO `cms_user` VALUES ('86', '赵丽颖', '照照', '123456', '0', '1234456@briup.combriup.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581326895641&di=dda42443eb3e835a91f0398f00b65909&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F35%2F34%2F19300001295750130986345801104.jpg', '2020-02-10 14:40:55');
