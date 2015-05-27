/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateFileController.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.core.Controller;
import com.lanstar.model.tenant.TemplateFile;

public class TemplateFileController extends Controller {
    public void index() {
        String pid = getPara( "R_SID" );
        pid = pid.substring( 2 );
        TemplateFile templateFile = TemplateFile.dao.findById( pid );
        redirect( "/e/stdtmp_file_"+templateFile.getTemplateFileCode()+"/rec?sid="+templateFile.getTemplateFileId() );
    }
}
