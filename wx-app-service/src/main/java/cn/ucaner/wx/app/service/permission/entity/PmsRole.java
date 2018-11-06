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
* @ClassName：PmsRole   
* @Description：   <p> 权限管理-角色</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:53:15   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class PmsRole extends PermissionBaseEntity {

	private static final long serialVersionUID = -1850274607153125161L;
	
	private String roleCode; // 角色编码: admin guest 
	private String roleName; // 角色名称

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * @Description: 角色名称
	 * @return String
	 * @Autor: Jason
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @Description: 角色名称
	 * @param roleName 
	 * @Autor: Jason
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public PmsRole() {

	}
}
