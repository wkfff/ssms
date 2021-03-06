/*评分汇总——空项分*/
CREATE OR REPLACE VIEW V_GRADE_SUM_BLANK AS
SELECT R_SID,C_PROJECT,SUM(IFNULL(N_SCORE,0))  N FROM SSM_GRADE_CONTENT
		WHERE C_PROJECT<>'总计' AND C_PROJECT<>'小计'
		AND IFNULL(B_BLANK,'0')='1'  GROUP BY R_SID,C_PROJECT;

/*评分汇总——实际得分*/
CREATE OR REPLACE VIEW V_GRADE_SUM_REAL AS
SELECT R_SID,C_PROJECT,SUM(IFNULL(N_SCORE_REAL,0))  N FROM SSM_GRADE_CONTENT
			WHERE C_PROJECT<>'总计' AND C_PROJECT<>'小计'
			AND IFNULL(B_BLANK,'0')<>'1' GROUP BY R_SID,C_PROJECT;

/*评分汇总——数据*/
CREATE OR REPLACE VIEW V_GRADE_SUM_DATA AS
SELECT T1.R_SID, T1.C_PROJECT,T1.N_INDEX
	,SUM(IFNULL(T1.N_SCORE,0)) N_SUBTOTAL /*项目标准分值小计*/
	,IFNULL(T2.N,0) N_BLANK/*空项分*/
	,IFNULL(T3.N,0) N_REAL /*实际得分*/
	,SUM(IFNULL(T1.N_SCORE,0)) -IFNULL(T2.N,0) N_GET/*应得分*/
	FROM SSM_GRADE_CONTENT T1
	/*空项分*/
	LEFT JOIN V_GRADE_SUM_BLANK T2
		ON T1.R_SID = T2.R_SID AND T1.C_PROJECT=T2.C_PROJECT
	/*实际得分*/
	LEFT JOIN  V_GRADE_SUM_REAL T3
			ON T1.R_SID = T3.R_SID AND T1.C_PROJECT=T3.C_PROJECT
	WHERE T1.C_PROJECT<>'总计' AND T1.C_PROJECT<>'小计'
	GROUP BY T1.R_SID,T1.C_PROJECT
	ORDER BY N_INDEX;

/*评分汇总——小计*/
CREATE OR REPLACE VIEW V_GRADE_SUM_SUBTOTAL AS
SELECT T1.R_SID
	,SUM(IFNULL(T1.N_SUBTOTAL,0)) N_SUBTOTAL /*项目标准分值小计*/
	,SUM(IFNULL(T1.N_BLANK,0)) N_BLANK/*空项分小计*/
	,SUM(IFNULL(T1.N_REAL,0)) N_REAL /*实际得分小计*/
	,SUM(IFNULL(T1.N_GET,0))  N_GET/*应得分小计*/
	FROM V_GRADE_SUM_DATA T1
	GROUP BY T1.R_SID;

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
	FROM V_GRADE_SUM_SUBTOTAL T3;


/*扣分汇总视图*/
CREATE OR REPLACE VIEW V_GRADE_SUM_DED AS
	SELECT R_SID,C_CATEGORY,C_PROJECT,
	IFNULL(C_CONTENT,'') C_CONTENT,
	IFNULL(C_DESC,'') C_DESC,
	IFNULL(N_SCORE,0)-IFNULL(N_SCORE_REAL,0) N_DED
	FROM SSM_GRADE_CONTENT
	WHERE IFNULL(N_SCORE_REAL,0)<IFNULL(N_SCORE,0)
	AND C_PROJECT<>'小计' AND C_PROJECT<>'总计'
	ORDER BY N_INDEX;