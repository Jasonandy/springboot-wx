/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.boss.permission.shiro.realm;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.ucaner.wx.app.core.enums.PublicStatusEnum;
import cn.ucaner.wx.app.service.permission.entity.PmsOperator;
import cn.ucaner.wx.app.service.permission.service.PmsOperatorRoleService;
import cn.ucaner.wx.app.service.permission.service.PmsOperatorService;
import cn.ucaner.wx.app.service.permission.service.PmsRolePermissionService;

/**
* @Package：cn.ucaner.wx.app.boss.permission.shiro.realm   
* @ClassName：OperatorRealm   
* @Description：   <p> 自定义realm  </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:46:46   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class OperatorRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(OperatorRealm.class);
	
	/**
	 * 操作员对象
	 */
	private static final String PMSOPERATOR = "PmsOperator";
	
	/**
	 * 角色集
	 */
	private static final String ROLES = "ROLES";
	
	/**
	 * 权限集
	 */
	private static final String PERMISSIONS = "PERMISSIONS";
	
	
	@Autowired
	private PmsOperatorService pmsOperatorService;
	
	@Autowired
	private PmsOperatorRoleService pmsOperatorRoleService;
	
	@Autowired
	private PmsRolePermissionService pmsRolePermissionService;

	
	/**
	 * 授权过程  - session里面获取操作的信息 
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginName = (String) principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		PmsOperator operator = (PmsOperator) session.getAttribute(PMSOPERATOR);
		if (operator == null) {
			//根据登录名取得操作员对象
			operator = pmsOperatorService.findOperatorByLoginName(loginName);
			session.setAttribute("PmsOperator", operator);
		}
		// 根据登录名查询操作员
		Long operatorId = operator.getId();
		Set<String> roles = (Set<String>) session.getAttribute(ROLES);
		if (roles == null || roles.isEmpty()) {
			//获取该操作员所有角色的编码集合
			roles = pmsOperatorRoleService.getRoleCodeByOperatorId(operatorId);
			session.setAttribute(ROLES, roles);
		}
		/**
		 * 该账号的所有的角色信息 - roles
		 */
		authorizationInfo.setRoles(roles);

		Set<String> permisstions = (Set<String>) session.getAttribute(PERMISSIONS);
		if (permisstions == null || permisstions.isEmpty()) {
			//根据操作员ID,获取所有的功能权限集
			permisstions = pmsRolePermissionService.getPermissionsByOperatorId(operatorId);
			session.setAttribute(PERMISSIONS, permisstions);
		}
		// 根据用户名查询权限
		authorizationInfo.setStringPermissions(permisstions);
		
		logger.info("PrincipalCollection - {} - 加载权限集合完毕.",JSON.toJSONString(authorizationInfo));
		return authorizationInfo;
	}

	/**
	 *  验证的核心方法 - 登录认证
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String loginName = (String) token.getPrincipal();
		if (StringUtils.isEmpty(loginName.trim())) { //不存在的账号
			logger.info("当前账号不存在,请核查后再输入!Please check you account  thks.");
			throw new UnknownAccountException();// 没找到帐号
		}

		/**
		 * 根据登录名查询操作员
		 */
		PmsOperator operator = pmsOperatorService.findOperatorByLoginName(loginName);

		if (operator == null) {
			logger.info("Please check you operator thks.");
			throw new UnknownAccountException();// 没找到帐号
		}

		/**
		 * UNACTIVE 账号被冻结 
		 */
		if (PublicStatusEnum.UNACTIVE.equals(operator.getStatus())) {
			logger.info("对不起,当前账号{}处于冻结状态,请解封后重试!",operator.getLoginName());
			throw new LockedAccountException(); // 帐号锁定
		}

		/**
		 * AuthenticatingRealm - 交给使用CredentialsMatcher进行密码匹配
		 */
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
			operator.getLoginName(), // 登录名
			operator.getLoginPwd(), // 密码
			ByteSource.Util.bytes(operator.getCredentialsSalt()), // salt=username+salt
			getName() // realm name
		);
		logger.info("用户 -{} 正由AuthenticatingRealm认证中心交付给密码匹配器匹配中.",operator.getLoginName());
		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
