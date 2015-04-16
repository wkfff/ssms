/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Validator.java
 * 创建时间：2015-04-12
 * 创建用户：张铮彬
 */

package com.lanstar.core.validate;

import com.lanstar.core.handle.HandleException;
import com.lanstar.core.handle.HandlerContext;
import com.lanstar.core.handle.action.ActionInvocation;
import com.lanstar.core.interceptor.Interceptor;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator.
 */
public abstract class Validator implements Interceptor {
    private ActionInvocation invocation;
    private HandlerContext context;
    private boolean shortCircuit = false;
    private boolean invalid = false;
    protected final Map<String, String> errorMsg = new LinkedHashMap<>();
    private static final String emailAddressPattern = "\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";

    protected void setShortCircuit( boolean shortCircuit ) {
        this.shortCircuit = shortCircuit;
    }

    final public void intercept( ActionInvocation invocation ) {
        Validator validator;
        try {validator = getClass().newInstance();} catch ( Exception e ) {throw new HandleException( e );}

        validator.context = invocation.getContext();
        validator.invocation = invocation;

        try {
            validator.validate( validator.context );
        } catch ( ValidateException e ) {/* should not be throw */}            // short circuit validate need this

        if ( validator.invalid )
            validator.handleError( validator.context );
    }

    protected abstract void validate( HandlerContext c );

    protected abstract void handleError( HandlerContext c );

    protected void addError( String errorKey, String errorMessage ) {
        invalid = true;
        errorMsg.put( errorKey, errorMessage );
        if ( shortCircuit ) {
            throw new ValidateException();
        }
    }

    /**
     * Validate Required.
     */
    protected void validateRequired( String field, String errorKey, String errorMessage ) {
        String value = getPara( field );
        if ( value == null || "".equals( value ) )    // 经测试,无输入时值为"",跳格键值为"\t",输入空格则为空格" "
            addError( errorKey, errorMessage );
    }

    protected String getPara( String field ) {
        Object value = context.getValue( field );
        return (String) value;
    }

    /**
     * Validate required string.
     */
    protected void validateRequiredString( String field, String errorKey, String errorMessage ) {
        String value = getPara( field );
        if ( value == null || "".equals( value.trim() ) )
            addError( errorKey, errorMessage );
    }

    /**
     * Validate integer.
     */
    protected void validateInteger( String field, int min, int max, String errorKey, String errorMessage ) {
        try {
            String value = getPara( field );
            int temp = Integer.parseInt( value );
            if ( temp < min || temp > max )
                addError( errorKey, errorMessage );
        } catch ( Exception e ) {
            addError( errorKey, errorMessage );
        }
    }

    /**
     * Validate long.
     */
    protected void validateLong( String field, long min, long max, String errorKey, String errorMessage ) {
        try {
            String value = getPara( field );
            long temp = Long.parseLong( value );
            if ( temp < min || temp > max )
                addError( errorKey, errorMessage );
        } catch ( Exception e ) {
            addError( errorKey, errorMessage );
        }
    }

    /**
     * Validate long.
     */
    protected void validateLong( String field, String errorKey, String errorMessage ) {
        try {
            String value = getPara( field );
            Long.parseLong( value );
        } catch ( Exception e ) {
            addError( errorKey, errorMessage );
        }
    }

    /**
     * Validate double.
     */
    protected void validateDouble( String field, double min, double max, String errorKey, String errorMessage ) {
        try {
            String value = getPara( field );
            double temp = Double.parseDouble( value );
            if ( temp < min || temp > max )
                addError( errorKey, errorMessage );
        } catch ( Exception e ) {
            addError( errorKey, errorMessage );
        }
    }

    /**
     * Validate double.
     */
    protected void validateDouble( String field, String errorKey, String errorMessage ) {
        try {
            String value = getPara( field );
            Double.parseDouble( value );
        } catch ( Exception e ) {
            addError( errorKey, errorMessage );
        }
    }

    /**
     * Validate date.
     */
    protected void validateDate( String field, Date min, Date max, String errorKey, String errorMessage ) {
        try {
            String value = getPara( field );
            Date temp = new SimpleDateFormat( datePattern )
                    .parse( value );    // Date temp = Date.valueOf(value); 为了兼容 64位 JDK
            if ( temp.before( min ) || temp.after( max ) )
                addError( errorKey, errorMessage );
        } catch ( Exception e ) {
            addError( errorKey, errorMessage );
        }
    }

    // TODO set in Const and config it in Constants. TypeConverter do the same thing.
    private static final String datePattern = "yyyy-MM-dd";

    /**
     * Validate date. Date formate: yyyy-MM-dd
     */
    protected void validateDate( String field, String min, String max, String errorKey, String errorMessage ) {
        // validateDate(field, Date.valueOf(min), Date.valueOf(max), errorKey, errorMessage);  为了兼容 64位 JDK
        try {
            SimpleDateFormat sdf = new SimpleDateFormat( datePattern );
            validateDate( field, sdf.parse( min ), sdf.parse( max ), errorKey, errorMessage );
        } catch ( ParseException e ) {
            addError( errorKey, errorMessage );
        }
    }

    /**
     * Validate equal field. Usually validate password and password again
     */
    protected void validateEqualField( String field_1, String field_2, String errorKey, String errorMessage ) {
        String value_1 = getPara( field_1 );
        String value_2 = getPara( field_2 );
        if ( value_1 == null || value_2 == null || (!value_1.equals( value_2 )) )
            addError( errorKey, errorMessage );
    }

    /**
     * Validate equal string.
     */
    protected void validateEqualString( String s1, String s2, String errorKey, String errorMessage ) {
        if ( s1 == null || s2 == null || (!s1.equals( s2 )) )
            addError( errorKey, errorMessage );
    }

    /**
     * Validate equal integer.
     */
    protected void validateEqualInteger( Integer i1, Integer i2, String errorKey, String errorMessage ) {
        if ( i1 == null || i2 == null || (i1.intValue() != i2.intValue()) )
            addError( errorKey, errorMessage );
    }

    /**
     * Validate email.
     */
    protected void validateEmail( String field, String errorKey, String errorMessage ) {
        validateRegex( field, emailAddressPattern, false, errorKey, errorMessage );
    }

    /**
     * Validate URL.
     */
    protected void validateUrl( String field, String errorKey, String errorMessage ) {
        try {
            String value = getPara( field );
            if ( value.startsWith( "https://" ) )
                value = "http://" + value.substring( 8 ); // URL doesn't understand the https protocol, hack it
            new URL( value );
        } catch ( MalformedURLException e ) {
            addError( errorKey, errorMessage );
        }
    }

    /**
     * Validate regular expression.
     */
    protected void validateRegex( String field, String regExpression, boolean isCaseSensitive, String errorKey, String errorMessage ) {
        String value = getPara( field );
        if ( value == null ) {
            addError( errorKey, errorMessage );
            return;
        }
        Pattern pattern = isCaseSensitive ?
                Pattern.compile( regExpression ) :
                Pattern.compile( regExpression, Pattern.CASE_INSENSITIVE );
        Matcher matcher = pattern.matcher( value );
        if ( !matcher.matches() )
            addError( errorKey, errorMessage );
    }

    /**
     * Validate regular expression and case sensitive.
     */
    protected void validateRegex( String field, String regExpression, String errorKey, String errorMessage ) {
        validateRegex( field, regExpression, true, errorKey, errorMessage );
    }

    protected void validateString( String field, boolean notBlank, int minLen, int maxLen, String errorKey, String errorMessage ) {
        String value = getPara( field );
        if ( value == null || value.length() < minLen || value.length() > maxLen )
            addError( errorKey, errorMessage );
        else if ( notBlank && "".equals( value.trim() ) )
            addError( errorKey, errorMessage );
    }

    /**
     * Validate string.
     */
    protected void validateString( String field, int minLen, int maxLen, String errorKey, String errorMessage ) {
        validateString( field, true, minLen, maxLen, errorKey, errorMessage );
    }
}



