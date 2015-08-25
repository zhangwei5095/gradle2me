
package framework.star45.hibernate4.core.db.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import framework.star45.hibernate4.core.db.page.Page;


public interface BaseDAO {
    /**
     * 将一个新的对象持久到数据库
     * @param entity 待保存对象
     * @return 持久化后的对象,有主键值
     */
    public Object create(Object entity);
    
    /**
     * 从数据库删除对象entity,该对象需要是persistent(持久化)状态的
     * @param entity 待删除对象
     */
    public void delete(Object entity) ;
    /**
     * 更新对象entity,该对象需要是persistent(持久化)状态的
     * @param entity 待更新对象
     */
    public void update(Object entity) ;
   
    /**
     * 根据HQL获得一个对象
     * @param select hql查询语句
     * @param values 参数列表
     * @return 单个对象
     */
    public Object getObjectByHQL(final String select, final Object[] values) ;
	/**
	 * 根据ID查找对象
	 * @param c 返回对象的Class
	 * @param id 对象标识
	 * @return 从数据库找到的对象
	 * 
	 */
	public Object getObjectById(final Class<?> c, final Serializable id);
	
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
	 */
	public List<?> getListByHQL(final String selectCount, final String select
			, final Object[] values, final Page page);
	/**
	 * 
	 * HQL不分页查询
	 * 返回全部列表，
	 * @param select 查询列表hql语句
	 * @param values 参数列表
	 * @return 对象列表
	 */
	public List<?> getListByHQL(final String select, final Object[] values);
	
	/**
	 * 得到一条记录，主要用于查询总数.也可用于查询单个对象,如果返回多个对象,抛出异常
	 * @param dc Hibernate Criteria
	 * @return 单值
	 */
	public Object getObjectByCriteria(final DetachedCriteria dc);
	/**
	 * DetachedCriteria列表查询
	 * 之所以不把排序字段放在DetachedCriteria内传入，是为了兼容所有数据库，有的数据库在查总数据时如果有排序字段会出错
	 * @param dc Hibernate Criteria
	 * @param orderBy 排序字段 "fa desc,fb,fc asc" 
	 * @param page 为空时不分页，非空时分页
	 * @return 对象列表
	 * 
	 */
	public List<?> getListByCriteria(final DetachedCriteria dc, final String orderBy, final Page page);
	
	
	/**
	 * 得到一条记录，主要用于查询总数.也可用于查询单个对象,如果返回多个对象,抛出异常
	 * 
	 * @param select 查询列表hql语句
	 * @param values 参数列表
	 * @return 单个对象
	 */
	public Object getObjectBySQL(final String select, final Object[] values);
	
	/**
     * SQL分页查询
     * 根据查询总数的SQL和查询记录的SQL,设置了页码的page,返回查询列表，同时设置PAGE总数。
     * @param selectCount 查询总数hql语句，如  "select count(*) from ..."
     * @param select 查询列表hql语句，如  "select * from ..."
     * @param 参数列表
     * @param page 分页控制器 
     * @param c 非空时每条结果记录封装成对应的对象，空时为对象数组
     * @return 对象列表
     */
	 public List<?> getListBySQL(final String selectCount, final String select, Object[] values, final Page page,final Class<?> c);

    /**
     * SQL不分页查询
     * @param select 查询列表hql语句，如  "select * from ..."
     * @param values 参数列表
     * @param c 非空时每条结果记录封装成对应的对象，空时为对象数组
     * @return 对象列表
     */
	 public List<?> getListBySQL(final String select,final Object[] values,final Class<?> c);

	/**
	 * 批量更新，如果使用HQL会出错（更新的是子类时）
     * @param sqlUpdate sql语句
     * @param values 参数列表
     * @return  单值
     */
    public Object updateBySql(final String sqlUpdate,final Object[] values);
	/**
	 * 保存对象,调用后对象仍然保持原来的状态,详见HIBERNATE的merge方法
	 * @param o 实体对象
	 */
	public void merge(Object o);
	/**
	 * 获得符合条件查询的前几条记录
	 * @param select 查询列表hql语句，如  "select * from ..."
	 * @param values 参数列表
	 * @param topnum 要获得的记录数
	 * @return 对象列表
	 */
	public List<?> getTopListByHQL(final String select, final Object[] values, final int topnum);

	
	
	
}
