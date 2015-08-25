package framework.star45.hibernate4.core.db;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

/**
 * 注解形式SessionFactoryBean扩展</br> 
 * 类名: SBRAnnotationSessionFactoryBean </br> 
 * 日期: 2013-5-14 下午05:34:19 </br> 
 * @author 徐帆 
 * @version 1.0
 */
public class SBRAnnotationSessionFactoryBean extends AnnotationSessionFactoryBean {
	private SessionFactoryProxy sessionFactoryProxy = new SessionFactoryProxy();

	@Override
	protected SessionFactory buildSessionFactory() throws Exception {
		SessionFactory sessionFactory = super.buildSessionFactory();
		this.sessionFactoryProxy.setSessionFactory(sessionFactory);
		this.sessionFactoryProxy.setConfiguration(this.getConfiguration());
		return sessionFactory;
	}

	/**
	 * 此方法在此类被spring容器初始化完后调用，其中设置了数据源和数据库方言</br> 
	 * 日期:2013-5-14 下午05:34:38
	 * @throws Exception 数据库连接或数据库服务器异常会抛出此异常
	 * @see org.springframework.orm.hibernate3.AbstractSessionFactoryBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		
		this.sessionFactoryProxy.setDataSource(this.getDataSource());
		this.sessionFactoryProxy.setHibernateDialect((String) this.getHibernateProperties().getProperty("hibernate.dialect"));
	}

	/**
	 * 供外界获取Hibernate SessionFactory</br> 
	 * 日期:2013-5-14 下午05:36:15
	 * @return Hibernate SessionFactory
	 * @see org.springframework.orm.hibernate3.AbstractSessionFactoryBean#getObject()
	 */
	@Override
	public SessionFactory getObject() {
		return this.sessionFactoryProxy;
	}
}
