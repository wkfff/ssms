/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Tro.java
 * 创建时间：下午2:28:36
 * 创建用户：苏志亮
 */
package com.lanstar.controller.system;

import java.util.List;

import com.lanstar.common.kit.StrKit;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.model.system.tenant.Government;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

public class TroubleSupervisionController extends Controller {

    public void index() {
    }

    public void list() {
        SqlBuilder sqlBuilder = builder();
        SqlStatement sqlStatement = sqlBuilder.toSqlStatement();
        List<Record> troubleSupervision = Db.find( "SELECT * FROM v_task_trouble_supervision" + " " + sqlStatement.getSql(), sqlStatement.getParams() );
        renderJson( troubleSupervision );
    }

    private SqlBuilder builder() {
        String fromTime = null;
        String toTime = null;
        if ( !StrKit.isBlank( this.getPara( "from" ) ) ) {
           int year = Integer.parseInt(this.getPara( "from" ).substring( 0, 4 ));
           int month = Integer.parseInt(this.getPara( "from" ).substring( 5, 7 ));
           fromTime=String.valueOf( year+month );
        }
        if ( !StrKit.isBlank( this.getPara( "to" ) ) ) {
            int year = Integer.parseInt(this.getPara( "to" ).substring( 0, 4 ));
            int month = Integer.parseInt(this.getPara( "to" ).substring( 5, 7 ));
            toTime=String.valueOf( year+month );
        }
        Government government = (Government) IdentityContextWrap.getIdentityContext( this ).getTenant();
        String atSql = null;
        if ( government.getLevel().equals( "1" ) ) atSql = "P_AT_PROVINCE = (SELECT `P_PROVINCE` FROM `sys_tenant_g` WHERE `SID`=?)";
        if ( government.getLevel().equals( "2" ) ) atSql = "P_AT_CITY = (SELECT `P_CITY` FROM `sys_tenant_g` WHERE `SID`=?)";
        if ( government.getLevel().equals( "3" ) ) atSql = "P_AT_COUNTY = (SELECT `P_COUNTY` FROM `sys_tenant_g` WHERE `SID`=?)";
        SqlBuilder builder = new SqlBuilder();
        builder.WHERE( atSql, government.getId() )
               ._If( this.isParaExists( "city" ) && !StrKit.isBlank( this.getPara( "city" ) ), "P_CITY = ?",
                   this.getPara( "city" ) )
               ._If( this.isParaExists( "country" ) && !StrKit.isBlank( this.getPara( "country" ) ), "P_COUNTY = ?",
                   this.getPara( "country" ) )
               ._If( this.isParaExists( "profession" ) && !StrKit.isBlank( this.getPara( "profession" ) ), "R_INDUSTRY = ?",
                   this.getPara( "profession" ) )
               ._If( this.isParaBlank( "from" ) == false, "YEAR(T_CREATE)+MONTH(T_CREATE) >= ?", fromTime )
               ._If( this.isParaBlank( "to" ) == false, "YEAR(T_CREATE)+MONTH(T_CREATE) <= ?", toTime );
        return builder;
    }
}
