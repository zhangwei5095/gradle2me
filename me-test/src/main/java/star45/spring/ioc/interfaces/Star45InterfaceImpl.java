/** 
* @file     Star45InterfaceImpl.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.spring.ioc.interfaces;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class Star45InterfaceImpl implements Star45Interface {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月8日 上午11:17:27
	 * @param name 
	 * @see star45.spring.ioc.interfaces.Star45Interface#save(java.lang.String) 
	 */ 
	
	
	@Override
	public void save(String name) {
		
		System.out.println("保存为    "+name);
		
	}

}
