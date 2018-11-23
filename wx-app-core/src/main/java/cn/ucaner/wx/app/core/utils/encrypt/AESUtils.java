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

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
* @Package：cn.ucaner.wx.app.core.utils.encrypt   
* @ClassName：AESUtils   
* @Description：   <p>  AES加解密</p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:41:37   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
class AESUtils {
	
	/** 默认秘钥 100-42=58    加解密的密钥串  fix 
	 * 后期优化为 fix 可以加载的密钥串 或者生成一个 根据一定的条件产生
	 * */
	protected static final String KEY = "NOPO3nzMD3dndwS0MccuMeXCHgVlGOoYyFwLdS24Im2e7YyhB0wrUsyYf0";
	
    /**
     *  AES解密
     * @param encryptValue 待解密内容
     * @param key 秘钥
     * @return
     * @throws Exception
     */
    protected static String decrypt(String encryptValue, String key) throws Exception {  
        return aesDecryptByBytes(base64Decode(encryptValue), key);  
    }
    
    /**
     * AES加密 
     * @param value 待加密内容
     * @param key 秘钥
     * @return
     * @throws Exception
     */
    protected static String encrypt(String value, String key) throws Exception {  
        return base64Encode(aesEncryptToBytes(value, key));  
    }  
	
    /**
     * @Description: base64Encode
     * @param bytes
     * @return String
     * @Autor: Jason
     */
    private static String base64Encode(byte[] bytes){  
        return Base64Utils.encrypt(bytes);  
    }  
      
    /**
     * @Description: base64Decode 
     * @param base64Code
     * @return
     * @throws Exception byte[]
     * @Autor: Jason
     */
    @SuppressWarnings("static-access")
    private static byte[] base64Decode(String base64Code) throws Exception{  
        return base64Code == null ? null : new Base64Utils().decrypt(base64Code);  
    }  
      
    /**
     * @Description: aesEncryptToBytes 
     * @param content
     * @param encryptKey
     * @return
     * @throws Exception byte[]
     * @Autor: Jason
     */
    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(encryptKey.getBytes()));  
  
        Cipher cipher = Cipher.getInstance("AES");  
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));  
          
        return cipher.doFinal(content.getBytes("utf-8"));  
    }  
      
    /**
     * @Description: aesDecryptByBytes 
     * @param encryptBytes
     * @param decryptKey
     * @return
     * @throws Exception String
     * @Autor: Jason
     */
    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(decryptKey.getBytes()));  
          
        Cipher cipher = Cipher.getInstance("AES");  
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));  
        byte[] decryptBytes = cipher.doFinal(encryptBytes);  
          
        return new String(decryptBytes);  
    }  
    
    /**
     * @Description: Test
     * @Autor: Jason
     */
    public static void main(String[] args) {
		
	}
    
}
