package cn.ucaner.wx.app.core.utils.bean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
* @Package：cn.ucaner.wx.app.core.utils.bean   
* @ClassName：BeanUtils   
* @Description：   <p>  Bean进行操作的相关工具方法  </p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:46:13   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class BeanUtils {
	
	/**
	 * 该方法将给定的所有对象参数列表转换合并生成一个Map，对于同名属性，依次由后面替换前面的对象属性
	 * @param objs 对象列表
	 * @return 对于值为null的对象将忽略掉
	 */
	public static Map<String, Object> toMap(Object... objs) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object object : objs) {
			if (object != null) {
				map.putAll(toMap(object));
			}
		}
		return map;
	}

	/**
	 * 获取接口的泛型类型，如果不存在则返回null
	 * @param clazz
	 * @return
	 */
	public static Class<?> getGenericClass(Class<?> clazz) {
		Type t = clazz.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ( (ParameterizedType) t ).getActualTypeArguments();
			return ( (Class<?>) p[0] );
		}
		return null;
	}
	
	/**
	 * Test for Jason
	 */
	public static void main(String[] args) {
	}
}