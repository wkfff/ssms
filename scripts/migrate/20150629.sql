-- 评分标准表:

DROP TABLE IF EXISTS SYS_GRADE_STD;
CREATE TABLE SYS_GRADE_STD
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_SID`			INT(11)	NOT NULL	 COMMENT '模板编号',
	`C_CATEGORY`			VARCHAR(300)	 COMMENT '一级要素',
	`C_PROJECT`			VARCHAR(300)	 COMMENT '二级要素',
	`C_REQUEST`			VARCHAR(2000)	 COMMENT '基本规范要求',
	`C_CONTENT`			VARCHAR(2000)	 COMMENT '企业达标标准',
	`N_SCORE`			INT	 COMMENT '标准分值',
	`C_METHOD`			VARCHAR(2000)	 COMMENT '考评方法',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);
-- 自评/评审报告模板表:

DROP TABLE IF EXISTS SYS_REPORT_TEMPLATE;
CREATE TABLE SYS_REPORT_TEMPLATE
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_SID`			INT(11)	NOT NULL	 COMMENT '模板编号',
	`Z_TYPE`			CHAR(1)	 COMMENT '类型',
	`C_CONTENT`			LONGTEXT	 COMMENT '内容',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 自评/评审报告表:

DROP TABLE IF EXISTS SYS_REPORT;
CREATE TABLE SYS_REPORT
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_SID`			INT(11)	NOT NULL	 COMMENT '方案编号',
	`Z_TYPE`			CHAR(1)	 COMMENT '类型',
	`C_CONTENT`			LONGTEXT	 COMMENT '内容',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 企业自评方案表:

DROP TABLE IF EXISTS SSM_GRADE_PLAN;
CREATE TABLE SSM_GRADE_PLAN
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`T_START`			DATETIME	 COMMENT '自评开始时间',
	`T_END`			DATETIME	 COMMENT '自评结束时间',
	`C_LEADER`			VARCHAR(50)	 COMMENT '自评组组长',
	`C_MEMBERS`			VARCHAR(3000)	 COMMENT '自评组主要成员',
	`N_GET`			INT	 DEFAULT 0	 COMMENT '得分项',
	`N_DEDUCT`			INT	 DEFAULT 0	 COMMENT '扣分项',
	`N_LACK`			INT	 DEFAULT 0	 COMMENT '缺项',
	`P_PROFESSION`			VARCHAR(32)	 COMMENT '专业编号',
	`S_PROFESSION`			VARCHAR(100)	 COMMENT '专业',
	`P_STATE`			VARCHAR(32)	 COMMENT '自评状态',
	`S_STATE`			VARCHAR(100)	 COMMENT '自评状态',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 企业自评内容表:

DROP TABLE IF EXISTS SSM_GRADE_CONTENT;
CREATE TABLE SSM_GRADE_CONTENT
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_SID`			INT(11)	NOT NULL	 COMMENT '自评方案表SID',
	`R_STD`			INT(11)	NOT NULL	 COMMENT '标准编号',
	`C_CATEGORY`			VARCHAR(300)	 COMMENT '一级要素',
	`C_PROJECT`			VARCHAR(300)	 COMMENT '二级要素',
	`C_REQUEST`			VARCHAR(2000)	 COMMENT '基本规范要求',
	`C_CONTENT`			VARCHAR(2000)	 COMMENT '企业达标标准',
	`N_SCORE`			INT	 COMMENT '标准分值',
	`C_METHOD`			VARCHAR(2000)	 COMMENT '考评方法',
	`C_DESC`			VARCHAR(2000)	 COMMENT '自评描述',
	`B_BLANK`			CHAR(1)	 COMMENT '是否缺项',
	`N_SCORE_REAL`			INT	 COMMENT '实际得分',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 评审方案表:

DROP TABLE IF EXISTS SSM_REVIEW_PLAN;
CREATE TABLE SSM_REVIEW_PLAN
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_ENTERPRISE`			INT(11)	 COMMENT '申请企业编号',
	`S_ENTERPRISE`			VARCHAR(300)	 COMMENT '申请企业',
	`C_ENTERPRISE_ADDR`			VARCHAR(500)	 COMMENT '申请企业地址',
	`T_START`			DATETIME	 COMMENT '评审开始时间',
	`T_END`			DATETIME	 COMMENT '评审结束时间',
	`R_LEADER`			INT(11)	 COMMENT '评审组组长',
	`S_LEADER`			VARCHAR(300)	 COMMENT '评审组组长',
	`P_STATE`			VARCHAR(32)	 DEFAULT 0	 COMMENT '评审状态',
	`S_STATE`			VARCHAR(100)	 COMMENT '评审状态',
	`R_SID`			INT(11)	 COMMENT '自评主表编号',
	`C_CONTACT`			VARCHAR(50)	 COMMENT '联系人',
	`C_PHONE`			VARCHAR(50)	 COMMENT '电话',
	`C_FAX`			VARCHAR(50)	 COMMENT '传真',
	`C_TEL`			VARCHAR(50)	 COMMENT '手机',
	`C_EMAIL`			VARCHAR(100)	 COMMENT '电子邮箱',
	`C_REVIEW_ADDR`			VARCHAR(500)	 COMMENT '评审企业地址',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 评审成员表:

DROP TABLE IF EXISTS SSM_REVIEW_MEMBERS;
CREATE TABLE SSM_REVIEW_MEMBERS
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_SID`			INT(11)	NOT NULL	 COMMENT '评审方案编号',
	`R_MEMBER`			INT(11)	 COMMENT '成员编号',
	`S_MEMBER`			VARCHAR(300)	 COMMENT '成员名称',
	`C_JOB`			VARCHAR(100)	 COMMENT '职称',
	`C_TEL`			VARCHAR(50)	 COMMENT '电话',
	`C_REMARKS`			VARCHAR(2000)	 COMMENT '备注',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 评审内容表:

DROP TABLE IF EXISTS SSM_REVIEW_CONTENT;
CREATE TABLE SSM_REVIEW_CONTENT
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_SID`			INT(11)	NOT NULL	 COMMENT '评审方案编号',
	`C_CATEGORY`			VARCHAR(300)	 COMMENT '一级要素',
	`C_PROJECT`			VARCHAR(300)	 COMMENT '二级要素',
	`C_REQUEST`			VARCHAR(2000)	 COMMENT '基本规范要求',
	`C_CONTENT`			VARCHAR(2000)	 COMMENT '企业达标标准',
	`N_SCORE`			INT	 COMMENT '标准分值',
	`C_METHOD`			VARCHAR(2000)	 COMMENT '考评方法',
	`C_DESC`			VARCHAR(2000)	 COMMENT '自评描述',
	`B_BLANK`			CHAR(1)	 COMMENT '是否缺项',
	`N_SCORE_REAL`			VARCHAR(2000)	 COMMENT '企业自评得分',
	`C_DESC_REVIEW`			VARCHAR(2000)	 COMMENT '评审描述',
	`N_SCORE_REVIEW`			INT	 COMMENT '评审得分',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 评审证书表:

DROP TABLE IF EXISTS SSM_REVIEW_CERT;
CREATE TABLE SSM_REVIEW_CERT
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_SID`			INT(11)	NOT NULL	 COMMENT '评审方案编号',
	`C_ENTERPRISE`			VARCHAR(300)	NOT NULL	 COMMENT '单位名称',
	`N_LEVEL`			INT	 COMMENT '等级',
	`P_INSDUTRY`			VARCHAR(32)	 COMMENT '行业编号',
	`S_INSDUTRY`			VARCHAR(100)	 COMMENT '行业',
	`P_PROFESSION`			VARCHAR(32)	 COMMENT '专业编号',
	`S_PROFESSION`			VARCHAR(100)	 COMMENT '专业',
	`C_CERT_NUMBER`			VARCHAR(100)	 COMMENT '证书编号',
	`C_ISSUING_AUTHORITY`			VARCHAR(300)	 COMMENT '发证机关',
	`C_PRINTED_NUMBER`			VARCHAR(100)	 COMMENT '印制编号',
	`T_ISSUING_DATE`			DATE	 COMMENT '发证日期',
	`T_VALIDITY`			DATE	 COMMENT '有效期截至',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 评审方案结果表:

DROP TABLE IF EXISTS SSM_RESULT_PLAN;
CREATE TABLE SSM_RESULT_PLAN
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_ENTERPRISE`			INT(11)	 COMMENT '申请企业编号',
	`S_ENTERPRISE`			VARCHAR(300)	 COMMENT '申请企业',
	`C_ENTERPRISE_ADDR`			VARCHAR(500)	 COMMENT '申请企业地址',
	`T_START`			DATETIME	 COMMENT '评审开始时间',
	`T_END`			DATETIME	 COMMENT '评审结束时间',
	`R_LEADER`			INT(11)	 COMMENT '评审组组长',
	`S_LEADER`			VARCHAR(300)	 COMMENT '评审组组长',
	`P_STATE`			VARCHAR(32)	 DEFAULT 0	 COMMENT '状态',
	`S_STATE`			VARCHAR(100)	 COMMENT '状态',
	`R_SID`			INT(11)	 COMMENT '自评主表编号',
	`C_CONTACT`			VARCHAR(50)	 COMMENT '联系人',
	`C_PHONE`			VARCHAR(50)	 COMMENT '电话',
	`C_FAX`			VARCHAR(50)	 COMMENT '传真',
	`C_TEL`			VARCHAR(50)	 COMMENT '手机',
	`C_EMAIL`			VARCHAR(100)	 COMMENT '电子邮箱',
	`C_REVIEW_ADDR`			VARCHAR(500)	 COMMENT '评审企业地址',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 评审成员结果表:

DROP TABLE IF EXISTS SSM_RESULT_MEMBERS;
CREATE TABLE SSM_RESULT_MEMBERS
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_SID`			INT(11)	NOT NULL	 COMMENT '评审方案编号',
	`R_MEMBER`			INT(11)	 COMMENT '成员编号',
	`S_MEMBER`			VARCHAR(300)	 COMMENT '成员名称',
	`C_JOB`			VARCHAR(100)	 COMMENT '职称',
	`C_TEL`			VARCHAR(50)	 COMMENT '电话',
	`C_REMARKS`			VARCHAR(2000)	 COMMENT '备注',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 评审内容结果表:

DROP TABLE IF EXISTS SSM_RESULT_CONTENT;
CREATE TABLE SSM_RESULT_CONTENT
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_SID`			INT(11)	NOT NULL	 COMMENT '评审方案编号',
	`C_CATEGORY`			VARCHAR(300)	 COMMENT '一级要素',
	`C_PROJECT`			VARCHAR(300)	 COMMENT '二级要素',
	`C_REQUEST`			VARCHAR(2000)	 COMMENT '基本规范要求',
	`C_CONTENT`			VARCHAR(2000)	 COMMENT '企业达标标准',
	`N_SCORE`			INT	 COMMENT '标准分值',
	`C_METHOD`			VARCHAR(2000)	 COMMENT '考评方法',
	`C_DESC`			VARCHAR(2000)	 COMMENT '自评描述',
	`B_BLANK`			CHAR(1)	 COMMENT '是否缺项',
	`N_SCORE_REAL`			VARCHAR(2000)	 COMMENT '企业自评得分',
	`C_DESC_REVIEW`			VARCHAR(2000)	 COMMENT '评审描述',
	`N_SCORE_REVIEW`			INT	 COMMENT '评审得分',
	`N_PERCENT`			DECIMAL(3,1)	 COMMENT '百分制得分',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 评审报告结果表:

DROP TABLE IF EXISTS SSM_RESULT_REPORT;
CREATE TABLE SSM_RESULT_REPORT
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_SID`			INT(11)	 COMMENT '评审方案编号',
	`C_CONTENT`			LONGTEXT	 COMMENT '内容',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 评审证书结果表:

DROP TABLE IF EXISTS SSM_RESULT_CERT;
CREATE TABLE SSM_RESULT_CERT
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_SID`			INT(11)	NOT NULL	 COMMENT '评审方案编号',
	`C_ENTERPRISE`			VARCHAR(300)	NOT NULL	 COMMENT '单位名称',
	`N_LEVEL`			INT	 COMMENT '等级',
	`P_INSDUTRY`			VARCHAR(32)	 COMMENT '行业编号',
	`S_INSDUTRY`			VARCHAR(100)	 COMMENT '行业',
	`P_PROFESSION`			VARCHAR(32)	 COMMENT '专业编号',
	`S_PROFESSION`			VARCHAR(100)	 COMMENT '专业',
	`C_CERT_NUMBER`			VARCHAR(100)	 COMMENT '证书编号',
	`C_ISSUING_AUTHORITY`			VARCHAR(300)	 COMMENT '发证机关',
	`C_PRINTED_NUMBER`			VARCHAR(100)	 COMMENT '印制编号',
	`T_ISSUING_DATE`			DATE	 COMMENT '发证日期',
	`T_VALIDITY`			DATE	 COMMENT '有效期截至',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TENANT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


