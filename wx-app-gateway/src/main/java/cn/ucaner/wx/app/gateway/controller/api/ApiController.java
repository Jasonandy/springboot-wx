package cn.ucaner.wx.app.gateway.controller.api;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ucaner.wx.app.core.vo.RespBody;
import cn.ucaner.wx.app.core.vo.RespBody.Status;
import cn.ucaner.wx.app.gateway.jwt.utils.JWTUtil;
import cn.ucaner.wx.app.gateway.shiro.utils.PasswordHelper;
import cn.ucaner.wx.app.service.permission.entity.PmsOperator;
import cn.ucaner.wx.app.service.permission.service.PmsOperatorService;

/**
* @Package：cn.ucaner.wx.app.gateway.controller.api   
* @ClassName：ApiController   
* @Description：   <p> ApiController api接口测试类 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午11:00:43   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
@RestController
@RequestMapping(value = "/api/v1")
public class ApiController {
	
	
	@Autowired
	private PmsOperatorService pmsOperatorService;
	
	
	/**
	 * @Description: login
	 * @param username
	 * @param password
	 * @return RespBody
	 * @Autor: Jason
	 */
	@PostMapping("/token")
	public RespBody login(@RequestParam("username") String username, @RequestParam("password") String password) {
		HashMap<String, Object> tokenMap = new HashMap<String, Object>();
		RespBody respBody = new RespBody();
		if (StringUtils.isEmpty(username)) {
			respBody.addError("username不存在!");
		}else {
			//盐加签
			tokenMap.put("jwtToken", JWTUtil.sign(username, password));
			respBody.addOK(tokenMap, "Jwt");
		}
	    return respBody;
	}
	
	@GetMapping("/getToken")
	public RespBody getToken() {
		HashMap<String, Object> tokenMap = new HashMap<String, Object>();
		RespBody respBody = new RespBody();
		String username = "admin";
		String password = "123456";
		PmsOperator operator = pmsOperatorService.findOperatorByLoginName(username);
		if (StringUtils.isEmpty(username)) {
			respBody.addError("username不存在!");
		}else {
			//盐加签 - [对密码用(username+随机盐)做两次MD5]得出passWd 
			String ecncodePwd = PasswordHelper.getPwd(password, operator.getCredentialsSalt());
			if (ecncodePwd.equals(operator.getLoginPwd())) {//如果匹配则说明是合法的,返回JWTtoken信息
				tokenMap.put("jwtToken", JWTUtil.sign(username, operator.getCredentialsSalt()));//用户名 校验盐
				//tokenMap.put("jwtToken", JWTUtil.sign(username, ecncodePwd));//用加密后passwd 做校验
				respBody.addOK(tokenMap, "密码正确登录成功!");
			}else {
				respBody.addFail("账号或者密码错误!");
			}
		}
	    return respBody;
	}
	
	
	/**
	 * @Description: jwtToken
	 * @return RespBody
	 * @Autor: Jason
	 */
	//@RequestMapping(name="/jwt",method = RequestMethod.POST)
	@RequestMapping("/jwt")
    public RespBody jwtToken() {
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put("name", "jwt");
		hashMap.put("value", "pass");
		return new RespBody(Status.OK,hashMap);
    }

}
