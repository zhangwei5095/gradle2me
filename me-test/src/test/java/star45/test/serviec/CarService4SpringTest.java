/** 
* @file     CarService4SpringTest.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.test.serviec;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import star45.test.service.CarService;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager")
public class CarService4SpringTest {

	@Resource
	private CarService carService;
	
	@Test
	public void testGetName(){
		String expected = "star011";
		String actual = carService.getName("011");
		
		assertEquals("失败。。。两个值不一样",expected, actual);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
