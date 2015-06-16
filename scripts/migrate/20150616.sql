-- ģ��汾��:
DROP TABLE IF EXISTS SSM_TEMPLATE_VERSION;
CREATE TABLE SSM_TEMPLATE_VERSION
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `R_TEMPLATE`  INT(11) COMMENT 'ģ����',
  `P_PROFESSION`  INT(11) COMMENT '��ҵרҵ���',
  `R_CREATE`  INT(11) COMMENT '�����˱��',
  `S_CREATE`  VARCHAR(50) COMMENT '������',
  `T_CREATE`  DATETIME COMMENT '����ʱ��',
  `R_UPDATE`  INT(11) COMMENT '�����˱��',
  `S_UPDATE`  VARCHAR(50) COMMENT '������',
  `T_UPDATE`  TIMESTAMP COMMENT '����ʱ��',
  `R_TENANT`  INT(11) COMMENT '��ҵ���',
  `S_TENANT`  VARCHAR(300) COMMENT '��ҵ����',
  `P_TENANT`  CHAR(1) COMMENT '�⻧����',
  `N_STATE`   INT COMMENT '״̬',
  `B_DELETE`  CHAR(1) COMMENT 'ɾ����־',
  `N_INDEX`   INT COMMENT '����',
  `N_VERSION` INT COMMENT '�汾',
  PRIMARY KEY (`SID`)
);


-- �����ϵģ���ļ���(�鵵):
DROP TABLE IF EXISTS SSM_STDTMP_FOLDER_ARCH;
CREATE TABLE SSM_STDTMP_FOLDER_ARCH
(
  `SID`        INT(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `C_NAME`     VARCHAR(300) COMMENT '�ļ�������',
  `R_TEMPLATE` INT(11) COMMENT '����ģ��SID',
  `R_SID`      INT(11)          DEFAULT 0 COMMENT '���ļ���',
  `R_SOURCE`   INT(11) COMMENT '��ԴSID����¼������������¼��',
  `C_DESC`     VARCHAR(2000) COMMENT '����',
  `C_LOGO`     VARCHAR(200) COMMENT 'LOGOͼ',
  `R_CREATE`   INT(11) COMMENT '�����˱��',
  `S_CREATE`   VARCHAR(50) COMMENT '������',
  `T_CREATE`   DATETIME COMMENT '����ʱ��',
  `R_UPDATE`   INT(11) COMMENT '�����˱��',
  `S_UPDATE`   VARCHAR(50) COMMENT '������',
  `T_UPDATE`   TIMESTAMP COMMENT '����ʱ��',
  `R_TENANT`   INT(11) COMMENT '�⻧���',
  `S_TENANT`   VARCHAR(300) COMMENT '�⻧����',
  `P_TENANT`   CHAR(1) COMMENT '�⻧����',
  `N_STATE`    INT COMMENT '״̬',
  `B_DELETE`   CHAR(1) COMMENT 'ɾ����־',
  `N_INDEX`    INT COMMENT '����',
  `N_VERSION`  INT COMMENT '�汾',
  PRIMARY KEY (`SID`)
);
-- �����ϵģ���ļ�(�鵵):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_ARCH;
CREATE TABLE SSM_STDTMP_FILE_ARCH
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `C_NAME`    VARCHAR(300) COMMENT '����',
  `R_SOURCE`  INT(11) COMMENT '��ԴSID����¼������������¼��',
  `C_DESC`    VARCHAR(2000) COMMENT '����',
  `R_SID`     INT(11) COMMENT '�����ļ���SID',
  `S_NAME`    VARCHAR(300) COMMENT '�����ļ�������',
  `B_REMIND`  CHAR(1)          DEFAULT 1 COMMENT '�Ƿ�����',
  `N_CYCLE`   INT COMMENT '�������ڣ�������',
  `P_CYCLE`   VARCHAR(32) COMMENT '���ڵ�λSID',
  `S_CYCLE`   VARCHAR(100) COMMENT '���ڵ�λ����',
  `C_EXPLAIN` VARCHAR(2000) COMMENT '���߽��',
  `P_TMPFILE` VARCHAR(32) COMMENT '�ļ����ͱ���',
  `S_TMPFILE` VARCHAR(100) COMMENT '�ļ���������',
  `R_CREATE`  INT(11) COMMENT '�����˱��',
  `S_CREATE`  VARCHAR(50) COMMENT '������',
  `T_CREATE`  DATETIME COMMENT '����ʱ��',
  `R_UPDATE`  INT(11) COMMENT '�����˱��',
  `S_UPDATE`  VARCHAR(50) COMMENT '������',
  `T_UPDATE`  TIMESTAMP COMMENT '����ʱ��',
  `R_TENANT`  INT(11) COMMENT '�⻧���',
  `S_TENANT`  VARCHAR(300) COMMENT '�⻧����',
  `P_TENANT`  CHAR(1) COMMENT '�⻧����',
  `N_STATE`   INT COMMENT '״̬',
  `B_DELETE`  CHAR(1) COMMENT 'ɾ����־',
  `N_INDEX`   INT COMMENT '����',
  `N_VERSION` INT COMMENT '�汾',
  PRIMARY KEY (`SID`)
);
-- �����ϵ�ƶ��ļ�ģ������(�鵵):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_01_ARCH;
CREATE TABLE SSM_STDTMP_FILE_01_ARCH
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `R_TMPFILE` INT(11) COMMENT 'ģ��ID',
  `C_NAME`    VARCHAR(300) COMMENT '����',
  `B_CONTROL` CHAR(1)          DEFAULT 0 COMMENT '�Ƿ��ܿ�',
  `C_NUMBER`  VARCHAR(32) COMMENT '�ļ����',
  `C_DEPT_01` VARCHAR(200) COMMENT 'ִ�в���',
  `C_DEPT_02` VARCHAR(200) COMMENT '�ල����',
  `T_DATE_01` DATE COMMENT '��������',
  `T_DATE_02` DATE COMMENT '�������',
  `T_DATE_03` DATE COMMENT '��׼����',
  `P_SATUS`   VARCHAR(32) COMMENT '�ļ�״̬����',
  `S_SATUS`   VARCHAR(100) COMMENT '�ļ�״̬����',
  `N_TIMES`   INT              DEFAULT 0 COMMENT '�޶�����',
  `R_CREATE`  INT(11) COMMENT '�����˱��',
  `S_CREATE`  VARCHAR(50) COMMENT '������',
  `T_CREATE`  DATETIME COMMENT '����ʱ��',
  `R_UPDATE`  INT(11) COMMENT '�����˱��',
  `S_UPDATE`  VARCHAR(50) COMMENT '������',
  `T_UPDATE`  TIMESTAMP COMMENT '����ʱ��',
  `R_TENANT`  INT(11) COMMENT '�⻧���',
  `S_TENANT`  VARCHAR(300) COMMENT '�⻧����',
  `P_TENANT`  CHAR(1) COMMENT '�⻧����',
  `N_STATE`   INT COMMENT '״̬',
  `B_DELETE`  CHAR(1) COMMENT 'ɾ����־',
  `N_INDEX`   INT COMMENT '����',
  `N_VERSION` INT COMMENT '�汾',
  PRIMARY KEY (`SID`)
);
-- �����ϵ֪ͨ�ļ�ģ������(�鵵):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_02_ARCH;
CREATE TABLE SSM_STDTMP_FILE_02_ARCH
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `R_TMPFILE` INT(11) COMMENT 'ģ��ID',
  `C_NAME`    VARCHAR(300) COMMENT '����',
  `C_NUMBER`  VARCHAR(32) COMMENT '�ļ����',
  `C_DEPT_01` VARCHAR(500) COMMENT '��������',
  `C_DEPT_02` VARCHAR(500) COMMENT '���Ͳ���',
  `C_DEPT_03` VARCHAR(500) COMMENT '���Ͳ���',
  `T_DATE_01` DATE COMMENT '��������',
  `P_SATUS`   VARCHAR(32) COMMENT '�ļ�״̬����',
  `S_SATUS`   VARCHAR(100) COMMENT '�ļ�״̬����',
  `N_TIMES`   INT              DEFAULT 0 COMMENT '�޶�����',
  `R_CREATE`  INT(11) COMMENT '�����˱��',
  `S_CREATE`  VARCHAR(50) COMMENT '������',
  `T_CREATE`  DATETIME COMMENT '����ʱ��',
  `R_UPDATE`  INT(11) COMMENT '�����˱��',
  `S_UPDATE`  VARCHAR(50) COMMENT '������',
  `T_UPDATE`  TIMESTAMP COMMENT '����ʱ��',
  `R_TENANT`  INT(11) COMMENT '�⻧���',
  `S_TENANT`  VARCHAR(300) COMMENT '�⻧����',
  `P_TENANT`  CHAR(1) COMMENT '�⻧����',
  `N_STATE`   INT COMMENT '״̬',
  `B_DELETE`  CHAR(1) COMMENT 'ɾ����־',
  `N_INDEX`   INT COMMENT '����',
  `N_VERSION` INT COMMENT '�汾',
  PRIMARY KEY (`SID`)
);
-- �����ϵִ���ļ�ģ������(�鵵):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_03_ARCH;
CREATE TABLE SSM_STDTMP_FILE_03_ARCH
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `R_TMPFILE` INT(11) COMMENT 'ģ��ID',
  `C_NAME`    VARCHAR(300) COMMENT '����',
  `R_CREATE`  INT(11) COMMENT '�����˱��',
  `S_CREATE`  VARCHAR(50) COMMENT '������',
  `T_CREATE`  DATETIME COMMENT '����ʱ��',
  `R_UPDATE`  INT(11) COMMENT '�����˱��',
  `S_UPDATE`  VARCHAR(50) COMMENT '������',
  `T_UPDATE`  TIMESTAMP COMMENT '����ʱ��',
  `R_TENANT`  INT(11) COMMENT '�⻧���',
  `S_TENANT`  VARCHAR(300) COMMENT '�⻧����',
  `P_TENANT`  CHAR(1) COMMENT '�⻧����',
  `N_STATE`   INT COMMENT '״̬',
  `B_DELETE`  CHAR(1) COMMENT 'ɾ����־',
  `N_INDEX`   INT COMMENT '����',
  `N_VERSION` INT COMMENT '�汾',
  PRIMARY KEY (`SID`)
);
-- �����ϵ��ѵִ���ļ�ģ������(�鵵):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_04_ARCH;
CREATE TABLE SSM_STDTMP_FILE_04_ARCH
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `R_TMPFILE` INT(11) COMMENT 'ģ��ID',
  `C_NAME`    VARCHAR(300) COMMENT '����',
  `T_TIME`    DATE COMMENT '��ѵ����',
  `C_USER_01` VARCHAR(300) COMMENT '������',
  `C_ADDR`    VARCHAR(200) COMMENT '��ѵ�ص�',
  `P_TYPE`    VARCHAR(32) COMMENT '��ѵ����',
  `S_TYPE`    VARCHAR(100) COMMENT '��ѵ����',
  `N_TIME`    INT COMMENT 'ѧʱ',
  `C_USER_02` VARCHAR(300) COMMENT '��¼��',
  `R_CREATE`  INT(11) COMMENT '�����˱��',
  `S_CREATE`  VARCHAR(50) COMMENT '������',
  `T_CREATE`  DATETIME COMMENT '����ʱ��',
  `R_UPDATE`  INT(11) COMMENT '�����˱��',
  `S_UPDATE`  VARCHAR(50) COMMENT '������',
  `T_UPDATE`  TIMESTAMP COMMENT '����ʱ��',
  `R_TENANT`  INT(11) COMMENT '�⻧���',
  `S_TENANT`  VARCHAR(300) COMMENT '�⻧����',
  `P_TENANT`  CHAR(1) COMMENT '�⻧����',
  `N_STATE`   INT COMMENT '״̬',
  `B_DELETE`  CHAR(1) COMMENT 'ɾ����־',
  `N_INDEX`   INT COMMENT '����',
  `N_VERSION` INT COMMENT '�汾',
  PRIMARY KEY (`SID`)
);
-- �����ϵ��ѵ�ļ�ģ������(�鵵):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_05_ARCH;
CREATE TABLE SSM_STDTMP_FILE_05_ARCH
(
  `SID`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `R_TMPFILE` INT(11) COMMENT 'ģ��ID',
  `C_NAME`    VARCHAR(300) COMMENT '����',
  `R_CREATE`  INT(11) COMMENT '�����˱��',
  `S_CREATE`  VARCHAR(50) COMMENT '������',
  `T_CREATE`  DATETIME COMMENT '����ʱ��',
  `R_UPDATE`  INT(11) COMMENT '�����˱��',
  `S_UPDATE`  VARCHAR(50) COMMENT '������',
  `T_UPDATE`  TIMESTAMP COMMENT '����ʱ��',
  `R_TENANT`  INT(11) COMMENT '�⻧���',
  `S_TENANT`  VARCHAR(300) COMMENT '�⻧����',
  `P_TENANT`  CHAR(1) COMMENT '�⻧����',
  `N_STATE`   INT COMMENT '״̬',
  `B_DELETE`  CHAR(1) COMMENT 'ɾ����־',
  `N_INDEX`   INT COMMENT '����',
  `N_VERSION` INT COMMENT '�汾',
  PRIMARY KEY (`SID`)
);
-- �������ܵǼ�̨��ģ������(�鵵):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_06_ARCH;
CREATE TABLE SSM_STDTMP_FILE_06_ARCH
(
  `SID`             INT(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `R_TMPFILE`       INT(11) COMMENT 'ģ��ID',
  `C_NAME`          VARCHAR(300) COMMENT '��������',
  `C_EXAMINER`      VARCHAR(300) COMMENT '�����',
  `T_EXAMINE`       DATE COMMENT '���ʱ��',
  `P_LEVEL`         VARCHAR(32) COMMENT '�����ȼ�',
  `C_MEASURE`       VARCHAR(2000) COMMENT '���Ĵ�ʩ',
  `C_RESPONSIBLE`   VARCHAR(300) COMMENT '���ĸ����ˣ����ţ�',
  `T_RECTIFICATION` DATE COMMENT 'Ҫ����������',
  `C_ACCEPTANCE`    VARCHAR(300) COMMENT '������',
  `R_CREATE`        INT(11) COMMENT '�����˱��',
  `S_CREATE`        VARCHAR(50) COMMENT '������',
  `T_CREATE`        DATETIME COMMENT '����ʱ��',
  `R_UPDATE`        INT(11) COMMENT '�����˱��',
  `S_UPDATE`        VARCHAR(50) COMMENT '������',
  `T_UPDATE`        TIMESTAMP COMMENT '����ʱ��',
  `R_TENANT`        INT(11) COMMENT '�⻧���',
  `S_TENANT`        VARCHAR(300) COMMENT '�⻧����',
  `P_TENANT`        CHAR(1) COMMENT '�⻧����',
  `N_STATE`         INT COMMENT '״̬',
  `B_DELETE`        CHAR(1) COMMENT 'ɾ����־',
  `N_INDEX`         INT COMMENT '����',
  `N_VERSION`       INT COMMENT '�汾',
  PRIMARY KEY (`SID`)
);
-- ������ҵ��Ա��֤�ǼǱ�ģ������(�鵵):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_07_ARCH;
CREATE TABLE SSM_STDTMP_FILE_07_ARCH
(
  `SID`           INT(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `R_TMPFILE`     INT(11) COMMENT 'ģ��ID',
  `C_NAME`        VARCHAR(300) COMMENT '����',
  `P_SEX`         VARCHAR(32) COMMENT '�Ա�',
  `S_SEX`         VARCHAR(100) COMMENT '�Ա�',
  `C_CARD`        VARCHAR(30) COMMENT '���֤����',
  `C_DEPT`        VARCHAR(200) COMMENT '���ڲ���',
  `C_WORKTYPE`    VARCHAR(100) COMMENT '����',
  `C_AUTH`        VARCHAR(300) COMMENT '��֤����',
  `T_CERT_GET`    DATE COMMENT 'ȡ֤ʱ��',
  `C_CERT`        VARCHAR(100) COMMENT '֤����',
  `T_CERT_REVIEW` DATE COMMENT '����ʱ��',
  `R_CREATE`      INT(11) COMMENT '�����˱��',
  `S_CREATE`      VARCHAR(50) COMMENT '������',
  `T_CREATE`      DATETIME COMMENT '����ʱ��',
  `R_UPDATE`      INT(11) COMMENT '�����˱��',
  `S_UPDATE`      VARCHAR(50) COMMENT '������',
  `T_UPDATE`      TIMESTAMP COMMENT '����ʱ��',
  `R_TENANT`      INT(11) COMMENT '�⻧���',
  `S_TENANT`      VARCHAR(300) COMMENT '�⻧����',
  `P_TENANT`      CHAR(1) COMMENT '�⻧����',
  `N_STATE`       INT COMMENT '״̬',
  `B_DELETE`      CHAR(1) COMMENT 'ɾ����־',
  `N_INDEX`       INT COMMENT '����',
  `N_VERSION`     INT COMMENT '�汾',
  PRIMARY KEY (`SID`)
);
-- �����豸̨�˼����ڼ����¼ģ������(�鵵):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_08_ARCH;
CREATE TABLE SSM_STDTMP_FILE_08_ARCH
(
  `SID`          INT(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `R_TMPFILE`    INT(11) COMMENT 'ģ��ID',
  `C_NAME`       VARCHAR(300) COMMENT '�豸����',
  `C_NO_REG`     VARCHAR(100) COMMENT '�Ǽ�֤��',
  `C_SPEC`       VARCHAR(100) COMMENT '���/�ͺ�',
  `C_MAKE_UNIT`  VARCHAR(100) COMMENT '���쵥λ',
  `C_NO_FACTORY` VARCHAR(100) COMMENT '�������',
  `C_DEPT`       VARCHAR(200) COMMENT 'ʹ�ò���',
  `T_TEST_LAST`  DATE COMMENT '���¼�������',
  `C_NO_REP`     VARCHAR(100) COMMENT '���鱨����',
  `T_TEST_NEXT`  DATE COMMENT '�´μ�������',
  `C_TEST_CON`   VARCHAR(3000) COMMENT '�������',
  `C_TEST_UNIT`  VARCHAR(200) COMMENT '���鵥λ',
  `R_CREATE`     INT(11) COMMENT '�����˱��',
  `S_CREATE`     VARCHAR(50) COMMENT '������',
  `T_CREATE`     DATETIME COMMENT '����ʱ��',
  `R_UPDATE`     INT(11) COMMENT '�����˱��',
  `S_UPDATE`     VARCHAR(50) COMMENT '������',
  `T_UPDATE`     TIMESTAMP COMMENT '����ʱ��',
  `R_TENANT`     INT(11) COMMENT '�⻧���',
  `S_TENANT`     VARCHAR(300) COMMENT '�⻧����',
  `P_TENANT`     CHAR(1) COMMENT '�⻧����',
  `N_STATE`      INT COMMENT '״̬',
  `B_DELETE`     CHAR(1) COMMENT 'ɾ����־',
  `N_INDEX`      INT COMMENT '����',
  `N_VERSION`    INT COMMENT '�汾',
  PRIMARY KEY (`SID`)
);
-- ��ȫ�������ڼ������¼ģ������(�鵵):
DROP TABLE IF EXISTS SSM_STDTMP_FILE_09_ARCH;
CREATE TABLE SSM_STDTMP_FILE_09_ARCH
(
  `SID`          INT(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `R_TMPFILE`    INT(11) COMMENT 'ģ��ID',
  `C_NAME`       VARCHAR(300) COMMENT '��ȫ��������',
  `C_SPEC`       VARCHAR(100) COMMENT '�ͺ�',
  `C_NO_FACTORY` VARCHAR(100) COMMENT '�������',
  `C_DEPT`       VARCHAR(500) COMMENT '���������豸��װλ��',
  `T_TEST_LAST`  DATE COMMENT '��������',
  `T_TEST_NEXT`  DATE COMMENT '�´μ�������',
  `C_NO_REP`     VARCHAR(100) COMMENT '���鱨����',
  `C_TEST_CON`   VARCHAR(3000) COMMENT '�������',
  `C_TEST_UNIT`  VARCHAR(200) COMMENT '���鵥λ',
  `R_CREATE`     INT(11) COMMENT '�����˱��',
  `S_CREATE`     VARCHAR(50) COMMENT '������',
  `T_CREATE`     DATETIME COMMENT '����ʱ��',
  `R_UPDATE`     INT(11) COMMENT '�����˱��',
  `S_UPDATE`     VARCHAR(50) COMMENT '������',
  `T_UPDATE`     TIMESTAMP COMMENT '����ʱ��',
  `R_TENANT`     INT(11) COMMENT '�⻧���',
  `S_TENANT`     VARCHAR(300) COMMENT '�⻧����',
  `P_TENANT`     CHAR(1) COMMENT '�⻧����',
  `N_STATE`      INT COMMENT '״̬',
  `B_DELETE`     CHAR(1) COMMENT 'ɾ����־',
  `N_INDEX`      INT COMMENT '����',
  `N_VERSION`    INT COMMENT '�汾',
  PRIMARY KEY (`SID`)
);