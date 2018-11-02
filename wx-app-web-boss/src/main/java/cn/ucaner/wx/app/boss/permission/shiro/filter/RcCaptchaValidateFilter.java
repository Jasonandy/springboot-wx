package cn.ucaner.wx.app.boss.permission.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @Package：cn.ucaner.wx.app.boss.permission.shiro.filter   
* @ClassName：RcCaptchaValidateFilter   
* @Description：   <p> RcCaptchaValidateFilter </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:47:29   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class RcCaptchaValidateFilter extends AccessControlFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(RcCaptchaValidateFilter.class);

	/**
	 * 是否开启验证码支持 - 默认为开启 
	 */
	private boolean captchaEbabled = true;

	/**
	 * 前台提交的验证码参数名 - 默认 - 初始化时候可以注入set注入
	 */
	private String captchaParam = "captchaCode";

	/**
	 *  验证码验证失败后存储到的属性名 - 默认 - 初始化bean的时候可以注入 set注入
	 */
	private String failureKeyAttribute = "shiroLoginFailure";


	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		
		/**
		 * 1、设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码 - html里面做控制 
		 *    <ftl>  里面做判断控制
		 */
		request.setAttribute("captchaEbabled", captchaEbabled);

		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		
		/**
		 * 2、判断验证码是否禁用 或不是表单提交（允许访问）
		 * captchaEbabled 没有被禁用 or 不是post方式提交数据 - 可以访问
		 */
		if (captchaEbabled == false || !"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
			return true;
		}
		
		// 获取页面提交的验证码
		String submitCaptcha = httpServletRequest.getParameter(captchaParam);
		// 获取session中的验证码
		String captcha = (String) httpServletRequest.getSession().getAttribute("rcCaptcha");
		
		/**
		 * 3、此时是表单提交，验证验证码是否正确
		 * 验证码的校验 - seesion:"rcCaptcha"  - 对比提交过来的验证码
		 */
		if (submitCaptcha.equals(captcha)) {
			logger.debug("原始匹配码为-{},验证码-{}校校验成功!",captcha,submitCaptcha);
			return true;
		}
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		/**
		 * 如果验证码失败了，存储失败key属性 - 前台展示 - html
		 */
		request.setAttribute(failureKeyAttribute, "验证码错误!");
		logger.debug("验证码校验错误,请重新尝试!");
		return true;
	}
	
	
	//--------------------------------SpringBean set 注入 -------------------------------------
	public void setCaptchaEbabled(boolean captchaEbabled) {
		this.captchaEbabled = captchaEbabled;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	public void setFailureKeyAttribute(String failureKeyAttribute) {
		this.failureKeyAttribute = failureKeyAttribute;
	}
	//--------------------------------SpringBean set 注入 -------------------------------------------

}
