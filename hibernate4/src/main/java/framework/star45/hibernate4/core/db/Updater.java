package framework.star45.hibernate4.core.db;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;


/**
 * 更新对象类 </br> 
 * 提供四种更新模式：MAX, MIN, MIDDLE, MULTI
 * <ul>
 * <li>MIDDLE：默认模式�?除了null外，都更新�?exclude和include例外�?/li>
 * <li>MAX：最大化更新模式。所有字段都更新（包括null）�?exclude例外�?/li>
 * <li>MIN：最小化更新模式。所有字段都不更新�?include例外�?/li>
 * </ul>
 * 类名: Updater </br> 
 * 日期: 2013-5-14 下午05:41:24 </br> 
 * @author 徐帆 
 * @version 1.0
 */
public class Updater<E> {
	
	protected Updater(E bean) {
		this.bean = bean;
	}


	/**
	 * 创建更新对象</br>
	 * 日期:2013-5-14 下午05:44:14
	 * @param <T> 要更新的对象类型
	 * @param bean 要更新的对象
	 * @return 更新对象
	 */
	public static final <T> Updater<T> create(T bean) {
		return new Updater<T>(bean);
	}
	

	/**
	 * 创建更新对象</br>
	 * 日期:2013-5-14 下午05:47:54
	 * @param <T> 要更新的对象类型
	 * @param bean 要更新的对象
	 * @param mode 更新模式,参加类说明
	 * @return 更新对象
	 */
	public static final <T> Updater<T> create(T bean, UpdateMode mode) {
		Updater<T> updater = new Updater<T>(bean);
		updater.setUpdateMode(mode);
		return updater;
	}


	/**
	 * 设置更新模式</br>
	 * 日期:2013-5-14 下午05:48:36
	 * @param mode 更新模式,参加类说明
	 * @return 更新对象
	 */
	public Updater<E> setUpdateMode(UpdateMode mode) {
		this.mode = mode;
		return this;
	}


	/**
	 * 设置更新操作包含的属性</br>
	 * 日期:2013-5-14 下午05:49:01
	 * @param property 属性名称
	 * @return 更新对象
	 */
	public Updater<E> include(String property) {
		includeProperties.add(property);
		return this;
	}


	/**
	 * 设置更新操作排除的属性</br>
	 * 日期:2013-5-14 下午05:49:26
	 * @param property 属性名称
	 * @return 更新对象
	 */
	public Updater<E> exclude(String property) {
		excludeProperties.add(property);
		return this;
	}
	

	/**
	 * 某一字段是否更新</br>
	 * 日期:2013-5-14 下午05:51:04
	 * @param name 字段名
	 * @param value 字段值。用于检查是否为NULL
	 * @return true 更新, false 不更新
	 */
	public boolean isUpdate(String name, Object value) {
		if (this.mode == UpdateMode.MAX) {
			return !excludeProperties.contains(name);
		} else if (this.mode == UpdateMode.MIN) {
			return includeProperties.contains(name);
		} else if (this.mode == UpdateMode.MIDDLE) {
			if (value != null) {
				return !excludeProperties.contains(name);
			} else {
				return includeProperties.contains(name);
			}
		} else {
			// never reach
		}
		return true;
	}

	private E bean;

	private Set<String> includeProperties = new HashSet<String>();

	private Set<String> excludeProperties = new HashSet<String>();
	
	private UpdateMode mode = UpdateMode.MIDDLE;

	protected final transient Logger logger = Logger.getLogger(this.getClass());


	public static enum UpdateMode {
		MAX, MIN, MIDDLE 
	}

	/**
	 * 获取待更新实体对象</br>
	 * 日期:2013-5-14 下午05:51:53
	 * @return 待更新实体对象
	 */
	public E getBean() {
		return bean;
	}

	/**
	 * 获取不更新的属性集合</br>
	 * 日期:2013-5-14 下午05:52:18
	 * @return 不更新的属性集合
	 */
	public Set<String> getExcludeProperties() {
		return excludeProperties;
	}

	/**
	 * 获取需要更新的属性集合</br>
	 * 日期:2013-5-14 下午05:52:59
	 * @return 需要更新的属性集合
	 */
	public Set<String> getIncludeProperties() {
		return includeProperties;
	}
}
