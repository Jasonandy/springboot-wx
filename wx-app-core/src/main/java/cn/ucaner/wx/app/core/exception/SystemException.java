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

/**
* @Package：cn.ucaner.wx.app.core.exception   
* @ClassName：SystemException   
* @Description：   <p> 系统异常 </p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:33:22   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class SystemException extends RuntimeException {
	
	private static final long serialVersionUID = 1401593546385403720L;

	/**
	 * system 系统异常
	 */
	public SystemException() {
		super();
	}

	/**
	 * 系统异常
	 * @param message 异常信息
	 */
	public SystemException(String message) {
		super(message);
	}

	/**
	 * 系统异常
	 * @param cause 异常语句
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * 系统异常
	 * @param message 异常信息
	 * @param cause   异常语句
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
}
