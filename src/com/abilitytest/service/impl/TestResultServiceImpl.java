package com.abilitytest.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abilitytest.dao.TestResultDao;
import com.abilitytest.entity.TestResult;
import com.abilitytest.service.TestResultService;

@Service("testResultService")
public class TestResultServiceImpl extends BaseServiceImpl<TestResult> implements TestResultService {

	@Autowired
	private TestResultDao testResultDao;
	
	@Override
	public List<Map<String, Object>> getAllResults(Integer testid){
		String sql ="SELECT a.testNumber,b.`name`,b.id_number,a.answer,a.usetime,date_format(a.finishtime,'%Y-%m-%d %T') as finishtime"
				+" from testresult as a,disabledman as b,testpool as c"
				+" WHERE c.id="+testid+" and b.id=c.person_id and a.testpool_id=c.id"
				+" and a.person_id=b.id and a.if_del=0 and b.if_del=0 and c.if_del=0"
				+" ORDER BY a.testNumber asc";
		return testResultDao.getBySQL(sql);
	}
}
