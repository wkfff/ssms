DELIMITER $$

/*评分汇总——空项分*/
CREATE OR REPLACE VIEW V_GRADE_SUM_BLANK AS
SELECT R_SID,C_PROJECT,SUM(IFNULL(N_SCORE,0))  N FROM SSM_GRADE_E_D 
		WHERE C_PROJECT<>'总计' AND C_PROJECT<>'小计'  
		AND IFNULL(B_BLANK,'0')='1'  GROUP BY R_SID,C_PROJECT;$$
		
/*评分汇总——实际得分*/		
CREATE OR REPLACE VIEW V_GRADE_SUM_REAL AS
SELECT R_SID,C_PROJECT,SUM(IFNULL(N_SCORE_REAL,0))  N FROM SSM_GRADE_E_D  
			WHERE C_PROJECT<>'总计' AND C_PROJECT<>'小计'  
			AND IFNULL(B_BLANK,'0')<>'1' GROUP BY R_SID,C_PROJECT;$$
					
/*评分汇总——数据*/
CREATE OR REPLACE VIEW V_GRADE_SUM_DATA AS
SELECT T1.R_SID, T1.C_PROJECT,T1.N_INDEX
	,SUM(IFNULL(T1.N_SCORE,0)) N_SUBTOTAL /*项目标准分值小计*/
	,IFNULL(T2.N,0) N_BLANK/*空项分*/
	,IFNULL(T3.N,0) N_REAL /*实际得分*/
	,SUM(IFNULL(T1.N_SCORE,0)) -IFNULL(T2.N,0) N_GET/*应得分*/
	FROM SSM_GRADE_E_D T1 
	/*空项分*/
	LEFT JOIN V_GRADE_SUM_BLANK T2 
		ON T1.R_SID = T2.R_SID AND T1.C_PROJECT=T2.C_PROJECT
	/*实际得分*/
	LEFT JOIN  V_GRADE_SUM_REAL T3 
			ON T1.R_SID = T3.R_SID AND T1.C_PROJECT=T3.C_PROJECT	
	WHERE T1.C_PROJECT<>'总计' AND T1.C_PROJECT<>'小计' 
	GROUP BY T1.R_SID,T1.C_PROJECT 	
	ORDER BY N_INDEX;$$
	
/*评分汇总——小计*/	
CREATE OR REPLACE VIEW V_GRADE_SUM_SUBTOTAL AS
SELECT T1.R_SID
	,SUM(IFNULL(T1.N_SUBTOTAL,0)) N_SUBTOTAL /*项目标准分值小计*/
	,SUM(IFNULL(T1.N_BLANK,0)) N_BLANK/*空项分小计*/
	,SUM(IFNULL(T1.N_REAL,0)) N_REAL /*实际得分小计*/
	,SUM(IFNULL(T1.N_GET,0))  N_GET/*应得分小计*/
	FROM V_GRADE_SUM_DATA T1 
	GROUP BY T1.R_SID;$$

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
SELECT R_SID,'小计',99999,N_SUBTOTAL,N_BLANK,N_REAL,N_GET,CONVERT(
	IFNULL(N_REAL,0) / IFNULL(N_GET,0)*100
	,DECIMAL(4,1))  N_P 
	FROM V_GRADE_SUM_SUBTOTAL T3;$$

		
/*扣分汇总视图*/
CREATE OR REPLACE VIEW V_GRADE_SUM_DED AS
	SELECT R_SID,C_CATEGORY,C_PROJECT,
	IFNULL(C_CONTENT,'') C_CONTENT,
	IFNULL(C_DESC,'') C_DESC,
	IFNULL(N_SCORE,0)-IFNULL(N_SCORE_REAL,0) N_DED
	FROM SSM_GRADE_E_D
	WHERE IFNULL(N_SCORE_REAL,0)<IFNULL(N_SCORE,0) 
	AND C_PROJECT<>'小计' AND C_PROJECT<>'总计'
	ORDER BY N_INDEX;$$

/*待评审企业自评列表*/
CREATE OR REPLACE VIEW V_GRADE_E_M AS
	SELECT T1.SID,T1.C_TITLE,IFNULL(T2.C_NAME,'佚名')  C_NAME ,
	T2.P_PROVINCE,T2.P_CITY,T2.P_COUNTY FROM SSM_GRADE_E_M T1
	INNER JOIN SYS_TENANT_E T2 ON T1.R_TENANT=T2.SID
	WHERE T1.N_STATE=1 
	AND NOT EXISTS(SELECT 1 FROM SSM_GRADE_R_M WHERE R_EID=T1.SID);$$

/*评审内容*/
CREATE OR REPLACE VIEW V_GRADE_R_D AS
	SELECT T1.C_CATEGORY,T1.C_PROJECT,T1.C_CONTENT,
	T1.N_SCORE,T1.C_METHOD,T1.C_DESC C_REQUEST,T1.N_SCORE_REAL N_SCORE_SELF,T1.N_INDEX, 
	T2.SID R_SID,T3.N_SCORE_REAL,T3.C_DESC,T1.SID R_DID
	 FROM SSM_GRADE_E_D T1
	INNER JOIN SSM_GRADE_R_M T2 ON T1.R_SID=T2.R_EID
	LEFT JOIN SSM_GRADE_R_D T3 ON T2.SID=T3.R_SID
	ORDER BY T1.N_INDEX;$$

/*评审列表*/
CREATE OR REPLACE VIEW V_GRADE_R_M AS
	SELECT T1.SID,T1.C_TITLE,T2.C_NAME C_NAME ,
	T2.P_PROVINCE,T2.P_CITY,T2.P_COUNTY,T2.N_STATE
	FROM SSM_GRADE_R_M T1
	INNER JOIN SYS_TENANT_E t2 ON t1.r_eid=t2.sid;$$

/*租户用户列表*/
CREATE OR REPLACE VIEW TENANT_USER AS
    SELECT A.`C_NAME` TENANT_NAME, UPPER(A.`C_CODE`) TENANT_CODE, SUBSTR(UPPER(A.`C_CODE`), 1, 1) TENANT_TYPE, A.`SID` TENANT_ID, B.`C_NAME` USER_NAME, UPPER(B.`C_USER`) USER_CODE, UPPER(B.`C_PASSWD`) USER_PASSWORD, B.`SID` USER_ID
    FROM SYS_TENANT_E A INNER JOIN SYS_TENANT_E_USER B ON A.`SID` = B.`R_SID`
    WHERE IFNULL(A.B_DELETE, 0) = 0
    UNION ALL
    SELECT A.`C_NAME` TENANT_NAME, UPPER(A.`C_CODE`) TENANT_CODE, SUBSTR(UPPER(A.`C_CODE`), 1, 1) TENANT_TYPE, A.`SID` TENANT_ID, B.`C_NAME` USER_NAME, UPPER(B.`C_USER`) USER_CODE, UPPER(B.`C_PASSWD`) USER_PASSWORD, B.`SID` USER_ID
    FROM SYS_TENANT_R A INNER JOIN SYS_TENANT_R_USER B ON A.`SID` = B.`R_SID`
    WHERE IFNULL(A.B_DELETE, 0) = 0
    UNION ALL
    SELECT A.`C_NAME` TENANT_NAME, UPPER(A.`C_CODE`) TENANT_CODE, SUBSTR(UPPER(A.`C_CODE`), 1, 1) TENANT_TYPE, A.`SID` TENANT_ID, B.`C_NAME` USER_NAME, UPPER(B.`C_USER`) USER_CODE, UPPER(B.`C_PASSWD`) USER_PASSWORD, B.`SID` USER_ID
    FROM SYS_TENANT_G A INNER JOIN SYS_TENANT_G_USER B ON A.`SID` = B.`R_SID`
    WHERE IFNULL(A.B_DELETE, 0) = 0
    UNION ALL
    SELECT '系统用户' TENANT_NAME, 'SYSTEM' TENANT_CODE, 'S' TENANT_TYPE, 0 TENANT_ID, A.`C_NAME` USER_NAME, UPPER(A.C_NAME) USER_CODE, UPPER(A.`C_PASSWD`) USER_PASSWORD, A.`SID` USER_ID
    FROM SYS_USER A
;$$

DELIMITER $$