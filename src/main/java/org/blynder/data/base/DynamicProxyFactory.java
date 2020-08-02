package org.blynder.data.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 
 * The DynamicProxyFactory will create a DynamicProxy class that will handle
 * the calls for the given interface.
 *
 */
public class DynamicProxyFactory {

	/**
	 * 
	 * The interface calls that will be handled.
	 * 
	 */
	private Class<?> proxyInterface;
	
	/**
	 * 
	 * The dynamicProxy instance that will handle the calls.
	 * 
	 */
	private InvocationHandler invocationHandler;
	
	public DynamicProxyFactory(Class<?> proxyInterface, InvocationHandler invocationHandler) {
		this.proxyInterface = proxyInterface;
		this.invocationHandler = invocationHandler;
	}
	
	/**
	 * 
	 * This method instantiates the class that will handle the method calls for
	 * the given interface. For example, if you want the FooInterface methods to
	 * be managed by the FooInvocationHandler proxy, you will instatiate a factory
	 * with those parameters, such as new DynamicProxyFactory(FooInterface.class, new
	 * FooInvocationHandler()) and calling the create method will return the instance.
	 * The given instance by this method can be assigned to the FooInterface variable.
	 * For example, you can do this:<br>
	 * FooInterface foo = new DynamicProxyFactory(FooInterface.class, new
	 * FooInvocationHandler()).create()<br>
	 * That line is what the autowiring process will do under the hood for the
	 * repositories that are autowired.
	 * 
	 * @return
	 * An instance of a dynamic proxy that will handle all the calls for the
	 * class.
	 * 
	 */
    public Object create() {
        return Proxy.newProxyInstance(
        		proxyInterface.getClassLoader(), 
        		new Class<?>[]{ proxyInterface }, 
        		invocationHandler
        	);
    }
	
}
