package com.abilitytest.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.abilitytest.common.Page;
import com.abilitytest.dao.TestPoolDao;
import com.abilitytest.entity.TestPool;

@Repository("testPoolDaoImpl")
public class TestPoolDaoImpl extends BaseDaoImpl<TestPool> implements TestPoolDao {

	@Override
	public Page getTestList(String sql,int pageNum,int pageSize){
		Session session = sessionFactory.getCurrentSession();
		Page page = new Page(pageNum, pageSize, 0);
		Object cObject = session.createSQLQuery("select count(a.id) "+sql).uniqueResult();
		if(cObject!=null)
			page.setTotalCount(((Number)cObject).intValue());
		Query query = session.createSQLQuery("select a.id as personid,b.id as testid,a.`name`,a.id_number,b.test,date_format(b.modifytime,'%Y-%m-%d %T') as modifytime"
				+sql+" order by a.id_number,b.modifytime desc")
				.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);		
		page.setPageList(query.setFirstResult((int)page.getFirstResult())
				.setMaxResults(pageSize).list());
		return page;
	}
}
