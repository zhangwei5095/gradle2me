package framework.star45.hibernate4.core.db;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.sql.DataSource;

import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.StatelessSessionBuilder;
import org.hibernate.TypeHelper;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

/**
 * 自定义SessionFactory代理,外界委托它来调用真实的SessionFactory </br> 
 * 方法注释参见Hibernate SessionFactory描述 </br> 
 * 类名: SessionFactoryProxy </br> 
 * 日期: 2013-5-14 下午05:39:20 </br> 
 * @author 徐帆 
 * @version 1.0
 */
public class SessionFactoryProxy implements SessionFactory {

	private static final long serialVersionUID = 1L;
	
	private SessionFactory sessionFactory = null;
	
	private DataSource dataSource = null;
	
	private String hibernateDialect = null;
	
	private Configuration configuration = null;

	@Override
	public void close() throws HibernateException {
		this.getSessionFactory().close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void evict(Class clazz) throws HibernateException {
		this.getSessionFactory().evict(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void evict(Class clazz, Serializable serializable) throws HibernateException {
		this.getSessionFactory().evict(clazz, serializable);
	}

	@Override
	public void evictCollection(String s) throws HibernateException {
		this.getSessionFactory().evictCollection(s);

	}

	@Override
	public void evictCollection(String s, Serializable serializable) throws HibernateException {
		this.getSessionFactory().evictCollection(s, serializable);
	}

	@Override
	public void evictEntity(String s) throws HibernateException {
		this.getSessionFactory().evictEntity(s);
	}

	@Override
	public void evictEntity(String s, Serializable serializable) throws HibernateException {
		this.getSessionFactory().evictEntity(s, serializable);
	}

	@Override
	public void evictQueries() throws HibernateException {
		this.getSessionFactory().evictQueries();
	}

	@Override
	public void evictQueries(String s) throws HibernateException {
		this.getSessionFactory().evictQueries(s);
	}

	@Override
	public Map<String, ClassMetadata> getAllClassMetadata() throws HibernateException {
		return this.getSessionFactory().getAllClassMetadata();
	}

	@Override
	public Map<?, ?> getAllCollectionMetadata() throws HibernateException {
		return this.getSessionFactory().getAllCollectionMetadata();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ClassMetadata getClassMetadata(Class clazz) throws HibernateException {
		return this.getSessionFactory().getClassMetadata(clazz);
	}

	@Override
	public ClassMetadata getClassMetadata(String clazz) throws HibernateException {
		return this.getSessionFactory().getClassMetadata(clazz);
	}

	@Override
	public CollectionMetadata getCollectionMetadata(String s) throws HibernateException {
		return this.getSessionFactory().getCollectionMetadata(s);
	}

	@Override
	public Session getCurrentSession() throws HibernateException {
		return this.getSessionFactory().getCurrentSession();
	}

	@Override
	public Set<?> getDefinedFilterNames() {
		return this.getSessionFactory().getDefinedFilterNames();
	}

	@Override
	public FilterDefinition getFilterDefinition(String filterDef) throws HibernateException {
		return this.getSessionFactory().getFilterDefinition(filterDef);
	}

	@Override
	public Statistics getStatistics() {
		return this.getSessionFactory().getStatistics();
	}

	@Override
	public boolean isClosed() {
		return this.getSessionFactory().isClosed();
	}

	@Override
	public Session openSession() throws HibernateException {
		return this.getSessionFactory().openSession();
	}



	@Override
	public StatelessSession openStatelessSession() {
		return this.getSessionFactory().openStatelessSession();
	}

	@Override
	public StatelessSession openStatelessSession(Connection connection) {
		return this.getSessionFactory().openStatelessSession(connection);
	}

	@Override
	public Reference getReference() throws NamingException {
		return this.getSessionFactory().getReference();
	}

	public SessionFactory getSessionFactory() {
		if (this.sessionFactory == null)
			throw new RuntimeException("系统尚未加载完成，sesion 尚未创建!");
		return this.sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getHibernateDialect() {
		return hibernateDialect;
	}

	public void setHibernateDialect(String hibernateDialect) {
		this.hibernateDialect = hibernateDialect;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	@Override
	public boolean containsFetchProfileDefinition(String s) {
		return getSessionFactory().containsFetchProfileDefinition(s);
	}

	@Override
	public Cache getCache() {
		return getSessionFactory().getCache();
	}

	@Override
	public TypeHelper getTypeHelper() {
		return getSessionFactory().getTypeHelper();
	}

	@Override
	public SessionFactoryOptions getSessionFactoryOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionBuilder withOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatelessSessionBuilder withStatelessOptions() {
		// TODO Auto-generated method stub
		return null;
	}
}
