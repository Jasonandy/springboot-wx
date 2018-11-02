package cn.ucaner.wx.app.gateway.shiro.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ucaner.wx.app.gateway.jwt.utils.JWTUtil;

/**
* @Package：cn.ucaner.wx.app.gateway.shiro.credentials   
* @ClassName：RetryLimitHashedCredentialsMatcher   
* @Description：   <p> 自定义的使用凭证匹配器</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午10:58:03   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	private Logger logger = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);
	
	/**
	 * api 调用次数限制
	 */
	private  final static int  EHCAHCE_API_CALL_COUNT = 100;
	
	
	/**
	 * ehcache api 调用次数限制 - apiCallNumsCache 
	 */
	private  final static String  EHCAHCE_API_CALL_NUMS = "apiCallNumsCache";
	
	/**
	 * 缓存 
	 */
	private Cache<String, AtomicInteger> apiCallNumsCache;

	/**
	* RetryLimitHashedCredentialsMatcher.  - passwordRetryCache
	* @param cacheManager
	 */
	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		apiCallNumsCache = cacheManager.getCache(EHCAHCE_API_CALL_NUMS);
	}

	/**
	 * Api接口请求次数限制 
	 *  - AuthenticationToken 是传递过来的信息
	 *  - AuthenticationInfo  是合法性的参考信息 
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String jwtToken = (String) token.getPrincipal();
		
		String username = JWTUtil.getUsername(jwtToken);//从jwtToken中获取userName
		
		// retry count + 1 AtomicInteger - 线程安全考虑
		AtomicInteger apiCallCount = apiCallNumsCache.get(username);
		
		
		if (apiCallCount == null) {
			apiCallCount = new AtomicInteger(0);
			//为空的话初始化  为 0 次统计开始
			apiCallNumsCache.put(username, apiCallCount);
		}
		
		if (apiCallCount.incrementAndGet() > EHCAHCE_API_CALL_COUNT) {
			// if retry count > EHCAHCE_API_CALL_COUNT throw
			throw new ExcessiveAttemptsException();
		}
		
		/**
		 * 是否匹配 - tokenHashedCredentials, accountCredentials - 校验通过 
		 * 
		 * token没有做复杂的校验 直接相等就ok. 需要优化.
		 */
		//boolean matches = super.doCredentialsMatch(token, info);
		
		//boolean matches = token.getPrincipal().equals(info.getPrincipals());
		
		boolean matches = true;
		
		/**
		 * 校验成功 -  删除登录次数ehcache次数记录缓存
		 */
		if (matches) {
			logger.info("Api JwtToken 校验成功!当前用户调用Api次数为:{}次!",apiCallCount);
		}
		
		return matches;
	}
}
