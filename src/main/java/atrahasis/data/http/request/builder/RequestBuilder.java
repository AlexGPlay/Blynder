package atrahasis.data.http.request.builder;

import java.lang.reflect.Method;

import atrahasis.data.http.annotations.Request;

public class RequestBuilder {

	private Method m;
	
	public RequestBuilder(Method m) {
		this.m = m;
	}
	
	public atrahasis.data.http.request.components.Request build(){
		Request request = getMethodRequest(m);
		if(request == null)
			return null;
		
		String url = request.url();
		String method = request.method();
		
		return new atrahasis.data.http.request.components.Request(url, method);
	}
	
	private Request getMethodRequest(Method m) {
		return m.getAnnotation(Request.class);
	}
	
}
