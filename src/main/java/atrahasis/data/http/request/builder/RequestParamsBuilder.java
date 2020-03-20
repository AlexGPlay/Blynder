package atrahasis.data.http.request.builder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import atrahasis.data.http.annotations.Data;
import atrahasis.data.http.annotations.Headers;
import atrahasis.data.http.annotations.Params;
import atrahasis.data.http.annotations.Segment;
import atrahasis.data.http.request.components.RequestParams;

public class RequestParamsBuilder {

	private Method m;
	private Object[] args;
	
	public RequestParamsBuilder(Method m, Object[] args) {
		this.m = m;
		this.args = args == null ? new Object[] {} : args;
	}
	
	public RequestParams build(){
		Map<Class<?>, Object> annotations = new HashMap<>();
		
		for(int i=0;i<args.length;i++) {
			Parameter p = m.getParameters()[i];
			Class<?> annotation = getAnnotationType(p);
			if(annotation != null) 
				annotations.put(annotation, args[i]);
		}
		
		return new RequestParams(
				annotations.get(Data.class), 
				annotations.get(Headers.class), 
				annotations.get(Params.class),
				annotations.get(Segment.class)
			);
	}
	
	private Class<?> getAnnotationType(Parameter parameter){
		String[] possibleAnnotations = {"Data", "Params", "Headers", "Segment"};
		
		for(String s : possibleAnnotations) {
			String completeName = "atrahasis.data.http.annotations." + s;
			Class<? extends Annotation> annotation = getAnnotationForName(completeName);
			if(parameter.isAnnotationPresent(annotation))
				return annotation;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private Class<? extends Annotation> getAnnotationForName(String name){
		try {
			return (Class<? extends Annotation>) Class.forName(name);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
}
