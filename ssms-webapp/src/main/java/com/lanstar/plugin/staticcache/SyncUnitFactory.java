/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SyncUnitFactory.java
 * 创建时间：2015-06-05
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.staticcache;

import com.lanstar.identity.IdentityContext;
import com.lanstar.model.tenant.TemplateFolder;

public abstract class SyncUnitFactory<T extends SyncUnit> {
    public static final SyncUnitFactory<SyncUnit> DEFAULT = new SyncUnitFactory<SyncUnit>() {
        @Override
        public SyncUnit getSyncUnit() {
            return new SyncUnit();
        }
    };

    public void sync( com.lanstar.model.system.TemplateFile sourceFile, com.lanstar.model.tenant.TemplateFile targetFile, TemplateFolder tenantFolder, IdentityContext targetContext ) {
        SyncUnit unit = getSyncUnit();
        unit.sourceFile = sourceFile;
        unit.targetFile = targetFile;
        unit.tenantFolder = tenantFolder;
        unit.targetContext = targetContext;
        unit.templateProp = sourceFile.getTemplateProp();

        unit.execute();
    }

    public abstract T getSyncUnit();
}
