package cn.ucaner.wx.app.data.elasticsearch.service;

/**
 * @ClassName：ElasticSearchService
 * @Description： <p> ElasticSearchService  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/11 - 8:54
 * @Modify By：
 * @ModifyTime： 2019/3/11
 * @Modify marker：
 * @version V1.0
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
