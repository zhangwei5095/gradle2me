/** 
* @file     ConcreteComponent.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月12日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.structure.decorator;

/**
 * @brief 具体构件角色
 * @details (ConcreteComponent)：定义一个将要接收附加责任的类。 	
 * @warning 注意事项
 * @date 2015年8月12日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class ConcreteComponent implements Component{

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 上午11:19:33
	 * @param key
	 * @return 
	 * @see star45.design.structure.decorator.Component#operation(java.lang.String) 
	 */ 
	
	
	@Override
	public String operation(String key) {
		
		String value = "star"+key;
		System.out.println("ConcreteComponent  "+value);
		return value;
		
	}
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 下午2:10:01 
	 * @see star45.design.structure.decorator.Component#doWork() 
	 */ 
	
	
	@Override
	public void doWork() {
		
		System.out.println("基层员工努力干活.....");
		
	}

}
