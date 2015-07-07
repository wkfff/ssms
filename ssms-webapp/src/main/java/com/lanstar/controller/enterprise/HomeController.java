/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseHomeController.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.app.Const;
import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.identity.TenantType;
import com.lanstar.model.system.Notice;
import com.lanstar.model.system.Profession;
import com.lanstar.model.tenant.*;
import com.lanstar.plugin.activerecord.Record;
import com.lanstar.service.common.todo.TodoBean;
import com.lanstar.service.common.todo.TodoService;
import com.lanstar.service.enterprise.EnterpriseService;
import com.lanstar.service.enterprise.ProfessionService;
import com.lanstar.service.enterprise.TemplateInitTask;
import com.lanstar.service.enterprise.TemplateInitializerState;

import java.util.List;
import java.util.Objects;

public class HomeController extends Controller {
    public void index() {
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        EnterpriseService enterpriseService = identityContext.getEnterpriseService();
        // 如果专业服务还没初始化，那么就初始化服务。
        if ( enterpriseService.professionServiceInitialized() == false ) {
            List<Profession> professions = enterpriseService.getProfessions();
            // 先从cookie里取出，如果没有的话就直接设置第一个了。
            Integer professionId = getCookieToInt( "profession" );
            Profession currentProfession = null;
            if ( professionId == null ) currentProfession = professions.get( 0 );
            else {
                for ( Profession profession : professions ) {
                    if ( Objects.equals( profession.getId(), professionId ) ) {
                        currentProfession = profession;
                        break;
                    }
                }
                // 如果根据cookie的值还是没有找到专业的话，则默认为第一个专业。
                if ( currentProfession == null ) currentProfession = professions.get( 0 );
            }
            if ( setProfession( currentProfession ) == false ) return;
        }

        setAttr( Const.HOME_PAGE, "/e/home" );
    }

    public void home() {
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        EnterpriseService enterpriseService = identityContext.getEnterpriseService();
        ProfessionService professionService = enterpriseService.getProfessionService();
        TemplateFolder folder = professionService.getTenantTemplateFolder();
        setAttr( "FILE_COUNT", folder == null ? 0 : folder.getFileCount() );

        setAttr( "FILE_NO_CREATE", identityContext.getTenantDb()
                                                  .queryLong( "select COUNT(*) from ssm_stdtmp_file where R_TENANT=? and P_TENANT=? and R_TEMPLATE=? and P_PROFESSION=? and N_COUNT = 0",
                                                          identityContext.getTenantId(),
                                                          identityContext.getTenantType().getName(),
                                                          professionService.getSystemTemplate().getId(),
                                                          professionService.getId() ) );

        // TODO 从待办表读取待办
        List<TemplateFile> files = TemplateFile.dao.find( "SELECT A.*\n"
                + "FROM SSM_STDTMP_FILE A\n"
                + "inner join SSM_STDTMP_FOLDER B on a.r_sid = b.sid\n"
                + "where B.R_TEMPLATE = ? AND B.R_TENANT=? AND B.P_TENANT=?\n"
                + "and A.N_COUNT = 0 ORDER BY B.N_INDEX, B.SID, A.N_INDEX, SID\n"
                + "limit 10", professionService.getSystemTemplate()
                                               .getId(), identityContext.getTenantId(), TenantType.ENTERPRISE
                .getName() );
        setAttr( "rs_todo", files );

        //接收的通知公告
        String sql = "SELECT * FROM V_NOTICE WHERE R_RECEIVER=? ORDER BY T_PUBLISH DESC LIMIT 8";
        List<Record> rs_notice = identityContext.getTenantDb().find( sql,identityContext.getTenantId());
        setAttr( "rs_notice", rs_notice );

//        setAttr( "rs_dev", TemplateFile08.dao.find( "select * from ssm_stdtmp_file_08 limit 10" ) );
//        setAttr( "rs_yh", TemplateFile06.dao.find( "select * from ssm_stdtmp_file_06 limit 10" ) );
//        setAttr( "rs_ry", TemplateFile07.dao.find( "select * from SSM_STDTMP_FILE_07 limit 10" ) );
        
        //隐患排查
        List<TodoBean> list_yh = TodoService.with( identityContext.getTenant() ).listTodoBean( "com.lanstar.quartz.tenantdb.TemplateFile06Task", 10 );
        setAttr( "rs_yh",list_yh);
        //特种设备
        List<TodoBean> list_dev = TodoService.with( identityContext.getTenant() ).listTodoBean( "com.lanstar.quartz.tenantdb.TemplateFile07Task", 10 );
        setAttr( "rs_dev",list_dev);
        //特种人员
        List<TodoBean> list_ry = TodoService.with( identityContext.getTenant() ).listTodoBean( "com.lanstar.quartz.tenantdb.TemplateFile08Task", 10 );
        setAttr( "rs_ry",list_ry);
    }

    public void setTemplate() {
        Integer id = getParaToInt( "profession" );
        Profession profession = Profession.dao.findById( id );
        if ( setProfession( profession ) ) redirect( "/" );
    }

    public void status() {
        IdentityContext context = IdentityContext.getIdentityContext( this );
        ProfessionService professionService = context.getEnterpriseService().getProfessionService();
        TemplateInitTask task = professionService.getTemplateInitTask();
        if ( task != null ) {
            setAttr( "LOGS", task.getLogs() );
            setAttr( "STATUS", task.status() );
        }
    }

    public void finish() {
        IdentityContext context = IdentityContext.getIdentityContext( this );
        ProfessionService professionService = context.getEnterpriseService().getProfessionService();
        TemplateInitTask task = professionService.getTemplateInitTask();
        if ( task != null && task.status() == TemplateInitializerState.FINISH ) {
            professionService.finishInitTemplate();
            redirect( "/" );
        }
    }

    private boolean setProfession( Profession profession ) {
        IdentityContext context = IdentityContext.getIdentityContext( this );
        EnterpriseService enterpriseService = context.getEnterpriseService();
        enterpriseService.initProfessionService( profession );
        ProfessionService professionService = enterpriseService.getProfessionService();
        // 一年内不过期
        setCookie( "profession", String.valueOf( profession.getId() ), 365 * 24 * 60 * 60 );
        // 如果模板未初始化则进行初始化
        if ( professionService.templateInitialized() == false ) {
            professionService.initTemplate( context.getIdentity() );
            redirect( "/e/status#this" );
            return false;
        }
        return true;
    }
}
