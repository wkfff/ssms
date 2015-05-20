/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：FileRenamePolicy.java
 * 创建时间：2015-05-19
 * 创建用户：张铮彬
 */

// Copyright (C) 2002 by Jason Hunter <jhunter_AT_acm_DOT_org>.
// All rights reserved.  Use of this class is limited.
// Please see the LICENSE for more information.

package com.oreilly.servlet.multipart;

import java.io.*;

/**
 * An interface to provide a pluggable file renaming policy, particularly
 * useful to handle naming conflicts with an existing file.
 * 
 * @author Jason Hunter
 * @version 1.0, 2002/04/30, initial revision, thanks to Changshin Lee for
 *                           the basic idea
 */
public interface FileRenamePolicy {
  
  /**
   * Returns a File object holding a new name for the specified file.
   *
   * @see FilePart#writeTo(File fileOrDirectory)
   */
  public File rename( File f );

}
