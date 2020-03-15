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

public class FilterManager {
	
	private Map<Class<?>, List<Class<?>>> controllerFilters;
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
	
	private Map<Class<?>, List<Method>> extractMethodsFromClassList(List<Class<?>> filters){
		Map<Class<?>, List<Method>> filterMethods = new HashMap<>();
		
		for(Class<?> filter : filters)
			filterMethods.put(filter, extractMethodsFromClass(filter));
		
		return filterMethods;
	}
	
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
	
	public Response call(Class<?> controller, Request request, Response response) {
		if(!isFiltered(controller)) 
			return response.canContinue(true);
		
		return executeFiltersFor(controller, request, response);
	}
	
	private boolean isFiltered(Class<?> controller) {
		return controllerFilters.containsKey(controller);
	}
	
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

	private Response executeFilterMethod(Object instance, Method method, Request request, Response response) {
		Object res = invokeFilterMethod(instance, method, request, response);
		if(!(res instanceof Response)) {
			return response;
		}
		return (Response) res;
	}
	
	private Object invokeFilterMethod(Object instance, Method method, Request request, Response response) {
		try {
			List<Object> dataParams = new ParamSorter().sortParameters(new HashMap<>(), method, null, request, response);
			Object filterReturn = method.invoke(instance, dataParams.toArray());
			return filterReturn == null ? response : filterReturn;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return response;
		}
	}
	
	private Object createFilterInstance(Class<?> filter) {
		try {
			return filter.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}
	
}
