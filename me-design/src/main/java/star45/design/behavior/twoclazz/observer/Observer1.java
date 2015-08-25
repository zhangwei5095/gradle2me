/** 
* @file     Observer1.java 
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

public class Observer1 implements Observer {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月10日 下午2:45:53 
	 * @see star45.design.behavior.twoclazz.observer.Observer#update() 
	 */

	@Override
	public void update() {
		
		System.out.println("观察者1 接收到信息。。。");

	}

}
