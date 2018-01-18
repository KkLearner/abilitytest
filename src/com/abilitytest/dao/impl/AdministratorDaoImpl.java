package com.abilitytest.dao.impl;

import org.springframework.stereotype.Repository;

import com.abilitytest.dao.AdministratorDao;
import com.abilitytest.entity.Administrator;

@Repository("administratorDaoImpl")
public class AdministratorDaoImpl extends BaseDaoImpl<Administrator> implements AdministratorDao {

}
