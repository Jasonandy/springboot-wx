package cn.ucaner.wx.app.boss.permission.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @Package：cn.ucaner.wx.app.boss.permission.shiro.realm   
* @ClassName：MyShiroRealm   
* @Description：   <p> MyShiroRealm 
* @See https://blog.csdn.net/aqsunkai/article/details/69757017
* </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:45:37   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class MyShiroRealm extends AuthorizingRealm{

	private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
	
	/**
	 * 登录认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.debug("进入登录认证中心了 - doGetAuthorizationInfo ");
		return null;
	}

	/**
	 * 权限认证!
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	    logger.info("权限认证!");
		// 返回null将会导致用户访问任何被拦截的请求时都会自动跳转到unauthorizedUrl指定的地址
		return null;
	}

}
