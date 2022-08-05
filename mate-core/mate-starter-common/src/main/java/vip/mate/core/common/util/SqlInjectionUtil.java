package vip.mate.core.common.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.util.ObjectUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * SQL注入处理工具类
 *
 * @author : hackerdom
 */
@Slf4j
public class SqlInjectionUtil {

    private static final String SQL_REGEX = "\\b(and|or)\\b.{1,6}?(=|>|<|\\bin\\b|\\blike\\b)|\\/\\*.+?\\*\\/|<\\s*script\\b|\\bEXEC\\b|UNION.+?SELECT|UPDATE.+?SET|INSERT\\s+INTO.+?VALUES|(SELECT|DELETE).+?FROM|(CREATE|ALTER|DROP|TRUNCATE)\\s+(TABLE|DATABASE)";

    private static final Pattern SQL_PATTERN = Pattern.compile(SQL_REGEX, Pattern.CASE_INSENSITIVE);

    private static boolean matching(String lowerValue, String param) {
        if (SQL_PATTERN.matcher(param).find()) {
            log.error("the parameter contains keywords {} that do not allow sql!", lowerValue);
            return true;
        }
        return false;
    }

    private static String toLowerCase(Object obj) {
        return Optional.ofNullable(obj)
                .map(Object::toString)
                .map(String::toLowerCase)
                .orElse("");
    }

    private static boolean checking(Object value) {
        String lowerValue = toLowerCase(value);
        return matching(lowerValue, lowerValue);
    }

    /**
     * get请求sql注入校验
     *
     * @param value 具体的检验
     * @return 是否存在不合规内容
     */
    public static boolean checkForGet(String value) {
        String lowerValue = URLDecoder.decode(value, StandardCharsets.UTF_8).toLowerCase();
        return Stream.of(lowerValue.split("\\&"))
                .map(kp -> kp.substring(kp.indexOf(StringPool.EQUALS) + 1))
                .parallel()
                .anyMatch(param -> matching(lowerValue, param));
    }

    /**
     * post请求sql注入校验
     *
     * @param value 具体的检验
     * @return 是否存在不合规内容
     */
    public static boolean checkForPost(String value) {
        List<JsonElement> result = new ArrayList<>();
        JsonElement jsonElement = GsonUtil.toJsonElement(value);
        iterator(jsonElement, result);
        return CollectionUtil.isNotEmpty(result);
    }

    private static void iterator(JsonElement jsonElement, List<JsonElement> result) {
        if (jsonElement.isJsonNull()) {
            return;
        }

        if (jsonElement.isJsonPrimitive()) {
            boolean hasInjection = checking(jsonElement.toString());
            if (hasInjection) {
                result.add(jsonElement);
            }
            return;
        }

        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            if (ObjectUtil.isNotEmpty(jsonArray)) {
                for (JsonElement je : jsonArray) {
                    iterator(je, result);
                }
            }
            return;
        }

        if (jsonElement.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> es = jsonElement.getAsJsonObject().entrySet();
            for (Map.Entry<String, JsonElement> en : es) {
                iterator(en.getValue(), result);
            }
        }
    }

}