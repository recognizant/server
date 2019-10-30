/*
MySQL Data Transfer
Source Host: localhost
Source Database: blind_shopping_ocr
Target Host: localhost
Target Database: blind_shopping_ocr
Date: 14-May-18 9:31:42 AM
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for billing
-- ----------------------------
DROP TABLE IF EXISTS `billing`;
CREATE TABLE `billing` (
  `tid` int(20) NOT NULL,
  `items` varchar(100) NOT NULL,
  `amount` double(20,0) NOT NULL,
  `total` varchar(20) NOT NULL,
  `date1` varchar(40) NOT NULL,
  PRIMARY KEY  (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for current_cart
-- ----------------------------
DROP TABLE IF EXISTS `current_cart`;
CREATE TABLE `current_cart` (
  `item_id` varchar(50) default NULL,
  `items_name` varchar(50) default NULL,
  `qty` varchar(50) default NULL,
  `item_price` varchar(50) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for item_offer
-- ----------------------------
DROP TABLE IF EXISTS `item_offer`;
CREATE TABLE `item_offer` (
  `store_id` varchar(20) NOT NULL,
  `item_id` varchar(20) NOT NULL,
  `item_offer` varchar(20) NOT NULL,
  PRIMARY KEY  (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `user_id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `utype` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for store_item
-- ----------------------------
DROP TABLE IF EXISTS `store_item`;
CREATE TABLE `store_item` (
  `store_id` varchar(20) NOT NULL,
  `item_id` varchar(20) NOT NULL,
  `item_name` varchar(20) NOT NULL,
  `item_desc` varchar(20) NOT NULL,
  `item_manu` varchar(20) NOT NULL,
  `item_price` double(20,0) NOT NULL,
  `item_type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for store_registration
-- ----------------------------
DROP TABLE IF EXISTS `store_registration`;
CREATE TABLE `store_registration` (
  `store_id` varchar(20) NOT NULL,
  `store_name` varchar(20) NOT NULL,
  `store_address` varchar(50) NOT NULL,
  `store_latitude` varchar(50) NOT NULL,
  `store_longitude` varchar(50) NOT NULL,
  PRIMARY KEY  (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `current_cart` VALUES ('2', 'Dairy Milk', '2', '30.0');
INSERT INTO `item_offer` VALUES ('1', '1', '20');
INSERT INTO `login` VALUES ('admin', 'admin', 'admin');
INSERT INTO `login` VALUES ('Yashwanth', 'yash123', 'user');
INSERT INTO `login` VALUES ('Sanjay', 'sanj123', 'user');
INSERT INTO `login` VALUES ('More', 'more123', 'store');
INSERT INTO `login` VALUES ('Sauradeep', 'saurav123', 'user');
INSERT INTO `login` VALUES ('BigBazaar', 'bigb123', 'store');
INSERT INTO `login` VALUES ('4', 'star123', 'store');
INSERT INTO `login` VALUES ('5', '123', 'store');
INSERT INTO `login` VALUES ('1', '123', 'store');
INSERT INTO `login` VALUES ('2', '123', 'store');
INSERT INTO `login` VALUES ('3', '123', 'store');
INSERT INTO `login` VALUES ('4', '123', 'store');
INSERT INTO `login` VALUES ('5', '123', 'store');
INSERT INTO `store_item` VALUES ('1', '1', 'good day biscuit', '500 grams each pack', 'Britannia', '10', 'Butter Biscuit');
INSERT INTO `store_item` VALUES ('1', '2', 'Dairy Milk', '100 grams', 'Cadbury', '30', 'Chocolate');
INSERT INTO `store_item` VALUES ('1', '3', 'Colgate', '250 grams', 'IDA', '40', 'Toothpaste');
INSERT INTO `store_item` VALUES ('1', '4', 'Dove Soap', '100 milli litres', 'Dove', '60', 'Shampoo');
INSERT INTO `store_item` VALUES ('1', '5', 'Carrot Fresh', '500 grams each pack', 'Local', '35', 'Vegetables');
INSERT INTO `store_item` VALUES ('1', '6', 'Apple Fruit', 'One Kilogram', 'Shimla Exports', '110', 'Fruits');
INSERT INTO `store_item` VALUES ('1', '7', 'Milky Mist', '1 litre', 'Hill', '45', 'Curd');
INSERT INTO `store_item` VALUES ('1', '8', 'Butter Cheez', '50 grams', 'Amul', '50', 'Butter');
INSERT INTO `store_registration` VALUES ('1', 'gjggj', 'jgbjkg', '5', '4');
INSERT INTO `store_registration` VALUES ('2', 'jkg', 'jkgkjg', '7', '6');
INSERT INTO `store_registration` VALUES ('3', 'hjgnvnm', 'nmvmn', '7', '6');
INSERT INTO `store_registration` VALUES ('4', 'wd', 'wdfef', '1254', '1452');
INSERT INTO `store_registration` VALUES ('5', 'tt', 'crsdtgugtd', '48467', '5454');
