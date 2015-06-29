/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：GradeController.java
 * 创建时间：2015年6月18日 下午3:52:56
 * 创建用户：林峰
 */
package com.lanstar.controller.review;

import static com.lanstar.common.EasyUIControllerHelper.PAGE_INDEX;
import static com.lanstar.common.EasyUIControllerHelper.PAGE_SIZE;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.common.kit.StrKit;
import com.lanstar.core.Controller;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.Page;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

/**
 * 评审办理查询企业
 *
 */
public class GradeController extends Controller {
    public void index(){
        
    }
    public void list() {
        SqlBuilder select = SQL.SELECT( " * " );
        SqlBuilder from = new SqlBuilder().FROM( "V_TENANT_E" );
        SqlBuilder where = this.buildWhere();
        if ( where != null ) from.append( where );

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if ( this.isParaExists( PAGE_INDEX ) && this.isParaExists( PAGE_SIZE ) ) {

            Page<Record> paginate = Db.paginate( this.getParaToInt( PAGE_INDEX ), this.getParaToInt( PAGE_SIZE ),
                    selectStatement.getSql(), fromStatement.getSql(), fromStatement.getParams() );
            this.renderJson( EasyUIControllerHelper.toDatagridResult( paginate ) );
        } else {
            List<Record> list = Db.find( selectStatement.getSql() + " " + fromStatement.getSql(),
                    fromStatement.getParams() );
            this.renderJson( list );
        }
    }

    protected SqlBuilder buildWhere() {
        String name = this.getPara( "C_NAME" );
        try {
            if ( !StrKit.isBlank( name ) ) name = URLDecoder.decode( name, "UTF-8" );
        } catch ( UnsupportedEncodingException e ) {

        }
        SqlBuilder builder = new SqlBuilder();
        builder.WHERE()
                ._If( this.isParaExists( "P_PRO" ) && !StrKit.isBlank( this.getPara( "P_PRO" ) ), "P_PROVINCE = ?",
                        this.getPara( "P_PRO" ) )
                ._If( this.isParaExists( "P_CITY" ) && !StrKit.isBlank( this.getPara( "P_CITY" ) ), "P_CITY = ?",
                        this.getPara( "P_CITY" ) )
                ._If( this.isParaExists( "P_COUNTY" ) && !StrKit.isBlank( this.getPara( "P_COUNTY" ) ), "P_COUNTY = ?",
                        this.getPara( "P_COUNTY" ) )
                ._If( this.isParaExists( "N_STATE" ), "N_STATE = ?", this.getPara( "N_STATE" ) )
                ._If( this.isParaBlank( "C_NAME" ) == false, "C_NAME like ?", "%" + name + "%" )
                ._If( this.isParaBlank( "R_TENANT_E" ) == false, "R_TENANT_E = ?", this.getPara( "R_TENANT_E" ) )
                ._If( this.isParaBlank( "T_START" ) == false, "T_START >= ?", this.getPara( "T_START" ) )
                ._If( this.isParaBlank( "T_END" ) == false, "T_END <= ?", this.getPara( "T_END" ) );

        return builder;
    }
}
