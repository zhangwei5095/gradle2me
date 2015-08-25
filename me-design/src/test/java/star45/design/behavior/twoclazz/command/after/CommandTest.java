/** 
* @file     CommandTest.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月12日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.behavior.twoclazz.command.after;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月12日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class CommandTest {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月12日 下午5:45:33
	 * @throws java.lang.Exception 
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link star45.design.behavior.twoclazz.command.after.Invoker#action()}.
	 */
	@Test
	public final void testAction() {
		
		//创建接收者
        Receiver receiver = new Receiver();
        //创建命令对象，设定它的接收者
        Command acommand = new ACommand(receiver);
        //创建命令对象，设定它的接收者
        Command bcommand = new BCommand(receiver);
        //创建命令对象，设定它的接收者
        Command ccommand = new CCommand(receiver);
        //创建请求者，把命令对象设置进去
        Invoker invoker = new Invoker(acommand,bcommand,ccommand);
        //执行方法
        invoker.actiona();
        invoker.actionb();
        invoker.actionc();
	}

}
