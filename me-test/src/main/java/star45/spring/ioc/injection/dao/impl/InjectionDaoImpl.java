/** 
* @file     InjectionDaoImpl.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.spring.ioc.injection.dao.impl;

import star45.spring.ioc.injection.dao.InjectionDao;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class InjectionDaoImpl implements InjectionDao {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月8日 下午1:37:10
	 * @param name
	 * @return 
	 * @see star45.spring.ioc.injection.dao.InjectionDao#getName(java.lang.String) 
	 */ 
	
	
	@Override
	public String getName(String name) {
		
		return name;
	}

}
