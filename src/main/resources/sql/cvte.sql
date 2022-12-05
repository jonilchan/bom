/*
 Navicat Premium Data Transfer

 Source Server         : cvte
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : cvte

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 05/12/2022 14:26:14
*/

CREATE DATABASE cvte;

USE cvte;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for md_item
-- ----------------------------
DROP TABLE IF EXISTS `md_item`;
CREATE TABLE `md_item`  (
  `item_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物料ID（自增）',
  `item_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物料编码',
  `item_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物料名称',
  `item_class_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物料分类代码',
  `item_class_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物料分类名称',
  `item_parent_id` int NULL DEFAULT 0 COMMENT '物料父节点id',
  `item_quantity` int NULL DEFAULT NULL COMMENT '物料数量',
  `item_check` int NULL DEFAULT NULL COMMENT '物料状态，0-为当前物料，1-为替代物料',
  `item_level` int NULL DEFAULT 1 COMMENT '物料所在层级，从1开始',
  `crt_user` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `crt_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `upd_user` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
  `upd_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of md_item
-- ----------------------------
INSERT INTO `md_item` VALUES (1, '01', '大屏整机', '001', '成品', 0, 1, 0, 1, 'admin', '2022-12-05 10:56:08', NULL, NULL);
INSERT INTO `md_item` VALUES (2, '01', '三星屏幕', '004', '采购物料', 1, 1, 0, 2, 'admin', '2022-12-05 10:56:10', NULL, NULL);
INSERT INTO `md_item` VALUES (3, '02', '喇叭', '004', '采购物料', 1, 2, 0, 2, 'admin', '2022-12-05 10:56:12', NULL, NULL);
INSERT INTO `md_item` VALUES (4, '03', '电容', '004', '采购物料', 9, 2, 0, 3, 'admin', '2022-12-05 10:56:14', NULL, NULL);
INSERT INTO `md_item` VALUES (5, '04', '保险丝', '004', '采购物料', 9, 2, 0, 3, 'admin', '2022-12-05 10:56:17', NULL, NULL);
INSERT INTO `md_item` VALUES (6, '01', '边框组件', '008', '虚拟件', 1, 1, 0, 2, 'admin', '2022-12-05 10:56:19', NULL, NULL);
INSERT INTO `md_item` VALUES (7, '05', '外边框', '004', '采购物料', 6, 1, 0, 3, 'admin', '2022-12-05 10:56:21', NULL, NULL);
INSERT INTO `md_item` VALUES (8, '06', '胶带', '004', '采购物料', 6, 1, 0, 3, 'admin', '2022-12-05 10:56:24', NULL, NULL);
INSERT INTO `md_item` VALUES (9, '01', 'PCB控制板', '002', 'PCB板卡', 1, 5, 0, 1, 'admin', '2022-12-05 10:56:26', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
