/**
 *<html>
 *<body>
 *	<P> Copyright(c)  ● JasonInternational ◆ Jason <jasonandy@hotail.com></p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.core.utils.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringEscapeUtils;

/**
* @Package：cn.ucaner.wx.app.core.utils.encode   
* @ClassName：EncodeUtils   
* @Description：   <p> 集成Commons-Codec,Commons-Lang及JDK提供的编解码方法.</p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:42:34   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class EncodeUtils {

	/**
	 * 默认 url 编码格式 -- 系统统一用UTF-8 包括Java和proprites xml html css js 等
	 */
	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	
	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * @Description: sha1编码
	 * @param input
	 * @return String
	 * @Autor: Jason
	 */
	public static String sha1Encode(String input) {
		return DigestUtils.sha1Hex(input);
	}

	/**
	 * @Description: md5编码.
	 * @param input
	 * @return String
	 * @Autor: Jason
	 */
	public static String md5Encode(String input) {
		return DigestUtils.md5Hex(input);
	}

	/**
	 * @Description: Hex编码.
	 * @param input
	 * @return String
	 * @Autor: Jason
	 */
	public static String encodeHex(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * @Description: Hex解码.
	 * @param input
	 * @return byte[]
	 * @Autor: Jason
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException("[UCANER-ERROR]Hex Decoder exception", e);
		}
	}

	/**
	 * @Description: Base64编码.
	 * @param input
	 * @return String
	 * @Autor: Jason
	 */
	public static String encodeBase64(byte[] input) {
		return Base64.encodeBase64String(input);
	}

	/**
	 * @Description: Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
	 * @param input
	 * @return String
	 * @Autor: Jason
	 */
	public static String encodeUrlSafeBase64(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * @Description: Base64解码.
	 * @param input
	 * @return byte[]
	 * @Autor: Jason
	 */
	public static byte[] decodeBase64(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * @Description: Base62编码.
	 * @param input
	 * @return String
	 * @Autor: Jason
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[ ( input[i] & 0xFF ) % BASE62.length];
		}
		return new String(chars);
	}

	/**
	 * @Description: Html 转码.
	 * @param html
	 * @return String
	 * @Autor: Jason
	 */
	public static String escapeHtml(String html) {
		return StringEscapeUtils.escapeHtml4(html);
	}

	/**
	 * @Description: Html 解码.
	 * @param htmlEscaped
	 * @return String
	 * @Autor: Jason
	 */
	public static String unescapeHtml(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml4(htmlEscaped);
	}

	/**
	 * @Description: Xml 转码.
	 * @param xml
	 * @return String
	 * @Autor: Jason
	 */
	public static String escapeXml(String xml) {
		return StringEscapeUtils.escapeXml10(xml);
	}

	/**
	 * @Description: Xml 解码.
	 * @param xmlEscaped
	 * @return String
	 * @Autor: Jason
	 */
	public static String unescapeXml(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}

	/**
	 * @Description: URL 编码, Encode默认为UTF-8. 
	 * @param part
	 * @return String
	 * @Autor: Jason
	 */
	public static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * @Description: URL 解码, Encode默认为UTF-8.
	 * @param part
	 * @return String
	 * @Autor: Jason
	 */
	public static String urlDecode(String part) {
		try {
			return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}
	
	
	//---------------------------------------TEST--------------------------------------
	public static void main(String[] args) {
		System.out.println("jasonandy@hotmail.com".getBytes());
		System.out.println(encodeBase64("jasonandy@hotmail.com".getBytes()));
		System.out.println(decodeBase64("amFzb25hbmR5QGhvdG1haWwuY29t"));
	}

}
//Outputs
//[B@eed1f14
//amFzb25hbmR5QGhvdG1haWwuY29t
//[B@568db2f2
