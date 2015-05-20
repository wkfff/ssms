/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：ExceededSizeException.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

// Copyright (C) 2007 by Jason Hunter <jhunter_AT_acm_DOT_org>.
// All rights reserved.  Use of this class is limited.
// Please see the LICENSE for more information.

package com.oreilly.servlet.multipart;

import java.io.IOException;

/** 
 * Thrown to indicate an upload exceeded the maximum size.
 *
 * @see MultipartParser
 *
 * @author <b>Jason Hunter</b>, Copyright &#169; 2007
 * @version 1.0, 2007/04/11
 */
public class ExceededSizeException extends IOException {

  /**
   * Constructs a new ExceededSizeException with no detail message.
   */
  public ExceededSizeException() {
    super();
  }

  /**
   * Constructs a new ExceededSizeException with the specified
   * detail message.
   *
   * @param s the detail message
   */
  public ExceededSizeException(String s) {
    super(s);
  }
}
