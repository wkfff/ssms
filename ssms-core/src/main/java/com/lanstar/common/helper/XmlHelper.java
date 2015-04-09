/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：XmlHelper.java
 * 创建时间：2015-03-31
 * 创建用户：张铮彬
 */
package com.lanstar.common.helper;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * XML工具类
 */
public class XmlHelper {

    /**
     * 获取指定输入流的XML的根节点
     */
    public static Element getDocumentElement( InputStream stream ) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder().parse( stream ).getDocumentElement();
    }

    public static Element getDocumentElement( File file ) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder().parse( file ).getDocumentElement();
    }

    /**
     * 获得XML节点的去除头尾空白字符值
     *
     * @param item XML节点
     * @param tag  属性名
     *
     * @return 属性值，并去除头尾的空白字符
     */
    public static String getTrimAttribute( Element item, String tag ) {
        String v = item.getAttribute( tag );
        if ( v == null || v.length() == 0 ) return v;
        else return v.trim();
    }

    /**
     * 解析文件的指定节点
     */
    public static INodeParser selectNodes( String fileName, String express, INodeParser actor ) {
        File file = new File( fileName );
        Asserts.check( !FileHelper.fileExists( fileName ), "指定的XML文件[%s]非法或不存在", fileName );

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        Element root;
        try {
            root = factory.newDocumentBuilder().parse( file ).getDocumentElement();
        } catch ( Throwable e ) {
            throw new RuntimeException( "XML文件[" + fileName + "]读写错误", e );
        }

        return selectNodes( root, express, actor );
    }

    /**
     * 利用XSL语法选择节点来处理
     *
     * @param e       要处理的XML节点
     * @param express 查找的XSL表达式
     * @param actor   行处理函数
     */
    public static INodeParser selectNodes( Element e, String express, INodeParser actor ) {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        NodeList nodes;
        try {
            nodes = (NodeList) xpath.evaluate( express, e, XPathConstants.NODESET );
        } catch ( XPathExpressionException e1 ) {
            throw new IllegalArgumentException( "没有找到对应规则[" + express + "]的XML阶段", e1 );
        }

        for ( int i = 0; i < nodes.getLength(); i++ ) {
            Node node = nodes.item( i );
            if ( node.getNodeType() != Node.ELEMENT_NODE ) continue;
            try {
                actor.parse( (Element) node );
            } catch ( Throwable e1 ) {
                throw new RuntimeException( e1 );
            }

        }
        return actor;
    }

    /**
     * 遍历子节点
     */
    public static INodeParser selectChildrenNodes( Element node, INodeParser actor ) {
        NodeList nodes = node.getChildNodes();
        for ( int i = 0; i < nodes.getLength(); i++ ) {
            Node n = nodes.item( i );
            if ( n.getNodeType() == Node.ELEMENT_NODE ) {
                try {
                    actor.parse( (Element) n );
                } catch ( Throwable e1 ) {
                    throw new RuntimeException( e1 );
                }
            }
        }
        return actor;
    }

    /**
     * 获得指定的一个子节点，如果有多个的话，则取第一个，其他忽略
     *
     * @param node   父节点
     * @param subtag 子节点名称
     *
     * @return 第一个查找到的节点
     */
    public static Element getSubNode( Element node, String subtag ) {
        NodeList nodes = node.getChildNodes();
        for ( int i = 0; i < nodes.getLength(); i++ ) {
            Node n = nodes.item( i );
            if ( n.getNodeType() == Node.ELEMENT_NODE && subtag.equals( n.getNodeName() ) ) return (Element) n;
        }
        return null;
    }

    /**
     * 获得文本内容，并删除头头尾尾的空白字符
     */
    public static String getTrimTextContext( Element n ) {
        String text = n.getTextContent();
        if ( text == null || text.length() == 0 ) return text;
        return text.replaceAll( "^\\s", "" ).replaceAll( "\\s$", "" );
    }

    /**
     * 获得节点属性，如果有属性则找属性，如果没有则找子节点
     */
    public static String getProperty( Element node, String tagname ) {
        String v = getTrimAttribute( node, tagname );
        if ( v == null || v.length() == 0 ) {
            Element sub = XmlHelper.getSubNode( node, tagname );
            if ( sub != null ) v = getTrimTextContext( sub );
        }
        return v;
    }

    /**
     * 处理所有的子节点
     */
    public static void getSubNodes( Element node, INodeParser np ) {
        NodeList nodes = node.getChildNodes();
        for ( int i = 0; i < nodes.getLength(); i++ ) {
            Node n = nodes.item( i );
            if ( n.getNodeType() != Node.ELEMENT_NODE ) continue;
            np.parse( (Element) n );
        }
    }

    /**
     * 将XML节点转换为字符串
     */
    public static String toXmlString( Element node, String encoding ) {
        String s;
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            /** 使用GB2312编码 */
            transformer.setOutputProperty( OutputKeys.ENCODING, encoding );
            Source source = new DOMSource( node );
            /** 直接输出到控制台 */
            //Result output = new StreamResult( System.out );
            StringWriter out = new StringWriter();
            Result output = new StreamResult( out );
            transformer.transform( source, output );
            out.flush();
            s = out.toString();
        } catch ( Exception ex ) {
            throw new RuntimeException( ex );
        }
        return s;
    }

    /**
     * 每个节点的处理接口定义
     */
    public interface INodeParser {
        void parse( Element node );
    }
}

