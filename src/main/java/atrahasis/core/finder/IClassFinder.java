package atrahasis.core.finder;

import java.util.List;

/**
 * 
 * This class provides an interface that can be used as the frameworks
 * class finder. The main purpose of the class is to find all the projects
 * classes and return them in a list. Any class that uses this interface
 * can be used in the configurator class so it overrides the built-in one.
 *
 */
public interface IClassFinder {

	/**
	 * 
	 * This method lists all the classes of the project.
	 * @return 
	 *  A list of the classes.
	 * @throws ClassNotFoundException
	 * If the class cannot find a previously indexed class in the projects
	 * folder.
	 * 
	 */
	public List<Class<?>> findClasses() throws ClassNotFoundException;
	
}
