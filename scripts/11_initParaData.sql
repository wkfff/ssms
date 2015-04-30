/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = '' */;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;


-- 初始化系统参数
INSERT INTO `SYS_PARA_MULTI` (`SID`, `C_NAME`, `C_CODE`, `C_VALUE`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TENANT`, `S_TENANT`, `P_TENANT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`)
VALUES
  (1, 'USER_SEX', '1', '男', 0, 'ADMIN', '2015-04-20 11:08:20', 0, 'ADMIN', '2015-04-20 11:08:20', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 'USER_SEX', '2', '女', 0, 'ADMIN', '2015-04-20 11:08:20', 0, 'ADMIN', '2015-04-20 11:08:20', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, 'SYS_PROFESSION', '01', '工贸', 0, 'admin', '2015-04-20 11:41:51', NULL, NULL, '2015-04-20 11:41:51', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (12, 'SYS_CYCLE', '01', '月', NULL, NULL, NULL, NULL, NULL, '2015-04-30 10:11:38', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, 'SYS_CYCLE', '02', '季', NULL, NULL, NULL, NULL, NULL, '2015-04-30 10:11:47', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, 'SYS_CYCLE', '03', '年', NULL, NULL, NULL, NULL, NULL, '2015-04-30 10:11:56', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- 初始化工贸模型
INSERT INTO `SYS_STDTMP_FOLDER` (`SID`, `C_NAME`, `R_SID`, `C_DESC`, `C_LOGO`, `P_PROFESSION`, `S_PROFESSION`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TENANT`, `S_TENANT`, `P_TENANT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`)
VALUES
  (1, '工贸达标体系模板', 0, 'adfdfe', NULL, '01', '工贸', 0, 'admin', '2015-04-20 11:41:52', 0, 'admin', '2015-04-21 22:35:54', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (4, '安全生产目标', 1, '安全生产目标', NULL, '01', '工贸', 0, 'admin', '2015-04-21 10:36:28', 0, 'admin', '2015-04-21 22:36:36', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (7, '目标', 4, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:36:52', NULL, NULL, '2015-04-21 22:36:52', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (8, '监测与考核', 4, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:37:09', NULL, NULL, '2015-04-21 22:37:09', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (9, '组织机构和职责', 1, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:50:14', NULL, NULL, '2015-04-21 22:50:14', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (10, '安全投入', 1, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:50:23', NULL, NULL, '2015-04-21 22:50:23', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (11, '法律法规', 1, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:50:40', NULL, NULL, '2015-04-21 22:50:40', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (12, '教育培训', 1, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:50:49', 0, 'admin', '2015-04-21 22:51:05', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (13, '生产设备设施', 1, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:51:22', NULL, NULL, '2015-04-21 22:51:22', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (14, '组织机构与人员', 9, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:51:44', NULL, NULL, '2015-04-21 22:51:44', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (15, '职责', 9, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:51:52', NULL, NULL, '2015-04-21 22:51:52', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (16, '安全生产费用', 10, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:52:23', NULL, NULL, '2015-04-21 22:52:23', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (17, '相关保险', 10, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:52:31', NULL, NULL, '2015-04-21 22:52:31', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (18, '法律法规、标准规范', 11, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:52:46', NULL, NULL, '2015-04-21 22:52:46', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (19, '规章制度', 11, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:52:57', NULL, NULL, '2015-04-21 22:52:57', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (20, '操作规程', 11, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:53:05', NULL, NULL, '2015-04-21 22:53:05', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (21, '评估', 11, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:53:14', NULL, NULL, '2015-04-21 22:53:14', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (22, '文件和档案管理', 11, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:53:23', NULL, NULL, '2015-04-21 22:53:23', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (23, '教育培训管理', 12, '', NULL, '01', '工贸', 0, 'admin', '2015-04-22 10:36:55', NULL, NULL, '2015-04-22 10:36:55', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (24, '安全生产管理人员教育培训', 12, '', NULL, '01', '工贸', 0, 'admin', '2015-04-22 10:37:12', NULL, NULL, '2015-04-22 10:37:12', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (25, '操作岗位人员教育培训', 12, '', NULL, '01', '工贸', 0, 'admin', '2015-04-22 10:39:10', NULL, NULL, '2015-04-22 10:39:10', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (26, '特种作业人员教育培训', 12, '', NULL, '01', '工贸', 0, 'admin', '2015-04-22 10:39:25', NULL, NULL, '2015-04-22 10:39:25', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (27, '其他人员教育培训', 12, '', NULL, '01', '工贸', 0, 'admin', '2015-04-22 10:39:35', NULL, NULL, '2015-04-22 10:39:35', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (28, '安全文化建设', 12, '', NULL, '01', '工贸', 0, 'admin', '2015-04-22 10:39:46', NULL, NULL, '2015-04-22 10:39:46', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (29, '生产设备设施建设', 13, '', NULL, '01', '工贸', 0, 'admin', '2015-04-22 10:40:04', NULL, NULL, '2015-04-22 10:40:04', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (30, '设备设施运行管理', 13, '', NULL, '01', '工贸', 0, 'admin', '2015-04-22 10:40:18', NULL, NULL, '2015-04-22 10:40:18', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
  (31, '设备设施到货验收和报废拆除', 13, '', NULL, '01', '工贸', 0, 'admin', '2015-04-22 10:57:20', NULL, NULL, '2015-04-22 10:57:20', 0, 'admin', 'S', NULL, NULL, NULL, NULL);

-- 初始化导航
INSERT INTO `SYS_NAV` (`SID`, `R_SID`, `C_NAME`, `C_URL`, `C_DESC`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TENANT`, `S_TENANT`, `P_TENANT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`, `C_ICON`)
VALUES
  (3, 0, '功能导航菜单', '', '123', NULL, NULL, NULL, 0, 'admin', '2015-04-29 16:42:57', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'agency'),
  (13, 3, '企业端导航', '', '', 0, 'admin', '2015-04-26 10:57:17', 0, 'admin', '2015-04-29 16:49:22', 0, 'admin', 'S', 0, '', 0, 0, ''),
  (14, 3, '评审端导航', '', '', 0, 'admin', '2015-04-26 10:57:26', 0, 'admin', '2015-04-29 16:48:10', 0, 'admin', 'S', 0, '', 0, 0, ''),
  (15, 3, '政府端导航', '', '', 0, 'admin', '2015-04-26 10:57:33', 0, 'admin', '2015-04-29 16:48:11', 0, 'admin', 'S', 0, '', 0, 0, ''),
  (16, 3, '系统运维端导航', '', '', 0, 'admin', '2015-04-26 10:57:46', 0, 'admin', '2015-04-29 16:48:12', 0, 'admin', 'S', 0, '', 0, 0, ''),
  (17, 13, '工作中心', '/index', '', 0, 'admin', '2015-04-26 10:58:17', NULL, NULL, '2015-04-26 10:58:17', 0, 'admin', 'S', NULL, NULL, NULL, NULL, NULL),
  (18, 13, '达标创建', '', '', 0, 'admin', '2015-04-26 10:58:42', -1, NULL, '2015-04-27 14:40:42', 0, 'admin', 'S', NULL, NULL, NULL, NULL, NULL),
  (19, 13, '在线自评', '', '', 0, 'admin', '2015-04-26 10:58:58', NULL, NULL, '2015-04-26 10:58:58', 0, 'admin', 'S', NULL, NULL, NULL, NULL, NULL),
  (20, 16, '系统配置', '', '', 0, 'admin', '2015-04-27 11:33:24', 0, 'admin', '2015-04-29 15:45:50', 0, 'admin', 'S', 0, '', 3, 0, 'nav-info'),
  (21, 20, '系统导航配置', '/sys/nav/index.html', '', 0, 'admin', '2015-04-27 11:33:44', NULL, NULL, '2015-04-27 11:33:44', 0, 'admin', 'S', NULL, NULL, NULL, NULL, NULL),
  (22, 19, '开始自评', '/e/grade_m/rec.html', '', -1, NULL, '2015-04-27 14:40:08', NULL, NULL, '2015-04-27 14:40:08', -1, NULL, 'E', NULL, NULL, NULL, NULL, NULL),
  (23, 19, '自评历史', '/e/grade_m/history.html', '', -1, NULL, '2015-04-27 14:40:36', NULL, NULL, '2015-04-27 14:40:36', -1, NULL, 'E', NULL, NULL, NULL, NULL, NULL),
  (24, 19, '自评草稿', '/e/grade_m/draft.html', '', -1, NULL, '2015-04-27 14:42:13', NULL, NULL, '2015-04-27 14:42:13', -1, NULL, 'E', NULL, NULL, NULL, NULL, NULL),
  (25, 19, '自评报告', '/e/grade_rep/index.html', '', -1, NULL, '2015-04-27 14:42:13', NULL, NULL, '2015-04-27 14:42:13', -1, NULL, 'E', NULL, NULL, NULL, NULL, NULL),
  (26, 19, '自评结果', '/e/grade_result/index.html', '', -1, NULL, '2015-04-27 14:42:13', NULL, NULL, '2015-04-27 14:42:13', -1, NULL, 'E', NULL, NULL, NULL, NULL, NULL),
  (25, 20, '专业管理', '/sys/profession/index.html', '', 0, 'admin', '2015-04-27 23:44:33', NULL, NULL, '2015-04-27 23:44:33', 0, 'admin', 'S', NULL, NULL, NULL, NULL, NULL),
  (26, 16, '租户管理', '', '', 0, 'admin', '2015-04-28 09:35:16', 0, 'admin', '2015-04-29 16:43:13', 0, 'admin', 'S', 0, '', 2, 0, 'agency'),
  (27, 26, '企业租户管理', '/sys/tenant_e/index.html', '', 0, 'admin', '2015-04-28 09:36:07', NULL, NULL, '2015-04-28 09:36:07', 0, 'admin', 'S', NULL, NULL, NULL, NULL, NULL),
  (28, 26, '政府租户管理', '/sys/tenant_g/index.html', '', 0, 'admin', '2015-04-28 09:37:51', 0, 'admin', '2015-04-28 09:38:35', 0, 'admin', 'S', NULL, NULL, NULL, NULL, NULL),
  (29, 26, '评审机构租户管理', '/sys/tenant_r/index.html', '', 0, 'admin', '2015-04-28 09:40:12', 0, 'admin', '2015-04-29 11:21:44', 0, 'admin', 'S', NULL, NULL, NULL, NULL, NULL);


/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;