/** 
* @file     ConcreteProxy.java 
* @brief    me-design's file 
* @author   许立亢 
* @date     2015年8月19日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.design.structure.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月19日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class ConcreteProxy implements InvocationHandler{
	/**
	 * 代理对象
	 */
	private Object proxy;
	/**
	 * 目标对象
	 */
	private Object target;

	
	
	private static Map<Class<?>, ConcreteProxy> concreteProxys = new HashMap<Class<?>, ConcreteProxy>();

	@SuppressWarnings("unchecked")
	public synchronized static <T> T getProxyInstance(Object target){
		Class<T> clazz = (Class<T>)target.getClass();
		ConcreteProxy concreteProxy = concreteProxys.get(clazz);
		if(concreteProxy == null){
			concreteProxy = new ConcreteProxy();
			try {
				concreteProxy.setTarget(target);
				
				ClassLoader loader = target.getClass().getClassLoader();
				Class<?>[] interfaces = target.getClass().getInterfaces();
				InvocationHandler h = concreteProxy;
				Object proxy = Proxy.newProxyInstance(loader, interfaces, h);
				
				concreteProxy.setProxy(proxy);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			concreteProxys.put(target.getClass(), concreteProxy);
		}
		return (T)concreteProxy.getProxy();
	}
	

	/**
	 * @brief 方法简短说明 
	 * @details 详细说明 
	 * @warning 注意事项
	 * @date 2015年8月19日 上午9:23:49
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 * @throws Throwable 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]) 
	 */ 
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		before();
		Object result = method.invoke(getTarget(), args);
		after();
		return result;
	}
	
	private void before(){
		System.out.println("开始执行方法前......");
	}
	private void after(){
		System.out.println("开始执行方法后......");
	}
	/** 
	 * @param proxy 要设置的 proxy 
	 */
	
	public void setProxy(Object proxy) {
		this.proxy = proxy;
	}

	/** 
	 * @param target 要设置的 target 
	 */
	
	public void setTarget(Object target) {
		this.target = target;
	}
	/** 
	 * @return proxy 
	 */
	
	public Object getProxy() {
		return proxy;
	}

	/** 
	 * @return target 
	 */
	
	public Object getTarget() {
		return target;
	}

}
