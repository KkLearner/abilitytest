package com.abilitytest.dao;

import com.abilitytest.common.Page;
import com.abilitytest.entity.TestPool;

public interface TestPoolDao extends BaseDao<TestPool> {

	public Page getTestList(String sql,int pageNum,int pageSize,boolean isPage);
}
