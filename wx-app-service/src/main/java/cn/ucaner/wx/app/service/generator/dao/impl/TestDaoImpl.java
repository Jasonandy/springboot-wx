package cn.ucaner.wx.app.service.generator.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.core.exception.SystemException;
import cn.ucaner.wx.app.core.framework.mvc.dao.impl.BaseDaoImpl;
import cn.ucaner.wx.app.service.generator.dao.TestDao;
import cn.ucaner.wx.app.service.generator.entity.Test;

/**
* @Package：cn.ucaner.wx.app.service.generator.dao.impl   
* @ClassName：TestDaoImpl   
* @Description：   <p> TestDaoImpl</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:42:45   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Repository
public class TestDaoImpl extends BaseDaoImpl<Test, String>  implements TestDao {

	private static Logger logger = LoggerFactory.getLogger(TestDaoImpl.class);
	
	private static final String SQLNAME_FIND_BY_PAGE_LIST = "findPageList";

	@Override
	public PageInfo<Test> findPageList(Map<String, Object> params, Page<Test> page) {
		try {
			PageHelper.startPage(page.getPageNum(), page.getPageSize());
			List<Test> list = sqlSession.selectList(getSqlName(SQLNAME_FIND_BY_PAGE_LIST), params);
			return new PageInfo<Test>(list);
		} catch (Exception e) {
			logger.error(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(SQLNAME_FIND_BY_PAGE_LIST)), e);
			throw new SystemException(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(SQLNAME_FIND_BY_PAGE_LIST)), e);
		}
	}

}
