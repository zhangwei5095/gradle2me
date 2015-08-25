/** 
* @file     ContreteSubjectTest.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月11日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.behavior.twoclazz.observer.jdk;

import java.util.Observer;

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

public class ContreteSubjectTest {
	
	
	@Test
	public void change(){
		ConcreteSubject observable = new ConcreteSubject();
		
		Observer observer = new ContreteObserver();
		observable.addObserver(observer);
		
		observable.setContext("许立亢");
		observable.change("star45");
		
		
	}

	
}
