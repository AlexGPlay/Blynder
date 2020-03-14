package atrahasis.core.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atrahasis.core.annotations.Filter;
import atrahasis.core.annotations.FilterWith;

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
	
	private List<Class<?>> getFilters(Class<?> controller, List<Class<?>> filters){
		List<Class<?>> controllerFilters = new ArrayList<>();
		FilterWith annotation = controller.getAnnotation(FilterWith.class);
		
		controllerFilters.addAll( getClassesFromFilterAsClass(annotation.filtersAsClasses()) );
		controllerFilters.addAll( getClassesFromFilterAsString(annotation.filtersAsString(), filters) );
		
		return controllerFilters;
	}
	
	private List<Class<?>> getClassesFromFilterAsClass(Class<?>[] controllerFilters){
		List<Class<?>> resultFilters = new ArrayList<>();
		
		for(Class<?> controllerFilter : controllerFilters) {
			if(!isFilter(controllerFilter))
				continue;
			
			resultFilters.add(controllerFilter);
		}
		
		return resultFilters;
	}
	
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
	
	private Class<?> getFilterFromString(String controllerFilterAsString, List<Class<?>> filters) {
		for(Class<?> filter : filters) {
			if(filter.getName().contains(controllerFilterAsString))
				return filter;
		}
		
		return null;
	}

	private boolean isFiltered(Class<?> controller) {
		return controller.isAnnotationPresent(FilterWith.class);
	}
	
	private boolean isFilter(Class<?> filter) {
		return filter.isAnnotationPresent(Filter.class);
	}

}
