/**
 * <html>
 * <body>
 *  <P> Copyright JasonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2018年7月25日 上午11:49:50</p>
 *  <p> Created by Jason </p>
 *  </body>
 * </html>
 */
package cn.ucaner.wx.app.service.generator.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.core.framework.mvc.dao.BaseDao;
import cn.ucaner.wx.app.core.framework.mvc.service.impl.BaseServiceImpl;
import cn.ucaner.wx.app.service.generator.dao.TestDao;
import cn.ucaner.wx.app.service.generator.entity.Test;
import cn.ucaner.wx.app.service.generator.service.TestService;

/**
* @Package：cn.ucaner.wx.app.service.generator.service.impl   
* @ClassName：TestServiceImpl   
* @Description：   <p> TestServiceImpl </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:43:52   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Service("testService")
public class TestServiceImpl extends BaseServiceImpl<Test, String> implements TestService {

	@Autowired
	private  TestDao testDao;
	
	@Override
	public void deleteByIds(List<String> ids) {
		
	}

	@Override
	protected BaseDao<Test, String> getDao() {
		return testDao;
	}

	@Override
	public PageInfo<Test> findPageList(Map<String, Object> params, Page<Test> page) {
		return testDao.findPageList(params, page);
	}

	
}
