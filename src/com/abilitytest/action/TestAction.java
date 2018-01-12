package com.abilitytest.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class TestAction {

	@ResponseBody
	@RequestMapping(value="/check")
	public Map<String, Object> check(){
		Map<String, Object> result = new HashMap<>();
		result.put("status", 0);
		result.put("msg", "ok");
		return result;
	}
	
	@RequestMapping("/welcome")
	public String welcome(){
		return "interfacetest/welcome";
	}
}
