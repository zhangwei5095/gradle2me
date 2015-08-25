/** 
* @file     LogInterceptor.java 
* @brief    my-lib's file 
* @author   许立亢 
* @date     2015年8月20日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.web.util.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月20日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class LogInterceptor implements MethodInterceptor {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月20日 下午4:11:57
	 * @param invocation
	 * @return
	 * @throws Throwable 
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation) 
	 */

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		 Logger loger = Logger.getLogger(invocation.getClass());  
		  
	        loger.info("--Log By Andy Chan -----------------------------------------------------------------------------");  
	        loger.info(invocation.getMethod() + ":BEGIN!--(Andy ChanLOG)");// 方法前的操作  
	        Object obj = invocation.proceed();// 执行需要Log的方法  
	        loger.info(invocation.getMethod() + ":END!--(Andy ChanLOG)");// 方法后的操作  
	        loger.info("-------------------------------------------------------------------------------------------------");  
	  
	        return obj;  
	}

}
