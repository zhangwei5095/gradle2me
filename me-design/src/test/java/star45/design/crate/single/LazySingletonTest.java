/** 
* @file     LazySingletonTest.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月11日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.crate.single;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月11日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class LazySingletonTest {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月11日 下午12:50:19
	 * @throws java.lang.Exception 
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link star45.design.crate.single.LazySingleton#getInstance()}.
	 */
	@Test
	public final void testGetInstance() {
		
		LazySingleton first = LazySingleton.getInstance();
		LazySingleton second = LazySingleton.getInstance();
		
		assertEquals("懒汉模式生产的两个实例不一样", first,second);
		
		
	}

}
