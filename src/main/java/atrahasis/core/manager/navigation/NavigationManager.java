package atrahasis.core.manager.navigation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atrahasis.core.finder.IRoutesFinder;
import atrahasis.core.network.Request;
import atrahasis.core.network.Response;
import atrahasis.core.template.Model;
import atrahasis.core.util.InstanceSaver;
import atrahasis.core.util.Pair;
import atrahasis.core.util.ParamSorter;

/**
 * 
 * The NavigationManager is the responsible for routing URLs into their controllers
 * and returning the response of the controller to the application manager.
 *
 */
public class NavigationManager {

	private String url;
	private IRoutesFinder finder;
	private Map<String, Pair<Class<?>,Method>> routes;

	public NavigationManager(String url, IRoutesFinder finder, Map<String, Pair<Class<?>,Method>> routes) {
		this.url = url;
		this.finder = finder;
		this.routes = routes;
	}

	public Pair<Response, Model> call() {
		Pair<String, Map<String,Object>> data = getUrlData();
		Pair<Class<?>, Method> controller = getController(data.object1);

		Class<?> clazz = controller.object1;
		Method method = controller.object2;

		Map<String,Object> params = data.object2;

		return invokeController(clazz, method, params);
	}

	/**
	 * 
	 * Extracts the URL params and returns the base url and the params.
	 * 
	 */
	private Pair<String, Map<String,Object>> getUrlData(){
		return finder.findRoute(routes, url);
	}


	/**
	 * 
	 * Returns the pair class-method where this path will be handled.
	 * 
	 */
	private Pair<Class<?>, Method> getController(String path){
		return routes.get(path);
	}
	
	/**
	 * 
	 *	Given the controller class, method and params, this method will return
	 *	the user actions. This actions will be the response the controller managed
	 *	and the model the user wants to render.
	 *
	 */
	private Pair<Response, Model> invokeController(Class<?> controllerClass, Method controllerMethod, Map<String,Object> controllerParams) {
		Request request = new Request("GET", new HashMap<>(), controllerParams, new HashMap<>());
		Response response = new Response();
		Model model = new Model();
		
		List<Object> dataParams = new ParamSorter().sortParameters(controllerParams, controllerMethod, model, request, response);
		Object responseObject = invokeMethod(controllerClass, controllerMethod, dataParams);
		return new Pair<Response,Model>(getValidResponse(responseObject, response), model);
	}
	
	/**
	 * 
	 * Method wrapper for the invoke reflection method in order to manage the
	 * invoke exceptions here.
	 * 
	 */
	private Object invokeMethod(Class<?> controllerClass, Method controllerMethod, List<Object> dataParams) {
		try {
			return controllerMethod.invoke( InstanceSaver.lookForInstance(controllerClass), dataParams.toArray() );
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 *	There can be two responses from a controller method. These responses are
	 *	the one given via method params and the returned one. This method will
	 *	evaluate those responses and get one of them.
	 *	The returned one will be the prioritized one, if there is no returned one
	 *	the one chosen will be the params one and if there is no response, the default
	 *	one will be returned, the default one doesn't have redirect nor render.
	 *
	 */
	private Response getValidResponse(Object returnResponse, Response paramsResponse) {
		Response toReturn;
		
		if(returnResponse == null) {
			toReturn = paramsResponse;
		}
		else {
			if(!(returnResponse instanceof Response)) {
				throw new RuntimeException("Controller return type must be response or void");
			}
			
			toReturn = (Response)returnResponse;
		}
		
		return toReturn;
	}
	
}
