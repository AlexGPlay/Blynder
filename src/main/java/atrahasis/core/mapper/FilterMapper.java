package atrahasis.core.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atrahasis.core.annotations.Filter;
import atrahasis.core.annotations.FilterWith;

/**
 * 
 * This class is the built-in filter mapper. The process that is followed for the
 * mapping is: <br>
 * 1. Iterate all the controllers.<br>
 * 2. If the controller doesn't have the filterWith annotation it will be skipped and
 * won't have an entry in the map.<br>
 * 3. In the other hand, if the controller does have the annotation, an entry will be
 * created with a list of the filters.
 *
 */
public class FilterMapper implements IFilterMapper{

	@Override
	public Map<Class<?>, List<Class<?>>> map(List<Class<?>> controllers, List<Class<?>> filters) {
		Map<Class<?>, List<Class<?>>> mapping = new HashMap<>();
		
		for(Class<?> controller : controllers) {
			if(!isFiltered(controller))
				continue;
			
			mapping.put(controller, getFilters(controller, filters));
		}
		
		return mapping;
	}
	
	/**
	 * 
	 * Given a controller and the filters of the application, this method
	 * will help itself with another two methods that extracts the classes
	 * from the filterWith annotation and add all of them in a list.
	 * This list is the one that holds the filters that needs to be executed
	 * for the controller.
	 * 
	 * @param controller that will be filtered.
	 * @param filters of the application
	 * @return
	 * A list with all the filters that apply to the given controller.
	 * 
	 */
	private List<Class<?>> getFilters(Class<?> controller, List<Class<?>> filters){
		List<Class<?>> controllerFilters = new ArrayList<>();
		FilterWith annotation = controller.getAnnotation(FilterWith.class);
		
		controllerFilters.addAll( getClassesFromFilterAsClass(annotation.filtersAsClasses()) );
		
		for(Class<?> clazz : getClassesFromFilterAsString(annotation.filtersAsString(), filters) )
			if(!controllerFilters.contains(clazz))
				controllerFilters.add(clazz);
		
		return controllerFilters;
	}
	
	/**
	 * 
	 * Given an array of classes, this method will check if the classes are all
	 * filters and then add them to the list.
	 * 
	 * @param controllerFilters is an array of classes that holds the classes
	 * that will filter the controller.
	 * @return
	 * A list that holds the filters that will apply to a given controller.
	 * 
	 */
	private List<Class<?>> getClassesFromFilterAsClass(Class<?>[] controllerFilters){
		List<Class<?>> resultFilters = new ArrayList<>();
		
		for(Class<?> controllerFilter : controllerFilters) {
			if(!isFilter(controllerFilter))
				continue;
			
			resultFilters.add(controllerFilter);
		}
		
		return resultFilters;
	}
	
	/**
	 * 
	 * Given an array of strings, this method will cast the strings to the class
	 * they refer to. After that, this method will check if the class is a filter
	 * and if it is a filter they will be added to the list.
	 * 
	 * @param controllerFilters is an array of strings that holds the classes
	 * that will filter the controller.
	 * @return
	 * A list that holds the filters that will apply to a given controller.
	 * 
	 */
	private List<Class<?>> getClassesFromFilterAsString(String[] controllerFilters, List<Class<?>> filters){
		List<Class<?>> resultFilters = new ArrayList<>();
		
		for(String controllerFilterAsString : controllerFilters) {
			Class<?> controllerFilter = getFilterFromString(controllerFilterAsString, filters);
			if(controllerFilter == null || !isFilter(controllerFilter))
				continue;
			
			resultFilters.add(controllerFilter);
		}
		
		return resultFilters;
	}
	
	/**
	 * 
	 * Given the string name and all the filters of the application, this method
	 * will try to map the name to a given filter and return it.
	 * 
	 * @param controllerFilterAsString is the name of the filter as string.
	 * @param filters list that holds all the filters of the app.
	 * @return
	 * The class the filter refers to.
	 * 
	 */
	private Class<?> getFilterFromString(String controllerFilterAsString, List<Class<?>> filters) {
		for(Class<?> filter : filters) {
			if(filter.getName().contains(controllerFilterAsString))
				return filter;
		}
		
		return null;
	}

	/**
	 * 
	 * Checks if the controller is filtered.
	 * 
	 * @param controller that will be checked.
	 * @return 
	 * True if the controller is filtered.<br>
	 * False if the controller isn't filtered.
	 * 
	 */
	private boolean isFiltered(Class<?> controller) {
		return controller.isAnnotationPresent(FilterWith.class);
	}
	
	/**
	 * 
	 * Checks if the class is a filter.
	 * 
	 * @param filter that will be checked.
	 * @return 
	 * True if the class is a filter.<br>
	 * False if the class isn't a filter.
	 * 
	 */
	private boolean isFilter(Class<?> filter) {
		return filter.isAnnotationPresent(Filter.class);
	}

}
