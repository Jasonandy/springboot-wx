package cn.ucaner.wx.app.gateway.shiro.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.ucaner.wx.app.gateway.shiro.principal.JWTToken;

/**
* @Package：cn.ucaner.wx.app.gateway.shiro.filter   
* @ClassName：JWTFilter   
* @Description：   <p> JWTFilter 接口鉴权filter preHandle->isAccessAllowed->isLoginAttempt->executeLogin </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午10:57:33   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class JWTFilter extends BasicHttpAuthenticationFilter{

	
	private Logger logger = LoggerFactory.getLogger(JWTFilter.class);
	
	/**
	 * Authorization - 请求头里面带的参数
	 */
	private  final static String  AUTHORIZATION = "Authorization";

	
	 /**
     * 1.对跨域提供支持 - preHandle - 对拦截器数据pre - 预处理.
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        
        /**
         * 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
         */
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

	/**
     * 2.如果带有 token，则对 token 进行检查，否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    	//判断请求的请求头是否带上 "Token"
        if (isLoginAttempt(request, response)) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                executeLogin(request, response);
                //return true;
            } catch (Exception e) {
                //token 错误
                responseError(response, e.getMessage());
            }
        }else {
        	 /*try {
                 executeLogin(request, response);
                 //return true;
             } catch (Exception e) {
                 //token 错误
                 responseError(response, e.getMessage());
             }*/
        	return true;//没有Authorization
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }
	
	/**
	 * 3.判断用户是否想要登入
	 * 检测header里面是否包含Authorization字段即可
	 */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(AUTHORIZATION);//判断是否带有token
        return authorization != null;
    }
    
    
    /**
     * 4.relam 里面做的校验处理
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader(AUTHORIZATION);//jwtToken 
        //将head的jwtToken传入校验
        JWTToken token = new JWTToken(authorization);
        logger.debug("Callback Api with jwtToken:{},Api被调用时传递的JWT参数.",authorization);
        //try {
		getSubject(request, response).login(token);//没有异常则表示是成功的
		//} catch (Exception e) {
			//e.printStackTrace();
			//logger.error("Api getSubject(request, response).login(token) 登陆异常!");
		//}
        return true;
    }


    
    /**
     * 将非法请求跳转到 /unauthorized/**   shiro 放行.
     */
    private void responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //设置编码，否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");
            //httpServletResponse.sendRedirect("/unauthorized/error" + message);
            httpServletResponse.sendRedirect("http://www.baidu.com/s?ie=UTF-8&wd=" + message);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
