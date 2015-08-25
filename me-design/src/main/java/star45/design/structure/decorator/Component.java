/** 
* @file     Component.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月12日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.structure.decorator;

/**
 * @brief 抽象构件角色
 * @details (Component)：给出一个抽象接口，以规范准备接收附加责任的对象。
 * @warning 注意事项
 * @date 2015年8月12日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public interface Component {
	
	public String operation(String key);
	
	public void doWork();
	
	
	
}
