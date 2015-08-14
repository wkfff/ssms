--------------------------------------------------------
-- 待办表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_TODO`;
CREATE TABLE `SYS_TODO` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `C_TITLE` VARCHAR(500) COMMENT '标题',
    `C_URL` VARCHAR(500) COMMENT 'URL地址',
    `T_BEGIN` DATETIME COMMENT '开始时间',
    `T_END` DATETIME COMMENT '结束时间',
    `C_CONTROL` VARCHAR(100) COMMENT '控制器',
    `R_SID` INT(11) COMMENT 'SID',
    `R_CREATE` INT(11) COMMENT '创建人编号',
    `S_CREATE` VARCHAR(50) COMMENT '创建人',
    `T_CREATE` DATETIME COMMENT '创建时间',
    `R_UPDATE` INT(11) COMMENT '更新人编号',
    `S_UPDATE` VARCHAR(50) COMMENT '更新人',
    `T_UPDATE` TIMESTAMP NOT NULL COMMENT '更新时间',
    `R_TENANT` INT(11) COMMENT '租户编号',
    `S_TENANT` VARCHAR(300) COMMENT '租户名称',
    `P_TENANT` CHAR(1) COMMENT '租户类型',
    `P_PROFESSION` INT(11) COMMENT '专业编号',
    `R_TEMPLATE` INT(11) COMMENT '模板ID',
    `N_STATE` INT(11) COMMENT '状态',
    `B_DELETE` CHAR(1) COMMENT '删除标志',
    `N_INDEX` INT(11) COMMENT '排序',
    `N_VERSION` INT(11) COMMENT '版本',
  PRIMARY KEY (`SID`)
);

--------------------------------------------------------
-- 待办归档表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_DONE`;
CREATE TABLE `SYS_DONE` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `C_TITLE` VARCHAR(500) COMMENT '标题',
    `C_URL` VARCHAR(500) COMMENT 'URL地址',
    `T_BEGIN` DATETIME COMMENT '开始时间',
    `T_END` DATETIME COMMENT '结束时间',
    `C_CONTROL` VARCHAR(100) COMMENT '控制器',
    `R_SID` INT(11) COMMENT 'SID',
    `R_CREATE` INT(11) COMMENT '创建人编号',
    `S_CREATE` VARCHAR(50) COMMENT '创建人',
    `T_CREATE` DATETIME COMMENT '创建时间',
    `R_UPDATE` INT(11) COMMENT '更新人编号',
    `S_UPDATE` VARCHAR(50) COMMENT '更新人',
    `T_UPDATE` TIMESTAMP NOT NULL COMMENT '更新时间',
    `R_TENANT` INT(11) COMMENT '租户编号',
    `S_TENANT` VARCHAR(300) COMMENT '租户名称',
    `P_TENANT` CHAR(1) COMMENT '租户类型',
    `P_PROFESSION` INT(11) COMMENT '专业编号',
    `R_TEMPLATE` INT(11) COMMENT '模板ID',
    `N_STATE` INT(11) COMMENT '状态',
    `B_DELETE` CHAR(1) COMMENT '删除标志',
    `N_INDEX` INT(11) COMMENT '排序',
    `N_VERSION` INT(11) COMMENT '版本',
  PRIMARY KEY (`SID`)
);