/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：LocaleToCharsetMap.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

// Copyright (C) 1998-2001 by Jason Hunter <jhunter_AT_acm_DOT_org>.
// All rights reserved.  Use of this class is limited.
// Please see the LICENSE for more information.

package com.oreilly.servlet;

import java.util.*;

/** 
 * A mapping to determine the (somewhat arbitrarily) preferred charset for 
 * a given locale.  Supports all locales recognized in JDK 1.1.  This
 * class is used by the LocaleNegotiator.
 *
 * @see LocaleNegotiator
 *
 * @author <b>Jason Hunter</b>, Copyright &#169; 1998
 * @version 1.0, 98/09/18
 */
public class LocaleToCharsetMap {

  private static Hashtable map;

  static {
    map = new Hashtable();

    map.put("ar", "ISO-8859-6");
    map.put("be", "ISO-8859-5");
    map.put("bg", "ISO-8859-5");
    map.put("ca", "ISO-8859-1");
    map.put("cs", "ISO-8859-2");
    map.put("da", "ISO-8859-1");
    map.put("de", "ISO-8859-1");
    map.put("el", "ISO-8859-7");
    map.put("en", "ISO-8859-1");
    map.put("es", "ISO-8859-1");
    map.put("et", "ISO-8859-1");
    map.put("fi", "ISO-8859-1");
    map.put("fr", "ISO-8859-1");
    map.put("hr", "ISO-8859-2");
    map.put("hu", "ISO-8859-2");
    map.put("is", "ISO-8859-1");
    map.put("it", "ISO-8859-1");
    map.put("iw", "ISO-8859-8");
    map.put("ja", "Shift_JIS");
    map.put("ko", "EUC-KR");     // Requires JDK 1.1.6
    map.put("lt", "ISO-8859-2");
    map.put("lv", "ISO-8859-2");
    map.put("mk", "ISO-8859-5");
    map.put("nl", "ISO-8859-1");
    map.put("no", "ISO-8859-1");
    map.put("pl", "ISO-8859-2");
    map.put("pt", "ISO-8859-1");
    map.put("ro", "ISO-8859-2");
    map.put("ru", "ISO-8859-5");
    map.put("sh", "ISO-8859-5");
    map.put("sk", "ISO-8859-2");
    map.put("sl", "ISO-8859-2");
    map.put("sq", "ISO-8859-2");
    map.put("sr", "ISO-8859-5");
    map.put("sv", "ISO-8859-1");
    map.put("tr", "ISO-8859-9");
    map.put("uk", "ISO-8859-5");
    map.put("zh", "GB2312");
    map.put("zh_TW", "Big5");

  }

  /**
   * Gets the preferred charset for the given locale, or null if the locale
   * is not recognized.
   *
   * @param loc the locale
   * @return the preferred charset
   */
  public static String getCharset(Locale loc) {
    String charset;

    // Try for an full name match (may include country)
    charset = (String) map.get(loc.toString());
    if (charset != null) return charset;

    // If a full name didn't match, try just the language
    charset = (String) map.get(loc.getLanguage());
    return charset;  // may be null
  }
}
