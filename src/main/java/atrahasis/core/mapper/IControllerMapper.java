package atrahasis.core.mapper;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import atrahasis.core.util.Pair;

/**
 * 
 * This interface provides the signature for the controller mapping action.
 * This action will map the URLs of the project to the method an class
 * they belong to.
 * For example, if there is a controller called "TestController" with a route
 * method called "TestMethod" and the route is "/test" this will return an entry
 * in the hash such as:<br>
 * "/test" => <br>
 * Pair.object1: "TestController"<br>
 * Pair.object2: "TestMethod"<br>
 *
 */
public interface IControllerMapper {

	/**
	 * 
	 * map method will, as it name says, map the controllers of the project
	 * into its URL as key and its class and method as value.
	 * The incoming list will have all the controllers that were filtered
	 * in the other processes, meaning that all the classes here must be controllers.
	 * To map the class we will need to find methods that are annotated with the 
	 * path annotation, that annotation has an attribute called value which
	 * has the URL that has to be mapped to that method.
	 * @param classes
	 * A list of controllers.
	 * @return
	 * A map of URL into a pair of class - method.<br>
	 * For example, if there is a controller called "TestController" with a route
	 * method called "TestMethod" and the route is "/test" this will return an entry
	 * in the hash such as:<br>
	 * "/test" => <br>
	 * Pair.object1: "TestController"<br>
	 * Pair.object2: "TestMethod"<br>
	 * 
	 */
	public Map<String, Map<String, Pair<Class<?>,Method>>> map(List<Class<?>> classes);
	
}
