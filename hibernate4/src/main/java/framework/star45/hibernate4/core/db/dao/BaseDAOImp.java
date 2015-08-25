package framework.star45.hibernate4.core.db.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import framework.star45.hibernate4.core.db.page.Page;



/**
 * @author 徐帆
 * 基本DAO类，hibernate增删改查操作的简单封装
 */
public class BaseDAOImp extends HibernateDaoSupport implements BaseDAO{
	/**
	 * 供spring注入SessionFactory使用
	 * @param sessionFactory
	 */
	@SuppressWarnings("unused")
	private void setSessionFactory2(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * 将一个新的对象持久到数据库</br> 
	 * 日期:2013-5-14 下午06:02:53
	 * @param entity 待保存对象
	 * @return 持久化后的对象,有主键值
	 * @see framework.star45.hibernate4.core.db.dao.BaseDAO#create(java.lang.Object)
	 */
    public Serializable create(final Object entity) {
       return getHibernateTemplate().save(entity);
    }

    /**
     * 从数据库删除对象entity,该对象需要是persistent(持久化)状态的</br> 
     * 日期:2013-5-14 下午06:04:05
     * @param entity 待删除对象
     * @see framework.star45.hibernate4.core.db.dao.BaseDAO#delete(java.lang.Object)
     */
    public void delete(final Object entity) {
       getHibernateTemplate().delete(entity);
    }


    /**
     * 更新对象entity,该对象需要是persistent(持久化)状态的</br> 
     * 日期:2013-5-14 下午06:05:18
     * @param entity 待更新对象
     * @see framework.star45.hibernate4.core.db.dao.BaseDAO#update(java.lang.Object)
     */
    public void update(final Object entity) {
       getHibernateTemplate().update(entity);
    }
    
	/**
	 * 保存对象,调用后对象仍然保持原来的状态,详见HIBERNATE的merge方法</br> 
	 * 日期:2013-5-14 下午06:05:46
	 * @param o 实体对象
	 * @see framework.star45.hibernate4.core.db.dao.BaseDAO#merge(java.lang.Object)
	 */
	public void merge(Object o){
		this.getHibernateTemplate().merge(o);
	}
   
    
	/**
	 * HQL分页查询
	 * 根据查询总数的HQL和查询记录的HQL以及设置了指定页码的Page对象，返回指定页的LIST对象
	 * 同时设置PAGE总数。
	 * 
	 * @param selectCount 查询总数hql语句
	 * @param select 查询列表hql语句
	 * @param values 参数列表
	 * @param page 设置了指定页码的Page对象
	 * @return 对象列表
	 * @see framework.star45.hibernate4.core.db.dao.BaseDAO#getListByHQL(java.lang.String, java.lang.String, java.lang.Object[], framework.star45.hibernate4.core.db.page.Page)
	 */
    public List<?> getListByHQL(final String selectCount, final String select, final Object[] values, final Page page) {
    	
    	if(selectCount != null && !selectCount.equals("")){
    		if(page != null){
	        	Long count = (Long)getObjectByHQL(selectCount, values);
	        	page.setTotal(count.intValue());
	        	if(page.isEmpty())
		            return Collections.EMPTY_LIST;
    		}
	        
    	}
        return getListByHQL(select, values, page);
    }

    /**
     * HQL分页查询</br>
     * 日期:2013-5-14 下午06:06:44
     * @param select 查询列表hql语句
     * @param values 参数列表
     * @param page 设置了指定页码的Page对象
     * @return 对象列表
     */
    private List<?> getListByHQL(final String select, final Object[] values, final Page page) {
    	// select:
    	HibernateCallback selectCallback = new HibernateCallback() {
    		public Object doInHibernate(Session session) {
    			Query query = session.createQuery(select);
    			if(values!=null) {
    				for(int i=0; i<values.length; i++)
    					query.setParameter(i, values[i]);
    			}
    			if(page != null){
    				return query.setFirstResult(page.getFirstResult())
    				.setMaxResults(page.getRp())
    				.list();
    			}else
    				return query.list();
    		}
    	};
    	List<?> list = (List<?>) getHibernateTemplate().executeFind(selectCallback);
    	
    	return list;
    }

 
    /**
	 * HQL不分页查询
	 * 返回全部列表，
     * 日期:2013-5-14 下午06:11:18
	 * @param select 查询列表hql语句
	 * @param values 参数列表
	 * @return 对象列表
     * @see framework.star45.hibernate4.core.db.dao.BaseDAO#getListByHQL(java.lang.String, java.lang.Object[])
     */
    public List<?> getListByHQL(final String select, final Object[] values) {
    	return this.getListByHQL(select, values, null);
    }

 
    /**
     * 根据HQL获得一个对象</br> 
     * 日期:2013-5-14 下午06:12:54
     * @param select hql查询语句
     * @param values 参数列表
     * @return 单个对象
     * @see framework.star45.hibernate4.core.db.dao.BaseDAO#getObjectByHQL(java.lang.String, java.lang.Object[])
     */
    public Object getObjectByHQL(final String select, final Object[] values) {
        HibernateCallback selectCallback = new HibernateCallback() {
            public Object doInHibernate(Session session) {
                Query query = session.createQuery(select);
                if(values!=null) {
                    for(int i=0; i<values.length; i++)
                        query.setParameter(i, values[i]);
                }
                return query.uniqueResult();
            }
        };
        return getHibernateTemplate().execute(selectCallback);
    }


   /**
    * 得到一条记录，主要用于查询总数.也可用于查询单个对象</br>
    * ,如果返回多个对象,抛出异常</br> 
    * 日期:2013-5-14 下午06:14:11
    * @param dc Hibernate Criteria
    * @return 单值
    * @see framework.star45.hibernate4.core.db.dao.BaseDAO#getObjectByCriteria(org.hibernate.criterion.DetachedCriteria)
    */
    public Object getObjectByCriteria(final DetachedCriteria dc) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) {
            	Criteria criteria = dc.getExecutableCriteria(session); 
            	//criteria.setProjection(Projections.rowCount());
                return criteria.uniqueResult();
            }
        };
        return getHibernateTemplate().execute(callback);
    }

   /**
	* DetachedCriteria列表查询
	* 之所以不把排序字段放在DetachedCriteria内传入，是为了兼容所有数据库，有的数据库在查总数据时如果有排序字段会出错
	* @param dc Hibernate Criteria
	* @param orderBy 排序字段 "fa desc,fb,fc asc" 
	* @param page 为空时不分页，非空时分页
	* @return 对象列表
	* @see framework.star45.hibernate4.core.db.dao.BaseDAO#getListByCriteria(org.hibernate.criterion.DetachedCriteria, java.lang.String, framework.star45.hibernate4.core.db.page.Page)
	*/
    public List<?> getListByCriteria(final DetachedCriteria dc,final String orderBy, final Page page) {
    	if(page !=null){
    		dc.setProjection(Projections.rowCount());
    		int i = Integer.parseInt(this.getObjectByCriteria(dc).toString());
	    	page.setTotal(i);
	        if(page.isEmpty())
	            return Collections.EMPTY_LIST;
    	}
    	//处理排序字段
    	if(orderBy != null){
    		String[] aOrderBys = orderBy.trim().split(",");
    		for(int i = 0; i< aOrderBys.length; i++){
    			String[] aOrderBy = aOrderBys[i].trim().split(" ");
    			//字段为空则不处理
    			if(StringUtils.isBlank(aOrderBy[0]))
    				continue;
    			
    			if(aOrderBy.length>1){//有正反序参数
    				if(aOrderBy[1].equalsIgnoreCase("desc")){
    					dc.addOrder(Property.forName(aOrderBy[0]).desc());
    				}else{
    					dc.addOrder(Property.forName(aOrderBy[0]).asc());
    				}
    			}else{//无正反序参数
    				dc.addOrder(Property.forName(aOrderBy[0]).asc());
    			}
    		}
    	}
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) {
            	Criteria criteria = dc.getExecutableCriteria(session);
            	criteria.setProjection(null);
            	if(page !=null){
            	criteria.setFirstResult(page.getFirstResult())
	            	.setMaxResults(page.getRp())
	            	.setFetchSize(page.getRp());
            	}
                return criteria.list();
            }
        };
        List<?> list = (List<?>)getHibernateTemplate().execute(callback); 
        return list;
    }


    /**
     * SQL分页查询
     * 根据查询总数的SQL和查询记录的SQL,设置了页码的page,返回查询列表，同时设置PAGE总数。
     * @param selectCount 查询总数hql语句，如  "select count(*) from ..."
     * @param select 查询列表hql语句，如  "select * from ..."
     * @param 参数列表
     * @param page 分页控制器 
     * @param c 非空时每条结果记录封装成对应的对象，空时为对象数组
     * @return 对象列表
     * @see framework.star45.hibernate4.core.db.dao.BaseDAO#getListBySQL(java.lang.String, java.lang.String, java.lang.Object[], framework.star45.hibernate4.core.db.page.Page, java.lang.Class)
     */
    public List<?> getListBySQL(final String selectCount, final String select, Object[] values, final Page page,final Class<?> c) {
    	if(selectCount != null && !selectCount.equals("")){
	        String count = getObjectBySQL(selectCount,values).toString();
	        page.setTotal(Integer.parseInt(count));
	        if(page.isEmpty())
	            return Collections.EMPTY_LIST;
    	}
        return getListBySQL(select, values, page,c);
    }
   

    /**
     * SQL不分页查询
     * @param select 查询列表hql语句，如  "select * from ..."
     * @param values 参数列表
     * @param c 非空时每条结果记录封装成对应的对象，空时为对象数组
     * @return 对象列表
     * @see framework.star45.hibernate4.core.db.dao.BaseDAO#getListBySQL(java.lang.String, java.lang.Object[], java.lang.Class)
     */
    public List<?> getListBySQL(final String select,final Object[] values,final Class<?> c) {
        return getListBySQL(select, values, null,c);
        
    }


    private List<?> getListBySQL(final String select,final Object[] values ,final Page page, final Class<?> c) {
    	// select:
    	HibernateCallback selectCallback = new HibernateCallback() {
    		public Object doInHibernate(Session session) {
    			Query query ;
    			if(c == null)
    				query = session.createSQLQuery(select);
    			else{
    				Map<?, ?> map = session.getSessionFactory().getAllClassMetadata();
    				if(map.containsKey(c.getName())){
    					query = session.createSQLQuery(select).addEntity(c);
    				}else{
    					query = session.createSQLQuery(select).setResultTransformer(Transformers.aliasToBean(c));
    				}
    			}
    			if(values!=null) {
    				for(int i=0; i<values.length; i++)
    					query.setParameter(i, values[i]);
    			}
    			if(page != null){
    				return query.setFirstResult(page.getFirstResult())
    				.setMaxResults(page.getRp())
    				.list();
    			}else
    				return query.list();
    		}
    	};
    	return (List<?>) getHibernateTemplate().executeFind(selectCallback);
    }
  

  
    /**
	 * 得到一条记录，主要用于查询总数.也可用于查询单个对象</br>
	 * ,如果返回多个对象,抛出运行时异常</br>
	 * @param select 查询列表hql语句
	 * @param values 参数列表
	 * @return 单个对象
     * @see framework.star45.hibernate4.core.db.dao.BaseDAO#getObjectBySQL(java.lang.String, java.lang.Object[])
     */
    public Object getObjectBySQL(final String select,final Object[] values) {
        HibernateCallback selectCallback = new HibernateCallback() {
            public Object doInHibernate(Session session) {
                Query query = session.createSQLQuery(select);
                if(values!=null) {
    				for(int i=0; i<values.length; i++)
    					query.setParameter(i, values[i]);
    			}
                return query.uniqueResult();
            }
        };
        return getHibernateTemplate().execute(selectCallback);
    }
    

 
    /**
	 * 根据ID查找对象
	 * @param c 返回对象的Class
	 * @param id 对象标识
	 * @return 从数据库找到的对象
     * @see framework.star45.hibernate4.core.db.dao.BaseDAO#getObjectById(java.lang.Class, java.io.Serializable)
     */
    public Object getObjectById(final Class<?> c,final Serializable id) {
       return getHibernateTemplate().get(c, id);
    }
    
 
    /**
	 * 批量更新，如果使用HQL会出错（更新的是子类时）
     * @param sqlUpdate sql语句
     * @param values 参数列表
     * @return  单值
     * @see framework.star45.hibernate4.core.db.dao.BaseDAO#updateBySql(java.lang.String, java.lang.Object[])
     */
    public Object updateBySql(final String sqlUpdate,final Object[] values) {
        HibernateCallback selectCallback = new HibernateCallback() {
            public Object doInHibernate(Session session) {
                Query query = session.createSQLQuery(sqlUpdate);
                if(values!=null) {
    				for(int i=0; i<values.length; i++)
    					query.setParameter(i, values[i]);
    			}
                return new Integer(query.executeUpdate());
            }
        };
        return getHibernateTemplate().execute(selectCallback);
    }

    /**
	 * 获得符合条件查询的前几条记录
	 * @param select 查询列表hql语句，如  "select * from ..."
	 * @param values 参数列表
	 * @param topnum 要获得的记录数
	 * @return 对象列表
     * @see framework.star45.hibernate4.core.db.dao.BaseDAO#getTopListByHQL(java.lang.String, java.lang.Object[], int)
     */
    public List<?> getTopListByHQL(final String select, final Object[] values, final int topnum) {
    	// select:
    	HibernateCallback selectCallback = new HibernateCallback() {
    		public Object doInHibernate(Session session) {
    			Query query = session.createQuery(select);
    			if(values!=null) {
    				for(int i=0; i<values.length; i++)
    					query.setParameter(i, values[i]);
    			}
    			if(topnum != -1){
					return query.setFirstResult(0)
					.setMaxResults(topnum)
					.list();
    			}else{
    				return query.list();
    			}
    		}
    	};
    	List<?> list = (List<?>) getHibernateTemplate().executeFind(selectCallback);
    	
    	return list;
    }

	

}

