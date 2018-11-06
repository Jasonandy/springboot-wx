/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.boss.permission.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ucaner.wx.app.boss.controller.common.BaseController;
import cn.ucaner.wx.app.boss.permission.biz.PmsMenuBiz;
import cn.ucaner.wx.app.core.dwz.DwzAjax;
import cn.ucaner.wx.app.core.enums.PublicEnum;
import cn.ucaner.wx.app.core.enums.PublicStatusEnum;
import cn.ucaner.wx.app.service.permission.entity.PmsMenu;
import cn.ucaner.wx.app.service.permission.service.PmsMenuService;

/**
* @Package：cn.ucaner.wx.app.boss.permission.controller   
* @ClassName：PmsMenuController   
* @Description：   <p> 权限-菜单控制器 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:43:00   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Controller
@RequestMapping("/pms/menu")
public class PmsMenuController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PmsMenuController.class);

	@Autowired
	private PmsMenuService pmsMenuService;
	@Autowired
	private PmsMenuBiz pmsMenuBiz;

	/**
	 * 列出要管理的菜单.
	 * 
	 * @return PmsMenuList .
	 */
	@RequiresPermissions("pms:menu:view")
	@RequestMapping("/list")
	public String listPmsMenu(HttpServletRequest req, Model model) {
		String editMenuController = "pms/menu/editUI";
		String str = pmsMenuBiz.getTreeMenu(editMenuController);
		model.addAttribute("tree", str);
		return "pms/pmsMenuList";
	}

	/**
	 * 进入新菜单添加页面.
	 * 
	 * @return PmsMenuAdd .
	 */
	@RequiresPermissions("pms:menu:add")
	@RequestMapping("/addUI")
	public String addPmsMenuUI(HttpServletRequest req, PmsMenu pmsMenu, Model model, Long pid) {
		if (null != pid) {
			PmsMenu parentMenu = pmsMenuService.getById(pid);
			pmsMenu.setParent(parentMenu);
			model.addAttribute(pmsMenu);
		}
		return "pms/pmsMenuAdd";
	}

	/**
	 * 保存新增菜单.
	 * 
	 * @return operateSuccess or operateError .
	 */
	@RequiresPermissions("pms:menu:add")
	@RequestMapping("/add")
	public String addPmsMenu(HttpServletRequest req, PmsMenu pmsMenu, Model model, DwzAjax dwz) {
		try {
			String name = pmsMenu.getName();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isLeaf", "YES");
			map.put("name", name);
			List<PmsMenu> list = pmsMenuService.getMenuByNameAndIsLeaf(map);
			if (list.size() > 0) {
				return operateError("同级菜单名称不能重复", model);
			}
			pmsMenu.setCreater(getPmsOperator().getLoginName());
			pmsMenu.setStatus(PublicStatusEnum.ACTIVE.name());
			pmsMenu.setIsLeaf("YES");
			if (null != pmsMenu.getParent().getId()) {
				pmsMenu.setLevel(pmsMenu.getParent().getLevel()+1);
			}else{
				pmsMenu.setLevel(1L);
				PmsMenu parent = new PmsMenu();
				parent.setId(0l);
				pmsMenu.setParent(parent);
			}
			pmsMenuService.savaMenu(pmsMenu);
		} catch (Exception e) {
			// 记录系统操作日志
			logger.error("== addPmsMenu exception:", e);
			return operateError("添加菜单出错", model);
		}
		return operateSuccess(model, dwz);
	}

	/**
	 * 进入菜单修改页面.
	 * 
	 * @return
	 */
	@RequiresPermissions("pms:menu:edit")
	@RequestMapping("/editUI")
	public String editPmsMenuUI(HttpServletRequest req, Long id, Model model) {
		if (null != id) {
			PmsMenu pmsMenu = pmsMenuService.getById(id);
			model.addAttribute(pmsMenu);
		}
		return "pms/pmsMenuEdit";
	}

	/**
	 * 保存要修改的菜单.
	 * 
	 * @return
	 */
	@RequiresPermissions("pms:menu:edit")
	@RequestMapping("/edit")
	public String editPmsMenu(HttpServletRequest req, PmsMenu menu, Model model, DwzAjax dwz) {
		try {
			PmsMenu parentMenu = menu.getParent();
			if (null == parentMenu) {
				parentMenu = new PmsMenu();
				parentMenu.setId(0L);
			}
			menu.setParent(parentMenu);
			pmsMenuService.update(menu);
			// 记录系统操作日志
			return operateSuccess(model, dwz);
		} catch (Exception e) {
			// 记录系统操作日志
			logger.error("== editPmsMenu exception:", e);
			return operateError("保存菜单出错", model);
		}

	}

	/**
	 * 删除菜单.
	 * 
	 * @return
	 */
	@RequiresPermissions("pms:menu:delete")
	@RequestMapping("/delete")
	public String delPmsMenu(HttpServletRequest req, Long menuId, Model model, DwzAjax dwz) {
		try {
			if (menuId == null || menuId == 0) {
				return operateError("无法获取要删除的数据", model);
			}
			PmsMenu menu = pmsMenuService.getById(menuId);
			if (menu == null) {
				return operateError("无法获取要删除的数据", model);
			}
			Long parentId = menu.getParent().getId(); // 获取父菜单ID

			// 先判断此菜单下是否有子菜单
			List<PmsMenu> childMenuList = pmsMenuService.listByParentId(menuId);
			if (childMenuList != null && !childMenuList.isEmpty()) {
				return operateError("此菜单下关联有【" + childMenuList.size() + "】个子菜单，不能支接删除!", model);
			}

			// 删除掉菜单
			pmsMenuService.delete(menuId);

			// 删除菜单后，要判断其父菜单是否还有子菜单，如果没有子菜单了就要装其父菜单设为叶子节点
			List<PmsMenu> childList = pmsMenuService.listByParentId(parentId);
			if (childList == null || childList.isEmpty()) {
				// 此时要将父菜单设为叶子
				PmsMenu parent = pmsMenuService.getById(parentId);
				parent.setIsLeaf(PublicEnum.YES.name());
				pmsMenuService.update(parent);
			}
			// 记录系统操作日志
			return operateSuccess(model, dwz);
		} catch (Exception e) {
			// 记录系统操作日志
			logger.error("== delPmsMenu exception:", e);
			return operateError("删除菜单出错", model);
		}
	}

}
