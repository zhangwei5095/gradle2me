/** 
* @file     ConcreteSubject.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月11日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.behavior.twoclazz.observer.jdk;

import java.util.Observable;

/**
 * @brief 被观察者具体实现类
 * @details jdk自带的的观察者模式，已经是现实了push(推)，pull(拉)两种模式
 * @warning 通知被观察者的时候一定加上：super.setChanged();
 * @date 2015年8月11日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class ConcreteSubject extends Observable {

	private String context;
	
	private String name;
	
	
	public void change(String context){
		
		super.setChanged();
		this.notifyObservers(context);
		
	}
	
	/** 
	 * @return context 
	 */
	
	public String getContext() {
		return context;
	}

	/** 
	 * @param context 要设置的 context 
	 */
	
	public void setContext(String context) {
		
		this.context = context;
	}
	/** 
	 * @return name 
	 */
	
	public String getName() {
		return name;
	}

	/** 
	 * @param name 要设置的 name 
	 */
	
	public void setName(String name) {
		this.name = name;
	}

	
	
}
