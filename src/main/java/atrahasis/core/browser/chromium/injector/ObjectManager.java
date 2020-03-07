package atrahasis.core.browser.chromium.injector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.HashMap;

public class ObjectManager {

	private URLClassLoader loader;
	private HashMap<Class<?>, Object> instances = new HashMap<>();
	
	public ObjectManager(URLClassLoader loader) {
		this.loader = loader;
	}
	
	public Object getInstanceForClass(Class<?> clazz) {
		return instances.get(clazz);
	}
	
	public Class<?> getClassWithoutInstance(String clazz){
		Class<?> app;
		try {
			app = Class.forName(clazz, true, loader);
			return app;
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public Class<?> getClass(String clazz) {
		Class<?> app;
		try {
			app = Class.forName(clazz, true, loader);
			instances.put(app, app.newInstance());
			return app;
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public Class<?> getClass(String clazz, String obClass, String method, Class<?> instance) {
		Class<?> app;
		try {
			app = Class.forName(clazz, true, loader);
			Method instanceMethod = app.getDeclaredMethod(method);
			Object o = instanceMethod.invoke(instances.get(instance));
			
			Class<?> fClass = clazz.equals(obClass) ? app : Class.forName(obClass, true, loader);
			
			instances.put(fClass, o);
			
			return fClass;
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public Class<?> getClass(String clazz, String obClass, String method, Class<?> instance, Class<?>[] paramTypes, Object[] args) {
		Class<?> app;
		try {
			app = Class.forName(clazz, true, loader);
			Method instanceMethod = app.getDeclaredMethod(method, paramTypes);
			Object o = instanceMethod.invoke(instances.get(instance), args);
			
			Class<?> fClass = clazz.equals(obClass) ? app : Class.forName(obClass, true, loader);
			
			instances.put(fClass, o);
			
			return fClass;
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public Object invokeMethodForClass(Class<?> clazz, String method, Object[] args, Class<?>[] paramTypes) {
		try {
			Method instanceMethod = clazz.getDeclaredMethod(method, paramTypes);
			Object o = instanceMethod.invoke(instances.get(clazz), args);
			
			return o;
		} 
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
}
