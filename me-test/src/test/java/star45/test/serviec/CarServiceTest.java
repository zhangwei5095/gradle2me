/** 
* @file     CarServiceTest.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.test.serviec;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import star45.test.service.CarService;
import star45.test.service.impl.CarServiceImpl;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */


public class CarServiceTest {
	
	private CarService carService;

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月7日 上午10:18:39
	 * @throws java.lang.Exception 
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月7日 上午10:18:39
	 * @throws java.lang.Exception 
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月7日 上午10:18:39
	 * @throws java.lang.Exception 
	 */
	@Before
	public void setUp() throws Exception {
		carService = new CarServiceImpl();
	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月7日 上午10:18:39
	 * @throws java.lang.Exception 
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link star45.test.service.impl.CarServiceImpl#getName(java.lang.String)}.
	 */
	@Test
	public final void testGetName() {
		
		String name = carService.getName("001");
		assertEquals("star001", name);
		
	}
	
	@Ignore
	@Test
	public void testAssertArrayEquals(){
		
		String[] expecteds = new String[3]; 
		expecteds[0] = "a";
		expecteds[1] = "b";
		expecteds[2] = "c1";
		
		String[] actuals = new String[]{"a","b","c"};
		assertArrayEquals("失败---两个数组不相等 ",expecteds, actuals);
	}

}
