/** 
* @file     ConcreteCommandB.java 
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

public class ConcreteCommandB extends Command {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月13日 上午11:10:41 
	 * @see star45.design.behavior.twoclazz.command.before.Command#execute() 
	 */

	@Override
	public void execute() {
		Receiver1 receiver1 = super.receiver1;
		receiver1.action3();
		Receiver2 receiver2 = super.receiver2;
		receiver2.action1();
		receiver2.action2();

	}

}
