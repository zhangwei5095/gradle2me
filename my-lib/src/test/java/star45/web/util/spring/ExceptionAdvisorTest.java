/** 
* @file     ExceptionAdvisorTest.java 
* @brief    my-lib's file 
* @author   许立亢 
* @date     2015年8月20日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.web.util.spring;

import java.util.ArrayList;
import java.util.List;

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
		List<String> list = new ArrayList<String>();
		
		list.add("5");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).equals("5")){
				String sour = list.get(0);
				String dist = list.get(i);
				list.set(i, sour);
				list.set(0, dist);
			}
		}
		for (String string : list) {
			System.out.println(string);
		}
		
		
	}
	

}
