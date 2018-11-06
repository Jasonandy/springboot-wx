/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.boss.permission.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.alibaba.fastjson.JSON;

import cn.ucaner.wx.app.service.permission.entity.PmsOperator;

/**
* @Package：cn.ucaner.wx.app.boss.permission.utils   
* @ClassName：PasswordHelper   
* @Description：   <p> 生成密码工具类 </p>
* @Author： - Jason   
* @CreatTime：2018年11月2日 下午1:43:48   
* @Modify By：   
* @ModifyTime：  2018年11月2日
* @Modify marker：   
* @version    V1.0
 */
public class PasswordHelper {

	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private static String algorithmName = "md5";

	/**
	 * hash次数
	 */
	private static String hashIteration = "2";

	private static int hashIterations = Integer.valueOf(hashIteration);

	/**
	 * @Description: 加密操作
	 * @param pmsOperator 
	 * @Autor: Jason
	 */
	public static void encryptPassword(PmsOperator pmsOperator) {

		pmsOperator.setsalt(randomNumberGenerator.nextBytes().toHex());

		String newPassword = new SimpleHash(algorithmName, pmsOperator.getLoginPwd(), ByteSource.Util.bytes(pmsOperator.getCredentialsSalt()), hashIterations).toHex();

		pmsOperator.setLoginPwd(newPassword);
	}

	/**
	 * 加密密码
	 * @param loginPwd
	 *            明文密码
	 * @param salt
	 * @return
	 */
	public static String getPwd(String loginPwd, String salt) {
		String newPassword = new SimpleHash(algorithmName, loginPwd, ByteSource.Util.bytes(salt), hashIterations).toHex();
		return newPassword;
	}
	
	/**
	 * @Description: Just for test
	 * @Autor: jasonandy@hotmail.com
	 */
	public static void main(String[] args) {
		PmsOperator pmsOperator = new  PmsOperator();
		pmsOperator.setLoginName("jasonandy@hotmail.com");
		pmsOperator.setLoginPwd("123456");
		encryptPassword(pmsOperator);
		System.out.println(pmsOperator.getsalt());//盐
		System.out.println(JSON.toJSONString(pmsOperator));
		//loginPwd salt
		System.out.println(getPwd("d869e6851828f49bb420c4cf8674a31a", "589babe4641abfe9b93bb2870d0a52af"));
		
	}

}
//Outputs
//7e0a5a4b5298358a6bc79b50d56470ce
//{"createTime":1541481056448,"credentialsSalt":"jasonandy@hotmail.com7e0a5a4b5298358a6bc79b50d56470ce","loginName":"jasonandy@hotmail.com","loginPwd":"302d2895a4578294a658a624822c51ad","version":0}
//49f014d882bc391a0c9a36e35b3e17b1
