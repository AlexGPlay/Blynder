package atrahasis.core.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import atrahasis.core.annotations.ApiController;
import atrahasis.core.annotations.Bean;
import atrahasis.core.annotations.Controller;
import atrahasis.core.annotations.Filter;
import atrahasis.core.annotations.Service;
import atrahasis.data.annotations.Repository;

/**
 * 
 * A data class that contains all the built-in beans.
 *
 */
public class BeanAnnotations {

	/**
	 * 
	 * A method that builds a list with all the beans of the project for their
	 * later use.
	 * @return
	 * A list that contains all the beans classes.
	 * 
	 */
	public static List<Class<? extends Annotation>> getBeans(){
		List<Class<? extends Annotation>> beans = new ArrayList<>();
		
		beans.add(Controller.class);
		beans.add(Bean.class);
		beans.add(Filter.class);
		beans.add(Service.class);
		beans.add(Repository.class);
		beans.add(ApiController.class);
		
		return beans;
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
			if(getStorableBeans().contains(annotation.annotationType()))
				return true;
		}
		return false;
	}
	
	/**
	 * 
	 * Creates a list that contains all of the storable classes.
	 * 
	 * @return
	 * A list that contaisn all the storable classes.
	 * 
	 */
	private static List<Class<? extends Annotation>> getStorableBeans(){
		List<Class<? extends Annotation>> beans = new ArrayList<>();
		
		beans.add(Repository.class);
		
		return beans;
	}
	
}
