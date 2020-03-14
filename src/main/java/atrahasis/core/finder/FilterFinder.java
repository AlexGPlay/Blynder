package atrahasis.core.finder;

import java.util.List;
import java.util.stream.Collectors;

import atrahasis.core.annotations.Filter;

/**
 * 
 * This class is the built-in implementation of the IFilterFinder interface.
 * This class will provide the built-in working method that given the project
 * classes will return the project filters.
 *
 */
public class FilterFinder implements IFilterFinder{

	public List<Class<?>> findFilters(List<Class<?>> classes){
		return classes
		.stream()
		.filter( this::isController )
		.collect( Collectors.toList() );
	}
	
	/**
	 * 
	 * This method, given a project class will check if the class is annotated
	 * with the filter annotation or not.
	 * @param clazz
	 * The class that will be checked.
	 * @return
	 * True if the class is annotated.<br>
	 * False if the class is not annotated.
	 */
	private boolean isController(Class<?> clazz) {
		return clazz.isAnnotationPresent(Filter.class);
	}
	
}
