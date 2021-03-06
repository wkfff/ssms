-- 隐患监管定时任务
DROP TABLE IF EXISTS `SYS_TASK_TROUBLE_SUPERVISION`;
CREATE TABLE `SYS_TASK_TROUBLE_SUPERVISION` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `R_TEMPLATE` INT(11) COMMENT '所属模板',
    `R_TENANT` INT(11) COMMENT '租户编号',
    `S_TENANT` VARCHAR(300) COMMENT '租户名称',
    `P_TENANT` CHAR(1) COMMENT '租户类型',
    `P_PROFESSION` INT(11) COMMENT '所属专业ID',
    `N_FINISH_TROUBLE` INT(11) COMMENT '已整改隐患数',
    `N_ALL_TROUBLE` INT(11) COMMENT '隐患总数',
    `T_CREATE`  DATE NULL  COMMENT '创建日期',
     PRIMARY KEY (`SID`)
);