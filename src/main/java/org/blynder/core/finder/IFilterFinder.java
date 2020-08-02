package org.blynder.core.finder;

import java.util.List;

/**
 * 
 * This class provides an interface that includes the signature for a method
 * that receives a list of classes and filters them to a list that only
 * contains filters. The classes that uses this interface will supply
 * one of the main processes of the framework.
 *
 */
public interface IFilterFinder {

	/**
	 * 
	 * This method is the responsible for filtering the project classes
	 * so the next framework can search for the filters and make the
	 * routing faster.
	 * It can be made searching for the classes that are annoted with the
	 * filter annotation.
	 * 
	 * @param classes
	 * A list that contains all the classes of the project.
	 * @return
	 * A list that contains all the filters of the project.
	 * 
	 */
	public List<Class<?>> findFilters(List<Class<?>> classes);
	
}
