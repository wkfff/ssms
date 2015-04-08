/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：HandlerResult.java
 * 创建时间：2015-04-08
 * 创建用户：张铮彬
 */

package com.lanstar.core.handle;

public class HandlerResult {
    private boolean stop;
    private StopHandle stopHandle;

    public boolean isStop() {
        return stop;
    }

    public void setStop( boolean stop ) {
        this.stop = stop;
    }

    public StopHandle getStopHandle() {
        return stopHandle;
    }

    public void setStopHandle( StopHandle stopHandle ) {
        this.stopHandle = stopHandle;
    }

    public void handle(HandlerContext context){
        if (stopHandle != null) stopHandle.handle(context);
    }
}
