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

import java.util.Set;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.service.permission.entity.PmsRolePermission;

/**
* @Package：cn.ucaner.wx.app.service.permission.service   
* @ClassName：PmsRolePermissionService   
* @Description：   <p>  角色权限service接口  </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:56:02   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public interface PmsRolePermissionService {

	/**
	 * @Description: 根据操作员ID，获取所有的功能权限集
	 * @param operatorId
	 * @return Set<String>
	 * @Autor: Jason
	 */
	public Set<String> getPermissionsByOperatorId(Long operatorId);

	/**
	 * @Description: 创建pmsRolePermission
	 * @param pmsRolePermission 
	 * @Autor: Jason
	 */
	void saveData(PmsRolePermission pmsRolePermission);

	/**
	 * @Description: 修改pmsRolePermission
	 * @param pmsRolePermission 
	 * @Autor: Jason
	 */
	void updateData(PmsRolePermission pmsRolePermission);

	/**
	 * @Description: 根据id获取数据pmsRolePermission 
	 * @param id
	 * @return PmsRolePermission
	 * @Autor: Jason
	 */
	PmsRolePermission getDataById(Long id);

	/**
	 * @Description: 分页查询pmsRolePermission
	 * @param pageParam
	 * @param pmsRolePermission
	 * @return PageInfo<PmsRolePermission>
	 * @Autor: Jason
	 */
	PageInfo<PmsRolePermission> listPage(Page<PmsRolePermission> pageParam,PmsRolePermission pmsRolePermission);
	
	/**
	 * @Description: 保存角色和权限之间的关联关系 
	 * @param roleId
	 * @param rolePermissionStr
	 * @Autor: Jason
	 */
	void saveRolePermission(Long roleId, String rolePermissionStr);

}
