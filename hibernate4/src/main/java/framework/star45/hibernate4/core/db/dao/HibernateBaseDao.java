package framework.star45.hibernate4.core.db.dao;

import static org.hibernate.EntityMode.POJO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.util.Assert;

import framework.star45.hibernate4.core.db.HibernateEntryUtils;
import framework.star45.hibernate4.core.db.Updater;



/**
 * 带范型的dao基类，一张数据表一个</br> 
 * 类名: HibernateBaseDao </br> 
 * 日期: 2013-5-14 下午06:21:15 </br> 
 * @author 徐帆 
 * @version 1.0
 */
public abstract class HibernateBaseDao<T, ID extends Serializable> extends HibernateSimpleDao {

	private Class<T> entityClass;

	/**
	 * 通过反射获取子类确定的泛型类
	 */
	@SuppressWarnings("unchecked")
	public HibernateBaseDao() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 创建实体对象
	 * @return 实体对象
	 */
	public T create() {
		try {
			return entityClass.newInstance();
		} catch (InstantiationException e) {
			log.error("对象初始化失败", e);
		} catch (IllegalAccessException e) {
			log.error("对象初始化失败", e);
		}
		
		return null;
	}
	
	/**
	 * 保存实体对象
	 * @param entity 待操作对象
	 * @return 实体对象，带ID
	 */
	public T save(T entity) {
		Assert.notNull(entity);
		getHibernateTemplate().save(entity);
		//this.getSession().flush();
		return entity;
	}
	
	/**
	 * 保存或更新   实体对象
	 * @param entity 待操作对象
	 * @return 实体对象，带ID
	 */
	public T saveOrUpdate(T entity) {
		Assert.notNull(entity);
		getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}
	
	
	/**
	 * 方法描述：批量保存实体对象
	 * @param entitys 待保存实体对象集合
	 * @return 操作成功数量
	 */
	public int saveAll(java.util.Collection<T> entitys) {
		Assert.notNull(entitys);
		int result = 0;
		for (T entity : entitys) {
			this.getHibernateTemplate().save(entity);
			result++;
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：更新实体对象到数据表，采用Updater的默认更新模式，即除了null外，都更新。exclude和include例外
	 * @param entity
	 * @return
	 */
	public T updateDefault(T entity) {
		return this.updateByUpdater(Updater.create(entity));
	}
	
	/**
	 * 依据设定的更新逻辑更新实体对象到数据表
	 * @param updater 更新对象
	 * @return 实体对象
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public T updateByUpdater(Updater<T> updater) {
		ClassMetadata cm = this.getClassMetadata(updater.getBean().getClass());
		T bean = updater.getBean();
//		T po = (T) this.getSession().load(this.entityClass, cm.getIdentifier(bean, POJO));
		T po = (T) this.getSession().load(this.entityClass, cm.getIdentifier(bean));
		this.updaterCopyToPersistentObject(updater, po);
		return po;
	}
	
	/**
	 * 将更新对象拷贝至实体对象
	 * @param updater 更新对象
	 * @param po 实体对象
	 */
	private void updaterCopyToPersistentObject(Updater<T> updater, T po) {
		Map<String, Object> map = HibernateEntryUtils.describe(updater.getBean());
		Set<Map.Entry<String, Object>> set = map.entrySet();
		for (Map.Entry<String, Object> entry : set) {
			String name = entry.getKey();
			Object value = entry.getValue();
			if (!updater.isUpdate(name, value)) {
				continue;
			}
			try {
				PropertyUtils.setProperty(po, name, value);
			} catch (Exception e) {
				log.warn("更新对象时，拷贝属性异常", e);
			}
		}
	}
	
	/**
	 * 删除实体对象
	 * @param entity 待删除实体对象
	 */
	public void delete(T entity) {
		Assert.notNull(entity);
		this.getHibernateTemplate().delete(entity);
		//this.getSession().flush();
	}
	
	/**
	 * 根据主键ID删除实体对象
	 * @param id 主键
	 */
	public void deleteById(ID id) {
		Assert.notNull(id);
		delete(this.load(id));
	}
	
	/**
	 * 
	 * 方法描述：根据单一属性批量删除实体对象
	 * @param prop 属性名称
	 * @param ids 属性值，符合属性值的进行删除
	 * @return
	 */
	public int deleteByProperty(String prop, Object... ids) {
		if (ids == null || ids.length == 0)
			return 0;
		Assert.hasLength(prop);

		StringBuffer hql = new StringBuffer("delete ");
		hql.append(this.entityClass.getName());
		hql.append(" where ");
		hql.append(prop);
		
		if(ids.length == 1){
			hql.append(" = :ids");
			if (log.isDebugEnabled()) {
				log.debug(hql.toString() + " result:" + StringUtils.join(ids));
			}
			return this.createQuery(hql.toString()).setParameter("ids", ids[0]).executeUpdate();
		}else{
			hql.append(" in (:ids)");
			if (log.isDebugEnabled()) {
				log.debug(hql.toString() + " result:" + StringUtils.join(ids));
			}
			return this.createQuery(hql.toString()).setParameterList("ids", ids).executeUpdate();
		}
	}
	
	/**
	 * 执行hql语句</br>
	 * 日期:2013-5-24 下午02:06:34
	 * @param hql hql语句
	 * @param param 参数列表
	 */
	public void executeHql(String hql,Map<String,Object> param)
	{
		Query query = getSession().createQuery(hql);
		if(param != null)
		{
			for(String key : param.keySet())
			{
				query.setParameter(key, param.get(key));
			}
		}
		query.executeUpdate();
	}
	
	
	/**
	 * 
	 * 根据主键ID获得实体对象
	 * @param id 主键
	 * @return 实体对象
	 */
	public T get(ID id) {
		return get(id, false);
	}
	
	/**
	 * 根据主键ID获得实体对象
	 * @param id 对象ID
	 * @param lock 是否锁定，使用LockOptions.UPGRADE
	 * @return 实体对象
	 */
	@SuppressWarnings("unchecked")
	public T get(ID id, boolean lock) {
		T entity;
		if (lock) {
			entity = (T) getSession().get(entityClass, id, LockOptions.UPGRADE);
		} else {
			entity = (T) getSession().get(entityClass, id);
		}
		return entity;
	}

	/**
	 * 加载实体对象
	 * @param id 主键
	 * @return 实体对象
	 */
	public T load(ID id) {
		Assert.notNull(id);
		return load(id, false);
	}
	
	/**
	 * 加载实体对象
	 * @param id 对象ID
	 * @param lock 是否锁定，使用LockOptions.UPGRADE
	 * @return 实体对象
	 */
	@SuppressWarnings("unchecked")
	public T load(ID id, boolean lock) {
		T entity;
		if (lock) {
			entity = (T) getSession().load(entityClass, id, LockOptions.UPGRADE);
		} else {
			entity = (T) getSession().load(entityClass, id);
		}
		return entity;
	}
	
	/**
	 * 刷新实体对象到数据表
	 * @param entity 实体对象
	 */
	public void refresh(T entity) {
		this.getSession().refresh(entity);
	}
	
	/**
	 * 按属性查找对象列表
	 * @param property 属性名
	 * @param value 属性值
	 * @param orders 排序器列表 可选
	 * @return
	 */
	public List<T> findByProperty(String property, Object value, Order... orders) {
		Assert.hasText(property);
		return findByCriteria(orders, Restrictions.eq(property, value));
	}
	
	/**
	 * 按条件查找对象列表
	 * @param crieterions Hibernate条件器
	 * @param orders 排序器列表 可选
	 * @return 对象列表
	 */
	public List<T> findByCriteria(Criterion[] crieterions, Order... orders) {
		return findByCriteria(crieterions, orders);
	}

	/**
	 * 返回所有实体对象
	 * @param orders 排序器列表 可选
	 * @return 对象列表
	 */
	public List<T> findAll(Order... orders) {
		return this.findByCriteria(orders);
	}
	
	/**
	 * 
	 * 方法描述：按属性查找唯一对象
	 * @param property 属性名
	 * @param value 属性值
	 * @return 单一对象
	 */
	@SuppressWarnings("unchecked")
	public T findUniqueByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return (T) createCriteria(Restrictions.eq(property, value)).uniqueResult();
	}

	/**
	 * 按属性统计记录数
	 * @param property 属性名
	 * @param value 属性值
	 * @return 符合条件的数量
	 */
	public int countByProperty(String property, Object value) {
		Assert.hasText(property);
		Assert.notNull(value);
		return ((Number) (createCriteria(Restrictions.eq(property, value)).setProjection(Projections.rowCount())
				.uniqueResult())).intValue();
	}
	
	/**
	 * 
	 * 方法描述：根据自定义条件查询对象
	 * @param orders 排序器列表，可选
	 * @param criterion 条件列表，可选
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Order[] orders, Criterion... criterion) {
		return createCriteria(orders, criterion).list();
	}

	/**
	 * 
	 * 方法描述：根据Criterion条件创建Criteria
	 * @param criterions
	 * @return
	 */
	protected Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	
	/**
	 * 
	 * 方法描述：根据Criterion条件创建Criteria
	 * @param criterions
	 * @return
	 */
	protected Criteria createCriteria(Order[] orders, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		for (Order o : orders) {
			criteria.addOrder(o);
		}
		return criteria;
	}
	
	protected ClassMetadata getClassMetadata(Class<?> clazz) {
		ClassMetadata cm = this.getClassMetadataIfAny(clazz);
		if (cm == null) {
			throw new RuntimeException("所更新的对象没有映射或不是实体对象");
		}
		return cm;
	}

	protected ClassMetadata getClassMetadataIfAny(Class<?> clazz) {
		return this.getSessionFactory().getClassMetadata(clazz);
	}
}
