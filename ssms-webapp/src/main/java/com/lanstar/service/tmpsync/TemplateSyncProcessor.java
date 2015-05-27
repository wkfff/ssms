/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateSyncProcessor.java
 * 创建时间：2015-05-26
 * 创建用户：张铮彬
 */

package com.lanstar.service.tmpsync;

import com.lanstar.common.log.Logger;
import com.lanstar.identity.IdentityContext;
import com.lanstar.model.system.Template;
import com.lanstar.model.system.TemplateFolder;

import java.util.List;

public class TemplateSyncProcessor implements SyncProcessor {
    private static final Logger log = Logger.getLogger( TemplateSyncProcessor.class );
    private final Template template;

    public TemplateSyncProcessor( Template template ) {
        this.template = template;
    }

    @Override
    public void sync( IdentityContext target ) {
        log.debug( "begin sync template...." );
        List<TemplateFolder> folders = template.listFolder( 0 );
        for ( TemplateFolder folder : folders ) {
            new FolderSyncProcessor( folder ).sync( target );
        }
        log.debug( "sync template finish...." );
    }
}
