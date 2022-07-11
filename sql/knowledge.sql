/*
 Navicat Premium Data Transfer

 Source Server         : 192.172.0.205_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 192.172.0.205:3306
 Source Schema         : knowledge_class

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 21/06/2022 14:15:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class_appointment
-- ----------------------------
DROP DATABASE IF EXISTS `knowledge_class`;
CREATE DATABASE `knowledge_class`
    DEFAULT CHARACTER SET utf8mb4   -- 乱码问题
    DEFAULT COLLATE utf8mb4_bin     -- 英文大小写不敏感问题
;
USE `knowledge_class`;

-- ----------------------------
-- Table structure for class_appointment
-- ----------------------------
DROP TABLE IF EXISTS `class_appointment`;
CREATE TABLE `class_appointment`  (
                                      `id` bigint(0) NOT NULL,
                                      `crlid` bigint(0) DEFAULT NULL,
                                      `userid` bigint(0) DEFAULT NULL,
                                      `appointment` datetime(0) DEFAULT NULL,
                                      `estarttime` datetime(0) DEFAULT NULL,
                                      `sendornot` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                      `userstatus` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_appointment
-- ----------------------------
INSERT INTO `class_appointment` VALUES (1, 1, 1, '2022-06-04 15:10:24', '2022-06-15 15:10:29', 'n', 'y');
INSERT INTO `class_appointment` VALUES (2, 1, 1, '2022-06-13 19:05:40', '2022-06-30 19:05:43', 'n', 'y');
INSERT INTO `class_appointment` VALUES (3, 1, 1, '2022-06-14 10:33:36', '2022-06-14 11:03:41', 'n', 'y');
INSERT INTO `class_appointment` VALUES (4, 1, 1, '2022-06-14 15:24:09', '2022-06-14 16:55:13', 'n', 'y');
INSERT INTO `class_appointment` VALUES (5, 1, 2, '2022-06-14 15:24:29', '2022-06-14 16:55:13', 'n', 'y');
INSERT INTO `class_appointment` VALUES (6, 1, 1, '2022-06-14 19:07:38', '2022-06-14 20:05:41', 'n', 'y');
INSERT INTO `class_appointment` VALUES (7, 1, 2, '2022-06-14 20:05:39', '2022-06-14 20:05:41', 'n', 'y');
INSERT INTO `class_appointment` VALUES (8, 2, 1, '2022-06-14 20:05:50', '2022-06-14 20:05:52', 'n', 'y');
INSERT INTO `class_appointment` VALUES (9, 1, 1, '2022-06-15 15:47:10', '2022-06-15 19:05:30', 'y', 'y');
INSERT INTO `class_appointment` VALUES (10, 1, 2, '2022-06-15 15:47:36', '2022-06-15 19:05:30', 'y', 'y');
INSERT INTO `class_appointment` VALUES (11, 2, 1, '2022-06-15 15:48:01', '2022-06-15 19:05:30', 'y', 'y');
INSERT INTO `class_appointment` VALUES (12, 1, 3, '2022-06-15 15:48:34', '2022-06-15 19:05:30', 'n', 'y');
INSERT INTO `class_appointment` VALUES (13, 2, 3, '2022-06-15 09:38:03', '2022-06-15 19:05:30', 'y', 'y');
INSERT INTO `class_appointment` VALUES (14, 1, 2, '2022-06-16 11:21:19', '2022-06-16 13:20:00', 'y', 'y');
INSERT INTO `class_appointment` VALUES (15, 2, 2, '2022-06-16 11:21:40', '2022-06-16 13:20:00', 'y', 'y');
INSERT INTO `class_appointment` VALUES (16, 1, 1, '2022-06-16 11:21:56', '2022-06-16 13:20:00', 'y', 'y');

-- ----------------------------
-- Table structure for class_classification
-- ----------------------------
DROP TABLE IF EXISTS `class_classification`;
CREATE TABLE `class_classification`  (
                                         `id` bigint(0) NOT NULL AUTO_INCREMENT,
                                         `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                         `parentid` bigint(0) DEFAULT NULL,
                                         `status` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_coupon_msg
-- ----------------------------
DROP TABLE IF EXISTS `class_coupon_msg`;
CREATE TABLE `class_coupon_msg`  (
                                     `id` bigint(0) NOT NULL,
                                     `crlid` bigint(0) DEFAULT NULL,
                                     `userid` bigint(0) DEFAULT NULL,
                                     `cpid` bigint(0) DEFAULT NULL,
                                     `nums` bigint(0) DEFAULT NULL,
                                     `type` int(0) DEFAULT NULL,
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_coupon_msg
-- ----------------------------
INSERT INTO `class_coupon_msg` VALUES (1, 1, 1, 1, 1, NULL);

-- ----------------------------
-- Table structure for class_currency_msg
-- ----------------------------
DROP TABLE IF EXISTS `class_currency_msg`;
CREATE TABLE `class_currency_msg`  (
                                       `id` bigint(0) NOT NULL,
                                       `img` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                                       `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                                       `ccid` bigint(0) NOT NULL,
                                       `describes` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                       `status` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                       `cstatus` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                       `address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                       `audition` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                       `enclosure` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                       `ordernum` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                       `privce` double DEFAULT NULL,
                                       `userid` bigint(0) default null,
                                       `stock` bigint(0) DEFAULT NULL,
                                       `groundingtime` datetime(0) DEFAULT NULL,
                                       `offshelftime` datetime(0) DEFAULT NULL,
                                       `starttime` datetime(0) DEFAULT NULL,
                                       `endtime` datetime(0) DEFAULT NULL,
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_currency_msg
-- ----------------------------
INSERT INTO `class_currency_msg` VALUES (1, '1', '1', 1, '1', '2', '2', '1', '1', '1', '1', 1, 1, '2022-06-22 12:19:36', '2022-06-22 12:19:39', '2022-06-22 12:19:44', '2022-06-22 12:19:46');

-- ----------------------------
-- Table structure for class_currency_watch
-- ----------------------------
DROP TABLE IF EXISTS `class_currency_watch`;
CREATE TABLE `class_currency_watch`  (
                                         `id` bigint(0) NOT NULL,
                                         `crlid` bigint(0) DEFAULT NULL,
                                         `userid` bigint(0) DEFAULT NULL,
                                         `firsttime` datetime(0) DEFAULT NULL,
                                         `quittime` datetime(0) DEFAULT NULL,
                                         `watchtime` bigint(0) DEFAULT NULL,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_currency_watch
-- ----------------------------
INSERT INTO `class_currency_watch` VALUES (1, 1, 1, '2022-06-14 16:58:22', '2022-06-15 16:58:26', 1);
INSERT INTO `class_currency_watch` VALUES (324086668485197824, 1, 2, '2022-06-13 07:24:43', '2022-06-13 07:25:08', 24720);
INSERT INTO `class_currency_watch` VALUES (324098795388272640, 1, 2, '2022-06-13 08:12:54', '2022-06-13 08:13:02', 8381);
INSERT INTO `class_currency_watch` VALUES (324491018718150656, 1, 2, '2022-06-14 10:11:28', '2022-06-14 10:12:35', 67218);
INSERT INTO `class_currency_watch` VALUES (324492630341713920, 1, 2, '2022-06-14 10:17:52', '2022-06-14 10:17:58', 6149);
INSERT INTO `class_currency_watch` VALUES (325163541059338240, 1, 1, '2022-06-16 06:43:49', '2022-06-16 06:47:35', 225721);

-- ----------------------------
-- Table structure for class_effective_duration
-- ----------------------------
DROP TABLE IF EXISTS `class_effective_duration`;
CREATE TABLE `class_effective_duration`  (
                                             `id` bigint(0) NOT NULL,
                                             `time` bigint(0) DEFAULT NULL,
                                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_effective_duration
-- ----------------------------
INSERT INTO `class_effective_duration` VALUES (1, 50000);

-- ----------------------------
-- Table structure for class_email_configure_info
-- ----------------------------
DROP TABLE IF EXISTS `class_email_configure_info`;
CREATE TABLE `class_email_configure_info`  (
                                               `id` bigint(0) NOT NULL,
                                               `mailhost` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                               `mailport` int(0) DEFAULT NULL,
                                               `mailusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                               `mailpassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                               `mailprotocol` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                               `mailfrom` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_email_configure_info
-- ----------------------------
INSERT INTO `class_email_configure_info` VALUES (1, 'smtp.qq.com', 465, '251100172@qq.com', 'uebakqdhsifgbgca', 'smtps', '251100172@qq.com');

-- ----------------------------
-- Table structure for class_likes_msg
-- ----------------------------
DROP TABLE IF EXISTS `class_likes_msg`;
CREATE TABLE `class_likes_msg`  (
                                    `id` bigint(0) NOT NULL,
                                    `crlid` bigint(0) DEFAULT NULL,
                                    `userid` bigint(0) DEFAULT NULL,
                                    `likes` int(0) DEFAULT NULL,
                                    `type` int(0) DEFAULT NULL,
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_likes_msg
-- ----------------------------
INSERT INTO `class_likes_msg` VALUES (1, 1, 1, 1, 1);

-- ----------------------------
-- Table structure for class_live_msg
-- ----------------------------
DROP TABLE IF EXISTS `class_live_msg`;
CREATE TABLE `class_live_msg`  (
                                   `id` bigint(0) NOT NULL,
                                   `crlid` bigint(0) DEFAULT NULL,
                                   `userid` bigint(0) DEFAULT NULL,
                                   `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                   `msg` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                   `type` int(0) DEFAULT NULL,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_live_msg
-- ----------------------------
INSERT INTO `class_live_msg` VALUES (1, 1, 1, '1', '1', NULL);

-- ----------------------------
-- Table structure for class_live_watch
-- ----------------------------
DROP TABLE IF EXISTS `class_live_watch`;
CREATE TABLE `class_live_watch`  (
                                     `id` bigint(0) NOT NULL,
                                     `crlid` bigint(0) DEFAULT NULL,
                                     `userid` bigint(0) DEFAULT NULL,
                                     `firsttime` datetime(0) DEFAULT NULL,
                                     `quittime` datetime(0) DEFAULT NULL,
                                     `watchtime` bigint(0) DEFAULT NULL,
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_live_watch
-- ----------------------------
INSERT INTO `class_live_watch` VALUES (1, 1, 1, '2022-06-16 09:39:01', '2022-06-17 09:39:06', 1);
INSERT INTO `class_live_watch` VALUES (2, 1, 1, '2022-06-16 11:58:15', '2022-06-22 11:58:24', 555555);
INSERT INTO `class_live_watch` VALUES (3, 2, 1, '2022-06-16 11:58:41', '2022-06-23 11:58:49', 55555);
INSERT INTO `class_live_watch` VALUES (4, 1, 2, '2022-06-14 12:23:18', '2022-06-29 12:23:23', 100000);
INSERT INTO `class_live_watch` VALUES (5, 2, 3, '2022-06-16 09:40:18', '2022-06-30 09:40:21', 1000000);
INSERT INTO `class_live_watch` VALUES (6, 1, 4, '2022-06-16 09:42:51', '2022-06-29 09:42:54', 1);
INSERT INTO `class_live_watch` VALUES (7, 1, 3, '2022-06-17 10:37:30', '2022-06-17 10:37:34', 342424234);
INSERT INTO `class_live_watch` VALUES (324074519117430784, 1, 1, '2022-06-13 06:36:26', '2022-06-13 06:37:48', 82133);
INSERT INTO `class_live_watch` VALUES (325473265356115968, 2, 1, '2022-06-17 11:14:33', '2022-06-17 11:16:11', 97523);

-- ----------------------------
-- Table structure for class_live_watch_totle
-- ----------------------------
DROP TABLE IF EXISTS `class_live_watch_totle`;
CREATE TABLE `class_live_watch_totle`  (
                                           `id` bigint(0) NOT NULL,
                                           `crlid` bigint(0) DEFAULT NULL,
                                           `maxnum` bigint(0) DEFAULT NULL,
                                           `effectiveviewers` bigint(0) DEFAULT NULL,
                                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_live_watch_totle
-- ----------------------------
INSERT INTO `class_live_watch_totle` VALUES (1, 1, 6, 3);
INSERT INTO `class_live_watch_totle` VALUES (325463357731864576, 2, 3, 2);

-- ----------------------------
-- Table structure for class_message_duplication
-- ----------------------------
DROP TABLE IF EXISTS `class_message_duplication`;
CREATE TABLE `class_message_duplication`  (
                                              `id` bigint(0) NOT NULL,
                                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_record_live
-- ----------------------------
DROP TABLE IF EXISTS `class_record_live`;
CREATE TABLE `class_record_live`  (
                                      `id` bigint(0) NOT NULL,
                                      `userid` bigint(0) NOT NULL,
                                      `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                                      `img` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                                      `live_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                      `num` bigint(0) DEFAULT NULL,
                                      `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                      `starttime` datetime(0) DEFAULT NULL,
                                      `endtime` datetime(0) DEFAULT NULL,
                                      `estarttime` datetime(0) DEFAULT NULL,
                                      `ordernum` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_record_live
-- ----------------------------
INSERT INTO `class_record_live` VALUES (1, 1, '1', '1', '1', 1, '1', '2022-06-22 12:08:05', '2022-06-22 12:08:10', '2022-06-22 12:08:12', '1');

SET FOREIGN_KEY_CHECKS = 1;



-- message service sql
DROP DATABASE IF EXISTS knowledge_message;
CREATE DATABASE knowledge_message
    DEFAULT CHARACTER SET utf8mb4 -- 乱码问题
    DEFAULT COLLATE utf8mb4_bin -- 英文大小写不敏感问题
;
USE knowledge_message;

-- set foreign_key_checks = off;

DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message`  (
                               `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '消息主键',
                               `exchange` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '交换机',
                               `routing_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '路由',
                               `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '消息内容',
                               `retry_count` tinyint(0) DEFAULT 10 COMMENT '重试次数 (默认10次)',
                               `contact_mail` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息发送失败时联系地址',
                               `status` tinyint(0) DEFAULT NULL COMMENT '消息状态 (0: 已失败, 1: 发送中, 2: 已发送, 3:已接收)',
                               `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
                               `update_time` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 326637732603756545 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;





-- order center sql
DROP DATABASE IF EXISTS knowledge_order_center;
CREATE DATABASE knowledge_order_center
    DEFAULT CHARACTER SET utf8mb4 -- 乱码问题
    DEFAULT COLLATE utf8mb4_bin -- 英文大小写不敏感问题
;
USE knowledge_order_center;

-- set foreign_key_checks = off;

-- ----------------------------
-- Table structure for tb_cart_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart_item`;
CREATE TABLE `tb_cart_item`  (
                                 `id` bigint(0) NOT NULL,
                                 `product_id` bigint(0) DEFAULT NULL COMMENT '商品的id',
                                 `product_sku_id` bigint(0) DEFAULT NULL COMMENT '商品sku的id',
                                 `member_id` bigint(0) DEFAULT NULL COMMENT '会员id',
                                 `quantity` int(0) DEFAULT NULL COMMENT '数量',
                                 `price` decimal(10, 2) DEFAULT NULL COMMENT '添加到购物车时的价格',
                                 `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
                                 `modify_date` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                                 `delete_status` int(0) DEFAULT 0 COMMENT '是否删除',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 UNIQUE INDEX `product_sku_id`(`product_sku_id`, `member_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
                             `id` bigint(0) NOT NULL COMMENT '订单id',
                             `member_id` bigint(0) NOT NULL COMMENT '会员id',
                             `member_username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户帐号',
                             `order_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单编号',
                             `coupon_id` bigint(0) DEFAULT NULL COMMENT '优惠券id',
                             `create_time` datetime(0) DEFAULT NULL COMMENT '提交时间',
                             `total_amount` decimal(10, 2) DEFAULT NULL COMMENT '订单总金额',
                             `freight_amount` decimal(10, 2) DEFAULT NULL COMMENT '运费金额',
                             `promotion_amount` decimal(10, 2) DEFAULT NULL COMMENT '促销优化金额（促销价、满减、阶梯价）',
                             `integration_amount` decimal(10, 2) DEFAULT NULL COMMENT '积分抵扣金额',
                             `coupon_amount` decimal(10, 2) DEFAULT NULL COMMENT '优惠券抵扣金额',
                             `discount_amount` decimal(10, 2) DEFAULT NULL COMMENT '管理员后台调整订单使用的折扣金额',
                             `acatual_pay_amount` decimal(10, 2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
                             `pay_type` int(0) DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
                             `source_type` int(0) DEFAULT NULL COMMENT '订单来源：0->PC订单；1->app订单',
                             `status` int(0) DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->待收货；3->交易成功；4->已关闭；5->售后中',
                             `order_type` int(0) DEFAULT NULL COMMENT '订单类型：0->正常订单；1->秒杀订单',
                             `delivery_company` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流公司(配送方式)',
                             `delivery_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '物流单号',
                             `bill_type` int(0) DEFAULT NULL COMMENT '发票类型：0->不开发票；1->电子发票；2->纸质发票',
                             `bill_header` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发票抬头',
                             `bill_content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发票内容',
                             `bill_receiver_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收票人电话',
                             `bill_receiver_email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收票人邮箱',
                             `receiver_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人姓名',
                             `receiver_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人电话',
                             `receiver_post_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人邮编',
                             `receiver_province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省份/直辖市',
                             `receiver_city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '城市',
                             `receiver_region` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区',
                             `receiver_detail_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '详细地址',
                             `note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单备注',
                             `confirm_status` int(0) DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
                             `use_integration` int(0) DEFAULT NULL COMMENT '下单时使用的积分',
                             `payment_time` datetime(0) DEFAULT NULL COMMENT '支付时间',
                             `delivery_time` datetime(0) DEFAULT NULL COMMENT '发货时间',
                             `receive_time` datetime(0) DEFAULT NULL COMMENT '确认收货时间',
                             `comment_time` datetime(0) DEFAULT NULL COMMENT '评价时间',
                             `modify_time` timestamp(0) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item`  (
                                  `id` bigint(0) NOT NULL,
                                  `order_id` bigint(0) DEFAULT NULL COMMENT '订单id',
                                  `order_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单编号',
                                  `product_sku_id` bigint(0) DEFAULT NULL COMMENT '商品sku id',
                                  `product_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品图片',
                                  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品名称',
                                  `product_price` decimal(10, 2) DEFAULT NULL COMMENT '销售价格',
                                  `product_quantity` int(0) DEFAULT NULL COMMENT '购买数量',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_operate_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_operate_history`;
CREATE TABLE `tb_order_operate_history`  (
                                             `id` bigint(0) NOT NULL,
                                             `order_id` bigint(0) DEFAULT NULL COMMENT '订单id',
                                             `operate_man` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人：用户；系统；后台管理员',
                                             `create_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
                                             `operation_type` int(0) DEFAULT NULL COMMENT '操作类型：0->关闭订单; 1->付款操作; 2->发货操作; 3->收货操作; 4->完成订单; 5->申请售后; ',
                                             `note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_setting
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_setting`;
CREATE TABLE `tb_order_setting`  (
                                     `id` bigint(0) NOT NULL,
                                     `flash_order_overtime` int(0) DEFAULT NULL COMMENT '秒杀订单超时关闭时间(分)',
                                     `normal_order_overtime` int(0) DEFAULT NULL COMMENT '正常订单超时时间(分)',
                                     `confirm_overtime` int(0) DEFAULT NULL COMMENT '发货后自动确认收货时间（天）',
                                     `finish_overtime` int(0) DEFAULT NULL COMMENT '自动完成交易时间，不能申请售后（天）',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;



/*
Navicat MySQL Data Transfer

Source Server         : myserver
Source Server Version : 80027
Source Host           : 43.142.16.79:3306
Source Database       : knowledge_payment_system

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2022-06-21 14:51:05
*/

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `knowledge_payment_system`;
CREATE DATABASE `knowledge_payment_system`
    DEFAULT CHARACTER SET utf8mb4   -- 乱码问题
    DEFAULT COLLATE utf8mb4_bin     -- 英文大小写不敏感问题
;
USE `knowledge_payment_system`;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
                          `id` bigint NOT NULL COMMENT '主键',
                          `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
                          `img` varchar(255) DEFAULT NULL COMMENT 'banner图片',
                          `orderno` bigint DEFAULT NULL COMMENT '排序号',
                          `link` varchar(255) DEFAULT NULL COMMENT 'banner链接',
                          `status` varchar(255) DEFAULT NULL COMMENT 'banner状态, 是否可用',
                          `author` varchar(255) DEFAULT NULL COMMENT 'banner添加人',
                          `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                          `is_deleted` varchar(255) DEFAULT NULL COMMENT '是否删除',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for employeeinfo
-- ----------------------------
DROP TABLE IF EXISTS `employeeinfo`;
CREATE TABLE `employeeinfo` (
                                `id` bigint unsigned NOT NULL,
                                `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                `uid` bigint DEFAULT NULL,
                                `tel` varchar(255) DEFAULT NULL,
                                `img` varchar(255) DEFAULT NULL,
                                `vx` varchar(255) DEFAULT NULL,
                                `qq` varchar(255) DEFAULT NULL,
                                `email` varchar(255) DEFAULT NULL,
                                `profile` varchar(255) DEFAULT NULL,
                                `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '0 表示在职, 1 表示已离职',
                                `entrytime` datetime DEFAULT NULL,
                                `sex` varchar(255) DEFAULT NULL,
                                `birthday` datetime DEFAULT NULL,
                                `author` varchar(255) DEFAULT NULL,
                                `is_deleted` varchar(255) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for goods_brand
-- ----------------------------
DROP TABLE IF EXISTS `goods_brand`;
CREATE TABLE `goods_brand` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `name` varchar(255) DEFAULT NULL COMMENT '名称',
                               `image` varchar(255) DEFAULT NULL COMMENT '图片网址',
                               `is_deleted` varchar(255) DEFAULT NULL COMMENT '逻辑删除',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for goods_category
-- ----------------------------
DROP TABLE IF EXISTS `goods_category`;
CREATE TABLE `goods_category` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `spg_id` bigint DEFAULT NULL COMMENT '品类编号',
                                  `name` varchar(255) DEFAULT NULL COMMENT '品类名称',
                                  `is_deleted` varchar(255) DEFAULT NULL COMMENT '| 逻辑删除 |',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for goods_category_params
-- ----------------------------
DROP TABLE IF EXISTS `goods_category_params`;
CREATE TABLE `goods_category_params` (
                                         `spc_id` bigint DEFAULT NULL COMMENT '商品品类ID',
                                         `spp_id` bigint DEFAULT NULL COMMENT '商品参数表ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for goods_params
-- ----------------------------
DROP TABLE IF EXISTS `goods_params`;
CREATE TABLE `goods_params` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `spp_id` bigint DEFAULT NULL COMMENT '参数编号',
                                `name` varchar(255) DEFAULT NULL COMMENT '参数名称',
                                `is_deleted` varchar(255) DEFAULT NULL COMMENT '逻辑删除',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for goods_params_value
-- ----------------------------
DROP TABLE IF EXISTS `goods_params_value`;
CREATE TABLE `goods_params_value` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `spp_id` bigint DEFAULT NULL COMMENT '参数编号',
                                      `value` varchar(255) DEFAULT NULL COMMENT '参数实际值',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=324512369459765249 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for goods_sku
-- ----------------------------
DROP TABLE IF EXISTS `goods_sku`;
CREATE TABLE `goods_sku` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `title` varchar(255) DEFAULT NULL COMMENT '商品名称',
                             `inventory` bigint DEFAULT NULL COMMENT '库存',
                             `images` varchar(255) DEFAULT NULL COMMENT '商品封面',
                             `description` varchar(255) DEFAULT NULL COMMENT '商品描述',
                             `gooddetails` varchar(255) DEFAULT NULL COMMENT '商品详情',
                             `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
                             `marketprice` decimal(10,0) DEFAULT NULL COMMENT '划线价格',
                             `orderno` int DEFAULT NULL COMMENT '排序号',
                             `minnum` int DEFAULT NULL COMMENT '最少购买数量',
                             `maxnum` int DEFAULT NULL COMMENT '最多购买数量',
                             `saleable` varchar(255) DEFAULT NULL COMMENT '是否上架(三种状态 立即上架 0 , 暂不上架 1, 定时上架 2)',
                             `valid` varchar(255) DEFAULT NULL COMMENT '是否有效',
                             `create_time` datetime DEFAULT NULL COMMENT '添加时间',
                             `last_update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                             `is_deleted` varchar(255) DEFAULT NULL COMMENT '逻辑删除',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=325471679979364353 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for goods_sku_slideShowingImgs
-- ----------------------------
DROP TABLE IF EXISTS `goods_sku_slideShowingImgs`;
CREATE TABLE `goods_sku_slideShowingImgs` (
                                              `sku_id` bigint DEFAULT NULL,
                                              `imgurl` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for goods_sku_spu
-- ----------------------------
DROP TABLE IF EXISTS `goods_sku_spu`;
CREATE TABLE `goods_sku_spu` (
                                 `sku_id` bigint DEFAULT NULL,
                                 `spu_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for goods_sku_value
-- ----------------------------
DROP TABLE IF EXISTS `goods_sku_value`;
CREATE TABLE `goods_sku_value` (
                                   `sku_id` bigint DEFAULT NULL COMMENT 'sku表ID',
                                   `value_id` bigint DEFAULT NULL COMMENT '| 参数值表ID |',
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=325471679979364356 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for goods_sorting
-- ----------------------------
DROP TABLE IF EXISTS `goods_sorting`;
CREATE TABLE `goods_sorting` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `name` varchar(255) DEFAULT NULL COMMENT '分类名称',
                                 `parent_id` bigint DEFAULT NULL COMMENT '上级分类ID',
                                 `if_parent` varchar(255) DEFAULT NULL COMMENT '是否含有下级分类',
                                 `is_deleted` varchar(255) DEFAULT NULL COMMENT '逻辑删除',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for goods_sorting_grand
-- ----------------------------
DROP TABLE IF EXISTS `goods_sorting_grand`;
CREATE TABLE `goods_sorting_grand` (
                                       `sorting_id` bigint DEFAULT NULL COMMENT '分类ID',
                                       `brand_id` bigint DEFAULT NULL COMMENT '品牌ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for goods_spu
-- ----------------------------
DROP TABLE IF EXISTS `goods_spu`;
CREATE TABLE `goods_spu` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `title` varchar(255) DEFAULT NULL COMMENT '标题',
                             `sub_title` varchar(255) DEFAULT NULL COMMENT '副标题',
                             `category_id` int DEFAULT NULL COMMENT '分类ID',
                             `spg_id` int DEFAULT NULL COMMENT '品类ID',
                             `saleable` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '是否上架',
                             `valid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '是否有效',
                             `create_time` datetime DEFAULT NULL COMMENT '添加时间',
                             `last_update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                             `is_deleted` varchar(255) DEFAULT NULL COMMENT '逻辑删除',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `code` varchar(255) DEFAULT NULL COMMENT '权限api(如:good/add)',
                              `name` varchar(255) DEFAULT NULL COMMENT '权限中文名',
                              `link` varchar(255) DEFAULT NULL COMMENT '前端组件名',
                              `parentid` int DEFAULT NULL COMMENT '父组件的ID',
                              `type` varchar(255) DEFAULT NULL COMMENT '类型',
                              `status` varchar(255) DEFAULT NULL,
                              `percode` varchar(255) DEFAULT NULL COMMENT '权限代码',
                              `icon` varchar(255) DEFAULT NULL COMMENT '前端组件icon',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `role` varchar(255) DEFAULT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for role_perms
-- ----------------------------
DROP TABLE IF EXISTS `role_perms`;
CREATE TABLE `role_perms` (
                              `roleid` int NOT NULL,
                              `permid` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for timing_sale
-- ----------------------------
DROP TABLE IF EXISTS `timing_sale`;
CREATE TABLE `timing_sale` (
                               `id` bigint NOT NULL COMMENT '主键',
                               `sku_id` bigint DEFAULT NULL COMMENT 'skuID',
                               `start_time` datetime DEFAULT NULL COMMENT '上架开始时间',
                               `end_time` datetime DEFAULT NULL COMMENT '| 上架结束时间 |',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
                              `uid` bigint NOT NULL,
                              `account` varchar(20) DEFAULT NULL,
                              `password` varchar(255) DEFAULT NULL,
                              `status` varchar(10) DEFAULT NULL COMMENT '0 正常, 1 锁定不能登录, 2 已删除',
                              `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
                              `register_time` datetime DEFAULT NULL COMMENT '注册时间',
                              PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
                             `uid` bigint NOT NULL,
                             `roleid` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : 192.172.0.205_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : 192.172.0.205:3306
 Source Schema         : knowledge_users

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 21/06/2022 14:24:55
*/

DROP DATABASE IF EXISTS `knowledge_users`;
CREATE DATABASE `knowledge_users`
    DEFAULT CHARACTER SET utf8mb4 -- 乱码问题
    DEFAULT COLLATE utf8mb4_bin -- 英文大小写不敏感问题
;
USE `knowledge_users`;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for growth_role1
-- ----------------------------
DROP TABLE IF EXISTS `growth_role1`;
CREATE TABLE `growth_role1`  (
                                 `growth_role_id` bigint(0) NOT NULL COMMENT '成长值规则id',
                                 `isregist` int(0) DEFAULT NULL COMMENT '注册获取成长值',
                                 `paid` int(0) DEFAULT NULL COMMENT '每购买一次获取成长值',
                                 `shared` int(0) DEFAULT NULL COMMENT '每次分享获取成长值',
                                 `invited` int(0) DEFAULT NULL COMMENT '邀请新用户获取成长值',
                                 `signin` int(0) DEFAULT NULL COMMENT '每日签到',
                                 PRIMARY KEY (`growth_role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for growth_role2
-- ----------------------------
DROP TABLE IF EXISTS `growth_role2`;
CREATE TABLE `growth_role2`  (
                                 `growth_role_id` bigint(0) NOT NULL COMMENT '成长值规则id',
                                 `money` double DEFAULT NULL COMMENT '需要满足的订单消费额',
                                 `point` int(0) DEFAULT NULL COMMENT '获取的成长值',
                                 PRIMARY KEY (`growth_role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of growth_role2
-- ----------------------------
INSERT INTO `growth_role2` VALUES (21, 100, 3);
INSERT INTO `growth_role2` VALUES (22, 500, 18);
INSERT INTO `growth_role2` VALUES (23, 1000, 40);
INSERT INTO `growth_role2` VALUES (24, 2000, 100);

-- ----------------------------
-- Table structure for level_role
-- ----------------------------
DROP TABLE IF EXISTS `level_role`;
CREATE TABLE `level_role`  (
                               `roleid` bigint(0) NOT NULL COMMENT '规则id',
                               `ismember` int(0) DEFAULT NULL COMMENT '是否注册会员(1是，0否)',
                               `money` double DEFAULT NULL COMMENT '累计消费(元)',
                               `growth_num` int(0) DEFAULT NULL COMMENT '成长值',
                               PRIMARY KEY (`roleid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of level_role
-- ----------------------------
INSERT INTO `level_role` VALUES (1, 1, NULL, 0);
INSERT INTO `level_role` VALUES (2, 1, NULL, 1000);
INSERT INTO `level_role` VALUES (3, 1, NULL, 2000);
INSERT INTO `level_role` VALUES (4, 1, 500, 4000);
INSERT INTO `level_role` VALUES (325452803919052801, 1, 800, 6000);

-- ----------------------------
-- Table structure for levelup_gift
-- ----------------------------
DROP TABLE IF EXISTS `levelup_gift`;
CREATE TABLE `levelup_gift`  (
                                 `gift_id` bigint(0) NOT NULL COMMENT '礼包类型id',
                                 `couponid` bigint(0) DEFAULT NULL COMMENT '优惠券id',
                                 `points` int(0) DEFAULT NULL COMMENT '送积分数',
                                 PRIMARY KEY (`gift_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of levelup_gift
-- ----------------------------
INSERT INTO `levelup_gift` VALUES (1, 1536672484048232450, 10);
INSERT INTO `levelup_gift` VALUES (2, 1536673706905952258, 50);
INSERT INTO `levelup_gift` VALUES (3, 1536673740112257025, 150);
INSERT INTO `levelup_gift` VALUES (4, 1536673750002425857, 300);
INSERT INTO `levelup_gift` VALUES (325452803919052802, 1536673750002425857, 200);

-- ----------------------------
-- Table structure for member_plus_type
-- ----------------------------
DROP TABLE IF EXISTS `member_plus_type`;
CREATE TABLE `member_plus_type`  (
                                     `plus_typeid` bigint(0) NOT NULL COMMENT '付费类型id',
                                     `plusname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员卡名',
                                     `bgcolor` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '背景色',
                                     `efficient_time` int(0) DEFAULT NULL COMMENT '有效时长（月）',
                                     `price` double DEFAULT NULL COMMENT '价格',
                                     `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '特权描述',
                                     `status` int(0) DEFAULT NULL COMMENT '类型状态',
                                     `modified_time` datetime(0) DEFAULT NULL COMMENT '最后修改时间',
                                     PRIMARY KEY (`plus_typeid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_plus_type
-- ----------------------------
INSERT INTO `member_plus_type` VALUES (1, '月度会员', '#FF7F00', 1, 29, '描述xxx', 1, '2022-06-10 18:02:46');
INSERT INTO `member_plus_type` VALUES (2, '季度会员', '#FF4500', 3, 89, '描述yyy', 1, '2022-06-10 16:47:26');
INSERT INTO `member_plus_type` VALUES (3, '半年会员', '#8B008B', 6, 159, '描述zzz', 1, '2022-06-10 16:48:34');
INSERT INTO `member_plus_type` VALUES (4, '年度大会员', '#6B8E23', 12, 279, '描述xyz', 1, '2022-06-10 16:50:15');
INSERT INTO `member_plus_type` VALUES (325485520224256000, '群决中农', 'laborum', 20, 870, '1 ali2a', 1, '2022-06-17 12:11:08');

-- ----------------------------
-- Table structure for member_rights
-- ----------------------------
DROP TABLE IF EXISTS `member_rights`;
CREATE TABLE `member_rights`  (
                                  `right_id` bigint(0) NOT NULL COMMENT '权益id',
                                  `right_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权益名称',
                                  `right_img` varchar(2555) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权益图标',
                                  `right_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权益说明',
                                  `status` int(0) DEFAULT NULL COMMENT '权益状态（1启用，0禁用）',
                                  `modified_time` datetime(0) DEFAULT NULL COMMENT '最后修改时间',
                                  PRIMARY KEY (`right_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_rights
-- ----------------------------
INSERT INTO `member_rights` VALUES (1, '商品折扣', 'a.jpg', '商品折扣说明', 1, '2022-06-02 17:01:17');
INSERT INTO `member_rights` VALUES (2, '消费折扣', 'http://dummyimage.com/400x400', '消费折扣说明', 0, '2022-06-17 16:13:29');
INSERT INTO `member_rights` VALUES (3, '急速退款', 'c.jpg', '说明', 1, '2022-06-02 17:02:42');
INSERT INTO `member_rights` VALUES (4, '专属客服', 'd.jpg', '说明', 1, '2022-06-03 17:03:23');
INSERT INTO `member_rights` VALUES (5, '优先发货', 'e.jpg', '说明', 1, '2022-06-02 17:03:51');
INSERT INTO `member_rights` VALUES (6, '特卖专享', 'f.jpg', '说明', 1, '2022-06-03 17:05:41');
INSERT INTO `member_rights` VALUES (7, '积分翻倍', 'g.jpg', '说明', 1, '2022-06-04 17:06:08');
INSERT INTO `member_rights` VALUES (325544503739940864, '8折折扣', 'http://192.172.0.205:9000/user-service/a1.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20220617%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20220617T075200Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=25dadc228d2618ec19d447fe5f2d54f67ff962bd4093d0331131ea55aa0f74d8', '商品八折价', 1, '2022-06-17 15:57:38');

-- ----------------------------
-- Table structure for messageid
-- ----------------------------
DROP TABLE IF EXISTS `messageid`;
CREATE TABLE `messageid`  (
                              `messageId` bigint(0) NOT NULL,
                              PRIMARY KEY (`messageId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for point_rule
-- ----------------------------
DROP TABLE IF EXISTS `point_rule`;
CREATE TABLE `point_rule`  (
                               `point_rule_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '积分规则id',
                               `sku_id` bigint(0) DEFAULT NULL COMMENT '商品唯一id',
                               `is_discount` int(0) DEFAULT NULL COMMENT '是否有积分折扣',
                               `status` int(0) DEFAULT NULL COMMENT '状态（1使用，0不使用）',
                               `deduction` int(0) DEFAULT NULL COMMENT '可用折扣积分数（1元=100积分）',
                               PRIMARY KEY (`point_rule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 325820296659468289 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of point_rule
-- ----------------------------
INSERT INTO `point_rule` VALUES (1, 1, 1, 1, 5);
INSERT INTO `point_rule` VALUES (2, 2, 1, 1, 5);
INSERT INTO `point_rule` VALUES (3, 3, 1, 1, 1500);
INSERT INTO `point_rule` VALUES (325818019596992512, 74, 1, 0, 780);
INSERT INTO `point_rule` VALUES (325820296659468288, 100, 0, 1, 500);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
                             `branch_id` bigint(0) NOT NULL COMMENT 'branch transaction id',
                             `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'global transaction id',
                             `context` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'undo_log context,such as serialization',
                             `rollback_info` longblob NOT NULL COMMENT 'rollback info',
                             `log_status` int(0) NOT NULL COMMENT '0:normal status,1:defense status',
                             `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
                             `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
                             UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_addr
-- ----------------------------
DROP TABLE IF EXISTS `user_addr`;
CREATE TABLE `user_addr`  (
                              `addr_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                              `uid` bigint(0) DEFAULT NULL COMMENT '用户uid',
                              `consignee` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人',
                              `country` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国家',
                              `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省',
                              `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市',
                              `district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区',
                              `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '具体门牌号',
                              `is_default` int(0) DEFAULT NULL COMMENT '是否默认(1是，0否)',
                              `modified_time` datetime(0) DEFAULT NULL COMMENT '最后修改时间',
                              PRIMARY KEY (`addr_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_addr
-- ----------------------------
INSERT INTO `user_addr` VALUES (1, 1, '张三', '中国', '湖北', '武汉', '江夏', 'xxx101号', 1, '2022-06-02 16:51:06');
INSERT INTO `user_addr` VALUES (2, 2, '张三', '中国', '', '上海', '普陀', 'xxx102号', 1, '2022-06-02 16:51:48');
INSERT INTO `user_addr` VALUES (3, 3, '张三', '中国', '广东', '广州', '天河', 'xxx103号', 1, '2022-06-03 16:53:00');
INSERT INTO `user_addr` VALUES (4, 4, '张三', '中国', '湖北', '武汉', '武昌', 'xxx104号', 1, '2022-06-02 16:53:46');
INSERT INTO `user_addr` VALUES (5, 5, '张三', '中国', '湖北', '武汉', '洪山', 'xxx105号', 1, '2022-06-03 16:54:35');

-- ----------------------------
-- Table structure for user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon`  (
                                `uid` bigint(0) DEFAULT NULL COMMENT '用户id',
                                `couponid` bigint(0) DEFAULT NULL COMMENT '优惠券id',
                                `status` int(0) DEFAULT NULL COMMENT '优惠券状态（是否过期）'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_coupon
-- ----------------------------
INSERT INTO `user_coupon` VALUES (1, 1536672484048232450, 1);
INSERT INTO `user_coupon` VALUES (1, 1536673706905952258, 0);
INSERT INTO `user_coupon` VALUES (1, 1536673740112257025, 0);
INSERT INTO `user_coupon` VALUES (7, 1536673750002425857, 0);

-- ----------------------------
-- Table structure for user_course_msg
-- ----------------------------
DROP TABLE IF EXISTS `user_course_msg`;
CREATE TABLE `user_course_msg`  (
                                    `uid` bigint(0) NOT NULL COMMENT '用户id',
                                    `course_id` bigint(0) DEFAULT NULL COMMENT '课程id',
                                    `course_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '课程名',
                                    `total_time` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学习总时长',
                                    `today_time` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '今日学习时长(小时）（每日时间会重置）',
                                    `modified_time` datetime(0) DEFAULT NULL COMMENT '最后修改时间',
                                    PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_course_msg
-- ----------------------------
INSERT INTO `user_course_msg` VALUES (1, 1, '1', '0.05h', '', '2022-06-20 14:56:37');
INSERT INTO `user_course_msg` VALUES (2, 1, '1', '0.016666666666666666h', '', '2022-06-20 14:56:37');

-- ----------------------------
-- Table structure for user_growth_log
-- ----------------------------
DROP TABLE IF EXISTS `user_growth_log`;
CREATE TABLE `user_growth_log`  (
                                    `growth_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '成长值日志id',
                                    `uid` bigint(0) DEFAULT NULL COMMENT '用户id',
                                    `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '成长值来源',
                                    `refer_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '成长值来源相关编号（待定）',
                                    `change_point` int(0) DEFAULT NULL COMMENT '变更成长值数',
                                    `create_time` datetime(0) DEFAULT NULL COMMENT '成长值日志生成时间',
                                    PRIMARY KEY (`growth_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_growth_log
-- ----------------------------
INSERT INTO `user_growth_log` VALUES (1, 1, '备注', NULL, 10, '2022-06-15 07:47:08');
INSERT INTO `user_growth_log` VALUES (5, 1, '备注', NULL, 1000, '2022-06-15 08:10:54');
INSERT INTO `user_growth_log` VALUES (6, 1, '备注', NULL, 11, '2022-06-18 16:37:54');
INSERT INTO `user_growth_log` VALUES (7, 1, '订单消费新增', NULL, 1, '2022-06-20 17:46:38');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
                              `uid` bigint(0) NOT NULL COMMENT '用户id',
                              `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户姓名',
                              `user_img` varchar(2550) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户头像',
                              `gender` int(0) DEFAULT NULL COMMENT '用户性别(1男，0女)',
                              `age` int(0) DEFAULT NULL COMMENT '用户年龄',
                              `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号码',
                              `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
                              `job` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户职业',
                              `source_way` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '来源方式(app...)',
                              `note` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
                              `modified_time` datetime(0) DEFAULT NULL COMMENT '最后修改时间',
                              `register_time` datetime(0) DEFAULT NULL COMMENT '注册日期',
                              PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'tom', 'a.jpg', 1, 18, '111222333', '203136930@qq.com', '学生', 'pc端', '备注xxx', '2022-06-10 17:24:28', '2022-06-01 17:35:26');
INSERT INTO `user_info` VALUES (2, 'jerry', 'a.jpg', 1, 18, '111222333', '251100172@qq.com', '学生', 'pc端', '备注xxx', '2022-06-10 17:24:28', '2022-06-03 17:35:30');
INSERT INTO `user_info` VALUES (3, '张三', 'a.jpg', 1, 18, '111222333', '12@a.com', '学生', 'pc端', '备注xxx', '2022-06-10 17:24:28', '2022-06-05 17:35:41');
INSERT INTO `user_info` VALUES (4, '张思', 'a.jpg', 1, 18, '111222333', '12@a.com', '学生', 'pc端', '备注xxx', '2022-06-10 17:24:28', '2022-06-05 17:35:45');
INSERT INTO `user_info` VALUES (5, '刘武', 'a.jpg', 1, 18, '111222333', '12@a.com', '学生', 'pc端', '备注xxx', '2022-06-10 17:24:28', '2022-06-06 17:35:50');
INSERT INTO `user_info` VALUES (7, '秦艳', 'http://dummyimage.com/400x400', 1, 18, '18667433136', 'y.irtfdwnl@qq.com', 'labore', 'in enim Duis ea occaecat', 'consecteturexercitation nostrud ex', NULL, '2004-09-22 15:32:14');

-- ----------------------------
-- Table structure for user_label
-- ----------------------------
DROP TABLE IF EXISTS `user_label`;
CREATE TABLE `user_label`  (
                               `label_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '标签id',
                               `label_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标签名称',
                               `label_rank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '一级归类',
                               `label_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标签类型',
                               `status` int(0) DEFAULT NULL,
                               PRIMARY KEY (`label_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1537364011379408899 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_label
-- ----------------------------
INSERT INTO `user_label` VALUES (1, '男', '自然性别', '统计', 1);
INSERT INTO `user_label` VALUES (2, '女', '自然性别', '统计', 1);
INSERT INTO `user_label` VALUES (3, '购物男', '购物性别', '规则', 1);
INSERT INTO `user_label` VALUES (4, '购物女', '购物性别', '规则', 1);
INSERT INTO `user_label` VALUES (5, '学生', '用户身份', '统计', 1);
INSERT INTO `user_label` VALUES (6, '家庭主妇', '用户身份', '统计', 1);
INSERT INTO `user_label` VALUES (7, '青年', '用户身份', '统计', 1);
INSERT INTO `user_label` VALUES (8, '中年', '用户身份', '统计', 1);
INSERT INTO `user_label` VALUES (9, '壮年', '用户身份', '统计', 1);
INSERT INTO `user_label` VALUES (10, '老年', '用户身份', '统计', 1);
INSERT INTO `user_label` VALUES (11, '高活跃', '用户活跃度', '规则', 1);
INSERT INTO `user_label` VALUES (12, '中活跃', '用户活跃度', '规则', 1);
INSERT INTO `user_label` VALUES (13, '低活跃', '用户活跃度', '规则', 1);
INSERT INTO `user_label` VALUES (14, '新用户', '用户活跃度', '规则', 1);
INSERT INTO `user_label` VALUES (15, '老用户', '用户活跃度', '规则', 1);
INSERT INTO `user_label` VALUES (16, '浏览购买型', '购物风格', '算法', 1);
INSERT INTO `user_label` VALUES (17, '搜索购买型', '购物风格', '算法', 1);
INSERT INTO `user_label` VALUES (18, '促销购买型', '购物风格', '算法', 1);
INSERT INTO `user_label` VALUES (1537364011379408898, '量性放飞', 'anim irure ut aliquip', 'tempor quis deserunt', 1);

-- ----------------------------
-- Table structure for user_label_info
-- ----------------------------
DROP TABLE IF EXISTS `user_label_info`;
CREATE TABLE `user_label_info`  (
                                    `uid` bigint(0) NOT NULL COMMENT '用户id',
                                    `label_id` bigint(0) DEFAULT NULL COMMENT '标签id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_label_info
-- ----------------------------
INSERT INTO `user_label_info` VALUES (1, 1);
INSERT INTO `user_label_info` VALUES (1, 3);
INSERT INTO `user_label_info` VALUES (1, 5);
INSERT INTO `user_label_info` VALUES (1, 12);
INSERT INTO `user_label_info` VALUES (1, 16);
INSERT INTO `user_label_info` VALUES (2, 2);
INSERT INTO `user_label_info` VALUES (2, 4);
INSERT INTO `user_label_info` VALUES (2, 5);
INSERT INTO `user_label_info` VALUES (2, 16);
INSERT INTO `user_label_info` VALUES (3, 1);
INSERT INTO `user_label_info` VALUES (4, 2);

-- ----------------------------
-- Table structure for user_learn_msg
-- ----------------------------
DROP TABLE IF EXISTS `user_learn_msg`;
CREATE TABLE `user_learn_msg`  (
                                   `uid` bigint(0) NOT NULL COMMENT '用户id',
                                   `total_course` int(0) DEFAULT NULL COMMENT '学习总课程数',
                                   `finished_course` int(0) DEFAULT NULL COMMENT '已完成总课程数',
                                   `total_time` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学习总时长（小时）',
                                   `today_time` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '今日学习时长(小时）（每日时间会重置）',
                                   `modified_time` datetime(0) DEFAULT NULL COMMENT '最后修改时间',
                                   PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_learn_msg
-- ----------------------------
INSERT INTO `user_learn_msg` VALUES (1, 1, NULL, NULL, '0.05h', '2022-06-20 14:56:37');
INSERT INTO `user_learn_msg` VALUES (2, 1, NULL, NULL, '0.016666666666666666h', '2022-06-20 14:56:37');

-- ----------------------------
-- Table structure for user_level
-- ----------------------------
DROP TABLE IF EXISTS `user_level`;
CREATE TABLE `user_level`  (
                               `level_id` bigint(0) NOT NULL COMMENT '会员级别id',
                               `level_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员级别数',
                               `level_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员级别名称',
                               `bgcolor` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '背景色',
                               `roleid` bigint(0) DEFAULT NULL COMMENT '升级条件（会员等级规则外键id）',
                               `giftid` bigint(0) DEFAULT NULL COMMENT '升级礼包类型id(外键)',
                               `min_point` int(0) DEFAULT NULL COMMENT '该级别最低分',
                               `max_point` int(0) DEFAULT NULL COMMENT '该级别最高分',
                               `rightid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '等级权益（会员权益id,逗号隔开）',
                               `status` int(0) DEFAULT NULL COMMENT '等级状态',
                               `modified_time` datetime(0) DEFAULT NULL COMMENT '最后的修改时间',
                               PRIMARY KEY (`level_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_level
-- ----------------------------
INSERT INTO `user_level` VALUES (1, 'LV1', '普通会员', '#4169E1', 1, 1, 0, 1000, '1', NULL, '2022-06-01 16:28:18');
INSERT INTO `user_level` VALUES (2, 'LV2', '黄金会员', '#FFD700', 2, 2, 1001, 3000, '1,2', NULL, '2022-06-01 16:32:16');
INSERT INTO `user_level` VALUES (4, 'LV3', '铂金会员', '#9370DB', 3, 3, 3001, 5000, '1,2,3', NULL, '2022-06-01 16:34:15');
INSERT INTO `user_level` VALUES (5, 'LV4', '钻石会员', '#1C1C1C', 4, 4, 5001, 6000, '1,2,3,4', NULL, '2022-06-01 16:36:16');
INSERT INTO `user_level` VALUES (325452803919052800, 'LV5', '白金会员', '#1245', 325452803919052801, 325452803919052802, 6001, 7010, '12,14,12', NULL, '2022-06-17 10:53:19');

-- ----------------------------
-- Table structure for user_member_info
-- ----------------------------
DROP TABLE IF EXISTS `user_member_info`;
CREATE TABLE `user_member_info`  (
                                     `uid` bigint(0) NOT NULL COMMENT '用户id',
                                     `level_id` bigint(0) DEFAULT NULL COMMENT '会员级别id（外键）',
                                     `level_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员级别数',
                                     `gift_status` int(0) DEFAULT NULL COMMENT '是否发放了升级礼包（1是，0否）,每次升级重置状态，发放了修改状态',
                                     `growth_num` int(0) DEFAULT NULL COMMENT '成长值数',
                                     `points` int(0) DEFAULT NULL COMMENT '积分数(抵扣规则：1元=100积分)',
                                     `modified_time` datetime(0) DEFAULT NULL COMMENT '最后修改时间',
                                     PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_member_info
-- ----------------------------
INSERT INTO `user_member_info` VALUES (1, 1, '', 0, 512, 521, '2022-06-20 17:46:38');
INSERT INTO `user_member_info` VALUES (2, 1, '', 1, 502, 200, '2022-06-10 17:41:40');
INSERT INTO `user_member_info` VALUES (3, 1, '', 1, 503, 200, '2022-06-10 17:41:40');
INSERT INTO `user_member_info` VALUES (4, 1, '', 1, 504, 200, '2022-06-10 17:41:40');
INSERT INTO `user_member_info` VALUES (5, 2, '', 1, 505, 200, '2022-06-10 17:41:40');
INSERT INTO `user_member_info` VALUES (7, 1, '', 0, 0, 10, '2022-06-20 14:15:00');

-- ----------------------------
-- Table structure for user_pay_log
-- ----------------------------
DROP TABLE IF EXISTS `user_pay_log`;
CREATE TABLE `user_pay_log`  (
                                 `pay_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '消费日志id',
                                 `uid` bigint(0) DEFAULT NULL COMMENT '用户id',
                                 `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '记录来源',
                                 `source_sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '相关单据id',
                                 `create_time` datetime(0) DEFAULT NULL COMMENT '记录生成时间',
                                 `amount` double DEFAULT NULL COMMENT '变动金额',
                                 PRIMARY KEY (`pay_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_pay_log
-- ----------------------------
INSERT INTO `user_pay_log` VALUES (1, 1, '来源1', '111', '2022-06-01 14:33:33', 100);
INSERT INTO `user_pay_log` VALUES (2, 1, '来源2', '222', '2022-06-01 14:33:33', 50);
INSERT INTO `user_pay_log` VALUES (3, 2, '来源3', '333', '2022-06-02 14:34:17', 200);

-- ----------------------------
-- Table structure for user_plus_info
-- ----------------------------
DROP TABLE IF EXISTS `user_plus_info`;
CREATE TABLE `user_plus_info`  (
                                   `uid` bigint(0) NOT NULL COMMENT '用户id',
                                   `plus_typeid` bigint(0) DEFAULT NULL COMMENT '付费会员类型id(外键）',
                                   `card_remind` int(0) DEFAULT NULL COMMENT '是否有续卡提醒(0不提醒，1提醒)',
                                   `open_time` datetime(0) DEFAULT NULL COMMENT '开卡时间',
                                   `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户会员状态（已过期，未过期）',
                                   `modified_time` datetime(0) DEFAULT NULL COMMENT '最后修改时间',
                                   PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_plus_info
-- ----------------------------
INSERT INTO `user_plus_info` VALUES (2, 1, 0, '2022-06-09 17:45:09', '未过期', '2022-06-10 17:45:27');

-- ----------------------------
-- Table structure for user_point_log
-- ----------------------------
DROP TABLE IF EXISTS `user_point_log`;
CREATE TABLE `user_point_log`  (
                                   `point_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '积分日志id',
                                   `uid` bigint(0) DEFAULT NULL COMMENT '用户id',
                                   `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '积分来源',
                                   `refer_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '积分来源相关编号（待定）',
                                   `change_point` int(0) DEFAULT NULL COMMENT '变更积分数',
                                   `create_time` datetime(0) DEFAULT NULL COMMENT '积分日志生成时间',
                                   PRIMARY KEY (`point_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_point_log
-- ----------------------------
INSERT INTO `user_point_log` VALUES (1, 1, '修改积分', NULL, 10, '2022-06-15 04:03:24');
INSERT INTO `user_point_log` VALUES (2, 1, '修改积分', NULL, 0, '2022-06-15 04:07:11');
INSERT INTO `user_point_log` VALUES (5, 1, '等级升级赠送', NULL, 50, '2022-06-15 08:10:55');
INSERT INTO `user_point_log` VALUES (6, 1, '备注', NULL, 11, '2022-06-18 16:37:32');
INSERT INTO `user_point_log` VALUES (7, 1, '等级升级赠送', NULL, 10, '2022-06-18 16:37:54');
INSERT INTO `user_point_log` VALUES (10, 7, '用户注册赠送', NULL, 10, '2022-06-20 14:15:00');
INSERT INTO `user_point_log` VALUES (11, 1, '订单积分扣除', '订单编号122', -10, '2022-06-20 17:46:38');
INSERT INTO `user_point_log` VALUES (12, 1, '订单消费新增', NULL, 10, '2022-06-20 17:46:38');

-- ----------------------------
-- Function structure for stuff
-- ----------------------------
DROP FUNCTION IF EXISTS `stuff`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `stuff`( str VARCHAR ( 8000 ), startIndex INT, length INT, Newstr VARCHAR ( 8000 )) RETURNS varchar(8000) CHARSET utf8mb4 COLLATE utf8mb4_bin
    DETERMINISTIC
BEGIN
RETURN concat(
    LEFT ( str, startIndex - 1 ),
        Newstr,
    RIGHT ( str, length( str ) - LOCATE( SUBSTRING( str, startIndex, length ), str ) - length+1)
	);

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;


/*
Navicat MySQL Data Transfer

Source Server         : 192.172.0.205project
Source Server Version : 80029
Source Host           : 192.172.0.205:3306
Source Database       : knowledge_qk

Target Server Type    : MYSQL
Target Server Version : 80029
File Encoding         : 65001

Date: 2022-06-21 15:06:41
*/

SET FOREIGN_KEY_CHECKS=0;


DROP DATABASE IF EXISTS `knowledge_qk`;
CREATE DATABASE `knowledge_qk`
    DEFAULT CHARACTER SET utf8mb4 -- 乱码问题
    DEFAULT COLLATE utf8mb4_bin -- 英文大小写不敏感问题
;
USE `knowledge_qk`;

-- ----------------------------
-- Table structure for channels
-- ----------------------------
DROP TABLE IF EXISTS `channels`;
CREATE TABLE `channels` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `channelName` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '渠道名称',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for coupon_availableItems
-- ----------------------------
DROP TABLE IF EXISTS `coupon_availableItems`;
CREATE TABLE `coupon_availableItems` (
                                         `id` int NOT NULL AUTO_INCREMENT,
                                         `goodsId` int DEFAULT NULL COMMENT '商品表外键id',
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for coupon_collection_usage_data
-- ----------------------------
DROP TABLE IF EXISTS `coupon_collection_usage_data`;
CREATE TABLE `coupon_collection_usage_data` (
                                                `id` bigint NOT NULL,
                                                `couponTypeId` int DEFAULT NULL COMMENT '优惠券类型(0: 满减券,1: 折扣券,2: 兑换券,3: 随机金额券)',
                                                `couponDetailsId` bigint DEFAULT NULL COMMENT '优惠券详情表外键id',
                                                `numberOfSheetsReceived` int DEFAULT NULL COMMENT '领取张数',
                                                `numberOfRecipients` int DEFAULT NULL COMMENT '领取人数',
                                                `numberOfSheetsUsed` int DEFAULT NULL COMMENT '使用张数',
                                                `numberOfUsers` int DEFAULT NULL COMMENT '使用人数',
                                                `currentTime` date DEFAULT NULL COMMENT '当前时间',
                                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for coupon_details
-- ----------------------------
DROP TABLE IF EXISTS `coupon_details`;
CREATE TABLE `coupon_details` (
                                  `id` bigint NOT NULL,
                                  `couponName` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '优惠券名称',
                                  `couponTypeId` int DEFAULT NULL COMMENT '优惠券类型表外键id',
                                  `couponDescription` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '优惠券描述',
                                  `numberOfReleases` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '发放数量',
                                  `channelsId` int DEFAULT NULL COMMENT '渠道表外键id',
                                  `availableItems` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '优惠券可用商品集合',
                                  `useThreshold` int DEFAULT NULL COMMENT '使用门槛(满多少钱)',
                                  `fullReduction` int NOT NULL COMMENT '满减金额',
                                  `validityPeriod` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '有效期',
                                  `user_levelId` int DEFAULT NULL COMMENT '领取人群(使用范围)  梅汉宙的会员等级表外键id',
                                  `availableQuantity` int DEFAULT NULL COMMENT '可领取数量',
                                  `singleUsableQuantity` int DEFAULT NULL COMMENT '单次可使用数量',
                                  `shareSettings` int DEFAULT NULL COMMENT '分享设置 (0: 不允许分享给微信好友,1: 允许)',
                                  `shareCopy` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分享文案',
                                  `transferSettings` int DEFAULT NULL COMMENT '转赠设置(0: 不允许转赠,1: )',
                                  `publicSettings` int DEFAULT NULL COMMENT '公开设置(0: 不允许用户自行领取 1; 允许用户领取)',
                                  `validityPeriodsId` int DEFAULT NULL COMMENT '过期提醒(有效期表外键id)',
                                  `pickUpInstructions` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '领取说明',
                                  `offerDescription` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '优惠说明',
                                  `usageNotice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '\r\n\r\n\r\n使用说明',
                                  `couponStatus` int DEFAULT NULL COMMENT '优惠券状态(0:未开始,1: 进行中,2: 已结束)',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for coupon_type
-- ----------------------------
DROP TABLE IF EXISTS `coupon_type`;
CREATE TABLE `coupon_type` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `couponTypeName` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '优惠券类型名称',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for coupon_user_details
-- ----------------------------
DROP TABLE IF EXISTS `coupon_user_details`;
CREATE TABLE `coupon_user_details` (
                                       `id` bigint NOT NULL,
                                       `couponTypeId` int DEFAULT NULL COMMENT '优惠券类型(0: 满减券,1: 折扣券,2: 兑换券,3: 随机金额券)',
                                       `coupondetailsId` bigint DEFAULT NULL COMMENT '优惠券详情表外键id',
                                       `usersId` bigint NOT NULL COMMENT '领取用户id',
                                       `pickUpTime` datetime NOT NULL COMMENT '优惠券领取时间',
                                       `couponUsageStatus` int DEFAULT NULL COMMENT '优惠券使用状态(0:未使用,1: 已使用,2: 已过期)',
                                       `userEmail` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户邮箱\r\n\r\n',
                                       `usageTime` datetime DEFAULT NULL COMMENT '优惠券使用时间',
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for full_discount_full_discount
-- ----------------------------
DROP TABLE IF EXISTS `full_discount_full_discount`;
CREATE TABLE `full_discount_full_discount` (
                                               `id` bigint NOT NULL,
                                               `eventName` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动名称',
                                               `typeOfActivity` int DEFAULT NULL COMMENT '活动类型(0: 满减活动,1: 满X元包邮活动,2: 满X元满赠活动)',
                                               `activities` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '活动内容(满减信息: 例: 满100减10)',
                                               `scopeOfApplication` int DEFAULT NULL COMMENT '适用商品范围',
                                               `activeStatus` int DEFAULT NULL COMMENT '活动状态(0:未开始,1: 进行中,2: 已结束)',
                                               `eventStartTime` date DEFAULT NULL COMMENT '活动开始时间',
                                               `eventEndTime` date DEFAULT NULL COMMENT '活动结束时间',
                                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for items_eligible_for_full_discount
-- ----------------------------
DROP TABLE IF EXISTS `items_eligible_for_full_discount`;
CREATE TABLE `items_eligible_for_full_discount` (
                                                    `fullDiscountFullDiscountId` bigint DEFAULT NULL COMMENT '满减满折活动表外键',
                                                    `goodsId` bigint DEFAULT NULL COMMENT '商品id\r\n'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for qk_marketing_details
-- ----------------------------
DROP TABLE IF EXISTS `qk_marketing_details`;
CREATE TABLE `qk_marketing_details` (
                                        `id` bigint NOT NULL,
                                        `activity_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动名称',
                                        `marketing_type_id` bigint DEFAULT NULL COMMENT '营销活动类型外键id',
                                        `activity_time` int DEFAULT NULL COMMENT '活动时间(0: 活动当天,1: 活动当周,2: 活动当月)',
                                        `member_ship` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员范围(0: 全部会员,1: 会员等级)',
                                        `active_status_id` bigint DEFAULT NULL COMMENT '活动状态表外键id',
                                        `add_time` datetime DEFAULT NULL COMMENT '添加时间',
                                        `activity_channel` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动渠道(0: 微信商城,1: 线下店铺, 2: app商城)',
                                        `rule_of_activity` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动规则(0: 消费折扣,1: 积分加倍, 2: 赠送优惠券,3: 包邮)',
                                        `notification_channel` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '通知渠道(0: 短信, 1: 商城消息推送)',
                                        `notification_time` int DEFAULT NULL COMMENT '通知时间(0: 不提醒, 1: 距离活动时间前提醒)',
                                        `notification_template` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '通知模版\r\n',
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for qk_marketing_status
-- ----------------------------
DROP TABLE IF EXISTS `qk_marketing_status`;
CREATE TABLE `qk_marketing_status` (
                                       `id` int NOT NULL,
                                       `active_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动状态(未开始,进行中,已结束)',
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for qk_marketing_type
-- ----------------------------
DROP TABLE IF EXISTS `qk_marketing_type`;
CREATE TABLE `qk_marketing_type` (
                                     `id` bigint NOT NULL,
                                     `marketing_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '营销类型名称\r\n',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for unrepeated_message
-- ----------------------------
DROP TABLE IF EXISTS `unrepeated_message`;
CREATE TABLE `unrepeated_message` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for validity_period
-- ----------------------------
DROP TABLE IF EXISTS `validity_period`;
CREATE TABLE `validity_period` (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `validityPeriodsName` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '过期提醒时间',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;