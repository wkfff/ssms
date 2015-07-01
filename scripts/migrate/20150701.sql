-- 移除无用的字段
ALTER TABLE `ssm_stdtmp_file`
    DROP COLUMN `R_SOURCE`;
ALTER TABLE `ssm_stdtmp_folder`
    DROP COLUMN `R_SOURCE`;

-- 创建归档表

create table sys_template_arch like sys_template;
create table sys_stdtmp_folder_arch like sys_stdtmp_folder;
create table sys_stdtmp_file_arch like sys_stdtmp_file;
create table sys_stdtmp_file_01_arch like sys_stdtmp_file_01;
create table sys_stdtmp_file_02_arch like sys_stdtmp_file_02;
create table sys_stdtmp_file_03_arch like sys_stdtmp_file_03;
create table sys_stdtmp_file_04_arch like sys_stdtmp_file_04;
create table sys_stdtmp_file_06_arch like sys_stdtmp_file_06;
create table sys_stdtmp_file_07_arch like sys_stdtmp_file_07;
create table sys_stdtmp_file_08_arch like sys_stdtmp_file_08;
create table sys_stdtmp_file_09_arch like sys_stdtmp_file_09;

ALTER TABLE `sys_template_arch`
    ADD COLUMN `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID' FIRST,
    CHANGE COLUMN `SID` `SID` INT(11) NULL COMMENT '归档前的ID' AFTER `UID`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`UID`);
ALTER TABLE `sys_stdtmp_folder_arch`
    ADD COLUMN `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID' FIRST,
    CHANGE COLUMN `SID` `SID` INT(11) NULL COMMENT '归档前的ID' AFTER `UID`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`UID`);
ALTER TABLE `sys_stdtmp_file_arch`
    ADD COLUMN `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID' FIRST,
    CHANGE COLUMN `SID` `SID` INT(11) NULL COMMENT '归档前的ID' AFTER `UID`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`UID`);
ALTER TABLE `sys_stdtmp_file_01_arch`
    ADD COLUMN `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID' FIRST,
    CHANGE COLUMN `SID` `SID` INT(11) NULL COMMENT '归档前的ID' AFTER `UID`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`UID`);
ALTER TABLE `sys_stdtmp_file_02_arch`
    ADD COLUMN `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID' FIRST,
    CHANGE COLUMN `SID` `SID` INT(11) NULL COMMENT '归档前的ID' AFTER `UID`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`UID`);
ALTER TABLE `sys_stdtmp_file_03_arch`
    ADD COLUMN `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID' FIRST,
    CHANGE COLUMN `SID` `SID` INT(11) NULL COMMENT '归档前的ID' AFTER `UID`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`UID`);
ALTER TABLE `sys_stdtmp_file_04_arch`
    ADD COLUMN `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID' FIRST,
    CHANGE COLUMN `SID` `SID` INT(11) NULL COMMENT '归档前的ID' AFTER `UID`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`UID`);
ALTER TABLE `sys_stdtmp_file_06_arch`
    ADD COLUMN `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID' FIRST,
    CHANGE COLUMN `SID` `SID` INT(11) NULL COMMENT '归档前的ID' AFTER `UID`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`UID`);
ALTER TABLE `sys_stdtmp_file_07_arch`
    ADD COLUMN `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID' FIRST,
    CHANGE COLUMN `SID` `SID` INT(11) NULL COMMENT '归档前的ID' AFTER `UID`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`UID`);
ALTER TABLE `sys_stdtmp_file_08_arch`
    ADD COLUMN `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID' FIRST,
    CHANGE COLUMN `SID` `SID` INT(11) NULL COMMENT '归档前的ID' AFTER `UID`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`UID`);
ALTER TABLE `sys_stdtmp_file_09_arch`
    ADD COLUMN `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID' FIRST,
    CHANGE COLUMN `SID` `SID` INT(11) NULL COMMENT '归档前的ID' AFTER `UID`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`UID`);