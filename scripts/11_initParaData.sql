/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 初始化系统参数
INSERT INTO `SYS_PARA_MULTI` (`SID`, `C_NAME`, `C_CODE`, `C_VALUE`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TANENT`, `S_TANENT`, `P_TANENT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`)
VALUES
    (1, 'USER_SEX', '1', '男', 0, 'ADMIN', '2015-04-20 11:08:20', 0, 'ADMIN', '2015-04-20 11:08:20', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
    (2, 'USER_SEX', '2', '女', 0, 'ADMIN', '2015-04-20 11:08:20', 0, 'ADMIN', '2015-04-20 11:08:20', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
    (11, 'SYS_PROFESSION', '01', '工贸', 0, 'admin', '2015-04-20 11:41:51', NULL, NULL, '2015-04-20 11:41:51', 0, 'admin', 'S', NULL, NULL, NULL, NULL);

-- 初始化工贸模型
INSERT INTO `SYS_STDTMP_FOLDER` (`SID`, `C_NAME`, `R_SID`, `C_DESC`, `C_LOGO`, `P_PROFESSION`, `S_PROFESSION`, `R_CREATE`, `S_CREATE`, `T_CREATE`, `R_UPDATE`, `S_UPDATE`, `T_UPDATE`, `R_TANENT`, `S_TANENT`, `P_TANENT`, `N_STATE`, `B_DELETE`, `N_INDEX`, `N_VERSION`)
VALUES
    (1, '工贸达标体系模板', 0, 'adfdfe', NULL, '01', '工贸', 0, 'admin', '2015-04-20 11:41:52', 0, 'admin', '2015-04-21 22:35:54', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
    (5, '', 0, '', NULL, NULL, NULL, 0, 'admin', '2015-04-21 15:22:27', NULL, NULL, '2015-04-21 15:22:27', 0, 'admin', 'S', NULL, NULL, NULL, NULL),
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
    (22, '文件和档案管理', 11, '', NULL, '01', '工贸', 0, 'admin', '2015-04-21 22:53:23', NULL, NULL, '2015-04-21 22:53:23', 0, 'admin', 'S', NULL, NULL, NULL, NULL);


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;