package cn.ucaner.wx.app.data.elasticsearch.service;

import cn.ucaner.wx.app.data.elasticsearch.model.Es;

import java.util.List;
import java.util.Map;



/**
 * @author Jason
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
