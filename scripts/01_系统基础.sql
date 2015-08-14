--------------------------------------------------------
-- 企业租户表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_TENANT_E`;
CREATE TABLE `SYS_TENANT_E` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `C_NAME` VARCHAR(300) NOT NULL COMMENT '企业名称',
    `C_CODE` VARCHAR(32) COMMENT '租户编号',
    `R_INDUSTRY` INT(11) COMMENT '所属行业',
    `P_STATE` VARCHAR(32) DEFAULT '1' COMMENT '企业状态编号',
    `S_STATE` VARCHAR(100) COMMENT '企业状态',
    `P_AT_PROVINCE` VARCHAR(32) COMMENT '所属辖区省份编号',
    `S_AT_PROVINCE` VARCHAR(100) COMMENT '所属辖区省份',
    `P_AT_CITY` VARCHAR(32) COMMENT '所属辖区地市编号',
    `S_AT_CITY` VARCHAR(100) COMMENT '所属辖区地市',
    `P_AT_COUNTY` VARCHAR(32) COMMENT '所属辖区地县编号',
    `S_AT_COUNTY` VARCHAR(100) COMMENT '所属辖区地县',
    `P_PROVINCE` VARCHAR(32) COMMENT '省份编号',
    `S_PROVINCE` VARCHAR(100) COMMENT '省份',
    `P_CITY` VARCHAR(32) COMMENT '地市编号',
    `S_CITY` VARCHAR(50) COMMENT '地市',
    `P_COUNTY` VARCHAR(32) COMMENT '县市编号',
    `S_COUNTY` VARCHAR(100) COMMENT '县市',
    `C_ADDR` VARCHAR(500) COMMENT '地址',
    `C_CONTACT` VARCHAR(32) COMMENT '企业联系人',
    `C_EMAIL` VARCHAR(50) COMMENT '电子邮箱',
    `C_TEL` VARCHAR(50) COMMENT '办公电话',
    `C_FAX` VARCHAR(50) COMMENT '传真',
    `C_ZIP` VARCHAR(50) COMMENT '邮政编码',
    `C_WEBSITE` VARCHAR(200) COMMENT '企业网站',
    `C_NUMBER` VARCHAR(100) COMMENT '营业执照注册号',
    `P_NATURE` VARCHAR(32) COMMENT '企业性质编号',
    `S_NATURE` VARCHAR(100) COMMENT '企业性质',
    `N_EMPLOYEE` INT(11) DEFAULT '0' COMMENT '员工总数',
    `N_SAFETY` INT(11) DEFAULT '0' COMMENT '专职安全人员数',
    `N_SPECIAL` INT(11) DEFAULT '0' COMMENT '特种作业人员数',
    `N_ASSETS` DECIMAL(16,4) DEFAULT '0.0000' COMMENT '固定资产',
    `N_INCOME` DECIMAL(16,4) DEFAULT '0.0000' COMMENT '主营业务收入',
    `C_SCOPE` VARCHAR(500) COMMENT '营业范围',
    `C_SUMMARY` VARCHAR(500) COMMENT '企业概述',
    `P_LEVEL` VARCHAR(32) COMMENT '当前达标等级',
    `S_LEVEL` VARCHAR(100) COMMENT '当前达标等级',
    `T_EXAMINE` DATETIME COMMENT '最后评审时间',
    `T_EXAMINE_NEXT` DATETIME COMMENT '下次复审时间',
    `T_PAY` DATETIME COMMENT '缴费日期',
    `T_PAY_NEXT` DATETIME COMMENT '下次缴费日期',
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
    `R_REVIEW` INT(11),
    `S_REVIEW` VARCHAR(300),
    PRIMARY KEY (`SID`)
);
--------------------------------------------------------
-- 企业租户专业表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_TENANT_E_PROFESSION`;
CREATE TABLE `SYS_TENANT_E_PROFESSION` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `P_PROFESSION` VARCHAR(32) COMMENT '专业编号',
    `S_PROFESSION` VARCHAR(100) COMMENT '专业',
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
-- 企业租户用户表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_TENANT_E_USER`;
CREATE TABLE `SYS_TENANT_E_USER` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `C_NAME` VARCHAR(100) COMMENT '姓名',
    `C_USER` VARCHAR(100) COMMENT '用户名',
    `S_NAME` VARCHAR(100) COMMENT '所属租户',
    `R_SID` INT(11) COMMENT '所属租户编号',
    `P_SEX` VARCHAR(32) DEFAULT '1' COMMENT '性别',
    `S_SEX` VARCHAR(100) COMMENT '性别',
    `T_BIRTH` DATE COMMENT '出生日期',
    `C_EMAIL` VARCHAR(50) COMMENT '电子邮箱',
    `C_MOBILE` VARCHAR(50) COMMENT '手机号',
    `C_TEL` VARCHAR(50) COMMENT '办公电话',
    `C_DEPT` VARCHAR(50) COMMENT '部门',
    `C_POSITION` VARCHAR(50) COMMENT '职务',
    `C_CARD` VARCHAR(50) COMMENT '身份证',
    `C_SCHOOL` VARCHAR(100) COMMENT '毕业学校',
    `P_EDUCATION` VARCHAR(32) COMMENT '学历编号',
    `S_EDUCATION` VARCHAR(100) COMMENT '学历',
    `P_DEGREE` VARCHAR(32) COMMENT '学位编号',
    `S_DEGREE` VARCHAR(50) COMMENT '学位',
    `P_STATE` VARCHAR(32) DEFAULT '0' COMMENT '状态编号',
    `S_STATE` VARCHAR(100) COMMENT '状态',
    `C_PASSWD` VARCHAR(50) COMMENT '密码',
    `T_LOGIN` DATETIME COMMENT '最后登录时间',
    `C_IP` VARCHAR(50) COMMENT '最后登陆IP',
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
-- 政府租户表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_TENANT_G`;
CREATE TABLE `SYS_TENANT_G` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `C_NAME` VARCHAR(500) NOT NULL COMMENT '单位名称',
    `C_CODE` VARCHAR(32) COMMENT '租户编号',
    `P_PROVINCE` VARCHAR(32) COMMENT '省份编号',
    `S_PROVINCE` VARCHAR(100) COMMENT '省份',
    `P_CITY` VARCHAR(32) COMMENT '地市编号',
    `S_CITY` VARCHAR(100) COMMENT '地市',
    `P_COUNTY` VARCHAR(32) COMMENT '县市编号',
    `S_COUNTY` VARCHAR(100) COMMENT '县市',
    `P_LEVEL` VARCHAR(32) COMMENT '行政等级编号',
    `S_LEVEL` VARCHAR(100) COMMENT '行政等级',
    `C_ADDR` VARCHAR(500) COMMENT '地址',
    `C_EMAIL` VARCHAR(50) COMMENT '电子邮箱',
    `C_TEL` VARCHAR(50) COMMENT '办公电话',
    `C_FAX` VARCHAR(50) COMMENT '传真',
    `C_ZIP` VARCHAR(50) COMMENT '邮政编码',
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
-- 政府租户用户表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_TENANT_G_USER`;
CREATE TABLE `SYS_TENANT_G_USER` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `C_NAME` VARCHAR(100) COMMENT '姓名',
    `C_USER` VARCHAR(100) COMMENT '用户名',
    `S_NAME` VARCHAR(100) COMMENT '所属租户',
    `R_SID` INT(11) COMMENT '所属租户编号',
    `C_PASSWD` VARCHAR(50) COMMENT '密码',
    `C_MOBILE` VARCHAR(50) COMMENT '手机号',
    `C_EMAIL` VARCHAR(50) COMMENT '电子邮箱',
    `C_TEL` VARCHAR(50) COMMENT '办公电话',
    `T_BIRTH` DATE COMMENT '出生日期',
    `P_SEX` VARCHAR(32) DEFAULT '1' COMMENT '性别',
    `S_SEX` VARCHAR(100) COMMENT '性别',
    `C_POSITION` VARCHAR(50) COMMENT '职务',
    `C_RESUME` VARCHAR(1000) COMMENT '工作简历',
    `C_SCHOOL` VARCHAR(300) COMMENT '毕业学校',
    `P_EDUCATION` VARCHAR(32) COMMENT '学历编号',
    `S_EDUCATION` VARCHAR(100) COMMENT '学历',
    `P_DEGREE` VARCHAR(32) COMMENT '学位编号',
    `S_DEGREE` VARCHAR(100) COMMENT '学位',
    `P_STATE` VARCHAR(32) DEFAULT '0' COMMENT '状态',
    `S_STATE` VARCHAR(100) COMMENT '状态',
    `T_LOGIN` DATETIME COMMENT '最后登录时间',
    `C_IP` VARCHAR(50) COMMENT '最后登陆IP',
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
-- 评审租户表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_TENANT_R`;
CREATE TABLE `SYS_TENANT_R` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `C_CODE` VARCHAR(32) NOT NULL COMMENT '租户编号',
    `C_NAME` VARCHAR(500) NOT NULL COMMENT '单位名称',
    `P_STATE` VARCHAR(32) DEFAULT '1' COMMENT '状态编号',
    `S_STATE` VARCHAR(100) COMMENT '状态',
    `P_PROVINCE` VARCHAR(32) COMMENT '省份编号',
    `S_PROVINCE` VARCHAR(100) COMMENT '省份',
    `P_CITY` VARCHAR(32) COMMENT '地市编号',
    `S_CITY` VARCHAR(100) COMMENT '地市',
    `P_COUNTY` VARCHAR(32) COMMENT '县市编号',
    `S_COUNTY` VARCHAR(100) COMMENT '县市',
    `C_ADDR` VARCHAR(500) COMMENT '地址',
    `C_EMAIL` VARCHAR(50) COMMENT '电子邮箱',
    `C_TEL` VARCHAR(50) COMMENT '办公电话',
    `C_FAX` VARCHAR(50) COMMENT '传真',
    `C_ZIP` VARCHAR(50) COMMENT '邮政编码',
    `C_NUMBER` VARCHAR(100) COMMENT '营业执照注册号',
    `C_ORG` VARCHAR(100) COMMENT '确定评审业务机关',
    `P_LEVEL` VARCHAR(32) COMMENT '机构级别',
    `S_LEVEL` VARCHAR(100) COMMENT '机构级别',
    `N_FULLTIME` INT(11) COMMENT '专职人员',
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
    `P_AT_PROVINCE` VARCHAR(32) COMMENT '所属辖区省份编号',
    `S_AT_PROVINCE` VARCHAR(100) COMMENT '所属辖区省份',
    `P_AT_CITY` VARCHAR(32) COMMENT '所属辖区地市编号',
    `S_AT_CITY` VARCHAR(100) COMMENT '所属辖区地市',
    `P_AT_COUNTY` VARCHAR(32) COMMENT '所属辖区地县编号',
    `S_AT_COUNTY` VARCHAR(100) COMMENT '所属辖区地县',
    PRIMARY KEY (`SID`)
);
--------------------------------------------------------
-- 评审租户用户表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_TENANT_R_USER`;
CREATE TABLE `SYS_TENANT_R_USER` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `S_NAME` VARCHAR(100) COMMENT '所属租户',
    `R_SID` INT(11) COMMENT '所属租户编号',
    `C_NAME` VARCHAR(100) COMMENT '姓名',
    `C_USER` VARCHAR(100) COMMENT '用户名',
    `C_PASSWD` VARCHAR(32) COMMENT '密码',
    `C_MOBILE` VARCHAR(16) COMMENT '手机号',
    `C_EMAIL` VARCHAR(50) COMMENT '电子邮箱',
    `C_TEL` VARCHAR(32) COMMENT '办公电话',
    `T_BIRTH` DATE COMMENT '出生日期',
    `P_SEX` VARCHAR(32) DEFAULT '1' COMMENT '性别',
    `S_SEX` VARCHAR(100) COMMENT '性别',
    `C_POSITION` VARCHAR(100) COMMENT '职务',
    `C_CERT` VARCHAR(100) COMMENT '聘请证书编号',
    `C_RESUME` VARCHAR(1000) COMMENT '工作简历',
    `C_SCHOOL` VARCHAR(300) COMMENT '毕业学校',
    `P_EDUCATION` VARCHAR(32) COMMENT '学历编号',
    `S_EDUCATION` VARCHAR(100) COMMENT '学历',
    `P_DEGREE` VARCHAR(32) COMMENT '学位编号',
    `S_DEGREE` VARCHAR(100) COMMENT '学位',
    `P_STATE` VARCHAR(32) DEFAULT '0' COMMENT '状态',
    `S_STATE` VARCHAR(100) COMMENT '状态',
    `T_LOGIN` DATETIME COMMENT '最后登录时间',
    `C_IP` VARCHAR(15) COMMENT '最后登陆IP',
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
-- 系统用户表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_USER`;
CREATE TABLE `SYS_USER` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `C_NAME` VARCHAR(100) COMMENT '用户名',
    `P_SEX` VARCHAR(32) DEFAULT '1' COMMENT '性别',
    `S_SEX` VARCHAR(100) COMMENT '性别',
    `C_EMAIL` VARCHAR(50) COMMENT '电子邮箱',
    `C_MOBILE` VARCHAR(50) COMMENT '手机号',
    `C_TEL` VARCHAR(50) COMMENT '办公电话',
    `P_STATE` VARCHAR(32) DEFAULT '0' COMMENT '状态',
    `S_STATE` VARCHAR(100) COMMENT '状态',
    `C_PASSWD` VARCHAR(50) COMMENT '密码',
    `T_LOGIN` DATETIME COMMENT '最后登录时间',
    `C_IP` VARCHAR(15) COMMENT '最后登陆IP',
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
-- 附件表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_ATTACH_FILE`;
CREATE TABLE `SYS_ATTACH_FILE` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `R_TABLE` VARCHAR(32) NOT NULL COMMENT '对应表',
    `R_SID` INT(11) NOT NULL COMMENT '对应记录编号',
    `C_INNER_NAME` VARCHAR(200) NOT NULL COMMENT '内部文件名',
    `C_OUTER_NAME` VARCHAR(200) NOT NULL COMMENT '外部文件名',
    `C_EXT` VARCHAR(50) NOT NULL COMMENT '扩展名',
    `N_LENGTH` INT(11) NOT NULL COMMENT '文件大小',
    `C_PATH` VARCHAR(200) NOT NULL COMMENT '物理路径，使用相对定位表示目录。',
    `P_SERVER` VARCHAR(200) NOT NULL COMMENT '存放附件的服务器名称',
    `R_CREATE` INT(11) NOT NULL COMMENT '创建人编号',
    `S_CREATE` VARCHAR(50) NOT NULL COMMENT '创建人',
    `T_CREATE` DATETIME NOT NULL COMMENT '创建时间',
    `R_UPDATE` INT(11) NOT NULL COMMENT '更新人编号',
    `S_UPDATE` VARCHAR(50) NOT NULL COMMENT '更新人',
    `T_UPDATE` TIMESTAMP NOT NULL COMMENT '更新时间',
    `R_TENANT` INT(11) NOT NULL COMMENT '租户编号',
    `S_TENANT` VARCHAR(300) NOT NULL COMMENT '租户名称',
    `P_TENANT` CHAR(1) NOT NULL COMMENT '租户类型',
    `N_STATE` INT(11) COMMENT '状态',
    `B_DELETE` CHAR(1) COMMENT '删除标志',
    `N_INDEX` INT(11) COMMENT '排序',
    `N_VERSION` INT(11) COMMENT '版本',
        PRIMARY KEY (`SID`)
);
--------------------------------------------------------
-- 富文本表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_ATTACH_TEXT`;
CREATE TABLE `SYS_ATTACH_TEXT` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `R_TABLE` VARCHAR(32) NOT NULL COMMENT '对应表',
    `R_SID` INT(11) NOT NULL COMMENT '对应记录编号',
    `R_FIELD` VARCHAR(32) NOT NULL COMMENT '对应字段',
    `C_CONTENT` LONGTEXT NOT NULL COMMENT '内容',
    `R_CREATE` INT(11) NOT NULL COMMENT '创建人编号',
    `S_CREATE` VARCHAR(50) NOT NULL COMMENT '创建人',
    `T_CREATE` DATETIME NOT NULL COMMENT '创建时间',
    `R_UPDATE` INT(11) NOT NULL COMMENT '更新人编号',
    `S_UPDATE` VARCHAR(50) NOT NULL COMMENT '更新人',
    `T_UPDATE` TIMESTAMP NOT NULL COMMENT '更新时间',
    `R_TENANT` INT(11) NOT NULL COMMENT '租户编号',
    `S_TENANT` VARCHAR(300) NOT NULL COMMENT '租户名称',
    `P_TENANT` CHAR(1) NOT NULL COMMENT '租户类型',
    `N_STATE` INT(11) COMMENT '状态',
    `B_DELETE` CHAR(1) COMMENT '删除标志',
    `N_INDEX` INT(11) COMMENT '排序',
    `N_VERSION` INT(11) COMMENT '版本',
        PRIMARY KEY (`SID`)
);

--------------------------------------------------------
-- 密码重置历史表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_HIS_PWDRESET`;
CREATE TABLE `SYS_HIS_PWDRESET` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `R_TENANT` INT(11) NOT NULL COMMENT '租户编号',
    `S_TENANT` VARCHAR(300) NOT NULL COMMENT '租户名称',
    `P_TENANT` CHAR(1) NOT NULL COMMENT '租户类型',
    `R_USER` INT(32) NOT NULL COMMENT '用户SID',
    `S_USER` VARCHAR(100) NOT NULL COMMENT '用户名',
    `C_EMAIL` VARCHAR(50) NOT NULL COMMENT '电子邮箱',
    `C_TOKEN` VARCHAR(32) NOT NULL COMMENT '重置令牌',
    `T_APPLY` DATETIME NOT NULL COMMENT '申请时间',
    `R_CREATE` INT(11) NOT NULL COMMENT '创建人编号',
    `S_CREATE` VARCHAR(50) NOT NULL COMMENT '创建人',
    `T_CREATE` DATETIME NOT NULL COMMENT '创建时间',
    `R_UPDATE` INT(11) NOT NULL COMMENT '更新人编号',
    `S_UPDATE` VARCHAR(50) NOT NULL COMMENT '更新人',
    `T_UPDATE` TIMESTAMP NOT NULL COMMENT '更新时间',
    `N_STATE` INT(11) COMMENT '状态',
    `B_DELETE` CHAR(1) COMMENT '删除标志',
    `N_INDEX` INT(11) COMMENT '排序',
    `N_VERSION` INT(11) COMMENT '版本',
    PRIMARY KEY (`SID`),
  UNIQUE KEY `C_TOKEN` (`C_TOKEN`)
);
--------------------------------------------------------
-- 行业表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_INDUSTRY`;
CREATE TABLE `SYS_INDUSTRY` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `C_NAME` VARCHAR(300) COMMENT '行业名称',
    `R_TEMPLATE` INT(11) COMMENT '默认模板',
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
-- 专业信息表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_PROFESSION`;
CREATE TABLE `SYS_PROFESSION` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `C_NAME` VARCHAR(300) COMMENT '专业名称',
    `R_TEMPLATE` INT(11) COMMENT '默认模板',
    `R_INDUSTRY` INT(11) COMMENT '所示行业',
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
-- 功能导航表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_NAV`;
CREATE TABLE `SYS_NAV` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `R_SID` INT(11) COMMENT '父导航',
    `C_NAME` VARCHAR(300) COMMENT '导航名称',
    `C_URL` VARCHAR(2000) COMMENT '导航URL',
    `C_DESC` VARCHAR(2000) COMMENT '导航描述',
    `C_ICON` VARCHAR(200) COMMENT '图标',
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
-- 区域数据表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_PARA_AREA`;
CREATE TABLE `SYS_PARA_AREA` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `C_CODE` VARCHAR(300) COMMENT '参数编码',
    `C_VALUE` VARCHAR(300) COMMENT '参数值',
    `C_PY_CODE` VARCHAR(300) COMMENT '拼音编码',
    `R_PARENT` INT(11) COMMENT '父级编号',
    `R_CODE` VARCHAR(300) COMMENT '父编码',
    `N_LEVEL` INT(11) COMMENT '层级',
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
