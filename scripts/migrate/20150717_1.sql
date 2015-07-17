CREATE OR REPLACE 
    
    VIEW `ssms`.`todo_stdtmp_file_03` 
    AS
   SELECT a.* FROM `ssm_stdtmp_file_03` a WHERE a.`SID` NOT IN (
   SELECT b.`R_SID` FROM `sys_attach_file` b WHERE YEAR(b.`T_CREATE`)= YEAR(NOW()) AND b.`R_TENANT`=a.`R_TENANT` AND
   b.`P_TENANT`=a.`P_TENANT` AND  b.`R_TABLE`='STDTMP_FILE_03' 
   ) AND a.`SID` NOT IN(SELECT R_SID FROM sys_todo WHERE C_CONTROL='STDFILE03')

CREATE OR REPLACE 
    VIEW `ssms`.`todo_stdtmp_file_04` 
    AS
    SELECT a.* FROM `ssm_stdtmp_file_04` a WHERE a.`SID` NOT IN (
    SELECT b.`R_SID` FROM `sys_attach_file` b WHERE YEAR(b.`T_CREATE`)= YEAR(NOW()) AND b.`R_TENANT`=a.`R_TENANT` AND
    b.`P_TENANT`=a.`P_TENANT` AND  b.`R_TABLE`='STDTMP_FILE_04' 
    ) AND a.`SID` NOT IN(SELECT R_SID FROM sys_todo WHERE C_CONTROL='STDFILE04')
