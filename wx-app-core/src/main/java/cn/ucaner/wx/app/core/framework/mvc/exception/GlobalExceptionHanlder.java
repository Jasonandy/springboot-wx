package cn.ucaner.wx.app.core.framework.mvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
* @Package：cn.ucaner.wx.app.core.framework.mvc.exception   
* @ClassName：GlobalExceptionHanlder   
* @Description：   <p> 全局的handler异常处理器，前后端系统合一,SimpleMappingExceptionResolver不能满足需求，需自定义异常处理器 </p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:50:10   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
@Component
public class GlobalExceptionHanlder implements HandlerExceptionResolver {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHanlder.class);

	/**
	* GlobalExceptionHanlder. - 全局异常处理器
	 */
	public GlobalExceptionHanlder() {
		logger.info("[Ucaner-Core]启用全局异常处理器...");
	}
	
	/**
	 * 对系统和业务里的异常做处理
	 * 
	 * 判断异常并进行分类,提供全局的异常处理机制.
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (handler != null && handler instanceof HandlerMethod) {
			logger.info("当前的异常为:{}",JSON.toJSONString(ex));//这里分批做处理
		}
		// 阻止 spring mvc 继续寻找视图
		return new ModelAndView();
	}

}
