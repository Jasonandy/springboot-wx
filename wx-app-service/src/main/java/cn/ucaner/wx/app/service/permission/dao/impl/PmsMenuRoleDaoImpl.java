package cn.ucaner.wx.app.service.permission.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.ucaner.wx.app.service.permission.dao.PmsMenuRoleDao;
import cn.ucaner.wx.app.service.permission.entity.PmsMenuRole;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao.impl   
* @ClassName：PmsMenuRoleDaoImpl   
* @Description：   <p> 菜单角色 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:46:25   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Repository
public class PmsMenuRoleDaoImpl extends PermissionBaseDaoImpl<PmsMenuRole> implements PmsMenuRoleDao {

	@Override
	public void deleteByRoleId(Long roleId) {
		super.getSqlSession().delete(getStatement("deleteByRoleId"), roleId);
	}

	/**
	 * 根据角色ID统计关联到此角色的菜单数.
	 * 
	 * @param roleId
	 *            角色ID.
	 * @return count.
	 */
	@Override
	public List<PmsMenuRole> listByRoleId(Long roleId) {
		return super.getSqlSession().selectList(getStatement("listByRoleId"), roleId);
	}
}