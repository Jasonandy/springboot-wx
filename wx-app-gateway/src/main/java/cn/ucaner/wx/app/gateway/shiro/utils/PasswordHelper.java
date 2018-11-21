/**
 * <html>
 * <body>
 *  <P> Copyright JasonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason  https://github.com/Jasonandy/springboot-wx</p>
 *  </body>
 * </html>
 */
package cn.ucaner.wx.app.gateway.shiro.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import cn.ucaner.wx.app.service.permission.entity.PmsOperator;

/**
* @Package：cn.ucaner.wx.app.gateway.shiro.utils   
* @ClassName：PasswordHelper   
* @Description：   <p>  生成密码工具类 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 上午10:55:47   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class PasswordHelper {

	/**
	 * 产生一个随机数盐 - 随机盐
	 */
	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	/**
	 * MD5算法 还有HMAC , SHA-1
	 */
	private static String algorithmName = "md5";

	/**
	 * Hash散列次数
	 */
	private static String hashIteration = "2";

	/**
	 * hashIterations hash次数
	 */
	private static int hashIterations = Integer.valueOf(hashIteration);

	
	
	public static void encryptPassword(PmsOperator pmsOperator) {

		pmsOperator.setsalt(randomNumberGenerator.nextBytes().toHex()); //随机盐产生

		//MD5 , LoginPwd:"123456", 认证加密盐:loginName + salt
		String newPassword = new SimpleHash(algorithmName, pmsOperator.getLoginPwd(), ByteSource.Util.bytes(pmsOperator.getCredentialsSalt()), hashIterations).toHex();//hex

		pmsOperator.setLoginPwd(newPassword);
	}

	/**
	 * [明文密码获取密文密码]
	 * @param loginPwd - 明文密码
	 * @param salt     - 盐
	 * @return         加密密码 
	 */
	public static String getPwd(String loginPwd, String salt) {
		String newPassword = new SimpleHash(algorithmName, loginPwd, ByteSource.Util.bytes(salt), hashIterations).toHex();
		return newPassword;
	}
	
	
	
	/**
	 * @Description: Just for test
	 * @Autor: wubin@wanguo.com
	 */
	public static void main(String[] args) {
		PmsOperator pmsOperator = new  PmsOperator();
		pmsOperator.setLoginName("jasonandy@hotmail.com");
		pmsOperator.setLoginPwd("123456");
		encryptPassword(pmsOperator);
		System.out.println("Salt:"+pmsOperator.getsalt());//随机盐
		System.out.println("credentialsSalt:"+pmsOperator.getCredentialsSalt());//[用户名+随机盐]:credentialsSalt
		System.out.println("loginPwd:"+pmsOperator.getLoginPwd());//loginPwd  [对-用户名+(用户名+随机盐)-做-2次-MD5]
		System.out.println("-------------------------------------------------");
		//System.out.println("loginPwd:"+pmsOperator.getLoginPwd());//loginPwd  [对-用户名+(用户名+随机盐)-做-2次-MD5]
		//System.out.println(JSON.toJSONString(pmsOperator));//pmsOperator
		//loginPwd salt
		//pmsOperator.setLoginPwd("123456");
		//System.out.println(getPwd(pmsOperator.getLoginPwd(), pmsOperator.getsalt()));
		//System.out.println(JSON.toJSONString(pmsOperator));//pmsOperator
		System.out.println(getPwd("123456", "jasonandy@hotmail.com08a4b3a8fc21a49cfbd69da04a27c953"));
		System.out.println(getPwd("123456", "edb7ff3b648c252113335b737d8a627a"));
		//盐    wubin@wanguo.com 8d78869f470951332959580424d4bf4f
		//pwd d3c59d25033dbf980d29554025c23a75
		
	}

}
//Outputs [getPwd的结果是唯一的]
//{"createTime":1533217672650,"credentialsSalt":"wubin@wanguo.com3b4803054f246091e40814d7ba53d58a","loginName":"wubin@wanguo.com","loginPwd":"dceeb89ae1c59e85416223036b1989a8","version":0}
