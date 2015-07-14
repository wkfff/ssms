-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.29 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.2.0.4974
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- 正在导出表  ssms.sys_nav 的数据：~0 rows (大约)
DELETE FROM `sys_nav`;
/*!40000 ALTER TABLE `sys_nav` DISABLE KEYS */;
INSERT INTO `sys_nav` (`SID`, `R_SID`, `C_NAME`, `C_URL`, `C_DESC`, `C_ICON`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TENANT`, `S_TENANT`, `P_TENANT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`) VALUES
	(3, 0, '功能导航菜单', '', '123', 'agency', NULL, NULL, NULL, 0, 'admin', '2015-05-22 14:55:25', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(13, 3, '企业端导航', NULL, 'aaa', NULL, 0, 'admin', '2015-04-26 10:57:17', 0, 'admin', '2015-05-22 16:32:54', 0, 'admin', 'S', 0, NULL, 0, 0),
	(14, 3, '评审端导航', '', '', '', 0, 'admin', '2015-04-26 10:57:26', 0, 'admin', '2015-05-22 14:55:26', 0, 'admin', 'S', 0, '', 0, 0),
	(15, 3, '政府端导航', '', '', '', 0, 'admin', '2015-04-26 10:57:33', 0, 'admin', '2015-05-22 14:55:27', 0, 'admin', 'S', 0, '', 0, 0),
	(16, 3, '系统运维端导航', '', '', '', 0, 'admin', '2015-04-26 10:57:46', 0, 'admin', '2015-05-22 14:55:28', 0, 'admin', 'S', 0, '', 0, 0),
	(18, 13, '13要素自主创建', '/e/stdtmp/tree', NULL, 'create', 0, 'admin', '2015-04-26 10:58:42', 1, 'admin', '2015-06-22 23:52:12', 0, 'admin', 'S', 0, NULL, 1, 0),
	(19, 13, '在线自评', '/e/gradeplan/', NULL, 'do', 0, 'admin', '2015-04-26 10:58:58', 1, 'admin', '2015-06-29 11:35:06', 0, 'admin', 'S', 0, NULL, 3, 0),
	(20, 16, '系统配置', '', '', 'config', 0, 'admin', '2015-04-27 11:33:24', 0, 'admin', '2015-05-22 14:55:29', 0, 'admin', 'S', 0, '', 3, 0),
	(21, 20, '系统导航配置', '/sys/nav/', '', '', 0, 'admin', '2015-04-27 11:33:44', 0, 'admin', '2015-05-22 14:55:02', 0, 'admin', 'S', 0, '', 99, 0),
	(27, 20, '专业管理', '/sys/professions/', NULL, NULL, 0, 'admin', '2015-04-27 23:44:33', 1, 'admin', '2015-06-22 23:40:28', 0, 'admin', 'S', 0, '', 3, 0),
	(28, 16, '租户管理', '', '', 'user', 0, 'admin', '2015-04-28 09:35:16', 0, 'admin', '2015-05-22 14:55:31', 0, 'admin', 'S', 0, '', 2, 0),
	(29, 28, '企业租户管理', '/sys/tenant_e/', '', '', 0, 'admin', '2015-04-28 09:36:07', 0, 'admin', '2015-05-22 14:55:02', 0, 'admin', 'S', 0, '', 0, 0),
	(30, 28, '政府租户管理', '/sys/tenant_g/', '', '', 0, 'admin', '2015-04-28 09:37:51', 0, 'admin', '2015-05-22 14:55:02', 0, 'admin', 'S', 0, '', 0, 0),
	(31, 28, '评审机构租户管理', '/sys/tenant_r/', '', '', 0, 'admin', '2015-04-28 09:40:12', 0, 'admin', '2015-05-22 14:55:02', 0, 'admin', 'S', 0, '', 0, 0),
	(32, 15, '通知公告', '', '', '', 0, 'admin', '2015-05-01 07:59:44', NULL, NULL, '2015-05-22 14:55:34', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
	(33, 32, '已发布', '/sys/notice/publics/', NULL, NULL, 0, 'admin', '2015-05-01 08:00:19', 1, 'admin', '2015-07-14 10:34:15', 0, 'admin', 'S', NULL, NULL, 4, NULL),
	(34, 32, '草稿箱', '/sys/notice/drafts/', NULL, NULL, 0, 'admin', '2015-05-01 08:00:35', 1, 'admin', '2015-07-14 10:34:15', 0, 'admin', 'S', NULL, NULL, 3, NULL),
	(35, 20, '模板管理', '/sys/template/', '', '', 0, 'admin', '2015-05-05 11:50:44', NULL, NULL, '2015-05-22 14:55:02', 0, 'admin', 'S', NULL, NULL, 1, NULL),
	(37, 20, '参数管理', '/sys/para_multi/', '', '', 1, 'admin', '2015-05-14 23:11:43', NULL, NULL, '2015-05-22 14:55:02', 1, 'admin', 'S', NULL, NULL, 98, NULL),
	(38, 14, '评审办理', '/r/grade/index', NULL, 'do', 1, 'admin', '2015-05-15 09:46:41', 1, 'admin', '2015-06-29 11:35:56', 1, 'admin', 'S', NULL, NULL, 1, NULL),
	(43, 13, '通知公告', '/sys/notice/receives', NULL, 'notice', 1, 'admin', '2015-05-16 10:50:39', 1, 'admin', '2015-07-14 10:34:44', 1, 'admin', 'S', 0, NULL, 5, 0),
	(44, 13, '知识库', '/knowledge/', NULL, 'knowledge', 1, 'admin', '2015-05-16 10:50:39', 1, 'admin', '2015-06-12 15:30:14', 1, 'admin', 'S', 0, NULL, 7, 0),
	(45, 13, '设置', NULL, NULL, 'config', 1, 'admin', '2015-05-16 10:50:39', 1, 'admin', '2015-06-14 14:14:29', 1, 'admin', 'S', 0, NULL, 8, 0),
	(46, 14, '评审历史', '/r/reviewhistory/', NULL, 'history', 1, 'admin', '2015-05-15 09:47:25', 1, 'admin', '2015-06-29 11:36:09', 1, 'admin', 'S', NULL, NULL, 2, NULL),
	(47, 14, '证书管理', '/static/carts.html', NULL, 'cert', 1, 'admin', '2015-05-15 09:47:25', 1, 'admin', '2015-06-11 17:33:31', 1, 'admin', 'S', NULL, NULL, 3, NULL),
	(48, 14, '通知公告', NULL, NULL, 'notice', 1, 'admin', '2015-05-15 09:47:25', 1, 'admin', '2015-06-10 16:50:08', 1, 'admin', 'S', NULL, NULL, 4, NULL),
	(49, 14, '知识库', '/knowledge/', NULL, 'knowledge', 1, 'admin', '2015-05-15 09:47:25', 1, 'admin', '2015-06-13 15:56:00', 1, 'admin', 'S', NULL, NULL, 5, NULL),
	(50, 14, '设置', NULL, NULL, 'config', 1, 'admin', '2015-05-15 09:47:25', 1, 'admin', '2015-06-11 09:09:46', 1, 'admin', 'S', NULL, NULL, 8, NULL),
	(51, 13, '评审结果', '/e/reviewresult', NULL, 'history', NULL, NULL, NULL, 1, 'admin', '2015-06-29 11:35:39', NULL, NULL, NULL, NULL, NULL, 6, NULL),
	(54, 13, '密码修改', '/e/tenant_eu/password', NULL, 'pwd', NULL, NULL, NULL, 1, 'admin', '2015-06-23 16:32:10', NULL, NULL, NULL, NULL, NULL, 9, NULL),
	(62, 45, '企业信息维护', '/e/tenant_e/rec', '123', 'agency', NULL, NULL, NULL, 1, 'admin', '2015-06-09 14:59:31', NULL, NULL, NULL, NULL, NULL, 1, NULL),
	(63, 45, '用户管理', '/e/tenant_eu/rec', '123', 'user', NULL, NULL, NULL, 1, 'admin', '2015-06-09 17:20:57', NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(64, 13, '13要素查看', '/e/stdtmp/view/0-0', NULL, 'read', NULL, NULL, NULL, 1, 'admin', '2015-06-24 13:25:36', NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(65, 14, '评审员管理', '/r/reviewer/', NULL, 'user', NULL, NULL, NULL, NULL, NULL, '2015-06-10 20:22:33', NULL, NULL, NULL, NULL, NULL, 6, NULL),
	(66, 14, '密码修改', '/r/tenant_ru/password', NULL, 'pwd', NULL, NULL, NULL, 1, 'admin', '2015-06-23 16:31:46', NULL, NULL, NULL, NULL, NULL, 9, NULL),
	(67, 48, '收到的公告', '/sys/notice/receives', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2015-07-14 10:34:53', NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(68, 48, '发布公告', '/sys/notice/rec', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2015-07-14 14:31:46', NULL, NULL, NULL, NULL, NULL, 1, NULL),
	(69, 50, '评审信息维护', '/r/tenant_r/rec', '维护评审信息', 'agency', 1, 'admin', '2015-05-15 09:47:25', 1, 'admin', '2015-06-10 18:00:10', 1, 'admin', 'S', 0, NULL, 1, 0),
	(70, 50, '用户管理', '/r/tenant_ru/rec', '用户信息管理', 'agency', NULL, NULL, NULL, NULL, NULL, '2015-06-10 18:00:34', NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(71, 15, '企业监管', '/g/grade/', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2015-06-13 17:58:45', NULL, NULL, NULL, NULL, NULL, 1, NULL),
	(72, 15, '监督统计', '/g/statistics/', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2015-06-13 14:30:03', NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(73, 15, '证书管理', '/static/carts.html', NULL, 'cert', NULL, NULL, NULL, 1, 'admin', '2015-06-12 10:38:33', NULL, NULL, NULL, NULL, NULL, 3, NULL),
	(74, 15, '知识库', '/knowledge/', NULL, 'knowledge', NULL, NULL, NULL, 1, 'admin', '2015-06-13 15:56:04', NULL, NULL, NULL, NULL, NULL, 4, NULL),
	(75, 15, '密码修改', '/g/tenant_gu/password', NULL, 'pwd', NULL, NULL, NULL, 1, 'admin', '2015-06-23 16:32:20', NULL, NULL, NULL, NULL, NULL, 5, NULL),
	(76, 15, '设置', NULL, NULL, 'config', NULL, NULL, NULL, NULL, NULL, '2015-06-12 10:06:23', NULL, NULL, NULL, NULL, NULL, 6, NULL),
	(77, 76, '政府信息维护', '/g/tenant_g/rec', '123', 'agency', NULL, NULL, NULL, 1, 'admin', '2015-06-12 11:28:30', NULL, NULL, NULL, NULL, NULL, 1, NULL),
	(78, 76, '用户管理', '/g/tenant_gu/rec', '123', 'agency', NULL, NULL, NULL, 1, 'admin', '2015-06-12 11:55:06', NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(79, 32, '发布新公告', '/sys/notice/rec', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2015-07-14 14:31:46', NULL, NULL, NULL, NULL, NULL, 1, NULL),
	(80, 13, '自评历史', '/e/gradeplan/history', NULL, 'history', NULL, NULL, NULL, 1, 'admin', '2015-06-29 11:35:24', NULL, NULL, NULL, NULL, NULL, 4, NULL),
	(81, 15, '隐患监管', '/static/danger.html', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-06-24 13:29:57', NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(82, 32, '收到的公告', '/sys/notice/receives', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-07-14 10:34:15', NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(83, 48, '草稿箱', '/sys/notice/drafts/', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2015-07-14 16:30:55', NULL, NULL, NULL, NULL, NULL, 3, NULL),
	(84, 48, '已发布', '/sys/notice/publics/', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-07-14 16:31:34', NULL, NULL, NULL, NULL, NULL, 4, NULL),
	(85, 13, '工作提醒', NULL, NULL, 'notice', NULL, NULL, NULL, NULL, NULL, '2015-07-09 15:04:16', NULL, NULL, NULL, NULL, NULL, 7, NULL),
	(86, 85, '隐患排查', '/e/todo/stdtmp06/', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2015-07-09 15:06:49', NULL, NULL, NULL, NULL, NULL, 1, NULL),
	(87, 85, '特种设备', '/e/todo/stdtmp08/', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2015-07-09 15:06:54', NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(88, 85, '特种人员', '/e/todo/stdtmp07/', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2015-07-09 15:06:58', NULL, NULL, NULL, NULL, NULL, 3, NULL);
/*!40000 ALTER TABLE `sys_nav` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
