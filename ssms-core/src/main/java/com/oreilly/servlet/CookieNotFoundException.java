/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：CookieNotFoundException.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

// Copyright (C) 1998-2001 by Jason Hunter <jhunter_AT_acm_DOT_org>.
// All rights reserved.  Use of this class is limited.
// Please see the LICENSE for more information.

package com.oreilly.servlet;

/** 
 * Thrown to indicate a cookie does not exist.
 *
 * @see CookieParser
 *
 * @author <b>Jason Hunter</b>, Copyright &#169; 2000
 * @version 1.0, 2000/03/19
 */
public class CookieNotFoundException extends Exception {

  /**
   * Constructs a new CookieNotFoundException with no detail message.
   */
  public CookieNotFoundException() {
    super();
  }

  /**
   * Constructs a new CookieNotFoundException with the specified
   * detail message.
   *
   * @param s the detail message
   */
  public CookieNotFoundException(String s) {
    super(s);
  }
}
