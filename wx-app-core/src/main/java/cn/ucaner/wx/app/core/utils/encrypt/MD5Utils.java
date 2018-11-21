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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

/**
* @Package：cn.ucaner.wx.app.core.utils.encrypt   
* @ClassName：MD5Utils   
* @Description：   <p> MD5</p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:38:16   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
class MD5Utils {
	protected final static String MD5_KEY = "MD5";
	
	protected final static String SHA_KEY = "SHA1";
	
	/**
	 * @param value
	 * @param key
	 * @return
	 */
	protected static String encrypt(String value,String key) {
		try {
			// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
			MessageDigest messageDigest = MessageDigest.getInstance(key);
			// 输入的字符串转换成字节数组
			byte[] inputByteArray = value.getBytes();
			// inputByteArray是输入字符串转换得到的字节数组
			messageDigest.update(inputByteArray);
			// 转换并返回结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();
			// 字符数组转换成字符串返回
			return byteArrayToHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * 字节数组转换为hex
	 * @param byteArray
	 * @return
	 */
	private static String byteArrayToHex(byte[] byteArray) {

		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A', 'B', 'C', 'D', 'E', 'F' };
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高）,转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}
	/**
	 * 获得16位的加密字符 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getMd5String16(String str) throws NoSuchAlgorithmException {
		String md5str = getMd5String32(str).substring(8);
		return md5str.substring(0, md5str.length() - 8);
	}

	/**
	 * 获得24位的MD5加密字符
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getMd5String24(String str) throws NoSuchAlgorithmException {

		String md5str = getMd5String32(str).substring(4);
		return md5str.substring(0, md5str.length() - 4);
	}

	/**
	 * 获得32位的MD5加密算法
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getMd5String32(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		byte b[] = md.digest();
		int i;
		StringBuffer buf = new StringBuffer();
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];

			if (i < 0)
				i += 256;

			if (i < 16)
				buf.append("0");

			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}

	/**
	 * 获取MD5密码
	 * @param password
	 * @param salt
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getMD5Pwd(String password, String salt) throws NoSuchAlgorithmException {
		String result = null;
		if (StringUtils.isNotBlank(salt)) {
			result = getMD5(getMD5(password) + salt);
		} else {
			result = getMD5(password);
		}
		return result;
	}

	/**
	 * 获取MD5加密数据
	 * @param input
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getMD5(String input) throws NoSuchAlgorithmException {
		String result = input;
		if (input != null) {
			MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
			md.update(input.getBytes());
			BigInteger hash = new BigInteger(1, md.digest());
			result = hash.toString(16);
			while (result.length() < 32) {//40 for SHA-1
				result = "0" + result;
			}
		}
		return result;
	}

	//------------------------TEST---------------------------------------
	public static void main(String[] args) {
		try {
			System.out.println(getMd5String16("jasonandy@hotmail.com"));
			System.out.println(getMd5String24("jasonandy@hotmail.com"));
			System.out.println(getMd5String32("jasonandy@hotmail.com"));
			System.out.println(getMD5Pwd("passwd", "salt"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}

//Outputs 
//21e8bed1f925bcf0
//9d6e21e8bed1f925bcf004f4
//02cd9d6e21e8bed1f925bcf004f4959a
//
//31cfccc227c348c90eedb7a9a41b880f
