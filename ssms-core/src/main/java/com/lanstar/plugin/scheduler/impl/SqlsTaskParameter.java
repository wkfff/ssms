/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SqlsTaskParameter.java
 * 创建时间：2015年5月14日 上午9:41:22
 * 创建用户：林峰
 */
package com.lanstar.plugin.scheduler.impl;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.lanstar.common.exception.WebException;
import com.lanstar.common.helper.BeanHelper;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.common.helper.XmlHelper;
import com.lanstar.common.log.Logger;
import com.lanstar.core.handle.db.HandlerDbContext;
import com.lanstar.core.handle.db.impl.SystemDbContext;
import com.lanstar.db.DBSession;
import com.lanstar.db.DbContext;
import com.lanstar.db.JdbcRecord;
import com.lanstar.db.JdbcRecordSet;
import com.lanstar.db.StoredProcPara;
import com.lanstar.plugin.db.SpringDbPlugin;
import com.lanstar.plugin.template.sql.SqlTemplate;
import com.lanstar.plugin.template.sql.TemplateContext;

/**
 * 自动执行SQL任务的参数
 *
 */
public class SqlsTaskParameter {
    final String id;
    final String caption;
    final boolean nolog;
    final List<ISqlsTask> params = new ArrayList<ISqlsTask>();
    HandlerDbContext SYSTEM_DB = new SystemDbContext();
    Iterable<DbContext> TENANT_DB = null;

    public Iterable<DbContext> getTenantDB() {
        SpringDbPlugin db = new SpringDbPlugin();
        db.startup();
        Iterable<DbContext> dbs = db.getDbContexts();
        return dbs;
    }

    public SqlsTaskParameter( Element e ) throws WebException {
        this.id = e.getAttribute( "id" );
        this.caption = e.getAttribute( "caption" );
        this.nolog = "true".equalsIgnoreCase( e.getAttribute( "nolog" ) );

        XmlHelper.selectChildrenNodes( e, new XmlHelper.INodeParser() {

            @Override
            public void parse( Element item ) {
                if ( "variables".equalsIgnoreCase( item.getNodeName() ) ) SqlsTaskParameter.this.params
                .add( new VaribleParameter( item ) );
                else if ( "execsql".equalsIgnoreCase( item.getNodeName() ) ) SqlsTaskParameter.this.params
                .add( new ExecSqlParameter( item ) );
                else if ( "importsql".equalsIgnoreCase( item.getNodeName() ) ) SqlsTaskParameter.this.params
                .add( new ImportSqlParameter( item ) );
                else if ( "java".equalsIgnoreCase( item.getNodeName() ) ) {
                    ISqlsTask task = BeanHelper.newInstance( item.getAttribute( "class" ), ISqlsTask.class );
                    task.parseParemeter( item );
                    SqlsTaskParameter.this.params.add( task );
                } else if ( "sp".equalsIgnoreCase( item.getNodeName() ) ) SqlsTaskParameter.this.params
                .add( new ExecSpParameter( item ) );
            }
        } );
    }

    public void execSql( String ds, String sql, TemplateContext context, JdbcRecord r ) {
        TemplateContext ctx = new TemplateContext( context.getVariables() );
        for ( String key : r.keySet() ) {
            ctx.setVariable( key, r.getString( key ) );
        }
        sql = SqlTemplate.parse( sql, ctx );

        this.execSql( ds, sql, context );
    }

    public void execSql( String ds, String sql, TemplateContext context ) {
        sql = SqlTemplate.parse( sql, context );

        // 所有租户数据库
        if ( ds.equals( "TENANT" ) ) {
            if ( this.TENANT_DB == null ) this.TENANT_DB = this.getTenantDB();
            for ( DbContext dbctx : this.TENANT_DB ) {
                try {
                    Logger.getLogger( ExecSqlParameter.class ).debug( "\t开始执行SQL:%s", sql );
                    try ( DBSession dbSession = dbctx.createDbSession() ) {
                        dbSession.execute( sql, new Object[] {} );
                    }
                } catch ( Exception e ) {
                    Logger.getLogger( ExecSqlParameter.class ).debug( "\t执行SQL出错:%s", e.getMessage() );
                }
            }
        } else if ( ds.equals( "SYSTEM" ) ) {
            this.SYSTEM_DB.execute( sql, new Object[] {} );
            try {
                this.SYSTEM_DB.close();
            } catch ( Exception e ) {

            }
        }
    }

    /**
     * 从其他数据库中迁移数据
     *
     */
    public class ImportSqlParameter implements ISqlsTask {

        public ImportSqlParameter( Element item ) throws WebException {
            this.parseParemeter( item );
        }

        String sourceSql;
        String targetSql;
        String caption;
        String srcDs;
        String targetDs;

        @Override
        public void execute( TemplateContext context ) throws WebException {
            String src_sql = SqlTemplate.parse( this.sourceSql, context );
            String target_sql = this.targetSql;
            if ( StringHelper.isBlank( src_sql ) || StringHelper.isBlank( target_sql ) ) return;

            Logger.getLogger( ImportSqlParameter.class ).debug( "\t开始运行:%s", this.caption );
            try {
                this.transfer( context, this.srcDs, this.targetDs, src_sql, target_sql );
            } catch ( Exception e ) {

            } finally {
            }
            Logger.getLogger( ImportSqlParameter.class ).debug( "\t正常结束:%s", this.caption );
        }

        private void transfer( final TemplateContext context, String srcDs, String targetDs, String src_sql,
                final String target_sql ) throws WebException {
            if ( srcDs.equals( "SYSTEM" ) ) {
                JdbcRecordSet rs = SqlsTaskParameter.this.SYSTEM_DB.getDBSession().query( src_sql, new Object[] {} );
                for ( JdbcRecord r : rs ) {
                    SqlsTaskParameter.this.execSql( targetDs, target_sql, context, r );
                }
            } else if ( srcDs.equals( "TENANT" ) ) {

            }
        }

        @Override
        public void parseParemeter( Element item ) throws WebException {
            this.caption = item.getAttribute( "caption" );
            this.sourceSql = XmlHelper.getProperty( item, "source" );
            this.targetSql = XmlHelper.getProperty( item, "target" );
            Element sub = XmlHelper.getSubNode( item, "source" );
            this.srcDs = XmlHelper.getAttribute( sub, "ds", "SYSTEM" );
            sub = XmlHelper.getSubNode( item, "target" );
            this.targetDs = XmlHelper.getAttribute( sub, "ds", "SYSTEM" );
        }

        @Override
        public String getCaption() {
            return this.caption;
        }
    }

    /**
     * 执行SQL命令
     *
     */
    public class ExecSqlParameter implements ISqlsTask {
        String sql;
        String caption;
        String ds;

        public ExecSqlParameter( Element item ) throws WebException {
            this.parseParemeter( item );
        }

        @Override
        public void execute( TemplateContext context ) throws WebException {
            if ( StringHelper.isBlank( this.sql ) ) return;
            Logger.getLogger( ExecSqlParameter.class ).debug( "\t开始运行:%s", this.caption );
            SqlsTaskParameter.this.execSql( this.ds, this.sql, context );
            Logger.getLogger( ExecSqlParameter.class ).debug( "\t正常结束:%s", this.caption );
        }

        @Override
        public void parseParemeter( Element item ) throws WebException {
            this.caption = item.getAttribute( "caption" );
            this.sql = item.getTextContent();
            this.ds = item.getAttribute( "ds" );
        }

        @Override
        public String getCaption() {
            return this.caption;
        }
    }

    /**
     * 设置环境变量
     *
     */
    public class VaribleParameter implements ISqlsTask {
        String sql;
        String ds;

        public VaribleParameter( Element item ) throws WebException {
            this.parseParemeter( item );
        }

        @Override
        public void execute( final TemplateContext context ) throws WebException {
            String sqlStr = SqlTemplate.parse( this.sql, context );
            if ( StringHelper.isBlank( sqlStr ) ) return;
            if ( this.ds.equals( "SYSTEM" ) ) {

                JdbcRecord r = SqlsTaskParameter.this.SYSTEM_DB.first( sqlStr, new Object[] {} );
                try {
                    SqlsTaskParameter.this.SYSTEM_DB.close();
                } catch ( Exception e ) {
                }
                for ( String key : r.keySet() ) {
                    context.setVariable( key, r.getString( key ) );
                    Logger.getLogger( VaribleParameter.class ).debug( "设置值：%s", key + "__" + r.getString( key ) );
                }
            }
            Logger.getLogger( VaribleParameter.class ).debug( "完成设置环境：%s", sqlStr );
        }

        @Override
        public void parseParemeter( Element e ) throws WebException {
            this.sql = e.getTextContent();
            this.ds = e.getAttribute( "ds" );
        }

        @Override
        public String getCaption() {
            return "设置环境变量";
        }
    }

    /**
     * 执行存储过程
     *
     */
    public class ExecSpParameter implements ISqlsTask {
        private String caption;
        private String spName;
        private String ds;
        private final List<StoredProcPara> params = new ArrayList<StoredProcPara>();

        public ExecSpParameter( Element item ) throws WebException {
            this.parseParemeter( item );
        }

        @Override
        public void execute( TemplateContext context ) throws WebException {
            List<Object> paras = new ArrayList<Object>();
            for ( StoredProcPara p : this.params ) {
                paras.add( SqlTemplate.parse( p.getRealValue(), context ) );
            }

            if ( this.ds.equals( "TENANT" ) ) {
                if ( SqlsTaskParameter.this.TENANT_DB == null ) SqlsTaskParameter.this.TENANT_DB = SqlsTaskParameter.this
                        .getTenantDB();
                for ( DbContext dbctx : SqlsTaskParameter.this.TENANT_DB ) {
                    try {
                        Logger.getLogger( ExecSqlParameter.class ).debug( "\t开始执行存储过程:%s", this.spName );

                        try ( DBSession dbSession = dbctx.createDbSession() ) {
                            dbSession.callProcedure( this.spName, paras.toArray() );
                        }
                    } catch ( Exception e ) {
                        Logger.getLogger( ExecSqlParameter.class ).debug( "\t执行存储过程出错:%s", e.getMessage() );
                    }
                }
            } else if ( this.ds.equals( "SYSTEM" ) ) {

                SqlsTaskParameter.this.SYSTEM_DB.getDBSession().callProcedure( this.spName, paras.toArray() );
                try {
                    SqlsTaskParameter.this.SYSTEM_DB.getDBSession().close();
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void parseParemeter( Element item ) throws WebException {
            this.caption = item.getAttribute( "caption" );
            this.spName = item.getAttribute( "name" );
            this.ds = item.getAttribute( "ds" );
            // 读取存储过程的参数
            XmlHelper.selectNodes( item, "parameter", new XmlHelper.INodeParser() {

                @Override
                public void parse( Element node ) {
                    StoredProcPara sppara = new StoredProcPara();
                    sppara.setParaName( node.getAttribute( "id" ) );
                    sppara.setParaType( XmlHelper.getAttribute( node, "dtype", "VARCHAR" ) );
                    sppara.setInout( XmlHelper.getAttribute( node, "inout", "in" ) );
                    sppara.setRealValue( node.getTextContent() );
                    ExecSpParameter.this.params.add( sppara );
                }
            } );
        }

        @Override
        public String getCaption() {
            return this.caption;
        }

    }

    public static interface ISqlsTask {

        public void execute( TemplateContext context ) throws WebException;

        public void parseParemeter( Element item ) throws WebException;

        public String getCaption();
    }
}