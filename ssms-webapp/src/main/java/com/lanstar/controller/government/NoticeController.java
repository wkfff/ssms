/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NoticeController.java
 * 创建时间：2015年6月5日 下午4:27:23
 * 创建用户：林峰
 */
package com.lanstar.controller.government;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.lanstar.common.EasyUIControllerHelper;
import com.lanstar.common.ModelInjector;
import com.lanstar.common.kit.StrKit;
import com.lanstar.controller.SimplateController;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.AttachFile;
import com.lanstar.model.system.AttachText;
import com.lanstar.model.system.Enterprise;
import com.lanstar.model.system.Government;
import com.lanstar.model.system.Notice;
import com.lanstar.model.system.NoticeReceiver;
import com.lanstar.model.system.Review;
import com.lanstar.model.system.TemplateGrade;
import com.lanstar.model.tenant.GradeContent;
import com.lanstar.model.tenant.ReviewMember;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.activerecord.Page;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.activerecord.statement.SQL;
import com.lanstar.plugin.activerecord.statement.SqlBuilder;
import com.lanstar.plugin.activerecord.statement.SqlStatement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public void rec() {
        //TODO:根据辖区过滤
        List<Record> list = tenantDb.find( "select SID R_RECEIVER,C_NAME S_RECEIVER,C_ADDR from sys_tenant_g" );
        List<Map<String,Object>> models = Lists.transform( list, new Function<Record, Map<String,Object>>(){
          @Override
          public Map<String,Object> apply( Record input ) {
              Map<String,Object> map = input.getColumns();
              map.put( "CHOOSE", false );
              return map;
          }
        });
        this.setAttr( "data_g",JSON.toJSONString( Lists.newArrayList(models)));
        
        list = tenantDb.find( "select SID R_RECEIVER,C_NAME S_RECEIVER,C_ADDR  from sys_tenant_r" );
        models = Lists.transform( list, new Function<Record, Map<String,Object>>(){
          @Override
          public Map<String,Object> apply( Record input ) {
              Map<String,Object> map = input.getColumns();
              map.put( "CHOOSE", false );
              return map;
          }
        });
        this.setAttr( "data_r",JSON.toJSONString( Lists.newArrayList(models)));
        
        list = tenantDb.find( "select SID R_RECEIVER,C_NAME S_RECEIVER,C_ADDR  from sys_tenant_e" );
        models = Lists.transform( list, new Function<Record, Map<String,Object>>(){
          @Override
          public Map<String,Object> apply( Record input ) {
              Map<String,Object> map = input.getColumns();
              map.put( "CHOOSE", false );
              return map;
          }
        });
        this.setAttr( "data_e",JSON.toJSONString( Lists.newArrayList(models)));
        
        
        Integer sid = this.getModel().getId();
        if (sid!=null){
            list = tenantDb.find( "select R_NOTICE,R_RECEIVER,S_RECEIVER,Z_TYPE from sys_notice_receiver where r_notice=?", sid.intValue() );
            
            Predicate<Record> predicate_g = new Predicate<Record>() {  
                @Override  
                public boolean apply(Record input) { 
                    String type = input.getStr( "Z_TYPE" );
                    return !StrKit.isBlank( type ) && type.equals( "G" );  
                }  
            };
            
            Predicate<Record> predicate_r = new Predicate<Record>() {  
                @Override  
                public boolean apply(Record input) { 
                    String type = input.getStr( "Z_TYPE" );
                    return !StrKit.isBlank( type ) && type.equals( "R" );  
                }  
            };
            
            Predicate<Record> predicate_e = new Predicate<Record>() {  
                @Override  
                public boolean apply(Record input) { 
                    String type = input.getStr( "Z_TYPE" );
                    return !StrKit.isBlank( type ) && type.equals( "E" );  
                }  
            };
            
            Function<Record, Map<String,Object>> func = new Function<Record,Map<String,Object>>(){  
                @Override  
                public Map<String,Object> apply(Record input) {
                    Map<String,Object> map = input.getColumns();
                    map.put( "CHOOSE", true );
                    return map;
                }
            };
            List<Map<String,Object>> models_g = FluentIterable.from( list ).filter( predicate_g ).transform( func ).toList();           
            this.setAttr( "governments",JSON.toJSONString( Lists.newArrayList(models_g)));
            
            List<Map<String,Object>> models_r = FluentIterable.from( list ).filter( predicate_r ).transform( func ).toList();
            this.setAttr( "reviews",JSON.toJSONString( Lists.newArrayList(models_r)));
            
            List<Map<String,Object>> models_e = FluentIterable.from( list ).filter( predicate_e ).transform( func ).toList();
            this.setAttr( "enterprises",JSON.toJSONString( Lists.newArrayList(models_e)));
        }
        super.rec();
    }

    /**
     * 发布
     */
    public void publish() {
        String sid = getPara("sid");
        if (StrKit.isEmpty(sid))
            sid = getPara("SID");
        if (sid == null)
            return;
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        Notice model = this.getModel();
        model.set( "R_PUBLISH", identityContext.getId() );
        model.set( "S_PUBLISH", identityContext.getTenantName() );
        model.set( "T_PUBLISH",  new Date());
        model.update();
        this.setAttr( "SID", model.getInt( "SID" ) );
    }

    @Override
    protected void afterSave( Notice model ) {
        String receiver_g = this.getPara("C_RECEIVER_G");
        String receiver_r = this.getPara("C_RECEIVER_R");
        String receiver_e = this.getPara("C_RECEIVER_E");
        
        List<NoticeReceiver> receivers = new ArrayList<NoticeReceiver>();
        if ( !StrKit.isBlank( receiver_g ) ) {
            List<NoticeReceiver> list = JSON.parseArray( receiver_g, NoticeReceiver.class );
            if (!list.isEmpty()){
                for ( NoticeReceiver rec : list ) {
                    rec.set( "R_NOTICE", model.getInt( "SID" ) );
                    rec.set( "Z_TYPE", "G" );
                }
                receivers.addAll( list );
            }
        }
        
        if ( !StrKit.isBlank( receiver_r ) ) {
            List<NoticeReceiver> list = JSON.parseArray( receiver_r, NoticeReceiver.class );
            if (!list.isEmpty()){
                for ( NoticeReceiver rec : list ) {
                    rec.set( "R_NOTICE", model.getInt( "SID" ) );
                    rec.set( "Z_TYPE", "R" );
                }
                receivers.addAll( list );
            }
        }
        
        if ( !StrKit.isBlank( receiver_e ) ) {
            List<NoticeReceiver> list = JSON.parseArray( receiver_e, NoticeReceiver.class );
            if (!list.isEmpty()){
                for ( NoticeReceiver rec : list ) {
                    rec.set( "R_NOTICE", model.getInt( "SID" ) );
                    rec.set( "Z_TYPE", "E" );
                }
                receivers.addAll( list );
            }
        }
        if (!receivers.isEmpty()){
            NoticeReceiver.dao.deleteById( model.getInt( "SID" ), "R_NOTICE");
            ModelKit.batchSave( this.identityContext.getTenantDb(), receivers );
        }
    }

    @Override
    protected void afterDel( Notice model ) {
        // TODO Auto-generated method stub
        super.afterDel( model );
    }

    /**
     * 查看
     */
    public void view() {
        String sid = getPara("sid");
        if (StrKit.isEmpty(sid))
            sid = getPara("SID");
        if (sid == null)
            return;

        Notice model = getDao().findById(sid);
        if (model != null)
            setAttrs(ModelKit.toMap(model));

//        String content = this.identityContext.getAttachTextService().getContent( "SYS_NOTICE", "C_CONTENT", Integer.parseInt( sid ) );
//        setAttr("C_CONTENT",content);

//        String fileSql = "SELECT * FROM sys_attach_file WHERE r_table = 'ssm_notice' AND r_sid = ?";
//        setAttr("noticeFile", AttachFile.dao.find(fileSql,sid));
    }

    public void publics() {

    }

    /**
     * 已发布
     */
    public void publics_list() {
        SqlBuilder select = SQL.SELECT("*");
        SqlBuilder from = new SqlBuilder().FROM(table.getName());
        SqlBuilder where = new SqlBuilder();


        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
//        R_TENANT
        where.WHERE("R_TENANT=?",identityContext.getTenantId());
        where.WHERE("R_PUBLISH is not null");
        if (where != null)
            from.append(where);
        SqlBuilder order = buildOrder();
        if (order != null)
            from.append(order);

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if (this.isParaExists(PAGE_INDEX) && this.isParaExists(PAGE_SIZE)) {
            Page<Notice> paginate = getDao().paginate(getParaToInt(PAGE_INDEX),
                                                      getParaToInt(PAGE_SIZE),
                                                      selectStatement.getSql(),
                                                      fromStatement.getSql(),
                                                      fromStatement.getParams());
            renderJson(EasyUIControllerHelper.toDatagridResult(paginate));
        } else {
            List<Notice> list = getDao().find(selectStatement.getSql() + " " + fromStatement.getSql(),
                                              fromStatement.getParams());
            renderJson(list);
        }
    }

    public void drafts() {
    }

    /**
     * 草稿
     */
    public void drafts_list() {
        SqlBuilder select = SQL.SELECT("*");
        SqlBuilder from = new SqlBuilder().FROM(table.getName());
        SqlBuilder where = new SqlBuilder();

        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
//        R_TENANT
        where.WHERE("R_TENANT=?",identityContext.getTenantId());
        where.WHERE("R_PUBLISH is null");


        if (where != null)
            from.append(where);
        SqlBuilder order = buildOrder();
        if (order != null)
            from.append(order);

        SqlStatement selectStatement = select.toSqlStatement();
        SqlStatement fromStatement = from.toSqlStatement();

        if (this.isParaExists(PAGE_INDEX) && this.isParaExists(PAGE_SIZE)) {
            Page<Notice> paginate = getDao().paginate(getParaToInt(PAGE_INDEX),
                                                      getParaToInt(PAGE_SIZE),
                                                      selectStatement.getSql(),
                                                      fromStatement.getSql(),
                                                      fromStatement.getParams());
            renderJson(EasyUIControllerHelper.toDatagridResult(paginate));
        } else {
            List<Notice> list = getDao().find(selectStatement.getSql() + " " + fromStatement.getSql(),
                                              fromStatement.getParams());
            renderJson(list);
        }
    }
}
