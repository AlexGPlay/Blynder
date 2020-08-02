package org.blynder.core.finder;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

import org.blynder.core.util.SystemAnnotations;

/**
 * 
 * The SystemFinder class is one of the main processes that will be executed
 * when the framework is loaded. This process indexes all the classes that
 * are annotated with a custom annotation.
 *
 */
public class SystemFinder implements ISystemFinder{

	public List<Class<?>> findClasses(List<Class<?>> clazz){
		return clazz
				.stream()
				.filter( this::isSystemCustomClass )
				.collect(Collectors.toList());
	}
	
	/**
	 * 
	 * Helper method that given a class checks if it is a custom class or not.
	 * @param clazz
	 * 	Is the class that will be checked.
	 * @return
	 * 	True if the class is a custom annotated class.<br>
	 * False if the class is not a custom annotated class.
	 */
	private boolean isSystemCustomClass(Class<?> clazz) {
		List<Class<? extends Annotation>> annotations = SystemAnnotations.getSystemAnnotations();
		
		for(Class<? extends Annotation> ann : annotations)
			if(clazz.isAnnotationPresent(ann))
				return true;
		
		return false;
	}
	
}
