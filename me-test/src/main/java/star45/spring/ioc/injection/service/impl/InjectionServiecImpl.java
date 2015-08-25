/** 
* @file     InjectionServiecImpl.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.spring.ioc.injection.service.impl;

import star45.spring.ioc.injection.dao.InjectionDao;
import star45.spring.ioc.injection.service.InjectionService;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class InjectionServiecImpl implements InjectionService {
	
	private InjectionDao injectionDao;
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月8日 下午1:37:45
	 * @param name
	 * @return 
	 * @see star45.spring.ioc.injection.service.InjectionService#getName(java.lang.String) 
	 */ 
	
	
	@Override
	public String getName(String name) {
		String value = injectionDao.getName("许立亢");
		System.out.println("你好 "+name);
		return "你好  "+value;
	}
	/** 
	 * @param injectionDao 要设置的 injectionDao 
	 */
	
	public void setInjectionDao(InjectionDao injectionDao) {
		this.injectionDao = injectionDao;
	}
	

}
