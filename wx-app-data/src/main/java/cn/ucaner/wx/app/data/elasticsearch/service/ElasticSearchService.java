package cn.ucaner.wx.app.data.elasticsearch.service;


/**
 * @author Jason
 */
public interface ElasticSearchService {

    /**
     * @param index
     * @param type
     * @param id
     * @param jsonStr
     */
    void insertById(String index, String type, String id, String jsonStr);

    /**
     * @param index
     * @param type
     * @param id
     * @param jsonStr
     */
    void updateById(String index, String type, String id, String jsonStr);

    /**
     * @param index
     * @param type
     * @param id
     */
    void deleteById(String index, String type, String id);

}
