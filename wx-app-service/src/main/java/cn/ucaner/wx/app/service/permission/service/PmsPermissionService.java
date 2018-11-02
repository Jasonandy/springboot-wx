package cn.ucaner.wx.app.service.permission.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.service.permission.entity.PmsPermission;

/**
* @Package：cn.ucaner.wx.app.service.permission.service   
* @ClassName：PmsPermissionService   
* @Description：   <p> 权限service接口 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:56:20   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public interface PmsPermissionService {

	/**
	 * @Description: 创建pmsPermission
	 * @param pmsPermission void
	 * @Autor: Jason
	 */
	void saveData(PmsPermission pmsPermission);

	/**
	 * @Description: 修改pmsPermission
	 * @param pmsPermission 
	 * @Autor: Jason
	 */
	void updateData(PmsPermission pmsPermission);

	/**
	 * @Description: 根据id获取数据pmsPermission
	 * @param id
	 * @return PmsPermission
	 * @Autor: Jason
	 */
	PmsPermission getDataById(Long id);

	/**
	 * @Description: 分页查询pmsPermission 
	 * @param pageParam
	 * @param pmsPermission
	 * @return PageInfo<PmsPermission>
	 * @Autor: Jason
	 */
	PageInfo<PmsPermission> listPage(Page<PmsPermission> pageParam,PmsPermission pmsPermission);

	/**
	 * @Description: 检查权限名称是否已存在 
	 * @param permissionName
	 * @return PmsPermission
	 * @Autor: Jason
	 */
	PmsPermission getByPermissionName(String permissionName);

	/**
	 * @Description: 检查权限是否已存在 
	 * @param permission
	 * @return PmsPermission
	 * @Autor: Jason
	 */
	PmsPermission getByPermission(String permission);

	/**
	 * @Description: 检查权限名称是否已存在(其他id) 
	 * @param permissionName
	 * @param id
	 * @return PmsPermission
	 * @Autor: Jason
	 */
	PmsPermission getByPermissionNameNotEqId(String permissionName, Long id);

	/**
	 * @Description: 删除 
	 * @param permissionId 
	 * @Autor: Jason
	 */
	void delete(Long permissionId);

	/**
	 * @Description: 根据角色查找角色对应的功能权限ID集
	 * @param roleId
	 * @return String
	 * @Autor: Jason
	 */
	String getPermissionIdsByRoleId(Long roleId);
	
	/**
	 * @Description: 查询所有的权限 
	 * @return List<PmsPermission>
	 * @Autor: Jason
	 */
	List<PmsPermission> listAll();

}
