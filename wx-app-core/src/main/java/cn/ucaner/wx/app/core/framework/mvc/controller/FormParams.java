/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.core.framework.mvc.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
* @Package：cn.ucaner.wx.app.core.framework.mvc.controller   
* @ClassName：FormParams   
* @Description：   <p>  FormParams  表单的参数封装base </p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:52:34   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class FormParams implements Serializable {

	private static final long serialVersionUID = -5819554419875365914L;

	private Map<String, Object> params = new HashMap<String, Object>();

	public FormParams() {
		super();
	}

	public FormParams(Map<String, Object> params) {
		super();
		this.params = params;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
