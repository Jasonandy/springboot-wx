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

	private static String hashIteration = "2";

	private static int hashIterations = Integer.valueOf(hashIteration);

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
	 * @Autor: wubin@wanguo.com
	 */
	public static void main(String[] args) {
		PmsOperator pmsOperator = new  PmsOperator();
		pmsOperator.setLoginName("wubin@wanguo.com");
		pmsOperator.setLoginPwd("123456");
		encryptPassword(pmsOperator);
		System.out.println(pmsOperator.getsalt());//盐
		System.out.println(JSON.toJSONString(pmsOperator));
		//loginPwd salt
		System.out.println(getPwd("d869e6851828f49bb420c4cf8674a31a", "589babe4641abfe9b93bb2870d0a52af"));
		
	}

}
//Outputs
//aae4349c6d0608abd8743459e5a721c5
//{"createTime":1532937035184,"credentialsSalt":"wubin@wanguo.comaae4349c6d0608abd8743459e5a721c5","loginName":"wubin@wanguo.com","loginPwd":"5ac0a041cedee117a6de7851b99810d8","version":0}
//49f014d882bc391a0c9a36e35b3e17b1
