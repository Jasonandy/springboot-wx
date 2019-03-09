package cn.ucaner.wx.app.data.elasticsearch.service;


/**
 * @author Jason
 */
public interface BulkProcessorService {

    /**
     * 插入数据通过id
     * @param index index
     * @param type  类型
     * @param id    id
     * @param jsonStr json 字符串
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
