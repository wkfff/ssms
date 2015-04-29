--自评统计项
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

DELIMITER ;