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

import cn.ucaner.wx.app.service.permission.entity.PmsPermission;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao   
* @ClassName：PmsPermissionDao   
* @Description：   <p>  权限点dao</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:50:37   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public interface PmsPermissionDao extends PermissionBaseDao<PmsPermission> {
	
	/**
	 * 根据实体ID集字符串获取对象列表.
	 * @param ids
	 * @return
	 */
	List<PmsPermission> findByIds(String ids);

	/**
	 * 检查权限名称是否已存在
	 * @param trim
	 * @return
	 */
	PmsPermission getByPermissionName(String permissionName);

	/**
	 * 检查权限是否已存在
	 * @param permission
	 * @return
	 */
	PmsPermission getByPermission(String permission);

	/**
	 * 检查权限名称是否已存在(其他id)
	 * @param permissionName
	 * @param id
	 * @return
	 */
	PmsPermission getByPermissionNameNotEqId(String permissionName, Long id);

	/**
	 * 获取叶子菜单下所有的功能权限
	 * @param valueOf
	 * @return
	 */
	List<PmsPermission> listAllByMenuId(Long menuId);

}
