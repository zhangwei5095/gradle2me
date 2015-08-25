/** 
* @file     DemoServiceImpl.java 
* @brief    me-web's file 
* @author   许立亢 
* @date     2015年8月4日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.xlk.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import star45.xlk.demo.dao.DemoDao;
import star45.xlk.demo.domain.Demo;
import star45.xlk.demo.service.DemoService;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月4日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@Service
public class DemoServiceImpl implements DemoService{
	@Resource
	private DemoDao demoDao;
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月4日 下午3:53:08
	 * @param key
	 * @return 
	 * @see star45.xlk.demo.service.DemoService#test(java.lang.String) 
	 */ 
	
	
	@Override
	public String test(String key) {
		
		return demoDao.test(key);
	}
	
	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月4日 下午5:32:03
	 * @param demo
	 * @return 
	 * @see star45.xlk.demo.service.DemoService#add(star45.xlk.demo.domain.Demo) 
	 */ 
	
	@Transactional(readOnly=false)
	@Override
	public Demo add(Demo demo) {
		
		return demoDao.add(demo);
	}
	

	/** 
	 * @param demoDao 要设置的 demoDao 
	 */
	
	public void setDemoDao(DemoDao demoDao) {
		this.demoDao = demoDao;
	}
	
	
}
