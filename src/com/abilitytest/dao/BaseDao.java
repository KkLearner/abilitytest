package com.abilitytest.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import com.abilitytest.common.Page;

public interface BaseDao<T> {

	public void getColName(); 
	
	public abstract void add(T t);

	public abstract boolean add(Map<String, Object> condition);
	
	public abstract void delete(T t);

	public abstract void delete(Map<String, Object> condition);
	
	public abstract void update(T t);
	
	public abstract boolean update(Map<String,Object> values,Map<String, Object> condition);
	
	public T getByIdWithoutDel(Integer id);

	public abstract T getById(Integer id);
	
	public abstract T getByLId(Long id);
	
	public abstract Map<String, Object> getByIdRMap(Object id);

	public abstract List<T> getAll();
	
	public List<T> getByCriterion(Criterion... criterions);
	
	public List<T> getByCriterion(Criterion criterion);
	
	public Page getPageByCriterion(Criterion criterion, int pageNum, int pageSize);
	
	public Page getPageByCriteria(Criteria criteria, int pageNum, int pageSize);
	
	public Page getPageByMap(Map<String, Object>map, int pageNum, int pageSize);
	
	public int getTotalRecords();

	List<T> find(String hql, Object... values);
	
	public T findByUniqueProperty(String prop, Object value);
	
	public int countByHQL(String hql);
	
	public void executeHQL(String hql);
	
	public void insert(List<T> ts);
	
	public void update(List<T> ts);
	
	public List<T> findBySQL(String sql);
	
	public List<Map<String, Object>> getBySQL(String sql);
	
	public List<T> findByMap(Map<String, Object>map);
	
	public Object getTopValueByParam(String param);
	
	public Integer findTotalByUniqueProperty(String key,Object value);
	
	public List<Map<String, Object>> getInfo(String what,String from,String where,String orderby);
}
