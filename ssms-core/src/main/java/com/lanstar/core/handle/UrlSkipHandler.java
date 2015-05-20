/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：UrlSkipHandler.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

import com.lanstar.common.kit.StrKit;
import com.lanstar.core.DispatchContext;

import java.util.regex.Pattern;

public class UrlSkipHandler implements Handler {

    private Pattern skipedUrlPattern;

    public UrlSkipHandler( String skipedUrlRegx, boolean isCaseSensitive ) {
        if ( StrKit.isBlank( skipedUrlRegx ) )
            throw new IllegalArgumentException( "The para excludedUrlRegx can not be blank." );
        skipedUrlPattern = isCaseSensitive ?
                Pattern.compile( skipedUrlRegx ) :
                Pattern.compile( skipedUrlRegx, Pattern.CASE_INSENSITIVE );
    }

    @Override
    public void handle( DispatchContext context, HandleChain next ) {
        if ( !skipedUrlPattern.matcher( context.getTarget() ).matches() )
            next.doHandle( context );
    }
}