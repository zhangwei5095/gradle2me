/** 
* @file     ExceptionAdvisor.java 
* @brief    my-lib's file 
* @author   许立亢 
* @date     2015年8月20日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.web.util.spring;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.dao.DataAccessException;

/**
 * @brief 统一异常处理
 * @details 由Spring AOP调用 输出异常信息，把程序异常抛向业务异常
 * @warning 注意事项
 * @date 2015年8月20日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class ExceptionAdvisor implements ThrowsAdvice {

	private static final Logger log = Logger.getLogger(ExceptionAdvisor.class);
	
	
	public void afterThrowing(Method method, Object[] args,Object target,Exception ex)throws Throwable{
		log.info("异常发生的class类：      "+target.getClass().getName());
		log.info("异常发生的method方法：      "+method.getName());
		for (Object arg : args) {
			log.info("方法的参数: " + arg); 
		}
		log.info("Exception class: " + ex.getClass().getName());  
        log.info("ex.getMessage():" + ex.getMessage());  
        ex.printStackTrace();  
        
        
     // 在这里判断异常，根据不同的异常返回错误。  
        if (ex.getClass().equals(DataAccessException.class))  
        {  
            ex.printStackTrace();  
            throw new BusinessException("数据库操作失败！");  
        } else if (ex.getClass().toString().equals(  
                NullPointerException.class.toString()))  
        {  
            ex.printStackTrace();  
            throw new BusinessException("调用了未经初始化的对象或者是不存在的对象！");  
        } else if (ex.getClass().equals(IOException.class))  
        {  
            ex.printStackTrace();  
            throw new BusinessException("IO异常！");  
        } else if (ex.getClass().equals(ClassNotFoundException.class))  
        {  
            ex.printStackTrace();  
            throw new BusinessException("指定的类不存在！");  
        } else if (ex.getClass().equals(ArithmeticException.class))  
        {  
            ex.printStackTrace();  
            throw new BusinessException("数学运算异常！");  
        } else if (ex.getClass().equals(ArrayIndexOutOfBoundsException.class))  
        {  
            ex.printStackTrace();  
            throw new BusinessException("数组下标越界!");  
        } else if (ex.getClass().equals(IllegalArgumentException.class))  
        {  
            ex.printStackTrace();  
            throw new BusinessException("方法的参数错误！");  
        } else if (ex.getClass().equals(ClassCastException.class))  
        {  
            ex.printStackTrace();  
            throw new BusinessException("类型强制转换错误！");  
        } else if (ex.getClass().equals(SecurityException.class))  
        {  
            ex.printStackTrace();  
            throw new BusinessException("违背安全原则异常！");  
        } else if (ex.getClass().equals(SQLException.class))  
        {  
            ex.printStackTrace();  
            throw new BusinessException("操作数据库异常！");  
        } else if (ex.getClass().equals(NoSuchMethodError.class))  
        {  
            ex.printStackTrace();  
            throw new BusinessException("方法末找到异常！");  
        } else if (ex.getClass().equals(InternalError.class))  
        {  
            ex.printStackTrace();  
            throw new BusinessException("Java虚拟机发生了内部错误");  
        } else  
        {  
            ex.printStackTrace();  
            throw new BusinessException("程序内部错误，操作失败！" + ex.getMessage());  
        }  
		
	}
}
