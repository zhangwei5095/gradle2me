/** 
* @file     Star45Aspect.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.spring.aop.aspect;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class Star45Aspect {

	public void before(){
		System.out.println("before进入前置通知。。。。。。。");
		
	}
	public void after(){
		System.out.println("after进入后置通知。。。。。。。");
		
	}
	public void afterReturning(){
		System.out.println("afterReturning");
	}
	public void afterThrowing(){
		
		System.out.println("afterThrowing");
		
	}
	public void around(){
		System.out.println("进入环绕通知。。。。。。。");
		
	}
	
	
	
}
