/** 
* @file     ProxyTest.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月19日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.structure.proxy.cglib;

import org.junit.AfterClass;
import org.junit.Test;

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
	@Test
	public final void testGetProxyInstance() {
	
		ConcreteProxy concreteProxy = new ConcreteProxy();
		MangerImpl manger = new MangerImpl();
		MangerImpl ss = (MangerImpl)concreteProxy.getProxyInstance(manger);
		ss.save("许立亢");
	}
	
	
	public static void main(String[] args) {
		
		 double c = 10+9+8+8+250+222+12+5.3+2.8+11.8+10+100+45.5+-36.5+11+8+11.98+8;
		 
		 
		
		 double a = 8+58.9+186.5+7+158+18.18+9+17+190+0.6+18;
		 
		 double b = 84+120+53.5+17+10+9+15+10+19.8+10+10+38.54+13.5+9+10;
		 System.out.println(c  +"   "+ (a+c));
		 
		System.out.println("  "+ a +"   "+b+"   "+(a+b));
		
	}

}
