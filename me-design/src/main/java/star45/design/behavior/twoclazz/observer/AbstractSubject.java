/** 
* @file     AbstractSubject.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月10日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.behavior.twoclazz.observer;

import java.util.Vector;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月10日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public abstract class AbstractSubject implements Subject {

	private Vector<Observer> vector = new Vector<Observer>();
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月10日 下午2:50:08
	 * @param observer 
	 * @see star45.design.behavior.twoclazz.observer.Subject#add(star45.design.behavior.twoclazz.observer.Observer) 
	 */

	@Override
	public void add(Observer observer) {
		vector.add(observer);

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月10日 下午2:50:08
	 * @param observer 
	 * @see star45.design.behavior.twoclazz.observer.Subject#del(star45.design.behavior.twoclazz.observer.Observer) 
	 */

	@Override
	public void del(Observer observer) {
		vector.remove(observer);

	}

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月10日 下午2:50:08 
	 * @see star45.design.behavior.twoclazz.observer.Subject#notifyObservers() 
	 */

	@Override
	public void notifyObservers() {
		
		for (Observer observer : vector) {
			
			observer.update();
		}

	}


}
