package com.abilitytest.dao.impl;

import org.springframework.stereotype.Repository;

import com.abilitytest.dao.PersonsDao;
import com.abilitytest.entity.Persons;

@Repository("personsDaoImpl")
public class PersonsDaoImpl extends BaseDaoImpl<Persons> implements PersonsDao {

}
