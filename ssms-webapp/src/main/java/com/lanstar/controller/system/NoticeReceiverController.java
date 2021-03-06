/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NoticeReceiverController.java
 * 创建时间：2015年7月2日 下午6:26:52
 * 创建用户：林峰
 */
package com.lanstar.controller.system;

import com.lanstar.controller.SimplateController;
import com.lanstar.model.system.NoticeReceiver;

/**
 * 通知公告接收者
 *
 */
public class NoticeReceiverController extends SimplateController<NoticeReceiver> {
    @Override
    protected NoticeReceiver getDao() {
        return NoticeReceiver.dao;
    }

}
