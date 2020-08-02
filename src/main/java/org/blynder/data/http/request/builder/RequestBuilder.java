package org.blynder.data.http.request.builder;

import java.lang.reflect.Method;

import org.blynder.data.http.annotations.Request;

/**
 * 
 * Given the method that is executed for the request, this class extracts the
 * url and the petition method and returns the component request. The component
 * request is a different object from the request network object.
 *
 */
public class RequestBuilder {

	private Method m;
	
	public RequestBuilder(Method m) {
		this.m = m;
	}
	
	public org.blynder.data.http.request.components.Request build(){
		Request request = getMethodRequest(m);
		if(request == null)
			return null;
		
		String url = request.url();
		String method = request.method();
		
		return new org.blynder.data.http.request.components.Request(url, method);
	}
	
	private Request getMethodRequest(Method m) {
		return m.getAnnotation(Request.class);
	}
	
}
