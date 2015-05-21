/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：EnterpriseHomeController.java
 * 创建时间：2015-05-20
 * 创建用户：张铮彬
 */

package com.lanstar.controller.enterprise;

import com.lanstar.core.Controller;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.Profession;

import java.util.List;

public class HomeController extends Controller {
    public void index() {
        IdentityContext identityContext = IdentityContext.getIdentityContext( this );
        boolean needChooseProfessions = identityContext.hasValue( Profession.class ) == false;
        if ( needChooseProfessions ) {
            List<Profession> professions = identityContext.getProfessions();
            if ( professions.size() == 1 ) {
                setAttr( "profession", professions.get( 0 ).getInt( "SID" ) );
                setTemplate();
                removeAttr( "profession" );
                needChooseProfessions = false;
            }
        }

        setAttr( "needChooseProfessions", needChooseProfessions );

        if ( needChooseProfessions == false ) setAttr( "profession", identityContext.getValue( Profession.class ) );
    }

    public void setTemplate() {
        Profession profession = Profession.dao.findById( getAttr( "profession" ) );
        IdentityContext context = IdentityContext.getIdentityContext( this );
        context.setProfession(profession);
    }
}
