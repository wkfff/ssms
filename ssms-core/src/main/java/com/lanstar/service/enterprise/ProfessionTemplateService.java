/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：TemplateService.java
 * 创建时间：2015-05-12
 * 创建用户：张铮彬
 */

package com.lanstar.service.enterprise;

import com.lanstar.core.handle.db.impl.SystemDbContext;
import com.lanstar.service.TenantContext;

public class ProfessionTemplateService implements AutoCloseable {
    protected final int professionId;
    protected final SystemDbContext source;
    // 模板项目列表，该列表中的所有内容都是应该被克隆的，因此请把要克隆的东西放到这个列表中。
    private final ClonableList<TenantContext> list = new ClonableList<>();

    ProfessionTemplateService( int professionId, SystemDbContext systemDbContext ) {
        this.professionId = professionId;
        this.source = systemDbContext;

        // TODO: 添加需要被克隆的内容
        // 达标体系模板
        list.add( new StandardTemplateFolderSet( this ) );
    }

    /**
     * 获取专业的模板服务。
     *
     * @param professionId 专业id
     */
    public static ProfessionTemplateService forProfession( int professionId ) {
        return new ProfessionTemplateService( professionId, new SystemDbContext() );
    }

    /**
     * 克隆模板到目标租户中
     */
    public void cloneTo( TenantContext target ) {
        list.cloneTo( target );
    }
    public void synchronousTo( TenantContext target ) {
        list.cloneTo( target );
    }

    public int getProfessionId() {
        return professionId;
    }

    @Override
    public void close() throws Exception {
        source.close();
    }
}

