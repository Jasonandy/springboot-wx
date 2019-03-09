package cn.ucaner.wx.app.data.elasticsearch.model;


public class Es {

    private String index;


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
