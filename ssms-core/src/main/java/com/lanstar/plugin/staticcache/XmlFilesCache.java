/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：XmlFilesCache.java
 * 创建时间：2015-04-09
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.staticcache;

import com.lanstar.common.helper.XmlHelper;
import com.lanstar.common.log.LogHelper;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public abstract class XmlFilesCache<T> extends FileCache<T> {
    @Override
    protected String getFileExtensions() {
        return ".xml";
    }

    @Override
    protected void parseFile( Map<String, T> pools, File file ) {
        Element root;
        try {
            root = XmlHelper.getDocumentElement( file );
        } catch ( ParserConfigurationException | IOException | SAXException e ) {
            LogHelper.error( XmlFilesCache.class, e, "解析XML文件[%s]出错", file.getName() );
            return;
        }
        parseXml( root, pools, file );
    }

    protected abstract void parseXml( Element root, Map<String, T> pools, File file );
}
