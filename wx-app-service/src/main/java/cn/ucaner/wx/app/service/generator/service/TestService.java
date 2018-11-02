package cn.ucaner.wx.app.service.generator.service;

import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.core.framework.mvc.service.BaseService;
import cn.ucaner.wx.app.service.generator.entity.Test;

/**
* @Package：cn.ucaner.wx.app.service.generator.service   
* @ClassName：TestService   
* @Description：   <p> TestService </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午9:45:22   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public interface  TestService extends BaseService<Test, String>{
	
	
	public PageInfo<Test> findPageList(Map<String, Object> params, Page<Test> page);
	
}
