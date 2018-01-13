package com.abilitytest.dao.impl;

import org.springframework.stereotype.Repository;

import com.abilitytest.dao.AnswersDao;
import com.abilitytest.entity.Answers;

@Repository("answersDaoImpl")
public class AnswersDaoImpl extends BaseDaoImpl<Answers> implements AnswersDao {

}
