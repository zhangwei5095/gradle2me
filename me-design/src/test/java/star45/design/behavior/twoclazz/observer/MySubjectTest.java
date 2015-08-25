/** 
* @file     MySubjectTest.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月10日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.behavior.twoclazz.observer;

import org.junit.Test;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月10日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class MySubjectTest {


	/**
	 * Test method for {@link star45.design.behavior.twoclazz.observer.MySubJect#operation()}.
	 */
	@Test
	public final void testOperation() {
		
		Subject subject = new MySubJect();
		subject.add(new Observer1());
		subject.add(new Observer2());
		subject.operation();
	}

}
