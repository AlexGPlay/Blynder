package org.blynder.core.finder;

import java.util.List;

/**
 * 
 * ISystemFinder provides an interface that can be used by the framework via
 * configurator. 
 * The main purpose of the class it to filter all the project classes that
 * are annotated and returning them, the bean classes can be found in the SystemAnnotations
 * helper class.
 *
 */
public interface ISystemFinder {

	/**
	 * 
	 * The findClasses method filters all the classes that a project has and 
	 * returning only the ones that are custom annotated, those classes can be found
	 * in the SystemAnnotations helper class.
	 * @param clazz
	 * 	Is the list of all the classes that the project has.
	 * @returns
	 * 	A list that only has the classes that are annotated with custom annotations.
	 * 
	 */
	public List<Class<?>> findClasses(List<Class<?>> clazz);
	
}
