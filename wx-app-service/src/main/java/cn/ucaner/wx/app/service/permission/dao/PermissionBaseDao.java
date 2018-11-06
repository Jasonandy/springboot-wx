/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.service.permission.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
* @Package：cn.ucaner.wx.app.service.permission.dao   
* @ClassName：PermissionBaseDao   
* @Description：   <p> 权限点数据访问层基础支撑接口 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:48:45   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public interface PermissionBaseDao<T> {

	/**
	 * @Description: 单条插入数据 
	 * @param entity
	 * @return int
	 * @Autor: Jason
	 */
	int insert(T entity);

	/**
	 * @Description: 批量插入数据 
	 * @param list
	 * @return int
	 * @Autor: Jason
	 */
	int insert(List<T> list);

	/**
	 * @Description: 根据id单条更新数据 
	 * @param entity
	 * @return int
	 * @Autor: Jason
	 */
	int update(T entity);

	/**
	 * @Description: 根据id批量更新数据 
	 * @param list
	 * @return int
	 * @Autor: Jason
	 */
	int update(List<T> list);

	/**
	 * @Description: 根据column批量更新数据
	 * @param paramMap
	 * @return int
	 * @Autor: Jason
	 */
	int update(Map<String, Object> paramMap);

	/**
	 * @Description: 根据id查询数据
	 * @param id
	 * @return T
	 * @Autor: Jason
	 */
	T getById(Long id);

	/**
	 * @Description:  根据column查询数据 
	 * @param paramMap
	 * @return T
	 * @Autor: Jason
	 */
	public T getByColumn(Map<String, Object> paramMap);

	/**
	 * @Description: 根据条件查询 getBy 
	 * @param paramMap
	 * @return T
	 * @Autor: Jason
	 */
	public T getBy(Map<String, Object> paramMap);

	/**
	 * @Description: 根据条件查询列表数据. 
	 * @param paramMap
	 * @return List<T>
	 * @Autor: Jason
	 */
	public List<T> listBy(Map<String, Object> paramMap);

	/**
	 * @Description:  根据column查询列表数据 
	 * @param paramMap
	 * @return List<T>
	 * @Autor: Jason
	 */
	public List<T> listByColumn(Map<String, Object> paramMap);

	/**
	 * @Description:  根据column查询记录数 
	 * @param paramMap
	 * @return Long
	 * @Autor: Jason
	 */
	Long getCountByColumn(Map<String, Object> paramMap);

	/**
	 * @Description: 根据id删除数据
	 * @param id
	 * @return int
	 * @Autor: Jason
	 */
	int delete(Long id);

	/**
	 * @Description:  根据ids批量删除数据
	 * @param list
	 * @return int
	 * @Autor: Jason
	 */
	int delete(List<T> list);

	/**
	 * @Description: 根据column批量删除数据 
	 * @param paramMap
	 * @return int
	 * @Autor: Jason
	 */
	int delete(Map<String, Object> paramMap);

	/**
	 * @Description:     分页查询数据 
	 * @param pageParam  分页信息 pageNum  pageSize etc .
	 * @param paramMap   查询参数
	 * @return PageInfo<T>
	 * @Autor: Jason
	 */
	public PageInfo<T> listPage(Page<T> pageParam, Map<String, Object> paramMap);

	/**
	 * @Description: getSessionTemplate 
	 * @return SqlSessionTemplate
	 * @Autor: Jason
	 */
	public SqlSessionTemplate getSessionTemplate();

	/**
	 * @Description: getSqlSession 
	 * @return SqlSession
	 * @Autor: Jason
	 */
	public SqlSession getSqlSession();
}
