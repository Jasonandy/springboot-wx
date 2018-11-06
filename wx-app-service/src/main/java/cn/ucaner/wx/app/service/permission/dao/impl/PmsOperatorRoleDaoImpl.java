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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.ucaner.wx.app.service.permission.dao.PmsOperatorRoleDao;
import cn.ucaner.wx.app.service.permission.entity.PmsOperatorRole;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao.impl   
* @ClassName：PmsOperatorRoleDaoImpl   
* @Description：   <p>  权限-操作员与角色dao实现</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:47:24   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Repository
public class PmsOperatorRoleDaoImpl extends PermissionBaseDaoImpl<PmsOperatorRole> implements PmsOperatorRoleDao {

	/**
	 * 根据操作员ID查找该操作员关联的角色.
	 * @param operatorId
	 * @return list .
	 */
	public List<PmsOperatorRole> listByOperatorId(Long operatorId) {
		return super.getSqlSession().selectList(getStatement("listByOperatorId"), operatorId);
	}

	/**
	 * 根据角色ID查找该操作员关联的操作员.
	 * @param roleId
	 * @return
	 */
	public List<PmsOperatorRole> listByRoleId(Long roleId) {
		return super.getSqlSession().selectList(getStatement("listByRoleId"), roleId);
	}

	/**
	 * 根据操作员ID删除与角色的关联记录.
	 * @param operatorId
	 *            .
	 */
	public void deleteByOperatorId(Long operatorId) {

		super.getSqlSession().delete(getStatement("deleteByOperatorId"), operatorId);
	}

	/**
	 * 根据角色ID删除操作员与角色的关联关系.
	 * @param roleId
	 *            .
	 */
	public void deleteByRoleId(Long roleId) {
		super.getSqlSession().delete(getStatement("deleteByRoleId"), roleId);
	}

	/**
	 * 根据角色ID和操作员ID删除关联数据(用于更新操作员的角色).
	 * @param roleId
	 *            角色ID.
	 * @param operatorId
	 *            操作员ID.
	 */
	@Override
	public void deleteByRoleIdAndOperatorId(Long roleId, Long operatorId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		paramMap.put("operatorId", operatorId);
		super.getSqlSession().delete(getStatement("deleteByRoleIdAndOperatorId"), paramMap);
	}

}
