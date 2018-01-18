package com.abilitytest.dao.impl;

import org.springframework.stereotype.Repository;

import com.abilitytest.dao.TestPoolDao;
import com.abilitytest.entity.TestPool;

@Repository("testPoolDaoImpl")
public class TestPoolDaoImpl extends BaseDaoImpl<TestPool> implements TestPoolDao {

}
