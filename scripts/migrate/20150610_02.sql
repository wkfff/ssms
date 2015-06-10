CREATE TABLE `SSM_STDTMP_FILE_01_ITEM` (
   `SID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `R_TMPFILE_01` INT(11) NOT NULL,
   `C_NAME` VARCHAR(300) DEFAULT NULL COMMENT '名称',
   `T_DATE_01` DATE DEFAULT NULL COMMENT '年审日期',
   `C_COMMENT` VARCHAR(300) DEFAULT NULL COMMENT '备注',
   `R_CREATE` INT(11) DEFAULT NULL COMMENT '创建人编号',
   `S_CREATE` VARCHAR(50) DEFAULT NULL COMMENT '创建人',
   `T_CREATE` DATETIME DEFAULT NULL COMMENT '创建时间',
   `R_UPDATE` INT(11) DEFAULT NULL COMMENT '更新人编号',
   `S_UPDATE` VARCHAR(50) DEFAULT NULL COMMENT '更新人',
   `T_UPDATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   `R_TENANT` INT(11) DEFAULT NULL COMMENT '租户编号',
   `S_TENANT` VARCHAR(300) DEFAULT NULL COMMENT '租户名称',
   `P_TENANT` CHAR(1) DEFAULT NULL COMMENT '租户类型',
   `N_STATE` INT(11) DEFAULT NULL COMMENT '状态',
   `B_DELETE` CHAR(1) DEFAULT NULL COMMENT '删除标志',
   `N_INDEX` INT(11) DEFAULT NULL COMMENT '排序',
   `N_VERSION` INT(11) DEFAULT NULL COMMENT '版本',
   PRIMARY KEY  (`SID`)
 ) ENGINE=MYISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8