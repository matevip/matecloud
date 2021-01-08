package vip.mate.core.common.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Gson工具类
 *
 * @author pangu
 * @link https://blog.csdn.net/zhengzhi123456789/article/details/50951234
 */
public class GsonUtil {

	/**
	 * 不用创建对象,直接使用Gson.就可以调用方法
	 */
	private static Gson gson = null;

	//判断gson对象是否存在了,不存在则创建对象
	static {
		//gson = new Gson();
		//当使用GsonBuilder方式时属性为空的时候输出来的json字符串是有键值key的,显示形式是"key":null，而直接new出来的就没有"key":null的
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	}

	/**
	 * 无参的私有构造方法
	 */
	private GsonUtil() {
	}

	/**
	 * 将对象转成json格式
	 *
	 * @param object 对象
	 * @return String json报文
	 */
	public static String toJson(Object object) {
		String gsonString = null;
		if (gson != null) {
			gsonString = gson.toJson(object);
		}
		return gsonString;
	}

	/**
	 * 将json转成特定的cls的对象
	 *
	 * @param gsonString gson串
	 * @param cls        　类名
	 * @return 泛型实例
	 */
	public static <T> T fromJson(String gsonString, Class<T> cls) {
		T t = null;
		if (gson != null) {
			//传入json对象和对象类型,将json转成对象
			t = gson.fromJson(gsonString, cls);
		}
		return t;
	}

	/**
	 * json字符串转成list
	 *
	 * @param gsonString gson字符串
	 * @param cls        类名
	 * @return List对象
	 */
	public static <T> List<T> fromGsonList(String gsonString, Class<T> cls) {
		List<T> list = null;
		if (gson != null) {
			//根据泛型返回解析指定的类型,TypeToken<List<T>>{}.getType()获取返回类型
			list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
			}.getType());
		}
		return list;
	}

	/**
	 * json字符串转成list
	 *
	 * @param json json串
	 * @param cls  类名
	 * @return List对象
	 */
	public static <T> List<T> fromJsonList(String json, Class<T> cls) {
		ArrayList<T> mList = new ArrayList<T>();

		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		for (final JsonElement elem : array) {
			mList.add(gson.fromJson(elem, cls));
		}
		return mList;
	}

	/**
	 * json字符串转成list中有map的
	 *
	 * @param gsonString gson字符串
	 * @return MapList
	 */
	public static <T> List<Map<String, T>> fromGsonListMap(String gsonString) {
		List<Map<String, T>> list = null;
		if (gson != null) {
			list = gson.fromJson(gsonString,
					new TypeToken<List<Map<String, T>>>() {
					}.getType());
		}
		return list;
	}

	/**
	 * json字符串转成map的
	 *
	 * @param gsonString gson字符串
	 * @return Map
	 */
	public static <T> Map<String, T> fromGsonMap(String gsonString) {
		Map<String, T> map = null;
		if (gson != null) {
			map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
			}.getType());
		}
		return map;
	}
}
