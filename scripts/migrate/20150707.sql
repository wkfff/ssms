ALTER TABLE `sys_todo`
    ADD COLUMN `P_PROFESSION` INT(11) NULL DEFAULT NULL COMMENT '专业编号' AFTER `P_TENANT`,
    ADD COLUMN `R_TEMPLATE` INT(11) NULL DEFAULT NULL COMMENT '模板ID' AFTER `P_PROFESSION`;

ALTER TABLE `sys_done`
    ADD COLUMN `P_PROFESSION` INT(11) NULL DEFAULT NULL COMMENT '专业编号' AFTER `P_TENANT`,
    ADD COLUMN `R_TEMPLATE` INT(11) NULL DEFAULT NULL COMMENT '模板ID' AFTER `P_PROFESSION`;

DROP TABLE `SSM_NOTICE`;
DROP TABLE `SSM_NOTICE_COMMENTS`;