/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.boss.controller.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.ucaner.wx.app.boss.permission.shiro.filter.RcCaptchaFilter;

/**
* @Package：cn.ucaner.wx.app.boss.controller.config   
* @ClassName：WebFilterConfig   
* @Description：   <p> WebFilterConfig 相当于web.xml的filter
*  验证码过滤器需要放到Shiro之后 因为Shiro将包装HttpSession 如果不 可能造成两次的sesison id 不一样
*  
*  Spring boot 会按照order值的大小,从小到大的顺序来依次过滤
*  {@link https://blog.csdn.net/mzh1992/article/details/66970924/}
* </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:40:40   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Configuration
public class WebFilterConfig {
	
	
	/**
	 * @Description: org.springframework.web.filter.DelegatingFilterProxy
	 * @return FilterRegistrationBean shiro 安全过滤器
	 * DelegatingFilterProxy就是一个对于servlet filter的代理，用这个类的好处主要是通过Spring容器来管理servlet filter的生命周期，
	 * 还有就是如果filter中需要一些Spring容器的实例，可以通过spring直接注入，另外读取一些配置文件这些便利的操作都可以通过Spring来配置实现。
	 * @Autor: Jason
	 */
	/*@Bean(name="shiroFilter")
	public FilterRegistrationBean initShiroFilter() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(getDelegatingFilterProxy());
	    registration.addUrlPatterns("/*");
	    registration.setName("shiroFilter");
	    registration.setAsyncSupported(true);
	    return registration;
	} 
	*/
	
	/**
	 * @Description: DelegatingFilterProxy 
	 * @return Filter
	 * @Autor: Jason
	 */
	/*@Bean(name="delegatingFilterProxy")
    public Filter  getDelegatingFilterProxy() {
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
		delegatingFilterProxy.setTargetFilterLifecycle(true);
    	return delegatingFilterProxy;
    }*/
	
	/**
	 * @Description: Web.xml filter配置化 
	 * @return FilterRegistrationBean
	 * @Autor: Jason
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean webXmlFilterRegister() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(RcCaptchaFilter());
	    registration.addUrlPatterns("/rcCaptcha.jpg");
	    registration.setName("rcCaptchaFilter");//rcCaptchaFilter
	    registration.setAsyncSupported(true);
	    registration.setOrder(Integer.MAX_VALUE); //从小到到大 先shrio优先 再验证码
	    return registration;
	} 
	
	
	/**
	 * @Description: RcCaptchaFilter
	 * @return Filter
	 * @Autor: Jason
	 */
	@Bean(name="rcCaptchaFilter")
    public Filter  RcCaptchaFilter() {
    	RcCaptchaFilter rcCaptchaFilter = new RcCaptchaFilter();
    	return rcCaptchaFilter;
    }

}
