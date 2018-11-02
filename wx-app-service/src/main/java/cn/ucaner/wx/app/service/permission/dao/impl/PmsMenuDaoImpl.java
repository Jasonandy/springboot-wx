package cn.ucaner.wx.app.service.permission.dao.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.ucaner.wx.app.service.permission.dao.PmsMenuDao;
import cn.ucaner.wx.app.service.permission.entity.PmsMenu;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao.impl   
* @ClassName：PmsMenuDaoImpl   
* @Description：   <p> 权限菜单</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:46:06   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Repository
public class PmsMenuDaoImpl extends PermissionBaseDaoImpl<PmsMenu> implements PmsMenuDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List listByRoleIds(String roleIdsStr) {
		List<String> roldIds = Arrays.asList(roleIdsStr.split(","));
		return super.getSqlSession().selectList(getStatement("listByRoleIds"), roldIds);
	}

	/**
	 * 根据父菜单ID获取该菜单下的所有子孙菜单.<br/>
	 * 
	 * @param parentId
	 *            (如果为空，则为获取所有的菜单).<br/>
	 * @return menuList.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List listByParent(Long parentId) {
		return super.getSqlSession().selectList(getStatement("listByParent"), parentId);
	}

	/**
	 * 根据菜单ID查找菜单（可用于判断菜单下是否还有子菜单）.
	 * 
	 * @param parentId
	 *            .
	 * @return menuList.
	 */
	@Override
	public List<PmsMenu> listByParentId(Long parentId) {
		return super.getSqlSession().selectList(getStatement("listByParentId"), parentId);
	}

	/***
	 * 根据名称和是否叶子节点查询数据
	 * 
	 * @param isLeaf
	 *            是否是叶子节点
	 * @param name
	 *            节点名称
	 * @return
	 */
	public List<PmsMenu> getMenuByNameAndIsLeaf(Map<String, Object> map) {
		return super.getSqlSession().selectList(getStatement("listBy"), map);
	}

}