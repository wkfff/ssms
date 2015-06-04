-- 隐患汇总登记台帐模板配置:
DROP TABLE IF EXISTS SYS_STDTMP_FILE_06;
CREATE TABLE SYS_STDTMP_FILE_06
(
  `SID`             INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`          VARCHAR(300) COMMENT '存在问题',
  `C_EXAMINER`      VARCHAR(300) COMMENT '检查人',
  `T_EXAMINE`       DATE COMMENT '检查时间',
  `P_LEVEL`         VARCHAR(32) COMMENT '隐患等级',
  `C_MEASURE`       VARCHAR(2000) COMMENT '整改措施',
  `C_RESPONSIBLE`   VARCHAR(300) COMMENT '整改负责人（部门）',
  `T_RECTIFICATION` DATE COMMENT '要求整改日期',
  `C_ACCEPTANCE`    VARCHAR(300) COMMENT '验收人',
  `R_CREATE`        INT(11) COMMENT '创建人编号',
  `S_CREATE`        VARCHAR(50) COMMENT '创建人',
  `T_CREATE`        DATETIME COMMENT '创建时间',
  `R_UPDATE`        INT(11) COMMENT '更新人编号',
  `S_UPDATE`        VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`        TIMESTAMP COMMENT '更新时间',
  `R_TENANT`        INT(11) COMMENT '租户编号',
  `S_TENANT`        VARCHAR(300) COMMENT '租户名称',
  `P_TENANT`        CHAR(1) COMMENT '租户类型',
  `N_STATE`         INT COMMENT '状态',
  `B_DELETE`        CHAR(1) COMMENT '删除标志',
  `N_INDEX`         INT COMMENT '排序',
  `N_VERSION`       INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);
-- 隐患汇总登记台帐模板配置:
DROP TABLE IF EXISTS SSM_STDTMP_FILE_06;
CREATE TABLE SSM_STDTMP_FILE_06
(
  `SID`             INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`          VARCHAR(300) COMMENT '存在问题',
  `C_EXAMINER`      VARCHAR(300) COMMENT '检查人',
  `T_EXAMINE`       DATE COMMENT '检查时间',
  `P_LEVEL`         VARCHAR(32) COMMENT '隐患等级',
  `C_MEASURE`       VARCHAR(2000) COMMENT '整改措施',
  `C_RESPONSIBLE`   VARCHAR(300) COMMENT '整改负责人（部门）',
  `T_RECTIFICATION` DATE COMMENT '要求整改日期',
  `C_ACCEPTANCE`    VARCHAR(300) COMMENT '验收人',
  `R_CREATE`        INT(11) COMMENT '创建人编号',
  `S_CREATE`        VARCHAR(50) COMMENT '创建人',
  `T_CREATE`        DATETIME COMMENT '创建时间',
  `R_UPDATE`        INT(11) COMMENT '更新人编号',
  `S_UPDATE`        VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`        TIMESTAMP COMMENT '更新时间',
  `R_TENANT`        INT(11) COMMENT '租户编号',
  `S_TENANT`        VARCHAR(300) COMMENT '租户名称',
  `P_TENANT`        CHAR(1) COMMENT '租户类型',
  `N_STATE`         INT COMMENT '状态',
  `B_DELETE`        CHAR(1) COMMENT '删除标志',
  `N_INDEX`         INT COMMENT '排序',
  `N_VERSION`       INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);