-- 所有用户添加"电子邮箱"字段
ALTER TABLE `sys_tenant_g_user`
    ADD COLUMN `C_EMAIL` VARCHAR(50) NULL DEFAULT NULL COMMENT '电子邮箱' AFTER `C_PASSWD`;
ALTER TABLE `sys_tenant_r_user`
    ADD COLUMN `C_EMAIL` VARCHAR(50) NULL DEFAULT NULL COMMENT '电子邮箱' AFTER `C_PASSWD`;

-- 创建密码重置历史表
DROP TABLE IF EXISTS SYS_HIS_PWDRESET;
CREATE TABLE `SYS_HIS_PWDRESET` (
    `SID`       INT(11)         NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `R_TENANT`  INT(11)         NOT NULL                COMMENT '租户编号',
    `S_TENANT`  VARCHAR(300)    NOT NULL                COMMENT '租户名称',
    `P_TENANT`  CHAR(1)         NOT NULL                COMMENT '租户类型',
    `R_USER`    INT(32)         NOT NULL                COMMENT '用户SID',
    `S_USER`    VARCHAR(100)    NOT NULL                COMMENT '用户名',
    `C_EMAIL`   VARCHAR(50)     NOT NULL                COMMENT '电子邮箱',
    `C_TOKEN`   VARCHAR(32)     NOT NULL                COMMENT '重置令牌',
    `T_APPLY`   DATETIME        NOT NULL                COMMENT '申请时间',
    `R_CREATE`  INT(11)         NOT NULL                COMMENT '创建人编号',
    `S_CREATE`  VARCHAR(50)     NOT NULL                COMMENT '创建人',
    `T_CREATE`  DATETIME        NOT NULL                COMMENT '创建时间',
    `R_UPDATE`  INT(11)         NOT NULL                COMMENT '更新人编号',
    `S_UPDATE`  VARCHAR(50)     NOT NULL                COMMENT '更新人',
    `T_UPDATE`  TIMESTAMP       NOT NULL                COMMENT '更新时间',
    `N_STATE`   INT(11)         COMMENT '状态',
    `B_DELETE`  CHAR(1)         COMMENT '删除标志',
    `N_INDEX`   INT(11)         COMMENT '排序',
    `N_VERSION` INT(11)         COMMENT '版本',
    PRIMARY KEY (`SID`),
    UNIQUE INDEX (`C_TOKEN`)
);
