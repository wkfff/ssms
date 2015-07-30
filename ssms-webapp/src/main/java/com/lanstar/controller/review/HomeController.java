/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewHomeController.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.controller.review;

import com.lanstar.app.Const;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.model.Done;
import com.lanstar.model.system.Notice;
import com.lanstar.model.Todo;
import com.lanstar.plugin.activerecord.Record;
import java.util.List;

public class HomeController extends Controller {
    public void index(){
        setAttr( Const.HOME_PAGE, "/r/home" );
    }

    public void home(){
        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );
        int tenantId = identityContext.getTenantId();
        int pageSize = 8;
        //接收的通知公告
        String sql = "SELECT * FROM V_NOTICE WHERE R_RECEIVER=? AND Z_TYPE=? ORDER BY T_PUBLISH DESC LIMIT ?";
        List<Record> rs_notice = identityContext.getTenantDb().find( sql,tenantId,identityContext.getTenantType().getName(),pageSize );
       
//        List<Notice> rs_notice = Notice.dao.find("select * from sys_notice where t_publish is not null order by t_publish desc limit 8");
        setAttr("rs_notice", rs_notice );
        
        //发布的通知公告
        List<Notice> rs_notice2 = Notice.dao.find("SELECT * FROM SYS_NOTICE WHERE T_PUBLISH IS NOT NULL AND R_TENANT=? AND P_TENANT=? ORDER BY T_PUBLISH DESC LIMIT 8",tenantId,identityContext.getTenantType().getName());
        setAttr("rs_notice2", rs_notice2 );
        
        List<Todo> rs_todo = Todo.dao.find("SELECT * FROM SYS_TODO WHERE C_CONTROL='REVIEW'");
        setAttr("rs_todo", rs_todo );
        
        List<Done> rs_done = Done.dao.find("SELECT * FROM SYS_DONE WHERE C_CONTROL='REVIEW'");
        setAttr("rs_done", rs_done );
    }
}
