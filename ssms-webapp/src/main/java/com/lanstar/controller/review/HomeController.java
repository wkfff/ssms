/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewHomeController.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.controller.review;

import com.lanstar.app.Const;
import com.lanstar.core.Controller;
import com.lanstar.model.Done;
import com.lanstar.model.system.Notice;
import com.lanstar.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class HomeController extends Controller {
    public void index(){
        setAttr( Const.HOME_PAGE, "/r/home" );
    }

    public void home(){
        List<Notice> rs_notice = Notice.dao.find("select * from sys_notice");
        setAttr("rs_notice", rs_notice );
        
        List<Notice> rs_notice2 = new ArrayList<Notice>();
        setAttr("rs_notice2", rs_notice2 );
        
        List<Todo> rs_todo = Todo.dao.find("select * from sys_todo where c_control='REVIEW'");
        setAttr("rs_todo", rs_todo );
        
        List<Done> rs_done = Done.dao.find("select * from sys_done where c_control='REVIEW'");
        setAttr("rs_done", rs_done );
    }
}
