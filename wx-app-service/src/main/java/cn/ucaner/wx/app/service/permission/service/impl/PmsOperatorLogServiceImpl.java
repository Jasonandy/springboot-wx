/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.service.permission.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.service.permission.dao.PmsOperatorLogDao;
import cn.ucaner.wx.app.service.permission.entity.PmsOperatorLog;
import cn.ucaner.wx.app.service.permission.service.PmsOperatorLogService;

/**
* @Package：cn.ucaner.wx.app.service.permission.service.impl   
* @ClassName：PmsOperatorLogServiceImpl   
* @Description：   <p> 操作员service接口实现 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:59:36   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Service("pmsOperatorLogService")
public class PmsOperatorLogServiceImpl implements PmsOperatorLogService {
	
	@Autowired
	private PmsOperatorLogDao pmsOperatorLogDao;

	/**
	 * 创建pmsOperator
	 */
	public void saveData(PmsOperatorLog pmsOperatorLog) {
		pmsOperatorLogDao.insert(pmsOperatorLog);
	}

	/**
	 * 修改pmsOperator
	 */
	public void updateData(PmsOperatorLog pmsOperatorLog) {
		pmsOperatorLogDao.update(pmsOperatorLog);
	}

	/**
	 * 根据id获取数据pmsOperator
	 * 
	 * @param id
	 * @return
	 */
	public PmsOperatorLog getDataById(Long id) {
		return pmsOperatorLogDao.getById(id);

	}

	/**
	 * 分页查询pmsOperator
	 * 
	 * @param pageParam
	 * @param ActivityVo
	 *            PmsOperator
	 * @return
	 */
	@Override
	public PageInfo<PmsOperatorLog> listPage(Page<PmsOperatorLog> pageParam, PmsOperatorLog pmsOperatorLog) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return pmsOperatorLogDao.listPage(pageParam, paramMap);
	}

}
