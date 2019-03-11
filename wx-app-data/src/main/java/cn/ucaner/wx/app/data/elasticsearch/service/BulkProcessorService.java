package cn.ucaner.wx.app.data.elasticsearch.service;

/**
 * @ClassName：BulkProcessorService
 * @Description： <p> BulkProcessorService  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/11 - 8:54
 * @Modify By：
 * @ModifyTime： 2019/3/11
 * @Modify marker：
 * @version V1.0
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
