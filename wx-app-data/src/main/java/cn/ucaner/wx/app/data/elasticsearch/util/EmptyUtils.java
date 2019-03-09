package cn.ucaner.wx.app.data.elasticsearch.util;

import java.util.List;
import java.util.Map;

/**
 * @author Jason
 */
public class EmptyUtils {

    /**
     * @param s
     * @return
     */
    public static boolean isEmpty(Object s) {
        if (s == null) {
            return true;
        }
        if ((s instanceof String) && (((String)s).trim().length() == 0)) {
            return true;
        }
        if (s instanceof Map) {
            return ((Map<?, ?>)s).isEmpty();
        }
        if (s instanceof List) {
            return ((List<?>)s).isEmpty();
        }
        if (s instanceof Object[]) {
            return (((Object[])s).length == 0);
        }
        return false;
    }

}
