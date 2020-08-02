package org.blynder.core.manager.navigation;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.blynder.core.annotations.Controller;
import org.blynder.core.annotations.RestController;
import org.blynder.core.finder.IRoutesFinder;
import org.blynder.core.manager.security.FilterManager;
import org.blynder.core.network.Request;
import org.blynder.core.network.Response;
import org.blynder.core.template.Model;
import org.blynder.core.util.Pair;
import org.blynder.core.util.ParamSorter;
import org.blynder.core.util.QueryParamsExtractor;
import org.blynder.core.util.SystemInstanceManager;
import org.blynder.core.view.WindowSize;

import javafx.scene.Scene;

/**
 * 
 * The NavigationManager is the responsible for routing URLs into their controllers
 * and returning the response of the controller to the application manager.
 *
 */
public class NavigationManager {

	/**
	 * The url that the user wants to navigate to.
	 */
	private String url;
	
	/**
	 * The RoutesFinder implementation that will be used to map the route.
	 */
	private IRoutesFinder finder;
	
	/**
	 * A map that contains all the routes of the application.
	 */
	private Map<String, Map<String, Pair<Class<?>,Method>>> routes;
	
	/**
	 * The filter manager instance that will filter the request if needed.
	 */
	private FilterManager filterManager;
	
	/**
	 * The request itself, it will be null if doesn't come from an ajax.
	 */
	private Request request;

	public NavigationManager(
			String url, 
			IRoutesFinder finder, 
			Map<String, Map<String, Pair<Class<?>,Method>>> routes, 
			FilterManager filterManager,
			Request request
	) {
		this.url = url;
		this.finder = finder;
		this.routes = routes;
		this.filterManager = filterManager;
		this.request = request == null ? new Request(url) : request;
	}

	/**
	 * 
	 * Class entry point. This method will invoke the main processes in order
	 * to navigate through the application. The steps that it will follow are:<br>
	 * 1. Extract the URL segments.<br>
	 * 2. Get the class-method pair that handles the URL with the request method.<br>
	 * 3. Extract the query params.<br>
	 * 4. Try to invoke the controller invoking the filters first if they exist.<br>
	 * 
	 * @return
	 * Pair<Response,Model>:<br>
	 * Response - The response that the controller gives back.<br>
	 * Model - The model that the controller fills if there is one.
	 */
	public Object[] call() {
		
		if(url == null || finder == null || routes == null || filterManager == null)
			return new Object[] {new Response().statusCode(404), new Model(), new WindowSize()};
		
		Pair<String, Map<String,Object>> data = getUrlData();
		
		if(data == null)
			return new Object[] {new Response().statusCode(404), new Model(), new WindowSize()};
		
		Pair<Class<?>, Method> controller = getController(data.object1, request.getMethod());
		
		if(controller == null)
			return new Object[] {new Response().statusCode(404), new Model(), new WindowSize()};

		Class<?> clazz = controller.object1;
		Method method = controller.object2;

		Map<String,Object> params = data.object2;
		Map<String,Object> queryParams = new QueryParamsExtractor(url).extract();
		params.putAll(queryParams);
		
		return invokeRoutingMethods(clazz, method, params);
	}

	/**
	 * 
	 * Extracts the URL params and returns the base url and the params.
	 * 
	 */
	private Pair<String, Map<String,Object>> getUrlData(){
		return finder.findRoute(routes, url, request.getMethod());
	}


	/**
	 * 
	 * Returns the pair class-method where this path will be handled.
	 * 
	 */
	private Pair<Class<?>, Method> getController(String path, String method){
		Map<String, Pair<Class<?>, Method>> pathMethods = routes.get(path);
		if(pathMethods == null)
			return null;
		
		return pathMethods.get(method);
	}
	

	/**
	 * 
	 * This method invokes the filter and the controller that are in charge of
	 * the URL invoked by the user.
	 *
	 */
	private Object[] invokeRoutingMethods(Class<?> controllerClass, Method controllerMethod, Map<String,Object> controllerParams) {
		Request request = this.request == null ? new Request(url, "GET", new HashMap<>(), controllerParams, new HashMap<>()) : this.request;
		Response response = new Response();
		Model model = new Model();
		WindowSize size = new WindowSize();
		
		response = invokeFilter(controllerClass, request, response);
		if(!response.isAbleToContinue()) 
			return new Object[] { response, model,size };
		
		response = invokeController(controllerClass, controllerMethod, controllerParams, model, request, response, size);
		response = insertResponseType(controllerClass, response);
		return new Object[] { response, model,size };
	}
	
	/**
	 * 
	 * Given the response that the controller handled, this method will insert
	 * the response type. The tyes that the application can handle right now are:
	 * - Api<br>
	 * - HTML<br>
	 * - FXML<br>
	 * - JavaFX (Scene)<br>
	 * - Swing (Component)<br>
	 * @param controller that handled the request.
	 * @param response that is returned by the controller.
	 * @return
	 * The response with the responseType field filled.
	 * 
	 */
	private Response insertResponseType(Class<?> controller, Response response) {
		if(response.getRedirect() != null)
			return response.responseType("application/redirect");
		
		if(response.getResponse() == null)
			return response.statusCode(500);
		
		if(controller.isAnnotationPresent(RestController.class))
			return response.responseType("application/api");
		
		else if(controller.isAnnotationPresent(Controller.class)) {
			if(response.getResponse() instanceof String) {
				String viewName = (String)response.getResponse();
				if(viewName.contains(".html"))
					return response.responseType("application/html");
				else if(viewName.contains(".fxml"))
					return response.responseType("application/fxml");
			}
			else if(response.getResponse() instanceof Scene) {
				return response.responseType("application/javafx");
			}
			
			else if(response.getResponse() instanceof Component) {
				return response.responseType("application/swing");
			}
		}
		
		return response.statusCode(500);
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
										Response response,
										WindowSize size
	) {
		List<Object> dataParams = new ParamSorter().sortParameters(controllerParams, controllerMethod, model, request, response, size);
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
			return controllerMethod.invoke( SystemInstanceManager.lookForInstance(controllerClass), dataParams.toArray() );
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
