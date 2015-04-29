-- 系统导航配置:
DROP TABLE IF EXISTS SYS_NAV;
CREATE TABLE SYS_NAV
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_SID`     INT(11) COMMENT '父导航',
  `C_NAME`    VARCHAR(300) COMMENT '导航名称',
  `C_URL`     VARCHAR(2000) COMMENT '导航URL',
  `C_DESC`    VARCHAR(2000) COMMENT '导航描述',
  'C_ICON'    VARCHAR(200) COMMENT '图标',
  `R_CREATE`  INT(11) COMMENT '创建人编号',
  `S_CREATE`  VARCHAR(50) COMMENT '创建人',
  `T_CREATE`  DATETIME COMMENT '创建时间',
  `R_UPDATE`  INT(11) COMMENT '更新人编号',
  `S_UPDATE`  VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`  TIMESTAMP COMMENT '更新时间',
  `R_TENANT`  INT(11) COMMENT '租户编号',
  `S_TENANT`  VARCHAR(300) COMMENT '租户名称',
  `P_TENANT`  CHAR(1) COMMENT '租户类型',
  `N_STATE`   INT COMMENT '状态',
  `B_DELETE`  CHAR(1) COMMENT '删除标志',
  `N_INDEX`   INT COMMENT '排序',
  `N_VERSION` INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);
