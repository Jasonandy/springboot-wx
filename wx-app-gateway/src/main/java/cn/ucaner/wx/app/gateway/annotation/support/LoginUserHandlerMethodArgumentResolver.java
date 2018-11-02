package cn.ucaner.wx.app.gateway.annotation.support;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.ucaner.wx.app.gateway.annotation.LoginUser;

/**
* @Package：cn.ucaner.wx.app.gateway.annotation.support   
* @ClassName：LoginUserHandlerMethodArgumentResolver   
* @Description：   <p>  SpringMVC3.1引入了HandlerMethodArgumentResolver接口,Spring调用该接口实现Controller的参数装配 </p>
* <code> 对参数进行判断,达到鉴权的效果 or 考虑采用 filter的方式 判断 是否放行  SpringMvc </code>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:27:27   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	/**
	 * 登录的cookie标志位
	 */
    public static final String LOGIN_TOKEN_KEY = "loginToken";
    
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(String.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,NativeWebRequest request, WebDataBinderFactory factory) throws Exception {

    	//这里做是否登录的逻辑判断
    	
        return "wubin@wanguo.com";
    }
}
