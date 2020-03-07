package atrahasis.core.finder;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

import atrahasis.core.util.BeanAnnotations;

/**
 * 
 * The beanFinder class is one of the main processes that will be executed
 * when the framework is loaded. This process indexes all the classes that
 * are annotated with the bean annotation or a bean annotation itself.
 *
 */
public class BeanFinder implements IBeanFinder{

	public List<Class<?>> findBeans(List<Class<?>> clazz){
		return clazz
				.stream()
				.filter( this::isBean )
				.collect(Collectors.toList());
	}
	
	/**
	 * 
	 * Helper method that given a class checks if it is a bean or not.
	 * @param clazz
	 * 	Is the class that will be checked.
	 * @return
	 * 	True if the class is a bean.<br>
	 * False if the class is not a bean.
	 */
	private boolean isBean(Class<?> clazz) {
		List<Class<? extends Annotation>> beans = BeanAnnotations.getBeans();
		
		for(Class<? extends Annotation> ann : beans)
			if(clazz.isAnnotationPresent(ann))
				return true;
		
		return false;
	}
	
}
