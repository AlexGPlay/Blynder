package atrahasis.data.http.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import atrahasis.data.http.request.Requester;
import atrahasis.data.http.request.builder.RequestBuilder;
import atrahasis.data.http.request.builder.RequestParamsBuilder;
import atrahasis.data.http.request.components.Request;
import atrahasis.data.http.request.components.RequestParams;

public class HttpRepositoryProxy implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		Request request = new RequestBuilder(m).build();
		RequestParams params = new RequestParamsBuilder(m, args).build();
		
		return new Requester(request, params).request();
	}
	
}
