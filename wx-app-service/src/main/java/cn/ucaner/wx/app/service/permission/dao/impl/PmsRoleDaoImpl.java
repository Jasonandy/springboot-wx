package cn.ucaner.wx.app.service.permission.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


import cn.ucaner.wx.app.service.permission.dao.PmsRoleDao;
import cn.ucaner.wx.app.service.permission.entity.PmsRole;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao.impl   
* @ClassName：PmsRoleDaoImpl   
* @Description：   <p>  权限角色dao实现 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:48:06   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Repository
public class PmsRoleDaoImpl extends PermissionBaseDaoImpl<PmsRole> implements PmsRoleDao {

	/**
	 * 获取所有角色列表，以供添加操作员时选择.
	 * 
	 * @return roleList .
	 */
	public List<PmsRole> listAll() {
		return super.getSqlSession().selectList(getStatement("listAll"));
	}

	/**
	 * 判断此权限是否关联有角色
	 * 
	 * @param permissionId
	 * @return
	 */
	public List<PmsRole> listByPermissionId(Long permissionId) {
		return super.getSqlSession().selectList(getStatement("listByPermissionId"), permissionId);
	}

	/**
	 * 根据角色名或者角色编号查询角色
	 * 
	 * @param roleName
	 * @param roleCode
	 * @return
	 */
	public PmsRole getByRoleNameOrRoleCode(String roleName, String roleCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleName", roleName);
		paramMap.put("roleCode", roleCode);
		return super.getSqlSession().selectOne(getStatement("getByRoleNameOrRoleCode"), paramMap);
	}
}
