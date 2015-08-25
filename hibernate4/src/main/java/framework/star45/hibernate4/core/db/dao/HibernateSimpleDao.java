package framework.star45.hibernate4.core.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.util.Assert;

import framework.star45.hibernate4.core.db.Finder;
import framework.star45.hibernate4.core.db.page.Pagination;


/**
 * 
 * Project Name：common
 * ClassName：SBRAnnotationSessionFactoryBean
 * Description：基本hibernate dao，提供基于hql查询数据的方法
 * @author: 徐帆 
 * @date: 2013-4-17 下午04:49:46
 * note:
 *
 */
public abstract class HibernateSimpleDao extends JdbcDao {

	private HibernateTemplate hibernateTemplate;

	/**
	 * spring 容器加载完毕该对象，并设置属性后调用,用于设置HibernateTemplate对象</br> 
	 * 日期:2013-5-14 下午06:29:17
	 * @throws Exception spring运行时异常
	 * @see framework.star45.hibernate4.core.db.dao.JdbcDao#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		
		hibernateTemplate = new HibernateTemplate(getSessionFactory());
	}

	/**
	 * 获取hibernate session</br>
	 * 日期:2013-5-14 下午06:30:42
	 * @return hibernate session对象
	 */
	public Session getSession() {
		return SessionFactoryUtils.getSession(getSessionFactory(), true);
	}
	
	protected HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	/**
	 * 通过HQL查询对象列表
	 * @param hql hql语句
	 * @param values 参数列表
	 * @return 对象列表
	 */
	@SuppressWarnings("unchecked")
	public List find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 通过HQL查询唯一对象
	 * @param hql hql语句
	 * @param values 参数列表
	 * @return 单一对象
	 */
	public Object findUnique(String hql, Object... values) {
		return createQuery(hql, values).uniqueResult();
	}
	
	/**
	 * 通过Finder获得分页数据
	 * @param finder 条件组装器
	 * @param pageNo 当前页码
	 * @param pageSize 每页条数
	 * @return 分页信息，其中包含有数据列表
	 */
	@SuppressWarnings("unchecked")
	public Pagination queryForPage(Finder finder, Integer pageNo, Integer pageSize) {
		int totalCount = countQueryResult(finder);
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		Query query = getSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(p.getFirstResult());
		query.setMaxResults(p.getPageSize());
		if (finder.isCacheable()) {
			query.setCacheable(true);
		}
		List list = query.list();
		p.setList(list);
		return p;
	}

	/**
	 * 通过Finder获得列表数据
	 * @param finder 条件组装器
	 * @return 对象列表
	 */
	@SuppressWarnings("unchecked")
	public List find(Finder finder) {
		Query query = finder.createQuery(getSession());
		List list = query.list();
		return list;
	}
	
	/**
	 * 通过Finder获得记录总数
	 * @param finder 条件组装器
	 * @return 记录数
	 */
	public int countQueryResult(Finder finder) {
		String hql = finder.getRowCountHql();
		Query query = getSession().createQuery(hql);
		finder.setParamsToQuery(query);
		
		if (finder.isCacheable()) {
			query.setCacheable(true);
		}

		return ((Number) query.iterate().next()).intValue();
	}
	
	/**
	 * 根据查询函数与参数列表创建Query对象
	 * @param queryString hql语句
	 * @param values 参数列表
	 * @return hibernate Query对象
	 */
	protected Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}
}
