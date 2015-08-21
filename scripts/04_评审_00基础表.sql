--------------------------------------------------------
-- 评审方案表
--------------------------------------------------------
DROP TABLE IF EXISTS `SSM_REVIEW_PLAN`;
CREATE TABLE `SSM_REVIEW_PLAN` (
  `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_ENTERPRISE` INT(11) COMMENT '申请企业编号',
  `S_ENTERPRISE` VARCHAR(300) COMMENT '申请企业',
  `C_ENTERPRISE_ADDR` VARCHAR(500) COMMENT '申请企业地址',
  `T_START` DATETIME COMMENT '评审开始时间',
  `T_END` DATETIME COMMENT '评审结束时间',
  `R_LEADER` INT(11) COMMENT '评审组组长',
  `S_LEADER` VARCHAR(300) COMMENT '评审组组长',
  `P_STATE` VARCHAR(32) DEFAULT '0' COMMENT '评审状态',
  `S_STATE` VARCHAR(100) COMMENT '评审状态',
  `R_SID` INT(11) COMMENT '自评主表编号',
  `C_CONTACT` VARCHAR(50) COMMENT '联系人',
  `C_PHONE` VARCHAR(50) COMMENT '电话',
  `C_FAX` VARCHAR(50) COMMENT '传真',
  `C_TEL` VARCHAR(50) COMMENT '手机',
  `C_EMAIL` VARCHAR(100) COMMENT '电子邮箱',
  `C_REVIEW_ADDR` VARCHAR(500) COMMENT '评审企业地址',
  `R_CREATE` INT(11) COMMENT '创建人编号',
  `S_CREATE` VARCHAR(50) COMMENT '创建人',
  `T_CREATE` DATETIME COMMENT '创建时间',
  `R_UPDATE` INT(11) COMMENT '更新人编号',
  `S_UPDATE` VARCHAR(50) COMMENT '更新人',
  `T_UPDATE` TIMESTAMP NOT NULL COMMENT '更新时间',
  `R_TENANT` INT(11) COMMENT '租户编号',
  `S_TENANT` VARCHAR(300) COMMENT '租户名称',
  `P_TENANT` CHAR(1) COMMENT '租户类型',
  `N_STATE` INT(11) COMMENT '状态',
  `B_DELETE` CHAR(1) COMMENT '删除标志',
  `N_INDEX` INT(11) COMMENT '排序',
  `N_VERSION` INT(11) COMMENT '版本',
  `C_JOB` VARCHAR(200),
  PRIMARY KEY (`SID`)
);

--------------------------------------------------------
-- 评审方案成员表
--------------------------------------------------------
DROP TABLE IF EXISTS `SSM_REVIEW_MEMBERS`;
CREATE TABLE `SSM_REVIEW_MEMBERS` (
  `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_SID` INT(11) NOT NULL COMMENT '评审方案编号',
  `R_MEMBER` INT(11) COMMENT '成员编号',
  `S_MEMBER` VARCHAR(300) COMMENT '成员名称',
  `C_JOB` VARCHAR(100) COMMENT '职称',
  `C_TEL` VARCHAR(50) COMMENT '电话',
  `C_REMARKS` VARCHAR(2000) COMMENT '备注',
  `R_CREATE` INT(11) COMMENT '创建人编号',
  `S_CREATE` VARCHAR(50) COMMENT '创建人',
  `T_CREATE` DATETIME COMMENT '创建时间',
  `R_UPDATE` INT(11) COMMENT '更新人编号',
  `S_UPDATE` VARCHAR(50) COMMENT '更新人',
  `T_UPDATE` TIMESTAMP NOT NULL COMMENT '更新时间',
  `R_TENANT` INT(11) COMMENT '租户编号',
  `S_TENANT` VARCHAR(300) COMMENT '租户名称',
  `P_TENANT` CHAR(1) COMMENT '租户类型',
  `N_STATE` INT(11) COMMENT '状态',
  `B_DELETE` CHAR(1) COMMENT '删除标志',
  `N_INDEX` INT(11) COMMENT '排序',
  `N_VERSION` INT(11) COMMENT '版本',
  PRIMARY KEY (`SID`)
);

--------------------------------------------------------
-- 评审内容表
--------------------------------------------------------
DROP TABLE IF EXISTS `SSM_REVIEW_CONTENT`;
CREATE TABLE `SSM_REVIEW_CONTENT` (
  `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_SID` INT(11) NOT NULL COMMENT '评审方案编号',
  `C_CATEGORY` VARCHAR(300) COMMENT '一级要素',
  `C_PROJECT` VARCHAR(300) COMMENT '二级要素',
  `C_REQUEST` VARCHAR(2000) COMMENT '基本规范要求',
  `C_CONTENT` VARCHAR(2000) COMMENT '企业达标标准',
  `N_SCORE` INT(11) COMMENT '标准分值',
  `C_METHOD` VARCHAR(2000) COMMENT '考评方法',
  `C_DESC` VARCHAR(2000) COMMENT '自评描述',
  `B_BLANK` CHAR(1) COMMENT '是否缺项',
  `N_SCORE_REAL` VARCHAR(2000) COMMENT '企业自评得分',
  `C_DESC_REVIEW` VARCHAR(2000) COMMENT '评审描述',
  `N_SCORE_REVIEW` INT(11) COMMENT '评审得分',
  `R_CREATE` INT(11) COMMENT '创建人编号',
  `S_CREATE` VARCHAR(50) COMMENT '创建人',
  `T_CREATE` DATETIME COMMENT '创建时间',
  `R_UPDATE` INT(11) COMMENT '更新人编号',
  `S_UPDATE` VARCHAR(50) COMMENT '更新人',
  `T_UPDATE` TIMESTAMP NOT NULL COMMENT '更新时间',
  `R_TENANT` INT(11) COMMENT '租户编号',
  `S_TENANT` VARCHAR(300) COMMENT '租户名称',
  `P_TENANT` CHAR(1) COMMENT '租户类型',
  `N_STATE` INT(11) COMMENT '状态',
  `B_DELETE` CHAR(1) COMMENT '删除标志',
  `N_INDEX` INT(11) COMMENT '排序',
  `N_VERSION` INT(11) COMMENT '版本',
  PRIMARY KEY (`SID`)
);

--------------------------------------------------------
-- 评审证书表
--------------------------------------------------------
DROP TABLE IF EXISTS `SSM_REVIEW_CERT`;
CREATE TABLE `SSM_REVIEW_CERT` (
  `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_SID` INT(11) NOT NULL COMMENT '评审方案编号',
  `C_ENTERPRISE` VARCHAR(300) NOT NULL COMMENT '单位名称',
  `C_LEVEL` VARCHAR(50) COMMENT '等级',
  `P_INSDUTRY` VARCHAR(32) COMMENT '行业编号',
  `S_INSDUTRY` VARCHAR(100) COMMENT '行业',
  `P_PROFESSION` VARCHAR(32) COMMENT '专业编号',
  `S_PROFESSION` VARCHAR(100) COMMENT '专业',
  `C_CERT_NUMBER` VARCHAR(100) COMMENT '证书编号',
  `C_ISSUING_AUTHORITY` VARCHAR(300) COMMENT '发证机关',
  `C_PRINTED_NUMBER` VARCHAR(100) COMMENT '印制编号',
  `T_ISSUING_DATE` DATE COMMENT '发证日期',
  `T_VALIDITY` DATE COMMENT '有效期截至',
  `R_CREATE` INT(11) COMMENT '创建人编号',
  `S_CREATE` VARCHAR(50) COMMENT '创建人',
  `T_CREATE` DATETIME COMMENT '创建时间',
  `R_UPDATE` INT(11) COMMENT '更新人编号',
  `S_UPDATE` VARCHAR(50) COMMENT '更新人',
  `T_UPDATE` TIMESTAMP NOT NULL COMMENT '更新时间',
  `R_TENANT` INT(11) COMMENT '租户编号',
  `S_TENANT` VARCHAR(300) COMMENT '租户名称',
  `P_TENANT` CHAR(1) COMMENT '租户类型',
  `N_STATE` INT(11) COMMENT '状态',
  `B_DELETE` CHAR(1) COMMENT '删除标志',
  `N_INDEX` INT(11) COMMENT '排序',
  `N_VERSION` INT(11) COMMENT '版本',
  PRIMARY KEY (`SID`)
);