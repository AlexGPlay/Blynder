package atrahasis.core.finder;

import java.util.List;

/**
 * 
 * IBeanFinder provides an interface that can be used by the framework via
 * configurator. 
 * The main purpose of the class it to filter all the project classes that
 * are beans and returning them, the bean classes can be found in the BeanAnnotations
 * helper class.
 *
 */
public interface IBeanFinder {

	/**
	 * 
	 * The findBeans method filters all the classes that a project has and 
	 * returning only the ones that are beans, those classes can be found
	 * in the BeanAnnotations helper class.
	 * @param clazz
	 * 	Is the list of all the classes that the project has.
	 * @returns
	 * 	A list that only has the classes that are annotated with beans annotations.
	 * 
	 */
	public List<Class<?>> findBeans(List<Class<?>> clazz);
	
}
