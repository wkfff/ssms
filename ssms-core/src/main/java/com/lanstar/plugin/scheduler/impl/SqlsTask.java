/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SqlsTask.java
 * 创建时间：2015年5月14日 上午9:39:43
 * 创建用户：林峰
 */
package com.lanstar.plugin.scheduler.impl;

import org.w3c.dom.Element;

import com.lanstar.plugin.scheduler.ScheduleTaskAbstr;
import com.lanstar.plugin.template.sql.TemplateContext;

/**
 * 自动执行SQL任务
 *
 */
public class SqlsTask  extends ScheduleTaskAbstr<SqlsTaskParameter> {

    /* (non-Javadoc)
     * @see com.lanstar.plugin.scheduler.ScheduleTaskAbstr#doExecute(java.lang.Object)
     */
    @Override
    protected void doExecute( SqlsTaskParameter params ) throws Exception {
        TemplateContext ctx = new TemplateContext();
        for( SqlsTaskParameter.ISqlsTask param: params.params ){
            try{
                ctx.setVariable("_task", params.id);
                param.execute(ctx);
            }catch(Exception e){
                throw e;
            }
        }
        
    }

    /* (non-Javadoc)
     * @see com.lanstar.plugin.scheduler.ScheduleTaskAbstr#getParameter(org.w3c.dom.Element)
     */
    @Override
    public SqlsTaskParameter getParameter( Element node ) throws Exception {
        return new SqlsTaskParameter(node);
    }

    /* (non-Javadoc)
     * @see com.lanstar.plugin.scheduler.ScheduleTaskAbstr#getParameter(java.lang.String)
     */
    @Override
    public SqlsTaskParameter getParameter( String para ) throws Exception {
        return null;
    }

}
