/*
 * 椤圭洰鍚嶇О锛氬畨鍏ㄧ敓浜ф爣鍑嗗寲绠＄悊绯荤粺(Safety Standardization Management System)
 * 鐗堟潈鐢虫槑锛氱宸炲競纾熀鐢靛瓙鏈夐檺鍏徃銆佺宸炲競钃濈煶鐢靛瓙鏈夐檺鍏徃鎵?鏈夛紝鏈粡璁稿彲涓嶅緱鍦ㄤ换浣曡蒋浠朵腑浠ヤ换浣曞舰寮忎娇鐢ㄥ叏閮ㄦ垨閮ㄥ垎浠ｇ爜锛屼笉寰楁洿鏀规湰椤圭洰鐨勪唬鐮併??
 * 鏂囦欢鍚嶇О锛歏alidateCode.java
 * 鍒涘缓鏃堕棿锛?2015-05-13
 * 鍒涘缓鐢ㄦ埛锛氬紶閾浆
 */

package com.lanstar.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码生成器
 *
 * @author dsna
 */
public class ValidateCode {
    // 图片的宽度。
    private int width = 160;
    // 图片的高度。
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 5;
    // 验证码干扰线数
    private int lineCount = 150;
    // 验证码
    private String code = null;
    // 验证码图片Buffer
    private BufferedImage buffImg = null;

    private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    public ValidateCode() {
        this.createCode();
    }

    /**
     * @param width  图片宽
     * @param height 图片高
     */
    public ValidateCode( int width, int height ) {
        this.width = width;
        this.height = height;
        this.createCode();
    }

    /**
     * @param width     图片宽
     * @param height    图片高
     * @param codeCount 字符个数
     * @param lineCount 干扰线条数
     */
    public ValidateCode( int width, int height, int codeCount, int lineCount ) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.createCode();
    }

    public void createCode() {
        int x = 0, fontHeight = 0, codeY = 0;
        int red = 0, green = 0, blue = 0;

        x = width / (codeCount + 2);//每个字符的宽度
        fontHeight = height - 2;//字体的高度
        codeY = height - 4;

        // 图像buffer
        buffImg = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
        Graphics2D g = buffImg.createGraphics();
        // 生成随机数
        Random random = new Random();
        // 将图像填充为白色
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, width, height );
        // 创建字体
        Font font = ImgFontByte.getFont( fontHeight );
        g.setFont( font );

        for ( int i = 0; i < lineCount; i++ ) {
            int xs = random.nextInt( width );
            int ys = random.nextInt( height );
            int xe = xs + random.nextInt( width / 8 );
            int ye = ys + random.nextInt( height / 8 );
            red = random.nextInt( 255 );
            green = random.nextInt( 255 );
            blue = random.nextInt( 255 );
            g.setColor( new Color( red, green, blue ) );
            g.drawLine( xs, ys, xe, ye );
        }

        // randomCode记录随机产生的验证码
        StringBuilder randomCode = new StringBuilder();
        // 随机产生codeCount个字符的验证码。
        for ( int i = 0; i < codeCount; i++ ) {
            String strRand = String.valueOf( codeSequence[random.nextInt( codeSequence.length )] );
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            red = random.nextInt( 255 );
            green = random.nextInt( 255 );
            blue = random.nextInt( 255 );
            g.setColor( new Color( red, green, blue ) );
            g.drawString( strRand, (i + 1) * x, codeY );
            // 将产生的四个随机数组合在一起。
            randomCode.append( strRand );
        }
        // 将四位数字的验证码保存到Session中。
        code = randomCode.toString();
    }

    public void write( String path ) throws IOException {
        OutputStream sos = new FileOutputStream( path );
        this.write( sos );
    }

    public void write( OutputStream sos ) throws IOException {
        ImageIO.write( buffImg, "png", sos );
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code;
    }
}
