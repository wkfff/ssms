/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SqlsTaskParameter.java
 * 创建时间：2015年5月14日 上午9:41:22
 * 创建用户：林峰
 */
package com.lanstar.plugin.scheduler.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.lanstar.common.exception.WebException;
import com.lanstar.common.helper.BeanHelper;
import com.lanstar.common.helper.StringHelper;
import com.lanstar.common.helper.XmlHelper;
import com.lanstar.common.log.Logger;
import com.lanstar.db.DBSession;
import com.lanstar.db.StoredProcPara;
import com.lanstar.plugin.template.sql.ITemplateContext;
import com.lanstar.plugin.template.sql.SqlTemplate;

import freemarker.template.utility.ClassUtil;
import freemarker.template.utility.StringUtil;

/**
 * 自动执行SQL任务的参数
 *
 */
public class SqlsTaskParameter {
    final String id;
    final String caption;
    final boolean nolog;
    final List<ISqlsTask> params = new ArrayList<ISqlsTask>();

    public SqlsTaskParameter( Element e ) throws WebException {
        this.id = e.getAttribute( "id" );
        this.caption = e.getAttribute( "caption" );
        this.nolog = "true".equalsIgnoreCase( e.getAttribute( "nolog" ) );
        XmlHelper.selectChildrenNodes( e, new XmlHelper.INodeParser(){

            @Override
            public void parse( Element item ) {
                System.out.println( item.getNodeName() );
                if ( "variables".equalsIgnoreCase( item.getNodeName() ) ) SqlsTaskParameter.this.params
                .add( new VaribleParameter( item ) );
                else if ( "execsql".equalsIgnoreCase( item.getNodeName() ) ) SqlsTaskParameter.this.params
                        .add( new ExecSqlParameter( item ) );
                else if ( "importsql".equalsIgnoreCase( item.getNodeName() ) ) SqlsTaskParameter.this.params
                        .add( new ImportSqlParameter( item ) );
                else if ( "java".equalsIgnoreCase( item.getNodeName() ) ) {
                    ISqlsTask task = BeanHelper.newInstance( item.getAttribute( "class" ),ISqlsTask.class);
                    task.parseParemeter( item );
                    SqlsTaskParameter.this.params.add( task );
                } else if ( "sp".equalsIgnoreCase( item.getNodeName() ) ) SqlsTaskParameter.this.params
                        .add( new ExecSpParameter( item ) );
            }
        });
    }

    /**
     * 从其他数据库中迁移数据对对应结构的表
     *
     * @author chis(chis123@qq.com)
     */
    public static class ImportSqlParameter implements ISqlsTask {

        public ImportSqlParameter( Element item ) throws WebException {
            this.parseParemeter( item );
        }

        String sourceDatasource;
        String sourceSql;
        String targetSql;
        String caption;

        public void execute( ITemplateContext context ) throws WebException {
            String src_sql = SqlTemplate.parse( this.sourceSql, context );
            String target_sql = this.targetSql;
            if ( StringHelper.isBlank( src_sql )
                    || StringHelper.isBlank( target_sql ) ) return;

            Logger.getLogger( ImportSqlParameter.class ).debug( "\t开始运行:%s",  this.caption );
            try {
                this.transfer( context, this.sourceDatasource, src_sql,target_sql );
            } catch ( Exception e ) {

            } finally {
            }
            Logger.getLogger( ImportSqlParameter.class ).debug( "\t正常结束:%s",  this.caption );
        }

        private void transfer( final ITemplateContext context, String srcConn,
                String src_sql, final String target_sql ) throws WebException {
//            DBSession src = new DBSession( srcConn );
//            final Connection targetConn = DbUtil.getDbConnection();
//            try {
//                final Statement ps = targetConn.createStatement();
//                final long[] count = { 0L };
//                src.query( src_sql, new Object[] {},
//                        new IRowActionWithColumns() {
//
//                            public void process( ResultSet rs,
//                                    List<RowColumn> cols, int index )
//                                    throws Exception {
//                                MapVariableContext ctx = new MapVariableContext(
//                                        context );
//                                for ( RowColumn col : cols ) {
//                                    ctx.setVariable( col.getName()
//                                            .toUpperCase(), rs.getString( col
//                                            .getName() ) );
//                                }
//                                String sql = SqlTemplate.parse( target_sql,
//                                        ctx );
//                                ps.addBatch( sql );
//
//                                // LogUtil.debug(SqlsTaskParameter.class, index
//                                // + "\t. = " + sql);
//                                if ( count[0] % 24000 == 0 ) {
//                                    ps.executeBatch();
//                                }
//                                count[0]++;
//                            }
//                        }, 0, true );
//                ps.executeBatch();
//                LogUtil.debug( ImportSqlParameter.class, "导入记录数：" + count[0] );
//            } catch ( SQLException e ) {
//                e.printStackTrace();
//                throw new WebException( e );
//            } finally {
//                src.close();
//                try {
//                    targetConn.close();
//                } catch ( Exception e ) {
//                }
//                ;
//            }
        }

        public void parseParemeter( Element item ) throws WebException {
            this.caption = item.getAttribute( "caption" );
            this.sourceDatasource = item.getAttribute( "sourceDataSource" );
            this.sourceSql = XmlHelper.getProperty( item, "source" );
            this.targetSql = XmlHelper.getProperty( item, "target" );
        }

        @Override
        public String getCaption() {
            return this.caption;
        }
    }

    /**
     * 执行SQL命令
     *
     * @author chis(chis123@qq.com)
     */
    public static class ExecSqlParameter implements ISqlsTask {

        public ExecSqlParameter( Element item ) throws WebException {
            this.parseParemeter( item );
        }

        String sql;
        String caption;

        public void execute( ITemplateContext context ) throws WebException {
            String sql1 = SqlTemplate.parse( this.sql, context );
            if ( StringHelper.isBlank( sql1 ) ) return;
            Logger.getLogger( ExecSqlParameter.class ).debug( "\t开始运行:%s",  this.caption );
//            DB.execute( sql1 );
            Logger.getLogger( ExecSqlParameter.class ).debug( "\t正常结束:%s",  this.caption );
        }

        public void parseParemeter( Element item ) throws WebException {
            this.caption = item.getAttribute( "caption" );
            this.sql = item.getTextContent();
        }

        @Override
        public String getCaption() {
            return this.caption;
        }
    }

    /**
     * 设置环境变量
     *
     * @author chis(chis123@qq.com)
     */
    public static class VaribleParameter implements ISqlsTask {

        public VaribleParameter( Element item ) throws WebException {
            this.parseParemeter( item );
        }

        String sql;

        public void execute( final ITemplateContext context )
                throws WebException {
            String sql1 = SqlTemplate.parse( this.sql, context );
            if ( StringHelper.isBlank( sql1 )) return;

//            DB.queryFirst( sql1, new IRowActionWithColumns() {
//
//                public void process( ResultSet rs, List<RowColumn> cols,
//                        int index ) throws Exception {
//                    for ( RowColumn col : cols ) {
//                        context.setVariable( col.getName(),
//                                rs.getString( col.getName() ) );
//                    }
//                }
//            } );
            Logger.getLogger( VaribleParameter.class ).debug( "完成设置环境：%s", sql1 );
        }

        public void parseParemeter( Element e ) throws WebException {
            this.sql = e.getTextContent();
        }

        @Override
        public String getCaption() {
            return "设置环境变量";
        }
    }

    /**
     * 执行存储过程
     * 
     * @author chis(chis123@qq.com)
     */
    public static class ExecSpParameter implements ISqlsTask {
        private String caption;
        private String spName;
        private final List<StoredProcPara> params = new ArrayList<StoredProcPara>();

        public ExecSpParameter( Element item ) throws WebException {
            this.parseParemeter( item );
        }

        public void execute( ITemplateContext context ) throws WebException {
            for ( StoredProcPara p : this.params )
                p.setRealValue( SqlTemplate.parse( p.getRealValue(),context ) );
//            DB.execStoredProc( this.spName, this.params );
        }

        public void parseParemeter( Element item ) throws WebException {
            this.caption = item.getAttribute( "caption" );
            this.spName = item.getAttribute( "name" );
            // 读取存储过程的参数
//            XmlUtil.walkOverElementSons( item, "parameter",
//                    new XmlUtil.IElementPicker() {
//                        public void pick( Element item ) throws WebException {
//                            StoredProcPara sppara = new StoredProcPara();
//
//                            sppara.setParaName( item.getAttribute( "id" ) );
//                            sppara.setParaType( XmlUtil.getAttribute( item,
//                                    "dtype", "VARCHAR" ) );
//                            sppara.setInout( XmlUtil.getAttribute( item,
//                                    "inout", "in" ) );
//                            sppara.setRealValue( item.getTextContent() );
//
//                            ExecSpParameter.this.params.add( sppara );
//                        }
//                    } );
        }

        @Override
        public String getCaption() {
            return this.caption;
        }

    }

    public static interface ISqlsTask {

        public void execute( ITemplateContext context ) throws WebException;

        public void parseParemeter( Element item ) throws WebException;

        public String getCaption();
    }
}