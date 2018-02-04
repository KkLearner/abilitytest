package com.abilitytest.action;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abilitytest.common.Page;
import com.abilitytest.common.ResultReturn;
import com.abilitytest.entity.Test;
import com.abilitytest.entity.TestPool;
import com.abilitytest.entity.TestResult;
import com.abilitytest.service.TestPoolService;
import com.abilitytest.service.TestResultService;
import com.abilitytest.service.TestService;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@RequestMapping("/test")
public class TestAction {

	@Autowired
	private TestPoolService testPoolService;
	@Autowired
	private TestResultService testResultService;
	
	@RequestMapping(value="/getTestList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTestList(@RequestParam Map<String, Object> map){
		Page page = testPoolService.getTestList(map);
		return ResultReturn.setMap(0, "success", (int)page.getTotalCount(), page.getPageList());
	}
	
	@RequestMapping(value="/getAllResults")
	@ResponseBody
	public Map<String, Object> getAllResults(@RequestParam(value="testid") Integer testid){
		List<Map<String, Object>> results = testResultService.getAllResults(testid);
		return ResultReturn.setMap(0, "success", results.size(), results);
	}
	
	@RequestMapping(value="/submitAnswer",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> submitAnswer(@RequestParam Map<String, Object>map){
		if(testResultService.submitAnswer(map))
			return ResultReturn.setMap(0, "success", 0, null);
		return ResultReturn.setMap(1, "inside error", 0, null);
	}
	
	@RequestMapping(value="/addOrUpdateTest",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOrUpdateTest(@RequestParam Map<String, Object>map){
		Integer id = testPoolService.addOrUpdateTest(map);
		if(id == null)			
			return ResultReturn.setMap(1, "inside error", 0, null);
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("testpool_id", id);
		list.add(rMap);
		return ResultReturn.setMap(0, "success", 1, list);
	}
}
