ALTER TABLE `sys_attach_text`
MODIFY COLUMN `C_CONTENT`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容' AFTER `R_FIELD`;