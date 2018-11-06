/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.boss.permission.shiro.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
* @Package：cn.ucaner.wx.app.boss.permission.shiro.filter   
* @ClassName：RcFormAuthenticationFilter   
* @Description：   <p> 自定义form表单认证过滤器<br/>
* 目的是：验证码过滤器发现验证码错误，不需要做认证过滤
* </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:47:12   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class RcFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		
		//failureKeyAttribute !=null 即存在问题 故停止继续做表的校验了 引用于login里面的登录校验之中  
		if (request.getAttribute(getFailureKeyAttribute()) != null) {
			//拒绝访问，不再校验账号和密码 
			return true;
		}
		return super.onAccessDenied(request, response, mappedValue);
	}
}
