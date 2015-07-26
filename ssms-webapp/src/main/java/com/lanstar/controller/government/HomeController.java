/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GovernmentHomeController.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.controller.government;

import com.lanstar.app.Const;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.model.system.Notice;
import com.lanstar.plugin.activerecord.Record;

import java.util.List;

public class HomeController extends Controller {
    public void index(){
        setAttr( Const.HOME_PAGE, "/g/home" );
    }
    
    public void home(){
        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );

        //接收的通知公告
        String sql = "SELECT * FROM V_NOTICE WHERE R_RECEIVER=? AND Z_TYPE=? ORDER BY T_PUBLISH DESC LIMIT 8";
        List<Record> rs_notice = identityContext.getTenantDb().find( sql,identityContext.getTenantId(),identityContext.getTenantType().getName());
        setAttr( "rs_notice", rs_notice );
        //发布的通知公告
        List<Notice> rs_publics = Notice.dao.find("SELECT * FROM SYS_NOTICE WHERE R_TENANT=? AND P_TENANT=? AND T_PUBLISH IS NOT NULL ORDER BY T_PUBLISH DESC LIMIT 8",identityContext.getTenantId(),identityContext.getTenantType().getName());
        setAttr("rs_publics", rs_publics );
    }
}
