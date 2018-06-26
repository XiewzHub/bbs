/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mobi_test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-26 18:18:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for forum_posts
-- ----------------------------
DROP TABLE IF EXISTS `forum_posts`;
CREATE TABLE `forum_posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_title` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `p_srctext` varchar(10000) COLLATE utf8_bin DEFAULT NULL,
  `p_text` varchar(11000) COLLATE utf8_bin DEFAULT NULL COMMENT '文字内容',
  `p_author` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `p_date` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `comment_num` int(11) DEFAULT '0',
  `view_num` int(11) DEFAULT '0',
  `set_top` int(11) DEFAULT '0',
  `mobile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='帖子';

-- ----------------------------
-- Records of forum_posts
-- ----------------------------
INSERT INTO `forum_posts` VALUES ('89', 'fdafdsfd123123123123', '\\posts\\89\\resources\\text\\srcText.md', '\\posts\\89\\resources\\text\\text.txt', 'admin', '2017-08-24', '0', '5', '0', '5');
INSERT INTO `forum_posts` VALUES ('90', '来个几个垃圾费 ', '\\posts\\90\\resources\\text\\srcText.md', '\\posts\\90\\resources\\text\\text.txt', 'admin', '2017-08-24', '0', '9', '0', '5');
INSERT INTO `forum_posts` VALUES ('91', '15645645612范德萨范德萨范德萨', '\\posts\\91\\resources\\text\\srcText.md', '\\posts\\91\\resources\\text\\text.txt', 'admin', '2017-08-24', '1', '31', '1', '4');
INSERT INTO `forum_posts` VALUES ('92', '地方撒', '\\posts\\92\\resources\\text\\srcText.md', '\\posts\\92\\resources\\text\\text.txt', 'admin', '2018-06-26', '0', '0', '0', null);
INSERT INTO `forum_posts` VALUES ('93', '地方撒', '\\posts\\93\\resources\\text\\srcText.md', '\\posts\\93\\resources\\text\\text.txt', 'admin', '2018-06-26', '0', '5', '0', null);

-- ----------------------------
-- Table structure for forum_user
-- ----------------------------
DROP TABLE IF EXISTS `forum_user`;
CREATE TABLE `forum_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_head` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `user_password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_mail` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  `user_birthday` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `user_sex` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `user_phonenum` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `is_admin` varchar(8) COLLATE utf8_bin DEFAULT 'N' COMMENT '是否管理员',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `forum_user_user_name_uindex` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of forum_user
-- ----------------------------
INSERT INTO `forum_user` VALUES ('2', '/images/head/934aec7fcfa8491bbd2236117b07fcf5.gif', 'admin', 'admin', '123123@qq.com', '2013-01-31', '男', '18163912013', 'Y');
INSERT INTO `forum_user` VALUES ('3', '/images/head/defaultHead.png', '石中王', 'admin', '673275656@qq.com', '1996-06-16', '男', '18163912027', 'N');
INSERT INTO `forum_user` VALUES ('12', '/images/head/defaultHead.png', '王礼赞', 'admin', 'wlz@qq.com', '1995-05-15', '男', '18163911994', 'Y');
INSERT INTO `forum_user` VALUES ('15', '/images/head/defaultHead.png', '黄老师', 'admin', '673275656@qq.com', '2017-05-21', '男', '18163911972', 'N');
INSERT INTO `forum_user` VALUES ('16', '/images/head/defaultHead.png', '谢稳桩', 'admin', '673275656@qq.com', '2014-03-05', '男', '18163911111', 'N');
INSERT INTO `forum_user` VALUES ('17', '/images/head/defaultHead.png', '嘻嘻嘻', '123', '673275656@qq.com', '', '女', '18163941222', 'N');
INSERT INTO `forum_user` VALUES ('18', '/images/head/defaultHead.png', '曾逸', '123456', 'a806625384@qq.com', '', '男', '22222222222', 'N');
INSERT INTO `forum_user` VALUES ('19', '/images/head/defaultHead.png', '哥哥哥', 'admin', 'gegege@qq.com', '', '女', '18163912088', 'N');
INSERT INTO `forum_user` VALUES ('20', '/images/head/defaultHead.png', 'user', '123', '123@qq.com', '1995-03-08', '男', '18163911234', 'N');

-- ----------------------------
-- Table structure for mobile_type
-- ----------------------------
DROP TABLE IF EXISTS `mobile_type`;
CREATE TABLE `mobile_type` (
  `mobi_id` int(11) NOT NULL AUTO_INCREMENT,
  `mobi_brand` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '手机品牌',
  `mobi_series` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '型号系列',
  `mobi_type` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '型号',
  `mobi_img` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `mobi_description` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`mobi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of mobile_type
-- ----------------------------
INSERT INTO `mobile_type` VALUES ('1', '三星', '盖乐世', 'A9', '/images/mobile/509d2d2797114b49a94263d5ed731634.jpg', '骁龙821处理器，超声波指纹识别');
INSERT INTO `mobile_type` VALUES ('2', '华为', 'Ascend', 'P8', '/images/mobile/xiaomi5s.jpg', '骁龙821处理器，超声波指纹识别');
INSERT INTO `mobile_type` VALUES ('3', '三星', '盖乐世', 'S6', '/images/mobile/xiaomi5s.jpg', '骁龙821处理器，超声波指纹识别');
INSERT INTO `mobile_type` VALUES ('4', '华为', 'Ascend', 'P6', '/images/mobile/xiaomi5s.jpg', '骁龙821处理器，超声波指纹识别');
INSERT INTO `mobile_type` VALUES ('5', '中兴', '天机7', 'MAX', '/images/mobile/xiaomi5s.jpg', '骁龙821处理器，超声波指纹识别');
INSERT INTO `mobile_type` VALUES ('6', '索尼', 'L', '39h(Xperia Z1)', '/images/mobile/xiaomi5s.jpg', '骁龙821处理器，超声波指纹识别');
INSERT INTO `mobile_type` VALUES ('7', '索尼', 'L', '36h', '/images/mobile/xiaomi5s.jpg', '骁龙821处理器，超声波指纹识别');
INSERT INTO `mobile_type` VALUES ('8', '华为', '荣耀', '3C', '/images/mobile/xiaomi5s.jpg', '华为荣耀，为你而生');
INSERT INTO `mobile_type` VALUES ('9', '华为', '荣耀', '6', '/images/mobile/xiaomi5s.jpg', '华为荣耀，荣耀荣耀');
INSERT INTO `mobile_type` VALUES ('10', '华为', '荣耀', '7', '/images/mobile/xiaomi5s.jpg', null);
INSERT INTO `mobile_type` VALUES ('11', '华为', '荣耀', '8', '/images/mobile/xiaomi5s.jpg', null);
INSERT INTO `mobile_type` VALUES ('12', '华为', '荣耀', '5', '/images/mobile/xiaomi5s.jpg', '华为荣耀5');
INSERT INTO `mobile_type` VALUES ('13', '华为', '荣耀', '4', '/images/mobile/xiaomi5s.jpg', null);
INSERT INTO `mobile_type` VALUES ('14', '华为', '荣耀', '9S', '/images/mobile/xiaomi5s.jpg', null);
INSERT INTO `mobile_type` VALUES ('15', '华为', '荣耀', '10', '/images/mobile/xiaomi5s.jpg', '华为的手机');
INSERT INTO `mobile_type` VALUES ('16', '华为', '荣耀', '11', '/images/mobile/xiaomi5s.jpg', null);
INSERT INTO `mobile_type` VALUES ('17', '华为', '荣耀', '12', '/images/mobile/xiaomi5s.jpg', null);
INSERT INTO `mobile_type` VALUES ('18', '华为', '荣耀', '13', '/images/mobile/xiaomi5s.jpg', null);
INSERT INTO `mobile_type` VALUES ('19', '华为', '荣耀', '14', '/images/mobile/xiaomi5s.jpg', null);
INSERT INTO `mobile_type` VALUES ('20', '华为', '荣耀', '15', '/images/mobile/xiaomi5s.jpg', null);
INSERT INTO `mobile_type` VALUES ('21', '华为', 'Ascend', 'P1', '/images/mobile/xiaomi5s.jpg', '华为第一代P1，哈哈哈');
INSERT INTO `mobile_type` VALUES ('24', '三星', '盖乐世', 'S8', '/images/mobile/xiaomi5s.jpg', '全面曲面屏幕');
INSERT INTO `mobile_type` VALUES ('25', '小米', '手机', '6', '/images/mobile/xiaomi5s.jpg', '小米发烧了');
INSERT INTO `mobile_type` VALUES ('26', '小米', 'Max', '2', '/images/mobile/xiaomi5s.jpg', '小米Max 2是小米公司发布了一款电池容量达5300毫安的手机，该机将主打大屏大电量。');
INSERT INTO `mobile_type` VALUES ('27', '小米', '手机', '5c', '/images/mobile/xiaomi5s.jpg', '第一代自研芯片就达到了高通625的水平');
INSERT INTO `mobile_type` VALUES ('28', '小米', '手机', '2s', '/images/mobile/xiaomi5s.jpg', '');

-- ----------------------------
-- Table structure for posts_carousel
-- ----------------------------
DROP TABLE IF EXISTS `posts_carousel`;
CREATE TABLE `posts_carousel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `posts_id` int(11) DEFAULT NULL COMMENT '帖子的id',
  `img_address` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '图片url',
  `posts_title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '帖子的标题',
  `posts_url` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '对应帖子的url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='帖子轮播控制';

-- ----------------------------
-- Records of posts_carousel
-- ----------------------------
INSERT INTO `posts_carousel` VALUES ('33', '89', '/images/carousel/2f4ffdd6cfbd42758478ea6a637165e3.jpg', 'fdafdsfd123123123123', '/posts/89/view');

-- ----------------------------
-- Table structure for posts_comment
-- ----------------------------
DROP TABLE IF EXISTS `posts_comment`;
CREATE TABLE `posts_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `posts_id` int(11) NOT NULL COMMENT '所评论的帖子id',
  `comment_user_id` int(11) DEFAULT NULL COMMENT '评论用户的id',
  `comment_text` varchar(6000) COLLATE utf8_bin DEFAULT NULL COMMENT '评论内容',
  `comment_date` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of posts_comment
-- ----------------------------
INSERT INTO `posts_comment` VALUES ('103', '91', '2', 'fdsafsdafsaf', '2018-06-26');
