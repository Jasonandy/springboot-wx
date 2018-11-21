/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.core.framework.mvc.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
* @Package：cn.ucaner.wx.app.core.framework.mvc.entity   
* @ClassName：BasePlatformEntity   
* @Description：   <p> 平台基类 </p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:50:47   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class BasePlatformEntity {
	/**
	 * 创建人id
	 */
	protected String cp;
	/**
	 * 创建人名称
	 */
	protected String cpName;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date ct;
	/**
	 * 修改人id
	 */
	protected String ep;

	/**
	 * 修改人名称
	 */
	protected String epName;
	/**
	 * 修改时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date et;

	/**
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}

	/**
	 * @param cp
	 *            the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp == null ? null : cp.trim();
	}

	/**
	 * @return the ct
	 */
	public Date getCt() {
		return ct;
	}

	/**
	 * @param ct
	 *            the ct to set
	 */
	public void setCt(Date ct) {
		this.ct = ct;
	}

	/**
	 * @return the ep
	 */
	public String getEp() {
		return ep;
	}

	/**
	 * @param ep
	 *            the ep to set
	 */
	public void setEp(String ep) {
		this.ep = ep == null ? null : ep.trim();
	}

	/**
	 * @return the et
	 */
	public Date getEt() {
		return et;
	}

	/**
	 * @param et
	 *            the et to set
	 */
	public void setEt(Date et) {
		this.et = et;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName == null ? null : cpName.trim();
	}

	public String getEpName() {
		return epName;
	}

	public void setEpName(String epName) {
		this.epName = epName == null ? null : epName.trim();
	}
}
