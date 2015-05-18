/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SPTaskParameter.java
 * 创建时间：2015年5月14日 下午3:37:59
 * 创建用户：林峰
 */
package com.lanstar.plugin.scheduler.impl;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.lanstar.common.helper.StringHelper;
import com.lanstar.common.helper.XmlHelper;
import com.lanstar.db.StoredProcPara;

/**
 * 存储过程的自动调度任务的参数
 *
 */
public class SPTaskParameter {
    private String spName;
    private final List<StoredProcPara> parames = new ArrayList<StoredProcPara>();

    public String getSpName() {
        return this.spName;
    }

    public void setSpName( String spName ) {
        this.spName = spName;
    }

    public List<StoredProcPara> getParames() {
        return this.parames;
    }

    /**
     * 从配置节点中获得参数<br>
     * 例子如下：<br>
     * 
     * <pre>
     * <job id="job02" caption="测试任务2" cron="" type="SP" spname="TEST">
     *  <parameter id="p1" dtype="string" inout="IN"></parameter>
     *  <parameter id="p2" dtype="integer" inout="INOUT"></parameter>
     * </job>
     * </pre>
     * 
     * @param node
     * @return
     * @throws Exception
     */
    public static SPTaskParameter fromXmlNode( Element node ) throws Exception {
        final SPTaskParameter p = new SPTaskParameter();
        String spName = node.getAttribute( "value" ); // 获得存储过程名称
        if ( StringHelper.isBlank( spName ) ) throw new Exception(
                "需要指定存储过程的名称" );
        p.setSpName( spName );
        // 读取存储过程的参数
        XmlHelper.selectNodes( node, "parameter", new XmlHelper.INodeParser() {

            @Override
            public void parse( Element node ) {
                StoredProcPara sppara = new StoredProcPara();
                sppara.setParaName( node.getAttribute( "id" ) );
                sppara.setParaType( XmlHelper.getAttribute( node, "dtype",
                        "VARCHAR2" ) );
                sppara.setInout( XmlHelper.getAttribute( node, "inout", "in" ) );
                sppara.setRealValue( node.getTextContent() );

                p.getParames().add( sppara );

            }
        } );
        return p;
    }
}
