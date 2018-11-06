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
* @ClassName：PmsPermission   
* @Description：   <p>  权限-权限表实体  </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:52:58   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class PmsPermission extends PermissionBaseEntity {

	private static final long serialVersionUID = -2175597348886393330L;
	
	private String permissionName; // 权限名称
	
	private String permission; // 权限标识

	/**
	 * 权限名称
	 * @return
	 */
	public String getPermissionName() {
		return permissionName;
	}

	/**
	 * 权限名称
	 * @return
	 */
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	/**
	 * 权限标识
	 * @return
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * 权限标识
	 * @return
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

}
