package com.abilitytest.dao.impl;

import org.springframework.stereotype.Repository;

import com.abilitytest.dao.TestResultDao;
import com.abilitytest.entity.TestResult;

@Repository("testResultDaoImpl")
public class TestResultDaoImpl extends BaseDaoImpl<TestResult> implements TestResultDao {

}
