/** 
* @file     DemoDaoImpl.java 
* @brief    me-web's file 
* @author   许立亢 
* @date     2015年8月4日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.xlk.demo.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import star45.xlk.demo.dao.DemoDao;
import star45.xlk.demo.domain.Demo;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月4日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@Repository
public class DemoDaoImpl implements DemoDao {
	@Resource
	private HibernateTemplate hibernateTemplate;

	/** 
	 * @param hibernateTemplate 要设置的 hibernateTemplate 
	 */
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月4日 下午3:54:53
	 * @param key
	 * @return 
	 * @see star45.xlk.demo.dao.DemoDao#test(java.lang.String) 
	 */ 
	
	
	@Override
	public String test(String key) {
		
		return key;
	}
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月4日 下午5:15:15
	 * @param demo
	 * @return 
	 * @see star45.xlk.demo.dao.DemoDao#add(star45.xlk.demo.domain.Demo) 
	 */ 
	
	
	@Override
	public Demo add(Demo demo) {
		hibernateTemplate.save(demo);
		return null;
	}

}
