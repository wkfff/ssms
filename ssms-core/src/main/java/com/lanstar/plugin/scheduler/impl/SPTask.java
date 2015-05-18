/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SPTask.java
 * 创建时间：2015年5月14日 下午3:37:49
 * 创建用户：林峰
 */
package com.lanstar.plugin.scheduler.impl;

import org.w3c.dom.Element;

import com.lanstar.common.log.Logger;
import com.lanstar.plugin.scheduler.ScheduleTaskAbstr;

/**
 * 执行存储过程
 *
 */
public class SPTask extends ScheduleTaskAbstr<SPTaskParameter> {

    /* (non-Javadoc)
     * @see com.rapidweb.scheduler.job.ScheduleTaskAbstr#doExecute(java.lang.Object)
     */
    @Override
    protected void doExecute(SPTaskParameter params) throws Exception {
//        DB.execStoredProc(params.getSpName(), params.getParames());
        Logger.getLogger( this.getClass()).debug( "执行存储过程");
    }

    /* (non-Javadoc)
     * @see com.rapidweb.scheduler.job.ScheduleTaskAbstr#getParameter(org.w3c.dom.Element)
     */
    @Override
    public SPTaskParameter getParameter(Element node) throws Exception {
        return SPTaskParameter.fromXmlNode(node);
    }

    @Override
    public SPTaskParameter getParameter(String para) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
