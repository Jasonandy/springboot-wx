package cn.ucaner.wx.chat.bot.framework.common.util.base;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @ClassName：StringHelper
 * @Description： <p> 字符串工具类 </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 14:56
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class StringHelper {

	public static Log logger = LogFactory.getLog(StringHelper.class);

	/**
	 * 前导标识
	 */
	public static final int BEFORE = 1;

	/**
	 * 后继标识
	 */
	public static final int AFTER = 2;

    /**
     * DEFAULT_PATH_SEPARATOR
     */
	public static final String DEFAULT_PATH_SEPARATOR = ",";

    /**
     * EMPTY
     */
    public static final String EMPTY = "";

    /**
     * SLASH
     */
    public static final String SLASH = "/";

	/**
	 * 将一个中间带逗号分隔符的字符串转换为ArrayList对象
	 * @param str
	 *            待转换的符串对象
	 * @return ArrayList对象
	 */
	public static ArrayList<?> strToArrayList(String str) {
		return strToArrayListManager(str, DEFAULT_PATH_SEPARATOR);
	}

	/**
	 * 将字符串对象按给定的分隔符separator转象为ArrayList对象
	 * @param str
	 *            待转换的字符串对象
	 * @param separator
	 *            字符型分隔符
	 * @return ArrayList对象
	 */
	public static ArrayList<String> strToArrayList(String str, String separator) {
		return strToArrayListManager(str, separator);
	}

	/**
	 * @Description: 分割
	 * @param str
	 * @param separator
	 * @return ArrayList<String>
	 * @Autor: jasonandy@hotmail.com
	 */
	private static ArrayList<String> strToArrayListManager(String str, String separator) {

		StringTokenizer strTokens = new StringTokenizer(str, separator);
		ArrayList<String> list = new ArrayList<String>();

		while (strTokens.hasMoreTokens()) {
			list.add(strTokens.nextToken().trim());
		}

		return list;
	}

    /**
     * 字符串是否为非空白 空白的定义如下： <br>
     * 1、不为null <br>
     * 2、不为不可见字符（如空格）<br>
     * 3、不为""<br>
     * @param str 被检测的字符串
     * @return 是否为非空
     */
    public static boolean isNotBlank(CharSequence str) {
        return false == isBlank(str);
    }

    /**
     * 字符串是否为空白 空白的定义如下： <br>
     * 1、为null <br>
     * 2、为不可见字符（如空格）<br>
     * 3、""<br>
     * @param str 被检测的字符串
     * @return 是否为空
     */
    public static boolean isBlank(CharSequence str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            /**
             * 只要有一个非空字符即为非空字符串
             */
            if (false == isBlankChar(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * 编码字符串
     * @param str 字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 编码后的字节码
     */
    public static byte[] bytes(CharSequence str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (null == charset) {
            return str.toString().getBytes();
        }
        return str.toString().getBytes(charset);
    }



    /**
     * 是否空白符<br>
     * 空白符包括空格、制表符、全角空格和不间断空格<br>
     * @param c 字符
     * @return 是否空白符
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int)
     */
    public static boolean isBlankChar(char c) {
        return isBlankChar((int) c);
    }

    /**
     * 是否空白符<br>
     * 空白符包括空格、制表符、全角空格和不间断空格<br>
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int)
     * @param c 字符
     * @return 是否空白符
     */
    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c) || c == '\ufeff' || c == '\u202a';
    }

	/**
	 * 将一个中间带逗号分隔符的字符串转换为字符型数组对象
	 * @param str
	 *            待转换的符串对象
	 * @return 字符型数组
	 */
	public static String[] strToStrArray(String str) {
		return strToStrArrayManager(str, DEFAULT_PATH_SEPARATOR);
	}

	/**
	 * 将字符串对象按给定的分隔符separator转象为字符型数组对象
	 * @param str  待转换的符串对象
	 * @param separator 字符型分隔符
	 * @return 字符型数组
	 */
	public static String[] strToStrArray(String str, String separator) {
		return strToStrArrayManager(str, separator);
	}

    /**
     * strToStrArrayManager
     * @param str
     * @param separator
     * @return
     */
	private static String[] strToStrArrayManager(String str, String separator) {
		StringTokenizer strTokens = new StringTokenizer(str, separator);
		String[] strArray = new String[strTokens.countTokens()];
		int i = 0;
		while (strTokens.hasMoreTokens()) {
			strArray[i] = strTokens.nextToken().trim();
			i++;
		}
		return strArray;
	}

    /**
     * strToSet
     * @param str
     * @return
     */
	public static Set<String> strToSet(String str) {
		return strToSet(str, DEFAULT_PATH_SEPARATOR);
	}

    /**
     * strToSet
     * @param str
     * @param separator
     * @return
     */
	public static Set<String> strToSet(String str, String separator) {
		String[] values = strToStrArray(str, separator);
		Set<String> result = new LinkedHashSet<String>();
		for (int i = 0; i < values.length; i++) {
			result.add(values[i]);
		}
		return result;
	}

	/**
	 * 将一个数组 用逗号分隔变为一个字符串
	 * @param array
	 * @return
	 */
	public static String ArrayToStr(Object[] array) {
		if (array == null || array.length < 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (Object obj : array) {
			if (StringUtils.isNotBlank(obj.toString())) {
				if (sb.length() > 0) {
					sb.append(DEFAULT_PATH_SEPARATOR);
				}
				sb.append(obj.toString());
			}
		}
		return sb.toString();
	}

    /**
     * CollectionToStr
     * @param coll
     * @return
     */
	public static String CollectionToStr(Collection<String> coll) {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (String string : coll) {
			if (i > 0) {
				sb.append(",");
			}
			i++;
			sb.append(string);
		}
		return sb.toString();
	}

	/**
	 * 转换给定字符串的编码格式
	 * @param inputString  待转换字符串对象
	 * @param inencoding 待转换字符串的编码格式
	 * @param outencoding  准备转换为的编码格式
	 * @return 指定编码与字符串的字符串对象
	 */
	public static String encodingTransfer(String inputString, String inencoding, String outencoding) {
		if (inputString == null || inputString.length() == 0) {
			return inputString;
		}
		try {
			return new String(inputString.getBytes(outencoding), inencoding);
		} catch (Exception e) {
			return inputString;
		}
	}

    /**
     * 字符串是否为非空白 空白的定义如下： <br>
     * 1、不为null <br>
     * 2、不为""<br>
     * @param str 被检测的字符串
     * @return 是否为非空
     */
    public static boolean isNotEmpty(CharSequence str) {
        return false == isEmpty(str);
    }

	/**
	 * 删除字符串中指定的字符 只要在delStrs参数中出现的字符，并且在inputString中也出现都会将其它删除
	 * @param inputString 待做删除处理的字符串
	 * @param delStrs  作为删除字符的参照数据，用逗号分隔。如果要删除逗号可以在这个字符串再加一个逗号
	 * @return 返回一个以inputString为基础不在delStrs出现新字符串
	 */
	public static String delString(String inputString, String delStrs) {
		if (inputString == null || inputString.length() == 0) {
			return inputString;
		}
		String[] strs = strToStrArray(delStrs);
		for (int i = 0; i < strs.length; i++) {
			if ("".equals(strs[i])) {
				inputString = inputString.replaceAll(" ", "");
			} else {
				if (strs[i].compareTo("a") >= 0) {
					inputString = replace(inputString, strs[i], "");
				} else {
					inputString = inputString.replaceAll("\\" + strs[i], "");
				}
			}
		}
		if (subCount(delStrs, ",") > strs.length) {
			inputString = inputString.replaceAll("\\,", "");
		}
		return inputString;
	}

	/**
	 * 去除左边多余的空格。
	 * @param value  待去左边空格的字符串
	 * @return 去掉左边空格后的字符串
	 */
	public static String trimLeft(String value) {
		String result = value;
		if (result == null) {
			return result;
		}
		char[] ch = result.toCharArray();
		int index = -1;
		for (int i = 0; i < ch.length; i++) {
			if (Character.isWhitespace(ch[i])) {
				index = i;
			} else {
				break;
			}
		}
		if (index != -1) {
			result = result.substring(index + 1);
		}
		return result;
	}

	/**
	 * 去除右边多余的空格。
	 * @param value 待去右边空格的字符串
	 * @return 去掉右边空格后的字符串
	 */
	public static String trimRight(String value) {
		String result = value;
		if (result == null) {
			return result;
		}
		char[] ch = result.toCharArray();
		int endIndex = -1;
		for (int i = ch.length - 1; i > -1; i--) {
			if (Character.isWhitespace(ch[i])) {
				endIndex = i;
			} else {
				break;
			}
		}
		if (endIndex != -1) {
			result = result.substring(0, endIndex);
		}
		return result;
	}

	/**
	 * 判断一个字符串中是否包含另一个字符串
	 * @param parentStr  父串
	 * @param subStr  子串
	 * @return 如果父串包含子串的内容返回真，否则返回假
	 */
	public static boolean isInclude(String parentStr, String subStr) {
		return parentStr.indexOf(subStr) >= 0;
	}

	/**
	 * 判断一个字符串中是否包含另一个字符串数组的任何一个
	 * @param parentStr    父串
	 * @param subStrs  子串集合(以逗号分隔的字符串)
	 * @return 如果父串包含子串的内容返回真，否则返回假
	 */
	public static boolean isIncludes(String parentStr, String subStrs) {
		String[] subStrArray = strToStrArray(subStrs);
		for (int j = 0; j < subStrArray.length; j++) {
			String subStr = subStrArray[j];
			if (isInclude(parentStr, subStr)) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	}

	/**
	 * 判断一个子字符串在父串中重复出现的次数
	 * @param parentStr 父串
	 * @param subStr  子串
	 * @return 子字符串在父字符串中重得出现的次数
	 */
	public static int subCount(String parentStr, String subStr) {
		int count = 0;

		for (int i = 0; i < ( parentStr.length() - subStr.length() ); i++) {
			String tempString = parentStr.substring(i, i + subStr.length());
			if (subStr.equals(tempString)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 得到在字符串中从开始字符串到结止字符串中间的子串
	 * @param parentStr 父串
	 * @param startStr 开始串
	 * @param endStr 结止串
	 * @return 返回开始串与结止串之间的子串
	 */
	public static String subString(String parentStr, String startStr, String endStr) {
		return parentStr.substring(parentStr.indexOf(startStr) + startStr.length(), parentStr.indexOf(endStr));
	}

	/**
	 * 得到在字符串中从开始字符串到结止字符串中间的子串的集合
	 * @param parentStr  父串
	 * @param startStr 开始串
	 * @param endStr 结止串
	 * @return 返回开始串与结止串之间的子串的集合
	 */
	public static List<String> subStringList(String parentStr, String startStr, String endStr) {
		List<String> result = new ArrayList<String>();
		List<String> elements = dividToList(parentStr, startStr, endStr);
		for (String element : elements) {
			if (!isIncludes(element, startStr) || !isIncludes(element, endStr)) {
				continue;
			}
			result.add(subString(element, startStr, endStr));
		}
		return result;

	}

	/**
	 * 将指定的String转换为Unicode的等价值
	 * 基础知识： 所有的转义字符都是由 '\' 打头的第二个字符 0-9 :八进制 u :是Unicode转意，长度固定为6位 Other:则为以下字母中的一个 b,t,n,f,r,",\ 都不满足，则产生一个编译错误
	 * 提供八进制也是为了和C语言兼容. b,t,n,f,r 则是为控制字符.书上的意思为:描述数据流的发送者希望那些信息如何被格式化或者被表示. 它可以写在代码的任意位置，只要转义后是合法的. 例如: int c=0\u003b
	 * 上面的代码可以编译通过，等同于int c=0; \u003b也就是';'的Unicode代码
	 * @param inStr 待转换为Unicode的等价字符串
	 * @return Unicode的字符串
	 */
	public static String getUnicodeStr(String inStr) {
		char[] myBuffer = inStr.toCharArray();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < inStr.length(); i++) {
			byte b = (byte) myBuffer[i];
			short s = (short) myBuffer[i];
			String hexS = Integer.toHexString(s);
			sb.append(" \\u");
			sb.append(fillStr(hexS, "0", 4, AFTER));
		}
		return sb.toString();

	}

	/**
	 * 判断一个字符串是否是合法的Java标识符
	 * @param s 待判断的字符串
	 * @return 如果参数s是一个合法的Java标识符返回真，否则返回假
	 */
	public static boolean isJavaIdentifier(String s) {
		if (s.length() == 0 || Character.isJavaIdentifierStart(s.charAt(0))) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
            if (!Character.isJavaIdentifierPart(s.charAt(i))) {
                return false;
            }
        }
		return true;
	}

	/**
	 * 替换字符串中满足条件的字符 例如: replaceAll("\com\google\platform\base\\util",'\\','/'); 返回的结果为: /com/google/platform/base/util
     * @param str 待替换的字符串
     * @param oldchar   待替换的字符
     * @param newchar 替换为的字符
     * @return 将str中的所有oldchar字符全部替换为newchar,并返回这个替换后的字符串
     */
	public static String replaceAll(String str, char oldchar, char newchar) {
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == oldchar) {
				chars[i] = newchar;
			}
		}
		return new String(chars);
	}

	/**
	 * 如果inStr字符串与没有给定length的长度，则用fill填充，在指定direction的方向 如果inStr字符串长度大于length就直截返回inStr，不做处理
	 * @param inStr
	 *            待处理的字符串
	 * @param fill
	 *            以该字符串作为填充字符串
	 * @param length
	 *            填充后要求的长度
	 * @param direction
	 *            填充方向，如果是AFTER填充在后面，否则填充在前面
	 * @return 返回一个指定长度填充后的字符串
	 */
	public static String fillStr(String inStr, String fill, int length, int direction) {
		if (inStr == null || inStr.length() > length || inStr.length() == 0) {
			return inStr;
		}
		StringBuffer tempStr = new StringBuffer("");
		for (int i = 0; i < length - inStr.length(); i++) {
			tempStr.append(fill);
		}
		if (direction == AFTER) {
			return new String(tempStr.toString() + inStr);
		} else {
			return new String(inStr + tempStr.toString());
		}
	}

	/**
	 * 字符串替换
	 * @param str
	 *            源字符串
	 * @param pattern
	 *            待替换的字符串
	 * @param replace
	 *            替换为的字符串
	 * @return
	 */
	public static String replace(String str, String pattern, String replace) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();
		while ( ( e = str.indexOf(pattern, s) ) >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
	}

	/**
	 * 分隔字符串到一个集合
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> dividToList(String str, String start, String end) {
		if (str == null || str.length() == 0) {
			return null;
		}
		int s = 0;
		int e = 0;
		List<String> result = new ArrayList<String>();
		if (isInclude(str, start) && isInclude(str, end)) {
			while ( ( e = str.indexOf(start, s) ) >= 0) {
				result.add(str.substring(s, e));
				s = str.indexOf(end, e) + end.length();
				result.add(str.substring(e, s));
			}
			if (s < str.length()) {
				result.add(str.substring(s));
			}
			if (s == 0) {
				result.add(str);
			}
		} else {
			result.add(str);
		}
		return result;
	}

	/**
	 * 首字母大写
	 * @param string
	 * @return
	 */
	public static String upperFrist(String string) {
		if (string == null) {
			return null;
		}
		String upper = string.toUpperCase();
		return upper.substring(0, 1) + string.substring(1);
	}

	/**
	 * 首字母小写
	 * @param string
	 * @return
	 */
	public static String lowerFrist(String string) {
		if (string == null) {
			return null;
		}
		String lower = string.toLowerCase();
		return lower.substring(0, 1) + string.substring(1);
	}

    /**
     * URLEncode
     * @param string
     * @param encode
     * @return
     */
	public static String URLEncode(String string, String encode) {
		try {
			return URLEncoder.encode(string, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将一个日期类型的对象，转换为指定格式的字符串
	 * 
	 * @param date
	 *            待转换的日期
	 * @param format
	 *            转换为字符串的相应格式 例如：DateToStr(new Date() ,"yyyy.MM.dd G 'at' hh:mm:ss a zzz");
	 * @return 一个字符串
	 *         <p>
	 **/
	public static String DateToStr(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * 对给定的字符串做html转义
	 * 
	 * @param string
	 * @return
	 */
	public static String escapeHtml(String string) {
		if (string == null || string.length() == 0) {
			return "";
		}

		char b;
		char c = 0;
		int i;
		int len = string.length();
		StringBuffer sb = new StringBuffer(len + 4);
		String t;

		for (i = 0; i < len; i += 1) {
			b = c;
			c = string.charAt(i);
			switch (c) {
			case '\\':
			case '"':
				sb.append('\\');
				sb.append(c);
				break;
			case '/':
				if (b == '<') {
					sb.append('\\');
				}
				sb.append(c);
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\r':
				sb.append("\\r");
				break;
			default:
				if (c < ' ' || ( c >= '\u0080' && c < '\u00a0' ) || ( c >= '\u2000' && c < '\u2100' )) {
					t = "000" + Integer.toHexString(c);
					sb.append("\\u" + t.substring(t.length() - 4));
				} else {
					sb.append(c);
				}
			}
		}

		return sb.toString();
	}

	/**
	 * 生成一个指定长度的随机字符串
	 * @param length
	 *            返回的字符串长度
	 * @return 返回一个随机
	 */
	public static String randomString(int length) {
		if (length < 1) {
			return null;
		}
		Random randGen = new Random();
		char[] numbersAndLetters = ( "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ).toCharArray();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(51)];
		}
		return new String(randBuffer);
	}

    /**
     * trim
     * @param target
     * @return
     */
	public final static String trim(String target) {
		if (target == null) {
            return null;
        }

		target = target.trim();

		return "".equals(target) ? null : target;
	}

	/**
	 * string to int
	 * @param numStr
	 * @return
	 */
	public static int strToInt(String numStr) {
		try {
			if (isNullOrBlank(numStr)) {
				return 0;
			}
			return Integer.valueOf(numStr);
		} catch (Exception e) {
			logger.error("str2int failed:", e);
		}
		return 0;
	}

	/**
	 * 判断null 或 空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNullOrBlank(String str) {
		if (str == null) {
			return true;
		}
		str = str.trim();
		if (!"".equals(str)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 判断 空字符串
	 * @param value
	 * @return
	 */
	public static boolean isBlank(String value) {
		boolean ret = false;
		if ( ( value != null ) && ("".equals(value))) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 判断null
	 */
	public static boolean isNull(Object value) {
		return ( value == null );
	}

	/**
	 * Title : 获取随机数
	 * @param digits
	 *            随机数取值范围 默认为0123456789
	 * @param length
	 *            随机数位数 默认为1位
	 * @return
	 */
	public static String getRandom(String digits, int length) {
		if (null == digits) {
			digits = "0123456789";
		}
		if (length <= 0) {
			length = 1;
		}
		char[] code = new char[length];
		String temp = "";
		for (int i = 0; i < length; i++) {
			code[i] = digits.charAt((int) ( Math.random() * digits.length() ));
			temp += "0";
		}
		String verifyCode = new String(code);

		if (verifyCode.equals(temp)) {
			verifyCode = getRandom(digits, length);
		}
		return verifyCode;
	}

	/**
	 * Title : 获取范围在min到max间的随机数
	 * @author : 聂水根
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandom(int min, int max) {
		return (int) ( Math.random() * ( max - min ) + min );
	}

	/**
	 * Title : 获取范围在min到max间的num个随机数
	 * @author : 聂水根
	 * @param min
	 * @param max
	 * @return
	 */
	public static Integer[] getRandomNum(int min, int max, int num) {
		Integer[] result = new Integer[num];
		for (int i = 0; i < num; i++) {
			result[i] = getRandom(min, max);
		}
		for (int i = 0; i < num; i++) {
			for (int k = i + 1; k < num; k++) {
				if (result[i].equals(result[k])) {
					getRandomNum(min, max, num);
				}
			}
		}
		return result;
	}

    /**
     * ifBlank
     * @param ss
     * @return
     */
	public static String ifBlank(String... ss) {
		String ret = "";
		for (String s : ss) {
			if (StringUtils.isNotBlank(s)) {
				ret = s;
				break;
			}
		}
		return ret;
	}

    /**
     * getUrlParamters
     * @param url
     * @param prefix
     * @return
     */
	public static String getUrlParamters(String url, String prefix) {
		String _return = null;
		if (url.indexOf("?") >= 0) {
			url = url.substring(url.indexOf("?") + 1);
			String[] paramters = url.split("&");
			if (paramters != null) {
				for (String s : paramters) {
					if (s.startsWith(prefix)) {
						String[] _temp = s.split("=");
						if (_temp.length > 1) {
							_return = _temp[1];
						}
					}
				}
			}
		}
		return _return;

	}

	/**
	 * 字符串转换成Long型数字
	 * @param src
	 * @return
	 */
	public static long toLong(String src) {
		long dest = 0;
		if (src == null || "".equals(src.trim())) {
			return 0;
		}
		try {
			dest = Long.parseLong(src.trim());
		} catch (Exception e) {
		}
		return dest;
	}

	/**
	 * 创建*Info类型的字符串，一般用于信息查询类接口的返回。
	 * 例如，调用buildInfoString("#",aaa,bbb,null,ddd); 得到字符串：aaa#bbb##ddd
	 * @param delimiter
	 * @param items
	 * @return
	 */
	public static String buildInfoString(String delimiter, Object... items) {
		StringBuffer buff = new StringBuffer();
		boolean firstItem = true;
		for (Object item : items) {
			if (firstItem) {
				firstItem = false;
			} else {
				buff.append(delimiter);
			}

			if (item == null) {
				buff.append("");
			} else {
				buff.append(item.toString());
			}
		}
		return buff.toString();
	}

    /**
     * yuan2Cent
     * @param src
     * @return
     */
	public static long yuan2Cent(String src) {
		if (src == null || "".equals(src.trim())) {
			return 0;
		}
		long ret = 0;
		try {
			ret = (long) ( Math.round(Double.parseDouble(src) * 100) );
		} catch (Exception e) {
		}
		return ret;
	}

	/**
	 * 把分转换为元
	 * @param src
	 * @param floor
	 *            是否取整
	 * @return
	 */
	public static String cent2Yuan(String src, boolean floor) {

		long temp = 0;
		try {
			String tem = src.trim();
			temp = Long.parseLong(tem);
		} catch (Exception e) {
		}
		return cent2Yuan(temp, floor);
	}

    /**
     * cent2Yuan
     * @param src
     * @param floor
     * @return
     */
	public static String cent2Yuan(long src, boolean floor) {
		DecimalFormat SDF_YUAN = new DecimalFormat("0.00");
		String yuan = "0.00";
		try {
			yuan = SDF_YUAN.format(src / 100.00);
		} catch (Exception e) {
		}
		if (floor) {
			int idxDot = yuan.indexOf(".");
			if (idxDot >= 0) {
				yuan = yuan.substring(0, idxDot);
			}
		}
		return yuan;

	}

    /**
     * cent2Yuan
     * @param src
     * @param floor
     * @return
     */
	public static String cent2Yuan(Double src, boolean floor) {
		DecimalFormat SDF_YUAN = new DecimalFormat("0.00");
		String yuan = "0.00";
		try {
			yuan = SDF_YUAN.format(src / 100.00);
		} catch (Exception e) {
		}
		if (floor) {
			int idxDot = yuan.indexOf(".");
			if (idxDot >= 0) {
				yuan = yuan.substring(0, idxDot);
			}
		}
		return yuan;

	}

	/**
	 * 
	 * 将一个字符串按分隔符分成多个子串。 此方法用于代替Jdk的String.split()方法，不同的地方：<br>
	 * (1)在此方法中，空字符串不管在哪个位置都视为一个有效字串。 <br>
	 * (2)在此方法中，分隔符参数不是一个正则表达式。<br>
	 * 
	 * @param src
	 *            源字符串
	 * @param delimiter
	 *            分隔符
	 * @return 字符串数组。
	 */
	public static String[] split(String src, String delimiter) {
		if (src == null || delimiter == null || "".equals(src.trim()) || "".equals(delimiter)) {
			return new String[] { src };
		}
		ArrayList<String> list = new ArrayList<String>();
		int lengthOfDelimiter = delimiter.length();
		int pos = 0;
		while (true) {
			if (pos < src.length()) {

				int indexOfDelimiter = src.indexOf(delimiter, pos);
				if (indexOfDelimiter < 0) {
					list.add(src.substring(pos));
					break;
				} else {
					list.add(src.substring(pos, indexOfDelimiter));
					pos = indexOfDelimiter + lengthOfDelimiter;
				}
			} else if (pos == src.length()) {
				list.add("");
				break;
			} else {
				break;
			}
		}
		String[] result = new String[list.size()];
		list.toArray(result);
		return result;
	}

	/**
	 * 若原对象为Null则返回空字符串,否则先转为String类型，再剪去字符串两端的空格及控制字符
	 * 
	 * @param src
	 * @return
	 */
	public static String trim(Object src) {
		if (src == null) {
			return "";
		}
		String str = src.toString();
		return str.trim();
	}

	/**
	 * 将一个形如key1=value1&key2=value2...的字符串转换成Map映射。
	 * @param src
	 * @return
	 * 
	 */
	public static Map<String, String> string2Map(String src) {
		return string2Map(src, String.class, String.class, "&", "=");
	}

	/**
	 * 将一个形如key1=value1&key2=value2...的字符串转换成Map映射。 注意：key和value只支持类型为String,Long,Integer,Float,Double！
	 * @param <K>
	 * @param <V>
	 * @param src
	 *            源字符串
	 * @param keyClass
	 *            生成的Map的Key的类型，默认String
	 * @param valueClass
	 *            生成的Map的Value的类型，默认String
	 * @param fieldDelimiter
	 *            字段与字段之间的分隔符，默认&
	 * @param keyValueDelimiter
	 *            key和value之间的分隔符，默认=
	 * @return
	 * 
	 */
	public static <K extends Object, V extends Object> Map<K, V> string2Map(String src, Class<K> keyClass, Class<V> valueClass,
			String fieldDelimiter, String keyValueDelimiter) {
		Map<K, V> result = new HashMap<K, V>();

		if (src == null || "".equals(src.trim())) {
			return result;
		}

		String[] fields = StringHelper.split(src, fieldDelimiter);
		for (int i = 0; i < fields.length; i++) {
			String[] keyValue = StringHelper.split(fields[i], keyValueDelimiter);
			String key = keyValue[0];
			String value = keyValue[1];

			K objKey = null;
			V objValue = null;

			if (keyClass == String.class) {
				objKey = (K) key;
			} else if (keyClass == Integer.class) {
				objKey = (K) Integer.valueOf(key);
			} else if (keyClass == Long.class) {
				objKey = (K) Long.valueOf(key);
			} else if (keyClass == Float.class) {
				objKey = (K) Float.valueOf(key);
			} else if (keyClass == Double.class) {
				objKey = (K) Double.valueOf(key);
			} else {
				return null;
			}

			if (valueClass == String.class) {
				objValue = (V) value;
			} else if (valueClass == Integer.class) {
				objValue = (V) Integer.valueOf(value);
			} else if (valueClass == Long.class) {
				objValue = (V) Long.valueOf(value);
			} else if (valueClass == Float.class) {
				objValue = (V) Float.valueOf(value);
			} else if (valueClass == Double.class) {
				objValue = (V) Double.valueOf(value);
			} else {
				return null;
			}

			result.put(objKey, objValue);

		}

		return result;
	}

	/**
	 * Map转换成字符串.主要用于打印调试信息
	 * @param map
	 * @return
	 */
	public static String map2String(Map map) {
		return map2String(map, "", "", "", true, "=");
	}

	/**
	 * Map转换成字符串，主要用于打印调试信息
	 * @param map
	 * @param head
	 *            输出的头
	 * @param entryPrefix
	 *            每一项输出的前缀
	 * @param foot
	 *            输出的脚
	 * @param isOneItemPl
	 *            是否每行一项
	 * @param kvSep
	 *            Key和Value的分隔符
	 * @return
	 */
	public static String map2String(Map map, String head, String entryPrefix, String foot, boolean isOneItemPl, String kvSep) {

		if (map == null) {
			return null;
		}
		String lineSeparator = (String) System.getProperty("line.separator");
		StringBuffer buff = new StringBuffer();
		if (head != null && !"".equals(head)) {
			buff.append(head);

			if (isOneItemPl) {
				buff.append(lineSeparator);
			}
		}
		if (entryPrefix == null) {
			entryPrefix = "";
		}
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Entry entry = (Entry) iterator.next();
			buff.append(entryPrefix).append(entry.getKey()).append(kvSep).append(StringHelper.ArrayToStr((Object[]) entry.getValue()));

			if (isOneItemPl) {
				buff.append(lineSeparator);
			}
		}
		if (foot != null && !"".equals(foot)) {
			buff.append(foot);
			if (isOneItemPl) {
				buff.append(lineSeparator);
			}
		}
		return buff.toString();
	}

	/**
	 * 填充数字0
	 * @param src
	 * @param targetLength
	 * @return
	 */
	public static String fill(long src, int targetLength) {
		return fill(String.valueOf(src), "0", targetLength, true);
	}

	/**
	 * 填充字符串。如果原字符串比目标长度长，则截去多出的部分。如果onTheLeft等于true 截去左边部分，否则截去右边部分。 注意填充物一般为单个字符，如果是多个字符，可能导致最后的结果不可用。
	 * @param src
	 *            原字符串
	 * @param padding
	 *            填充物，一般是0、空格等
	 * @param targetLength
	 *            目标长度
	 * @param onTheLeft
	 *            是否左填充。
	 * @return
	 */
	public static String fill(String src, String padding, int targetLength, boolean onTheLeft) {

		if (src == null) {
			src = "";
		}
		while (src.length() < targetLength) {
			if (onTheLeft) {
				src = padding + src;
			} else {
				src = src + padding;
			}
		}
		if (src.length() > targetLength) {
			if (onTheLeft) {
				src = src.substring(src.length() - targetLength);
			} else {
				src = src.substring(0, targetLength);
			}
		}
		return src;
	}

    /**
     * changeListToString
     * @param source
     * @param delimiter
     * @return
     */
	public static String changeListToString(List<String> source, String delimiter) {
		StringBuilder builder = new StringBuilder();
		if (source != null && source.size() > 0) {
			int len = source.size();
			for (int i = 0; i < len; i++) {
				builder.append(source.get(i));
				if (i < len - 1) {
					builder.append(delimiter);
				}

			}
		}
		return builder.toString();
	}

    /**
     * changeListToStringWithTag
     * @param source
     * @param delimiter
     * @param tag
     * @return
     */
	public static String changeListToStringWithTag(List<String> source, String delimiter, String tag) {
		StringBuilder builder = new StringBuilder();
		if (source != null && source.size() > 0) {
			int len = source.size();
			for (int i = 0; i < len; i++) {
				builder.append(tag + source.get(i) + tag);
				if (i < len - 1) {
					builder.append(delimiter);
				}

			}
		}
		return builder.toString();
	}

	/**
	 * 是否存在null、或者空字符串。任意一个参数满足条件，返回true；否则返回false。<br>
	 * 先将参数对象转成字符串，修剪后进行判断。仅包含空格或ASCII控制字符也视为条件满足。<br>
	 * Noe:Null Or Empty<br>
	 * @param someObj
	 * @return
	 */
	public static boolean existNoe(Object... someObj) {
		if (someObj == null || someObj.length == 0) {
			throw new RuntimeException("参数不能为空,必须有至少一个对象");
		}
		for (int i = 0; i < someObj.length; i++) {
			Object obj = someObj[i];
			if (obj == null || "".equals(obj.toString().trim())) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 若原字符串为Null则返回空字符串。
	 * @param src
	 * @return
	 */
	public static String null2Empty(String src) {
		if (src == null) {
			return "";
		}
		return src;
	}

	/**
	 * 若原字符串为Null则返回空字符串。
	 * 
	 * @param src
	 * @return
	 */
	public static boolean isEmpty(String src) {
		String value = null2Empty(src);
		if ("".equals(value)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否全部非空
	 * @param src
	 * @return
	 */
	public static boolean isAllNotEmpty(String... src) {
		for (int i = 0; i < src.length; i++) {
			String value = src[i];
			if (value == null || "".equals(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否全部为空
	 * @param src
	 * @return
	 */
	public static boolean isAllEmpty(String... src) {
		for (int i = 0; i < src.length; i++) {
			String value = src[i];
			if (value != null && !"".equals(value)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否全为字母或数字
	 * @param src
	 * @return
	 */
	public static boolean isLetterOrNumber(String src) {
		if (src == null) {
			return false;
		}
		try {
			byte[] bytesOfSrc = src.getBytes("utf-8");

			for (int i = 0; i < bytesOfSrc.length; i++) {
				byte one = bytesOfSrc[i];
				if (one < '0' || ( one > '9' && one < 'A' ) || ( one > 'Z' && one < 'a' ) || one > 'z') {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

    /**
     * privacyInfo
     * @param currString
     * @return
     */
	public static String privacyInfo(String currString) {
		String targetString = "";
		if (!StringHelper.isBlank(currString)) {
			if (currString.length() <= 3) {
				targetString = currString.replace(StringUtils.substring(currString, 0, 1), "*");
			} else if (currString.length() > 3) {
				targetString = currString.replace(StringUtils.substring(currString, 0, 2), "*");
			}
		}

		return targetString;
	}

    /**
     * 字符串是否为空，空的定义如下:<br>
     * 1、为null <br>
     * 2、为""<br>
     * @param str 被检测的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 如果对象是字符串是否为空串空的定义如下:<br>
     * 1、为null <br>
     * 2、为""<br>
     * @param obj 对象
     * @return 如果为字符串是否为空串
     */
    public static boolean isEmptyIfStr(Object obj) {
        if (null == obj) {
            return true;
        } else if (obj instanceof CharSequence) {
            return 0 == ((CharSequence) obj).length();
        }
        return false;
    }

    /**
     * 去掉指定后缀
     * @param str 字符串
     * @param suffix 后缀
     * @return 切掉后的字符串，若后缀不是 suffix， 返回原字符串
     */
    public static String removeSuffix(CharSequence str, CharSequence suffix) {
        if (isEmpty(str) || isEmpty(suffix)) {
            return str(str);
        }
        final String str2 = str.toString();
        if (str2.endsWith(suffix.toString())) {
            // 截取前半段
            return subPre(str2, str2.length() - suffix.length());
        }
        return str2;
    }

    /**
     * 切割指定位置之前部分的字符串
     * @param string 字符串
     * @param toIndex 切割到的位置（不包括）
     * @return 切割后的剩余的前半部分字符串
     */
    public static String subPre(CharSequence string, int toIndex) {
        return sub(string, 0, toIndex);
    }


    /**
     * {@link CharSequence} 转为字符串，null安全
     * @param cs {@link CharSequence}
     * @return 字符串
     */
    public static String str(CharSequence cs) {
        return null == cs ? null : cs.toString();
    }


    /**
     * 改进JDK subString<br>
     * index从0开始计算，最后一个字符为-1<br>
     * 如果from和to位置一样，返回 "" <br>
     * 如果from或to为负数，则按照length从后向前数位置，如果绝对值大于字符串长度，则from归到0，to归到length<br>
     * 如果经过修正的index中from大于to，则互换from和to example: <br>
     * abcdefgh 2 3 =》 c <br>
     * abcdefgh 2 -3 =》 cde <br>
     * @param str String
     * @param fromIndex 开始的index（包括）
     * @param toIndex 结束的index（不包括）
     * @return 字串
     */
    public static String sub(CharSequence str, int fromIndex, int toIndex) {
        if (isEmpty(str)) {
            return str(str);
        }
        int len = str.length();

        if (fromIndex < 0) {
            fromIndex = len + fromIndex;
            if (fromIndex < 0) {
                fromIndex = 0;
            }
        } else if (fromIndex > len) {
            fromIndex = len;
        }

        if (toIndex < 0) {
            toIndex = len + toIndex;
            if (toIndex < 0) {
                toIndex = len;
            }
        } else if (toIndex > len) {
            toIndex = len;
        }

        if (toIndex < fromIndex) {
            int tmp = fromIndex;
            fromIndex = toIndex;
            toIndex = tmp;
        }

        if (fromIndex == toIndex) {
            return EMPTY;
        }

        return str.toString().substring(fromIndex, toIndex);
    }


    /**
     * 切割指定位置之后部分的字符串
     *
     * @param string 字符串
     * @param fromIndex 切割开始的位置（包括）
     * @return 切割后后剩余的后半部分字符串
     */
    public static String subSuf(CharSequence string, int fromIndex) {
        if (isEmpty(string)) {
            return null;
        }
        return sub(string, fromIndex, string.length());
    }

	/**
	 * 比较两个字符串是否相等
	 * @param one
	 * @param another
	 * @return
	 */
	public static boolean equals(String one, String another) {
		if (one == null) {
			if (another == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (another == null) {
				return false;
			} else {
				return one.equals(another);
			}
		}
	}
	
	/**
	 * 获取字符串
	 * @param value 字符串
	 * @param maxLen 最大字符串长度
	 * @return
	 */
	public static String getSubStr(String value,int maxLen){
		if (value.length() > maxLen) {
			value = value.substring(0, maxLen);
		}
		return value;
	}

    public static boolean getBoolean(String str) {
        if (isNull(str)) {
            return false;
        } else if ("0".equals(str.trim())) {
            return false;
        } else {
            return "1".equals(str.trim()) || "true".equalsIgnoreCase(str.trim());
        }
    }


    /**
	 * @Description: Just for test
	 * @Autor:jasonandy@hotmail.com
	 */
	public static void main(String[] args) {
	    //buildInfoString
        String strKey = buildInfoString("_", "湘A10086", "2019-01-01", "10010");
        System.out.println(strKey);
        System.out.println(getUnicodeStr(strKey));
        //System.out.println(getDelRepeStrKey("湘A10086","2019-01-01","10010"));
	}
}
