package com.abilitytest.service;

import java.util.Map;

import com.abilitytest.common.Page;
import com.abilitytest.entity.TestPool;

public interface TestPoolService extends BaseService<TestPool> {

	public Page getTestList(Map<String, Object> map);
}
