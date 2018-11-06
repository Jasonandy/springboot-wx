/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.service.permission.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.ucaner.wx.app.service.permission.dao.PmsPermissionDao;
import cn.ucaner.wx.app.service.permission.entity.PmsPermission;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao.impl   
* @ClassName：PmsPermissionDaoImpl   
* @Description：   <p> 权限功能点dao实现 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:47:46   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Repository
public class PmsPermissionDaoImpl extends PermissionBaseDaoImpl<PmsPermission> implements PmsPermissionDao {

	/**
	 * 根据实体ID集字符串获取对象列表.
	 * @param idStr
	 * @return
	 */
	public List<PmsPermission> findByIds(String idStr) {
		List<String> ids = Arrays.asList(idStr.split(","));
		return this.getSqlSession().selectList(getStatement("findByIds"), ids);
	}

	/**
	 * 检查权限名称是否已存在
	 * @param trim
	 * @return
	 */
	public PmsPermission getByPermissionName(String permissionName) {
		return this.getSqlSession().selectOne(getStatement("getByPermissionName"), permissionName);

	}

	/**
	 * 检查权限是否已存在
	 * @param permission
	 * @return
	 */
	public PmsPermission getByPermission(String permission) {
		return this.getSqlSession().selectOne(getStatement("getByPermission"), permission);
	}

	/**
	 * 检查权限名称是否已存在(其他id)
	 * @param permissionName
	 * @param id
	 * @return
	 */
	public PmsPermission getByPermissionNameNotEqId(String permissionName, Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("permissionName", permissionName);
		paramMap.put("id", id);
		return this.getSqlSession().selectOne(getStatement("getByPermissionNameNotEqId"), paramMap);
	}

	/**
	 * 获取叶子菜单下所有的功能权限
	 * @param valueOf
	 * @return
	 */
	public List<PmsPermission> listAllByMenuId(Long menuId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuId", menuId);
		return this.getSqlSession().selectList(getStatement("listAllByMenuId"), param);
	}
}
