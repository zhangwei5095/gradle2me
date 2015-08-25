/** 
* @file     CarServiceImpl.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.test.service.impl;

import star45.test.service.CarService;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class CarServiceImpl implements CarService {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月7日 上午10:12:47
	 * @param id
	 * @return 
	 * @see star45.test.service.CarService#getName(java.lang.String) 
	 */ 
	
	
	@Override
	public String getName(String id) {
		
		String name = "star" + id;
		
		return name;
	}

}
