package swingmvc.finder;

import java.util.List;
import java.util.stream.Collectors;

import swingmvc.annotations.Controller;

public class ControllerFinder {

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
