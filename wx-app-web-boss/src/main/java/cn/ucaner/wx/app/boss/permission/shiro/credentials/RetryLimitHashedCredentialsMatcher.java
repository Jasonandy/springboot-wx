/**
 * <html>
 * <body>
 *  <P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *  </body>
 * </html>
 */
package cn.ucaner.wx.app.boss.permission.shiro.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ucaner.wx.app.service.permission.entity.PmsOperator;
import cn.ucaner.wx.app.service.permission.service.PmsOperatorService;

/**
* @Package：cn.ucaner.wx.app.boss.permission.shiro.credentials   
* @ClassName：RetryLimitHashedCredentialsMatcher   
* @Description：   <p> 自定义的使用凭证匹配器 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:48:13   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	@Autowired
	private PmsOperatorService pmsOperatorService;

	/**
	 * 缓存 
	 */
	private Cache<String, AtomicInteger> passwordRetryCache;

	/**
	* RetryLimitHashedCredentialsMatcher.  - passwordRetryCache
	* @param cacheManager
	 */
	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	/**
	 * 登录密码校验  + 次数限制
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// retry count + 1 AtomicInteger - 线程安全考虑
		AtomicInteger retryCount = passwordRetryCache.get(username);
		
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			//为空的话初始化  为 0 次统计开始
			passwordRetryCache.put(username, retryCount);
		}
		
		if (retryCount.incrementAndGet() > 5) {
			// if retry count > 5 throw
			throw new ExcessiveAttemptsException();
		}
		
		/**
		 * 是否匹配 - tokenHashedCredentials, accountCredentials - 校验通过 
		 */
		boolean matches = super.doCredentialsMatch(token, info);
		
		/**
		 * 校验成功 -  删除登录次数ehcache次数记录缓存
		 * clear retry count  - 匹配的话 移除登录次数统计 
		 */
		if (matches) {
			passwordRetryCache.remove(username);
			
			// 根据登录名查询操作员
			PmsOperator operator = pmsOperatorService.findOperatorByLoginName(username);
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			//存入操作员对象信息
			session.setAttribute("PmsOperator", operator);
		}
		
		return matches;
	}
}
