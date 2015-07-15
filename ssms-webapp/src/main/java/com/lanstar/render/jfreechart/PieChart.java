/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：PieChart.java
 * 创建时间：2015-07-06
 * 创建用户：张铮彬
 */

package com.lanstar.render.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PieChart extends Chart<PieChart> {
    DefaultPieDataset dataset = new DefaultPieDataset();

    public PieChart( String title ) {
        super( title );
    }

    public PieChart setValue( Comparable<?> key, Number value ) {
        dataset.setValue( key, value );
        return this;
    }

    public PieChart setValue( Comparable<?> key, double value ) {
        dataset.setValue( key, value );
        return this;
    }

    @Override
    protected JFreeChart getChart() {
        JFreeChart chart = ChartFactory.createPieChart3D( title, dataset, legend, tooltips, false );
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator( new StandardPieSectionLabelGenerator( "{0}({2})",
                NumberFormat.getNumberInstance(),
                new DecimalFormat( "0.00%" ) ) );
        plot.setNoDataMessage( "无相关数据" );
        return chart;
    }
}
