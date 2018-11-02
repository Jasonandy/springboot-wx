package cn.ucaner.wx.app.service.permission.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.service.permission.entity.PmsRole;

/**
* @Package：cn.ucaner.wx.app.service.permission.service   
* @ClassName：PmsRoleService   
* @Description：   <p> 角色service接口 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:55:35   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public interface PmsRoleService {

	/**
	 * @Description: 创建pmsRole  [创建角色 - 管理员 - 超级管理人员 - 游客 - 等]
	 * @param pmsRole  [ 营销人员 - 管理人员 ]
	 * @Autor: Jason
	 */
	void saveData(PmsRole pmsRole);

	/**
	 * @Description: 修改pmsRole
	 * @param pmsRole 
	 * @Autor: Jason
	 */
	void updateData(PmsRole pmsRole);

	/**
	 * @Description: 根据id获取数据pmsRole 
	 * @param id
	 * @return PmsRole
	 * @Autor: Jason
	 */
	PmsRole getDataById(Long id);

	/**
	 * @Description: 分页查询pmsRole
	 * @param pageParam
	 * @param pmsRole
	 * @return PageInfo<PmsRole>
	 * @Autor: Jason
	 */
	PageInfo<PmsRole> listPage(Page<PmsRole> pageParam,PmsRole pmsRole);

	/**
	 * @Description: 获取所有角色列表，以供添加操作员时选择 
	 * @return List<PmsRole>
	 * @Autor: Jason
	 */
	public List<PmsRole> listAllRole();

	/**
	 * @Description: 判断此权限是否关联有角色 
	 * @param permissionId
	 * @return List<PmsRole>
	 * @Autor: Jason
	 */
	List<PmsRole> listByPermissionId(Long permissionId);

	/**
	 * 根据角色名或者角色编号查询角色
	 * @param roleName
	 * @param roleCode
	 * @return PmsRole
	 */
	PmsRole getByRoleNameOrRoleCode(String roleName, String roleCode);

	/**
	 * @Description: 删除
	 * @param roleId 角色id
	 * @Autor: Jason
	 */
	void delete(Long roleId);

}
