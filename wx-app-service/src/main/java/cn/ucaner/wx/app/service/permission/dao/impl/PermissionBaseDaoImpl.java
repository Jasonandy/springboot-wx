package cn.ucaner.wx.app.service.permission.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.core.exception.BizException;
import cn.ucaner.wx.app.service.permission.dao.PermissionBaseDao;
import cn.ucaner.wx.app.service.permission.entity.PermissionBaseEntity;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao.impl   
* @ClassName：PermissionBaseDaoImpl   
* @Description：   <p>  数据访问层基础支撑类</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:45:46   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public abstract class PermissionBaseDaoImpl<T extends PermissionBaseEntity> extends SqlSessionDaoSupport implements PermissionBaseDao<T> {

	protected static final Log LOG = LogFactory.getLog(PermissionBaseDaoImpl.class);

	public static final String SQL_INSERT = "insert";
	public static final String SQL_BATCH_INSERT = "batchInsert";
	public static final String SQL_UPDATE_BY_ID = "update";
	public static final String SQL_BATCH_UPDATE_BY_IDS = "batchUpdateByIds";
	public static final String SQL_BATCH_UPDATE_BY_COLUMN = "batchUpdateByColumn";
	public static final String SQL_SELECT_BY_ID = "selectByPrimaryKey";
	public static final String SQL_LIST_BY_COLUMN = "listByColumn";
	public static final String SQL_COUNT_BY_COLUMN = "getCountByColumn";
	public static final String SQL_DELETE_BY_ID = "deleteByPrimaryKey";
	public static final String SQL_BATCH_DELETE_BY_IDS = "batchDeleteByIds";
	public static final String SQL_BATCH_DELETE_BY_COLUMN = "batchDeleteByColumn";
	public static final String SQL_LIST_PAGE = "listPage";
	public static final String SQL_LIST_BY = "listBy";
	public static final String SQL_LIST_PAGE_COUNT = "listPageCount";
	public static final String SQL_COUNT_BY_PAGE_PARAM = "countByPageParam"; // 根据当前分页参数进行统计

	/**
	 * 注入SqlSessionTemplate实例(要求Spring中进行SqlSessionTemplate的配置).
	 * 可以调用sessionTemplate完成数据库操作.
	 */
	//@Autowired
	//private SqlSessionTemplate sessionTemplate;
	
	protected SqlSessionTemplate sessionTemplate;

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	public SqlSession getSqlSession() {
		return super.getSqlSession();
	}
	
	
	@Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
	}

	/**
	 * 单条插入数据.
	 */
	public int insert(T entity) {
		int result = sessionTemplate.insert(getStatement(SQL_INSERT), entity);
		if (result <= 0) {
			throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,insert返回0.{%s}", getStatement(SQL_INSERT));
		}
		return result;
	}

	/**
	 * 批量插入数据.
	 */
	public int insert(List<T> list) {
		if (list.isEmpty() || list.size() <= 0) {
			return 0;
		}
		int result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list);
		if (result <= 0) {
			throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,batchInsert返回0.{%s}", getStatement(SQL_BATCH_INSERT));
		}
		return result;
	}

	/**
	 * 根据id单条更新数据.
	 */
	public int update(T entity) {
		entity.setEditTime(new Date());
		int result = sessionTemplate.update(getStatement(SQL_UPDATE_BY_ID), entity);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,updateByPrimaryKey返回0.{%s}", getStatement(SQL_UPDATE_BY_ID));
		}
		return result;
	}

	/**
	 * 根据id批量更新数据.
	 */
	public int update(List<T> list) {
		if (list.isEmpty() || list.size() <= 0) {
			return 0;
		}
		int result = sessionTemplate.update(getStatement(SQL_BATCH_UPDATE_BY_IDS), list);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,batchUpdateByIds返回0.{%s}", getStatement(SQL_BATCH_UPDATE_BY_IDS));
		}
		return result;
	}

	/**
	 * 根据column批量更新数据.
	 */
	public int update(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return 0;
		}
		int result = sessionTemplate.update(getStatement(SQL_BATCH_UPDATE_BY_COLUMN), paramMap);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,batchUpdateByColumn返回0.{%s}", getStatement(SQL_BATCH_UPDATE_BY_COLUMN));
		}
		return result;
	}

	/**
	 * 根据id查询数据.
	 */
	public T getById(Long id) {
		return sessionTemplate.selectOne(getStatement(SQL_SELECT_BY_ID), id);
	}

	/**
	 * 根据column查询数据.
	 */
	public T getByColumn(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return null;
		}
		return sessionTemplate.selectOne(getStatement(SQL_LIST_BY_COLUMN), paramMap);
	}

	/**
	 * 根据条件查询 getBy: selectOne <br/>
	 * 
	 * @param paramMap
	 * @return
	 */
	public T getBy(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return null;
		}
		return sessionTemplate.selectOne(getStatement(SQL_LIST_BY), paramMap);
	}

	/**
	 * 根据条件查询列表数据.
	 */
	public List<T> listBy(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return null;
		}
		return sessionTemplate.selectList(getStatement(SQL_LIST_BY), paramMap);
	}

	/**
	 * 根据column查询列表数据.
	 */
	public List<T> listByColumn(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return null;
		}
		return sessionTemplate.selectList(getStatement(SQL_LIST_BY_COLUMN), paramMap);
	}

	/**
	 * 根据column查询记录数.
	 */
	public Long getCountByColumn(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return null;
		}
		return sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_COLUMN), paramMap);
	}

	/**
	 * 根据id删除数据.
	 */
	public int delete(Long id) {
		return (int) sessionTemplate.delete(getStatement(SQL_DELETE_BY_ID), id);
	}

	/**
	 * 根据id批量删除数据.
	 */
	public int delete(List<T> list) {
		if (list.isEmpty() || list.size() <= 0) {
			return 0;
		} else {
			return (int) sessionTemplate.delete(getStatement(SQL_BATCH_DELETE_BY_IDS), list);
		}
	}

	/**
	 * 根据column批量删除数据.
	 */
	public int delete(Map<String, Object> paramMap) {
		if (paramMap == null) {
			return 0;
		} else {
			return (int) sessionTemplate.delete(getStatement(SQL_BATCH_DELETE_BY_COLUMN), paramMap);
		}
	}
	
	
	/**
	 * 分页逻辑 通用baseDao
	 */
    public PageInfo<T> listPage(Page<T> pageParam, Map<String, Object> paramMap){
    	
    	return null;
    }
	
	
	/**
	 * 函数功能说明 ： 获取Mapper命名空间.
	 * 
	 * @参数：@param sqlId
	 * @参数：@return
	 * @return：String
	 * @throws
	 */
	public String getStatement(String sqlId) {
		String name = this.getClass().getName();
		// 单线程用StringBuilder，确保速度；多线程用StringBuffer,确保安全
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(".").append(sqlId);
		return sb.toString();
	}

}
