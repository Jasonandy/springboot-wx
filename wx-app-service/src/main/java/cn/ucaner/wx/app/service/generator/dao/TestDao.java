/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.service.generator.dao;

import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.core.framework.mvc.dao.BaseDao;
import cn.ucaner.wx.app.service.generator.entity.Test;

/**
* @Package：cn.ucaner.wx.app.service.generator.dao   
* @ClassName：TestDao   
* @Description：   <p> TestDao</p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:43:07   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
//@Mapper //方便springBoot扫描到接口
public interface TestDao extends BaseDao<Test, String>{
	
	
	PageInfo<Test> findPageList(Map<String, Object> params, Page<Test> page);
	
    
}