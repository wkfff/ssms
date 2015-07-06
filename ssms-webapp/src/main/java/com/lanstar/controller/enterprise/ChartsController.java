/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ChartsController.java
 * 创建时间：2015-07-06
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.core.Controller;
import com.lanstar.render.jfreechart.JFreeChartRender;

public class ChartsController extends Controller {
    public void chart01() {
        render( JFreeChartRender.PIE( "达标体系完成情况分析" )
                                .setValue( "苹果", 100 )
                                .setValue( "梨子", 200 )
                                .setWidth( 350 )
                                .setHeight( 200 )
                                .render() );
    }

    public void chart02() {
        render( JFreeChartRender.PIE( "特种设备年检率" )
                                .setValue( "苹果", 100 )
                                .setValue( "梨子", 200 )
                                .setWidth( 350 )
                                .setHeight( 200 )
                                .render() );
    }
}
