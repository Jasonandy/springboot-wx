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

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.ucaner.wx.app.service.permission.dao.PmsOperatorDao;
import cn.ucaner.wx.app.service.permission.entity.PmsOperator;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao.impl   
* @ClassName：PmsOperatorDaoImpl   
* @Description：   <p>  权限操作员dao实现</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:46:43   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Repository
public class PmsOperatorDaoImpl extends PermissionBaseDaoImpl<PmsOperator> implements PmsOperatorDao {

	/**
	 * 根据操作员登录名获取操作员信息.
	 */
	public PmsOperator findByLoginName(String loginName) {
		return super.getSqlSession().selectOne(getStatement("findByLoginName"), loginName);
	}

	@Override
	public List<PmsOperator> listByRoleId(Long roleId) {
		return super.getSqlSession().selectList(getStatement("listByRoleId"), roleId);
	}

}
