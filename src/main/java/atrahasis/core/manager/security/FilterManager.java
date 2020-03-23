package atrahasis.core.manager.security;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atrahasis.core.network.Request;
import atrahasis.core.network.Response;
import atrahasis.core.util.ParamSorter;

/**
 * 
 * The FilterManager class is in charge of executing the filters that are
 * filtering a controller.
 *
 */
public class FilterManager {
	
	/**
	 * 
	 * A map of controller-filters. It stores the filters that the controller
	 * needs to be executed before the routing method.
	 * 
	 */
	private Map<Class<?>, List<Class<?>>> controllerFilters;
	
	/**
	 * 
	 * A map of filter-methods. It stores the methods of the filter that will
	 * be executed.
	 * 
	 */
	private Map<Class<?>, List<Method>> filterMethods;
	
	public FilterManager(Map<Class<?>, List<Class<?>>> controllerFilters, List<Class<?>> filters) {
		this.controllerFilters = controllerFilters;
		filterMethods = extractMethodsFromClassList(filters);
	}
	
	//////////////////////////////////////////////////////////
	//														//
	//														//
	//					INIT METHODS						//
	//														//
	//														//
	//////////////////////////////////////////////////////////
	
	/**
	 * 
	 * Given the list of filters, this method will extract all the methods of the
	 * filters and store them in a map of Filter - Methods.
	 * 
	 * @param filters
	 * A list with all the filters of the application.
	 * @return
	 * A Map that contains the relation of Filter - Methods.
	 * 
	 */
	private Map<Class<?>, List<Method>> extractMethodsFromClassList(List<Class<?>> filters){
		Map<Class<?>, List<Method>> filterMethods = new HashMap<>();
		
		for(Class<?> filter : filters)
			filterMethods.put(filter, extractMethodsFromClass(filter));
		
		return filterMethods;
	}
	
	/**
	 * 
	 * Given a filter class this method will extract all its methods and
	 * return them in a list.
	 * 
	 * @param clazz
	 * The filter class.
	 * 
	 * @return
	 * The list of the methods.
	 * 
	 */
	private List<Method> extractMethodsFromClass(Class<?> clazz){
		return Arrays.asList(clazz.getDeclaredMethods());
	}
	
	//////////////////////////////////////////////////////////
	//														//
	//														//
	//					MANAGEMENT METHODS					//
	//														//
	//														//
	//////////////////////////////////////////////////////////
	
	/**
	 * 
	 * Entry point of the filter manager. It will check if the controller that was issued
	 * to answer the request is filtered or not. In case it isn't filtered the response
	 * will be returned as canContinue so the controller method can be executed. In the other hand,
	 * if the controller is filtered, the filter methods will be executed.
	 * 
	 * @param controller that will handle the request.
	 * @param request that was handled by the app.
	 * @param response that will be returned from the filter.
	 * @return
	 * The response allowing or not the continuation of the execution.
	 * 
	 */
	public Response call(Class<?> controller, Request request, Response response) {
		if(!isFiltered(controller)) 
			return response.canContinue(true);
		
		return executeFiltersFor(controller, request, response);
	}
	
	/**
	 * 
	 * Checks if the controller is filtered or not.
	 * 
	 * @param controller that will be checked if has any filter.
	 * @return 
	 * True if the controller is filtered.<br>
	 * False if the controller is not filtered.
	 * 
	 */
	private boolean isFiltered(Class<?> controller) {
		return controllerFilters.containsKey(controller);
	}
	
	/**
	 * 
	 * Issues the execution of the filters for the given controller. For that,
	 * the filter list for the class will be extracted for the map and after
	 * that the methods will be executed one by one. If any filters answers
	 * with can't continue, the response will be returned and the other
	 * filters won't be executed.
	 * 
	 * @param controller that handles the path.
	 * @param request that was handled by the app.
	 * @param response that has to be returned.
	 * @return
	 * The response setting the can or can't continue.
	 * 
	 */
	private Response executeFiltersFor(Class<?> controller, Request request, Response response) {
		List<Class<?>> filters = controllerFilters.get(controller);
		
		for(Class<?> filter : filters) {
			response = executeFilterMethods(filter, request, response);
			if(!response.isAbleToContinue())
				return response;
			
			response.canContinue(false);
		}
		
		return response.canContinue(true);
	}
	
	/**
	 * 
	 * One by one all the methods of the filter are executed. If any method answers
	 * with can't continue, the response will be returned and the other
	 * methods won't be executed.
	 * 
	 * @param filter that will be executed.
	 * @param request that was handled by the app.
	 * @param response that will be returned.
	 * @return
	 * The response setting the can or can't continue.
	 * 
	 */
	private Response executeFilterMethods(Class<?> filter, Request request, Response response) {
		List<Method> methods = filterMethods.get(filter);
		
		Object instance = createFilterInstance(filter);
		
		if(instance == null)
			return response;
		
		for(Method method : methods) {
			response = executeFilterMethod(instance, method, request, response);
			if(!response.isAbleToContinue())
				return response;
			
			response.canContinue(false);
		}
		
		return response.canContinue(true);
	}

	/**
	 * 
	 * Executes the method and checks if the return value is a response. In
	 * case it isn't, it will return the parameter response, this process
	 * is followed because the methods can return an empty value and set the
	 * values in the parameter response instead of returning it.
	 * 
	 * @param instance of the filter.
	 * @param method that will be executed.
	 * @param request that was handled by the app.
	 * @param response that will be returned.
	 * @return
	 * A response, it can be the parameter one or the return value of the function.
	 * 
	 */
	private Response executeFilterMethod(Object instance, Method method, Request request, Response response) {
		Object res = invokeFilterMethod(instance, method, request, response);
		if(!(res instanceof Response)) {
			return response;
		}
		return (Response) res;
	}
	
	/**
	 * 
	 * This method is executed instead of the invoke one so the second one can be
	 * wrapped. This method will execute the filter method with the asked params 
	 * and return the response from the params if there is no return or the returned
	 * one if it exists.
	 * 
	 * @param instance of the filter.
	 * @param method that will be executed.
	 * @param request that was handled by the app.
	 * @param response that will be returned.
	 * @return
	 * A response, it can be the parameter one or the return value of the function.
	 * 
	 */
	private Object invokeFilterMethod(Object instance, Method method, Request request, Response response) {
		try {
			List<Object> dataParams = new ParamSorter().sortParameters(new HashMap<>(), method, null, request, response);
			Object filterReturn = method.invoke(instance, dataParams.toArray());
			return filterReturn == null ? response : filterReturn;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return response;
		}
	}
	
	/**
	 * 
	 * Given the filter class, this method will try to create an instance of
	 * that filter with the blank constructor.
	 * 
	 * @param filter class that will be instantiated.
	 * @return The instance of the filter if it was possible to create or null
	 * in other case.
	 * 
	 */
	private Object createFilterInstance(Class<?> filter) {
		try {
			return filter.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}
	
}
