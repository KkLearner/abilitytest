package com.abilitytest.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.abilitytest.common.Page;
import com.abilitytest.dao.BaseDao;
import com.sun.istack.internal.FinalArrayList;

@SuppressWarnings("all")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	protected SessionFactory sessionFactory;
	@Autowired
	private HttpServletRequest request;
	
	private Class clazz; 

	private String entityName; 
	
	private String tableName;
	
	private HashSet<String> colName;

	public BaseDaoImpl() {
		Class cls = this.getClass();
		ParameterizedType pt = (ParameterizedType) cls.getGenericSuperclass();
		clazz = (Class) pt.getActualTypeArguments()[0];
		entityName = clazz.getSimpleName();
		colName=new HashSet<>();
		if(clazz.isAnnotationPresent(Table.class)){
			Table annotation=(Table)clazz.getAnnotation(Table.class);
			if(annotation!=null)
				tableName=annotation.name();
			Field []fields=clazz.getDeclaredFields();
			for(Field field:fields){
				if (field.isAnnotationPresent(Column.class)) 
	               colName.add(field.getAnnotation(Column.class).name());	            				
			}
			
		}	
	}
	
	@Override
	public void getColName() {
		if(colName!=null&&!colName.isEmpty())
			return;
		try {
			colName=new HashSet<>();
			Session session=sessionFactory.getCurrentSession();
			List list=session.createSQLQuery("SELECT COLUMN_NAME FROM information_schema.columns" 
							+" where table_name='"+tableName+"'").setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
			int size=list.size();
			for(int i=0;i<size;++i)
				colName.add((String)list.get(i));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void add(T t) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.save(t);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean add(Map<String, Object> condition){
		if(condition==null||condition.isEmpty())
			return false;
		Session session = sessionFactory.getCurrentSession();
		try{
			StringBuffer colN=new StringBuffer("");
			StringBuffer colV=new StringBuffer("");
			Set<String> keys=condition.keySet();
			boolean flag=false;
			for(String key:keys){
				if(colName.contains(key)){
					Object value=condition.get(key);
					colN.append(key+",");
					colV.append(":"+key+",");
					flag=true;
				}			
			}
			if(flag){
				colN.setLength(colN.length()-1);
				colV.setLength(colV.length()-1);
			}
			String sql="insert into "+tableName+" ( "+colN.toString()+" )"+" values( "+colV.toString()+" )";
			SQLQuery query=session.createSQLQuery(sql);
			for(String key:keys)
				if(colName.contains(key))
					query.setParameter(key, condition.get(key));
			query.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public void delete(T t) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.delete(t);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}

	@Override
	public void delete(Map<String, Object> condition){
		if(condition==null||condition.isEmpty())
			return;
		Session session=sessionFactory.getCurrentSession();
		try{
			String sql="delete from "+tableName+" where ";
			StringBuffer v=new StringBuffer("");
			Set<String> keys=condition.keySet();
			boolean flag=false;
			for(String key:keys)
				if(colName.contains(key)){
					v.append(key+"=:"+key+" and ");
					flag=true;
				}
			if(flag)
				v.setLength(v.length()-4);
			SQLQuery query=session.createSQLQuery(sql+v.toString());
			for(String key:keys)
				if(colName.contains(key))
					query.setParameter(key, condition.get(key));
			query.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(T t) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.merge(t);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}

	@Override
	public boolean update(Map<String,Object> values,Map<String, Object> condition){
		Session session = sessionFactory.getCurrentSession();
		boolean ok=true;
		try{
			StringBuffer v=new StringBuffer("");
			Set<String> vkeys=values.keySet();
			boolean flag=false;
			for(String key:vkeys)
				if(colName.contains(key)){
					v.append(key+"=:"+key+",");
					flag=true;
				}
			if(flag)
				v.setLength(v.length()-1);
			flag=false;
			StringBuffer c=new StringBuffer("");
			Set<String> ckeys=condition.keySet();
			for(String key:ckeys)
				if(colName.contains(key)){
					c.append(key+"=:"+key+" and ");
					flag=true;
				}
			if(flag)
				c.setLength(c.length()-4);
			String sql="update "+tableName+" set "+v.toString()+" where "+c.toString();
			SQLQuery query=session.createSQLQuery(sql);
			for(String key:vkeys)
				if(colName.contains(key))
					query.setParameter(key, values.get(key));
			for(String key:ckeys)
				if(colName.contains(key))
					query.setParameter(key, condition.get(key));
			query.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			ok=false;
		}
		return ok;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T getByIdWithoutDel(Integer id) {
		Session session = sessionFactory.getCurrentSession();	
		T t = null;
		try{
			t=(T)session.createCriteria(clazz).add(Restrictions.eq("if_del", 0)).add(Restrictions.idEq(id))
					.uniqueResult();
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return t;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T getById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		T t = null;
		try{
			t = (T)session.get(this.clazz, id);
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return t;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T getByLId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		T t=null;
		try{
			t = (T)session.get(this.clazz, id);
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return t;
	}

	@Override
	public Map<String, Object> getByIdRMap(Object id){
		Session session=sessionFactory.getCurrentSession();   
		Map<String, Object> entity=null;
		try {
			entity=(Map<String, Object>)session.createSQLQuery("select * from "+tableName+" where id="+id)
					.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Session session = sessionFactory.getCurrentSession();
		T t = null;
		List l = new ArrayList<T>();
		try{
			Query query = session.createQuery("from " + entityName);
			l = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return l;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByCriterion(Criterion... criterions) {
		Session session=sessionFactory.getCurrentSession();
		T t = null;	
		Criteria criteria=session.createCriteria(clazz);
		for(Criterion criterion : criterions){
			criteria.add(criterion);
		}
		List<T> list=criteria.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByCriterion(Criterion criterion) {
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(clazz);
		cri.add(criterion);
		List<T> list=cri.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page getPageByCriterion(Criterion criterion, int pageNum,
			int pageSize) {
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(clazz);
		if (criterion != null) 
			cri.add(criterion);
		return getPageByCriteria(cri, pageNum, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page getPageByCriteria(Criteria criteria, int pageNum,
			int pageSize) {
		Page page = new Page(pageNum, pageSize,criteria.list().size());
		List<T> pageList = criteria.setFirstResult((int)page.getFirstResult())
				.setMaxResults((int)pageSize).list();
		page.setPageList(pageList);	
		return page;
	}
	
	@Override
	public Page getPageByMap(Map<String, Object>map, int pageNum, int pageSize){
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(clazz);
		Page page = new Page();
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		for(String key : map.keySet()){
			Object value = map.get(key);
			if(!colName.contains(key))
				continue;
			if (value instanceof Integer) {
				int v = ((Integer) value).intValue();
				cri.add(Restrictions.eq(key, v));
			 } else if (value instanceof String) {
			    String s = (String) value;
			    cri.add(Restrictions.eq(key, s));
			 } else if (value instanceof Double) {
			    double d = ((Double) value).doubleValue();
			    cri.add(Restrictions.eq(key, d));
			 } else if (value instanceof Float) {
				 float f = ((Float) value).floatValue();
				 cri.add(Restrictions.eq(key, f));
			 } else if (value instanceof Long) {
				 long l = ((Long) value).longValue();
				 cri.add(Restrictions.eq(key, l));
			 } else if (value instanceof Boolean) {
				 boolean b = ((Boolean) value).booleanValue();
				 cri.add(Restrictions.eq(key, b));
			 } else if (value instanceof Date) {
				 Date d = (Date) value;
				 cri.add(Restrictions.eq(key, d));
			 }
		}
		page.setTotalCount(cri.list().size());
		page.setPageList(cri.setFirstResult((int)page.getFirstResult()).setMaxResults((int)pageSize).list());
		return page;
	}

	@Override
	public int getTotalRecords() {
		Session session = sessionFactory.getCurrentSession();
		int records=0;
		try{
			Query q = session.createQuery("select count(*) from " + entityName);
			records = ((Long) q.uniqueResult()).intValue();
		}	
		catch(Exception e){
			e.printStackTrace();
		}
		return records;
	}
	
	@Override
	public List<T> find(final String hql, Object...values) {
		Session session = sessionFactory.getCurrentSession();
		List<T> list=new ArrayList<>();		
		try{
			Query query = session.createQuery(hql);
			int i = 0;
			for(Object value : values){
				 if (value instanceof Integer) {
					int v = ((Integer) value).intValue();
					query.setInteger(i++, v);
				 } else if (value instanceof String) {
				    String s = (String) value;
				    query.setString(i++, s);
				 } else if (value instanceof Double) {
				    double d = ((Double) value).doubleValue();
				    query.setDouble(i++, d);
				 } else if (value instanceof Float) {
					 float f = ((Float) value).floatValue();
					 query.setFloat(i++, f);
				 } else if (value instanceof Long) {
					 long l = ((Long) value).longValue();
					 query.setLong(i++, l);
				 } else if (value instanceof Boolean) {
					 boolean b = ((Boolean) value).booleanValue();
			    	query.setBoolean(i++, b);
				 } else if (value instanceof Date) {
					 Date d = (Date) value;
					 query.setDate(i++, (Date) value);
				 }
			}
			list = query.list();
		}		
		catch(Exception e){
			e.printStackTrace();
		}	
		return list;
	}
	
	@Override
	public T findByUniqueProperty(String prop, Object value){
		if(value == null)
			return null;
		Session session = sessionFactory.getCurrentSession();	
		T t = null;
		try{
			Query query = session.createQuery("from " + entityName +" bean where bean." + prop +" = :value");
			if (value instanceof Integer) {
				int v = ((Integer) value).intValue();
				query.setInteger("value", v);
			 } else if (value instanceof String) {
			    String s = (String) value;
			    query.setString("value", s);
			 } else if (value instanceof Double) {
			    double d = ((Double) value).doubleValue();
			    query.setDouble("value", d);
			 } else if (value instanceof Float) {
				 float f = ((Float) value).floatValue();
				 query.setFloat("value", f);
			 } else if (value instanceof Long) {
				 long l = ((Long) value).longValue();
				 query.setLong("value", l);
			 } else if (value instanceof Boolean) {
				 boolean b = ((Boolean) value).booleanValue();
		    	query.setBoolean("value", b);
			 } else if (value instanceof Date) {
				 Date d = (Date) value;
				 query.setDate("value", (Date) value);
			 }
			t = (T) query.uniqueResult();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return t;
	}
		
	@Override
	public int countByHQL(String hql){
		Session session = sessionFactory.getCurrentSession();	
		int count=0;
		try{
			count = ((Number)session.createQuery(hql).uniqueResult()).intValue();
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		return count;
	}
	
	@Override
	public void executeHQL(final String hql){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			session.createQuery(hql).executeUpdate();
			tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}	
	}
	
	@Override
	public void insert(final List<T> ts){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			for(int i = 0; i < ts.size(); i ++){
				session.save(ts.get(i));
				if(i % 20 == 0){
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}	
	}
	
	@Override
	public void update(final List<T> ts){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try{
			for(int i = 0; i < ts.size(); i ++){
				session.merge(ts.get(i));
				if(i % 20 == 0){
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}
	}
	
	@Override
	public List<T> findBySQL(String sql){
		Session session = sessionFactory.getCurrentSession();
		List<T> list=new ArrayList<>();
		try{
			list = session.createSQLQuery(sql)
					.addEntity(clazz).list();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<T> findByMap(Map<String, Object>map){
		Session session = sessionFactory.getCurrentSession();
		List<T> list = null;
		Criteria criteria = session.createCriteria(clazz);
		try{
			for(String key : map.keySet()){
				Object value = map.get(key);
				if(!colName.contains(key))
					continue;
				if (value instanceof Integer) {
					int v = Integer.valueOf(String.valueOf(value));
					criteria.add(Restrictions.eq(key, v));
				 } else if (value instanceof String) {
				    String s = String.valueOf(value);
				    criteria.add(Restrictions.like(key, s));
				 } else if (value instanceof Double) {
				    double d = Double.valueOf(String.valueOf(value));
				    criteria.add(Restrictions.eq(key, d));
				 } else if (value instanceof Float) {
					 float f = Float.valueOf(String.valueOf(value));
					 criteria.add(Restrictions.eq(key, f));
				 } else if (value instanceof Long) {
					 long l = Long.valueOf(String.valueOf(value));
					 criteria.add(Restrictions.eq(key, l));
				 } else if (value instanceof Boolean) {
					 boolean b = Boolean.valueOf(String.valueOf(value));
					 criteria.add(Restrictions.eq(key, b));
				 } else if (value instanceof Date) {
					 Date d = (Date)value;
					 criteria.add(Restrictions.eq(key, d));
				 }
			}
			list = criteria.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Object getTopValueByParam(String param){
		Session session = sessionFactory.getCurrentSession();
		Object i = 0;
		Criteria criteria = session.createCriteria(clazz);
		try{
			i = criteria.setProjection(Projections.projectionList().add(Projections.max(param))).uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	@Override
	public Integer findTotalByUniqueProperty(String key,Object value){
		Session session = sessionFactory.getCurrentSession();
		Integer total = 0;
		try{
			Criteria criteria = session.createCriteria(clazz);
			criteria.add(Restrictions.eq(key,value));
			total=criteria.list().size();
		}catch(Exception e){
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public List<Map<String, Object>> getBySQL(String sql){
		Session session = sessionFactory.getCurrentSession();
		List<Map<String, Object>> list=new ArrayList<>();
		try{
			list = session.createSQLQuery(sql)
					.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
					.list();
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		return list;
	}

	@Override
	public List<Map<String, Object>> getInfo(String what,String from,String where,String orderby) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("select "+what+" ");
		buffer.append("from "+(what==null?tableName:what)+" ");
		buffer.append("where "+(where==null?"1=1":where)+" ");
		buffer.append("order by "+(orderby==null?"id desc":orderby)+" ");
		return getBySQL(buffer.toString());
	}
}
