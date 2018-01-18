package com.abilitytest.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultReturn {

	public static Map<String, Object> setMap(int status,String msg,int total,List results) {
		Map<String, Object> result = new HashMap<>();
		result.put("status", status);
		result.put("msg", msg);
		result.put("results", results);
		result.put("total", total);
		return result;
	}
}
