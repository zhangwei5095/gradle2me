/** 
* @file     Invoker.java 
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

public class Invoker {
	
	private Command acommand = null;
	private Command bcommand = null;
	private Command ccommand = null;

	
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月13日 上午11:45:27
	 * @param acommand
	 * @param bcommand
	 * @param ccommand 
	 */ 
	
	public Invoker(Command acommand, Command bcommand, Command ccommand) {
		super();
		this.acommand = acommand;
		this.bcommand = bcommand;
		this.ccommand = ccommand;
	}
	public void actiona(){
		
		acommand.execute();
		
	}
	public void actionb(){
		
		bcommand.execute();
		
	}
	public void actionc(){
		
		ccommand.execute();
		
	}
}
