/** 
* @file     Demo.java 
* @brief    hibernate4's file 
* @author   许立亢 
* @date     2015年8月4日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.xlk.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月4日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@Entity
@Table(name="demo")
public class Demo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	@Column
	private String name;
	/** 
	 * @return id 
	 */
	
	public String getId() {
		return id;
	}
	/** 
	 * @param id 要设置的 id 
	 */
	
	public void setId(String id) {
		this.id = id;
	}
	/** 
	 * @return name 
	 */
	
	public String getName() {
		return name;
	}
	/** 
	 * @param name 要设置的 name 
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
}
