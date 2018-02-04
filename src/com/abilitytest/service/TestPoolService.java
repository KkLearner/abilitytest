package com.abilitytest.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.abilitytest.common.Page;
import com.abilitytest.entity.TestPool;

public interface TestPoolService extends BaseService<TestPool> {

	public Page getTestList(Map<String, Object> map);
	
	public Integer addOrUpdateTest(@RequestParam Map<String, Object>map);
}
