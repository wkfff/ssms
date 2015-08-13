/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ReviewNoticeController.java
 * 创建时间：下午4:33:17
 * 创建用户：苏志亮
 */
package com.lanstar.controller.system;

import java.util.Date;
import java.util.List;

import com.lanstar.common.Asserts;
import com.lanstar.common.ModelInjector;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.model.system.ReviewNotice;
import com.lanstar.model.system.tenant.Review;
import com.lanstar.plugin.activerecord.ModelKit;
import com.lanstar.plugin.sqlinxml.SqlKit;

public class ReviewNoticeController extends Controller {

    public void index() {
        Integer tenantId = getParaToInt( "eid" );
        Integer professionId = getParaToInt( "pro" );
        Asserts.notNull( tenantId, "存在非法参数" );
        Asserts.notNull( professionId, "存在非法参数" );
        setAttr( "eid", tenantId );
        setAttr( "pro", professionId );
    };

    public void publishs() {
        this.index();
        String reader = getPara( "reader" );// 判断是否是企业在读该数据 存在就标企业在读发布公告
        if ( reader != null && !reader.equals( "" ) ) {
            setAttr( "reader", reader );
        }
    }

    public void list() {
        Integer tenantId = getParaToInt( "eid" );
        Integer professionId = getParaToInt( "pro" );
        Integer state = getParaToInt( "state" );// 发布 为发布 状态值
        String sql;
        if ( state == 1 ) sql = "SELECT * FROM sys_review_notice WHERE N_STATE=1 AND R_RECEIVER=? AND P_PROFESSION=?  ORDER BY T_PUBLISH DESC";
        else sql = "SELECT * FROM sys_review_notice WHERE N_STATE=0 AND R_RECEIVER=? AND P_PROFESSION=? ORDER BY T_CREATE DESC";
        List<ReviewNotice> list = ReviewNotice.dao.find( sql, tenantId, professionId );
        renderJson( list );
    };

    public void draftForm() {
        Integer tenantId = getParaToInt( "eid" );
        Integer professionId = getParaToInt( "pro" );
        Integer sid = getParaToInt( "sid" );
        String reader = getPara( "reader" );// 判断是否是企业在读该数据 存在就标企业在读发布公告
        setAttr( "eid", tenantId );
        setAttr( "pro", professionId );
        if ( sid == null ) return;
        ReviewNotice model = ReviewNotice.dao.findById( sid );
        if ( reader != null && !reader.equals( "" ) ) {
            if ( !model.getReader() ) {
                model.setReader( true );
                model.update();
            }
        }
        if ( model != null ) setAttrs( ModelKit.toMap( model ) );
    }

    public void publishForm() {
        this.draftForm();
    }

    public void save() {
        Integer tenantId = getParaToInt( "eid" );
        Integer professionId = getParaToInt( "pro" );
        Integer sid = getParaToInt( "sid" );
        String title = getPara( "title" );
        String content = getPara( "content" );
        ReviewNotice model = null;
        if ( sid != null ) model = ReviewNotice.dao.findById( sid );
        else model = new ReviewNotice();

        model.setTitle( title );
        model.setContent( content );
        model.setState( false );// 用来标识发布状态 true表示已发布false表示未发布
        model.setReader( false );// 设置阅读状态默认值
        model.setRceiverId( tenantId );
        model.setProfessionId( professionId );
        ModelInjector.injectOpreator( model, IdentityContextWrap.getIdentityContext( this ) );

        if ( sid != null ) model.update();
        else model.save();
        setAttr( "id", model.getId() );
        renderJson( model.getId() );
    }

    public void publish() {
        this.save();
        Integer sid = getAttr( "id" );
        Review tenant = (Review) IdentityContextWrap.getIdentityContext( this ).getTenant();
        ReviewNotice model = ReviewNotice.dao.findById( sid );
        model.setPublishId( tenant.getId() );
        model.setPublishName( tenant.getName() );
        model.setPublishTime( new Date() );
        model.setState( true );
        model.update();
        renderJson( model.getId() );
    }

    public void del() {
        Integer sid = getParaToInt( "sid" );
        ReviewNotice model = ReviewNotice.dao.findById( sid );
        if ( model == null ) {
            renderJson( false );
            return;
        }
        renderJson( model.delete() );
    }
}
