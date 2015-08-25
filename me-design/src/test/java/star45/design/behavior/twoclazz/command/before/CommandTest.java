/** 
* @file     CommandTest.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月13日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.behavior.twoclazz.command.before;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月13日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class CommandTest {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月13日 上午11:18:56
	 * @throws java.lang.Exception 
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link star45.design.behavior.twoclazz.command.before.Invoker#action()}.
	 */
	@Test
	public final void testAction() {
		
		
		Command command = new ConcreteCommandA();
//		Command command = new ConcreteCommandA();
		Invoker invoker = new Invoker(command);
		invoker.action();
		
		
	}

}
