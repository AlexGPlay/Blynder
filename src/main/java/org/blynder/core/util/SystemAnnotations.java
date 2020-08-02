package org.blynder.core.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.blynder.core.annotations.Bean;
import org.blynder.core.annotations.Controller;
import org.blynder.core.annotations.Filter;
import org.blynder.core.annotations.RestController;
import org.blynder.core.annotations.Service;
import org.blynder.data.annotations.Repository;

/**
 * 
 * A data class that contains all the built-in annotations.
 *
 */
public class SystemAnnotations {

	/**
	 * 
	 * A method that builds a list with all the annotations of the project for their
	 * later use.
	 * @return
	 * A list that contains all the annotation classes.
	 * 
	 */
	public static List<Class<? extends Annotation>> getSystemAnnotations(){
		List<Class<? extends Annotation>> annotations = new ArrayList<>();
		
		annotations.add(Controller.class);
		annotations.add(Bean.class);
		annotations.add(Filter.class);
		annotations.add(Service.class);
		annotations.add(Repository.class);
		annotations.add(RestController.class);
		
		return annotations;
	}
	
	/**
	 * 
	 * Given an annotation class checks if the beans is storable. Storable
	 * beans are stored in memory since they won't need to be re-instantiated.
	 * For example, the repository interfaces doesn't hold any information so
	 * they are only instantiated once.
	 * 
	 * @param clazz that will be checked.
	 * @return
	 * True if it storable<br>
	 * False if it isn't.
	 * 
	 */
	public static boolean isStorable(Class<?> clazz) {
		Annotation[] classes = clazz.getAnnotations();
		for(Annotation annotation : classes) {
			if(getStorableAnnotations().contains(annotation.annotationType()))
				return true;
		}
		return false;
	}
	
	/**
	 * 
	 * Creates a list that contains all of the storable classes.
	 * 
	 * @return
	 * A list that contains all the storable classes.
	 * 
	 */
	private static List<Class<? extends Annotation>> getStorableAnnotations(){
		List<Class<? extends Annotation>> storableAnnotations = new ArrayList<>();
		
		storableAnnotations.add(Repository.class);
		
		return storableAnnotations;
	}
	
}
