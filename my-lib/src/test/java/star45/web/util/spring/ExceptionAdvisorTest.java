/** 
* @file     ExceptionAdvisorTest.java 
* @brief    my-lib's file 
* @author   许立亢 
* @date     2015年8月20日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.web.util.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月20日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class ExceptionAdvisorTest extends UnitTestBase{

	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月20日 下午4:22:42 
	 */

	public ExceptionAdvisorTest() {
		super("classpath:spring-interceptor-test.xml");
	}
	
	@Test
	public final void test() throws BusinessException {
			UserService userService = super.getBean("userService");
			userService.save("许立亢");
			System.out.println("执行完毕。。。。。。");
			
		
		
	}
	@Test
	public void test01(){
		String name = "我的-222222-33333-445454.txt";
		
		int index = name.lastIndexOf("-");
		
		int index1 = name.lastIndexOf(".");
		
		String lastname = name.substring(index1);
		
		String fristname = name.substring(0,index);
		String newname = fristname+lastname;
		System.out.println(newname);
		
		
	}
	

}
