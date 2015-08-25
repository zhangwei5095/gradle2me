/** 
* @file     Car.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.test.domain;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class Car {
	
	private String id;
	
	private String name;

	/** 
	 * @return id 
	 */
	
	public String getId() {
		return id;
	}

	/** 
	 * @param id 要设置的 id 
	 */
	
	public void setId(String id) {
		this.id = id;
	}

	/** 
	 * @return name 
	 */
	
	public String getName() {
		return name;
	}

	/** 
	 * @param name 要设置的 name 
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
}
