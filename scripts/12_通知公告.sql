
--------------------------------------------------------
-- 通知公告表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_NOTICE`;
CREATE TABLE `SYS_NOTICE` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `C_TITLE` VARCHAR(300) COMMENT '标题',
    `C_CONTENT` TEXT COMMENT '内容',
    `T_BEGIN` DATETIME COMMENT '生效时间起',
    `T_END` DATETIME COMMENT '生效时间止',
    `N_READER` INT(11) COMMENT '已阅人数',
    `R_PUBLISH` INT(11) COMMENT '发布人SID',
    `S_PUBLISH` VARCHAR(300) COMMENT '发布人名称',
    `T_PUBLISH` DATETIME COMMENT '发布时间',
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
-- 通知公告评论表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_NOTICE_COMMENTS`;
CREATE TABLE `SYS_NOTICE_COMMENTS` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `R_NOTICE` INT(11) COMMENT '通知公告',
    `C_CONTENT` TEXT COMMENT '评论内容',
    `R_COMMENTS` INT(11) COMMENT '引用评论',
    `B_AUDIT` CHAR(1) COMMENT '是否已审',
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
-- 通知公告接收人表
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_NOTICE_RECEIVER`;
CREATE TABLE `SYS_NOTICE_RECEIVER` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `R_NOTICE` INT(11) COMMENT '通知公告',
    `Z_TYPE` CHAR(1) COMMENT '类型',
    `R_RECEIVER` INT(11) COMMENT '接收者编号',
    `S_RECEIVER` VARCHAR(300) COMMENT '接收者名称',
    `T_READ` DATETIME COMMENT '阅读时间',
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

/*通知公告*/
CREATE OR REPLACE VIEW V_NOTICE AS
SELECT T1.*,T2.R_RECEIVER,T2.S_RECEIVER,T2.Z_TYPE
FROM SYS_NOTICE T1
INNER JOIN SYS_NOTICE_RECEIVER T2 ON T1.SID=T2.R_NOTICE
WHERE T1.R_PUBLISH IS NOT NULL
ORDER BY T1.T_PUBLISH DESC;
