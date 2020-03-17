package atrahasis.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atrahasis.core.finder.IAutowiredFinder;
import atrahasis.core.mapper.IAutowiredMapper;
import atrahasis.data.http.HttpRepository;
import atrahasis.data.http.proxy.HttpProxyFactory;

/**
 * 
 * A class that allows the framework to save the autowired instances so the
 * framework can make for a better use of memory.
 *
 */
public class BeanInstanceManager {

	private static Map<Class<?>, Object> instances = new HashMap<>();
	private static List<Class<?>> beans;
	private static IAutowiredMapper mapper;
	private static IAutowiredFinder finder;
	
	public static void initInstanceSaver(List<Class<?>> beans, IAutowiredMapper mapper, IAutowiredFinder finder) {
		BeanInstanceManager.beans = beans;
		BeanInstanceManager.mapper = mapper;
		BeanInstanceManager.finder = finder;
	}
	
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
		return BeanAnnotations.isStorable(clazz) ? getStorableObject(clazz) : getUnstorableObject(clazz);
	}
	
	private static Object getStorableObject(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		Object o = instances.get(clazz);
		
		if(o == null) {
			o = instantiateObject(clazz);
			instances.put(clazz, o);
		}
		
		return o;
	}
	
	private static Object getUnstorableObject(Class<?> clazz) {
		return instantiateObject(clazz);
	}
	
	private static Object instantiateObject(Class<?> clazz) {
		try {
			Object sI = getSpecialInstance(clazz);
			Object o = sI == null ? clazz.newInstance() : sI;
			List<Class<?>> classes = new ArrayList<>();
			classes.add(clazz);
			
			mapper.mapAutowired(o, beans, finder.findAutowired(classes));
			return o;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Object getSpecialInstance(Class<?> clazz) {
		if(clazz.isInterface() && HttpRepository.class.isAssignableFrom(clazz)) {
			return new HttpProxyFactory(clazz).create();
		}
		
		return null;
	}
	
}
