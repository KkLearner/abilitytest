package com.abilitytest.service.impl;

import org.springframework.stereotype.Service;

import com.abilitytest.entity.Persons;
import com.abilitytest.service.PersonsService;

@Service("personsService")
public class PersonsServiceImpl extends BaseServiceImpl<Persons> implements PersonsService {

}
