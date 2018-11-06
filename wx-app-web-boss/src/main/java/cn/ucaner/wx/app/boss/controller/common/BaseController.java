/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.boss.controller.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;

import cn.ucaner.wx.app.core.dwz.DWZ;
import cn.ucaner.wx.app.core.dwz.DwzAjax;
import cn.ucaner.wx.app.service.permission.entity.PmsOperator;

/**
* @Package：cn.ucaner.wx.app.boss.controller.common   
* @ClassName：BaseController   
* @Description：   <p> controller基类 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:40:15   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public abstract class BaseController {

	/**
	 * 获取shiro 的session
	 * 
	 * @return
	 */
	protected Session getSession() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		return session;
	}

	/**
	 * 获取当前用户信息
	 * 
	 * @return
	 */
	protected PmsOperator getPmsOperator() {
		PmsOperator operator = (PmsOperator) this.getSession().getAttribute("PmsOperator");
		return operator;
	}

	/**
	 * 响应DWZ的ajax失败请求,跳转到ajaxDone视图.
	 * 
	 * @param message
	 *            提示消息.
	 * @param model
	 *            model.
	 * @return ajaxDone .
	 */
	protected String operateError(String message, Model model) {
		DwzAjax dwz = new DwzAjax();
		dwz.setStatusCode(DWZ.ERROR);
		dwz.setMessage(message);
		model.addAttribute("dwz", dwz);
		return "common/ajaxDone";
	}

	/**
	 * 响应DWZ的ajax失败成功,跳转到ajaxDone视图.
	 * 
	 * @param model
	 *            model.
	 * @param dwz
	 *            页面传过来的dwz参数
	 * @return ajaxDone .
	 */
	protected String operateSuccess(Model model, DwzAjax dwz) {
		dwz.setStatusCode(DWZ.SUCCESS);
		dwz.setMessage("操作成功");
		model.addAttribute("dwz", dwz);
		return "common/ajaxDone";
	}

}
