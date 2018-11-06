/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.service.permission.dao;

import java.util.List;

import cn.ucaner.wx.app.service.permission.entity.PmsRolePermission;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao   
* @ClassName：PmsRolePermissionDao   
* @Description：   <p> 角色权限dao  </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:51:13   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public interface PmsRolePermissionDao extends PermissionBaseDao<PmsRolePermission> {

	/**
	 * @Description:    根据角色ID找到角色关联的权限点
	 * @param roleId	 roleId
	 * @return List<PmsRolePermission> rolePermissionList
	 * @Autor: Jason
	 */
	public List<PmsRolePermission> listByRoleId(final long roleId);

	/**
	 * 根据角色ID字符串获取相应角色-权限关联信息.
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<PmsRolePermission> listByRoleIds(String roleIdsStr);

	/**
	 * @Description: deleteByRoleIdAndPermissionId 
	 * @param roleId
	 * @param permissionId 
	 * @Autor: Jason
	 */
	public void deleteByRoleIdAndPermissionId(Long roleId, Long permissionId);
	
	
	/**
	 * @Description: deleteByRoleId
	 * @param roleId 
	 * @Autor: Jason
	 */
	public void deleteByRoleId(Long roleId);
}
