package com.abilitytest.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import com.abilitytest.common.Page;
import com.abilitytest.dao.BaseDao;
import com.abilitytest.service.BaseService;


public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseDao<T> dao;
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl(){
	}
	
	@Override
	public void add(T t){
		this.dao.add(t);
	}
	
	@Override
	public boolean add(Map<String, Object>map){
		return this.dao.add(map);
	}
	
	@Override
	public void delete(T t){
		this.dao.delete(t);
	}
	
	@Override
	public void delete(Map<String, Object> condition){
		dao.delete(condition);
	}
	
	@Override
	public void update(T t){
		this.dao.update(t);
	}
	
	@Override
	public boolean update(Map<String,Object> values,Map<String, Object> condition){
		return dao.update(values, condition);
	}
	
	@Override
	public T getByIdWithoutDel(Integer id){
		return dao.getByIdWithoutDel(id);
	}
	
	@Override
	public T getById(Integer id){
		return this.dao.getById(id);
	}

	@Override
	public T getById(Long id){
		return this.dao.getByLId(id);
	}
	
	@Override
	public Map<String, Object> getByIdRMap(Object id){
		return dao.getByIdRMap(id);
	}
	
	@Override
	public List<T> getAll() {
		return this.dao.getAll();
	}

	@Override
	public List<T> getByCriterion(Criterion... criterions) {
		return this.dao.getByCriterion(criterions);
	}

	@Override
	public List<T> getByCriterion(Criterion criterion) {
		return this.dao.getByCriterion(criterion);
	}

	@Override
	public Page getPageByCriterion(Criterion criterion, int pageNum, int pageSize) {
		return this.dao.getPageByCriterion(criterion, pageNum, pageSize);
	}
	
	@Override
	public Page getPageByCriteria(Criteria criteria, int pageNum, int pageSize) {
		return this.dao.getPageByCriteria(criteria, pageNum, pageSize);
	}
	
	@Override
	public Page getPageByMap(Map<String, Object>map, int pageNum, int pageSize){
		return this.dao.getPageByMap(map, pageNum, pageSize);
	}

	@Override
	public int getTotalRecords() {
		return this.dao.getTotalRecords();
	}

	@Override
	public List<T> find(String hql, Object... values) {
		return this.dao.find(hql, values);
	}

	@Override
	public T findByUniqueProperty(String prop, Object value) {
		return this.dao.findByUniqueProperty(prop, value);
	}

	@Override
	public void insert(List<T> ts) {
		this.dao.insert(ts);
	}

	@Override
	public void update(List<T> ts) {
		this.dao.update(ts);
	}

	@Override
	public List<T> findBySQL(String sql) {
		return this.dao.findBySQL(sql);
	}

	@Override
	public int countByHQL(String hql) {
		return dao.countByHQL(hql);
	}
	@Override
	public List<Map<String, Object>> getBySQL(String sql){
		return this.dao.getBySQL(sql);
	}
	@Override
	public List<T> findByMap(Map<String, Object>map){
		return dao.findByMap(map);
	}
	
	@Override
	public Object getTopValueByParam(String param){
		return this.dao.getTopValueByParam(param);
	}

	@Override
	public Integer findTotalByUniqueProperty(String key, Object value) {
		return this.dao.findTotalByUniqueProperty(key, value);
	}

}
