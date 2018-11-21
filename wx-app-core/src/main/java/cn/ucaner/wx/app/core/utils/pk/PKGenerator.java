/**
 *<html>
 *<body>
 *	<P> Copyright(c)  ● JasonInternational ◆ Jason <jasonandy@hotail.com></p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.core.utils.pk;

import java.security.SecureRandom;
import java.util.UUID;

/**
* @Package：cn.ucaner.wx.app.core.utils.pk   
* @ClassName：PKGenerator   
* @Description：   <p> 主键ID 生成器</p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:37:46   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class PKGenerator {

	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * REMAKR: synchronized volitale 
	 */
    private static SecureRandom random = new SecureRandom();
	    
	    
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
	    return UUID.randomUUID().toString();
	}
	    
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.  [ID数据库设计为32位VarChar Mysql]
	 */
	public static String uuid32() {
	    return UUID.randomUUID().toString().replaceAll("-", "");
	}
	    
	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
	    return Math.abs(random.nextLong());
	}
	    
	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
	    byte[] randomBytes = new byte[length];
	    random.nextBytes(randomBytes);
	    return encodeBase62(randomBytes);
	}
	    
	/**
	 * @Description: 加码器 Base64
	 * @param input
	 * @return String
	 * @Autor: wubin@wanguo.com
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[ ( input[i] & 0xFF ) % BASE62.length];
		}
		return new String(chars);
	}
	
	
	//-----------------------------Test-----------------------------------------
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(uuid32());
			System.out.println(uuid());
			System.out.println("------------------------");
		}
	}
	
}
