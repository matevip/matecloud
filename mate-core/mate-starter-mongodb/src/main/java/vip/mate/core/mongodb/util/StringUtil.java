package vip.mate.core.mongodb.util;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 *
 * @author LaoWang
 * @date 2020-10-20
 */
public class StringUtil {

	private StringUtil() {
		super();
	}

	/**
	 * 出去null和""
	 *
	 * @param src
	 * @return
	 */
	public static String formatNull(String src) {
		return (src == null || "null".equals(src)) ? "" : src;
	}

	/**
	 * 判断字符串是否为空的正则表达式，空白字符对应的unicode编码
	 */
	private static final String EMPTY_REGEX = "[\\s\\u00a0\\u2007\\u202f\\u0009-\\u000d\\u001c-\\u001f]+";

	/**
	 * 验证字符串是否为空
	 *
	 * @param input
	 * @return
	 */
	public static boolean isEmpty(String input) {
		return input == null || input.equals("") || input.matches(EMPTY_REGEX);
	}

	public static boolean isNotEmpty(String input) {
		return !isEmpty(input);
	}

	private static final String NUM_REG = "(\\+|\\-)?\\s*\\d+(\\.\\d+)?";


	//首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

	//首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}


	/**
	 * 判断是否数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (isEmpty(str)) {
			return false;
		}

		if (str.trim().matches(NUM_REG)) {
			return true;
		}

		return false;
	}

	/**
	 * 判断是否包含有乱码的数据,如果字符串中包含有替换字符就认为是乱码
	 *
	 * @param str
	 * @return
	 */
	public static boolean containUnreadableCode(String str) {
		return contain(str, "\\ufffd");
	}

	/**
	 * 判读是否包含数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean containNumber(String str) {
		return contain(str, "\\d");
	}

	/**
	 * 判断是否包含a-zA-Z_0-9
	 *
	 * @param str
	 * @return
	 */
	public static boolean containWord(String str) {
		return contain(str, "\\w");
	}

	/**
	 * 是否包含有标点符号
	 *
	 * @param str
	 * @return
	 */
	public static boolean containPunct(String str) {
		return contain(str, PUNCT_REG);
	}

	public static boolean contain(String str, String regex) {
		if (isEmpty(str) || isEmpty(regex)) {
			return false;
		}

		if (str.trim().matches(regex)) {
			return true;
		}

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return true;
		}

		return false;
	}

	/**
	 * 替换所有的（不区分大小写）
	 *
	 * @param input
	 * @param regex
	 * @param replacement
	 * @return
	 */
	public static String replaceAll(String input, String regex,
	                                String replacement) {
		return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(input)
				.replaceAll(replacement);
	}

	/**
	 * 移除所有的空格
	 *
	 * @param text
	 * @return
	 */
	public static String removeAllSpace(String text) {
		if (isEmpty(text)) {
			return text;
		}

		return text.replaceAll("[ ]+", "");
	}

	private static final String PUNCT_REG = "[^a-zA-Z0-9\\u4e00-\\u9fa5]";

	/**
	 * 移除字符串中的所有的中英文标点符号
	 *
	 * @param str
	 * @return
	 */
	public static String removeAllPunct(String str) {
		if (isEmpty(str)) {
			return str;
		}

		return str.replaceAll(PUNCT_REG, "");
	}

	/**
	 * 计算str中包含多少个子字符串sub
	 *
	 * @param str
	 * @param sub
	 * @return
	 */
	public static int countMatches(String str, String sub) {
		if (isEmpty(str) || isEmpty(sub)) {
			return 0;
		}

		int count = 0;
		int idx = 0;
		while ((idx = str.indexOf(sub, idx)) != -1) {
			count++;
			idx += sub.length();
		}

		return count;
	}

	/**
	 * 获得源字符串的一个子字符串
	 *
	 * @param str        ：源字符串
	 * @param beginIndex ：开始索引（包括）
	 * @param endIndex   ：结束索引（不包括）
	 * @return
	 */
	public static String substring(String str, int beginIndex, int endIndex) {
		if (isEmpty(str)) {
			return str;
		}

		int length = str.length();

		if (beginIndex >= length || endIndex <= 0 || beginIndex >= endIndex) {
			return null;
		}

		if (beginIndex < 0) {
			beginIndex = 0;
		}
		if (endIndex > length) {
			endIndex = length;
		}

		return str.substring(beginIndex, endIndex);
	}

	/**
	 * 计算str中包含子字符串sub所在位置的前一个字符或者后一个字符和sub所组成的新字符串
	 *
	 * @param str
	 * @param sub
	 * @return
	 */
	public static Set<String> substring(String str, String sub) {
		if (isEmpty(str) || isEmpty(sub)) {
			return null;
		}

		Set<String> result = new HashSet<String>();
		int idx = 0;
		while ((idx = str.indexOf(sub, idx)) != -1) {
			String temp = substring(str, idx - 1, idx + sub.length());
			if (!isEmpty(temp)) {
				temp = removeAllPunct(temp);
				if (!sub.equalsIgnoreCase(temp) && !containWord(temp)) {
					result.add(temp);
				}

			}

			temp = substring(str, idx, idx + sub.length() + 1);
			if (!isEmpty(temp)) {
				temp = removeAllPunct(temp);
				if (!sub.equalsIgnoreCase(temp) && !containWord(temp)) {
					result.add(temp);
				}
			}

			idx += sub.length();
		}

		return result;
	}

	/**
	 * 过滤掉XML中无法解析的非法字符
	 *
	 * @param content
	 * @return
	 */
	public static String wrapXmlContent(String content) {
		if (isEmpty(content)) {
			return "";
		}

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < content.length(); i++) {
			char ch = content.charAt(i);
			if ((ch == '\t') || (ch == '\n') || (ch == '\r')
					|| ((ch >= ' ') && (ch <= 55295))
					|| ((ch >= 57344) && (ch <= 65533))
					|| ((ch >= 65536) && (ch <= 1114111))) {
				result.append(ch);
			}
		}

		return result.toString();
	}

	/**
	 * 判断字符串的长度
	 *
	 * @param str
	 * @return
	 */
	public static boolean overLength(String str) {
		if (isEmpty(str)) {
			return false;
		}

		return str.length() > 1 ? true : false;
	}

	/**
	 * 字符串中含有特殊字符的处理
	 *
	 * @param str
	 * @return
	 */
	public static String specialStr(String str) {
		str = str.replaceAll("[^\\u4e00-\\u9fa5 | 0-9| a-zA-Z | \\.]+", " ")
				.replaceAll("[\\.]{2,}", " ").trim();
		return str;
	}

	/**
	 * 将特殊符号去掉，但是保留空格
	 *
	 * @param str
	 * @return
	 */
	public static String replaceInValidateChar(String str) {
		return str.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5\\s+]", " ");
	}

	/**
	 * 返回字符串对应的unicode编码
	 *
	 * @param str
	 * @return
	 */
	public static String[] toHexString(String str) {
		char[] chars = str.toCharArray();

		String[] result = new String[chars.length];

		for (int i = 0; i < chars.length; i++) {
			result[i] = Integer.toHexString((int) chars[i]);
		}

		return result;
	}

	public static String getUuid() {
		return UUID.randomUUID().toString();
	}

	public static boolean isUrl(String src) {
		String regex = "http[s]?:\\/\\/([\\w-]+\\.[\\w-]+)(\\.[\\w-])+(:\\d{2,10})?.*";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(src);
		return matcher.matches();
	}

	/**
	 * sql 查询转义
	 *
	 * @param str
	 * @return
	 */
	public static String escapeSql(String str) {
		if (StringUtil.isNotEmpty(str)) {
			StringBuffer strbuff = new StringBuffer();
			for (String s : str.split("")) {
				if (s.equals("%") || s.equals("_") || s.equals("\\")) {
					strbuff.append("\\");
				}
				strbuff.append(s);
			}
			return strbuff.toString();
		}
		return str;
	}
}
