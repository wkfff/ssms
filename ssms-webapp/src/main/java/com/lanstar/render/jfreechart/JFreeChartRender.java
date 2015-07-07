/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JFreeChartRender.java
 * 创建时间：2015-07-06
 * 创建用户：张铮彬
 */

package com.lanstar.render.jfreechart;

import com.lanstar.common.log.Logger;
import com.lanstar.core.render.Render;
import com.lanstar.core.render.RenderException;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

public class JFreeChartRender extends Render {
    protected final Logger LOG = Logger.getLogger( getClass() );

    private static final String CONTENT_TYPE = "image/jpeg";
    private final Chart<?> chart;
    private static final StandardChartTheme standardChartTheme;

    static {
        //创建主题样式
        standardChartTheme = new StandardChartTheme( "CN" );
        //设置标题字体
        standardChartTheme.setExtraLargeFont( new Font( "宋书", Font.BOLD, 20 ) );
        //设置图例的字体
        standardChartTheme.setRegularFont( new Font( "宋书", Font.PLAIN, 15 ) );
        //设置轴向的字体
        standardChartTheme.setLargeFont( new Font( "宋书", Font.PLAIN, 15 ) );
    }

    JFreeChartRender( Chart<?> chart ) {
        this.chart = chart;
    }

    @Override
    public void render() {
        response.reset();
        response.setContentType( CONTENT_TYPE );
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            JFreeChart chart = this.chart.getChart();
            standardChartTheme.apply( chart );
            ChartUtilities.writeChartAsJPEG( os, chart, this.chart.width, this.chart.height );
        } catch ( Exception e ) {
            throw new RenderException( e );
        } finally {
            try {
                if ( os != null ) {
                    os.flush();
                    os.close();
                }
            } catch ( IOException e ) {
                LOG.error( e.getMessage(), e );
            }
        }
    }

    public static PieChart PIE( String title ) {
        return new PieChart( title );
    }
}
