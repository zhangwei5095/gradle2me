package framework.star45.hibernate4.core.db;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

/**
 * Hibernate实体工具类</br> 
 * 类名: HibernateEntryUtils </br> 
 * 日期: 2013-5-14 下午05:28:51 </br> 
 * @author 徐帆 
 * @version 1.0
 */
public class HibernateEntryUtils {

	protected static Logger logger = Logger.getLogger(HibernateEntryUtils.class);


	/**
	 * 获得同时有get和set的field和value</br>
	 * 日期:2013-5-14 下午05:29:14
	 * @param bean 要解析的bean
	 * @return 字段名-值映射列表
	 */
	public static Map<String, Object> describe(Object bean) {
		Map<String, Object> des = new HashMap<String, Object>();
		PropertyDescriptor desor[] = PropertyUtils.getPropertyDescriptors(bean);
		String name = null;
		for (int i = 0; i < desor.length; i++) {
			if (desor[i].getReadMethod() != null && desor[i].getWriteMethod() != null) {
				name = desor[i].getName();
				try {
					des.put(name, PropertyUtils.getProperty(bean, name));
				} catch (Exception e) {
					throw new RuntimeException("属性不存在：" + name);
				}
			}
		}
		return des;
	}

}
