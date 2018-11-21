/**
 * <html>
 * <body>
 *  <P> Copyright JasonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason  https://github.com/Jasonandy/springboot-wx</p>
 *  </body>
 * </html>
 */
package cn.ucaner.wx.app.gateway.controller.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ucaner.wx.app.core.framework.mvc.controller.BaseController;
import cn.ucaner.wx.app.core.vo.RespBody;
import cn.ucaner.wx.app.core.vo.RespBody.Status;
import cn.ucaner.wx.app.service.permission.entity.PmsRole;
import cn.ucaner.wx.app.service.permission.service.PmsRoleService;

/**
* @Package：cn.ucaner.wx.app.gateway.controller.permission   
* @ClassName：PermissionController   
* @Description：   <p> PermissionController 权限控制相关 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午10:59:50   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@Controller
@RequestMapping(value = "/pms")
public class PermissionController extends BaseController{

	
	private static final long serialVersionUID = 3696830735084172783L;
	
	@Autowired
	private PmsRoleService pmsRoleService;
	
	
	@RequestMapping("/test")
    @ResponseBody
    public RespBody queryPms(String trxNo) {
		List<PmsRole> list = pmsRoleService.listAllRole();
		return new RespBody(Status.OK,list);
    }

	@Override
	protected String getBasePath() {
		return "/";
	}

}
