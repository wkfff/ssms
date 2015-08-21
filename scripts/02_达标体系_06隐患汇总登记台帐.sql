--------------------------------------------------------
-- 模板文件配置表: 隐患汇总登记台帐
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_STDTMP_FILE_06`;
CREATE TABLE `SYS_STDTMP_FILE_06` (
    `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `R_TEMPLATE` INT(11) COMMENT '所属模板',
    `R_TMPFILE` INT(11) NOT NULL,
    `C_NAME` VARCHAR(300) COMMENT '存在问题',
    `C_EXAMINER` VARCHAR(300) COMMENT '检查人',
    `T_EXAMINE` DATE COMMENT '检查时间',
    `P_TYPE` VARCHAR(32) COMMENT '隐患类型(预防、纠正)',
    `C_DEPT` VARCHAR(200) COMMENT '隐患所在区域/部门',
    `P_LEVEL` VARCHAR(32) COMMENT '隐患等级',
    `C_MEASURE` VARCHAR(2000) COMMENT '整改措施',
    `C_RESPONSIBLE` VARCHAR(300) COMMENT '整改部门',
    `T_RECTIFICATION` DATE COMMENT '要求整改日期',
    `C_PLANT` VARCHAR(2000) COMMENT '治理方案',
    `C_ACCEPTANCE_DEPT` VARCHAR(300) COMMENT '验收部门',
    `C_ACCEPTANCE` VARCHAR(300) COMMENT '验收人',
    `T_ACCEPTANCE` DATE COMMENT '验收时间',
    `B_FINISH` CHAR(1) COMMENT '隐患闭环情况',
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
-- 模板文件配置归档表: 隐患汇总登记台帐
--------------------------------------------------------
DROP TABLE IF EXISTS `SYS_STDTMP_FILE_06_ARCH`;
CREATE TABLE `SYS_STDTMP_FILE_06_ARCH` (
    `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '内部ID',
    `SID` INT(11) COMMENT '归档前的ID',
    `R_TEMPLATE` INT(11) COMMENT '所属模板',
    `R_TMPFILE` INT(11) NOT NULL,
    `C_NAME` VARCHAR(300) COMMENT '存在问题',
    `C_EXAMINER` VARCHAR(300) COMMENT '检查人',
    `T_EXAMINE` DATE COMMENT '检查时间',
    `P_TYPE` VARCHAR(32) COMMENT '隐患类型(预防、纠正)',
    `C_DEPT` VARCHAR(200) COMMENT '隐患所在区域/部门',
    `P_LEVEL` VARCHAR(32) COMMENT '隐患等级',
    `C_MEASURE` VARCHAR(2000) COMMENT '整改措施',
    `C_RESPONSIBLE` VARCHAR(300) COMMENT '整改部门',
    `T_RECTIFICATION` DATE COMMENT '要求整改日期',
    `C_PLANT` VARCHAR(2000) COMMENT '治理方案',
    `C_ACCEPTANCE_DEPT` VARCHAR(300) COMMENT '验收部门',
    `C_ACCEPTANCE` VARCHAR(300) COMMENT '验收人',
    `T_ACCEPTANCE` DATE COMMENT '验收时间',
    `B_FINISH` CHAR(1) COMMENT '隐患闭环情况',
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

DROP TABLE IF EXISTS `SSM_STDTMP_FILE_06`;
CREATE TABLE `SSM_STDTMP_FILE_06` (
  `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TENANT` INT(11) COMMENT '租户编号',
  `S_TENANT` VARCHAR(300) COMMENT '租户编号',
  `P_TENANT` CHAR(1) COMMENT '租户编号',
  `R_TEMPLATE` INT(11) COMMENT '所属模板ID',
  `R_TMPFOLDER` INT(11) COMMENT '所属模板目录',
  `P_PROFESSION` INT(11) COMMENT '所属专业ID',
  `R_TMPFILE` INT(11) NOT NULL,
  `C_NAME` VARCHAR(300) COMMENT '存在问题',
  `C_EXAMINER` VARCHAR(300) COMMENT '检查人',
  `T_EXAMINE` DATE COMMENT '检查时间',
  `P_TYPE` VARCHAR(32) COMMENT '隐患类型(预防、纠正)',
  `C_DEPT` VARCHAR(200) COMMENT '隐患所在区域/部门',
  `P_LEVEL` VARCHAR(32) COMMENT '隐患等级',
  `C_MEASURE` VARCHAR(2000) COMMENT '整改措施',
  `C_RESPONSIBLE` VARCHAR(300) COMMENT '整改部门',
  `T_RECTIFICATION` DATE COMMENT '要求整改日期',
  `C_PLANT` VARCHAR(2000) COMMENT '治理方案',
  `C_ACCEPTANCE_DEPT` VARCHAR(300) COMMENT '验收部门',
  `C_ACCEPTANCE` VARCHAR(300) COMMENT '验收人',
  `T_ACCEPTANCE` DATE COMMENT '验收时间',
  `B_FINISH` CHAR(1) COMMENT '隐患闭环情况',
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

DROP TABLE IF EXISTS `SSM_STDTMP_FILE_06_ARCH`;
CREATE TABLE `SSM_STDTMP_FILE_06_ARCH` (
  `UID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '归档用主键',
  `N_ARCH_VERSION` INT(11) NOT NULL DEFAULT '0' COMMENT '归档版本',
  `SID` INT(11) COMMENT '自增主键',
  `R_TENANT` INT(11) COMMENT '租户编号',
  `S_TENANT` VARCHAR(300) COMMENT '租户编号',
  `P_TENANT` CHAR(1) COMMENT '租户编号',
  `R_TEMPLATE` INT(11) COMMENT '所属模板ID',
  `R_TMPFOLDER` INT(11) COMMENT '所属模板目录',
  `P_PROFESSION` INT(11) COMMENT '所属专业ID',
  `R_TMPFILE` INT(11) NOT NULL,
  `C_NAME` VARCHAR(300) COMMENT '存在问题',
  `C_EXAMINER` VARCHAR(300) COMMENT '检查人',
  `T_EXAMINE` DATE COMMENT '检查时间',
  `P_TYPE` VARCHAR(32) COMMENT '隐患类型(预防、纠正)',
  `C_DEPT` VARCHAR(200) COMMENT '隐患所在区域/部门',
  `P_LEVEL` VARCHAR(32) COMMENT '隐患等级',
  `C_MEASURE` VARCHAR(2000) COMMENT '整改措施',
  `C_RESPONSIBLE` VARCHAR(300) COMMENT '整改部门',
  `T_RECTIFICATION` DATE COMMENT '要求整改日期',
  `C_PLANT` VARCHAR(2000) COMMENT '治理方案',
  `C_ACCEPTANCE_DEPT` VARCHAR(300) COMMENT '验收部门',
  `C_ACCEPTANCE` VARCHAR(300) COMMENT '验收人',
  `T_ACCEPTANCE` DATE COMMENT '验收时间',
  `B_FINISH` CHAR(1) COMMENT '隐患闭环情况',
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
