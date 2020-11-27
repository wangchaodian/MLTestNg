package com.qa.restclient;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * url参数转换，替换值
 */
public class UrlParam {

	public static void main(String[] args) {
		String  json = "{\"emotionId\":\"120\",\"remark\":\"\",\"userId\":\"344\"}";
		JSONObject jsonObject = JSONObject.parseObject(json);
		System.out.println(jsonObject);
		
	}
	
	// 通过key替换value值
	public static  void  mapReplace(Map<String, Object> map,String key, String value) {
		if (map.containsKey(key)) {
			String st = map.get(key).toString();
			st = value;
			map.put(key, value);
		}
	}

	/**
	 * 将url参数转换成map
	 */
	public static Map<String, Object> getUrlParams(String param) {
		Map<String, Object> map = new HashMap<String, Object>(0);
		if (StringUtils.isBlank(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 将map转换成url
	 */
	public static String getUrlParamsByMap(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}

	//通过时间格式获取当前时间 ，time = "yyyy-MM-dd HH:mm:ss"
	public static String getNowDate(String time){

		DateFormat df = new SimpleDateFormat(time);
		Date date = new Date();
		String dateString = df.format(date);
		return dateString;
	}

}
