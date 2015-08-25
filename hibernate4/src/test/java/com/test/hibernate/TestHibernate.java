/** 
* @file     TestHibernate.java 
* @brief    project-web's file 
* @author   许立亢 
* @date     2015年8月3日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package com.test.hibernate;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import star45.xlk.demo.dao.DemoDao;


/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月3日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class TestHibernate {
	
	private DemoDao demoDao;
    
    /**
     * 这个before方法在所有的测试方法之前执行，并且只执行一次
     * 所有做Junit单元测试时一些初始化工作可以在这个方法里面进行
     * 比如在before方法里面初始化ApplicationContext和userService
     */
    @Before
    public void before(){
    	//通过spring.xml配置文件创建Spring的应用程序上下文环境
    			ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext-hibernate.xml","applicationContext-dao-demo.xml"});
    			System.out.println(ac);
    			demoDao = (DemoDao) ac.getBean("demoDao");
    }
    
    @Test
    public void tesLoad(){
    	String value = demoDao.test("xlk");
    	assertEquals("xlk", value);
    }

}
