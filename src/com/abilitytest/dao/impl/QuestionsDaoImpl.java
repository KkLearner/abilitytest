package com.abilitytest.dao.impl;

import org.springframework.stereotype.Repository;

import com.abilitytest.dao.QuestionsDao;
import com.abilitytest.dao.impl.BaseDaoImpl;
import com.abilitytest.entity.Questions;

@Repository("questionsDaoImpl")
public class QuestionsDaoImpl extends BaseDaoImpl<Questions> implements QuestionsDao {

}
