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
* @ClassName：PmsOperatorLog   
* @Description：   <p> 权限管理-操作员操作日志</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:52:29   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class PmsOperatorLog extends PermissionBaseEntity {

	private static final long serialVersionUID = 742891253537618199L;

	private Long operatorId; // 操作员ID
	private String operatorName; // 操作员登录名
	private String operateType; // 操作类型（参与枚举:OperatorLogTypeEnum,1:增加,2:修改,3:删除,4:查询,5:登录）
	private String ip; // IP地址
	private String content; // 操作内容

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
