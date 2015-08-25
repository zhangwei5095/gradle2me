/** 
* @file     AsyncExceptionHandler.java 
* @brief    my-lib's file 
* @author   许立亢 
* @date     2015年8月25日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.web.util.spring.exception;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

/**
 * @brief 异步调度异常处理器
 * @details spring4.1新版本特性
 * 在异步调度中也提供了相应的异常处理器进行捕获来记录异常： 
 * @warning 注意事项
 * @date 2015年8月25日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	private static final Logger log = Logger.getLogger(AsyncExceptionHandler.class);
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月25日 上午10:40:35
	 * @param ex
	 * @param method
	 * @param params 
	 * @see org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler#handleUncaughtException(java.lang.Throwable, java.lang.reflect.Method, java.lang.Object[]) 
	 */

	@Override
	public void handleUncaughtException(Throwable ex, Method method,
			Object... params) {
		
		log.info("调用异步任务出错了, message : " + ex.getMessage());

	}

}
