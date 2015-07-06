/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JFreeChart.java
 * 创建时间：2015-07-06
 * 创建用户：张铮彬
 */

package com.lanstar.render.jfreechart;

import org.jfree.chart.JFreeChart;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class Chart<T extends Chart> {
    protected final String title;
    protected int width = 400;
    protected int height = 300;
    protected boolean legend;
    protected boolean tooltips;

    public Chart( String title ) {
        this.title = title;
    }

    protected abstract JFreeChart getChart();

    public T setLegend( boolean legend ) {
        this.legend = legend;
        return (T) this;
    }

    public T setTooltips( boolean tooltips ) {
        this.tooltips = tooltips;
        return (T) this;
    }

    public T setWidth( int width ) {
        this.width = width;
        return (T) this;
    }

    public T setHeight( int height ) {
        this.height = height;
        return (T) this;
    }

    public JFreeChartRender render() {
        return new JFreeChartRender( this );
    }
}
