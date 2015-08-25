/** 
* @file     ConcreteDecoratorB.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月12日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.structure.decorator;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月12日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class ConcreteDecoratorB extends Decorator {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 上午11:26:09
	 * @param component 
	 */ 
	
	public ConcreteDecoratorB(Component component) {
		super(component);
	}
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 上午11:29:12
	 * @param key
	 * @return 
	 * @see star45.design.structure.decorator.Decorator#operation(java.lang.String) 
	 */ 
	
	
	@Override
	public String operation(String key) {
		String temp = super.operation(key);
		System.out.println("decoratorB  接着工作  "+key);
		return temp;
	}
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 下午2:30:29 
	 * @see star45.design.structure.decorator.Decorator#doWork() 
	 */ 
	
	
	@Override
	public void doWork() {
		this.doWorkAfter();
		super.doWork();
	}
	
	public void doWorkAfter(){
		System.out.println("领导拿着工程项目卖钱。。。");
	}
	

}
