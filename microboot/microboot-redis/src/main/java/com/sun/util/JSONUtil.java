package com.sun.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {

	// 定义将对象转换成JSON对象的方法
	public static JSONObject objToJson(Object obj) {
		// 创建一个JSONObject对象
		JSONObject json = new JSONObject();
		// 将对象的属性名称和对应的值以键值对的形式保存到json对象中
		// 取得对象的的名称和对应的值
		Field[] fs = obj.getClass().getDeclaredFields();
		// 取得名称
		for (int i = 0; i < fs.length; i++) {
			String fname = fs[i].getName();
			Object fvalue = null;
			// 取得概属性对应的getter方法
			try {
				Method m = obj.getClass().getMethod("get" + initCap(fname));
				fvalue = m.invoke(obj);
				if ("Date".equals(fs[i].getType().getSimpleName())) {
					json.put(fname, new SimpleDateFormat("yyyy-MM-dd").format(fvalue));
				} else {
					json.put(fname, fvalue);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return json;
	}

	public static JSONObject listToJson(List<?> all, String jsonName) {
		// 创建一个JSONObject对象
		JSONObject json = new JSONObject();
		// 创建一个JSON数组
		JSONArray array = new JSONArray();
		// 迭代传递的集合
		Iterator<?> iter = all.iterator();
		while (iter.hasNext()) {
			array.add(objToJson(iter.next()));
		}
		json.put(jsonName, array);
		return json;

	}

	// 将查询单的数据量保存到json数组中
	public static JSONObject listToJson(List<?> all, int count, String jsonName) {
		// 创建一个JSONObject对象
		JSONObject json = listToJson(all, jsonName);
		json.put("count", count);
		return json;
	}

	/**
	 * 讲属性名的首字母改成大写
	 * 
	 * @param fname
	 * @return
	 */
	private static String initCap(String fname) {
		return fname.substring(0, 1).toUpperCase() + fname.substring(1);
	}

}
