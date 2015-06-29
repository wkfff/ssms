DELIMITER $$

/*自评初始化*/
DROP PROCEDURE IF EXISTS `P_GRADE_INIT`$$
CREATE PROCEDURE P_GRADE_INIT(IN IN_SID INT,IN IN_PRO INT,IN IN_TENANTID INT,IN IN_TENANTTYPE CHAR(1)) 
/*传入参数： IN_SID 自评主表的SID   , IN_PRO 企业的专业,为-1时从企业专业表中查询*/
    BEGIN
	
	/*根据专业从模板的标准评分表生成*/
	INSERT INTO  SSM_GRADE_E_D(R_SID,R_STD,C_CATEGORY,C_PROJECT,C_REQUEST,C_CONTENT,N_SCORE,C_METHOD,N_STATE,R_TENANT,P_TENANT) 
        SELECT IN_SID,T4.SID,T4.C_CATEGORY,T4.C_PROJECT,T4.C_REQUEST,T4.C_CONTENT,T4.N_SCORE,T4.C_METHOD,0,IN_TENANTID,IN_TENANTTYPE 
	FROM SYS_PROFESSION T2
	LEFT JOIN SYS_TEMPLATE T3 ON T2.R_TEMPLATE=T3.SID
	LEFT JOIN SSM_GRADE_STD T4 ON T3.SID=T4.R_SID
	WHERE T2.SID=IN_PRO
	AND NOT EXISTS(SELECT 1 FROM SSM_GRADE_E_D WHERE R_SID=IN_SID AND R_STD=T4.SID);

	UPDATE SSM_GRADE_E_D SET N_INDEX=SID WHERE R_SID=IN_SID;

	/*根据专业从自评报告的标准评分表生成*/
	INSERT INTO SYS_ATTACH_TEXT(R_TABLE,R_SID,R_FIELD,C_CONTENT,R_TENANT,P_TENANT)
	SELECT 'SSM_GRADE_REPORT',IN_SID,T4.R_FIELD,T4.C_CONTENT,IN_TENANTID,IN_TENANTTYPE
	FROM SYS_PROFESSION T2
	LEFT JOIN SYS_TEMPLATE T3 ON T2.R_TEMPLATE=T3.SID
	LEFT JOIN SYS_ATTACH_TEXT T4 ON T3.SID=T4.R_SID AND T4.R_TABLE='SSM_GRADE_REPORT_TMP' AND T4.R_FIELD='CONTENT'
	WHERE T2.SID=IN_PRO
	AND NOT EXISTS(SELECT 1 FROM SYS_ATTACH_TEXT WHERE R_SID=IN_SID AND R_TABLE='SSM_GRADE_REPORT' AND R_FIELD='CONTENT');
		
    END$$

/*自评统计项*/
DROP PROCEDURE IF EXISTS `P_GRADE_SUM`$$
CREATE PROCEDURE P_GRADE_SUM(IN IN_SID INT)  
    BEGIN
	/*得分、扣分、缺项统计*/
	UPDATE SSM_GRADE_E_M T1 
	LEFT JOIN (SELECT R_SID,COUNT(*)  N FROM SSM_GRADE_E_D  
		WHERE IFNULL(C_PROJECT,'')<>'总计' AND IFNULL(C_PROJECT,'')<>'小计'  AND N_SCORE_REAL IS NOT NULL  AND N_SCORE_REAL=N_SCORE GROUP BY R_SID) T2 ON T1.SID = T2.R_SID
	LEFT JOIN (SELECT R_SID,COUNT(*)  N FROM SSM_GRADE_E_D 
		WHERE IFNULL(C_PROJECT,'')<>'总计' AND IFNULL(C_PROJECT,'')<>'小计'  AND N_SCORE_REAL IS NOT NULL  AND N_SCORE_REAL<N_SCORE  GROUP BY R_SID) T3 ON T1.SID = T3.R_SID
	LEFT JOIN (SELECT R_SID,COUNT(*)  N FROM SSM_GRADE_E_D 
		WHERE IFNULL(C_PROJECT,'')<>'总计' AND IFNULL(C_PROJECT,'')<>'小计'  AND B_BLANK='1'  GROUP BY R_SID) T4 ON T1.SID = T4.R_SID
	SET T1.N_GET=IFNULL(T2.N,0),T1.N_DEDUCT=IFNULL(T3.N,0),T1.N_LACK=IFNULL(T4.N,0)
	WHERE T1.SID=IN_SID;
	
	/*得分小计*/
	UPDATE SSM_GRADE_E_D T1
	LEFT JOIN (SELECT C_CATEGORY,SUM(N_SCORE_REAL) N FROM SSM_GRADE_E_D 
		WHERE R_SID=IN_SID AND IFNULL(C_PROJECT,'')<>'总计' AND IFNULL(C_PROJECT,'')<>'小计'   GROUP BY C_CATEGORY) T2 ON T1.C_CATEGORY = T2.C_CATEGORY
	SET T1.N_SCORE_REAL=T2.N
	WHERE T1.C_CATEGORY=T2.C_CATEGORY
	AND T1.C_PROJECT='小计' AND T1.R_SID=IN_SID;
	
	/*总计*/
	UPDATE SSM_GRADE_E_D T1
	,(SELECT SUM(N_SCORE_REAL) N FROM SSM_GRADE_E_D WHERE R_SID=IN_SID AND C_PROJECT='小计' ) T2
	SET T1.N_SCORE_REAL=T2.N
	WHERE T1.R_SID=IN_SID 
	AND T1.C_PROJECT='总计' AND T1.R_SID=IN_SID;
    END$$

/*自评完成处理*/
DROP PROCEDURE IF EXISTS `P_GRADE_COMPLETE_E`$$
CREATE PROCEDURE P_GRADE_COMPLETE_E(IN IN_SID INT)  
    BEGIN
	/*更改状态*/
	UPDATE SSM_GRADE_E_M SET N_STATE=3 WHERE SID=IN_SID;

	/*从自评报告模板生成*/
	INSERT INTO SYS_ATTACH_TEXT(R_TABLE，R_SID,R_FIELD,C_CONTENT,R_TENANT,S_TENANT,P_TENANT)
	SELECT 'SSM_GRADE_REPORT',IN_SID,'CONTENT',T3.C_CONTENT,T1.R_TENANT,T1.S_TENANT,T1.P_TENANT FROM 
	SSM_GRADE_E_M T1
	LEFT JOIN SYS_TENANT_E_PROFESSION T2 ON T1.R_TENANT=T2.R_TENANT
	LEFT JOIN SSM_GRADE_REPORT_TMP T3 ON T3.P_PROFESSION=T2.P_PROFESSION
	WHERE NOT EXISTS(SELECT 1 FROM SSM_GRADE_REPORT WHERE R_SID=IN_SID);

    END$$



/*自评保存验证*/
DROP FUNCTION IF EXISTS `F_GRADE_CHECK_E`$$
 CREATE FUNCTION F_GRADE_CHECK_E(IN_SID INT(11))
    RETURNS INT
  BEGIN
	DECLARE N INT;
	SELECT COUNT(*) INTO N FROM SSM_GRADE_E_D WHERE R_SID=IN_SID AND IFNULL(N_SCORE_REAL,0)=0 AND IFNULL(B_BLANK,'0')<>'1';
	RETURN N;
  END$$

/*评审完成处理*/
DROP PROCEDURE IF EXISTS `P_GRADE_COMPLETE_R`$$
CREATE PROCEDURE `P_GRADE_COMPLETE_R`(IN IN_SID INT,IN IN_STATE INT)
BEGIN
	/*更改状态*/
	UPDATE SSM_GRADE_R_M SET N_STATE=IN_STATE WHERE SID=IN_SID;
    END$$

/*评审完成验证*/
DROP FUNCTION IF EXISTS `F_GRADE_CHECK_R`$$
CREATE FUNCTION F_GRADE_CHECK_R(IN_SID INT(11))
  RETURNS INT
  BEGIN
    DECLARE N INT;
    SELECT COUNT(*) INTO N FROM SSM_GRADE_R_D WHERE R_SID=IN_SID AND IFNULL(N_SCORE_REVIEW,0)=0 AND IFNULL(B_BLANK,0)=0 AND IFNULL(C_PROJECT,'')<>'小计' AND IFNULL(C_PROJECT,'')<>'总计';
  RETURN N;
END$$

/*评审统计项*/
DROP PROCEDURE IF EXISTS `P_GRADE_SUM_R`$$
CREATE PROCEDURE P_GRADE_SUM_R(IN IN_SID INT)  
    BEGIN
	/*得分、扣分、缺项统计*/
	UPDATE SSM_GRADE_R_M T1 
	LEFT JOIN (SELECT R_SID,COUNT(*)  N FROM SSM_GRADE_R_D  
		WHERE IFNULL(C_PROJECT,'')<>'总计' AND IFNULL(C_PROJECT,'')<>'小计'  AND N_SCORE_REVIEW IS NOT NULL  AND N_SCORE_REVIEW=N_SCORE GROUP BY R_SID) T2 ON T1.SID = T2.R_SID
	LEFT JOIN (SELECT R_SID,COUNT(*)  N FROM SSM_GRADE_R_D 
		WHERE IFNULL(C_PROJECT,'')<>'总计' AND IFNULL(C_PROJECT,'')<>'小计'  AND N_SCORE_REVIEW IS NOT NULL  AND N_SCORE_REVIEW<N_SCORE  GROUP BY R_SID) T3 ON T1.SID = T3.R_SID
	LEFT JOIN (SELECT R_SID,COUNT(*)  N FROM SSM_GRADE_R_D 
		WHERE IFNULL(C_PROJECT,'')<>'总计' AND IFNULL(C_PROJECT,'')<>'小计'  AND B_BLANK='1'  GROUP BY R_SID) T4 ON T1.SID = T4.R_SID
	SET T1.N_GET=IFNULL(T2.N,0),T1.N_DEDUCT=IFNULL(T3.N,0),T1.N_LACK=IFNULL(T4.N,0)
	WHERE T1.SID=IN_SID;
	
	/*得分小计*/
	UPDATE SSM_GRADE_R_D T1
	LEFT JOIN (SELECT C_CATEGORY,SUM(N_SCORE_REVIEW) N FROM SSM_GRADE_R_D 
		WHERE R_SID=IN_SID AND IFNULL(C_PROJECT,'')<>'总计' AND IFNULL(C_PROJECT,'')<>'小计'   GROUP BY C_CATEGORY) T2 ON T1.C_CATEGORY = T2.C_CATEGORY
	SET T1.N_SCORE_REVIEW=T2.N
	WHERE T1.C_CATEGORY=T2.C_CATEGORY
	AND T1.C_PROJECT='小计' AND T1.R_SID=IN_SID;
	
	/*总计*/
	UPDATE SSM_GRADE_R_D T1
	,(SELECT SUM(N_SCORE_REVIEW) N FROM SSM_GRADE_R_D WHERE R_SID=IN_SID AND C_PROJECT='小计' ) T2
	SET T1.N_SCORE_REVIEW=T2.N
	WHERE T1.R_SID=IN_SID 
	AND T1.C_PROJECT='总计' AND T1.R_SID=IN_SID;
    END$$

DELIMITER ;