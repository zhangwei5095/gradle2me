/** 
* @file     Star45InterfaceTest.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月8日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.spring.ioc.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import star45.spring.aop.base.UnitTestBase;

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
public class Star45InterfaceTest extends UnitTestBase{
	
	
	public Star45InterfaceTest(){
		super("classpath:spring-ioc.xml");
	}
	@Test
	public void testSave(){
		Star45Interface star45Interface =  super.getBean("star45Interface");
		star45Interface.save("xlk");
		
	}
	
}
