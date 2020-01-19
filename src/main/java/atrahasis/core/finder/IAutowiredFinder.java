package atrahasis.core.finder;

import java.lang.reflect.Field;
import java.util.List;

import atrahasis.core.util.Pair;

/**
 * 
 * The Autowired Finder Interface provides a way to create new autowired finder
 * of your own, meaning that any class that implements this interface can be
 * used with a configurator to create a new way of finding fields marked as
 * "autowired".  
 *
 */
public interface IAutowiredFinder {

	/**
	 * 
	 * Given a list of classes, this method iterates all the classes in order
	 * to find all the autowired annotations, returning a list of the class
	 * where this field was found and the field itself.
	 * This process is used so later in the application initialization process
	 * it is possible assign an instance to the autowired fields.
	 * @param classes are the classes of the project which will be iterated looking for
	 * autowired fields.
	 * @return A list of pairs, each pair is made of a field annotated as autowired and 
	 * the class that contains this field.
	 * 
	 */
	public List<Pair<Class<?>,Field>> findAutowired(List<Class<?>> classes);
	
}
