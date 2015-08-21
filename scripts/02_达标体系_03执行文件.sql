--------------------------------------------------------
-- 模板文件配置表: 执行文件
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_STDTMP_FILE_03`;
CREATE TABLE `SYS_STDTMP_FILE_03` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `R_TEMPLATE` INT(11) COMMENT '所属模板',
    `R_TMPFILE` INT(11) COMMENT '模板ID',
    `C_NAME` VARCHAR(300) COMMENT '名称',
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
-- 模板文件配置归档表: 执行文件
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_STDTMP_FILE_03_ARCH`;
CREATE TABLE `SYS_STDTMP_FILE_03_ARCH` (
    `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID',
    `SID` INT(11) COMMENT '归档前的ID',
    `R_TEMPLATE` INT(11) COMMENT '所属模板',
    `R_TMPFILE` INT(11) COMMENT '模板ID',
    `C_NAME` VARCHAR(300) COMMENT '名称',
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
    PRIMARY KEY (`UID`)
);
--------------------------------------------------------
-- 租户达标体系文件表: 执行文件
--------------------------------------------------------
DROP TABLE IF EXISTS `SSM_STDTMP_FILE_03`;
CREATE TABLE `SSM_STDTMP_FILE_03` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `R_TENANT` INT(11) COMMENT '租户编号',
    `S_TENANT` VARCHAR(300) COMMENT '租户编号',
    `P_TENANT` CHAR(1) COMMENT '租户编号',
    `R_TEMPLATE` INT(11) COMMENT '所属模板ID',
    `R_TMPFOLDER` INT(11) COMMENT '所属模板目录',
    `P_PROFESSION` INT(11) COMMENT '所属专业ID',
    `R_TMPFILE` INT(11) COMMENT '模板ID',
    `C_NAME` VARCHAR(300) COMMENT '名称',
    `R_CREATE` INT(11) COMMENT '创建人编号',
    `S_CREATE` VARCHAR(50) COMMENT '创建人',
    `T_CREATE` DATETIME COMMENT '创建时间',
    `R_UPDATE` INT(11) COMMENT '更新人编号',
    `S_UPDATE` VARCHAR(50) COMMENT '更新人',
    `T_UPDATE` TIMESTAMP NOT NULL COMMENT '更新时间',
    `N_STATE` INT(11) COMMENT '状态',
    `B_DELETE` CHAR(1) COMMENT '删除标志',
    `N_INDEX` INT(11) COMMENT '排序',
    `N_VERSION` INT(11) COMMENT '版本',
    PRIMARY KEY (`SID`)
);

--------------------------------------------------------
-- 租户达标体系文件归档表: 执行文件
--------------------------------------------------------
DROP TABLE IF EXISTS `SSM_STDTMP_FILE_03_ARCH`;
CREATE TABLE `SSM_STDTMP_FILE_03_ARCH` (
    `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '归档用主键',
    `N_ARCH_VERSION` INT(11) NOT NULL DEFAULT '0' COMMENT '归档版本',
    `SID` INT(11) COMMENT '自增主键',
    `R_TENANT` INT(11) COMMENT '租户编号',
    `S_TENANT` VARCHAR(300) COMMENT '租户编号',
    `P_TENANT` CHAR(1) COMMENT '租户编号',
    `R_TEMPLATE` INT(11) COMMENT '所属模板ID',
    `R_TMPFOLDER` INT(11) COMMENT '所属模板目录',
    `P_PROFESSION` INT(11) COMMENT '所属专业ID',
    `R_TMPFILE` INT(11) COMMENT '模板ID',
    `C_NAME` VARCHAR(300) COMMENT '名称',
    `R_CREATE` INT(11) COMMENT '创建人编号',
    `S_CREATE` VARCHAR(50) COMMENT '创建人',
    `T_CREATE` DATETIME COMMENT '创建时间',
    `R_UPDATE` INT(11) COMMENT '更新人编号',
    `S_UPDATE` VARCHAR(50) COMMENT '更新人',
    `T_UPDATE` TIMESTAMP NOT NULL COMMENT '更新时间',
    `N_STATE` INT(11) COMMENT '状态',
    `B_DELETE` CHAR(1) COMMENT '删除标志',
    `N_INDEX` INT(11) COMMENT '排序',
    `N_VERSION` INT(11) COMMENT '版本',
    PRIMARY KEY (`UID`)
);
