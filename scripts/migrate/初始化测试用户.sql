DELETE FROM sys_tenant_e WHERE sid=9901;
DELETE FROM sys_tenant_e_user WHERE sid=9901;
DELETE FROM sys_tenant_e_profession WHERE sid=9901;
DELETE FROM sys_tenant_r WHERE sid=9902;
DELETE FROM sys_tenant_r_user WHERE sid=9902;
DELETE FROM sys_tenant_g WHERE sid=9903;
DELETE FROM sys_tenant_g_user WHERE sid=9903;

INSERT INTO `sys_tenant_e` (`SID`, `C_NAME`, `C_CODE`, `R_INDUSTRY`, `P_STATE`, `S_STATE`, `P_PROVINCE`, `S_PROVINCE`, `P_CITY`, `S_CITY`, `P_COUNTY`, `S_COUNTY`, `C_ADDR`, `C_CONTACT`, `C_EMAIL`, `C_TEL`, `C_FAX`, `C_ZIP`, `C_WEBSITE`, `C_NUMBER`, `P_NATURE`, `S_NATURE`, `N_EMPLOYEE`, `N_SAFETY`, `N_SPECIAL`, `N_ASSETS`, `N_INCOME`, `C_SCOPE`, `C_SUMMARY`, `P_LEVEL`, `S_LEVEL`, `T_EXAMINE`, `T_EXAMINE_NEXT`, `T_PAY`, `T_PAY_NEXT`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TENANT`, `S_TENANT`, `P_TENANT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`, `R_REVIEW`, `S_REVIEW`) VALUES('9901','福建永创意信息科技有限公司','E3501029901','1','1',NULL,'350000','福建','350100','福州市','350102','鼓楼区','test','test','e@163.com','111',NULL,NULL,NULL,NULL,NULL,NULL,'0','0','0','0.0000','0.0000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','admin','2015-05-29 09:44:54',NULL,NULL,'2015-06-04 16:48:34','0','系统用户','S','0',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `sys_tenant_e_user` (`SID`, `C_NAME`, `C_USER`, `S_NAME`, `R_SID`, `P_SEX`, `S_SEX`, `T_BIRTH`, `C_EMAIL`, `C_MOBILE`, `C_TEL`, `C_DEPT`, `C_POSITION`, `C_CARD`, `C_SCHOOL`, `P_EDUCATION`, `S_EDUCATION`, `P_DEGREE`, `S_DEGREE`, `P_STATE`, `S_STATE`, `C_PASSWD`, `T_LOGIN`, `C_IP`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TENANT`, `S_TENANT`, `P_TENANT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`) VALUES('9901','管理员','admin','福建永创意信息科技有限公司','9901','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,'e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-06-05 09:31:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `sys_tenant_e_profession` (`SID`, `P_PROFESSION`, `S_PROFESSION`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TENANT`, `S_TENANT`, `P_TENANT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`) VALUES('9901','1','工贸',NULL,NULL,NULL,NULL,NULL,'2015-06-05 09:31:04','9901','9901','E',NULL,NULL,NULL,NULL);


INSERT INTO `sys_tenant_r` (`SID`, `C_CODE`, `C_NAME`, `P_STATE`, `S_STATE`, `P_PROVINCE`, `S_PROVINCE`, `P_CITY`, `S_CITY`, `P_COUNTY`, `S_COUNTY`, `C_ADDR`, `C_EMAIL`, `C_TEL`, `C_FAX`, `C_ZIP`, `C_NUMBER`, `C_ORG`, `P_LEVEL`, `S_LEVEL`, `N_FULLTIME`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TENANT`, `S_TENANT`, `P_TENANT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`) VALUES('9902','R3501029902','福州典正评审公司','1',NULL,'350000','福建','350100','福州市','350102','鼓楼区','工业路','dianzhen@163.com','0591-48516628',NULL,NULL,'3501020028','福州',NULL,NULL,NULL,'1','admin','2015-06-04 16:53:29',NULL,NULL,'2015-06-04 16:53:29','0','系统用户','S',NULL,NULL,NULL,NULL);
INSERT INTO `sys_tenant_r_user` (`SID`, `S_NAME`, `R_SID`, `C_NAME`, `C_USER`, `C_PASSWD`, `C_MOBILE`, `C_TEL`, `T_BIRTH`, `P_SEX`, `S_SEX`, `C_POSITION`, `C_CERT`, `C_RESUME`, `C_SCHOOL`, `P_EDUCATION`, `S_EDUCATION`, `P_DEGREE`, `S_DEGREE`, `P_STATE`, `S_STATE`, `T_LOGIN`, `C_IP`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TENANT`, `S_TENANT`, `P_TENANT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`) VALUES('9902','福州典正评审公司','9902','管理员','admin','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-06-04 16:53:29',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

INSERT INTO `sys_tenant_g` (`SID`, `C_NAME`, `C_CODE`, `P_PROVINCE`, `S_PROVINCE`, `P_CITY`, `S_CITY`, `P_COUNTY`, `S_COUNTY`, `C_ADDR`, `C_EMAIL`, `C_TEL`, `C_FAX`, `C_ZIP`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TENANT`, `S_TENANT`, `P_TENANT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`) VALUES('9903','福州鼓楼区政府','G3501029903','350000','福建','350100','福州市','350102','鼓楼区','12121212','10085','3232323',NULL,NULL,'1','admin','2015-05-27 17:40:40',NULL,NULL,'2015-05-27 17:40:36','0','系统用户','S',NULL,'1',NULL,NULL);
INSERT INTO `sys_tenant_g_user` (`SID`, `C_NAME`, `C_USER`, `S_NAME`, `R_SID`, `C_PASSWD`, `C_MOBILE`, `C_TEL`, `T_BIRTH`, `P_SEX`, `S_SEX`, `C_POSITION`, `C_RESUME`, `C_SCHOOL`, `P_EDUCATION`, `S_EDUCATION`, `P_DEGREE`, `S_DEGREE`, `P_STATE`, `S_STATE`, `T_LOGIN`, `C_IP`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TENANT`, `S_TENANT`, `P_TENANT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`) VALUES('9903','管理员','admin','福州鼓楼区政府','9903','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-27 17:39:50',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
