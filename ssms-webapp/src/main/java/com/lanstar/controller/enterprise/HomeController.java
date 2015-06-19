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
import com.lanstar.service.enterprise.EnterpriseService;
import com.lanstar.service.enterprise.ProfessionService;

import java.util.List;
import java.util.Objects;

public class HomeController extends Controller {
    public void index() {
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        EnterpriseService enterpriseService = identityContext.getEnterpriseService();
        // 如果专业服务还没初始化，那么就初始化服务。
        if ( enterpriseService.getProfessionService() == null ) {
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
                if ( currentProfession == null ) throw new RuntimeException( "无法取得企业的专业！" );
            }
            setProfession( currentProfession );
        }

        setAttr( Const.HOME_PAGE, "/e/home" );
    }

    public void setTemplate() {
        Integer id = getParaToInt( "profession" );
        Profession profession = Profession.dao.findById( id );
        setProfession( profession );

        redirect( "/" );
    }

    public void home() {
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        EnterpriseService enterpriseService = identityContext.getEnterpriseService();
        ProfessionService professionService = enterpriseService.getProfessionService();
        TemplateFolder folder = professionService.getTenantTemplateFolder();
        setAttr( "FILE_COUNT", folder == null ? 0 : folder.getFileCount() );

        setAttr( "FILE_NO_CREATE", identityContext.getTenantDb()
                                                  .queryLong( "select COUNT(*) from ssm_stdtmp_file where N_COUNT = 0" ) );

        List<TemplateFile> files = TemplateFile.dao.find( "SELECT *\n"
                + "FROM SSM_STDTMP_FILE\n"
                + "WHERE R_SID IN (\n"
                + "    SELECT SID FROM SSM_STDTMP_FOLDER\n"
                + "    WHERE R_TEMPLATE = ? AND R_TENANT=? AND P_TENANT=?\n"
                + ")\n"
                + "and N_COUNT = 0 limit 0, 15", professionService.getSystemTemplate()
                                                                  .getId(), identityContext.getTenantId(), TenantType.ENTERPRISE
                .getName() );
        setAttr( "rs_todo", files );

        List<Notice> rs_notice = Notice.dao.find( "select * from ssm_notice" );
        setAttr( "rs_notice", rs_notice );

        setAttr( "rs_dev", TemplateFile08.dao.find( "select * from ssm_stdtmp_file_08 limit 15" ) );
        setAttr( "rs_yh", TemplateFile06.dao.find( "select * from ssm_stdtmp_file_06 limit 15" ) );
        setAttr( "rs_ry", TemplateFile07.dao.find( "select * from SSM_STDTMP_FILE_07 limit 15" ) );
    }

    private void setProfession( Profession profession ) {
        IdentityContext context = IdentityContext.getIdentityContext( this );
        context.getEnterpriseService().setProfessionService( profession );
        setCookie( "profession", String.valueOf( profession.getId() ), 365 * 24 * 60 * 60 );
    }
}
