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
* @ClassName：CheckParams   
* @Description：   <p> optz 后期优化为 百度的fluent流式校验工具  or HibernateValidator [版本迭代之后进行相关的优化20180725]</p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:53:05   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class CheckParams implements Serializable {

	private static final long serialVersionUID = 5029944078684863514L;
	/**
	 * 校验参数
	 */
	private Map<String, Serializable> params = new HashMap<String, Serializable>();

	public CheckParams() {
		super();
	}

	/**
	 * @param params
	 */
	public CheckParams(Map<String, Serializable> params) {
		super();
		this.params = params;
	}

	/**
	 * @return the params
	 */
	public Map<String, Serializable> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Map<String, Serializable> params) {
		this.params = params;
	}

}
