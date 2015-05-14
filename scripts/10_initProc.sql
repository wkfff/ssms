DELIMITER $$

/*自评初始化*/
DROP PROCEDURE IF EXISTS `P_GRADE_INIT`$$
CREATE PROCEDURE P_GRADE_INIT(IN IN_SID INT,IN IN_PRO INT,IN IN_TENANTID INT,IN IN_TENANTTYPE CHAR(1)) 
/*传入参数： IN_SID 自评主表的SID   , IN_PRO 企业的专业,为-1时从企业专业表中查询*/
    BEGIN
	
	/*根据专业从模板的标准评分表生成*/
	INSERT INTO  SSM_GRADE_E_D(R_SID,R_STD,C_CATEGORY,C_PROJECT,C_REQUEST,C_CONTENT,N_SCORE,C_METHOD,N_STATE) 
        SELECT IN_SID,T4.SID,T4.C_CATEGORY,T4.C_PROJECT,T4.C_REQUEST,T4.C_CONTENT,T4.N_SCORE,T4.C_METHOD,0 
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
CREATE PROCEDURE P_GRADE_SUM(IN in_sid INT)  
    BEGIN
	/*得分、扣分、缺项统计*/
	UPDATE ssm_grade_e_m t1 
	LEFT JOIN (SELECT r_sid,COUNT(*)  N FROM SSM_GRADE_E_D  
		WHERE C_PROJECT<>'总计' AND C_PROJECT<>'小计'  AND N_SCORE_REAL IS NOT NULL  AND N_SCORE_REAL=N_SCORE GROUP BY R_SID) t2 ON t1.sid = t2.r_sid
	LEFT JOIN (SELECT r_sid,COUNT(*)  N FROM SSM_GRADE_E_D 
		WHERE C_PROJECT<>'总计' AND C_PROJECT<>'小计'  AND N_SCORE_REAL IS NOT NULL  AND N_SCORE_REAL<N_SCORE  GROUP BY R_SID) t3 ON t1.sid = t3.r_sid
	LEFT JOIN (SELECT r_sid,COUNT(*)  N FROM SSM_GRADE_E_D 
		WHERE C_PROJECT<>'总计' AND C_PROJECT<>'小计'  AND B_BLANK='1'  GROUP BY R_SID) t4 ON t1.sid = t4.r_sid
	SET t1.N_GET=t2.N,t1.N_DEDUCT=t3.N,t1.N_LACK=t4.N
	WHERE t1.sid=in_sid;
	
	/*得分小计*/
	UPDATE ssm_grade_e_d t1
	LEFT JOIN (SELECT C_CATEGORY,SUM(N_SCORE_REAL) N FROM ssm_grade_e_d 
		WHERE R_SID=in_sid AND C_PROJECT<>'总计' AND C_PROJECT<>'小计'   GROUP BY C_CATEGORY) t2 ON t1.C_CATEGORY = t2.C_CATEGORY
	SET t1.N_SCORE_REAL=t2.N
	WHERE t1.C_CATEGORY=t2.C_CATEGORY
	AND t1.C_PROJECT='小计';
	
	/*总计*/
	UPDATE ssm_grade_e_d t1
	,(SELECT SUM(N_SCORE_REAL) N FROM ssm_grade_e_d WHERE R_SID=in_sid AND C_PROJECT='小计' ) t2
	SET t1.N_SCORE_REAL=t2.N
	WHERE t1.R_SID=in_sid 
	AND t1.C_PROJECT='总计';
    END$$

/*自评完成处理*/
DROP PROCEDURE IF EXISTS `P_GRADE_COMPLETE_E`$$
CREATE PROCEDURE P_GRADE_COMPLETE_E(IN in_sid INT)  
    BEGIN
	/*更改状态*/
	UPDATE SSM_GRADE_E_M set N_STATE=1 where SID=in_sid;

	/*从自评报告模板生成*/
	INSERT INTO SSM_GRADE_REPORT(R_SID,C_CONTENT)
	select in_sid,t3.C_CONTENT from 
	SSM_GRADE_E_M t1
	left join SYS_TENANT_E_PROFESSION t2 on t1.r_tenant=t2.r_tenant
	left join SSM_GRADE_REPORT_TMP t3 on t3.P_PROFESSION=t2.P_PROFESSION
	where not exists(select 1 from SSM_GRADE_REPORT where r_sid=in_sid);
    END$$



/*自评保存验证*/
DROP FUNCTION IF EXISTS `F_GRADE_CHECK_E`$$
 CREATE FUNCTION F_GRADE_CHECK_E(in_sid INT(11))
    RETURNS INT
  BEGIN
	DECLARE n INT;
	SELECT COUNT(*) INTO n FROM SSM_GRADE_E_D WHERE R_SID=IN_SID AND IFNULL(N_SCORE_REAL,0)=0 AND IFNULL(B_BLANK,'0')<>'1';
	RETURN n;
  END$$

/*评审完成处理*/
DROP PROCEDURE IF EXISTS `P_GRADE_COMPLETE_R`$$
CREATE PROCEDURE P_GRADE_COMPLETE_R(IN in_sid INT)  
    BEGIN
	/*更改状态*/
	UPDATE SSM_GRADE_R_M set N_STATE=1 where SID=in_sid;
    END$$

/*评审完成验证*/
DROP FUNCTION IF EXISTS `F_GRADE_CHECK_R`$$
CREATE FUNCTION F_GRADE_CHECK_R(in_sid INT(11),in_eid INT(11))
  RETURNS INT
  BEGIN
    DECLARE n1 INT;
    DECLARE n2 INT;
    SELECT COUNT(*) INTO N1 FROM SSM_GRADE_E_D WHERE R_SID=in_eid AND IFNULL(B_BLANK,0)=0 AND C_PROJECT<>'小计' AND C_PROJECT<>'总计';
    SELECT COUNT(*) INTO N2 FROM SSM_GRADE_R_D WHERE R_SID=in_sid AND IFNULL(N_SCORE_REAL,0)=0;
  RETURN n1-n2;
END$$

DELIMITER ;