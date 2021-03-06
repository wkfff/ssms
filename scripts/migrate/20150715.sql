/*租户用户列表(添加邮箱)*/
CREATE OR REPLACE VIEW TENANT_USER AS
    SELECT A.`C_NAME` TENANT_NAME, UPPER(A.`C_CODE`) TENANT_CODE, SUBSTR(UPPER(A.`C_CODE`), 1, 1) TENANT_TYPE, A.`SID` TENANT_ID, B.`C_NAME` USER_NAME, UPPER(B.`C_USER`) USER_CODE, UPPER(B.`C_PASSWD`) USER_PASSWORD, B.`SID` USER_ID,A.`C_EMAIL`
    FROM SYS_TENANT_E A INNER JOIN SYS_TENANT_E_USER B ON A.`SID` = B.`R_SID`
    WHERE IFNULL(A.B_DELETE, 0) = 0
    UNION ALL
    SELECT A.`C_NAME` TENANT_NAME, UPPER(A.`C_CODE`) TENANT_CODE, SUBSTR(UPPER(A.`C_CODE`), 1, 1) TENANT_TYPE, A.`SID` TENANT_ID, B.`C_NAME` USER_NAME, UPPER(B.`C_USER`) USER_CODE, UPPER(B.`C_PASSWD`) USER_PASSWORD, B.`SID` USER_ID,A.`C_EMAIL`
    FROM SYS_TENANT_R A INNER JOIN SYS_TENANT_R_USER B ON A.`SID` = B.`R_SID`
    WHERE IFNULL(A.B_DELETE, 0) = 0
    UNION ALL
    SELECT A.`C_NAME` TENANT_NAME, UPPER(A.`C_CODE`) TENANT_CODE, SUBSTR(UPPER(A.`C_CODE`), 1, 1) TENANT_TYPE, A.`SID` TENANT_ID, B.`C_NAME` USER_NAME, UPPER(B.`C_USER`) USER_CODE, UPPER(B.`C_PASSWD`) USER_PASSWORD, B.`SID` USER_ID,A.`C_EMAIL`
    FROM SYS_TENANT_G A INNER JOIN SYS_TENANT_G_USER B ON A.`SID` = B.`R_SID`
    WHERE IFNULL(A.B_DELETE, 0) = 0
    UNION ALL
    SELECT '系统用户' TENANT_NAME, 'SYSTEM' TENANT_CODE, 'S' TENANT_TYPE, 0 TENANT_ID, A.`C_NAME` USER_NAME, UPPER(A.C_NAME) USER_CODE, UPPER(A.`C_PASSWD`) USER_PASSWORD, A.`SID` USER_ID,A.`C_EMAIL`
    FROM SYS_USER A