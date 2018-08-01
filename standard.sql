/*
Navicat MySQL Data Transfer

Source Server         : mypc
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : standard

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-06-04 08:52:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for standard
-- ----------------------------
DROP TABLE IF EXISTS `standard`;
CREATE TABLE `standard` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '使用序列赋值',
  `std_num` varchar(50) NOT NULL,
  `zhname` varchar(40) NOT NULL COMMENT '中文名称',
  `version` varchar(10) NOT NULL COMMENT '版本',
  `keys` varchar(50) NOT NULL COMMENT '关键字/词',
  `release_date` datetime DEFAULT NULL COMMENT '发布日期',
  `impl_date` datetime DEFAULT NULL COMMENT '实施日期',
  `package_path` varchar(100) NOT NULL DEFAULT '' COMMENT '附件路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `std_num` (`std_num`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of standard
-- ----------------------------
INSERT INTO `standard` VALUES ('1', 'GB 6657.1-2018', '玩具安全 第1部分：基本规范', '2018', '规范', '2018-06-03 22:59:22', '2018-06-08 00:00:00', 'b84b1734-b40a-45f1-9558-e8011ea4bc553.jpg');
INSERT INTO `standard` VALUES ('3', 'GB 6657.2-2018', '玩具安全 第三部分：易燃性能', '2017', '易燃', '2018-06-04 08:40:12', '2018-06-03 00:00:00', 'e57b8d5c-c323-472b-914d-e3718e1e340d0.jpg');
INSERT INTO `standard` VALUES ('6', 'GB 6657.4-2018', '玩具安全 第四部分：特定元素的迁移', '2018', '元素', '2018-06-03 21:31:11', '2018-06-02 00:00:00', '5908cb87-9b5c-4119-84a7-f4f5125beb787.jpg');
INSERT INTO `standard` VALUES ('7', 'GB 6657.5-2018', ' 	玩具安全 第五部分：特定元素的迁移', '2018', '特定', '2018-06-03 22:46:03', '2018-06-02 00:00:00', '8d733a23-154d-425b-8564-0534dc1a472b典.jpg');
INSERT INTO `standard` VALUES ('10', 'GB 6657.8-2018', '阿萨德客户', '2018', '客户', '2018-06-03 10:08:15', '2018-06-04 10:15:38', '3e80602c-df33-4584-bd47-dda534f3f3551.jpg');
INSERT INTO `standard` VALUES ('11', 'GB 6657.9-2018', '四季发财年级', '2018', '四季', '2018-06-03 10:10:52', '2018-06-04 00:00:00', '6485d663-9a7c-4ec8-9559-8043e6ae170cd.jpg');
INSERT INTO `standard` VALUES ('15', 'GB 6657.3-2018', '玩具安全', '2018', '玩具', '2018-06-04 08:49:19', '2018-06-04 00:00:00', '96ea4308-c44b-4b8e-b4c8-55212f9c522c0.jpg');
