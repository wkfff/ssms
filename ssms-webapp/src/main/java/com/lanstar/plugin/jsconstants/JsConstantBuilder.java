/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：JsConstantBuilder.java
 * 创建时间：2015-06-12
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.jsconstants;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.io.Closeables;
import com.google.common.io.Files;
import com.lanstar.common.log.Logger;
import com.lanstar.plugin.IPlugin;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsConstantBuilder implements IPlugin {
    private static final JsConstantBuilder me = new JsConstantBuilder();
    private final Logger log = Logger.getLogger( JsConstantBuilder.class );
    private String filePath = "constants.js";
    private List<ParametersGetter<?>> getters = new ArrayList<>();
    private final Function<ParametersGetter<?>, String> getterStringFunction = new Function<ParametersGetter<?>, String>() {
        @Override
        public String apply( ParametersGetter<?> input ) {
            return "var " + input.getName() + " = " + JSON.toJSONString( input.getParameters() );
        }
    };

    private JsConstantBuilder() {
    }

    public static JsConstantBuilder me() {
        return me;
    }

    public JsConstantBuilder add( ParametersGetter<?> getter ) {
        getters.add( getter );
        return this;
    }

    public boolean build() {
        List<String> list = Lists.transform( getters, getterStringFunction );
        try {
            compress( list, Files.newWriter( new File( filePath ), Charsets.UTF_8 ) );
            return true;
        } catch ( IOException e ) {
            log.error( "生成js文件的时候发生了错误...", e );
            return false;
        }
    }

    @Override
    public boolean start() {
        log.debug( "正在生成常量脚本..." );
        boolean success = build();
        if ( success ) log.debug( "生成常量脚本完成..." );
        return success;
    }

    @Override
    public boolean stop() {
        return true;
    }

    public String getFilePath() {
        return filePath;
    }

    public JsConstantBuilder setFilePath( String filePath ) {
        this.filePath = filePath;
        return this;
    }

    private void compress( List<String> codes, Writer writer ) throws IOException {
        Reader in;
        try {
            in = new StringReader( Joiner.on( ";" ).join( codes ) );
            JavaScriptCompressor compressor = new JavaScriptCompressor( in, new ErrorReporter() {
                public void warning( String message, String sourceName,
                        int line, String lineSource, int lineOffset ) {
                    if ( line < 0 ) {
                        System.err.println( "/n[WARNING] " + message );
                    } else {
                        System.err.println( "/n[WARNING] " + line + ':' + lineOffset + ':' + message );
                    }
                }

                public void error( String message, String sourceName,
                        int line, String lineSource, int lineOffset ) {
                    if ( line < 0 ) {
                        System.err.println( "/n[ERROR] " + message );
                    } else {
                        System.err.println( "/n[ERROR] " + line + ':' + lineOffset + ':' + message );
                    }
                }

                public EvaluatorException runtimeError( String message, String sourceName,
                        int line, String lineSource, int lineOffset ) {
                    error( message, sourceName, line, lineSource, lineOffset );
                    return new EvaluatorException( message );
                }
            } );
            compressor.compress( writer, -1, true, false, false, false );
        } finally {
            Closeables.close( writer, true );
        }
    }
}

