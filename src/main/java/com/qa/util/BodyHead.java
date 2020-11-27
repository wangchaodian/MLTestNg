package com.qa.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.qa.restclient.*;

public class BodyHead {

	public static String timestamp;
	public static String sign;

//	将时间戳、token等参数传入header中
	public static HashMap<String, String> headMap(String type, String token) {

		// 准备请求头信息
		long time = System.currentTimeMillis();
		timestamp = String.valueOf(time);
		// System.out.println(timestamp);

		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", type);
		headermap.put("Authorization", token);
		headermap.put("timestamp", timestamp); // 时间戳

		return headermap;

	}

	public static String sign(String user, String password) {

		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("username", user);
		map.put("password", password);
		map.put("timestamp", timestamp);
		sign = SignUtils.getSign(map,
				"9d59ce5f7f14a181e74575a8083717f96e963954");
		map.put("sign", sign);

		return sign;

	}
	
	public static String body(String user, String password, String sign) {

		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("username", user);
		map.put("password", password);
		map.put("timestamp", timestamp);
		sign = SignUtils.getSign(map,"9d59ce5f7f14a181e74575a8083717f96e963954");
		map.put("sign", sign);
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> map1 : map.entrySet()) {
			String key = map1.getKey();

			String value = map1.getValue();
			sb.append("&" + key + "=" + value);

		}
		return sb.toString();

	}
		
	//根据传入的接口参数，获得sign
	public static String body1(String str){
		
		SortedMap<String, String> map = new TreeMap<String, String>();
		String [] arr1 = str.split("\\&");
		for (int i = 0; i < arr1.length; i++) {
			String [] arr2 = arr1[i].split("\\=");
			if (arr2.length == 2) {
				map.put(arr2[0], arr2[1]);	
			}	
		}
		map.put("timestamp", timestamp);
		sign = SignUtils.getSign(map,"9d59ce5f7f14a181e74575a8083717f96e963954");
		map.put("sign", sign);
		return sign;
	}
	
	//根据传入的接口参数str，转换为map然后替换key中value值
	public static String body2(String str) throws UnsupportedEncodingException{
		
		try {
			Map<String, Object> map = UrlParam.getUrlParams(str); 
			String nowTime = UrlParam.getNowDate("yyyy-MM-dd HH:mm:ss");
			//urlEncode编码转义当前时间
			String encodeTime = URLEncoder.encode(nowTime, "UTF-8");
			//替换searchSnapshotTime参数的值
			UrlParam.mapReplace(map, "searchSnapshotTime", encodeTime);
			String param = UrlParam.getUrlParamsByMap(map);
			return param;
		} catch (Exception e) {
			String param = str;
			return param;
		}

	}
	
//	post请求参数，直接返回传入参数
	public static String body3(String str){
		return str;
	}
		
//	将par参数进行返回
	public static String st(String a) throws UnsupportedEncodingException{	
		
		String str = a.substring(1);
		String par = body2(str);
		String param = "?"+par;
//		String param= URLEncoder.encode(param1, "UTF-8");
		return param;	
	}
}	
