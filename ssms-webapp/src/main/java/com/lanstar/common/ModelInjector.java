/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ModelInjector.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.common;

import com.lanstar.core.TypeConverter;
import com.lanstar.identity.Identity;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.plugin.activerecord.ActiveRecordException;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.activerecord.Table;
import com.lanstar.plugin.activerecord.TableMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class ModelInjector {
    public static void injectActiveRecordModel( Model<?> model, HttpServletRequest request, boolean skipConvertError ) {
        Table table = TableMapping.me().getTable( model.getClass() );

        @SuppressWarnings("unchecked")
        Map<String, String[]> parasMap = request.getParameterMap();
        for ( Map.Entry<String, String[]> e : parasMap.entrySet() ) {
            String paraKey = e.getKey();
            Class<?> colType = table.getColumnType( paraKey );
            if ( colType == null )
                throw new ActiveRecordException( "The model attribute " + paraKey + " is not exists." );
            String[] paraValue = e.getValue();
            try {
                // Object value = Converter.convert(colType, paraValue != null ? paraValue[0] : null);
                Object value = paraValue[0] != null ? TypeConverter.convert( colType, paraValue[0] ) : null;
                Object modelValue = model.get( paraKey );
                if ( Objects.equals( modelValue, value ) == false ) model.set( paraKey, value );
            } catch ( Exception ex ) {
                if ( skipConvertError == false )
                    throw new RuntimeException( "Can not convert parameter: " + paraKey, ex );
            }
        }

        IdentityContext context = IdentityContextWrap.getIdentityContext( request );
        if ( context != null ) injectOpreator( model, context );
    }

    public static void injectActiveRecordModel( Model<?> model, Map<String, String> parasMap ) {
        Table table = TableMapping.me().getTable( model.getClass() );

        for ( Map.Entry<String, String> e : parasMap.entrySet() ) {
            String paraKey = e.getKey();
            Class<?> colType = table.getColumnType( paraKey );
            if ( colType == null )
                continue;
            String paraValue = e.getValue();
            try {
                // Object value = Converter.convert(colType, paraValue != null ? paraValue[0] : null);
                Object value = paraValue != null ? TypeConverter.convert( colType, paraValue ) : null;
                Object modelValue = model.get( paraKey );
                if ( Objects.equals( modelValue, value ) == false ) model.set( paraKey, value );
            } catch ( Exception ex ) {
                throw new RuntimeException( "Can not convert parameter: " + paraKey, ex );
            }
        }
    }

    public static void injectActiveRecordModel( Model<?> model, Map<String, Object> parasMap, String... skipAttrs ) {
        Table table = TableMapping.me().getTable( model.getClass() );
        List<String> skipList = Arrays.asList( skipAttrs );

        for ( Map.Entry<String, Object> e : parasMap.entrySet() ) {
            String paraKey = e.getKey();
            if ( skipList.contains( paraKey ) ) continue;
            Class<?> colType = table.getColumnType( paraKey );
            if ( colType == null )
                throw new ActiveRecordException( "The model attribute " + paraKey + " is not exists." );
            Object paraValue = e.getValue();
            if ( paraValue == null ) continue;
            try {
                // Object value = Converter.convert(colType, paraValue != null ? paraValue[0] : null);
                Object value;
                if ( paraValue.getClass() == String.class )
                    value = TypeConverter.convert( colType, (String) paraValue );
                else if ( paraValue.getClass() == colType ) value = paraValue;
                else continue;

                Object modelValue = model.get( paraKey );
                if ( Objects.equals( modelValue, value ) == false ) model.set( paraKey, value );
            } catch ( Exception ex ) {
                throw new RuntimeException( "Can not convert parameter: " + paraKey, ex );
            }
        }
    }

    public static void injectOpreator( Model<?> model, IdentityContext context ) {
        Asserts.notNull( context, "context can not null" );
        injectOpreator( model, context.getIdentity() );
    }

    public static void injectOpreator( Model<?> model, Identity identity ) {
        injectOpreator( model, identity, false );
    }

    public static void injectOpreator( Model<?> model, Identity identity, boolean skipTenant ) {
        Asserts.notNull( model, "model can not null" );
        Asserts.notNull( identity, "identity can not null" );

        Table table = TableMapping.me().getTable( model.getClass() );
        String primaryKey = table.getPrimaryKey();
        Object id = model.get( primaryKey );
        if ( id == null ) { // for insert
            model.set( "R_CREATE", identity.getId() );
            model.set( "S_CREATE", identity.getName() );
            model.set( "T_CREATE", new Date() );

            model.set( "R_UPDATE", identity.getId() );
            model.set( "S_UPDATE", identity.getName() );
            model.set( "T_UPDATE", new Date() );

            if ( skipTenant == false ) {
                model.set( "R_TENANT", identity.getTenantId() );
                model.set( "S_TENANT", identity.getTenantName() );
                model.set( "P_TENANT", identity.getTenantType().getName() );
            }
        } else { // for update
            if ( model.isModified() == false ) return;
            model.set( "R_UPDATE", identity.getId() );
            model.set( "S_UPDATE", identity.getName() );
            model.set( "T_UPDATE", new Date() );
        }
    }
}
