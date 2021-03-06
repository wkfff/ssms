/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：BrowerController.java
 * 创建时间：2015-07-13
 * 创建用户：张铮彬
 */

package com.lanstar.controller.common;

import com.lanstar.app.UserAgentInterceptor;
import com.lanstar.core.Controller;
import com.lanstar.core.Rapidware;
import com.lanstar.core.aop.ClearInterceptor;
import com.lanstar.core.aop.ClearLayer;

@ClearInterceptor(ClearLayer.ALL)
public class BrowerController extends Controller {
    public void index() {
        if ( Rapidware.me().getConstants().getDevMode() != false ) return;

        if ( UserAgentInterceptor.isValidBrowser( this ) ) {
            redirect( "/" );
        }
    }
}
