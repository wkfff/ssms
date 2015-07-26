/*
 * 项目名称：安全生产标准化管理系统(Safety Standardization Management System)
 * 版权申明：福州市磬基电子有限公司、福州市蓝石电子有限公司所有，未经许可不得在任何软件中以任何形式使用全部或部分代码，不得更改本项目的代码。
 * 文件名称：AdditionalIdentity.java
 * 创建时间：2015-07-26
 * 创建用户：张铮彬
 */

package com.lanstar.identity.interceptor;

import com.google.common.base.Strings;
import com.lanstar.core.ActionInvocation;
import com.lanstar.core.Controller;
import com.lanstar.core.aop.Interceptor;
import com.lanstar.identity.Identity;
import com.lanstar.identity.IdentityContextWrap;
import com.lanstar.identity.IdentityHolder;
import com.lanstar.identity.IdentityKit;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class IdentitySwitch implements Interceptor {
    static IdentityConfig getConfig( ActionInvocation ai ) {
        IdentityConfig config = ai.getMethod().getAnnotation( IdentityConfig.class );
        if ( config == null )
            config = ai.getController().getClass().getAnnotation( IdentityConfig.class );

        return config;
    }

    @Override
    public void intercept( final ActionInvocation ai ) {
        IdentityConfig config = getConfig( ai );

        Controller controller = ai.getController();
        IdentityHolder holder = IdentityKit.getIdentityHolder( controller );

        // 在开始执行前切换身份
        IdentityType type = IdentityType.ADDITIONAL;
        final List<String> fields = new ArrayList<>();
        if ( config != null ) {
            type = config.value();
            for ( String f : config.fields() ) {
                if ( Strings.isNullOrEmpty( f ) ) continue;
                fields.add( f );
            }
        }
        type.switchIdentity( holder );
        try {
            holder.runAs( new IdentityHolder.Action() {
                @Override
                public void invoke( Identity identity ) {
                    for ( String fieldName : fields ) {
                        Field field = getDeclaredField(ai.getController(), fieldName);
                        if ( field != null ) {
                            if ( field.getType().isAssignableFrom( IdentityContextWrap.class ) ) {
                                try {
                                    field.setAccessible( true );
                                    IdentityContextWrap wrap = (IdentityContextWrap) field.get( ai.getController() );
                                    wrap.switchIdentity( identity );
                                } catch ( IllegalAccessException ignored ) {
                                }
                            }
                        }
                    }
                    ai.invoke();
                }
            } );
        } finally {
            // 执行结束后切换回默认身份
            holder.resetDefaultIdentity();
        }
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     * @param object : 子类对象
     * @param fieldName : 父类中的属性名
     * @return 父类中的属性对象
     */

    private static Field getDeclaredField(Object object, String fieldName){
        Field field = null ;

        Class<?> clazz = object.getClass() ;

        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName) ;
                return field ;
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }

        return null;
    }
}
