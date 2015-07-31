/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：CaptchaRender.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

package com.lanstar.render;

import com.lanstar.app.Const;
import com.lanstar.common.ValidateCode;
import com.lanstar.common.kit.StrKit;
import com.lanstar.core.Controller;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.RenderException;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

public class CaptchaRender extends Render {
    private static String CAPTCHA_KEY = Const.CAPTCHA_KEY;
    private static CaptchaRender me = new CaptchaRender();

    public static CaptchaRender getInstance() {
        return me;
    }

    public static String getCaptchaKey() {
        return CAPTCHA_KEY;
    }

    public static void setCaptchaKey( String captchaKey ) {
        if ( captchaKey != null && captchaKey.length() > 0 && CAPTCHA_KEY.equals( captchaKey ) == false )
            CAPTCHA_KEY = captchaKey;
    }

    @Override
    public void render() {
        ValidateCode vCode = new ValidateCode( 120, 40, 4, 0 );

        // 设置响应的类型格式为图片格式
        response.setContentType( "image/jpeg" );
        //禁止图像缓存。
        response.setHeader( "Pragma", "no-cache" );
        response.setHeader( "Cache-Control", "no-cache" );
        response.setDateHeader( "Expires", 0 );

        request.getSession().setAttribute( CAPTCHA_KEY, vCode.getCode() );

        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            vCode.write( outputStream );
        } catch ( Exception e ) {
            throw new RenderException( e );
        } finally {
            if ( outputStream != null )
                try { outputStream.close(); } catch ( IOException ignored ) { }
        }
    }

    public static boolean validate( Controller controller, String inputCode ) {
        if ( StrKit.isEmpty( inputCode ) ) return false;
        String code = controller.getSessionAttr( CAPTCHA_KEY );
        if ( inputCode.equalsIgnoreCase( code ) ) {
            controller.removeSessionAttr( CAPTCHA_KEY );
            return true;
        }
        return false;
    }
}
