/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.core.framework.mvc.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.core.exception.SystemException;
import cn.ucaner.wx.app.core.framework.mvc.dao.BaseDao;
import cn.ucaner.wx.app.core.framework.mvc.entity.BaseEntity;
import cn.ucaner.wx.app.core.utils.bean.BeanUtils;
import cn.ucaner.wx.app.core.utils.pk.PKGenerator;

/**
* @Package：cn.ucaner.wx.app.core.framework.mvc.dao.impl   
* @ClassName：BaseDaoImpl   
* @Description：   <p> 基础Dao接口实现类，实现该类的子类必须设置泛型类型 </p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:51:35   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class BaseDaoImpl<T extends BaseEntity, PK extends Serializable> implements BaseDao<T, PK> {
	
	private static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
	
	@Resource
	protected SqlSessionTemplate sqlSession;
	

	/**
	 * SQLNAME -  分割符号 - ' . '
	 */
	//private final String SQLNAME_SEPARATOR = ".";

	/**
	 * 获取泛型类型的实体对象类全名    mapper.xml 的命名空间就用这个   by wubin@wanguo.com
	 */
	private String sqlMapperNamespace = getDefaultSqlMapperNamespace();

	private final static String SQLNAME_FIND = "find";
	private final static String SQLNAME_FIND_BY_ID = "findById";
	private final static String SQLNAME_FIND_BY_IDS = "findByIds";
	private final static String SQLNAME_FIND_LIST = "findList";
	private final static String SQLNAME_FIND_LIST_BY_PARAMS = "findListByParams";
	private final static String SQLNAME_FIND_LIST_BY_PAGE = "findListByPage";
	private final static String SQLNAME_FIND_ALL = "findAll";
	private final static String SQLNAME_INSERT = "insert";
	private final static String SQLNAME_DELETE = "delete";
	private final static String SQLNAME_DELETE_BY_ID = "deleteById";
	private final static String SQLNAME_DELETE_BY_IDS = "deleteByIds";
	private final static String SQLNAME_DELETE_ALL = "deleteAll";
	private final static String SQLNAME_UPDATE = "update";
	private final static String SQLNAME_UPDATE_BY_PARAMS = "updateByParams";
	private final static String SQLNAME_CHECK = "check";
	private final static String SQLNAME_BATCH_DELETE = "batchDelete";
	private final static String SQLNAME_BATCH_LOGICAL_DELETE = "batchLogicalDelete";
	private final static String SQLNAME_BATCH_INSERT = "batchInsert";
	private final static String SQLNAME_BATCH_INSERT_BY_MAP = "batchInsertByMap";
	private final static String SQLNAME_BATCH_UPDATE = "batchUpdate";
	private final static String SQLNAME_COUNT = "count";


	public String getSqlMapperNamespace() {
		return sqlMapperNamespace;
	}

	public void setSqlMapperNamespace(String sqlMapperNamespace) {
		this.sqlMapperNamespace = sqlMapperNamespace;
	}

	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * 获取泛型类型的实体对象类全名
	 * @return
	 */
	private String getDefaultSqlMapperNamespace() {
		Class<?> genericClass = BeanUtils.getGenericClass(this.getClass());
		return genericClass == null ? null : genericClass.getName();
	}

	/**
	 * 将sqlMapperNamespace与给定的sqlName组合在一起.
	 * @param sqlName
	 * @return
	 */
	/*protected String getSqlName(String sqlName) {
		System.out.println(sqlMapperNamespace + SQLNAME_SEPARATOR + sqlName);
		return sqlMapperNamespace + SQLNAME_SEPARATOR + sqlName;
	}*/
	
	
	/**
	 * @Description: getStatement
	 * @param sqlName
	 * @return String
	 * @Autor: Jason
	 */
	public String getSqlName(String sqlName) {
		String name = this.getClass().getName();
		// 单线程用StringBuilder，确保速度；多线程用StringBuffer,确保安全
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(".").append(sqlName);
		return sb.toString();
	}
	

	@Override
	public T find(T entity) {
		try {
			Assert.notNull(entity);
			return sqlSession.selectOne(getSqlName(SQLNAME_FIND), entity);
		} catch (Exception e) {
			logger.error(String.format("查询对象出错！语句：%s", getSqlName(SQLNAME_FIND)), e);
			throw new SystemException(String.format("查询对象出错！语句：%s", getSqlName(SQLNAME_FIND)), e);
		}
	}

	@Override
	public T findById(PK id) {
		try {
			Assert.notNull(id);
			return sqlSession.selectOne(getSqlName(SQLNAME_FIND_BY_ID), id);
		} catch (Exception e) {
			logger.error(String.format("根据ID查询对象出错！语句：%s", getSqlName(SQLNAME_FIND_BY_ID)), e);
			throw new SystemException(String.format("根据ID查询对象出错！语句：%s", getSqlName(SQLNAME_FIND_BY_ID)), e);
		}
	}

	@Override
	public List<T> findByIds(List<PK> ids) {
		try {
			Assert.notNull(ids);
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_BY_IDS), ids);
		} catch (Exception e) {
			logger.error(String.format("根据ID集合查询对象出错！语句：%s", getSqlName(SQLNAME_FIND_BY_IDS)), e);
			throw new SystemException(String.format("根据ID集合查询对象出错！语句：%s", getSqlName(SQLNAME_FIND_BY_IDS)), e);
		}
	}

	@Override
	public List<T> findList(T entity) {
		try {
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_LIST), entity);
		} catch (Exception e) {
			logger.error(String.format("查询对象列表出错！语句：%s", getSqlName(SQLNAME_FIND_LIST)), e);
			throw new SystemException(String.format("查询对象列表出错！语句：%s", getSqlName(SQLNAME_FIND_LIST)), e);
		}
	}

	@Override
	public List<T> findListByParams(Map<String, Object> params) {
		try {
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_LIST_BY_PARAMS), params);
		} catch (Exception e) {
			logger.error(String.format("查询对象列表出错！语句：%s", getSqlName(SQLNAME_FIND_LIST_BY_PARAMS)), e);
			throw new SystemException(String.format("查询对象列表出错！语句：%s", getSqlName(SQLNAME_FIND_LIST_BY_PARAMS)), e);
		}
	}

	@Override
	public List<T> findAll() {
		try {
			return sqlSession.selectList(getSqlName(SQLNAME_FIND_ALL));
		} catch (Exception e) {
			logger.error(String.format("查询所有对象列表出错！语句：%s", getSqlName(SQLNAME_FIND_ALL)), e);
			throw new SystemException(String.format("查询所有对象列表出错！语句：%s", getSqlName(SQLNAME_FIND_ALL)), e);
		}
	}

	@Override
	public PageInfo<T> findListByPage(Map<String, Object> params, Page<T> page) {
		try {
			// List<T> list =
			// sqlSession.selectList(getSqlName(SQLNAME_FIND_LIST_BY_PAGE),
			// parms,
			// new RowBounds(page.getPageNum(), page.getPageSize()));
			PageHelper.startPage(page.getPageNum(), page.getPageSize());
			List<T> list = sqlSession.selectList(getSqlName(SQLNAME_FIND_LIST_BY_PAGE), params);
			return new PageInfo<T>(list);
		} catch (Exception e) {
			logger.error(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(SQLNAME_FIND_LIST_BY_PAGE)), e);
			throw new SystemException(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(SQLNAME_FIND_LIST_BY_PAGE)), e);
		}
	}

	@Override
	public Long count() {
		try {
			return sqlSession.selectOne(getSqlName(SQLNAME_COUNT));
		} catch (Exception e) {
			logger.error(String.format("查询对象总记录数出错！语句：%s", getSqlName(SQLNAME_COUNT)), e);
			throw new SystemException(String.format("查询对象总记录数出错！语句：%s", getSqlName(SQLNAME_COUNT)), e);
		}
	}

	@Override
	public Long count(T entity) {
		try {
			return sqlSession.selectOne(getSqlName(SQLNAME_COUNT), entity);
		} catch (Exception e) {
			logger.error(String.format("查询对象总记录数出错！语句：%s", getSqlName(SQLNAME_COUNT)), e);
			throw new SystemException(String.format("查询对象总记录数出错！语句：%s", getSqlName(SQLNAME_COUNT)), e);
		}
	}

	@Override
	public void delete(T entity) {
		try {
			Assert.notNull(entity);
			sqlSession.delete(getSqlName(SQLNAME_DELETE), entity);
		} catch (Exception e) {
			logger.error(String.format("删除对象出错！语句：%s", getSqlName(SQLNAME_DELETE)), e);
			throw new SystemException(String.format("删除对象出错！语句：%s", getSqlName(SQLNAME_DELETE)), e);
		}
	}

	@Override
	public void deleteById(PK id) {
		try {
			Assert.notNull(id);
			sqlSession.delete(getSqlName(SQLNAME_DELETE_BY_ID), id);
		} catch (Exception e) {
			logger.error(String.format("根据ID删除对象出错！语句：%s", getSqlName(SQLNAME_DELETE_BY_ID)), e);
			throw new SystemException(String.format("根据ID删除对象出错！语句：%s", getSqlName(SQLNAME_DELETE_BY_ID)), e);
		}
	}

	@Override
	public Long deleteByIds(List<PK> ids) {
		try {
			Assert.notNull(ids);
			sqlSession.delete(getSqlName(SQLNAME_DELETE_BY_IDS), ids);
		} catch (Exception e) {
			logger.error(String.format("根据ID集合删除对象出错！语句：%s", getSqlName(SQLNAME_DELETE_BY_IDS)), e);
			throw new SystemException(String.format("根据ID集合删除对象出错！语句：%s", getSqlName(SQLNAME_DELETE_BY_IDS)), e);
		}
		return null;
	}

	@Override
	public void deleteAll() {
		try {
			sqlSession.delete(getSqlName(SQLNAME_DELETE_ALL));
		} catch (Exception e) {
			logger.error(String.format("删除所有对象出错！语句：%s", getSqlName(SQLNAME_DELETE_ALL)), e);
			throw new SystemException(String.format("删除所有对象出错！语句：%s", getSqlName(SQLNAME_DELETE_ALL)), e);
		}
	}

	@Override
	public PK insert(T entity) {
		try {
			Assert.notNull(entity);
			if (StringUtils.isBlank(entity.getId())) {
				entity.setId(PKGenerator.uuid32());
			}
			sqlSession.insert(getSqlName(SQLNAME_INSERT), entity);
			return (PK) entity.getId();
		} catch (Exception e) {
			logger.error(String.format("保存对象出错！语句：%s", getSqlName(SQLNAME_INSERT)), e);
			throw new SystemException(String.format("保存对象出错！语句：%s", getSqlName(SQLNAME_INSERT)), e);
		}
	}

	@Override
	public void update(T entity) {
		try {
			Assert.notNull(entity);
			sqlSession.update(getSqlName(SQLNAME_UPDATE), entity);
		} catch (Exception e) {
			logger.error(String.format("更新对象出错！语句：%s", getSqlName(SQLNAME_UPDATE)), e);
			throw new SystemException(String.format("更新对象出错！语句：%s", getSqlName(SQLNAME_UPDATE)), e);
		}
	}

	@Override
	public void updateByParams(Map<String, Object> params) {
		try {
			Assert.notNull(params);
		 	sqlSession.update(getSqlName(SQLNAME_UPDATE_BY_PARAMS), params);
		} catch (Exception e) {
			logger.error(String.format("更新对象出错！语句：%s", getSqlName(SQLNAME_UPDATE_BY_PARAMS)), e);
			throw new SystemException(String.format("更新对象出错！语句：%s", getSqlName(SQLNAME_UPDATE_BY_PARAMS)), e);
		}

	}

	@Override
	public boolean check(Map<String, Serializable> params) {
		try {
			T t = sqlSession.selectOne(getSqlName(SQLNAME_CHECK), params);
			if (t != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(String.format("检查数据是否已经存在出错！语句：%s", getSqlName(SQLNAME_CHECK)), e);
			throw new SystemException(String.format("检查数据是否已经存在出错！语句：%s", getSqlName(SQLNAME_CHECK)), e);
		}
	}
	
	@Override
	public void batchLogicalDelete(Map<String, Object> map) {
		try {
			Assert.notNull(map);
			Assert.notNull(map.get("list"));
			sqlSession.update(getSqlName(SQLNAME_BATCH_LOGICAL_DELETE), map);
		} catch (Exception e) {
			logger.error(String.format("批量删除对象出错！语句：%s", getSqlName(SQLNAME_BATCH_DELETE)), e);
			throw new SystemException(String.format("批量删除对象出错！语句：%s", getSqlName(SQLNAME_BATCH_DELETE)), e);
		}
	}
	
	@Override
	public void batchDelete(List<PK> ids) {
		try {
			Assert.notNull(ids);
			sqlSession.delete(getSqlName(SQLNAME_BATCH_DELETE), ids);
		} catch (Exception e) {
			logger.error(String.format("批量删除对象出错！语句：%s", getSqlName(SQLNAME_BATCH_DELETE)), e);
			throw new SystemException(String.format("批量删除对象出错！语句：%s", getSqlName(SQLNAME_BATCH_DELETE)), e);
		}
	}

	@Override
	public void batchInsert(List<T> entitys) {
		try {
			Assert.notNull(entitys);
			for (T entity : entitys) {
				/**
				 * 增加判断，如果添加数据已经存在ID
				 */
				if (StringUtils.isBlank(entity.getId())) {
					entity.setId(PKGenerator.uuid32());
				}
			}
			sqlSession.insert(getSqlName(SQLNAME_BATCH_INSERT), entitys);
		} catch (Exception e) {
			logger.error(String.format("批量插入对象出错！语句：%s", getSqlName(SQLNAME_BATCH_INSERT)), e);
			throw new SystemException(String.format("批量插入对象出错！语句：%s", getSqlName(SQLNAME_BATCH_INSERT)), e);
		}
	}

	@Override
	public void batchInsertByMap(Map<String, Object> paramsMap) {
		try {
			Assert.notNull(paramsMap);
			List<T> entitys = (List<T>) paramsMap.get("entitys");
			for (T entity : entitys) {
				if (StringUtils.isBlank(entity.getId())) {
					entity.setId(PKGenerator.uuid32());
				}
			}

			sqlSession.insert(getSqlName(SQLNAME_BATCH_INSERT_BY_MAP), paramsMap);
		} catch (Exception e) {
			logger.error(String.format("批量插入对象出错！语句：%s", getSqlName(SQLNAME_BATCH_INSERT_BY_MAP)), e);
			throw new SystemException(String.format("批量插入对象出错！语句：%s", getSqlName(SQLNAME_BATCH_INSERT_BY_MAP)), e);
		}

	}

	@Override
	public void batchUpdate(List<T> entitys) {
		try {
			Assert.notNull(entitys);
			sqlSession.update(getSqlName(SQLNAME_BATCH_UPDATE), entitys);
		} catch (Exception e) {
			logger.error(String.format("批量更新对象出错！语句：%s", getSqlName(SQLNAME_BATCH_UPDATE)), e);
			throw new SystemException(String.format("批量更新对象出错！语句：%s", getSqlName(SQLNAME_BATCH_UPDATE)), e);
		}
	}
}
