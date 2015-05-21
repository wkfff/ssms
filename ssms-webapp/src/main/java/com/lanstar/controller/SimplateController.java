/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SimplateController.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller;

import com.lanstar.app.Const;
import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.common.ModelInjector;
import com.lanstar.core.Controller;
import com.lanstar.core.aop.Before;
import com.lanstar.core.render.JsonRender;
import com.lanstar.identity.IdentityContext;
import com.lanstar.plugin.activerecord.*;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;
import com.lanstar.plugin.activerecord.tx.Tx;
import com.lanstar.plugin.activerecord.tx.TxConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.lanstar.common.EasyUIControllerHelper.PAGE_INDEX;
import static com.lanstar.common.EasyUIControllerHelper.PAGE_SIZE;

public abstract class SimplateController<T extends Model<T>> extends Controller {
    protected final Table table = TableMapping.me().getTable( getDao().getClass() );
    protected IdentityContext identityContext;
    protected DbPro tenantDb;

    @Override
    public void init( HttpServletRequest request, HttpServletResponse response, String urlPara ) {
        super.init( request, response, urlPara );
        identityContext = IdentityContext.getIdentityContext( this );
        tenantDb = identityContext.getTenantDb();
    }

    public void index() {

    }

    public void rec() {
        String sid = getPara( "sid" );

        T model = getDao().findById( sid );
        setAttrs( ModelKit.toMap( model ) );
    }

    @Before(Tx.class)
    @TxConfig(Const.TENANT_DB_NAME)
    public void save() {
        T model = getModel();

        Integer sid = model.getInt( "SID" );

        beforeSave( model );
        if ( sid == null ) model.save();
        else model.update();

        afterSave( model );
        setAttr( "SID", model.getInt( "SID" ) );
        renderJson();
    }

    public void del() {
        T model = getModel();
        render( new JsonRender( model.delete() ).forIE() );
    }

    public void list() {
        SqlBuilder select = SQL.SELECT( "*" );
        SqlBuilder from = new SqlBuilder().FROM( table.getName() );
        SqlBuilder where = buildWhere();
        if ( where != null ) from.append( where );

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if ( this.isParaExists( PAGE_INDEX ) && this.isParaExists( PAGE_SIZE ) ) {
            Page<T> paginate = getDao().paginate( getParaToInt( PAGE_INDEX ), getParaToInt( PAGE_SIZE ),
                    selectStatement.getSql(),
                    fromStatement.getSql(),
                    fromStatement.getParams() );
            renderJson( EasyUIControllerHelper.toDatagridResult( paginate ) );
        } else {
            List<T> list = getDao().find(
                    selectStatement.getSql() + " " + fromStatement.getSql(), fromStatement.getParams() );
            renderJson( list );
        }
    }

    protected SqlBuilder buildWhere() {
        return null;
    }

    protected void beforeSave( T model ) {

    }

    protected void afterSave( T model ) {

    }

    protected T getModel() {
        Integer sid = getParaToInt( "SID" );
        T model;
        if ( sid != null ) model = getDao().findById( sid );
        else model = newModel();
        ModelInjector.injectActiveRecordModel( model, getRequest(), false );
        return model;
    }

    protected abstract T getDao();

    @SuppressWarnings("unchecked")
    protected T newModel() {
        try {
            return (T) table.getModelClass().newInstance();
        } catch ( InstantiationException | IllegalAccessException e ) {
            throw new RuntimeException( e );
        }
    }
}
