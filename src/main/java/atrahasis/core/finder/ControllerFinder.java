package atrahasis.core.finder;

import java.util.List;
import java.util.stream.Collectors;

import atrahasis.core.annotations.Controller;

public class ControllerFinder implements IControllerFinder{

	public List<Class<?>> findControllers(List<Class<?>> classes){
		return classes
		.stream()
		.filter( this::isController )
		.collect( Collectors.toList() );
	}
	
	private boolean isController(Class<?> clazz) {
		return clazz.isAnnotationPresent(Controller.class);
	}
	
}
