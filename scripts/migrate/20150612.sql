alter table `sys_tenant_r`
add column `P_AT_PROVINCE` varchar(32) NULL COMMENT '所属辖区省份编号' ;
alter table `sys_tenant_r`
add column `S_AT_PROVINCE` varchar(100) NULL COMMENT '所属辖区省份' ;
alter table `sys_tenant_r`
add column `P_AT_CITY` varchar(32) NULL COMMENT '所属辖区地市编号' ;
alter table `sys_tenant_r`
add column `S_AT_CITY` varchar(100) NULL COMMENT '所属辖区地市' ;
alter table `sys_tenant_r`
add column `P_AT_COUNTY` varchar(32) NULL COMMENT '所属辖区地县编号' ;
alter table `sys_tenant_r`
add column `S_AT_COUNTY` varchar(100) NULL COMMENT '所属辖区地县' ;