package cn.ucaner.wx.app.boss.controller.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.ucaner.wx.app.boss.controller.common.BaseController;
import cn.ucaner.wx.app.core.dwz.DWZ;
import cn.ucaner.wx.app.core.dwz.DwzAjax;
import cn.ucaner.wx.app.service.permission.entity.PmsOperator;
import cn.ucaner.wx.app.service.permission.exception.PermissionException;
import cn.ucaner.wx.app.service.permission.service.PmsMenuService;
import cn.ucaner.wx.app.service.permission.service.PmsOperatorRoleService;

/**
* @Package：cn.ucaner.wx.app.boss.controller.login   
* @ClassName：LoginController   
* @Description：   <p>  LoginController 登录控制器 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:41:49   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Controller
public class LoginController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private PmsOperatorRoleService pmsOperatorRoleService;
	
	
	@Autowired
	private PmsMenuService pmsMenuService;

	/**
	 * @Description:  进入后台登陆页面 
	 * @param req
	 * @param model
	 * @return String
	 * @Autor: Jason
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest req, Model model) {

		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (PermissionException.class.getName().equals(exceptionClassName)) {
			error = "网络异常,请联系管理员";
		} else if (exceptionClassName != null) {
			error = "错误提示：" + exceptionClassName;
		}
		model.addAttribute("message", error);
		return "system/login";
	}

	/**
	 * @Description: 登陆后台管理系统.
	 * @param req
	 * @param model
	 * @return String
	 * @Autor: Jason
	 */
	@RequestMapping("/")
	public String index(HttpServletRequest req, Model model) {
		PmsOperator pmsOperator = (PmsOperator) this.getSession().getAttribute("PmsOperator");
		try {
			String tree = this.buildOperatorPermissionMenu(pmsOperator);
			model.addAttribute("tree", tree);
		} catch (PermissionException e) {
			logger.error("登录异常:" + e.getMessage());
			model.addAttribute("message", e.getMessage());
			return "system/login";
		}
		return "system/index";

	}

	/**
	 * @Description: 进入退出系统确认页面 
	 * @return String
	 * @Autor: Jason
	 */
	@RequestMapping(value = "/admin/confirm", method = RequestMethod.GET)
	public String confirm() {
		return "system/confirm";
	}

	/**
	 * @Description:  退出系统
	 * @param request
	 * @param model
	 * @return String
	 * @Autor: Jason
	 */
	@RequestMapping(value = "/admin/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, Model model) {
		// 不是以form的形式提交的数据,要new一个DwzAjax对象
		DwzAjax dwz = new DwzAjax();
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("employee");
			logger.info("***clean session success!***");
		} catch (Exception e) {
			logger.error(e.getMessage());
			dwz.setStatusCode(DWZ.ERROR);
			dwz.setMessage("退出系统时系统出现异常，请通知系统管理员！");
			model.addAttribute("dwz", dwz);
			return "admin.common.ajaxDone";
		}
		return "admin.login";
	}

	/**
	 * @Description: 获取用户的菜单权限 
	 * @param pmsOperator
	 * @return
	 * @throws PermissionException String
	 * @Autor: Jason
	 */
	private String buildOperatorPermissionMenu(PmsOperator pmsOperator) throws PermissionException {
		// 根据用户ID得到该用户的所有角色拼成的字符串
		String roleIds = pmsOperatorRoleService.getRoleIdsByOperatorId(pmsOperator.getId());
		if (StringUtils.isBlank(roleIds)) {
			logger.error("==>用户[" + pmsOperator.getLoginName() + "]没有配置对应的权限角色");
			throw new RuntimeException("该帐号已被取消所有系统权限");
		}
		// 根据操作员拥有的角色ID,构建管理后台的树形权限功能菜单
		return this.buildPermissionTree(roleIds);
	}

	/**
	 * @Description: 根据操作员拥有的角色ID,构建管理后台的树形权限功能菜单
	 * @param roleIds
	 * @return
	 * @throws PermissionException String
	 * @Autor: Jason
	 */
	@SuppressWarnings("rawtypes")
	public String buildPermissionTree(String roleIds) throws PermissionException {
		List treeData = null;
		try {
			treeData = pmsMenuService.listByRoleIds(roleIds);
			//if (StringUtils.isEmpty((CharSequence) treeData)) {
			if (treeData == null || treeData.isEmpty()) {
				logger.error("用户没有分配菜单权限");
				throw new PermissionException(PermissionException.PERMISSION_USER_NOT_MENU, "该用户没有分配菜单权限"); // 该用户没有分配菜单权限
			}
		} catch (Exception e) {
			logger.error("根据角色查询菜单出现错误", e);
			throw new PermissionException(PermissionException.PERMISSION_QUERY_MENU_BY_ROLE_ERROR, "根据角色查询菜单出现错误"); // 查询当前角色的
		}
		StringBuffer strJson = new StringBuffer();
		buildAdminPermissionTree("0", strJson, treeData);
		return strJson.toString();
	}

	/**
	 * @Description: 构建管理后台的树形权限功能菜单
	 * @param pId
	 * @param treeBuf
	 * @param menuList void
	 * @Autor: Jason
	 */
	@SuppressWarnings("rawtypes")
	private void buildAdminPermissionTree(String pId, StringBuffer treeBuf, List menuList) {

		List<Map> listMap = getSonMenuListByPid(pId.toString(), menuList);
		for (Map map : listMap) {
			String id = map.get("id").toString();// id
			String name = map.get("name").toString();// 名称
			String isLeaf = map.get("isLeaf").toString();// 是否叶子
			String level = map.get("level").toString();// 菜单层级（1、2、3、4）
			String url = map.get("url").toString(); // ACTION访问地址
			String navTabId = "";
			if (!StringUtils.isEmpty((CharSequence) map.get("targetName"))) {
				navTabId = map.get("targetName").toString(); // 用于刷新查询页面
			}

			if ("1".equals(level)) {
				treeBuf.append("<div class='accordionHeader'>");
				treeBuf.append("<h2> <span>Folder</span> " + name + "</h2>");
				treeBuf.append("</div>");
				treeBuf.append("<div class='accordionContent'>");
			}

			if ("YES".equals(isLeaf)) {
				treeBuf.append("<li><a href='" + url + "' target='navTab' rel='" + navTabId + "'>" + name + "</a></li>");
			} else {

				if ("1".equals(level)) {
					treeBuf.append("<ul class='tree treeFolder'>");
				} else {
					treeBuf.append("<li><a>" + name + "</a>");
					treeBuf.append("<ul>");
				}

				buildAdminPermissionTree(id, treeBuf, menuList);

				if ("1".equals(level)) {
					treeBuf.append("</ul>");
				} else {
					treeBuf.append("</ul></li>");
				}

			}

			if ("1".equals(level)) {
				treeBuf.append("</div>");
			}
		}

	}

	/**
	 * @Description: 根据(pId)获取(menuList)中的所有子菜单集合.
	 * @param pId 父菜单ID.
	 * @param menuList 菜单集合.
	 * @return List<Map> sonMenuList
	 * @Autor: Jason
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Map> getSonMenuListByPid(String pId, List menuList) {
		List sonMenuList = new ArrayList<Object>();
		for (Object menu : menuList) {
			Map map = (Map) menu;
			if (map != null) {
				String parentId = map.get("pId").toString();// 父id
				if (parentId.equals(pId)) {
					sonMenuList.add(map);
				}
			}
		}
		return sonMenuList;
	}

}
