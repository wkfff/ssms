/*自评统计项*/
DELIMITER $$
DROP PROCEDURE IF EXISTS `P_GRADE_SUM`$$
CREATE PROCEDURE P_GRADE_SUM(IN in_sid INT)  
    BEGIN
	/*得分、扣分、缺项统计*/
	UPDATE ssm_grade_e_m t1 
	LEFT JOIN (SELECT r_sid,COUNT(*)  N FROM SSM_GRADE_E_D  
		WHERE R_PROJECT<>'TOTAL' AND R_PROJECT<>'SUBTOTAL'  AND N_SCORE_REAL IS NOT NULL  AND N_SCORE_REAL=N_SCORE GROUP BY R_SID) t2 ON t1.sid = t2.r_sid
	LEFT JOIN (SELECT r_sid,COUNT(*)  N FROM SSM_GRADE_E_D 
		WHERE R_PROJECT<>'TOTAL' AND R_PROJECT<>'SUBTOTAL'  AND N_SCORE_REAL IS NOT NULL  AND N_SCORE_REAL<N_SCORE  GROUP BY R_SID) t3 ON t1.sid = t3.r_sid
	LEFT JOIN (SELECT r_sid,COUNT(*)  N FROM SSM_GRADE_E_D 
		WHERE R_PROJECT<>'TOTAL' AND R_PROJECT<>'SUBTOTAL'  AND B_BLANK='1'  GROUP BY R_SID) t4 ON t1.sid = t4.r_sid
	SET t1.N_GET=t2.N,t1.N_DEDUCT=t3.N,t1.N_LACK=t4.N
	WHERE t1.sid=in_sid;
	
	/*得分小计*/
	UPDATE ssm_grade_e_d t1
	LEFT JOIN (SELECT R_CATEGORY,SUM(N_SCORE_REAL) N FROM ssm_grade_e_d 
		WHERE R_SID=in_sid AND R_PROJECT<>'TOTAL' AND R_PROJECT<>'SUBTOTAL'   GROUP BY R_CATEGORY) t2 ON t1.R_CATEGORY = t2.R_CATEGORY
	SET t1.N_SCORE_REAL=t2.N
	WHERE t1.R_CATEGORY=t2.R_CATEGORY
	AND t1.R_PROJECT='SUBTOTAL';
	
	/*总计*/
	UPDATE ssm_grade_e_d t1
	,(SELECT SUM(N_SCORE_REAL) N FROM ssm_grade_e_d WHERE R_SID=in_sid AND R_PROJECT='SUBTOTAL' ) t2
	SET t1.N_SCORE_REAL=t2.N
	WHERE t1.R_SID=in_sid 
	AND t1.R_PROJECT='TOTAL';
    END$$

/*自评完成处理*/
DROP PROCEDURE IF EXISTS `P_GRADE_COMPLETE_SELF`$$
CREATE PROCEDURE P_GRADE_COMPLETE_SELF(IN in_sid INT)  
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
DROP FUNCTION IF EXISTS `F_GRADE_CHECK`$$
 CREATE FUNCTION F_GRADE_CHECK(in_sid INT(11))
    RETURNS INT
  BEGIN
	DECLARE n INT;
	SELECT COUNT(*) INTO n FROM SSM_GRADE_E_D WHERE R_SID=IN_SID AND N_SCORE_REAL IS NULL AND IFNULL(B_BLANK,'0')<>'1';
	RETURN n;
  END$$


DELIMITER $$
/*评分汇总——空项分*/
CREATE OR REPLACE VIEW V_GRADE_SUM_BLANK AS
SELECT R_SID,R_PROJECT,SUM(IFNULL(N_SCORE,0))  N FROM SSM_GRADE_E_D 
		WHERE R_PROJECT<>'TOTAL' AND R_PROJECT<>'SUBTOTAL'  
		AND IFNULL(B_BLANK,'0')='1'  GROUP BY R_SID,R_PROJECT
/*评分汇总——实际得分*/		
CREATE OR REPLACE VIEW V_GRADE_SUM_REAL AS
SELECT R_SID,R_PROJECT,SUM(IFNULL(N_SCORE_REAL,0))  N FROM SSM_GRADE_E_D  
			WHERE R_PROJECT<>'TOTAL' AND R_PROJECT<>'SUBTOTAL'  
			AND IFNULL(B_BLANK,'0')<>'1' GROUP BY R_SID,R_PROJECT
					
/*评分汇总——数据*/
CREATE OR REPLACE VIEW V_GRADE_SUM_DATA AS
SELECT T1.R_SID, T1.R_PROJECT,T1.S_PROJECT,T1.N_INDEX
	,SUM(IFNULL(T1.N_SCORE,0)) N_SUBTOTAL /*项目标准分值小计*/
	,IFNULL(T2.N,0) N_BLANK/*空项分*/
	,IFNULL(T3.N,0) N_REAL /*实际得分*/
	,SUM(IFNULL(T1.N_SCORE,0)) -IFNULL(T2.N,0) N_GET/*应得分*/
	FROM SSM_GRADE_E_D T1 
	/*空项分*/
	LEFT JOIN V_GRADE_SUM_BLANK T2 
		ON T1.R_SID = T2.R_SID AND T1.R_PROJECT=T2.R_PROJECT
	/*实际得分*/
	LEFT JOIN  V_GRADE_SUM_REAL T3 
			ON T1.R_SID = T3.R_SID AND T1.R_PROJECT=T3.R_PROJECT	
	WHERE T1.R_PROJECT<>'TOTAL' AND T1.R_PROJECT<>'SUBTOTAL' 
	GROUP BY T1.R_SID,T1.R_PROJECT 	
	ORDER BY N_INDEX
	
/*评分汇总——小计*/	
CREATE OR REPLACE VIEW V_GRADE_SUM_SUBTOTAL AS
SELECT T1.R_SID
	,SUM(IFNULL(T1.N_SUBTOTAL,0)) N_SUBTOTAL /*项目标准分值小计*/
	,SUM(IFNULL(T1.N_BLANK,0)) N_BLANK/*空项分小计*/
	,SUM(IFNULL(T1.N_REAL,0)) N_REAL /*实际得分小计*/
	,SUM(IFNULL(T1.N_GET,0))  N_GET/*应得分小计*/
	FROM V_GRADE_SUM_DATA T1 
	GROUP BY T1.R_SID

/*评分汇总视图*/
CREATE OR REPLACE VIEW V_GRADE_SUM AS
SELECT T1.*
	,CONVERT(
	IFNULL(T1.N_REAL,0) / IFNULL(T2.N_GET,0)*100
	,DECIMAL(4,1))  N_P /*百分制得分*/
	FROM V_GRADE_SUM_DATA T1 
	/*小计*/
	LEFT JOIN V_GRADE_SUM_SUBTOTAL T2 
		ON T1.R_SID = T2.R_SID
UNION ALL
SELECT R_SID,'','小计',99999,N_SUBTOTAL,N_BLANK,N_REAL,N_GET,CONVERT(
	IFNULL(N_REAL,0) / IFNULL(N_GET,0)*100
	,DECIMAL(4,1))  N_P 
	FROM V_GRADE_SUM_SUBTOTAL T3

		
/*扣分汇总视图*/
CREATE OR REPLACE VIEW V_GRADE_SUM_DED AS
SELECT R_SID,S_CATEGORY,S_PROJECT,
IFNULL(C_CONTENT,'') C_CONTENT,
IFNULL(C_DESC,'') C_DESC,
IFNULL(N_SCORE,0)-IFNULL(N_SCORE_REAL,0) N_DED
FROM SSM_GRADE_E_D
WHERE IFNULL(N_SCORE_REAL,0)<IFNULL(N_SCORE,0) 
AND R_PROJECT<>'SUBTOTAL' AND R_PROJECT<>'TOTAL'
ORDER BY N_INDEX

DELIMITER ;