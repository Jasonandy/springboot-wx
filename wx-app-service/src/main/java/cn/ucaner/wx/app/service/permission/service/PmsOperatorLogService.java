package cn.ucaner.wx.app.service.permission.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.service.permission.entity.PmsOperatorLog;

/**
* @Package：cn.ucaner.wx.app.service.permission.service   
* @ClassName：PmsOperatorLogService   
* @Description：   <p>  操作员日志service接口 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:57:15   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public interface PmsOperatorLogService {

	/**
	 * @Description: 创建pmsOperatorLog
	 * @param pmsOperatorLog 
	 * @Autor: Jason
	 */
	void saveData(PmsOperatorLog pmsOperatorLog);

	/**
	 * @Description: 修改pmsOperatorLog
	 * @param pmsOperatorLog 
	 * @Autor: Jason
	 */
	void updateData(PmsOperatorLog pmsOperatorLog);

	/**
	 * @Description: 根据id获取数据pmsOperatorLog
	 * @param id
	 * @return PmsOperatorLog
	 * @Autor: Jason
	 */
	PmsOperatorLog getDataById(Long id);

	/**
	 * @Description: 分页查询pmsOperatorLog 
	 * @param pageParam
	 * @param pmsOperatorLog
	 * @return PageInfo<PmsOperatorLog>
	 * @Autor: Jason
	 */
	PageInfo<PmsOperatorLog> listPage(Page<PmsOperatorLog> pageParam,PmsOperatorLog pmsOperatorLog);
}
