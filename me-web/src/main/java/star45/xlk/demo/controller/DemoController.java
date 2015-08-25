/** 
* @file     DemoController.java 
* @brief    me-web's file 
* @author   许立亢 
* @date     2015年8月4日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.xlk.demo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import star45.xlk.demo.service.DemoService;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月4日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@Controller
public class DemoController {
	/**
	 * 
	 */
	@Resource
	private DemoService demoService;

	/** 
	 * @param demoService 要设置的 demoService 
	 */
	
	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}
	
	

}
