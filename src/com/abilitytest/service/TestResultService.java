package com.abilitytest.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.abilitytest.entity.TestResult;

public interface TestResultService extends BaseService<TestResult> {

	public List<Map<String, Object>> getAllResults(Integer testid);
}
