--------------------------------------------------------
-- 模板文件配置表: 安全附件定期检查检验记录
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_STDTMP_FILE_09`;
CREATE TABLE `SYS_STDTMP_FILE_09` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `R_TEMPLATE` INT(11) COMMENT '所属模板',
    `R_TMPFILE` INT(11) NOT NULL COMMENT '模板ID',
    `C_NAME` VARCHAR(300) COMMENT '安全附件名称',
    `C_SPEC` VARCHAR(100) COMMENT '型号',
    `C_NO_FACTORY` VARCHAR(100) COMMENT '出厂编号',
    `C_POSITION` VARCHAR(500) COMMENT '所属特种设备安装位置',
    `T_TEST_LAST` DATE COMMENT '检验日期',
    `T_TEST_NEXT` DATE COMMENT '下次检验日期',
    `C_NO_REP` VARCHAR(100) COMMENT '检验报告编号',
    `C_TEST_CON` VARCHAR(3000) COMMENT '检验结论',
    `C_TEST_UNIT` VARCHAR(200) COMMENT '检验单位',
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
-- 模板文件配置归档表: 安全附件定期检查检验记录
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_STDTMP_FILE_09_ARCH`;
CREATE TABLE `SYS_STDTMP_FILE_09_ARCH` (
    `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID',
    `SID` INT(11) COMMENT '归档前的ID',
    `R_TEMPLATE` INT(11) COMMENT '所属模板',
    `R_TMPFILE` INT(11) NOT NULL COMMENT '模板ID',
    `C_NAME` VARCHAR(300) COMMENT '安全附件名称',
    `C_SPEC` VARCHAR(100) COMMENT '型号',
    `C_NO_FACTORY` VARCHAR(100) COMMENT '出厂编号',
    `C_POSITION` VARCHAR(500) COMMENT '所属特种设备安装位置',
    `T_TEST_LAST` DATE COMMENT '检验日期',
    `T_TEST_NEXT` DATE COMMENT '下次检验日期',
    `C_NO_REP` VARCHAR(100) COMMENT '检验报告编号',
    `C_TEST_CON` VARCHAR(3000) COMMENT '检验结论',
    `C_TEST_UNIT` VARCHAR(200) COMMENT '检验单位',
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

DROP TABLE IF EXISTS `SSM_STDTMP_FILE_09`;
CREATE TABLE `SSM_STDTMP_FILE_09` (
  `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TENANT` INT(11) COMMENT '租户编号',
  `S_TENANT` VARCHAR(300) COMMENT '租户编号',
  `P_TENANT` CHAR(1) COMMENT '租户编号',
  `R_TEMPLATE` INT(11) COMMENT '所属模板ID',
  `R_TMPFOLDER` INT(11) COMMENT '所属模板目录',
  `P_PROFESSION` INT(11) COMMENT '所属专业ID',
  `R_TMPFILE` INT(11) NOT NULL COMMENT '模板ID',
  `C_NAME` VARCHAR(300) COMMENT '安全附件名称',
  `C_SPEC` VARCHAR(100) COMMENT '型号',
  `C_NO_FACTORY` VARCHAR(100) COMMENT '出厂编号',
  `C_POSITION` VARCHAR(500) COMMENT '所属特种设备安装位置',
  `T_TEST_LAST` DATE COMMENT '检验日期',
  `T_TEST_NEXT` DATE COMMENT '下次检验日期',
  `C_NO_REP` VARCHAR(100) COMMENT '检验报告编号',
  `C_TEST_CON` VARCHAR(3000) COMMENT '检验结论',
  `C_TEST_UNIT` VARCHAR(200) COMMENT '检验单位',
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

DROP TABLE IF EXISTS `SSM_STDTMP_FILE_09_ARCH`;
CREATE TABLE `SSM_STDTMP_FILE_09_ARCH` (
  `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '归档用主键',
  `N_ARCH_VERSION` INT(11) NOT NULL DEFAULT '0' COMMENT '归档版本',
  `SID` INT(11) COMMENT '自增主键',
  `R_TENANT` INT(11) COMMENT '租户编号',
  `S_TENANT` VARCHAR(300) COMMENT '租户编号',
  `P_TENANT` CHAR(1) COMMENT '租户编号',
  `R_TEMPLATE` INT(11) COMMENT '所属模板ID',
  `R_TMPFOLDER` INT(11) COMMENT '所属模板目录',
  `P_PROFESSION` INT(11) COMMENT '所属专业ID',
  `R_TMPFILE` INT(11) NOT NULL COMMENT '模板ID',
  `C_NAME` VARCHAR(300) COMMENT '安全附件名称',
  `C_SPEC` VARCHAR(100) COMMENT '型号',
  `C_NO_FACTORY` VARCHAR(100) COMMENT '出厂编号',
  `C_POSITION` VARCHAR(500) COMMENT '所属特种设备安装位置',
  `T_TEST_LAST` DATE COMMENT '检验日期',
  `T_TEST_NEXT` DATE COMMENT '下次检验日期',
  `C_NO_REP` VARCHAR(100) COMMENT '检验报告编号',
  `C_TEST_CON` VARCHAR(3000) COMMENT '检验结论',
  `C_TEST_UNIT` VARCHAR(200) COMMENT '检验单位',
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
