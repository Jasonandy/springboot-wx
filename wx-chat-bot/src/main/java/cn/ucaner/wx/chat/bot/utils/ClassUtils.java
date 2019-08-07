package cn.ucaner.wx.chat.bot.utils;

import java.lang.reflect.Method;

/**
 * @ClassName：ClassUtils
 * @Description： <p> ClassUtils </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:40
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class ClassUtils {

    /**
     * 通过方法名调用方法
     * @param clazz 调用的Class
     * @param methodName 调用的方法名
     * @param param 调用的参数
     */
    public static <T> void invokeMethod(Class<?> clazz, String methodName, T param) {
        try {
            Method m1 = clazz.getDeclaredMethod(methodName, new Class[]{param.getClass()});
            m1.invoke(clazz.newInstance(), new Object[]{param});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
