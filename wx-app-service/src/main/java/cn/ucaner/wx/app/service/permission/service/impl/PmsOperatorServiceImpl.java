package cn.ucaner.wx.app.service.permission.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.core.enums.PublicStatusEnum;
import cn.ucaner.wx.app.service.permission.dao.PmsOperatorDao;
import cn.ucaner.wx.app.service.permission.dao.PmsOperatorRoleDao;
import cn.ucaner.wx.app.service.permission.entity.PmsOperator;
import cn.ucaner.wx.app.service.permission.entity.PmsOperatorRole;
import cn.ucaner.wx.app.service.permission.service.PmsOperatorService;

/**
* @Package：cn.ucaner.wx.app.service.permission.service.impl   
* @ClassName：PmsOperatorServiceImpl   
* @Description：   <p>  操作员service接口实现 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:59:05   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Service("pmsOperatorService")
public class PmsOperatorServiceImpl implements PmsOperatorService {
	
	@Autowired
	private PmsOperatorDao pmsOperatorDao;

	@Autowired
	private PmsOperatorRoleDao pmsOperatorRoleDao;

	/**
	 * 创建pmsOperator
	 */
	public void saveData(PmsOperator pmsOperator) {
		pmsOperatorDao.insert(pmsOperator);
	}

	/**
	 * 修改pmsOperator
	 */
	public void updateData(PmsOperator pmsOperator) {
		pmsOperatorDao.update(pmsOperator);
	}

	/**
	 * 根据id获取数据pmsOperator
	 * @param id
	 * @return
	 */
	public PmsOperator getDataById(Long id) {
		return pmsOperatorDao.getById(id);

	}

	/**
	 * 分页查询pmsOperator
	 * @param pageParam
	 * @param ActivityVo
	 *            PmsOperator
	 * @return
	 */
	@Override
	public PageInfo<PmsOperator> listPage(Page<PmsOperator> pageParam, PmsOperator pmsOperator) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginName", pmsOperator.getLoginName()); // 操作员登录名（精确查询）
		paramMap.put("realName", pmsOperator.getRealName()); // 操作员姓名（模糊查询）
		paramMap.put("status", pmsOperator.getStatus()); // 状态
		return pmsOperatorDao.listPage(pageParam, paramMap);
	}

	/**
	 * 根据ID删除一个操作员，同时删除与该操作员关联的角色关联信息. type="1"的超级管理员不能删除.
	 * @param id
	 *            操作员ID.
	 */
	public void deleteOperatorById(Long operatorId) {
		PmsOperator pmsOperator = pmsOperatorDao.getById(operatorId);
		if (pmsOperator != null) {
			if ("admin".equals(pmsOperator.getType())) {
				throw new RuntimeException("【" + pmsOperator.getLoginName() + "】为超级管理员，不能删除！");
			}
			pmsOperatorDao.delete(operatorId);
			// 删除原来的角色与操作员关联
			pmsOperatorRoleDao.deleteByOperatorId(operatorId);
		}
	}

	/**
	 * 更新操作员信息.
	 * 
	 * @param operator
	 */
	public void update(PmsOperator operator) {
		pmsOperatorDao.update(operator);

	}

	/**
	 * 根据操作员ID更新操作员密码.
	 * @param operatorId
	 * @param newPwd
	 *            (已进行SHA1加密)
	 */
	public void updateOperatorPwd(Long operatorId, String newPwd) {
		PmsOperator pmsOperator = pmsOperatorDao.getById(operatorId);
		pmsOperator.setLoginPwd(newPwd);
		pmsOperatorDao.update(pmsOperator);
	}

	/**
	 * 根据登录名取得操作员对象
	 */
	public PmsOperator findOperatorByLoginName(String loginName) {
		return pmsOperatorDao.findByLoginName(loginName);
	}

	/**
	 * 保存操作員信息及其关联的角色.
	 * @param pmsOperator
	 * @param roleOperatorStr
	 */

	@Transactional
	public void saveOperator(PmsOperator pmsOperator, String roleOperatorStr) {
		// 保存操作员信息
		pmsOperatorDao.insert(pmsOperator);
		// 保存角色关联信息
		if (StringUtils.isNotBlank(roleOperatorStr) && roleOperatorStr.length() > 0) {
			saveOrUpdateOperatorRole(pmsOperator, roleOperatorStr);
		}
	}

	/**
	 * 保存用户和角色之间的关联关系
	 */
	private void saveOrUpdateOperatorRole(PmsOperator pmsOperator, String roleIdsStr) {
		// 删除原来的角色与操作员关联
		List<PmsOperatorRole> listPmsOperatorRoles = pmsOperatorRoleDao.listByOperatorId(pmsOperator.getId());
		Map<Long, PmsOperatorRole> delMap = new HashMap<Long, PmsOperatorRole>();
		for (PmsOperatorRole pmsOperatorRole : listPmsOperatorRoles) {
			delMap.put(pmsOperatorRole.getRoleId(), pmsOperatorRole);
		}
		if (StringUtils.isNotBlank(roleIdsStr)) {
			// 创建新的关联
			String[] roleIds = roleIdsStr.split(",");
			for (int i = 0; i < roleIds.length; i++) {
				long roleId = Long.parseLong(roleIds[i]);
				if (delMap.get(roleId) == null) {
					PmsOperatorRole pmsOperatorRole = new PmsOperatorRole();
					pmsOperatorRole.setOperatorId(pmsOperator.getId());
					pmsOperatorRole.setRoleId(roleId);
					pmsOperatorRole.setCreater(pmsOperator.getCreater());
					pmsOperatorRole.setCreateTime(new Date());
					pmsOperatorRole.setStatus(PublicStatusEnum.ACTIVE.name());
					pmsOperatorRoleDao.insert(pmsOperatorRole);
				} else {
					delMap.remove(roleId);
				}
			}
		}

		Iterator<Long> iterator = delMap.keySet().iterator();
		while (iterator.hasNext()) {
			long roleId = iterator.next();
			pmsOperatorRoleDao.deleteByRoleIdAndOperatorId(roleId, pmsOperator.getId());
		}
	}

	/**
	 * 修改操作員信息及其关联的角色.
	 * @param pmsOperator
	 * @param roleOperatorStr
	 */
	public void updateOperator(PmsOperator pmsOperator, String roleOperatorStr) {
		pmsOperatorDao.update(pmsOperator);
		// 更新角色信息
		this.saveOrUpdateOperatorRole(pmsOperator, roleOperatorStr);
	}

}
