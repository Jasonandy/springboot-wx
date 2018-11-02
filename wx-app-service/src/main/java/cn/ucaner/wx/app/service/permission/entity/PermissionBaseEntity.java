package cn.ucaner.wx.app.service.permission.entity;

import java.io.Serializable;
import java.util.Date;

/**
* @Package：cn.ucaner.wx.app.service.permission.entity   
* @ClassName：PermissionBaseEntity   
* @Description：   <p> 权限基类</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:51:30   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class PermissionBaseEntity implements Serializable {

	private static final long serialVersionUID = -1164227376672815589L;
	
	private Long id;// 主键ID.
	
	private Integer version = 0;// 版本号默认为0
	
	private String status;// 状态 PublicStatusEnum  激活和未激活  
	
	
	//-------------------------------------------------------------
	private String creater;// 创建人.
	private Date createTime = new Date();// 创建时间.
	private String editor;// 修改人.
	private Date editTime;// 修改时间.
	private String remark;// 描述
	//--------------------------------------------------------------
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
