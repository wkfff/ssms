-- 企业租户表:
DROP TABLE IF EXISTS SYS_TENANT_E;
CREATE TABLE SYS_TENANT_E
(
  `SID`            INT(11)      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`         VARCHAR(300) NOT NULL COMMENT '企业名称',
  `C_CODE`         VARCHAR(32) COMMENT '租户编号',
  `P_STATE`        VARCHAR(32)           DEFAULT 1 COMMENT '企业状态编号',
  `S_STATE`        VARCHAR(100) COMMENT '企业状态',
  `P_PROVINCE`     VARCHAR(32) COMMENT '省份编号',
  `S_PROVINCE`     VARCHAR(100) COMMENT '省份',
  `P_CITY`         VARCHAR(32) COMMENT '地市编号',
  `S_CITY`         VARCHAR(50) COMMENT '地市',
  `P_COUNTY`       VARCHAR(32) COMMENT '县市编号',
  `S_COUNTY`       VARCHAR(100) COMMENT '县市',
  `C_ADDR`         VARCHAR(500) COMMENT '地址',
  `C_EMAIL`        VARCHAR(50) COMMENT '电子邮箱',
  `C_TEL`          VARCHAR(50) COMMENT '办公电话',
  `C_FAX`          VARCHAR(50) COMMENT '传真',
  `C_ZIP`          VARCHAR(50) COMMENT '邮政编码',
  `C_WEBSITE`      VARCHAR(200) COMMENT '企业网站',
  `C_NUMBER`       VARCHAR(100) COMMENT '营业执照注册号',
  `P_NATURE`       VARCHAR(32) COMMENT '企业性质编号',
  `S_NATURE`       VARCHAR(100) COMMENT '企业性质',
  `N_EMPLOYEE`     INT                   DEFAULT 0 COMMENT '员工总数',
  `N_SAFETY`       INT                   DEFAULT 0 COMMENT '专职安全人员数',
  `N_SPECIAL`      INT                   DEFAULT 0 COMMENT '特种作业人员数',
  `N_ASSETS`       DECIMAL(16, 4)        DEFAULT 0 COMMENT '固定资产',
  `N_INCOME`       DECIMAL(16, 4)        DEFAULT 0 COMMENT '主营业务收入',
  `C_SCOPE`        VARCHAR(500) COMMENT '营业范围',
  `C_SUMMARY`      VARCHAR(500) COMMENT '企业概述',
  `P_LEVEL`        VARCHAR(32) COMMENT '当前达标等级',
  `S_LEVEL`        VARCHAR(100) COMMENT '当前达标等级',
  `T_EXAMINE`      DATETIME COMMENT '最后评审时间',
  `T_EXAMINE_NEXT` DATETIME COMMENT '下次复审时间',
  `T_PAY`          DATETIME COMMENT '缴费日期',
  `T_PAY_NEXT`     DATETIME COMMENT '下次缴费日期',
  `R_CREATE`       INT(11) COMMENT '创建人编号',
  `S_CREATE`       VARCHAR(50) COMMENT '创建人',
  `T_CREATE`       DATETIME COMMENT '创建时间',
  `R_UPDATE`       INT(11) COMMENT '更新人编号',
  `S_UPDATE`       VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`       TIMESTAMP COMMENT '更新时间',
  `R_TENANT`       INT(11) COMMENT '租户编号',
  `S_TENANT`       VARCHAR(300) COMMENT '租户名称',
  `P_TENANT`       CHAR(1) COMMENT '租户类型',
  `N_STATE`        INT COMMENT '状态',
  `B_DELETE`       CHAR(1) COMMENT '删除标志',
  `N_INDEX`        INT COMMENT '排序',
  `N_VERSION`      INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);
-- 企业用户表:
DROP TABLE IF EXISTS SYS_TENANT_E_USER;
CREATE TABLE SYS_TENANT_E_USER
(
  `SID`         INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`      VARCHAR(100) COMMENT '姓名',
  `C_USER`      VARCHAR(100) COMMENT '用户名',
  `S_NAME`      VARCHAR(100) COMMENT '所属租户',
  `R_SID`        INT(11)    COMMENT '所属租户编号',
  `P_SEX`       VARCHAR(32)      DEFAULT 1 COMMENT '性别',
  `S_SEX`       VARCHAR(100) COMMENT '性别',
  `T_BIRTH`     DATE COMMENT '出生日期',
  `C_EMAIL`     VARCHAR(50) COMMENT '电子邮箱',
  `C_MOBILE`    VARCHAR(50) COMMENT '手机号',
  `C_TEL`       VARCHAR(50) COMMENT '办公电话',
  `C_DEPT`      VARCHAR(50) COMMENT '部门',
  `C_POSITION`  VARCHAR(50) COMMENT '职务',
  `C_CARD`      VARCHAR(50) COMMENT '身份证',
  `C_SCHOOL`    VARCHAR(100) COMMENT '毕业学校',
  `P_EDUCATION` VARCHAR(32) COMMENT '学历编号',
  `S_EDUCATION` VARCHAR(100) COMMENT '学历',
  `P_DEGREE`    VARCHAR(32) COMMENT '学位编号',
  `S_DEGREE`    VARCHAR(50) COMMENT '学位',
  `P_STATE`     VARCHAR(32)      DEFAULT 0 COMMENT '状态编号',
  `S_STATE`     VARCHAR(100) COMMENT '状态',
  `C_PASSWD`    VARCHAR(50) COMMENT '密码',
  `T_LOGIN`     DATETIME COMMENT '最后登录时间',
  `C_IP`        VARCHAR(50) COMMENT '最后登陆IP',
  `R_CREATE`    INT(11) COMMENT '创建人编号',
  `S_CREATE`    VARCHAR(50) COMMENT '创建人',
  `T_CREATE`    DATETIME COMMENT '创建时间',
  `R_UPDATE`    INT(11) COMMENT '更新人编号',
  `S_UPDATE`    VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`    TIMESTAMP COMMENT '更新时间',
  `R_TENANT`    INT(11) COMMENT '租户编号',
  `S_TENANT`    VARCHAR(300) COMMENT '租户名称',
  `P_TENANT`    CHAR(1) COMMENT '租户类型',
  `N_STATE`     INT COMMENT '状态',
  `B_DELETE`    CHAR(1) COMMENT '删除标志',
  `N_INDEX`     INT COMMENT '排序',
  `N_VERSION`   INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);
-- 企业联系方式表:
DROP TABLE IF EXISTS SYS_TENANT_E_CONTACT;
CREATE TABLE SYS_TENANT_E_CONTACT
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_ADDR`    VARCHAR(500) COMMENT '地址',
  `C_EMAIL`   VARCHAR(50) COMMENT '电子邮箱',
  `C_TEL`     VARCHAR(50) COMMENT '办公电话',
  `C_FAX`     VARCHAR(50) COMMENT '传真',
  `C_ZIP`     VARCHAR(50) COMMENT '邮政编码',
  `B_MAIN`    CHAR(1)          DEFAULT 0 COMMENT '是否主联系方式',
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
-- 企业所属专业表:
DROP TABLE IF EXISTS SYS_TENANT_E_PROFESSION;
CREATE TABLE SYS_TENANT_E_PROFESSION
(
  `SID`          INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `P_PROFESSION` VARCHAR(32) COMMENT '专业编号',
  `S_PROFESSION` VARCHAR(100) COMMENT '专业',
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
-- 评审租户表:
DROP TABLE IF EXISTS SYS_TENANT_R;
CREATE TABLE SYS_TENANT_R
(
  `SID`        INT(11)      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_CODE`     VARCHAR(32) COMMENT '租户编号',
  `C_NAME`     VARCHAR(500) NOT NULL COMMENT '单位名称',
  `P_STATE`    VARCHAR(32)           DEFAULT 1 COMMENT '状态编号',
  `S_STATE`    VARCHAR(100) COMMENT '状态',
  `P_PROVINCE` VARCHAR(32) COMMENT '省份编号',
  `S_PROVINCE` VARCHAR(100) COMMENT '省份',
  `P_CITY`     VARCHAR(32) COMMENT '地市编号',
  `S_CITY`     VARCHAR(100) COMMENT '地市',
  `P_COUNTY`   VARCHAR(32) COMMENT '县市编号',
  `S_COUNTY`   VARCHAR(100) COMMENT '县市',
  `C_ADDR`     VARCHAR(500) COMMENT '地址',
  `C_EMAIL`    VARCHAR(50) COMMENT '电子邮箱',
  `C_TEL`      VARCHAR(50) COMMENT '办公电话',
  `C_FAX`      VARCHAR(50) COMMENT '传真',
  `C_ZIP`      VARCHAR(50) COMMENT '邮政编码',
  `C_NUMBER`   VARCHAR(100) COMMENT '营业执照注册号',
  `C_ORG`      VARCHAR(100) COMMENT '确定评审业务机关',
  `P_LEVEL`    VARCHAR(32) COMMENT '机构级别',
  `S_LEVEL`    VARCHAR(100) COMMENT '机构级别',
  `N_FULLTIME` INT COMMENT '专职人员',
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
-- 评审专家信息表:
DROP TABLE IF EXISTS SYS_TENANT_R_USER;
CREATE TABLE SYS_TENANT_R_USER
(
  `SID`         INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `S_NAME`      VARCHAR(100) COMMENT '所属租户',
  `R_SID`        INT(11)    COMMENT '所属租户编号',
  `C_NAME`      VARCHAR(100) COMMENT '姓名',
  `C_USER`     VARCHAR(100) COMMENT '用户名',
  `C_PASSWD`    VARCHAR(32) COMMENT '密码',
  `C_MOBILE`    VARCHAR(16) COMMENT '手机号',
  `C_TEL`       VARCHAR(32) COMMENT '办公电话',
  `T_BIRTH`     DATE COMMENT '出生日期',
  `P_SEX`       VARCHAR(32)      DEFAULT 1 COMMENT '性别',
  `S_SEX`       VARCHAR(100) COMMENT '性别',
  `C_POSITION`  VARCHAR(100) COMMENT '职务',
  `C_CERT`      VARCHAR(100) COMMENT '聘请证书编号',
  `C_RESUME`    VARCHAR(1000) COMMENT '工作简历',
  `C_SCHOOL`    VARCHAR(300) COMMENT '毕业学校',
  `P_EDUCATION` VARCHAR(32) COMMENT '学历编号',
  `S_EDUCATION` VARCHAR(100) COMMENT '学历',
  `P_DEGREE`    VARCHAR(32) COMMENT '学位编号',
  `S_DEGREE`    VARCHAR(100) COMMENT '学位',
  `P_STATE`     VARCHAR(32)      DEFAULT 0 COMMENT '状态',
  `S_STATE`     VARCHAR(100) COMMENT '状态',
  `T_LOGIN`     DATETIME COMMENT '最后登录时间',
  `C_IP`        VARCHAR(15) COMMENT '最后登陆IP',
  `R_CREATE`    INT(11) COMMENT '创建人编号',
  `S_CREATE`    VARCHAR(50) COMMENT '创建人',
  `T_CREATE`    DATETIME COMMENT '创建时间',
  `R_UPDATE`    INT(11) COMMENT '更新人编号',
  `S_UPDATE`    VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`    TIMESTAMP COMMENT '更新时间',
  `R_TENANT`    INT(11) COMMENT '租户编号',
  `S_TENANT`    VARCHAR(300) COMMENT '租户名称',
  `P_TENANT`    CHAR(1) COMMENT '租户类型',
  `N_STATE`     INT COMMENT '状态',
  `B_DELETE`    CHAR(1) COMMENT '删除标志',
  `N_INDEX`     INT COMMENT '排序',
  `N_VERSION`   INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);
-- 评审机构联系方式表:
DROP TABLE IF EXISTS SYS_TENANT_R_CONTACT;
CREATE TABLE SYS_TENANT_R_CONTACT
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_ADDR`    VARCHAR(500) COMMENT '地址',
  `C_EMAIL`   VARCHAR(50) COMMENT '电子邮箱',
  `C_TEL`     VARCHAR(50) COMMENT '办公电话',
  `C_FAX`     VARCHAR(50) COMMENT '传真',
  `C_ZIP`     VARCHAR(50) COMMENT '邮政编码',
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
-- 评审机构评审专业表:
DROP TABLE IF EXISTS SYS_TENANT_R_PROFESSION;
CREATE TABLE SYS_TENANT_R_PROFESSION
(
  `SID`          INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `P_PROFESSION` VARCHAR(32) COMMENT '专业编号',
  `S_PROFESSION` VARCHAR(100) COMMENT '专业',
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
-- 政府租户表:
DROP TABLE IF EXISTS SYS_TENANT_G;
CREATE TABLE SYS_TENANT_G
(
  `SID`        INT(11)      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`     VARCHAR(500) NOT NULL COMMENT '单位名称',
  `C_CODE`     VARCHAR(32) COMMENT '租户编号',
  `P_PROVINCE` VARCHAR(32) COMMENT '省份编号',
  `S_PROVINCE` VARCHAR(100) COMMENT '省份',
  `P_CITY`     VARCHAR(32) COMMENT '地市编号',
  `S_CITY`     VARCHAR(100) COMMENT '地市',
  `P_COUNTY`   VARCHAR(32) COMMENT '县市编号',
  `S_COUNTY`   VARCHAR(100) COMMENT '县市',
  `C_ADDR`     VARCHAR(500) COMMENT '地址',
  `C_EMAIL`    VARCHAR(50) COMMENT '电子邮箱',
  `C_TEL`      VARCHAR(50) COMMENT '办公电话',
  `C_FAX`      VARCHAR(50) COMMENT '传真',
  `C_ZIP`      VARCHAR(50) COMMENT '邮政编码',
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
-- 政府用户表:
DROP TABLE IF EXISTS SYS_TENANT_G_USER;
CREATE TABLE SYS_TENANT_G_USER
(
  `SID`         INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`      VARCHAR(100) COMMENT '姓名',
  `C_USER`      VARCHAR(100) COMMENT '用户名',
  `S_NAME`      VARCHAR(100) COMMENT '所属租户',
  `R_SID`        INT(11)    COMMENT '所属租户编号',
  `C_PASSWD`    VARCHAR(50) COMMENT '密码',
  `C_MOBILE`    VARCHAR(50) COMMENT '手机号',
  `C_TEL`       VARCHAR(50) COMMENT '办公电话',
  `T_BIRTH`     DATE COMMENT '出生日期',
  `P_SEX`       VARCHAR(32)      DEFAULT 1 COMMENT '性别',
  `S_SEX`       VARCHAR(100) COMMENT '性别',
  `C_POSITION`  VARCHAR(50) COMMENT '职务',
  `C_RESUME`    VARCHAR(1000) COMMENT '工作简历',
  `C_SCHOOL`    VARCHAR(300) COMMENT '毕业学校',
  `P_EDUCATION` VARCHAR(32) COMMENT '学历编号',
  `S_EDUCATION` VARCHAR(100) COMMENT '学历',
  `P_DEGREE`    VARCHAR(32) COMMENT '学位编号',
  `S_DEGREE`    VARCHAR(100) COMMENT '学位',
  `P_STATE`     VARCHAR(32)      DEFAULT 0 COMMENT '状态',
  `S_STATE`     VARCHAR(100) COMMENT '状态',
  `T_LOGIN`     DATETIME COMMENT '最后登录时间',
  `C_IP`        VARCHAR(50) COMMENT '最后登陆IP',
  `R_CREATE`    INT(11) COMMENT '创建人编号',
  `S_CREATE`    VARCHAR(50) COMMENT '创建人',
  `T_CREATE`    DATETIME COMMENT '创建时间',
  `R_UPDATE`    INT(11) COMMENT '更新人编号',
  `S_UPDATE`    VARCHAR(50) COMMENT '更新人',
  `T_UPDATE`    TIMESTAMP COMMENT '更新时间',
  `R_TENANT`    INT(11) COMMENT '租户编号',
  `S_TENANT`    VARCHAR(300) COMMENT '租户名称',
  `P_TENANT`    CHAR(1) COMMENT '租户类型',
  `N_STATE`     INT COMMENT '状态',
  `B_DELETE`    CHAR(1) COMMENT '删除标志',
  `N_INDEX`     INT COMMENT '排序',
  `N_VERSION`   INT COMMENT '版本',
  PRIMARY KEY (`SID`)
);
-- 系统用户信息表:
DROP TABLE IF EXISTS SYS_USER;
CREATE TABLE SYS_USER
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`    VARCHAR(100) COMMENT '用户名',
  `P_SEX`     VARCHAR(32)      DEFAULT 1 COMMENT '性别',
  `S_SEX`     VARCHAR(100) COMMENT '性别',
  `C_EMAIL`   VARCHAR(50) COMMENT '电子邮箱',
  `C_MOBILE`  VARCHAR(50) COMMENT '手机号',
  `C_TEL`     VARCHAR(50) COMMENT '办公电话',
  `P_STATE`   VARCHAR(32)      DEFAULT 0 COMMENT '状态',
  `S_STATE`   VARCHAR(100) COMMENT '状态',
  `C_PASSWD`  VARCHAR(50) COMMENT '密码',
  `T_LOGIN`   DATETIME COMMENT '最后登录时间',
  `C_IP`      VARCHAR(15) COMMENT '最后登陆IP',
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
-- 系统日志表:
DROP TABLE IF EXISTS SYS_AUDIT_LOG;
CREATE TABLE SYS_AUDIT_LOG
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_USER`    INT(11) COMMENT '操作用户',
  `S_USER`    VARCHAR(100) COMMENT '用户名',
  `P_TYPE`    VARCHAR(32) COMMENT '日志类型ID',
  `S_TYPE`    VARCHAR(100) COMMENT '日志类型名',
  `C_DESC`    VARCHAR(500) COMMENT '日志描述',
  `C_DATA`    TEXT COMMENT '日志数据',
  `C_IP`      VARCHAR(100) COMMENT '客户端地址',
  `C_CLIENT`  VARCHAR(500) COMMENT '客户端信息',
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
-- 系统参数_单值表:
DROP TABLE IF EXISTS SYS_PARA_SINGLE;
CREATE TABLE SYS_PARA_SINGLE
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`    VARCHAR(300) COMMENT '参数名',
  `C_CODE`    VARCHAR(100) COMMENT '参数编码',
  `C_VALUE`   VARCHAR(300) COMMENT '参数值',
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
-- 系统参数_多值表:
DROP TABLE IF EXISTS SYS_PARA_MULTI;
CREATE TABLE SYS_PARA_MULTI
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`    VARCHAR(300) COMMENT '参数名',
  `C_CODE`    VARCHAR(100) COMMENT '参数编码',
  `C_VALUE`   VARCHAR(300) COMMENT '参数值',
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
-- 系统参数_树状表:
DROP TABLE IF EXISTS SYS_PARA_TREE;
CREATE TABLE SYS_PARA_TREE
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`    VARCHAR(300) COMMENT '参数名',
  `C_CODE`    VARCHAR(300) COMMENT '参数编码',
  `C_VALUE`   VARCHAR(300) COMMENT '参数值',
  `R_PARENT`  INT(11) COMMENT '父级编号',
  `C_PATH`    VARCHAR(3000) COMMENT '路径',
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
-- 地区信息表:
DROP TABLE IF EXISTS SYS_PARA_AREA;
CREATE TABLE SYS_PARA_AREA
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_CODE`    VARCHAR(300) COMMENT '参数编码',
  `C_VALUE`   VARCHAR(300) COMMENT '参数值',
  `R_PARENT`  INT(11) COMMENT '父级编号',
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
-- 日志表:
DROP TABLE IF EXISTS SSM_TENANT_LOG;
CREATE TABLE SSM_TENANT_LOG
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_USER`    INT(11) COMMENT '操作用户',
  `S_USER`    VARCHAR(100) COMMENT '用户名',
  `P_TYPE`    VARCHAR(32) COMMENT '日志类型ID',
  `S_TYPE`    VARCHAR(100) COMMENT '日志类型名',
  `C_DESC`    VARCHAR(500) COMMENT '日志描述',
  `C_DATA`    TEXT COMMENT '日志数据',
  `C_IP`      VARCHAR(100) COMMENT '客户端地址',
  `C_CLIENT`  VARCHAR(500) COMMENT '客户端信息',
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
-- 企业达标情况表:
DROP TABLE IF EXISTS SSM_TENANT_E_STANDARD;
CREATE TABLE SSM_TENANT_E_STANDARD
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `P_LEVEL`   VARCHAR(32) COMMENT '达标等级',
  `S_LEVEL`   VARCHAR(100) COMMENT '达标等级',
  `T_EXAMINE` DATETIME COMMENT '评审时间',
  `T_NEXT`    DATETIME COMMENT '下次复审时间',
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
-- 通知公告表:
DROP TABLE IF EXISTS SSM_NOTICE;
CREATE TABLE SSM_NOTICE
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_TITLE`   INT(11) COMMENT '标题',
  `C_CONTENT` TEXT COMMENT '内容',
  `T_BEGIN`   DATETIME COMMENT '生效时间起',
  `T_END`     DATETIME COMMENT '生效时间止',
  `N_READER`  INT COMMENT '已阅人数',
  `R_PUBLIC`  INT(11) COMMENT '发布人SID',
  `S_PUBLIC`  VARCHAR(300) COMMENT '发布人名称',
  `T_PUBLIC`  DATETIME COMMENT '发布时间',
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
-- 通知公告评论表:
DROP TABLE IF EXISTS SSM_NOTICE_COMMENTS;
CREATE TABLE SSM_NOTICE_COMMENTS
(
  `SID`        INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `R_NOTICE`   INT(11) COMMENT '通知公告',
  `C_CONTENT`  TEXT COMMENT '评论内容',
  `R_COMMENTS` INT(11) COMMENT '引用评论',
  `B_AUDIT`    CHAR(1) COMMENT '是否已审',
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
-- 知识库分类表:
DROP TABLE IF EXISTS SSM_KNOWLEDGE_SORT;
CREATE TABLE SSM_KNOWLEDGE_SORT
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`    VARCHAR(300) COMMENT '名称',
  `R_PARENT`  INT(11) COMMENT '上级分类',
  `C_PATH`    VARCHAR(1000) COMMENT '路径',
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
-- 知识库文件表:
DROP TABLE IF EXISTS SSM_KNOWLEDGE_FILE;
CREATE TABLE SSM_KNOWLEDGE_FILE
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `C_NAME`    VARCHAR(300) COMMENT '名称',
  `R_SORT`    INT(11) COMMENT '分类',
  `S_SORT`    VARCHAR(200) COMMENT '分类',
  `C_DEPT`    VARCHAR(200) COMMENT '发布部门',
  `T_DATE`    DATE COMMENT '发布日期',
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
