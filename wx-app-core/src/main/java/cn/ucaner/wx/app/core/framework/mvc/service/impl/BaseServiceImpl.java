package cn.ucaner.wx.app.core.framework.mvc.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.core.framework.mvc.dao.BaseDao;
import cn.ucaner.wx.app.core.framework.mvc.entity.BaseEntity;
import cn.ucaner.wx.app.core.framework.mvc.service.BaseService;

/**
* @Package：cn.ucaner.wx.app.core.framework.mvc.service.impl   
* @ClassName：BaseServiceImpl   
* @Description：   <p> BaseServiceImpl </p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:49:49   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public abstract class BaseServiceImpl<T extends BaseEntity, PK extends Serializable> implements BaseService<T, PK> {
	/**
	 * 获取数据库操作类
	 * 
	 * @return
	 */
	protected abstract BaseDao<T, PK> getDao();

	@Override
	public T find(T entity) {
		return getDao().find(entity);
	}

	@Override
	public T findById(PK id) {
		return getDao().findById(id);
	}

	@Override
	public List<T> findByIds(List<PK> ids) {
		return getDao().findByIds(ids);
	}

	@Override
	public List<T> findList(T entity) {
		return getDao().findList(entity);
	}

	@Override
	public List<T> findListByParams(Map<String, Object> params) {
		return getDao().findListByParams(params);
	}

	@Override
	public PageInfo<T> findListByPage(Map<String, Object> parms, Page<T> page) {
		return getDao().findListByPage(parms, page);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public Long count() {
		return getDao().count();
	}

	@Override
	public Long count(T entity) {
		return getDao().count(entity);
	}

	@Override
	public PK insert(T entity) {
		return getDao().insert(entity);
	}

	@Override
	public void delete(T entity) {
		getDao().delete(entity);
	}

	@Override
	public void deleteById(PK id) {
		getDao().deleteById(id);
	}


	@Override
	public void deleteAll() {
		getDao().deleteAll();
	}

	@Override
	public void update(T entity) {
		getDao().update(entity);
	}

	@Override
	public void updateByParams(Map<String, Object> params) {
		getDao().updateByParams(params);
	}

	@Override
	public boolean check(Map<String, Serializable> params) {
		return getDao().check(params);
	}

	@Override
	public void batchDelete(List<PK> ids) {
		if (ids.size() > 0) {
			int batchCount = 250;//每批提交数量
			int batchLastIndex = batchCount;// 每批最后一个的下标
			for (int index = 0; index < ids.size();) {
				if (batchLastIndex >= ids.size()) {
					batchLastIndex = ids.size();
					getDao().batchDelete(ids.subList(index, batchLastIndex));
					break;// 数据插入完毕，退出循环
				} else {
					getDao().batchDelete(ids.subList(index, batchLastIndex));
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + ( batchCount - 1 );
				}
			}
		}
	}
	
	@Override
	public void batchLogicalDelete(Map<String, Object> map) {
		if (map.get("list") != null &&  map.get("list") instanceof List) {
			getDao().batchLogicalDelete(map);
		}
	}

	@Override
	public void batchInsert(List<T> entitys) {
		if (entitys.size() > 0) {
			int batchCount = 250;//每批提交数量
			int batchLastIndex = batchCount;// 每批最后一个的下标
			for (int index = 0; index < entitys.size();) {
				if (batchLastIndex >= entitys.size()) {
					batchLastIndex = entitys.size();
					getDao().batchInsert(entitys.subList(index, batchLastIndex));
					break;// 数据插入完毕，退出循环
				} else {
					getDao().batchInsert(entitys.subList(index, batchLastIndex));
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + ( batchCount - 1 );
				}
			}
		}
	}

	@Override
	public void batchInsertByMap(Map<String, Object> paramsMap) {
		List<T> entitys = (List<T>) paramsMap.get("entitys");
		if (entitys.size() > 0) {
			int batchCount = 250;//每批提交数量
			int batchLastIndex = batchCount;// 每批最后一个的下标
			for (int index = 0; index < entitys.size();) {
				if (batchLastIndex >= entitys.size()) {
					batchLastIndex = entitys.size();
					List<T> list = entitys.subList(index, batchLastIndex);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("hashTableName", paramsMap.get("hashTableName"));
					map.put("entitys", list);
					getDao().batchInsertByMap(map);
					break;// 数据插入完毕，退出循环
				} else {
					List<T> list = entitys.subList(index, batchLastIndex);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("hashTableName", paramsMap.get("hashTableName"));
					map.put("entitys", list);
					getDao().batchInsertByMap(map);
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + ( batchCount - 1 );
				}
			}
		}

	}

	@Override
	public void batchUpdate(List<T> entitys) {
		if (entitys.size() > 0) {
			int batchCount = 250;//每批提交数量
			int batchLastIndex = batchCount;// 每批最后一个的下标
			for (int index = 0; index < entitys.size();) {
				if (batchLastIndex >= entitys.size()) {
					batchLastIndex = entitys.size();
					getDao().batchUpdate(entitys.subList(index, batchLastIndex));
					break;// 数据插入完毕，退出循环
				} else {
					getDao().batchUpdate(entitys.subList(index, batchLastIndex));
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + ( batchCount - 1 );
				}
			}
		}
	}

}
