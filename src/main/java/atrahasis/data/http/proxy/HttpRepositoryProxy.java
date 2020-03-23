package atrahasis.data.http.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import atrahasis.data.http.request.Requester;
import atrahasis.data.http.request.builder.RequestBuilder;
import atrahasis.data.http.request.builder.RequestParamsBuilder;
import atrahasis.data.http.request.components.Request;
import atrahasis.data.http.request.components.RequestParams;

/**
 * 
 * The DynamicProxy that will handle all the invocations for HttpRepositories.
 * This class will extract the method parameters and the annotation in order
 * to build the request. All the building and the requesting process is handled
 * in builder and service classes.
 *
 */
public class HttpRepositoryProxy implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		Request request = new RequestBuilder(m).build();
		RequestParams params = new RequestParamsBuilder(m, args).build();
		
		return new Requester(request, params).request();
	}
	
}
