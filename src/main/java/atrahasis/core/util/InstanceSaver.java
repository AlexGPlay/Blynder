package atrahasis.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * A class that allows the framework to save the autowired instances so the
 * framework can make for a better use of memory.
 *
 */
public class InstanceSaver {

	private static Map<Class<?>, Object> instances = new HashMap<>();
	
	/**
	 * 
	 * A method that looks for an instance of an autowired field, if it doesn't
	 * exist this method creates it.
	 * @param clazz
	 * The class that needs an instance.
	 * @return
	 * An instance of the class.
	 * @throws IllegalAccessException
	 * f this Field object is enforcing Java language access control and the 
	 * underlying field is either inaccessible or final.
	 * @throws InstantiationException
	 * if this Class represents an abstract class, an interface, an array class, 
	 * a primitive type, or void; or if the class has no nullary constructor; 
	 * or if the instantiation fails for some other reason.
	 * 
	 */
	public static Object lookForInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		Object o = instances.get(clazz);
		
		if(o == null) {
			o = clazz.newInstance();
			instances.put(clazz, o);
		}
		
		return o;
	}
	
}
