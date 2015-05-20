/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ModelInjector.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.common.kit.StrKit;
import com.lanstar.plugin.activerecord.ActiveRecordException;
import com.lanstar.plugin.activerecord.Model;
import com.lanstar.plugin.activerecord.Table;
import com.lanstar.plugin.activerecord.TableMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ModelInjector
 */
final class ModelInjector {

    @SuppressWarnings("unchecked")
    public static <T> T inject( Class<?> modelClass, HttpServletRequest request, boolean skipConvertError ) {
        String modelName = modelClass.getSimpleName();
        return (T) inject( modelClass, StrKit.firstCharToLowerCase( modelName ), request, skipConvertError );
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> T inject( Class<?> modelClass, String modelName, HttpServletRequest request, boolean skipConvertError ) {
        Object model;
        try {
            model = modelClass.newInstance();
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }

        if ( model instanceof Model )
            injectActiveRecordModel( (Model) model, modelName, request, skipConvertError );
        else
            injectCommonModel( model, modelName, request, modelClass, skipConvertError );

        return (T) model;
    }

    private static void injectCommonModel( Object model, String modelName, HttpServletRequest request, Class<?> modelClass, boolean skipConvertError ) {
        Method[] methods = modelClass.getMethods();
        for ( Method method : methods ) {
            String methodName = method.getName();
            if ( methodName.startsWith( "set" ) == false )    // only setter method
                continue;

            Class<?>[] types = method.getParameterTypes();
            if ( types.length != 1 )                        // only one parameter
                continue;

            String attrName = methodName.substring( 3 );
            String value = request.getParameter( modelName + "." + StrKit.firstCharToLowerCase( attrName ) );
            if ( value != null ) {
                try {
                    method.invoke( model, TypeConverter.convert( types[0], value ) );
                } catch ( Exception e ) {
                    if ( skipConvertError == false )
                        throw new RuntimeException( e );
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private static void injectActiveRecordModel( Model<?> model, String modelName, HttpServletRequest request, boolean skipConvertError ) {
        Table table = TableMapping.me().getTable( model.getClass() );

        String modelNameAndDot = modelName + ".";

        @SuppressWarnings("unchecked")
        Map<String, String[]> parasMap = request.getParameterMap();
        for ( Entry<String, String[]> e : parasMap.entrySet() ) {
            String paraKey = e.getKey();
            if ( paraKey.startsWith( modelNameAndDot ) ) {
                String paraName = paraKey.substring( modelNameAndDot.length() );
                Class colType = table.getColumnType( paraName );
                if ( colType == null )
                    throw new ActiveRecordException( "The model attribute " + paraKey + " is not exists." );
                String[] paraValue = e.getValue();
                try {
                    // Object value = Converter.convert(colType, paraValue != null ? paraValue[0] : null);
                    Object value = paraValue[0] != null ? TypeConverter.convert( colType, paraValue[0] ) : null;
                    model.set( paraName, value );
                } catch ( Exception ex ) {
                    if ( skipConvertError == false )
                        throw new RuntimeException( "Can not convert parameter: " + modelNameAndDot + paraName, ex );
                }
            }
        }
    }
}