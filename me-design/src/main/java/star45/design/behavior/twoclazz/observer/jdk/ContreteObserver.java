/** 
* @file     ContreteObserver.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月11日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.behavior.twoclazz.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @brief 观察者具体实现类
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月11日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class ContreteObserver implements Observer {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月11日 上午9:17:14
	 * @param o
	 * @param arg 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object) 
	 */

	@Override
	public void update(Observable o, Object arg) {
		
		System.out.println("观察者  的名字  "+((ConcreteSubject)o).getContext());
		System.out.println("观察者  更新的内容  "+arg);

	}

}
