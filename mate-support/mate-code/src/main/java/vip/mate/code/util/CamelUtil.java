package vip.mate.code.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gao
 * 驼峰与下划线互转
 */
public class CamelUtil {
    /**
     * 下划线转驼峰法
     *
     * @param line       源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0))
                    : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰法转下划线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰Map集
     *
     * @param map 源字符串
     * @return 转换后的Map
     */
    public static Map<String, Object> getUnderline2CamelMap(Map<String, Object> map) {
        Map<String, Object> newMap = new HashMap<>();
        for (String key : map.keySet()) {
            String camel = underline2Camel(key, true);
            //存在 " " 转换为""
            if (" ".equals(map.get(key))) {
                newMap.put(camel, "");
            } else {
                newMap.put(camel, map.get(key));
            }
        }
        return newMap;
    }


    /**
     * Map集
     * 驼峰转下划线
     *
     * @param map 源字符串
     * @return 转换后的Map
     */
    public static Map<String, Object> getCamel2UnderlineMap(Map<String, Object> map) {
        Map<String, Object> newMap = new HashMap<>();
        for (String key : map.keySet()) {
            String camel = camel2Underline(key);
            newMap.put(camel, map.get(key));
        }
        return newMap;
    }


    /**
     * 驼峰法转下划线List套Map集
     *
     * @param list 源字符串
     * @return 转换后的List套Map
     */
    public static List<Map<String, Object>> getUnderline2CamelList(List<Map<String, Object>> list) {
        Map<String, Object> newMap = new HashMap<>();
        List<Map<String, Object>> returnList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            for (String key : map.keySet()) {
                String camel = underline2Camel(key, true);
                newMap.put(camel, map.get(key));
            }
            returnList.add(newMap);
        }

        return returnList;
    }


    /**
     * 下划线转驼峰法List套Map集
     *
     * @param list 源字符串
     * @return 转换后的List套Map
     */
    public static List<Map<String, Object>> getCamel2UnderlineList(List<Map<String, Object>> list) {
        Map<String, Object> newMap = new HashMap<>();
        List<Map<String, Object>> returnList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            for (String key : map.keySet()) {
                String camel = camel2Underline(key);
                newMap.put(camel, map.get(key));
            }
            returnList.add(newMap);
        }

        return returnList;
    }


    /**
     * 下划线转驼峰法List
     *
     * @param list 源字符串
     * @return 转换后的List套String
     */
    public static List<String> getList(List<String> list) {
        List<String> returnList = new ArrayList<>();
        System.out.println(list.get(0));
        for (String key : list) {
            String camel = underline2Camel(key, true);
            returnList.add(camel);
        }
        return returnList;
    }
}
