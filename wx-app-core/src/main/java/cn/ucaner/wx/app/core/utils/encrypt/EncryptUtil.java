/**
 *<html>
 *<body>
 *	<P> Copyright(c)  ● JasonInternational ◆ Jason <jasonandy@hotail.com></p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.core.utils.encrypt;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
* @Package：cn.ucaner.wx.app.core.utils.encrypt   
* @ClassName：EncryptUtil   
* @Description：   <p> 加密工具类  -- 后期可以优化为对配置文件做加解密处理</p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:38:31   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
@SuppressWarnings("restriction")
public class EncryptUtil {

	private static final Log loger  = LogFactory.getLog(EncryptUtil.class);

	
	/**
	 * 密码盐  -- 配置文件配置化 盐  用来加解 配置文件
	 */
	public static final String PWDSALT = "WANGUO";

	/**
	 * 私有构造方法,将该工具类设为单例模式.
	 */
	private EncryptUtil() {
	}

	/**
	 * 用MD5算法进行加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return MD5加密后的结果
	 */
	public static String encodeMD5String(String str) {
		return encode(str, "MD5");
	}

	/**
	 * 用SHA算法进行加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return SHA加密后的结果
	 */
	public static String encodeSHAString(String str) {
		return encode(str, "SHA");
	}

	/**
	 * 用base64算法进行加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return base64加密后的结果
	 */
	public static String encodeBase64String(String str) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(str.getBytes());
	}

	/**
	 * 用base64算法进行解密
	 * 
	 * @param str
	 *            需要解密的字符串
	 * @return base64解密后的结果
	 * @throws IOException
	 */
	public static String decodeBase64String(String str) throws IOException {
		BASE64Decoder encoder = new BASE64Decoder();
		return new String(encoder.decodeBuffer(str));
	}

	private static String encode(String str, String method) {
		MessageDigest mdInst = null;
		// 把密文转换成十六进制的字符串形式
		// 单线程用StringBuilder，速度快 多线程用stringbuffer，安全
		StringBuilder dstr = new StringBuilder();
		try {
			// 获得MD5摘要算法的 MessageDigest对象
			mdInst = MessageDigest.getInstance(method);
			// 使用指定的字节更新摘要
			mdInst.update(str.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			for (int i = 0; i < md.length; i++) {
				int tmp = md[i];
				if (tmp < 0) {
					tmp += 256;
				}
				if (tmp < 16) {
					dstr.append("0");
				}
				dstr.append(Integer.toHexString(tmp));
			}
		} catch (NoSuchAlgorithmException e) {
			loger.error(e);
		}
		return dstr.toString();
	}
	
	/**
	 * @Description: Test
	 * @param args
	 * @throws IOException
	 * @Autor: Jason
	 */
	public static void main(String[] args) throws IOException {
		String code = "jasonandy@hotmail.com";
		String encode = encodeBase64String(code);  //base64-加码
		String decode = decodeBase64String(encode);
		System.out.printf("encode: %s ,decode: %s %n",encode,decode);
		System.out.printf("encodeMD5: %s %n",encodeMD5String(code));
		System.out.printf("encodeSHA: %s %n",encodeSHAString(code));
	}

}
//Outputs
/*encode: amFzb25hbmR5QGhvdG1haWwuY29t ,decode: jasonandy@hotmail.com 
encodeMD5: 02cd9d6e21e8bed1f925bcf004f4959a 
encodeSHA: 715f038192a9863225ec80ebd9ba2978878fe491*/
