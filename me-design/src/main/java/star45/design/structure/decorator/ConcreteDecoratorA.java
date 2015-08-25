/** 
* @file     ConcreteDecoratorA.java 
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

public class ConcreteDecoratorA extends Decorator {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 上午11:25:03
	 * @param component 
	 */ 
	
	public ConcreteDecoratorA(Component component) {
		super(component);
		
	}
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 上午11:27:14
	 * @param key
	 * @return 
	 * @see star45.design.structure.decorator.Decorator#operation(java.lang.String) 
	 */ 
	
	
	@Override
	public String operation(String key) {
		System.out.println("decoratorA  开始工作。。。"+key);
		return super.operation(key);
	}
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 下午2:27:15 
	 * @see star45.design.structure.decorator.Decorator#doWork() 
	 */ 
	
	
	@Override
	public void doWork() {
		super.doWork();
		this.doWorkBefore();
	}
	
	private void doWorkBefore(){
		System.out.println("领导开始调研市场。。。");
		System.out.println("领导开始开发需求。。。");
		System.out.println("领导开始下发任务。。。");
	}
	
	

}
