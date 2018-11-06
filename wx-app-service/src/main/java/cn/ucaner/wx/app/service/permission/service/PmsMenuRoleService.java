/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.service.permission.service;

/**
* @Package：cn.ucaner.wx.app.service.permission.service   
* @ClassName：PmsMenuRoleService   
* @Description：   <p> 菜单角色service接口 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:57:46   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public interface PmsMenuRoleService {

	/**
	 * 根据角色ID统计关联到此角色的菜单数.
	 * @param roleId
	 *            角色ID.
	 * @return count.
	 */
	public int countMenuByRoleId(Long roleId);

	/**
	 * 根据角色id，删除该角色关联的所有菜单权限
	 * @param roleId
	 */
	public void deleteByRoleId(Long roleId);

	/**
	 * @Description: saveRoleMenu 
	 * @param roleId
	 * @param roleMenuStr
	 * @Autor: Jason
	 */
	public void saveRoleMenu(Long roleId, String roleMenuStr);

}
