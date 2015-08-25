/** 
* @file     UserServiceImpl.java 
* @brief    my-lib's file 
* @author   许立亢 
* @date     2015年8月20日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.web.util.spring;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月20日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class UserServiceImpl implements UserService {

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月20日 下午4:18:09
	 * @param name 
	 * @throws Exception 
	 * @see star45.web.util.spring.UserService#save(java.lang.String) 
	 */

	@Override
	public void save(String name) throws BusinessException {
		System.out.println("执行save 方法为： "+name);
		
		throw new NullPointerException();

	}

}
