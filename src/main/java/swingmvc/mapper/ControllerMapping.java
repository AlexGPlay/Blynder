package swingmvc.mapper;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import swingmvc.annotations.Path;
import swingmvc.util.Pair;

public class ControllerMapping {

	public Map<String, Pair<Class<?>,Method>> map(List<Class<?>> classes){
		Map<String, Pair<Class<?>,Method>> map = new HashMap<>();
		
		classes
		.stream()
		.forEach( c -> map.putAll( mapController(c) ) );
		
		return map;
	}
	
	private Map<String, Pair<Class<?>,Method>> mapController(Class<?> clazz){
		List<Method> pathMethods = getPathMethods(clazz);
		Map<String, Pair<Class<?>,Method>> map = new HashMap<>();
		
		pathMethods
		.stream()
		.forEach(
				m -> map.put(
						getValue(m), 
						new Pair<Class<?>,Method>(clazz,m))
		);
		
		return map;
	}
	
	private String getValue(Method method) {
		return method.getAnnotation(Path.class).value();
	}

	private List<Method> getPathMethods(Class<?> clazz) {
		return Arrays
				.stream(clazz.getMethods())
				.filter(m -> m.isAnnotationPresent(Path.class))
				.collect(Collectors.toList());
	}
	
}
