-- ���ֱ�׼��:

DROP TABLE IF EXISTS SSM_GRADE_STD;
CREATE TABLE SSM_GRADE_STD
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '��������',
	`P_PROFESSION`			VARCHAR(32)	NOT NULL	 COMMENT 'רҵ���',
	`S_PROFESSION`			VARCHAR(100)	 COMMENT 'רҵ',
	`C_CATEGORY`			VARCHAR(300)	 COMMENT '��Ŀ',
	`C_PROJECT`			VARCHAR(300)	 COMMENT '��Ŀ',
	`C_CONTENT`			VARCHAR(2000)	 COMMENT '����',
	`N_SCORE`			INT	 COMMENT '��׼��ֵ',
	`C_METHOD`			VARCHAR(2000)	 COMMENT '��������',
	`R_CREATE`			INT(11)	 COMMENT '�����˱��',
	`S_CREATE`			VARCHAR(50)	 COMMENT '������',
	`T_CREATE`			DATETIME	 COMMENT '����ʱ��',
	`R_UPDATE`			INT(11)	 COMMENT '�����˱��',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '������',
	`T_UPDATE`			TIMESTAMP	 COMMENT '����ʱ��',
	`R_TENANT`			INT(11)	 COMMENT '�⻧���',
	`S_TENANT`			VARCHAR(300)	 COMMENT '�⻧����',
	`P_TANENT`			CHAR(1)	 COMMENT '�⻧����',
	`N_STATE`			INT	 COMMENT '״̬',
	`B_DELETE`			CHAR(1)	 COMMENT 'ɾ����־',
	`N_INDEX`			INT	 COMMENT '����',
	`N_VERSION`			INT	 COMMENT '�汾',
	PRIMARY KEY  (`SID`)
);


-- ��ҵ��������:

DROP TABLE IF EXISTS SSM_GRADE_E_M;
CREATE TABLE SSM_GRADE_E_M
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '��������',
	`C_TITLE`			VARCHAR(300)	 COMMENT '����',
	`T_START`			DATETIME	 COMMENT '������ʼʱ��',
	`T_END`			DATETIME	 COMMENT '��������ʱ��',
	`C_LEADER`			VARCHAR(50)	 COMMENT '�������鳤',
	`C_MEMBERS`			VARCHAR(3000)	 COMMENT '��������Ҫ��Ա',
	`N_GET`			INT	 COMMENT '�÷���',
	`N_DEDUCT`			INT	 COMMENT '�۷���',
	`N_LACK`			INT	 COMMENT 'ȱ��',
	`P_STATE`			VARCHAR(32)	 COMMENT '״̬',
	`S_STATE`			VARCHAR(100)	 COMMENT '״̬',
	`R_CREATE`			INT(11)	 COMMENT '�����˱��',
	`S_CREATE`			VARCHAR(50)	 COMMENT '������',
	`T_CREATE`			DATETIME	 COMMENT '����ʱ��',
	`R_UPDATE`			INT(11)	 COMMENT '�����˱��',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '������',
	`T_UPDATE`			TIMESTAMP	 COMMENT '����ʱ��',
	`R_TENANT`			INT(11)	 COMMENT '�⻧���',
	`S_TENANT`			VARCHAR(300)	 COMMENT '�⻧����',
	`P_TANENT`			CHAR(1)	 COMMENT '�⻧����',
	`N_STATE`			INT	 COMMENT '״̬',
	`B_DELETE`			CHAR(1)	 COMMENT 'ɾ����־',
	`N_INDEX`			INT	 COMMENT '����',
	`N_VERSION`			INT	 COMMENT '�汾',
	PRIMARY KEY  (`SID`)
);


-- ��ҵ������ϸ��:

DROP TABLE IF EXISTS SSM_GRADE_E_D;
CREATE TABLE SSM_GRADE_E_D
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '��������',
	`R_SID`			INT(11)	NOT NULL	 COMMENT '������',
	`R_STD`			INT(11)	NOT NULL	 COMMENT '��׼���',
	`C_CATEGORY`			VARCHAR(300)	 COMMENT '��Ŀ',
	`C_PROJECT`			VARCHAR(300)	 COMMENT '��Ŀ',
	`C_CONTENT`			VARCHAR(2000)	 COMMENT '����',
	`N_SCORE`			INT	 COMMENT '��׼��ֵ',
	`C_METHOD`			VARCHAR(2000)	 COMMENT '��������',
	`C_DESC`			VARCHAR(2000)	 COMMENT '��������',
	`B_BLANK`			CHAR(1)	 COMMENT '����',
	`N_SCORE_REAL`			INT	 COMMENT 'ʵ�ʵ÷�',
	`R_CREATE`			INT(11)	 COMMENT '�����˱��',
	`S_CREATE`			VARCHAR(50)	 COMMENT '������',
	`T_CREATE`			DATETIME	 COMMENT '����ʱ��',
	`R_UPDATE`			INT(11)	 COMMENT '�����˱��',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '������',
	`T_UPDATE`			TIMESTAMP	 COMMENT '����ʱ��',
	`R_TENANT`			INT(11)	 COMMENT '�⻧���',
	`S_TENANT`			VARCHAR(300)	 COMMENT '�⻧����',
	`P_TANENT`			CHAR(1)	 COMMENT '�⻧����',
	`N_STATE`			INT	 COMMENT '״̬',
	`B_DELETE`			CHAR(1)	 COMMENT 'ɾ����־',
	`N_INDEX`			INT	 COMMENT '����',
	`N_VERSION`			INT	 COMMENT '�汾',
	PRIMARY KEY  (`SID`)
);


-- ��������:

DROP TABLE IF EXISTS SSM_GRADE_R_M;
CREATE TABLE SSM_GRADE_R_M
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '��������',
	`T_START`			DATETIME	 COMMENT '����ʼʱ��',
	`T_END`			DATETIME	 COMMENT '�������ʱ��',
	`C_LEADER`			VARCHAR(50)	 COMMENT '�������鳤',
	`C_MEMBERS`			VARCHAR(3000)	 COMMENT '��������Ҫ��Ա',
	`N_GET`			INT	 COMMENT '�÷���',
	`N_DEDUCT`			INT	 COMMENT '�۷���',
	`N_LACK`			INT	 COMMENT 'ȱ��',
	`P_STATE`			VARCHAR(32)	 COMMENT '״̬',
	`S_STATE`			VARCHAR(100)	 COMMENT '״̬',
	`R_CREATE`			INT(11)	 COMMENT '�����˱��',
	`S_CREATE`			VARCHAR(50)	 COMMENT '������',
	`T_CREATE`			DATETIME	 COMMENT '����ʱ��',
	`R_UPDATE`			INT(11)	 COMMENT '�����˱��',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '������',
	`T_UPDATE`			TIMESTAMP	 COMMENT '����ʱ��',
	`R_TENANT`			INT(11)	 COMMENT '�⻧���',
	`S_TENANT`			VARCHAR(300)	 COMMENT '�⻧����',
	`P_TANENT`			CHAR(1)	 COMMENT '�⻧����',
	`N_STATE`			INT	 COMMENT '״̬',
	`B_DELETE`			CHAR(1)	 COMMENT 'ɾ����־',
	`N_INDEX`			INT	 COMMENT '����',
	`N_VERSION`			INT	 COMMENT '�汾',
	PRIMARY KEY  (`SID`)
);


-- ������ϸ��:

DROP TABLE IF EXISTS SSM_GRADE_R_D;
CREATE TABLE SSM_GRADE_R_D
(

	`SID`		INT(11) 	NOT NULL auto_increment COMMENT '��������',
	`R_ED`			INT(11)	NOT NULL	 COMMENT '��ϸ����',
	`C_DESC`			VARCHAR(2000)	 COMMENT '��������',
	`B_BLANK`			CHAR(1)	 COMMENT '����',
	`N_SCORE_REAL`			INT	 COMMENT 'ʵ�ʵ÷�',
	`R_CREATE`			INT(11)	 COMMENT '�����˱��',
	`S_CREATE`			VARCHAR(50)	 COMMENT '������',
	`T_CREATE`			DATETIME	 COMMENT '����ʱ��',
	`R_UPDATE`			INT(11)	 COMMENT '�����˱��',
	`S_UPDATE`			VARCHAR(50)	 COMMENT '������',
	`T_UPDATE`			TIMESTAMP	 COMMENT '����ʱ��',
	`R_TENANT`			INT(11)	 COMMENT '�⻧���',
	`S_TENANT`			VARCHAR(300)	 COMMENT '�⻧����',
	`P_TANENT`			CHAR(1)	 COMMENT '�⻧����',
	`N_STATE`			INT	 COMMENT '״̬',
	`B_DELETE`			CHAR(1)	 COMMENT 'ɾ����־',
	`N_INDEX`			INT	 COMMENT '����',
	`N_VERSION`			INT	 COMMENT '�汾',
	PRIMARY KEY  (`SID`)
);


