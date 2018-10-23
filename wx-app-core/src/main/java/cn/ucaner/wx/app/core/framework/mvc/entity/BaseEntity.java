package cn.ucaner.wx.app.core.framework.mvc.entity;

import java.io.Serializable;

/**
* @Package：cn.ucaner.wx.app.core.framework.mvc.entity   
* @ClassName：BaseEntity   
* @Description：   <p>  BaseEntity 实体基类</p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:50:34   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 2368417877456900821L;
	
	/**
	 * 主键ID
	 */
	protected String id;

	public BaseEntity() {
		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
}
