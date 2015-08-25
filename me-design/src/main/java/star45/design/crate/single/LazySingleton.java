/** 
* @file     LazySingleton.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月11日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.crate.single;

/**
 * @brief 单例模式之懒汉模式
 * @details 懒汉式其实是一种比较形象的称谓。既然懒，那么在创建对象实例的时候就不着急。
 * 会一直等到马上要使用对象实例的时候才会创建，懒人嘛，
 * 总是推脱不开的时候才会真正去执行工作，因此在装载对象的时候不创建对象实例。
 * @warning 懒汉式是典型的时间换空间,就是每次获取实例都会进行判断，看是否需要创建实例，
 * 浪费判断的时间。当然，如果一直没有人使用的话，那就不会创建实例，则节约内存空间
 * @date 2015年8月11日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class LazySingleton {
	/**
	 * 私有变量
	 */
	private static LazySingleton instance = null;
	/**
	 * 
	 * @brief 私有构造方法 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月11日 下午12:49:15
	 */
	private LazySingleton(){
		
	}
	/**
	 * 
	 * @brief 静态工厂方法 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月11日 下午12:49:29
	 * @return
	 */
	public static synchronized LazySingleton getInstance(){
		
		if(instance == null){
			instance = new LazySingleton();
		}
		return instance;
		
	}
}
