/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SystemHomeController.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.controller.system;

import com.lanstar.app.Const;
import com.lanstar.core.Controller;

public class HomeController extends Controller {
    public void index(){
        setAttr( Const.HOME_PAGE, "/s/home" );
    }
    public void home(){

    }
}
