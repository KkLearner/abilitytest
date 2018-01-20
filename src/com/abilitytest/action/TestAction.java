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

import com.abilitytest.common.Page;
import com.abilitytest.common.ResultReturn;
import com.abilitytest.entity.Test;
import com.abilitytest.service.TestPoolService;
import com.abilitytest.service.TestService;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@RequestMapping("/test")
public class TestAction {

	@Autowired
	private TestPoolService testPoolService;
	
	@RequestMapping(value="/getTestList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTestList(@RequestParam Map<String, Object> map){
		Page page = testPoolService.getTestList(map);
		return ResultReturn.setMap(0, "success", (int)page.getTotalCount(), page.getPageList());
	}
}
