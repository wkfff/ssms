-- 评分标准表:

DROP TABLE IF EXISTS SSM_GRADE_STD;
CREATE TABLE SSM_GRADE_STD
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`P_PROFESSION`			VARCHAR(32)	NOT NULL	 COMMENT '专业编号',
	`S_PROFESSION`			VARCHAR(100)	 COMMENT '专业',
	`C_CATEGORY`			VARCHAR(300)	 COMMENT '类目',
	`C_PROJECT`			VARCHAR(300)	 COMMENT '项目',
	`C_CONTENT`			VARCHAR(2000)	 COMMENT '内容',
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
	`P_TANENT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 企业自评主表:

DROP TABLE IF EXISTS SSM_GRADE_E_M;
CREATE TABLE SSM_GRADE_E_M
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`C_TITLE`			VARCHAR(300)	 COMMENT '标题',
	`T_START`			DATETIME	 COMMENT '自评开始时间',
	`T_END`			DATETIME	 COMMENT '自评结束时间',
	`C_LEADER`			VARCHAR(50)	 COMMENT '自评组组长',
	`C_MEMBERS`			VARCHAR(3000)	 COMMENT '自评组主要成员',
	`N_GET`			INT	 COMMENT '得分项',
	`N_DEDUCT`			INT	 COMMENT '扣分项',
	`N_LACK`			INT	 COMMENT '缺项',
	`P_STATE`			VARCHAR(32)	 COMMENT '状态',
	`S_STATE`			VARCHAR(100)	 COMMENT '状态',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TANENT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 企业自评明细表:

DROP TABLE IF EXISTS SSM_GRADE_E_D;
CREATE TABLE SSM_GRADE_E_D
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_SID`			INT(11)	NOT NULL	 COMMENT '主表编号',
	`R_STD`			INT(11)	NOT NULL	 COMMENT '标准编号',
	`C_CATEGORY`			VARCHAR(300)	 COMMENT '类目',
	`C_PROJECT`			VARCHAR(300)	 COMMENT '项目',
	`C_CONTENT`			VARCHAR(2000)	 COMMENT '内容',
	`N_SCORE`			INT	 COMMENT '标准分值',
	`C_METHOD`			VARCHAR(2000)	 COMMENT '考评方法',
	`C_DESC`			VARCHAR(2000)	 COMMENT '自评描述',
	`B_BLANK`			CHAR(1)	 COMMENT '空项',
	`N_SCORE_REAL`			INT	 COMMENT '实际得分',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TANENT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 评审主表:

DROP TABLE IF EXISTS SSM_GRADE_R_M;
CREATE TABLE SSM_GRADE_R_M
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`T_START`			DATETIME	 COMMENT '评审开始时间',
	`T_END`			DATETIME	 COMMENT '评审结束时间',
	`C_LEADER`			VARCHAR(50)	 COMMENT '评审组组长',
	`C_MEMBERS`			VARCHAR(3000)	 COMMENT '评审组主要成员',
	`N_GET`			INT	 COMMENT '得分项',
	`N_DEDUCT`			INT	 COMMENT '扣分项',
	`N_LACK`			INT	 COMMENT '缺项',
	`P_STATE`			VARCHAR(32)	 COMMENT '状态',
	`S_STATE`			VARCHAR(100)	 COMMENT '状态',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TANENT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


-- 评审明细表:

DROP TABLE IF EXISTS SSM_GRADE_R_D;
CREATE TABLE SSM_GRADE_R_D
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '自增主键',
	`R_ED`			INT(11)	NOT NULL	 COMMENT '明细项编号',
	`C_DESC`			VARCHAR(2000)	 COMMENT '评审描述',
	`B_BLANK`			CHAR(1)	 COMMENT '空项',
	`N_SCORE_REAL`			INT	 COMMENT '实际得分',
	`R_CREATE`			INT(11)	 COMMENT '创建人编号',
	`S_CREATE`			VARCHAR(50)	 COMMENT '创建人',
	`T_CREATE`			DATETIME	 COMMENT '创建时间',
	`R_UPDATE`			INT(11)	 COMMENT '更新人编号',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '更新人',
	`T_UPDATE`			TIMESTAMP	 COMMENT '更新时间',
	`R_TENANT`			INT(11)	 COMMENT '租户编号',
	`S_TENANT`			VARCHAR(300)	 COMMENT '租户名称',
	`P_TANENT`			CHAR(1)	 COMMENT '租户类型',
	`N_STATE`			INT	 COMMENT '状态',
	`B_DELETE`			CHAR(1)	 COMMENT '删除标志',
	`N_INDEX`			INT	 COMMENT '排序',
	`N_VERSION`			INT	 COMMENT '版本',
	PRIMARY KEY  (`SID`)
);


