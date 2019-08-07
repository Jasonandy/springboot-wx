package cn.ucaner.wx.chat.bot.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.util.Iterator;


/**
 * @ClassName：XmlUtils
 * @Description： <p> XmlUtils </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:44
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class XmlUtils {



    public static <T> T toObject(String xml,Class<T> clazz) throws Exception {
        T t = clazz.newInstance();
        Document doc = DocumentHelper.parseText(xml);
        Element root = doc.getRootElement();
        Iterator iterator = root.elementIterator();
        while (iterator.hasNext()){
            Element el = (Element)iterator.next();
            String name = el.getName();
            Object data = el.getData();
            try {
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                field.set(t, data);
            }catch (NoSuchFieldException e){
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }

}
