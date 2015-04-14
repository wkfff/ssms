-- 初始化数据库数据
INSERT INTO SYS_PARA_MULTI(C_CODE, C_VALUE, R_CREATOR, S_CREATOR, T_CREATE, R_UPDATE, S_UPDATE)
SELECT * FROM (
    SELECT 'USER_SEX_1' C_CODE, '男' C_VALUE, 'ADMIN' R_CREATOR, 'ADMIN' S_CREATOR, NOW() T_CREATE, 'ADMIN' R_UPDATE, 'ADMIN' S_UPDATE FROM DUAL
    UNION
    SELECT 'USER_SEX_2' C_CODE, '女' C_VALUE, 'ADMIN' R_CREATOR, 'ADMIN' S_CREATOR, NOW() T_CREATE, 'ADMIN' R_UPDATE, 'ADMIN' S_UPDATE FROM DUAL
) A
WHERE A.C_CODE NOT IN (SELECT C_CODE FROM SYS_PARA_MULTI)
;