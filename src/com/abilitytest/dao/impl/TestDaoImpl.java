package com.abilitytest.dao.impl;

import org.springframework.stereotype.Repository;

import com.abilitytest.dao.TestDao;
import com.abilitytest.entity.Test;

@Repository("testDaoImpl")
public class TestDaoImpl extends BaseDaoImpl<Test> implements TestDao {

}
