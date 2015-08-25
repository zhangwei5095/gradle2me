/** 
* @file     DemoService.java 
* @brief    me-web's file 
* @author   许立亢 
* @date     2015年8月4日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.xlk.demo.service;

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

public interface DemoService {
	
	public String test(String key);
	
	public Demo add(Demo demo);
}
