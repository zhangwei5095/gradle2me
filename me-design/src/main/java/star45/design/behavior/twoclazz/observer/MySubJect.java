/** 
* @file     MySubJect.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月10日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.behavior.twoclazz.observer;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月10日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class MySubJect extends AbstractSubject {
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月10日 下午2:54:02 
	 * @see star45.design.behavior.twoclazz.observer.AbstractSubject#operation() 
	 */ 
	
	
	@Override
	public void operation() {
		System.out.println("更新 自己。。。!");  
		 notifyObservers();  
	}
}
