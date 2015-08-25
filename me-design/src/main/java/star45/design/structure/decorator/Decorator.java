/** 
* @file     Decorator.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月12日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.structure.decorator;

/**
 * @brief 装饰角色
 * @details (Decorator)：持有一个构件(Component)对象的实例，并定义一个与抽象构件接口一致的接口。
 * @warning 注意事项
 * @date 2015年8月12日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public abstract class Decorator implements Component {
	
	private Component component;
	
	

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 上午11:21:41
	 * @param component 
	 */ 
	
	public Decorator(Component component) {
		super();
		this.component = component;
	}



	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 上午11:21:04
	 * @param key
	 * @return 
	 * @see star45.design.structure.decorator.Component#operation(java.lang.String) 
	 */

	@Override
	public  String operation(String key) {
		//委托给构件
		return component.operation(key);
	}
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 下午2:12:47 
	 * @see star45.design.structure.decorator.Component#doWork() 
	 */ 
	
	
	@Override
	public void doWork() {
		component.doWork();
	}

}
