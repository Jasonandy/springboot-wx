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

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
* @Package：cn.ucaner.wx.app.core.utils.encrypt   
* @ClassName：CryptAES   
* @Description：   <p> CryptAES </p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:39:59   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class CryptAES {

	private static final String AESTYPE = "AES/ECB/PKCS5Padding";

	/**
	 * @Description: AES_Encrypt 
	 * @param keyStr
	 * @param plainText
	 * @return String
	 * @Autor: Jason
	 */
	public static String AES_Encrypt(String keyStr, String plainText) {
		byte[] encrypt = null;
		try {
			Key key = generateKey(keyStr);
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encrypt = cipher.doFinal(plainText.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64(encrypt));
	}

	/**
	 * @Description: AES_Decrypt 
	 * @param keyStr
	 * @param encryptData
	 * @return String
	 * @Autor: Jason
	 */
	public static String AES_Decrypt(String keyStr, String encryptData) {
		byte[] decrypt = null;
		try {
			Key key = generateKey(keyStr);
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.DECRYPT_MODE, key);
			decrypt = cipher.doFinal(Base64.decodeBase64(encryptData));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(decrypt).trim();
	}

	/**
	 * @Description: generateKey   生成密钥对
	 * @param key
	 * @return
	 * @throws Exception Key
	 * @Autor: Jason
	 */
	private static Key generateKey(String key) throws Exception {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			return keySpec;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * @Description: 生成des key 
	 * @param length
	 * @return
	 * @throws Exception byte[]
	 * @Autor: Jason
	 */
	public static byte[] generateDesKey(int length) throws Exception {
        //实例化  
        KeyGenerator kgen = null;
        kgen = KeyGenerator.getInstance("AES");
        //设置密钥长度  
        kgen.init(length);  
        //生成密钥  
        SecretKey skey = kgen.generateKey();  
        //返回密钥的二进制编码  
        return skey.getEncoded();  
    }

	
	/**
	 * @throws Exception 
	 * @Description: Test
	 * @Autor: jasonandy@hotmail.com
	 */
	public static void main(String[] args) throws Exception {
		//Key generateKey = generateKey("jasoandy@hotmail.com");
		//System.out.println(generateKey.getAlgorithm());
		//System.out.println(generateKey.getFormat());
		//System.out.println(generateKey.getEncoded());
		byte[] generateDesKey = generateDesKey(128);
		String aes_Decrypt = AES_Decrypt(generateDesKey.toString(), "jasonandy@hotmail.com");
		System.out.println(aes_Decrypt);
	}
}
