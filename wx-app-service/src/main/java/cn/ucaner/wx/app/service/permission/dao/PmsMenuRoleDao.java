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

import cn.ucaner.wx.app.service.permission.entity.PmsMenuRole;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao   
* @ClassName：PmsMenuRoleDao   
* @Description：   <p> 菜单角色关联表</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:49:17   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public interface PmsMenuRoleDao extends PermissionBaseDao<PmsMenuRole> {

	/**
	 * 根据角色ID删除菜单与角色的关联记录.
	 * @param roleId
	 */
	void deleteByRoleId(Long roleId);

	/**
	 * 根据角色ID统计关联到此角色的菜单数.
	 * @param roleId
	 *            角色ID.
	 * @return count.
	 */
	List<PmsMenuRole> listByRoleId(Long roleId);
}