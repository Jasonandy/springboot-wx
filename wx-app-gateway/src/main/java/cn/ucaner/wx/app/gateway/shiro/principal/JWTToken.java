package cn.ucaner.wx.app.gateway.shiro.principal;

import org.apache.shiro.authc.AuthenticationToken;

/**
* @Package：cn.ucaner.wx.app.gateway.shiro.principal   
* @ClassName：JWTToken   
* @Description：   <p> JWTToken - principal Shiro的校验对象 . 包含username信息等 仿[UsernamePasswordToken] </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午10:57:12   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class JWTToken implements AuthenticationToken{
	

	private static final long serialVersionUID = -1929842923741950361L;

	/**
	 * jwt token
	 */
    private String token;

    
    public JWTToken(String token) {
        this.token = token;
    }
    
    
	@Override
	public Object getPrincipal() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
	
	

}
