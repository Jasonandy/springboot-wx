package cn.ucaner.wx.app.boss.permission.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.fastjson.JSON;

import cn.ucaner.wx.app.boss.permission.shiro.credentials.RetryLimitHashedCredentialsMatcher;
import cn.ucaner.wx.app.boss.permission.shiro.filter.RcCaptchaValidateFilter;
import cn.ucaner.wx.app.boss.permission.shiro.filter.RcFormAuthenticationFilter;
import cn.ucaner.wx.app.boss.permission.shiro.realm.OperatorRealm;
import cn.ucaner.wx.app.boss.permission.shiro.spring.SpringCacheManagerWrapper;

/**
* @Package：cn.ucaner.wx.app.boss.permission.shiro.config   
* @ClassName：ShiroConfiguration   
* @Description：   <p> ShiroConfiguration  权限filter的拦截配置 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:48:32   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Configuration
public class ShiroConfiguration {
	
	private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
	
	 /**
	  * @Description: shiroFilter 
	  * ShiroFilterFactoryBean 处理拦截资源文件问题
	  * 单独一个ShiroFilterFactoryBean配置是或报错的，以为在初始化ShiroFilterFactoryBean的时候需要注入：
	  * 	SecurityManager  Filter Chain
	  * 		1、一个URL可以配置多个Filter，使用逗号分隔 
	  * 		2、当设置多个过滤器时，全部验证通过，才视为通过
	  * 		3、部分过滤器可指定参数，如perms，roles
	  * @param securityManager
	  * @return ShiroFilterFactoryBean
	  * @Autor: Jason
	  */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        
        shiroFilterFactoryBean.setSecurityManager(securityManager); //SecurityManager
        shiroFilterFactoryBean.setLoginUrl("/login"); //默认的登录页面 /login
        shiroFilterFactoryBean.setSuccessUrl("/index"); //登录成功后的路由页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/system/unauthorized.jsp"); //跳转到未授权的提示页面

        /**
         * filter过滤器
         */
        
        //-------------------------------filtersMap-----------------------------------------------
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //filtersMap.put("rcCaptchaFilter", getRcCaptchaFilter()); //验证码生成filter
        filtersMap.put("authc", getRcFormAuthenticationFilter()); //基于Form表单的身份验证过滤器，为了控制验证码
        filtersMap.put("rcCaptchaValidate", getRcCaptchaValidateFilter()); //验证码验证过滤器
        shiroFilterFactoryBean.setFilters(filtersMap);
        //-------------------------------filtersMap-----------------------------------------------
        
        /**
         * filter 过滤链 (一层一层传递 )
         * 
         */
        //--------------------------------filterChain-----------------------------------------------
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //rcCaptchaFilter
        filterChainDefinitionMap.put("/rcCaptcha*", "anon");//验证码可以匿名访问到
        filterChainDefinitionMap.put("/system/unauthorized.jsp", "anon");
        filterChainDefinitionMap.put("/common/**", "anon");
        filterChainDefinitionMap.put("/dwz/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/login", "rcCaptchaValidate,authc");
        //filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "logout");//shiro 自己有实现logout filter
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //--------------------------------filterChain-----------------------------------------------
        logger.info("[Shiro]-shiroFilter-初始化成功,shiroFilterFactoryBean:{}.",JSON.toJSONString(shiroFilterFactoryBean));
        return shiroFilterFactoryBean;
    }
    
    /**
     * @Description: rcFormAuthenticationFilter - from表达提交参数的属性命名
     * @return RcFormAuthenticationFilter
     * @Autor:  WubBin@wanguo.com
     */
    @Bean(name="rcFormAuthenticationFilter")
    public RcFormAuthenticationFilter getRcFormAuthenticationFilter() {
    	// <!-- from表达提交参数的属性命名 -->
    	RcFormAuthenticationFilter rcFormAuthenticationFilter = new RcFormAuthenticationFilter();
    	rcFormAuthenticationFilter.setUsernameParam("loginName");
    	rcFormAuthenticationFilter.setPasswordParam("wanguoPwd");
    	rcFormAuthenticationFilter.setRememberMeParam("rememberMe");
    	rcFormAuthenticationFilter.setLoginUrl("/login");
    	rcFormAuthenticationFilter.setFailureKeyAttribute("shiroLoginFailure");
    	return rcFormAuthenticationFilter;
    }
    
    
    /**
     * @Description: RcCaptchaValidateFilter - 验证码验证过滤器
     * @return RcCaptchaValidateFilter
     * @Autor: WubBin@wanguo.com
     */
    @Bean(name="rcCaptchaValidateFilter")
    public RcCaptchaValidateFilter getRcCaptchaValidateFilter() {
    	RcCaptchaValidateFilter rcCaptchaValidateFilter = new RcCaptchaValidateFilter();
    	rcCaptchaValidateFilter.setCaptchaEbabled(true);//false关闭校验 true 打开校验
    	rcCaptchaValidateFilter.setCaptchaParam("captchaCode");
    	rcCaptchaValidateFilter.setFailureKeyAttribute("shiroLoginFailure");
    	return rcCaptchaValidateFilter;
    }
    
    
    /**
     * @Description: EhCacheManagerFactoryBean 加载Xml ehcache具体的配置化实现
     * @return EhCacheManagerFactoryBean
     * @Autor: Jason
     */
    @Bean(name="ehcacheManager")
    public EhCacheManagerFactoryBean getEhCacheManagerFactoryBean() {  
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();  
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("config/ehcache-shiro.xml"));
        logger.debug(String.format("[Ehcache]初始化成功-ehcacheManager:%s",JSON.toJSONString(ehCacheManagerFactoryBean) ));
        return ehCacheManagerFactoryBean;  
    }  
  
    /**
     * @Description: EhCacheManager 引入Ehcache缓存
     * @return EhCacheManager
     * @Autor: Wubin@wanguo.com
     */
    public EhCacheCacheManager getSpringCacheManager(){
    	EhCacheCacheManager springEhCacheCacheManager = new EhCacheCacheManager();
    	springEhCacheCacheManager.setCacheManager(getEhCacheManagerFactoryBean().getObject());
    	return springEhCacheCacheManager;
    }
    
    /**
     * @Description: SpringCacheManagerWrapper springCache 管理ehcache 
     * @return SpringCacheManagerWrapper
     * @Autor: Jason
     */
	public SpringCacheManagerWrapper getSpringCacheManagerWrapper(){
		SpringCacheManagerWrapper springCacheManagerWrapper = new SpringCacheManagerWrapper();
		springCacheManagerWrapper.setCacheManager(getSpringCacheManager());
		return springCacheManagerWrapper;
	}
	
    
    /**
     * @Description: 凭证匹配器
     * @return RetryLimitHashedCredentialsMatcher
     * @Autor: wubin@wanuo.com
     */
	@Bean(name="hashedCredentialsMatcher")
	public RetryLimitHashedCredentialsMatcher hashedCredentialsMatcher(){
		RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(getSpringCacheManagerWrapper());
		retryLimitHashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
		retryLimitHashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
		return retryLimitHashedCredentialsMatcher;
	}

	
	
	/**
	 * @Description: 自定义操作realm
	 * @return OperatorRealm
	 * @Autor: Jason
	 */
	@Bean
	public OperatorRealm getOperatorRealm(){
		OperatorRealm operatorRealm = new OperatorRealm();
		operatorRealm.setCredentialsMatcher(hashedCredentialsMatcher());//匹配器
		operatorRealm.setCachingEnabled(false);
		
		//ehcahe 
		operatorRealm.setAuthorizationCachingEnabled(true);
		operatorRealm.setAuthorizationCacheName("authorizationCache");//authorizationCache
		return operatorRealm;
	}
    
    
    
    /**
     * @Description: Shiro生命周期处理器 
     * @return LifecycleBeanPostProcessor  - org.apache.shiro.spring.LifecycleBeanPostProcessor
     * @Autor: Jason
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    
    /**
	 * @Description: OperatorRealm 安全管理器   - core - ShiroFilterFactoryBean
	 * @return DefaultWebSecurityManager
	 * @Autor: Jason
	 */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getOperatorRealm()); //自定义realm
        logger.debug(String.format("[Shiro]初始化成功-securityManager:%s",JSON.toJSONString(securityManager)));
        return securityManager;
    }
    
    
    /**
     * @Description: 定义aop切面，用于代理如@RequiresPermissions注解的控制器，进行权限控制 
     * @return DefaultAdvisorAutoProxyCreator
     * @Autor: Jason
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    
    /**
     * @Description: 开启shiro aop注解支持 使用代理方式  所以需要开启代码支持
     * @return AuthorizationAttributeSourceAdvisor securityManager
     * @Autor: Jason
     */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager());
		return authorizationAttributeSourceAdvisor;
	}
 

}
