package atrahasis.data.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxyFactory {

	private Class<?> proxyInterface;
	private InvocationHandler invocationHandler;
	
	public DynamicProxyFactory(Class<?> proxyInterface, InvocationHandler invocationHandler) {
		this.proxyInterface = proxyInterface;
		this.invocationHandler = invocationHandler;
	}
	
    public Object create() {
        return Proxy.newProxyInstance(
        		proxyInterface.getClassLoader(), 
        		new Class<?>[]{ proxyInterface }, 
        		invocationHandler
        	);
    }
	
}
