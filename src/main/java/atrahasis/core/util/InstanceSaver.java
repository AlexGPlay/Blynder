package atrahasis.core.util;

import java.util.HashMap;
import java.util.Map;

public class InstanceSaver {

	private static Map<Class<?>, Object> instances = new HashMap<>();
	
	public static Object lookForInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		Object o = instances.get(clazz);
		
		if(o == null) {
			o = clazz.newInstance();
			instances.put(clazz, o);
		}
		
		return o;
	}
	
}
