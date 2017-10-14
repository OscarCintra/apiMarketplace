/*

Source Server         : mysqlweb
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : mktplace

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-13 19:07:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for produtos
-- ----------------------------
DROP TABLE IF EXISTS `produtos`;
CREATE TABLE `produtos` (
  `Id` varchar(40) NOT NULL,
  `Ean` varchar(40) DEFAULT NULL,
  `Title` varchar(300) DEFAULT NULL,
  `Brand` varchar(100) DEFAULT NULL,
  `Price` decimal(9,2) DEFAULT NULL,
  `Stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
