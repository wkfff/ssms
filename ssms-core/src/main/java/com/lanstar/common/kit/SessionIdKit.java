/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：SessionIdKit.java
 * 创建时间：2015-05-21
 * 创建用户：张铮彬
 */

package com.lanstar.common.kit;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Random;

public class SessionIdKit {

    protected static Random random;
    private static boolean weakRandom;
    private static volatile Object lock = new Object();

    private static final SessionIdKit me = new SessionIdKit();

    private SessionIdKit() {
        try {
            // This operation may block on some systems with low entropy. See
            // this page
            // for workaround suggestions:
            // http://docs.codehaus.org/display/JETTY/Connectors+slow+to+startup
            System.out.println( "Init SecureRandom." );
            random = new SecureRandom();
            weakRandom = false;
        } catch ( Exception e ) {
            System.err.println( "Could not generate SecureRandom for session-id randomness" );
            random = new Random();
            weakRandom = true;
        }
    }

    public static SessionIdKit me() {
        return me;
    }

    public String generate( HttpServletRequest request ) {
        synchronized ( lock ) {
            String id = null;
            while ( id == null || id.length() == 0 ) {    //)||idInUse(id))
                long r0 = weakRandom ?
                        (hashCode() ^ Runtime.getRuntime().freeMemory() ^ random.nextInt() ^ (
                                ((long) request.hashCode()) << 32)) :
                        random.nextLong();
                long r1 = random.nextLong();
                if ( r0 < 0 ) r0 = -r0;
                if ( r1 < 0 ) r1 = -r1;
                id = Long.toString( r0, 36 ) + Long.toString( r1, 36 );
            }
            return id;
        }
    }
}