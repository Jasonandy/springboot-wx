package cn.ucaner.wx.app.data.elasticsearch.service;

import cn.ucaner.wx.app.data.elasticsearch.model.Es;

import java.util.List;
import java.util.Map;

/**
 * @ClassName：QueryService
 * @Description： <p> QueryService  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/11 - 8:55
 * @Modify By：
 * @ModifyTime： 2019/3/11
 * @Modify marker：
 * @version V1.0
*/
public interface QueryService {

    /**
     * @param es
     * @param storeId
     * @param storeName
     * @param startDate
     * @param endDate
     * @return
     */
    List<Map<String, Object>> queryListFromES(Es es, int storeId, String storeName, String startDate, String endDate);

}
