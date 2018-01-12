package com.abilitytest.service.impl;

import org.springframework.stereotype.Service;

import com.abilitytest.entity.Test;
import com.abilitytest.service.TestService;

@Service("testService")
public class TestServiceImpl extends BaseServiceImpl<Test> implements TestService {

}
