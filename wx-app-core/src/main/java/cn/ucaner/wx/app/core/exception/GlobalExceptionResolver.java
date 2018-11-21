/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.core.exception;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @Package：cn.ucaner.wx.app.core.exception   
* @ClassName：GlobalExceptionResolver   
* @Description：   <p>  全局异常处理器</p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:33:10   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
@ControllerAdvice 
public class GlobalExceptionResolver {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Map<String, String> exceptionHandler(HttpServletRequest request, Exception e) {
		// 对捕获的异常进行处理并打印日志等，之后返回json数据，方式与Controller相同
		Map<String, String> map = new HashMap<String, String>();
		map.put("statusCode", "100003");
		return map;
	}

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseBody
	public Map<String, String> runtimeExceptionHandler(HttpServletRequest request, RuntimeException e) {
		// 对捕获的异常进行处理并打印日志等，之后返回json数据，方式与Controller相同
		Map<String, String> map = new HashMap<String, String>();
		map.put("statusCode", "100004");
		map.put("error", e.getMessage());
		return map;
	}

	@ExceptionHandler(value = SQLException.class)
	@ResponseBody
	public Map<String, String> sqlExceptionHandler(HttpServletRequest request, SQLException e) {
		// 对捕获的异常进行处理并打印日志等，之后返回json数据，方式与Controller相同
		Map<String, String> map = new HashMap<String, String>();
		map.put("statusCode", "100005");
		return map;
	}
	
	@ExceptionHandler(value = IOException.class)
	@ResponseBody
	public Map<String, String> ioExceptionHandler(HttpServletRequest request, IOException e) {
		// 对捕获的异常进行处理并打印日志等，之后返回json数据，方式与Controller相同
		Map<String, String> map = new HashMap<String, String>();
		map.put("statusCode", "100006");
		return map;
	}

}