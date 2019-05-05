package swingmvc.finder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import swingmvc.annotations.Autowired;
import swingmvc.util.Pair;

public class AutowiredFinder {

	public List<Pair<Class<?>,Field>> findAutowired(List<Class<?>> classes){
		List<Pair<Class<?>,Field>> fields = new ArrayList<>();
		
		for(Class<?> clazz : classes)
			fields.addAll(findAutowiredFields(clazz));
		
		return fields;
	}
	
	private List<Pair<Class<?>,Field>> findAutowiredFields(Class<?> clazz){
		List<Field> allFields = new ArrayList<Field>( Arrays.asList( clazz.getFields() ) );
		allFields.addAll( Arrays.asList( clazz.getDeclaredFields() ) );
		
		return allFields
				.stream()
				.filter(this::isAutowired)
				.map(s -> new Pair<Class<?>,Field>(clazz,s))
				.collect(Collectors.toList());
	}
	
	private boolean isAutowired(Field field) {
		return field.isAnnotationPresent(Autowired.class);
	}
	
}
