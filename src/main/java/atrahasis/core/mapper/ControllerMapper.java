package atrahasis.core.mapper;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import atrahasis.core.annotations.Controller;
import atrahasis.core.annotations.Path;
import atrahasis.core.util.Pair;

/**
 * 
 * This class is the built-in controller mapper. The process this class
 * will do, is to map the controllers into a map that has the URL as the key
 * and a pair of class - method as the value, making the application faster
 * when navigating between URLs. For more information read the IControllerMapper
 * documentation.
 *
 */
public class ControllerMapper implements IControllerMapper{

	public Map<String, Map<String, Pair<Class<?>,Method>>> map(List<Class<?>> classes){
		Map<String, Map<String, Pair<Class<?>,Method>>> map = new HashMap<>();
		
		classes
		.stream()
		.forEach( c -> map.putAll( mapController(c) ) );
		
		return map;
	}
	
	/**
	 * 
	 * Given a controller, this method will map the URL into the
	 * controller class and method. This will be made using the getPathMethods
	 * to extract the path methods from a given class and the getValue method
	 * that will extract the URL from a path method.
	 * @param clazz
	 * The controller that will be analyzed.
	 * @return
	 * A map containing the URLs inside the controller and a pair that references
	 * the class itself and the method that controlls that URL.
	 * 
	 */
	private Map<String, Map<String, Pair<Class<?>,Method>>> mapController(Class<?> clazz){
		List<Method> pathMethods = getPathMethods(clazz);
		Map<String, Map<String, Pair<Class<?>,Method>>> map = new HashMap<>();
		
		pathMethods
		.stream()
		.forEach(
			m -> {
				String path = getValue(m);
				String method = controllerAnnotation(clazz, m.getAnnotation(Path.class).method());
				Map<String, Pair<Class<?>,Method>> temp = map.containsKey(path) ? map.get(path) : new HashMap<>();
				temp.put(method, new Pair<Class<?>,Method>(clazz,m));
				map.put(getValue(m), temp);
			}
		);
		
		return map;
	}
	
	private String controllerAnnotation(Class<?> clazz, String method){
		if(clazz.isAnnotationPresent(Controller.class))
			return "GET";
		return method;
	}
	
	/**
	 * 
	 * Given a path method, this class will extract the URL from the annotation.
	 * @param method
	 * A path method.
	 * @return
	 * The URL that is linked to the method.
	 * 
	 */
	private String getValue(Method method) {
		String path = method.getAnnotation(Path.class).value();
		return path.startsWith("/") ? path : "/" + path;
	}

	/**
	 * 
	 * Iterates all the class methods looking for the path ones and returning
	 * them as a list.
	 * @param clazz
	 * The controller class that will be analyzed.
	 * @return
	 * A list of path methods.
	 * 
	 */
	private List<Method> getPathMethods(Class<?> clazz) {
		return Arrays
				.stream(clazz.getMethods())
				.filter(m -> m.isAnnotationPresent(Path.class))
				.collect(Collectors.toList());
	}
	
}
