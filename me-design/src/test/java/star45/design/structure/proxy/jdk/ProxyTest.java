/** 
* @file     ProxyTest.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月19日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.structure.proxy.jdk;

import org.junit.AfterClass;
import org.junit.Test;

import star45.design.structure.proxy.Manger;
import star45.design.structure.proxy.MangerImpl;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月19日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class ProxyTest {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月19日 上午10:40:54
	 * @throws java.lang.Exception 
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link star45.design.structure.proxy.jdk#getProxyInstance(java.lang.Object)}.
	 */
	@SuppressWarnings("static-access")
	@Test
	public final void testGetProxyInstance() {
	
		ConcreteProxy concreteProxy = new ConcreteProxy();
		MangerImpl manger = new MangerImpl();
		Manger ss = (Manger)concreteProxy.getProxyInstance(manger);
		ss.save("许立亢");
	}

}
