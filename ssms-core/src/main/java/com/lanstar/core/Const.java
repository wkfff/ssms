/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：Const.java
 * 创建时间：2015-05-18
 * 创建用户：张铮彬
 */

package com.lanstar.core;

import com.lanstar.core.render.ViewType;

import java.io.File;

public interface Const {
    String RAPIDWARE_VERSION = "1.0";

    String DEFAULT_ENCODING = "UTF-8";

    boolean DEFAULT_DEV_MODE = false;

    String DEFAULT_URL_PARA_SEPARATOR = "-";

    ViewType DEFAULT_VIEW_TYPE = ViewType.FREE_MARKER;

    String DEFAULT_FREEMARKER_VIEW_EXTENSION = ".ftl";

    int DEFAULT_FREEMARKER_TEMPLATE_UPDATE_DELAY = 3600;

    String DEFAULT_TOKEN_NAME = "rapidware_token";

    int DEFAULT_SECONDS_OF_TOKEN_TIME_OUT = 900;            // 900 seconds ---> 15 minutes

    int MIN_SECONDS_OF_TOKEN_TIME_OUT = 300;                // 300 seconds ---> 5 minutes

    int DEFAULT_MAX_POST_SIZE = 1024 * 1024 * 10;       // 10M

    String DEFAULT_FILE_RENDER_BASE_PATH = File.separator + "download" + File.separator;
}
