/** 
* @file     DecoratorTest.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月12日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.structure.decorato;

import org.junit.AfterClass;
import org.junit.Test;

import star45.design.structure.decorator.Component;
import star45.design.structure.decorator.ConcreteComponent;
import star45.design.structure.decorator.ConcreteDecoratorA;
import star45.design.structure.decorator.ConcreteDecoratorB;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月12日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class DecoratorTest {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 上午11:30:28
	 * @throws java.lang.Exception 
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link star45.design.structure.decorator.Decorator#operation(java.lang.String)}.
	 */
	@Test
	public final void testOperation() {
		
		Component component = new ConcreteComponent();
		component = new ConcreteDecoratorA(component);
		component = new ConcreteDecoratorB(component);
		
//		component.operation("许立亢");
		component.doWork();
		
	}

}
