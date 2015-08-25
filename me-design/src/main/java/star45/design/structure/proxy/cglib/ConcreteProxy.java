/** 
 * @file     ConcreteProxy.java 
 * @brief    me-design's file 
 * @author   许立亢 
 * @date     2015年8月19日
 * @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
 */

package star45.design.structure.proxy.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @brief 类简短说明
 * @details 详细说明
 * @warning 注意事项
 * @date 2015年8月19日
 * @author 许立亢
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class ConcreteProxy implements MethodInterceptor {

	private Object target;

	public Object getProxyInstance(Object target) {
		this.target = target;  
        // 声明增强类实例  
        Enhancer enhancer = new Enhancer();   
        // 设置被代理类字节码，CGLIB根据字节码生成被代理类的子类  
        enhancer.setSuperclass(this.target.getClass());    
        // 设置要代理的拦截器，回调函数，即一个方法拦截   new MethodInterceptor()  
        enhancer.setCallback(this);   
        // 创建代理对象 实例   
        return enhancer.create();    
	}

	/**
	 * @brief 方法简短说明
	 * @details 详细说明
	 * @warning 注意事项
	 * @date 2015年8月19日 上午11:12:05
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @throws Throwable
	 * @see org.springframework.cglib.proxy.MethodInterceptor#intercept(java.lang.Object,
	 *      java.lang.reflect.Method, java.lang.Object[],
	 *      org.springframework.cglib.proxy.MethodProxy)
	 */

	@Override
	public Object intercept(Object target, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		before();
		Object result = proxy.invokeSuper(target, args);
		after();
		return result;
	}
	private void before(){
		System.out.println("开始执行方法前......");
	}
	private void after(){
		System.out.println("开始执行方法后......");
	}

}
