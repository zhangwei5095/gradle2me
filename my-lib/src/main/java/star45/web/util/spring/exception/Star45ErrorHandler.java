/** 
* @file     Star45ErrorHandler.java 
* @brief    my-lib's file 
* @author   许立亢 
* @date     2015年8月25日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.web.util.spring.exception;

import org.apache.log4j.Logger;
import org.springframework.util.ErrorHandler;

/**
 * @brief 事件调度异常处理器
 * @details spring4.1新版本特性
 * @warning 注意事项
 * @date 2015年8月25日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class Star45ErrorHandler implements ErrorHandler {
	
	private static final Logger log = Logger.getLogger(Star45ErrorHandler.class);
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月25日 上午10:22:23
	 * @param t 
	 * @see org.springframework.util.ErrorHandler#handleError(java.lang.Throwable) 
	 */ 
	
	
	@Override
	public void handleError(Throwable t) {
		
		log.info("事件失败了, error message : " + t.getMessage());
		
	}

}
