/** 
* @file     TestSpring.java 
* @brief    project-web's file 
* @author   许立亢 
* @date     2015年8月3日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.test.spring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import star45.xlk.demo.service.DemoService;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月3日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class TestSpring {
	
	@Test
	public void testLoad(){
		//通过spring.xml配置文件创建Spring的应用程序上下文环境
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext-test.xml",
		"applicationContext-hibernate.xml"});
		System.out.println(ac);
		//从Spring的IOC容器中获取bean对象
		DemoService demoService = (DemoService) ac.getBean("demoService");
		//执行测试方法
		String value = demoService.test("xlk");
		System.out.println("测试的值是：  "+value);
		assertEquals("xlk", value);
	}
}
