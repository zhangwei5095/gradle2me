/** 
* @file     Star45Biz.java 
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

public class AspectBiz {
	
	public String sysHello(String name){
		String value = "Hello "+name;
		System.out.println(value);
		try {
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException();
	}
	public String sysHello(){
		
		System.out.println("sysHello");
		
		throw new RuntimeException();
	}
}
