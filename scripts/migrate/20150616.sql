-- 评审结果表
DROP TABLE IF EXISTS SSM_REVIEW_RESULT;
CREATE TABLE SSM_REVIEW_RESULT
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TENANT_E`  INT(11) COMMENT '企业编号',
  `S_TENANT_E`  VARCHAR(300) COMMENT '企业名称',
  `P_PROFESSION`  INT(11) COMMENT '企业专业编号',
  `S_PROFESSION`  INT(11) COMMENT '企业专业',
  `T_START`			DATETIME	 COMMENT '评审开始时间',
  `T_END`			DATETIME	 COMMENT '评审结束时间',
  `C_CONTENT`  LONGTEXT COMMENT '评审内容',
  `C_SUM`  LONGTEXT COMMENT '评分汇总表',
  `C_DED`  LONGTEXT COMMENT '扣分汇总表',
  `C_REP`  LONGTEXT COMMENT '评审报告',
  `C_CERT`  LONGTEXT COMMENT '评审证书',
  `R_CREATE`  INT(11) COMMENT '创建人编号',
  `S_CREATE`  VARCHAR(50) COMMENT '创建人',
  `T_CREATE`  DATETIME COMMENT '创建时间',
  `R_UPDATE`  INT(11) COMMENT '更新人编号',
  `S_UPDATE`  VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`  TIMESTAMP COMMENT '更新时间',
  `R_TENANT`  INT(11) COMMENT '评审机构编号',
  `S_TENANT`  VARCHAR(300) COMMENT '评审机构名称',
  `P_TENANT`  CHAR(1) COMMENT '租户类型',
  `N_STATE`   INT COMMENT '状态',
  `S_STATE`   INT COMMENT '状态',
  `B_DELETE`  CHAR(1) COMMENT '删除标志',
  `N_INDEX`   INT COMMENT '排序',
  `N_VERSION` INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);


-- 模板版本库:
DROP TABLE IF EXISTS SSM_TEMPLATE_VERSION;
CREATE TABLE SSM_TEMPLATE_VERSION
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TEMPLATE`  INT(11) COMMENT '模板编号',
  `P_PROFESSION`  INT(11) COMMENT '企业专业编号',
  `R_CREATE`  INT(11) COMMENT '创建人编号',
  `S_CREATE`  VARCHAR(50) COMMENT '创建人',
  `T_CREATE`  DATETIME COMMENT '创建时间',
  `R_UPDATE`  INT(11) COMMENT '更新人编号',
  `S_UPDATE`  VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`  TIMESTAMP COMMENT '更新时间',
  `R_TENANT`  INT(11) COMMENT '企业编号',
  `S_TENANT`  VARCHAR(300) COMMENT '企业名称',
  `P_TENANT`  CHAR(1) COMMENT '租户类型',
  `N_STATE`   INT COMMENT '状态',
  `B_DELETE`  CHAR(1) COMMENT '删除标志',
  `N_INDEX`   INT COMMENT '排序',
  `N_VERSION` INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);


-- 达标体系模板文件夹(归档):
DROP TABLE IF EXISTS SSM_STDTMP_FOLDER_ARCH;
CREATE TABLE SSM_STDTMP_FOLDER_ARCH
(
  `SID`        INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`     VARCHAR(300) COMMENT '文件夹名称',
  `R_TEMPLATE` INT(11) COMMENT '所属模板SID',
  `R_SID`      INT(11)          DEFAULT 0 COMMENT '父文件夹',
  `R_SOURCE`   INT(11) COMMENT '来源SID，记录复制自哪条记录。',
  `C_DESC`     VARCHAR(2000) COMMENT '描述',
  `C_LOGO`     VARCHAR(200) COMMENT 'LOGO图',
  `R_CREATE`   INT(11) COMMENT '创建人编号',
  `S_CREATE`   VARCHAR(50) COMMENT '创建人',
  `T_CREATE`   DATETIME COMMENT '创建时间',
  `R_UPDATE`   INT(11) COMMENT '更新人编号',
  `S_UPDATE`   VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`   TIMESTAMP COMMENT '更新时间',
  `R_TENANT`   INT(11) COMMENT '租户编号',
  `S_TENANT`   VARCHAR(300) COMMENT '租户名称',
  `P_TENANT`   CHAR(1) COMMENT '租户类型',
  `N_STATE`    INT COMMENT '状态',
  `B_DELETE`   CHAR(1) COMMENT '删除标志',
  `N_INDEX`    INT COMMENT '排序',
  `N_VERSION`  INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);
-- 达标体系模板文件(归档):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_ARCH;
CREATE TABLE SSM_STDTMP_FILE_ARCH
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`    VARCHAR(300) COMMENT '名称',
  `R_SOURCE`  INT(11) COMMENT '来源SID，记录复制自哪条记录。',
  `C_DESC`    VARCHAR(2000) COMMENT '描述',
  `R_SID`     INT(11) COMMENT '所属文件夹SID',
  `S_NAME`    VARCHAR(300) COMMENT '所属文件夹名称',
  `B_REMIND`  CHAR(1)          DEFAULT 1 COMMENT '是否提醒',
  `N_CYCLE`   INT COMMENT '更新周期（数量）',
  `P_CYCLE`   VARCHAR(32) COMMENT '周期单位SID',
  `S_CYCLE`   VARCHAR(100) COMMENT '周期单位名称',
  `C_EXPLAIN` VARCHAR(2000) COMMENT '政策解读',
  `P_TMPFILE` VARCHAR(32) COMMENT '文件类型编码',
  `S_TMPFILE` VARCHAR(100) COMMENT '文件类型名称',
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
-- 达标体系制度文件模板配置(归档):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_01_ARCH;
CREATE TABLE SSM_STDTMP_FILE_01_ARCH
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TMPFILE` INT(11) COMMENT '模板ID',
  `C_NAME`    VARCHAR(300) COMMENT '名称',
  `B_CONTROL` CHAR(1)          DEFAULT 0 COMMENT '是否受控',
  `C_NUMBER`  VARCHAR(32) COMMENT '文件编号',
  `C_DEPT_01` VARCHAR(200) COMMENT '执行部门',
  `C_DEPT_02` VARCHAR(200) COMMENT '监督部门',
  `T_DATE_01` DATE COMMENT '编制日期',
  `T_DATE_02` DATE COMMENT '审核日期',
  `T_DATE_03` DATE COMMENT '批准日期',
  `P_SATUS`   VARCHAR(32) COMMENT '文件状态编码',
  `S_SATUS`   VARCHAR(100) COMMENT '文件状态名称',
  `N_TIMES`   INT              DEFAULT 0 COMMENT '修订次数',
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
-- 达标体系通知文件模板配置(归档):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_02_ARCH;
CREATE TABLE SSM_STDTMP_FILE_02_ARCH
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TMPFILE` INT(11) COMMENT '模板ID',
  `C_NAME`    VARCHAR(300) COMMENT '名称',
  `C_NUMBER`  VARCHAR(32) COMMENT '文件编号',
  `C_DEPT_01` VARCHAR(500) COMMENT '发布部门',
  `C_DEPT_02` VARCHAR(500) COMMENT '主送部门',
  `C_DEPT_03` VARCHAR(500) COMMENT '抄送部门',
  `T_DATE_01` DATE COMMENT '发布日期',
  `P_SATUS`   VARCHAR(32) COMMENT '文件状态编码',
  `S_SATUS`   VARCHAR(100) COMMENT '文件状态名称',
  `N_TIMES`   INT              DEFAULT 0 COMMENT '修订次数',
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
-- 达标体系执行文件模板配置(归档):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_03_ARCH;
CREATE TABLE SSM_STDTMP_FILE_03_ARCH
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TMPFILE` INT(11) COMMENT '模板ID',
  `C_NAME`    VARCHAR(300) COMMENT '名称',
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
-- 达标体系培训执行文件模板配置(归档):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_04_ARCH;
CREATE TABLE SSM_STDTMP_FILE_04_ARCH
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TMPFILE` INT(11) COMMENT '模板ID',
  `C_NAME`    VARCHAR(300) COMMENT '名称',
  `T_TIME`    DATE COMMENT '培训日期',
  `C_USER_01` VARCHAR(300) COMMENT '教育人',
  `C_ADDR`    VARCHAR(200) COMMENT '培训地点',
  `P_TYPE`    VARCHAR(32) COMMENT '培训种类',
  `S_TYPE`    VARCHAR(100) COMMENT '培训种类',
  `N_TIME`    INT COMMENT '学时',
  `C_USER_02` VARCHAR(300) COMMENT '记录人',
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
-- 达标体系培训文件模板配置(归档):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_05_ARCH;
CREATE TABLE SSM_STDTMP_FILE_05_ARCH
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TMPFILE` INT(11) COMMENT '模板ID',
  `C_NAME`    VARCHAR(300) COMMENT '名称',
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
-- 隐患汇总登记台帐模板配置(归档):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_06_ARCH;
CREATE TABLE SSM_STDTMP_FILE_06_ARCH
(
  `SID`             INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TMPFILE`       INT(11) COMMENT '模板ID',
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
-- 特种作业人员持证登记表模板配置(归档):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_07_ARCH;
CREATE TABLE SSM_STDTMP_FILE_07_ARCH
(
  `SID`           INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TMPFILE`     INT(11) COMMENT '模板ID',
  `C_NAME`        VARCHAR(300) COMMENT '姓名',
  `P_SEX`         VARCHAR(32) COMMENT '性别',
  `S_SEX`         VARCHAR(100) COMMENT '性别',
  `C_CARD`        VARCHAR(30) COMMENT '身份证号码',
  `C_DEPT`        VARCHAR(200) COMMENT '所在部门',
  `C_WORKTYPE`    VARCHAR(100) COMMENT '工种',
  `C_AUTH`        VARCHAR(300) COMMENT '发证机关',
  `T_CERT_GET`    DATE COMMENT '取证时间',
  `C_CERT`        VARCHAR(100) COMMENT '证书编号',
  `T_CERT_REVIEW` DATE COMMENT '复审时间',
  `R_CREATE`      INT(11) COMMENT '创建人编号',
  `S_CREATE`      VARCHAR(50) COMMENT '创建人',
  `T_CREATE`      DATETIME COMMENT '创建时间',
  `R_UPDATE`      INT(11) COMMENT '更新人编号',
  `S_UPDATE`      VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`      TIMESTAMP COMMENT '更新时间',
  `R_TENANT`      INT(11) COMMENT '租户编号',
  `S_TENANT`      VARCHAR(300) COMMENT '租户名称',
  `P_TENANT`      CHAR(1) COMMENT '租户类型',
  `N_STATE`       INT COMMENT '状态',
  `B_DELETE`      CHAR(1) COMMENT '删除标志',
  `N_INDEX`       INT COMMENT '排序',
  `N_VERSION`     INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);
-- 特种设备台账及定期检验记录模板配置(归档):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_08_ARCH;
CREATE TABLE SSM_STDTMP_FILE_08_ARCH
(
  `SID`          INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TMPFILE`    INT(11) COMMENT '模板ID',
  `C_NAME`       VARCHAR(300) COMMENT '设备名称',
  `C_NO_REG`     VARCHAR(100) COMMENT '登记证号',
  `C_SPEC`       VARCHAR(100) COMMENT '规格/型号',
  `C_MAKE_UNIT`  VARCHAR(100) COMMENT '制造单位',
  `C_NO_FACTORY` VARCHAR(100) COMMENT '出厂编号',
  `C_DEPT`       VARCHAR(200) COMMENT '使用部门',
  `T_TEST_LAST`  DATE COMMENT '最新检验日期',
  `C_NO_REP`     VARCHAR(100) COMMENT '检验报告编号',
  `T_TEST_NEXT`  DATE COMMENT '下次检验日期',
  `C_TEST_CON`   VARCHAR(3000) COMMENT '检验结论',
  `C_TEST_UNIT`  VARCHAR(200) COMMENT '检验单位',
  `R_CREATE`     INT(11) COMMENT '创建人编号',
  `S_CREATE`     VARCHAR(50) COMMENT '创建人',
  `T_CREATE`     DATETIME COMMENT '创建时间',
  `R_UPDATE`     INT(11) COMMENT '更新人编号',
  `S_UPDATE`     VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`     TIMESTAMP COMMENT '更新时间',
  `R_TENANT`     INT(11) COMMENT '租户编号',
  `S_TENANT`     VARCHAR(300) COMMENT '租户名称',
  `P_TENANT`     CHAR(1) COMMENT '租户类型',
  `N_STATE`      INT COMMENT '状态',
  `B_DELETE`     CHAR(1) COMMENT '删除标志',
  `N_INDEX`      INT COMMENT '排序',
  `N_VERSION`    INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);
-- 安全附件定期检查检验记录模板配置(归档):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_09_ARCH;
CREATE TABLE SSM_STDTMP_FILE_09_ARCH
(
  `SID`          INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_TMPFILE`    INT(11) COMMENT '模板ID',
  `C_NAME`       VARCHAR(300) COMMENT '安全附件名称',
  `C_SPEC`       VARCHAR(100) COMMENT '型号',
  `C_NO_FACTORY` VARCHAR(100) COMMENT '出厂编号',
  `C_DEPT`       VARCHAR(500) COMMENT '所属特种设备安装位置',
  `T_TEST_LAST`  DATE COMMENT '检验日期',
  `T_TEST_NEXT`  DATE COMMENT '下次检验日期',
  `C_NO_REP`     VARCHAR(100) COMMENT '检验报告编号',
  `C_TEST_CON`   VARCHAR(3000) COMMENT '检验结论',
  `C_TEST_UNIT`  VARCHAR(200) COMMENT '检验单位',
  `R_CREATE`     INT(11) COMMENT '创建人编号',
  `S_CREATE`     VARCHAR(50) COMMENT '创建人',
  `T_CREATE`     DATETIME COMMENT '创建时间',
  `R_UPDATE`     INT(11) COMMENT '更新人编号',
  `S_UPDATE`     VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`     TIMESTAMP COMMENT '更新时间',
  `R_TENANT`     INT(11) COMMENT '租户编号',
  `S_TENANT`     VARCHAR(300) COMMENT '租户名称',
  `P_TENANT`     CHAR(1) COMMENT '租户类型',
  `N_STATE`      INT COMMENT '状态',
  `B_DELETE`     CHAR(1) COMMENT '删除标志',
  `N_INDEX`      INT COMMENT '排序',
  `N_VERSION`    INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);
