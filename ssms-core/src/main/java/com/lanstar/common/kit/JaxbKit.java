/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JaxbKit.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.common.kit;

import com.lanstar.common.log.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

public class JaxbKit {

    protected final static Logger LOG = Logger.getLogger( JaxbKit.class );

    /**
     * string -> object
     */
    @SuppressWarnings("unchecked")
    public static <T> T unmarshal( String src, Class<T> clazz ) {
        T result;
        try {
            Unmarshaller avm = JAXBContext.newInstance( clazz ).createUnmarshaller();
            result = (T) avm.unmarshal( new StringReader( src ) );
        } catch ( JAXBException e ) {
            throw new RuntimeException( e );
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T unmarshal( File xmlFile, Class<T> clazz ) {
        T result;
        try {
            Unmarshaller avm = JAXBContext.newInstance( clazz ).createUnmarshaller();
            result = (T) avm.unmarshal( xmlFile );
        } catch ( JAXBException e ) {
            throw new RuntimeException( e );
        }
        return result;
    }

    /**
     * object -> string
     */
    public static String marshal( Object jaxbElement ) {
        StringWriter sw;
        try {
            Marshaller fm = JAXBContext.newInstance( jaxbElement.getClass() ).createMarshaller();
            fm.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
            sw = new StringWriter();
            fm.marshal( jaxbElement, sw );
        } catch ( JAXBException e ) {
            throw new RuntimeException( e );
        }
        return sw.toString();
    }
}