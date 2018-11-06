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
* @ClassName：PmsRolePermission   
* @Description：   <p> 权限管理-角色权限关联表  </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:53:31   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class PmsRolePermission extends PermissionBaseEntity {

	private static final long serialVersionUID = -9012707031072904356L;
	
	/**
	 * 角色ID
	 */
	private Long roleId; 
	
	private Long permissionId;// 权限ID

	/**
	 * @Description: 角色ID
	 * @return Long
	 * @Autor: Jason
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @Description: 角色ID
	 * @param roleId 
	 * @Autor: Jason
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @Description: 权限ID
	 * @return Long
	 * @Autor: Jason
	 */
	public Long getPermissionId() {
		return permissionId;
	}

	/**
	 * @Description: 权限ID
	 * @param permissionId
	 * @Autor: Jason
	 */
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public PmsRolePermission() {
		super();
	}

}
