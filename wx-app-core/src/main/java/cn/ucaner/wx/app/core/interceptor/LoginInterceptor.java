package cn.ucaner.wx.app.core.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

/**
* @Package：cn.ucaner.wx.app.core.interceptor   
* @ClassName：LoginInterceptor   
* @Description：   <p> 登录拦截器</p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:48:41   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
//@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	public static final String LOGIN_SUCCESS_FLAG = "ucaner-wx-apps-login";
	
	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 如果返回true
	 * 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session = request.getSession(true);
		//seesion判断登录标志位flag
		if (session.getAttribute(LOGIN_SUCCESS_FLAG) == null){
			Map<String, Object> map = new HashMap<String, Object>();
			//状态码
			map.put("statusCode", "100001");
			PrintWriter out = response.getWriter();
			out.println(JSON.toJSONString(map));
			return false;
		}
		return true;
	}

	
	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception{
		
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception{
	
	}
}
