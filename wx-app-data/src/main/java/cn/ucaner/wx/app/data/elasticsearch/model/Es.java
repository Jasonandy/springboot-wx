package cn.ucaner.wx.app.data.elasticsearch.model;

/**
 * @ClassName：Es
 * @Description： <p> Es  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/11 - 8:53
 * @Modify By：
 * @ModifyTime： 2019/3/11
 * @Modify marker：
 * @version V1.0
*/
public class Es {

    /**
     * 索引
     */
    private String index;

    /**
     * 类型
     */
    private String type;


    public Es(String index, String type) {
        this.index = index;
        this.type = type;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }
}
