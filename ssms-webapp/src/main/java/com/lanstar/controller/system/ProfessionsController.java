/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ProfessionsController.java
 * 创建时间：2015-06-18
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.google.common.collect.Lists;
import com.lanstar.common.ModelInjector;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.model.system.*;
import com.lanstar.plugin.activerecord.Db;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.plugin.jsconstants.JsConstantBuilder;
import com.lanstar.plugin.sqlinxml.SqlKit;

import java.util.*;

public class ProfessionsController extends Controller {
    public void index() {
        List<Record> result = Db.find( SqlKit.sql( "system.profession.all" ) );
        Map<Integer, Map<String, Object>> pools = new HashMap<>();
        for ( Record record : result ) {
            Integer industryId = record.getInt( "id" );
            String industryName = record.getStr( "name" );
            Integer professionId = record.getInt( "proid" );
            String professionName = record.getStr( "proname" );
            Integer professionTemplate = record.getInt( "tmpid" );
            Map<String, Object> industry = pools.get( industryId );
            if ( industry == null ) {
                industry = new HashMap<>();
                industry.put( "id", industryId );
                industry.put( "name", industryName );
                pools.put( industryId, industry );
            }

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> professions = (List<Map<String, Object>>) industry.get( "professions" );
            if ( professions == null ) {
                professions = new ArrayList<>();
                industry.put( "professions", professions );
            }

            if ( professionId != null ) {
                Map<String, Object> profession = new HashMap<>();
                profession.put( "id", professionId );
                profession.put( "name", professionName );
                profession.put( "selectedTemplate", professionTemplate );
                professions.add( profession );
            }
        }

        setAttr( "industries", Lists.newArrayList( pools.values() ) );
        //原先的setAttr( "templates"，Template.dao.find( "select SID code, C_NAME name from sys_template where IFNULL(B_DELETE, 0)=0" )） 这个不行会出现空指针异常
        //重新构造数据才行
        List<Template> templatesList = Template.dao.find( "select SID code, C_NAME name from sys_template where IFNULL(B_DELETE, 0)=0" );
        List<Map<String,Object>> templatesRulsult = new ArrayList<>();
        Map<String,Object> templateMap;
        for(Template template : templatesList){
            templateMap = new HashMap<>();
            Integer code= template.getInt( "code" );
            String name= template.getStr( "name" );
            templateMap.put( "CODE", code);
            templateMap.put( "NAME" , name );
            templatesRulsult.add(templateMap );
        }
        setAttr( "templates",templatesRulsult );
    }

    public void saveProfession() {
        Integer id = getParaToInt( "id" );
        String name = getPara( "name" );
        Integer template = getParaToInt( "template" );
        Integer industryId = getParaToInt( "industryId" );
        IdentityContext context = IdentityContextWrap.getIdentityContext( this );
        Profession profession;
        if ( id == null ) profession = new Profession();
        else profession = Profession.dao.findById( id );

        ModelInjector.injectOpreator( profession, context );
        profession.setName( name );
        profession.setTemplateId( template );
        profession.setIndustryId( industryId );

        if ( id == null ) profession.save();
        else profession.update();
        renderJson( profession.getId() );

        JsConstantBuilder.me().build();
    }

    public void delProfession() {
        Integer id = getParaToInt( "id" );
        if ( id != null ) renderJson( Profession.dao.deleteById( id ) );
        else renderJson( false );

        JsConstantBuilder.me().build();
    }

    public void removeIndustry() {
        Integer id = getParaToInt( "id" );
        if ( id != null ) renderJson( Industry.dao.deleteById( id ) );
        else renderJson( false );

        JsConstantBuilder.me().build();
    }

    public void saveIndustry() {
        Integer id = getParaToInt( "id" );
        String name = getPara( "name" );
        Industry industry;
        if ( id == null ) industry = new Industry();
        else industry = Industry.dao.findById( id );

        IdentityContext context = IdentityContextWrap.getIdentityContext( this );
        industry.setName( name );
        ModelInjector.injectOpreator( industry, context );

        if ( id == null ) industry.save();
        else industry.update();
        renderJson( industry.getId() );

        JsConstantBuilder.me().build();
    }
}
