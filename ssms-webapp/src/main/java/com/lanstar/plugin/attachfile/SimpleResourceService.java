/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SimpleResourceService.java
 * 创建时间：2015-05-27
 * 创建用户：张铮彬
 */

package com.lanstar.plugin.attachfile;

import java.io.*;

public class SimpleResourceService implements ResourceService {
    private final FileSystemResource baseFolder;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public SimpleResourceService( String baseFolder ) {
        this.baseFolder = new FileSystemResource( baseFolder+"/" );
        if (!this.baseFolder.exists()) this.baseFolder.getFile().mkdirs();
    }

    /**
     * 根据定位符获取资源
     *
     * @param location 相对路径
     */
    @Override
    public InputStreamSource getResource( String location ) {
        return baseFolder.createRelative( location );
    }

    /**
     * 将资源保存到定位符表示的路径中
     *
     * @param source   资源
     * @param location 相对路径
     *
     * @throws IOException
     */
    @Override
    public long saveResource( InputStreamSource source, String location ) throws IOException {
        FileSystemResource file = (FileSystemResource) this.baseFolder.createRelative( location );
        if (!file.exists()) {
            File tmp = file.getFile();
            tmp.getParentFile().mkdirs();
            tmp.createNewFile();
        }
        byte[] buffer = new byte[1024];
        long length = 0;
        try ( InputStream input = source.getInputStream(); OutputStream output = file.getOutputStream() ) {
            int count;
            while ( (count = input.read( buffer )) > 0 ) {
                output.write( buffer, 0, count );
                length+=count;
            }
        }
        return length;
    }

    @Override
    public void removeResouce( String location ) {
        FileSystemResource file = (FileSystemResource) this.baseFolder.createRelative( location );
        if (!file.exists()) {
            throw new RuntimeException( "文件不存在" );
        }
        if (file.getFile().delete() == false)
            throw new RuntimeException( "文件删除失败" );
    }

    @Override
    public String getServerCode() {
        // TODO:
        return "f01";
    }
}