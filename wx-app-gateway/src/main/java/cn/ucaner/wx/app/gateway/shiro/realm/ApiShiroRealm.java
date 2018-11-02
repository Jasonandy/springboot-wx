package cn.ucaner.wx.app.gateway.shiro.realm;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.ucaner.wx.app.core.enums.PublicStatusEnum;
import cn.ucaner.wx.app.gateway.jwt.utils.JWTUtil;
import cn.ucaner.wx.app.gateway.shiro.principal.JWTToken;
import cn.ucaner.wx.app.service.permission.entity.PmsOperator;
import cn.ucaner.wx.app.service.permission.service.PmsOperatorRoleService;
import cn.ucaner.wx.app.service.permission.service.PmsOperatorService;
import cn.ucaner.wx.app.service.permission.service.PmsRolePermissionService;

/**
* @Package：cn.ucaner.wx.app.gateway.shiro.realm   
* @ClassName：ApiShiroRealm   
* @Description：   <p> ApiShiroRealm </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午10:56:41   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
//@Component
public class ApiShiroRealm extends AuthorizingRealm{
	
	private static final Logger logger = LoggerFactory.getLogger(ApiShiroRealm.class);
	
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
     * JWT签名密钥 - 自定义前面密钥
     */
     //public static final String SING_SECRET = "edb7ff3b648c252113335b737d8a627a";

    
    /**
     * 必须重写此方法,不然Shiro会报错.
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
    
    
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String jwtToken = (String) principals.getPrimaryPrincipal();
		
		if (StringUtils.isEmpty(jwtToken)) {
			logger.info("jwtToken不存在,请检查是否成功登陆!");
		}
		
		String loginName = JWTUtil.getUsername(jwtToken);//从jwtToken中获取userName
		
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

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String jwtToken = (String) token.getPrincipal();
		if (StringUtils.isEmpty(jwtToken)) {
			logger.info("jwtToken不存在,请检查是否成功登陆!");
			throw new UnknownAccountException("JWT-TOKEN异常!请检查请求头!");// 没找到Token
		}
		
		String userName = JWTUtil.getUsername(jwtToken);//从jwtToken中获取userName
		
		PmsOperator operator = pmsOperatorService.findOperatorByLoginName(userName);
		
		if (operator == null) {
			logger.info("Please check you operator thks.");
			throw new UnknownAccountException("未知账号!");// 没找到帐号
		}
		
		if (PublicStatusEnum.UNACTIVE.equals(operator.getStatus())) {
			logger.info("对不起,当前账号{}处于冻结状态,请解封后重试!",operator.getLoginName());
			throw new LockedAccountException("账号处于锁定状态,稍后重试!"); // 帐号锁定
		}
		
		//[随机盐+userName]   -- 用什么Sign 就用什么verify
		if (!JWTUtil.verify(jwtToken, userName, operator.getCredentialsSalt())) {
		//if (!JWTUtil.verify(jwtToken, userName, operator.getLoginPwd())) {
            throw new AuthenticationException("[请检查你的账号/用户名是否正确]Please check you password or account.");
		}	
		
		/**
		 * AuthenticatingRealm - 交给使用CredentialsMatcher进行密码匹配
		 */
		logger.info("用户 -{} 正由AuthenticatingRealm认证中心交付给密码匹配器匹配中.",operator.getLoginName());
		
		// return new SimpleAuthenticationInfo(token, token, "apiShiroRelam");//可以自己实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
			//operator.getLoginName(), // 登录名
			//operator.getLoginPwd(), // 密码
			//token.getPrincipal(),
			jwtToken,
			jwtToken,
			//token.getCredentials(),
			//ByteSource.Util.bytes(operator.getCredentialsSalt()), //cn.ucaner.wx.gateway.shiro.principal.JWTToken 没有加盐操作
			getName() // realm name
		);
	    return authenticationInfo;
		//return new SimpleAuthenticationInfo(token.getCredentials(), token.getCredentials(), operator.getCredentialsSalt(),getName());
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
