package org.blynder.core.mapper;

import java.lang.reflect.Field;
import java.util.List;

import org.blynder.core.util.Pair;

/**
 * 
 * IAutowiredMapper provides and interface that will allow the user to inject
 * a dependency into a variable annotated as autowired.
 * This interface allows the user to inherit it and override the built-in
 * class with a configurator.
 *
 */
public interface IAutowiredMapper {

	/**
	 * 
	 * Given the beans of the project and the a pair made of a field that
	 * is autowired and the class it belongs to, this method will inject
	 * a dependency into the class' field. Only the beans can be autowired,
	 * that is why only the beans are given as classes.
	 * @param classes
	 * A list of the project beans.
	 * @param fields
	 * A list of pairs that are class and field. <br>
	 * For more information go to IAutowiredMapper.
	 * @throws IllegalArgumentException
	 * if the specified object is not an instance of the class or interface 
	 * declaring the underlying field (or a subclass or implementor thereof), 
	 * or if an unwrapping conversion fails.
	 * @throws IllegalAccessException
	 * f this Field object is enforcing Java language access control and the 
	 * underlying field is either inaccessible or final.
	 * @throws InstantiationException
	 * if this Class represents an abstract class, an interface, an array class, 
	 * a primitive type, or void; or if the class has no nullary constructor; 
	 * or if the instantiation fails for some other reason.
	 * 
	 */
	public void mapAutowired(Object parentInstance, List<Class<?>> classes, List<Pair<Class<?>,Field>> fields) throws IllegalArgumentException, IllegalAccessException, InstantiationException;
	
}
