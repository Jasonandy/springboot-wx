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

import cn.ucaner.wx.app.service.permission.entity.PmsRole;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao   
* @ClassName：PmsRoleDao   
* @Description：   <p> 权限角色dao </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:50:57   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public interface PmsRoleDao extends PermissionBaseDao<PmsRole> {

	/**
	 * 获取所有角色列表，以供添加操作员时选择.
	 * @return roleList .
	 */
	public List<PmsRole> listAll();

	/**
	 * 判断此权限是否关联有角色
	 * @param permissionId
	 * @return
	 */
	public List<PmsRole> listByPermissionId(Long permissionId);

	/**
	 * 根据角色名或者角色编号查询角色
	 * @param roleName
	 * @param roleCode
	 * @return
	 */
	public PmsRole getByRoleNameOrRoleCode(String roleName, String roleCode);

}
