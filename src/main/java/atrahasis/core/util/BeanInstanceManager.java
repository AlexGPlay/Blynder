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
	 * A methdo that creates the instances of the beans. It will store the 
	 * instance if it is an storable beans or in other hand if it isn't the instance
	 * will die once it is used.
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
	
	/**
	 * 
	 * Given a class that is storable, this method will check if an instance of that
	 * bean already exists and return it or it will instantiate the first instance of
	 * that bean and put it in the map for another use.
	 * 
	 * @param clazz which instance will be returned.
	 * @return An instance of the class.
	 *
	 */
	private static Object getStorableObject(Class<?> clazz) {
		Object o = instances.get(clazz);
		
		if(o == null) {
			o = instantiateObject(clazz);
			instances.put(clazz, o);
		}
		
		return o;
	}
	
	/**
	 * 
	 * Given a class that is not storable, this method will instantiate the given
	 * class and return that instance.
	 * 
	 * @param clazz which instance will be returned.
	 * @return An instance of the class.
	 *
	 */
	private static Object getUnstorableObject(Class<?> clazz) {
		return instantiateObject(clazz);
	}
	
	/**
	 * 
	 * This method creates the instance of the given class. The class can be
	 * an special instance. If the class is an special instance the instantiation
	 * will be created in another way, if it isn't an special instance it will
	 * be created using the default constructor. After creating the instance
	 * this method will call the autowired mapper and will map the autowired fields
	 * of the class.
	 * 
	 * @param clazz that will be instantiated.
	 * @return
	 * The instance of the object.
	 * 
	 */
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
	
	/**
	 * 
	 * This methods returns an instance of a class if it is an special class
	 * or null if it isn't. An special instance is for example, the HttpRepository,
	 * it needs an special instance because those classes are interfaces. The instance
	 * will be created from the HttpProxyFactory.
	 * 
	 * @param clazz that will be instantiated if it is specialInstance.
	 * @return
	 * The instance of the class if it special.<br>
	 * Null if it isn't special.
	 * 
	 */
	private static Object getSpecialInstance(Class<?> clazz) {
		if(clazz.isInterface() && HttpRepository.class.isAssignableFrom(clazz)) {
			return new HttpProxyFactory(clazz).create();
		}
		
		return null;
	}
	
}
