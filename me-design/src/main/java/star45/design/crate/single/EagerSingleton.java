/** 
* @file     EagerSingleton.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月11日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.crate.single;

/**
 * @brief 单例模式之饿汉模式
 * @details 饿汉式是典型的空间换时间，当类装载的时候就会创建类的实例，
 * 不管你用不用，先创建出来，然后每次调用的时候，就不需要再判断，节省了运行时间。
 * @warning 饿汉式是典型的空间换时间，当类装载的时候就会创建类的实例，
 * 不管你用不用，先创建出来，然后每次调用的时候，就不需要再判断，节省了运行时间。
 * @date 2015年8月11日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class EagerSingleton {

	/**
	 * 静态私有变量
	 */
	private static EagerSingleton instance = new EagerSingleton();
	/**
	 * 
	 * @brief 私有构造方法
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月11日 下午12:30:54
	 */
	private EagerSingleton(){
		
	}
	/**
	 * 
	 * @brief 静态工程方法 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月11日 下午12:31:43
	 * @return
	 */
	public static EagerSingleton getInstance(){
		
		return instance;
	}
	
}
