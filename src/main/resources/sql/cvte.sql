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

 Date: 06/12/2022 13:25:42
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for md_item
-- ----------------------------
DROP TABLE IF EXISTS `md_item`;
CREATE TABLE `md_item`
(
    `item_id`         int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物料ID（自增）',
    `item_code`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物料编码',
    `item_name`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物料名称',
    `item_class_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物料分类代码',
    `item_class_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物料分类名称',
    `crt_user`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
    `crt_time`        timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `upd_user`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新者',
    `upd_time`        timestamp NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of md_item
-- ----------------------------
INSERT INTO `md_item`
VALUES (1, '01', '大屏整机', '001', '成品', 'admin', '2022-12-05 10:56:08', NULL, NULL);
INSERT INTO `md_item`
VALUES (2, '01', '三星屏幕', '004', '采购物料', 'admin', '2022-12-05 10:56:10', NULL, NULL);
INSERT INTO `md_item`
VALUES (3, '02', '喇叭', '004', '采购物料', 'admin', '2022-12-05 10:56:12', NULL, NULL);
INSERT INTO `md_item`
VALUES (4, '03', '电容', '004', '采购物料', 'admin', '2022-12-05 10:56:14', NULL, NULL);
INSERT INTO `md_item`
VALUES (5, '04', '保险丝', '004', '采购物料', 'admin', '2022-12-05 10:56:17', NULL, NULL);
INSERT INTO `md_item`
VALUES (6, '01', '边框组件', '008', '虚拟件', 'admin', '2022-12-05 10:56:19', NULL, NULL);
INSERT INTO `md_item`
VALUES (7, '05', '外边框', '004', '采购物料', 'admin', '2022-12-05 10:56:21', NULL, NULL);
INSERT INTO `md_item`
VALUES (8, '06', '胶带', '004', '采购物料', 'admin', '2022-12-05 10:56:24', NULL, NULL);
INSERT INTO `md_item`
VALUES (9, '01', 'PCB控制板', '002', 'PCB板卡', 'admin', '2022-12-05 10:56:26', NULL, NULL);
INSERT INTO `md_item`
VALUES (10, '02', '手机', '001', '成品', 'admin', '2022-12-06 13:08:59', NULL, NULL);

-- ----------------------------
-- Table structure for md_item_rela
-- ----------------------------
DROP TABLE IF EXISTS `md_item_rela`;
CREATE TABLE `md_item_rela`
(
    `md_item_rela_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '关联关系自增id',
    `parent_id`       int NULL DEFAULT NULL COMMENT '父节点id',
    `child_id`        int NULL DEFAULT NULL COMMENT '子节点id',
    `child_quality`   int NULL DEFAULT NULL COMMENT '子节点数量',
    `item_check`      int NULL DEFAULT 0 COMMENT '物料状态，1-为当前物料，0-为替代物料',
    PRIMARY KEY (`md_item_rela_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of md_item_rela
-- ----------------------------
INSERT INTO `md_item_rela`
VALUES (1, 1, 2, 1, 1);
INSERT INTO `md_item_rela`
VALUES (2, 1, 3, 2, 1);
INSERT INTO `md_item_rela`
VALUES (3, 1, 9, 5, 1);
INSERT INTO `md_item_rela`
VALUES (4, 9, 4, 2, 1);
INSERT INTO `md_item_rela`
VALUES (5, 9, 5, 2, 1);
INSERT INTO `md_item_rela`
VALUES (6, 1, 6, 1, 1);
INSERT INTO `md_item_rela`
VALUES (7, 6, 7, 1, 1);
INSERT INTO `md_item_rela`
VALUES (8, 6, 7, 1, 1);
INSERT INTO `md_item_rela`
VALUES (9, 10, 2, 1, 1);
INSERT INTO `md_item_rela`
VALUES (10, NULL, 1, 1, 1);
INSERT INTO `md_item_rela`
VALUES (12, NULL, 2, 1, 1);
INSERT INTO `md_item_rela`
VALUES (13, NULL, 3, 1, 1);
INSERT INTO `md_item_rela`
VALUES (14, NULL, 4, 1, 1);
INSERT INTO `md_item_rela`
VALUES (15, NULL, 5, 1, 1);
INSERT INTO `md_item_rela`
VALUES (16, NULL, 6, 1, 1);
INSERT INTO `md_item_rela`
VALUES (17, NULL, 7, 1, 1);
INSERT INTO `md_item_rela`
VALUES (18, NULL, 8, 1, 1);
INSERT INTO `md_item_rela`
VALUES (19, NULL, 9, 1, 1);
INSERT INTO `md_item_rela`
VALUES (20, NULL, 10, 1, 1);

SET
FOREIGN_KEY_CHECKS = 1;
