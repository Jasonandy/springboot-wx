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

	public SystemException() {
		super();
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
}
