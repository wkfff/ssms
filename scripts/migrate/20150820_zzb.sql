ALTER TABLE `SYS_STDTMP_FILE_04`
	CHANGE COLUMN `N_TIME` `C_TIME` VARCHAR(50) NULL DEFAULT NULL COMMENT 'ѧʱ' AFTER `S_TYPE`;
;

ALTER TABLE `SYS_STDTMP_FILE_04_ARCH`
	CHANGE COLUMN `N_TIME` `C_TIME` VARCHAR(50) NULL DEFAULT NULL COMMENT 'ѧʱ' AFTER `S_TYPE`;
;

ALTER TABLE `SSM_STDTMP_FILE_04`
	CHANGE COLUMN `N_TIME` `C_TIME` VARCHAR(50) NULL DEFAULT NULL COMMENT 'ѧʱ' AFTER `S_TYPE`;
;

ALTER TABLE `SSM_STDTMP_FILE_04_ARCH`
	CHANGE COLUMN `N_TIME` `C_TIME` VARCHAR(50) NULL DEFAULT NULL COMMENT 'ѧʱ' AFTER `S_TYPE`;
;
