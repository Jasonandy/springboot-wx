/**
 * <html>
 * <body>
 *  <P> Copyright JasonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason  https://github.com/Jasonandy/springboot-wx</p>
 *  </body>
 * </html>
 */
package cn.ucaner.wx.app.gateway.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.ucaner.wx.app.core.framework.mvc.controller.BaseController;
import cn.ucaner.wx.app.core.vo.RespBody;
import cn.ucaner.wx.app.core.vo.RespBody.Status;
import cn.ucaner.wx.app.gateway.annotation.LoginUser;
import cn.ucaner.wx.app.service.generator.entity.Test;
import cn.ucaner.wx.app.service.generator.service.TestService;
import cn.ucaner.wx.app.service.permission.entity.PmsRole;
import cn.ucaner.wx.app.service.permission.service.PmsRoleService;

/**
* @Package：cn.ucaner.wx.app.gateway.controller   
* @ClassName：DemoController   
* @Description：   <p> DemoController </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午10:59:27   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController extends BaseController{
	
	private static final long serialVersionUID = 5990430834934059226L;
	
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private TestService testService;
	
	
	@Autowired
	private PmsRoleService pmsRoleService;
	
	
	@RequestMapping("/test")
    @ResponseBody
    public String orderQuery(String trxNo) {
		Map<String, Object> parmas = new HashMap<String, Object>();
		parmas.put("key", "key");
		parmas.put("value", "value");
		PageInfo<Test> pageInfo = testService.findPageList(parmas,new Page<Test>(1, 2));
		/*PageInfo<Object> page = new PageInfo<>();
		page.setPageNum(1);
		page.setPageSize(3);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Test> list = testService.findAll();*/
		logger.debug("demo.test");
        return JSON.toJSONString(pageInfo);
    }
	
	@RequestMapping("/all")
    @ResponseBody
    public String find(String id) {
		List<Test> all = testService.findAll();
        return JSON.toJSONString(all);
    }
	
	
	@RequestMapping("/pms")
    @ResponseBody
    public RespBody queryPms() {
		List<PmsRole> list = pmsRoleService.listAllRole();
		logger.debug("list:{}",list);
		return new RespBody(Status.OK,list);
    }
	
	
	@RequestMapping("/api")
    @ResponseBody
    public RespBody testHanlder(@LoginUser String uid) {
		if(uid == null){
            new RespBody(Status.ERROR,"非法登录");
        }
        return new RespBody(Status.OK,"恭喜您有权限登录:USER ID IS "+uid);
    }
	

	@Override
	protected String getBasePath() {
		return "/demo";
	}

}
