package cn.ucaner.wx.app.service.generator.entity;

import cn.ucaner.wx.app.core.framework.mvc.entity.BaseEntity;

/**
* @Package：cn.ucaner.wx.app.service.generator.entity   
* @ClassName：Test   
* @Description：   <p> Test </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:43:31   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class Test extends BaseEntity {
	
	private static final long serialVersionUID = 6749282528553286704L;

	private String id;

    private String name;

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }
}