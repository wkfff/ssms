/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：NullLoggerFactory.java
 * 创建时间：2015-05-18
 * 创建用户：张铮彬
 */

/**
 * Copyright (c) 2011-2015, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lanstar.common.log;

/**
 * NullLoggerFactory.
 */
public class NullLoggerFactory implements ILoggerFactory {

    public Logger getLogger( Class<?> clazz ) {
        return INSTANCE;
    }

    public Logger getLogger( String name ) {
        return INSTANCE;
    }

    private static final Logger INSTANCE = new Logger() {

        @Override
        public void info( String format, Object... arguments ) {

        }

        public void debug( String message ) {
        }

        public void debug( String message, Throwable t ) {
        }

        public void error( String message ) {
        }

        public void error( String message, Throwable t ) {
        }

        @Override
        public void error( String format, Object... arguments ) {

        }

        public void info( String message ) {
        }

        public void info( String message, Throwable t ) {
        }

        @Override
        public void debug( String format, Object... arguments ) {

        }

        public boolean isDebugEnabled() {
            return false;
        }

        public boolean isInfoEnabled() {
            return false;
        }

        public boolean isWarnEnabled() {
            return false;
        }

        public boolean isErrorEnabled() {
            return false;
        }

        public boolean isFatalEnabled() {
            return false;
        }

        public void warn( String message ) {
        }

        public void warn( String message, Throwable t ) {
        }

        @Override
        public void warn( String format, Object... arguments ) {

        }

        public void fatal( String message ) {
        }

        public void fatal( String message, Throwable t ) {
        }

        @Override
        public void fatal( String format, Object... arguments ) {

        }
    };
}
