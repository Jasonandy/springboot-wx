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

import cn.ucaner.wx.app.service.permission.entity.PmsOperator;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao   
* @ClassName：PmsOperatorDao   
* @Description：   <p>  操作员dao</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:49:36   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public interface PmsOperatorDao extends PermissionBaseDao<PmsOperator> {

	/**
	 * 根据操作员登录名获取操作员信息.
	 * @param loginName
	 * @return operator .
	 */
	PmsOperator findByLoginName(String loginName);

	/**
	 * 根据角色ID找到操作员列表.
	 * @param roleId
	 * @return
	 */
	List<PmsOperator> listByRoleId(Long roleId);
}
