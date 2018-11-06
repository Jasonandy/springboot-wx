/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.service.permission.entity;

/**
* @Package：cn.ucaner.wx.app.service.permission.entity   
* @ClassName：PmsMenu   
* @Description：   <p> 菜单实体</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:51:45   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class PmsMenu extends PermissionBaseEntity {
	
	private static final long serialVersionUID = 1L;

	/** 菜单名称 **/
	private String name;

	/** 菜单地址 **/
	private String url;

	/** 菜单编号（用于显示时排序） **/
	private String number;

	/** 是否为叶子节点 **/
	private String isLeaf;

	/** 菜单层级 **/
	private Long level;

	/** 父节点:一级菜单为0 **/
	private PmsMenu parent;

	/** 目标名称 **/
	private String targetName;

	public PmsMenu() {
		super();
	}

	/** 菜单名称 **/
	public String getName() {
		return name;
	}

	/** 菜单名称 **/
	public void setName(String name) {
		this.name = name;
	}

	/** 菜单地址 **/
	public String getUrl() {
		return url;
	}

	/** 菜单地址 **/
	public void setUrl(String url) {
		this.url = url;
	}

	/** 菜单编号（用于显示时排序） **/
	public String getNumber() {
		return number;
	}

	/** 菜单编号（用于显示时排序） **/
	public void setNumber(String number) {
		this.number = number;
	}

	/** 是否为叶子节点 **/
	public String getIsLeaf() {
		return isLeaf;
	}

	/** 是否为叶子节点 **/
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	/** 菜单层级 **/
	public Long getLevel() {
		return level;
	}

	/** 菜单层级 **/
	public void setLevel(Long level) {
		this.level = level;
	}

	/** 父节点:一级菜单为0 **/
	public PmsMenu getParent() {
		return parent;
	}

	/** 父节点:一级菜单为0 **/
	public void setParent(PmsMenu parent) {
		this.parent = parent;
	}

	/** 目标名称 **/
	public String getTargetName() {
		return targetName;
	}

	/** 目标名称 **/
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

}
