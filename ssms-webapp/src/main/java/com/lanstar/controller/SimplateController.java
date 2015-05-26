/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SimplateController.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.common.ModelInjector;
import com.lanstar.common.kit.StrKit;
import com.lanstar.core.Controller;
import com.lanstar.core.render.JsonRender;
import com.lanstar.identity.IdentityContext;
import com.lanstar.plugin.activerecord.*;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

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
        if ( identityContext == null ) return;
        tenantDb = identityContext.getTenantDb();
    }

    public void index() {

    }

    public void rec() {
        String sid = getPara( "sid" );
        if ( StrKit.isEmpty( sid ) ) sid = getPara( "SID" );
        if ( sid == null ) return;

        T model = getDao().findById( sid );
        if ( model != null ) setAttrs( ModelKit.toMap( model ) );
    }

    public void save() {
        T model = getModel();

        Integer sid = model.getInt( "SID" );

        boolean[] handled = new boolean[1];
        beforeSave( model, handled );
        if ( handled[0] == false ) {
            if ( sid == null ) model.save();
            else model.update();
        }

        afterSave( model );
        setAttr( "SID", model.getInt( "SID" ) );
        renderJson();
    }

    public void batchSave() {
        JSONArray data = JSON.parseArray( getPara( "data" ) );
        for ( int i = 0; i < data.size(); i++ ) {
            JSONObject item = data.getJSONObject( i );

            String sid = item.getString( "SID" );
            T model;
            if ( sid.startsWith( "_" ) ) {
                item.remove( "SID" );
                model = newModel();
            } else {
                model = getDao().findById( sid );
            }
            ModelInjector.injectActiveRecordModel( model, item );

            boolean[] handled = new boolean[1];
            beforeSave( model, handled );
            if ( handled[0] == false ) {
                if ( sid.startsWith( "_" ) ) model.save();
                else model.update();
            }
            afterSave( model );
        }
        renderJson();
    }

    public void del() {
        T model = getModel();
        boolean[] handled = new boolean[1];
        beforeDel( model, handled );
        boolean success = model.delete();
        afterDel( model );
        render( new JsonRender( success ).forIE() );
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

    protected void beforeSave( T model, boolean[] handled ) {
    }

    protected void afterSave( T model ) {

    }

    protected void beforeDel( T model, boolean[] handled ) {

    }

    protected void afterDel( T model ) {

    }

    protected T getModel() {
        Enumeration<String> paraNames = this.getParaNames();
        Map<String, String> paraMap = new CaseInsensitiveContainerFactory.CaseInsensitiveMap();
        while ( paraNames.hasMoreElements() ) {
            String paraName = paraNames.nextElement();
            if ( isParaBlank( paraName ) ) continue;
            String para = getPara( paraName );
            if ( "undefined".equalsIgnoreCase( para ) ) continue;
            if ( "null".equalsIgnoreCase( para ) ) continue;
            paraMap.put( paraName, para );
        }

        T model;
        String tmp = paraMap.get( "SID" );
        if ( StrKit.isEmpty( tmp ) == false ) model = getDao().findById( tmp );
        else model = newModel();

        ModelInjector.injectActiveRecordModel( model, paraMap );
        ModelInjector.injectOpreator( model, identityContext );
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
