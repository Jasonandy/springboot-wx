/**
 * <html>
 * <body>
 *  <P> Copyright 1994 JsonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 19941115</p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.wx.app.boss.permission.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.boss.controller.common.BaseController;
import cn.ucaner.wx.app.boss.permission.utils.ValidateUtils;
import cn.ucaner.wx.app.core.dwz.DwzAjax;
import cn.ucaner.wx.app.core.enums.PublicStatusEnum;
import cn.ucaner.wx.app.service.permission.entity.PmsPermission;
import cn.ucaner.wx.app.service.permission.entity.PmsRole;
import cn.ucaner.wx.app.service.permission.service.PmsPermissionService;
import cn.ucaner.wx.app.service.permission.service.PmsRoleService;

/**
* @Package：cn.ucaner.wx.app.boss.permission.controller   
* @ClassName：PmsPermissionController   
* @Description：   <p> 权限管理模块的Permission类，包括权限点管理、角色管理、操作员管理  </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:49:47   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Controller
@RequestMapping("/pms/permission")
public class PmsPermissionController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PmsPermissionController.class);
	
	@Autowired
	private PmsPermissionService pmsPermissionService;
	
	@Autowired
	private PmsRoleService pmsRoleService;


	/**
	 * 分页列出pms权限，也可根据权限获权限名称进行查询.
	 * 
	 * @return PmsPermissionList or operateError.
	 */
	@RequiresPermissions("pms:permission:view")
	@RequestMapping("/list")
	public String listPmsPermission(HttpServletRequest req, Page<PmsPermission> pageParam, PmsPermission pmsPermission, Model model) {
		try {
			PageInfo<PmsPermission> pmsPermissionList = pmsPermissionService.listPage(pageParam, pmsPermission);
			model.addAttribute(pmsPermissionList);
			model.addAttribute("pageParam", pageParam);
			return "pms/pmsPermissionList";
		} catch (Exception e) {
			logger.error("== listPmsPermission exception:", e);
			return operateError("获取数据失败", model);
		}
	}

	/**
	 * 进入添加Pms权限页面 .
	 * 
	 * @return addPmsPermissionUI .
	 */
	@RequiresPermissions("pms:permission:add")
	@RequestMapping("/addUI")
	public String addPmsPermissionUI() {
		return "pms/pmsPermissionAdd";
	}

	/**
	 * 将权限信息保存到数据库中
	 * 
	 * @return operateSuccess or operateError.
	 */
	@RequiresPermissions("pms:permission:add")
	@RequestMapping("/add")
	public String addPmsPermission(HttpServletRequest req, PmsPermission pmsPermission, Model model, DwzAjax dwz) {
		try {

			// 表单数据校验
			String validateMsg = validatePmsPermission(pmsPermission);
			if (StringUtils.isNotBlank(validateMsg)) {
				return operateError(validateMsg, model); // 返回错误信息
			}

			String permissionName = pmsPermission.getPermissionName().trim();
			String permission = pmsPermission.getPermission();
			// 检查权限名称是否已存在
			PmsPermission checkName = pmsPermissionService.getByPermissionName(permissionName);
			if (checkName != null) {
				return operateError("权限名称【" + permissionName + "】已存在", model);
			}
			// 检查权限是否已存在
			PmsPermission checkPermission = pmsPermissionService.getByPermission(permission);
			if (checkPermission != null) {
				return operateError("权限【" + permission + "】已存在", model);
			}
			pmsPermission.setStatus(PublicStatusEnum.ACTIVE.name());
			pmsPermission.setCreater(getPmsOperator().getLoginName());
			pmsPermission.setCreateTime(new Date());
			pmsPermissionService.saveData(pmsPermission);

			return operateSuccess(model, dwz); // 返回operateSuccess视图,并提示“操作成功”
		} catch (Exception e) {
			logger.error("== addPmsPermission exception:", e);
			return operateError("保存失败", model);
		}
	}

	/**
	 * 校验Pms权限信息.
	 * 
	 * @param pmsPermission
	 *            .
	 * @return msg .
	 */
	private String validatePmsPermission(PmsPermission pmsPermission) {
		String msg = ""; // 用于存放校验提示信息的变量
		String permissionName = pmsPermission.getPermissionName(); // 权限名称
		String permission = pmsPermission.getPermission(); // 权限标识
		String desc = pmsPermission.getRemark(); // 权限描述
		// 权限名称 permissionName
		msg += ValidateUtils.lengthValidate("权限名称", permissionName, true, 3, 90);
		// 权限标识 permission
		msg += ValidateUtils.lengthValidate("权限标识", permission, true, 3, 100);
		// 描述 desc
		msg += ValidateUtils.lengthValidate("描述", desc, true, 3, 60);
		return msg;
	}

	/**
	 * 转到权限修改页面 .
	 * 
	 * @return editPmsPermissionUI or operateError .
	 */
	@RequiresPermissions("pms:permission:edit")
	@RequestMapping("/editUI")
	public String editPmsPermissionUI(HttpServletRequest req, Long id, Model model) {
		try {
			PmsPermission pmsPermission = pmsPermissionService.getDataById(id);
			model.addAttribute("pmsPermission", pmsPermission);
			return "pms/pmsPermissionEdit";
		} catch (Exception e) {
			logger.error("== editPmsPermissionUI exception:", e);
			return operateError("获取数据失败", model);
		}
	}

	/**
	 * 保存修改后的权限信息
	 * 
	 * @return operateSuccess or operateError .
	 */
	@RequiresPermissions("pms:permission:edit")
	@RequestMapping("/edit")
	public String editPmsPermission(HttpServletRequest req, PmsPermission permission, Model model, DwzAjax dwz) {
		try {
			Long id = permission.getId();
			PmsPermission pmsPermission = pmsPermissionService.getDataById(id);
			if (pmsPermission == null) {
				return operateError("无法获取要修改的数据", model);
			} else {

				String permissionName = permission.getPermissionName();
				String remark = permission.getRemark();

				pmsPermission.setPermissionName(permissionName);
				pmsPermission.setRemark(remark);

				// 表单数据校验
				String validateMsg = validatePmsPermission(pmsPermission);
				if (StringUtils.isNotBlank(validateMsg)) {
					return operateError(validateMsg, model); // 返回错误信息
				}

				// 检查权限名称是否已存在
				PmsPermission checkName = pmsPermissionService.getByPermissionNameNotEqId(permissionName, id);
				if (checkName != null) {
					return operateError("权限名称【" + permissionName + "】已存在", model);
				}

				pmsPermissionService.updateData(pmsPermission);

				return operateSuccess(model, dwz);
			}
		} catch (Exception e) {
			logger.error("== editPmsPermission exception:", e);
			return operateError("修改失败", model);
		}
	}

	/**
	 * 删除一条权限记录
	 * 
	 * @return operateSuccess or operateError .
	 */
	@RequiresPermissions("pms:permission:delete")
	@RequestMapping("/delete")
	public String deletePmsPermission(HttpServletRequest req, Long permissionId, Model model, DwzAjax dwz) {
		try {
			PmsPermission permission = pmsPermissionService.getDataById(permissionId);
			if (permission == null) {
				return operateError("无法获取要删除的数据", model);
			}
			// 判断此权限是否关联有角色，要先解除与角色的关联后才能删除该权限
			List<PmsRole> roleList = pmsRoleService.listByPermissionId(permissionId);
			if (roleList != null && !roleList.isEmpty()) {
				return operateError("权限【" + permission.getPermission() + "】关联了【" + roleList.size() + "】个角色，要解除所有关联后才能删除。其中一个角色名为:" + roleList.get(0).getRoleName(), model);
			}
			pmsPermissionService.delete(permissionId);
			return operateSuccess(model, dwz); // 返回operateSuccess视图,并提示“操作成功”
		} catch (Exception e) {
			logger.error("== deletePmsPermission exception:", e);
			return operateError("删除限权异常", model);
		}
	}
}
