USE `ssms`;

/*Table structure for table `ssm_notice` */

DROP TABLE IF EXISTS `ssm_notice`;

CREATE TABLE `ssm_notice` (
  `SID` int(11) NOT NULL auto_increment COMMENT '自增主键',
  `C_TITLE` varchar(500) default NULL COMMENT '标题',
  `C_CONTENT` text COMMENT '内容',
  `T_BEGIN` datetime default NULL COMMENT '生效时间起',
  `T_END` datetime default NULL COMMENT '生效时间止',
  `N_READER` int(11) default NULL COMMENT '已阅人数',
  `R_PUBLISH` int(11) default NULL COMMENT '发布人SID',
  `S_PUBLISH` varchar(300) default NULL COMMENT '发布人名称',
  `T_PUBLISH` datetime default NULL COMMENT '发布时间',
  `R_CREATE` int(11) default NULL COMMENT '创建人编号',
  `S_CREATE` varchar(50) default NULL COMMENT '创建人',
  `T_CREATE` datetime default NULL COMMENT '创建时间',
  `R_UPDATE` int(11) default NULL COMMENT '更新人编号',
  `S_UPDATE` varchar(50) default NULL COMMENT '更新人',
  `T_UPDATE` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
  `R_TENANT` int(11) default NULL COMMENT '租户编号',
  `S_TENANT` varchar(300) default NULL COMMENT '租户名称',
  `P_TENANT` char(1) default NULL COMMENT '租户类型',
  `N_STATE` int(11) default NULL COMMENT '状态',
  `B_DELETE` char(1) default NULL COMMENT '删除标志',
  `N_INDEX` int(11) default NULL COMMENT '排序',
  `N_VERSION` int(11) default NULL COMMENT '版本',
  PRIMARY KEY  (`SID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `ssm_notice` */

insert  into `ssm_notice`(`SID`,`C_TITLE`,`C_CONTENT`,`T_BEGIN`,`T_END`,`N_READER`,`R_PUBLISH`,`S_PUBLISH`,`T_PUBLISH`,`R_CREATE`,`S_CREATE`,`T_CREATE`,`R_UPDATE`,`S_UPDATE`,`T_UPDATE`,`R_TENANT`,`S_TENANT`,`P_TENANT`,`N_STATE`,`B_DELETE`,`N_INDEX`,`N_VERSION`) values (1,'安全生产标准化1',NULL,NULL,NULL,NULL,9903,'福州鼓楼区政府','2015-06-14 08:39:53',9903,'管理员','2015-06-14 08:39:53',9903,'管理员','2015-06-14 09:31:08',9903,'福州鼓楼区政府','G',NULL,NULL,NULL,NULL),(2,'安全生产标准化2',NULL,NULL,NULL,NULL,9903,'福州鼓楼区政府','2015-06-14 08:33:44',9903,'管理员','2015-06-14 08:33:44',9903,'管理员','2015-06-14 09:31:12',9903,'福州鼓楼区政府','G',NULL,NULL,NULL,NULL),(3,'安全生产标准化3',NULL,NULL,NULL,NULL,9903,'福州鼓楼区政府','2015-06-14 08:40:06',9903,'管理员','2015-06-14 08:40:06',9903,'管理员','2015-06-14 09:31:13',9903,'福州鼓楼区政府','G',NULL,NULL,NULL,NULL),(4,'安全生产标准化4',NULL,NULL,NULL,NULL,9903,'福州鼓楼区政府','2015-06-14 08:36:15',9903,'管理员','2015-06-14 08:36:15',9903,'管理员','2015-06-14 09:31:15',9903,'福州鼓楼区政府','G',NULL,NULL,NULL,NULL);
