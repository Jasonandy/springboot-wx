/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.service.permission.entity;

/**
* @Package：cn.ucaner.wx.app.service.permission.entity   
* @ClassName：PmsOperator   
* @Description：   <p> 权限管理-操作员</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:52:14   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class PmsOperator extends PermissionBaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 登录名
	 */
	private String loginName;
	
	/**
	 * 登录密码
	 */
	private String loginPwd;
	
	/**
	 * 姓名
	 */
	private String realName; 
	
	/**
	 * 手机号
	 */
	private String mobileNo;  

	/**
	 * 操作员类型 - admin:超级管理员 common:普通操作员
	 * 
	 * 超级管理员由系统初始化时添加,不能删除.
	 */
	private String type; 
	
	
	private String salt;// 盐

	/**
	 * 登录名
	 * @return
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 登录名
	 * 
	 * @return
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 登录密码
	 * 
	 * @return
	 */
	public String getLoginPwd() {
		return loginPwd;
	}

	/**
	 * 登录密码
	 * @return
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	/**
	 * 姓名
	 * 
	 * @return
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * 姓名
	 * 
	 * @return
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 手机号
	 * 
	 * @return
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * 手机号
	 * 
	 * @return
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * 操作员类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 操作员类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 盐
	 * @return
	 */
	public String getsalt() {
		return salt;
	}

	/**
	 * 盐
	 * @param salt
	 */
	public void setsalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 认证加密的盐
	 * @return loginName + salt
	 */
	public String getCredentialsSalt() {
		return loginName + salt;
	}

	public PmsOperator() {

	}

}
