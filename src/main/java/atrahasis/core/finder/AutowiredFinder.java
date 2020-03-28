package atrahasis.core.finder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import atrahasis.core.util.Pair;
import atrahasis.core.annotations.Autowired;

/**
 * 
 * The AutowiredFinder class is the built-in implementation if the AutowiredFinder
 * interface, making it possible to use the framework autowired function
 * without creating a user own implementation.
 *
 */
public class AutowiredFinder implements IAutowiredFinder{

	public List<Pair<Class<?>,Field>> findAutowired(List<Class<?>> classes){
		List<Pair<Class<?>,Field>> fields = new ArrayList<>();
		
		for(Class<?> clazz : classes)
			fields.addAll(findAutowiredFields(clazz));
		
		return fields;
	}
	
	/**
	 * 
	 * Given a class, this method looks for autowired fields in all the class fields.
	 * This private method is used only for a class in order to make the process easier and
	 * more readable.
	 * @param clazz is the class which will be analyzed looking for autowired fields.
	 * @return A list composed of pairs made of the current class and its autowired fields.
	 * 
	 */
	private List<Pair<Class<?>,Field>> findAutowiredFields(Class<?> clazz){
		List<Field> allFields = new ArrayList<Field>( Arrays.asList( clazz.getFields() ) );
		
		for(Field field : clazz.getDeclaredFields())
			if(!allFields.contains(field))
				allFields.add(field);
		
		return allFields
				.stream()
				.filter(this::isAutowired)
				.map(s -> new Pair<Class<?>,Field>(clazz,s))
				.collect(Collectors.toList());
	}
	
	/**
	 * 
	 * A boolean method used to encapsulate the Autowired finding condition.
	 * @param field which will be tested.
	 * @return <b>True</b> if the field is autowired annotated or <b>False</b> if not.
	 * 
	 */
	private boolean isAutowired(Field field) {
		return field.isAnnotationPresent(Autowired.class);
	}
	
}
