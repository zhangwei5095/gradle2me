/** 
* @file     CCommand.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月12日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.behavior.twoclazz.command.after;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月12日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class CCommand implements Command {

	private Receiver receiver;
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 下午5:35:25
	 * @param receiver 
	 */ 
	
	public CCommand(Receiver receiver) {
		super();
		this.receiver = receiver;
	}
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 下午5:33:45 
	 * @see star45.design.behavior.twoclazz.command.after.Command#execute() 
	 */

	@Override
	public void execute() {
		this.receiver.actionC();

	}

}
