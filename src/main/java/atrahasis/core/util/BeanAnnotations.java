package atrahasis.core.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import atrahasis.core.annotations.Bean;
import atrahasis.core.annotations.Controller;

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
		
		return beans;
	}
	
}
