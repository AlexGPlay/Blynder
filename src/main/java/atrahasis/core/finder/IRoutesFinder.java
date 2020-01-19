package atrahasis.core.finder;

import java.lang.reflect.Method;
import java.util.Map;

import atrahasis.core.util.Pair;

/**
 * 
 * The IRoutesFinder interface class provides an interface for one of the 
 * main process of the framework. This class will map a given url to an existing
 * project url and map the params. For example, given the url "/test/3" it
 * will map it to the url "/test/{id}" and map the param 3 to the variable id.
 *
 */
public interface IRoutesFinder {

	/**
	 * 
	 * This method will map an existing project url to the url the user
	 * wants to navigate. It will also map the variables given in the url
	 * to the variables of the controller method that hosts that url.
	 * For example, if the user wants to navigate to "/test/3" and there is
	 * one controller that hosts "/test/{id}" this method will map the
	 * petition to that controller and map 3 to the id parameter.
	 * @param routes
	 * The routes that are hosted in the project. This hash is formed with a 
	 * string that contains the base route and the pair of it is the class
	 * that hosts the controller and the method that hosts the URL.
	 * An example of this could be that there is a URL called "/test/{id}" and
	 * its pair can be the TestController class and the TestMethod.
	 * @param url
	 * The URL that the user wants to go to.
	 * @return
	 * Returns a pair of base URL - Variable data.
	 * For example, if the user wants to navigate to "/test/3" and there is
	 * a route called "/test/{id}" this will return a pair containing the
	 * following data:<br>
	 * Pair.object1 = "/test/{id}"<br>
	 * Pair.object2 = Map => {<br>
	 * 	"id" => 3		<br>
	 * }
	 * 	
	 */
	public Pair<String, Map<String,Object>> findRoute(Map<String, Pair<Class<?>,Method>> routes, String url);
	
}
