/** 
* @file     InjectionTest.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.spring.ioc.di;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import star45.spring.aop.base.UnitTestBase;
import star45.spring.ioc.injection.service.InjectionService;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月8日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class InjectionTest extends UnitTestBase{

	public InjectionTest(){
		super("classpath:spring-ioc-di.xml");
	}
	@Test
	public void testGetName(){
		InjectionService injectionServiec =  super.getBean("injectionServiec");
		
		String actual = injectionServiec.getName("许立亢");
		
		Assert.assertEquals("你好  许立亢", actual);
		
	}
	
}
