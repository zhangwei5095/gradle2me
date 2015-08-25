/** 
* @file     UnitTestBase.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.web.util.spring;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class UnitTestBase {
	
	private ClassPathXmlApplicationContext context;
	
	private String springXmlPath;
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月8日 上午10:20:35 
	 */

	public UnitTestBase() {
		
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月8日 上午10:20:53
	 * @param springXmlPath 
	 */ 
	
	public UnitTestBase(String springXmlPath) {
		super();
		this.springXmlPath = springXmlPath;
	}
	@Before
	public void before(){
		if(StringUtils.isNotEmpty(springXmlPath)){
//			springXmlPath = "classpath*:spring-*.xml";
		}
		try {
			context = new ClassPathXmlApplicationContext(springXmlPath.split("[,\\s]+"));
			context.start();
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	@After
	public void after(){
		context.destroy();
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends Object> T getBean(String beanId) {
		
		try {
			return (T)context.getBean(beanId);
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	protected <T extends Object> T getBean(Class<T> clazz) {
		
		try {
			return (T)context.getBean(clazz);
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
