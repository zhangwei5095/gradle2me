/** 
* @file     Command.java 
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

public abstract class Command {
	
	protected Receiver1 receiver1 = new Receiver1();
	
	protected Receiver2 receiver2 = new Receiver2();
	
	public abstract void execute();
	
}
