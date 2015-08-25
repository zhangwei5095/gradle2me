/** 
* @file     Invoker.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月13日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.behavior.twoclazz.command.before;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月13日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class Invoker {
	
	private Command command;

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月13日 上午11:17:06
	 * @param command 
	 */ 
	
	public Invoker(Command command) {
		super();
		this.command = command;
	}
	
	public void action(){
		command.execute();
	}
}
