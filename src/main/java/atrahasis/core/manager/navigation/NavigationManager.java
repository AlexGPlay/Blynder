package atrahasis.core.manager.navigation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atrahasis.core.finder.IRoutesFinder;
import atrahasis.core.manager.security.FilterManager;
import atrahasis.core.network.Request;
import atrahasis.core.network.Response;
import atrahasis.core.template.Model;
import atrahasis.core.util.BeanInstanceManager;
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
	private FilterManager filterManager;

	public NavigationManager(String url, IRoutesFinder finder, Map<String, Pair<Class<?>,Method>> routes, FilterManager filterManager) {
		this.url = url;
		this.finder = finder;
		this.routes = routes;
		this.filterManager = filterManager;
	}

	public Pair<Response, Model> call() {
		Pair<String, Map<String,Object>> data = getUrlData();
		Pair<Class<?>, Method> controller = getController(data.object1);

		Class<?> clazz = controller.object1;
		Method method = controller.object2;

		Map<String,Object> params = data.object2;
		
		return invokeRoutingMethods(clazz, method, params);
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
	 * This method invokes the filter and the controller that are in charge of
	 * the URL invoked by the user.
	 *
	 */
	private Pair<Response, Model> invokeRoutingMethods(Class<?> controllerClass, Method controllerMethod, Map<String,Object> controllerParams) {
		Request request = new Request(url, "GET", new HashMap<>(), controllerParams, new HashMap<>());
		Response response = new Response();
		Model model = new Model();
		
		response = invokeFilter(controllerClass, request, response);
		if(!response.isAbleToContinue()) 
			return new Pair<Response,Model>(response, model);
		
		response = invokeController(controllerClass, controllerMethod, controllerParams, model, request, response);
		return new Pair<Response,Model>(response, model);
	}
	
	/**
	 * 
	 * Invokes the filterManager with the url controller, the given request and the base response.
	 * 
	 * @param controllerClass that is routing the url
	 * @param request sent by the user
	 * @param response that will be managed
	 * @return response -> <br>
	 * 	The continue flag will be true if the request passes the filters<br>
	 * 	The continue flag will be false if the request didn't pass the filters
	 * 	and there will be a redirect or render to manage the request.
	 * 
	 */
	private Response invokeFilter(Class<?> controllerClass, Request request, Response response) {
		return filterManager.call(controllerClass, request, response);
	}
	
	/**
	 * 
	 *	Given the controller class, method and params, this method will return
	 *	the user actions. This actions will be the response the controller managed
	 *	and the model the user wants to render.
	 *
	 */
	private Response invokeController(Class<?> controllerClass, 
										Method controllerMethod, 
										Map<String,Object> controllerParams, 
										Model model,
										Request request, 
										Response response
	) {
		List<Object> dataParams = new ParamSorter().sortParameters(controllerParams, controllerMethod, model, request, response);
		Object responseObject = invokeMethod(controllerClass, controllerMethod, dataParams);
		return getValidResponse(responseObject, response);
	}
	
	/**
	 * 
	 * Method wrapper for the invoke reflection method in order to manage the
	 * invoke exceptions here.
	 * 
	 */
	private Object invokeMethod(Class<?> controllerClass, Method controllerMethod, List<Object> dataParams) {
		try {
			return controllerMethod.invoke( BeanInstanceManager.lookForInstance(controllerClass), dataParams.toArray() );
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
