package com.abilitytest.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abilitytest.entity.Test;
import com.abilitytest.service.TestService;
import com.sun.org.apache.bcel.internal.generic.NEW;

@RequestMapping("/test")
@Controller
public class TestAction {

	@Autowired
	private TestService testService;
	
	@ResponseBody
	@RequestMapping(value="/check")
	public Map<String, Object> check(){
		Map<String, Object> result = new HashMap<>();
		result.put("status", 0);
		result.put("msg", "ok");
		return result;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@RequestParam Map<String, Object> param){
		param.put("uDate", new Date());
		testService.add(param);
		return "interfacetest/welcome";
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public Map<String, Object> get(@RequestParam Map<String, Object> param){
		return testService.getByIdRMap(param.get("id"));
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(@RequestParam Map<String, Object> param){
		Test test = testService.getById(1);
		test.setName("noon");
		testService.update(test);
		return "interfacetest/welcome";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam Map<String, Object> param){
		testService.delete(param);
		return "interfacetest/welcome";
	}
}
