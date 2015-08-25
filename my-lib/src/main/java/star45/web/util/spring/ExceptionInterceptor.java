/** 
* @file     ExceptionInterceptor.java 
* @brief    my-lib's file 
* @author   许立亢 
* @date     2015年8月20日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.web.util.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月20日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class ExceptionInterceptor implements MethodInterceptor {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月20日 下午5:07:25
	 * @param invocation
	 * @return
	 * @throws Throwable 
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation) 
	 */

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("haha。。。");
		try {
			invocation.proceed();
		} catch (Exception e) {
			System.out.println("统一异常拦截器。。。");
			e.printStackTrace();
		}
		
		return null;
	}

}
