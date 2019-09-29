package atrahasis.core.finder;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

import atrahasis.core.util.BeanAnnotations;

public class BeanFinder {

	public List<Class<?>> findBeans(List<Class<?>> clazz){
		return clazz
				.stream()
				.filter( this::isBean )
				.collect(Collectors.toList());
	}
	
	private boolean isBean(Class<?> clazz) {
		List<Class<? extends Annotation>> beans = BeanAnnotations.getBeans();
		
		for(Class<? extends Annotation> ann : beans)
			if(clazz.isAnnotationPresent(ann))
				return true;
		
		return false;
	}
	
}
