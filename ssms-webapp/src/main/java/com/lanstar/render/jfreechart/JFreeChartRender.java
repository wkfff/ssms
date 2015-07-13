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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

public class JFreeChartRender extends Render {
    protected final Logger LOG = Logger.getLogger( getClass() );

    private static final String CONTENT_TYPE = "image/jpeg";
    private final Chart<?> chart;
    private static final StandardChartTheme standardChartTheme;

    private static Font FONT = new Font( "宋体", Font.PLAIN, 12 );

    static {
        //创建主题样式
        standardChartTheme = new StandardChartTheme( "CN" );
        standardChartTheme.setExtraLargeFont( FONT );
        standardChartTheme.setRegularFont( FONT );
        standardChartTheme.setLargeFont( FONT );
        standardChartTheme.setSmallFont( FONT );
        standardChartTheme.setTitlePaint( new Color( 51, 51, 51 ) );
        standardChartTheme.setSubtitlePaint( new Color( 85, 85, 85 ) );
        standardChartTheme.setChartBackgroundPaint( Color.WHITE );
        standardChartTheme.setLegendBackgroundPaint( Color.WHITE );// 设置标注
        standardChartTheme.setLegendItemPaint( Color.BLACK );//
        standardChartTheme.setChartBackgroundPaint( Color.WHITE );
        standardChartTheme.setPlotBackgroundPaint( Color.WHITE );// 绘制区域
        standardChartTheme.setPlotOutlinePaint( Color.WHITE );// 绘制区域外边框
        standardChartTheme.setLabelLinkPaint( new Color( 8, 55, 114 ) );// 链接标签颜色
        standardChartTheme.setLabelLinkStyle( PieLabelLinkStyle.CUBIC_CURVE );

        standardChartTheme.setAxisOffset( new RectangleInsets( 5, 12, 5, 12 ) );
        standardChartTheme.setDomainGridlinePaint( new Color( 192, 208, 224 ) );// X坐标轴垂直网格颜色
        standardChartTheme.setRangeGridlinePaint( new Color( 192, 192, 192 ) );// Y坐标轴水平网格颜色

        standardChartTheme.setBaselinePaint( Color.WHITE );
        standardChartTheme.setCrosshairPaint( Color.BLUE );// 不确定含义
        standardChartTheme.setAxisLabelPaint( new Color( 51, 51, 51 ) );// 坐标轴标题文字颜色
        standardChartTheme.setTickLabelPaint( new Color( 67, 67, 72 ) );// 刻度数字
        standardChartTheme.setBarPainter( new StandardBarPainter() );// 设置柱状图渲染
        standardChartTheme.setXYBarPainter( new StandardXYBarPainter() );// XYBar 渲染

        standardChartTheme.setItemLabelPaint( Color.black );
        standardChartTheme.setThermometerPaint( Color.white );// 温度计
        ChartFactory.setChartTheme( standardChartTheme );
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
            chart.setTextAntiAlias(false);
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
