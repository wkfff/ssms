/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NoticeController.java
 * 创建时间：2015年6月5日 下午4:27:23
 * 创建用户：林峰
 */
package com.lanstar.controller.system;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.Notice;
import com.lanstar.model.system.NoticeReceiver;
import com.lanstar.model.system.tenant.Government;
import com.lanstar.model.system.tenant.Review;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.Page;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

import java.util.*;

import static com.lanstar.common.EasyUIControllerHelper.PAGE_INDEX;
import static com.lanstar.common.EasyUIControllerHelper.PAGE_SIZE;

/**
 * 通知公告
 *
 */
public class NoticeController extends SimplateController<Notice> {
    @Override
    protected Notice getDao() {
        return Notice.dao;
    }
    
    public Map<String,List<Record>> getAllReceiver(){
        String sql = "";
        String where = "";
        String order = " ORDER BY N_INDEX";
        List<Record> list = null;
        Map<String,List<Record>> map = new HashMap<String,List<Record>>();
        
        //政府端需要列出所有类型的接收者，并且根据行政等级过滤
        if ( this.identityContext.getTenantType().compareTo( TenantType.GOVERNMENT ) == 0 ) {
            Government gov = Government.dao.findById( this.identityContext.getTenantId() );

            String code;
            String level = StrKit.empty( gov.getStr( "P_LEVEL" ), "" );
            if ( level.equals( "1" ) ) {
                code = gov.getStr( "P_PROVINCE" );
                where = " where P_PROVINCE ='" + code + "'";
            } else if ( level.equals( "2" ) ) {
                code = gov.getStr( "P_CITY" );
                where = " where P_CITY ='" + code + "'";
            } else {
                code = gov.getStr( "P_COUNTY" );
                where = " where P_COUNTY ='" + code + "'";
            }

            // 政府
            sql = "select SID R_RECEIVER,C_NAME S_RECEIVER,C_ADDR from sys_tenant_g " + where + order;
            list = this.tenantDb.find( sql );
            map.put( "G", list );

            // 评审
            sql = "select SID R_RECEIVER,C_NAME S_RECEIVER,C_ADDR  from sys_tenant_r" + where + order;
            list = this.tenantDb.find( sql );
            map.put( "R", list );
        }
        //评审端只要列出辖区内企业
        if ( this.identityContext.getTenantType().compareTo( TenantType.REVIEW ) == 0 ) {
            Review review = Review.dao.findById( this.identityContext.getTenantId() );
            where = " where P_PROVINCE ='" + review.getStr( "P_PROVINCE" ) + "' and P_CITY='"+review.getStr( "P_CITY" )+"' and P_COUNTY='"+review.getStr( "P_COUNTY" )+"'";
        }
        // 企业
        sql = "select SID R_RECEIVER,C_NAME S_RECEIVER,C_ADDR  from sys_tenant_e" + where + order;
        list = this.tenantDb.find( sql );
        map.put( "E", list );
        return map;
    }
    
    @Override
    public void rec() {
        // 是否显示标题栏
        String tb = this.getPara( "tb" );
        this.setAttr( "tb", StrKit.isBlank( tb ) ? "1" : tb );
        List<Record> list = null;
        List<Map<String, Object>> models = null;

        Map<String,List<Record>> map = getAllReceiver();
        list = map.get( "G" );
        if (list!=null && !list.isEmpty()){
            models = Lists.transform( list, new Function<Record, Map<String, Object>>() {
                @Override
                public Map<String, Object> apply( Record input ) {
                    Map<String, Object> map = input.getColumns();
                    map.put( "CHOOSE", false );
                    return map;
                }
            } );
            this.setAttr( "data_g", JSON.toJSONString( Lists.newArrayList( models ) ) );
        }
        
        list = map.get( "R" );
        if (list!=null && !list.isEmpty()){
            models = Lists.transform( list, new Function<Record, Map<String, Object>>() {
                @Override
                public Map<String, Object> apply( Record input ) {
                    Map<String, Object> map = input.getColumns();
                    map.put( "CHOOSE", false );
                    return map;
                }
            } );
            this.setAttr( "data_r", JSON.toJSONString( Lists.newArrayList( models ) ) );
        }
        list = map.get( "E" );
        if (list!=null && !list.isEmpty()){
            models = Lists.transform( list, new Function<Record, Map<String, Object>>() {
                @Override
                public Map<String, Object> apply( Record input ) {
                    Map<String, Object> map = input.getColumns();
                    map.put( "CHOOSE", false );
                    return map;
                }
            } );
            this.setAttr( "data_e", JSON.toJSONString( Lists.newArrayList( models ) ) );
        }
        
        //取当前通知公告的所有接收者
        Integer sid = this.getModel().getId();
        if ( sid != null ) {
            list = this.tenantDb.find( "select R_NOTICE,R_RECEIVER,S_RECEIVER,Z_TYPE from sys_notice_receiver where r_notice=?",
                    sid.intValue() );

            Predicate<Record> predicate_g = new Predicate<Record>() {
                @Override
                public boolean apply( Record input ) {
                    String type = input.getStr( "Z_TYPE" );
                    return !StrKit.isBlank( type ) && type.equals( "G" );
                }
            };

            Predicate<Record> predicate_r = new Predicate<Record>() {
                @Override
                public boolean apply( Record input ) {
                    String type = input.getStr( "Z_TYPE" );
                    return !StrKit.isBlank( type ) && type.equals( "R" );
                }
            };

            Predicate<Record> predicate_e = new Predicate<Record>() {
                @Override
                public boolean apply( Record input ) {
                    String type = input.getStr( "Z_TYPE" );
                    return !StrKit.isBlank( type ) && type.equals( "E" );
                }
            };

            Function<Record, Map<String, Object>> func = new Function<Record, Map<String, Object>>() {
                @Override
                public Map<String, Object> apply( Record input ) {
                    Map<String, Object> map = input.getColumns();
                    map.put( "CHOOSE", true );
                    return map;
                }
            };
            List<Map<String, Object>> models_g = FluentIterable.from( list ).filter( predicate_g ).transform( func ).toList();
            this.setAttr( "governments", JSON.toJSONString( Lists.newArrayList( models_g ) ) );

            List<Map<String, Object>> models_r = FluentIterable.from( list ).filter( predicate_r ).transform( func ).toList();
            this.setAttr( "reviews", JSON.toJSONString( Lists.newArrayList( models_r ) ) );

            List<Map<String, Object>> models_e = FluentIterable.from( list ).filter( predicate_e ).transform( func ).toList();
            this.setAttr( "enterprises", JSON.toJSONString( Lists.newArrayList( models_e ) ) );
        }
//        super.rec();

        Notice model = getModel();
        if ( model != null ) {
            model.setContent( model.getContent() );
            setAttrs( ModelKit.toMap( model ) );
        }
    }

    /**
     * 发布操作
     */
    public void publish() {
        String sid = this.getPara( "sid" );
        if ( StrKit.isEmpty( sid ) ) sid = this.getPara( "SID" );
        if ( sid == null ) return;
        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );
        Notice model = this.getModel();
        model.set( "R_PUBLISH", identityContext.getId() );
        model.set( "S_PUBLISH", identityContext.getTenantName() );
        model.set( "T_PUBLISH", new Date() );
        model.update();
        this.setAttr( "SID", model.getInt( "SID" ) );
        this.renderJson();
    }

    @Override
    protected void afterSave( final Notice model ) {
        // 保存后处理接收者
        String receiver_g = this.getPara( "C_RECEIVER_G" );
        String receiver_r = this.getPara( "C_RECEIVER_R" );
        String receiver_e = this.getPara( "C_RECEIVER_E" );
        
        List<Record> tenants = null;
        Map<String,List<Record>> map = getAllReceiver();
        List<NoticeReceiver> receivers = new ArrayList<NoticeReceiver>();
        if ( !StrKit.isBlank( receiver_g ) ) {
            if (receiver_g.equals( "全部" )){
                tenants = map.get( "G" );
                if(!tenants.isEmpty()){
                    List<NoticeReceiver> list = Lists.transform( tenants, new Function<Record, NoticeReceiver>() {
                        @Override
                        public NoticeReceiver apply( Record input ) {
                            NoticeReceiver receiver = new NoticeReceiver();
                            receiver.setR_RECEIVER( input.getInt( "R_RECEIVER" ) );
                            receiver.setS_RECEIVER( input.getStr( "S_RECEIVER" ) );
                            receiver.set( "R_NOTICE", model.getInt( "SID" ) );
                            receiver.set( "Z_TYPE", "G" );
                            return receiver;
                        }
                    } );
                    receivers.addAll( list );
                }
            }
            else{
                List<NoticeReceiver> list = JSON.parseArray( receiver_g, NoticeReceiver.class );
                if ( !list.isEmpty() ) {
                    for ( NoticeReceiver rec : list ) {
                        rec.set( "R_NOTICE", model.getInt( "SID" ) );
                        rec.set( "Z_TYPE", "G" );
                    }
                    receivers.addAll( list );
                }
            }
        }

        if ( !StrKit.isBlank( receiver_r ) ) {
            if (receiver_r.equals( "全部" )){
                tenants = map.get( "R" );
                if (!tenants.isEmpty()){
                    List<NoticeReceiver> list = Lists.transform( tenants, new Function<Record, NoticeReceiver>() {
                        @Override
                        public NoticeReceiver apply( Record input ) {
                            NoticeReceiver receiver = new NoticeReceiver();
                            receiver.setR_RECEIVER( input.getInt( "R_RECEIVER" ) );
                            receiver.setS_RECEIVER( input.getStr( "S_RECEIVER" ) );
                            receiver.set( "R_NOTICE", model.getInt( "SID" ) );
                            receiver.set( "Z_TYPE", "R" );
                            return receiver;
                        }
                    } );
                    receivers.addAll( list );
                }
            }
            else{
                List<NoticeReceiver> list = JSON.parseArray( receiver_r, NoticeReceiver.class );
                if ( !list.isEmpty() ) {
                    for ( NoticeReceiver rec : list ) {
                        rec.set( "R_NOTICE", model.getInt( "SID" ) );
                        rec.set( "Z_TYPE", "R" );
                    }
                    receivers.addAll( list );
                }
            }
        }

        if ( !StrKit.isBlank( receiver_e ) ) {
            if (receiver_e.equals( "全部" )){
                tenants = map.get( "E" );
                if (!tenants.isEmpty()){
                    List<NoticeReceiver> list = Lists.transform( tenants, new Function<Record, NoticeReceiver>() {
                        @Override
                        public NoticeReceiver apply( Record input ) {
                            NoticeReceiver receiver = new NoticeReceiver();
                            receiver.setR_RECEIVER( input.getInt( "R_RECEIVER" ) );
                            receiver.setS_RECEIVER( input.getStr( "S_RECEIVER" ) );
                            receiver.set( "R_NOTICE", model.getInt( "SID" ) );
                            receiver.set( "Z_TYPE", "E" );
                            return receiver;
                        }
                    } );
                    receivers.addAll( list );
                }
            }
            else{
                List<NoticeReceiver> list = JSON.parseArray( receiver_e, NoticeReceiver.class );
                if ( !list.isEmpty() ) {
                    for ( NoticeReceiver rec : list ) {
                        rec.set( "R_NOTICE", model.getInt( "SID" ) );
                        rec.set( "Z_TYPE", "E" );
                    }
                    receivers.addAll( list );
                }
            }
        }
        if ( !receivers.isEmpty() ) {
            NoticeReceiver.dao.deleteById( model.getInt( "SID" ), "R_NOTICE" );
            ModelKit.batchSave( this.identityContext.getTenantDb(), receivers );
        }
    }

    @Override
    protected void afterDel( Notice model ) {
        // 删除后同时清除接收者
        this.tenantDb.deleteById( "SYS_NOTICE_RECEIVER", "R_NOTICE", model.getId() );
    }

    /**
     * 查看
     */
    public void view() {
        String sid = this.getPara( "sid" );
        if ( StrKit.isEmpty( sid ) ) sid = this.getPara( "SID" );
        if ( sid == null ) return;

        Notice model = this.getDao().findById( sid );
        NoticeReceiver receiver = NoticeReceiver.dao.findFirst(
                "select * from sys_notice_receiver where r_notice = ? and r_receiver=? and n_state=0", sid,
                this.identityContext.getTenantId() );
        if ( receiver != null ) {
            receiver.set( "N_STATE", 1 );
            receiver.update();

            Record r = this.tenantDb.findFirst( "select count(*) N_READER from sys_notice_receiver where r_notice=? and n_state=1", sid );
            if ( r != null ) {
                model.set( "N_READER", r.getLong( "N_READER" ) );
                model.update();
            }
        }
        
        StringBuffer sb = new StringBuffer();
        List<NoticeReceiver> receivers = NoticeReceiver.dao.find( "select * from sys_notice_receiver where r_notice=?" ,sid);
        for(NoticeReceiver r:receivers){
            sb.append( r.getStr( "S_RECEIVER" ) ).append( " " );
        }
        this.setAttr( "RECEIVERS", sb.toString() );

        if ( model != null ) this.setAttrs( ModelKit.toMap( model ) );
    }

    /**
     * 已发布列表
     */
    public void publics() {

    }

    /**
     * 已发布列表数据
     */
    public void publics_list() {
        SqlBuilder select = SQL.SELECT( "*" );
        SqlBuilder from = new SqlBuilder().FROM( this.table.getName() );
        SqlBuilder where = new SqlBuilder();

        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );
        where.WHERE( "R_TENANT=?", identityContext.getTenantId() );
        where.WHERE( "P_TENANT=?", identityContext.getTenantType().getName() );
        where.WHERE( "R_PUBLISH is not null" );

        if ( where != null ) from.append( where );
        SqlBuilder order = this.buildOrder();
        if ( order != null ) from.append( order );

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if ( this.isParaExists( PAGE_INDEX ) && this.isParaExists( PAGE_SIZE ) ) {
            Page<Notice> paginate = this.getDao().paginate( this.getParaToInt( PAGE_INDEX ), this.getParaToInt( PAGE_SIZE ),
                    selectStatement.getSql(), fromStatement.getSql(), fromStatement.getParams() );
            this.renderJson( EasyUIControllerHelper.toDatagridResult( paginate ) );
        } else {
            List<Notice> list = this.getDao().find( selectStatement.getSql() + " " + fromStatement.getSql(), fromStatement.getParams() );
            this.renderJson( list );
        }
    }

    /**
     * 草稿列表
     */
    public void drafts() {
    }

    /**
     * 草稿列表数据
     */
    public void drafts_list() {
        SqlBuilder select = SQL.SELECT( "*" );
        SqlBuilder from = new SqlBuilder().FROM( this.table.getName() );
        SqlBuilder where = new SqlBuilder();

        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );
        // R_TENANT
        where.WHERE( "R_TENANT=?", identityContext.getTenantId() );
        where.WHERE( "P_TENANT=?", identityContext.getTenantType().getName() );
        where.WHERE( "R_PUBLISH is null" );

        if ( where != null ) from.append( where );
        SqlBuilder order = this.buildOrder();
        if ( order != null ) from.append( order );

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if ( this.isParaExists( PAGE_INDEX ) && this.isParaExists( PAGE_SIZE ) ) {
            Page<Notice> paginate = this.getDao().paginate( this.getParaToInt( PAGE_INDEX ), this.getParaToInt( PAGE_SIZE ),
                    selectStatement.getSql(), fromStatement.getSql(), fromStatement.getParams() );
            this.renderJson( EasyUIControllerHelper.toDatagridResult( paginate ) );
        } else {
            List<Notice> list = this.getDao().find( selectStatement.getSql() + " " + fromStatement.getSql(), fromStatement.getParams() );
            this.renderJson( list );
        }
    }
    
    
    /**
     * 收到的通知公告列表
     */
    public void receives() {

    }

    /**
     * 收到的通知公告列表
     */
    public void receives_list() {
        SqlBuilder select = SQL.SELECT( "*" );
        SqlBuilder from = new SqlBuilder().FROM( "V_NOTICE SYS_NOTICE" );
        SqlBuilder where = new SqlBuilder();

        IdentityContext identityContext = IdentityContextWrap.getIdentityContext( this );
        where.WHERE( "R_RECEIVER=?", identityContext.getTenantId() );
        where.WHERE( "Z_TYPE=?", identityContext.getTenantType().getName() );

        if ( where != null ) from.append( where );
        SqlBuilder order = this.buildOrder();
        if ( order != null ) from.append( order );

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if ( this.isParaExists( PAGE_INDEX ) && this.isParaExists( PAGE_SIZE ) ) {
            Page<Notice> paginate = this.getDao().paginate( this.getParaToInt( PAGE_INDEX ), this.getParaToInt( PAGE_SIZE ),
                    selectStatement.getSql(), fromStatement.getSql(), fromStatement.getParams() );
            this.renderJson( EasyUIControllerHelper.toDatagridResult( paginate ) );
        } else {
            List<Notice> list = this.getDao().find( selectStatement.getSql() + " " + fromStatement.getSql(), fromStatement.getParams() );
            this.renderJson( list );
        }
    }

    @Override
    protected SqlBuilder buildOrder() {
        SqlBuilder order = new SqlBuilder();
        order.ORDER_BY( "T_PUBLISH DESC,T_UPDATE DESC" );
        return order;
    }
    
    
}