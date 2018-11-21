/**
 *<html>
 *<body>
 *	<P> Copyright(c)  ● JasonInternational ◆ Jason <jasonandy@hotail.com></p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.core.vo;

import java.io.Serializable;

/**
* @Package：cn.ucaner.wx.app.core.vo   
* @ClassName：RespBody   
* @Description：   <p>  api接口响应 - 要整合微信官方错误码  后期optz </p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:34:44   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class RespBody implements Serializable{

	private static final long serialVersionUID = -6599447507957097341L;
	
	/**
	 * 状态
	 */
	private Status status;
	/**
	 * 结果
	 */
	private Object result;
	/**
	 * 消息描述
	 */
	private String message;

	public RespBody() {
		super();
	}

	public RespBody(Status status) {
		super();
		this.status = status;
	}

	public RespBody(Status status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public RespBody(Status status, Object result) {
		super();
		this.status = status;
		this.result = result;
	}

	public RespBody(Status status, Object result, String message) {
		super();
		this.status = status;
		this.result = result;
		this.message = message;
	}

	/**
	 * 结果类型信息
	 */
	public enum Status {
		OK, ERROR, FAIL
	}

	/**
	 * 添加成功结果信息
	 */
	public void addOK(String message) {
		this.message = message;
		this.status = Status.OK;
	}

	/**
	 * 添加成功结果信息
	 */
	public void addOK(Object result, String message) {
		this.message = message;
		this.status = Status.OK;
		this.result = result;
	}

	/**
	 * 添加错误消息
	 */
	public void addError(String message) {
		this.message = message;
		this.status = Status.ERROR;
	}
	
	public void addFail(String message) {
		this.message = message;
		this.status = Status.ERROR;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
