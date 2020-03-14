package atrahasis.core;

import atrahasis.core.configurator.BasicConfigurator;
import atrahasis.core.configurator.IConfigurator;
import atrahasis.core.exception.MapApplicationException;
import atrahasis.core.manager.ApplicationManager;

/**
 * 
 * Application is the facade that the user will use to interact with the
 * framework. This facade has a direct communication with the ApplicationManager
 * instance, this instance is the one that will do all the heavy work.
 * 
 */
public class Application{
	
	public static void launchApp() {
		Application.launchApp(new BasicConfigurator());
	}
	
	public static void launchApp(IConfigurator configurator) {
		try {
			ApplicationManager.initializeInstance(configurator);
		} catch (MapApplicationException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void navigate(String url) {
		ApplicationManager.getInstance().navigate(url);
	}
	
	public void set(String name, Object value) {
		ApplicationManager.getInstance().put(name, value);
	}
	
	public Object get(String name) {
		return ApplicationManager.getInstance().get(name);
	}
	
	public Object remove(String name) {
		return ApplicationManager.getInstance().remove(name);
	}
	
	public boolean hasKey(String key) {
		return ApplicationManager.getInstance().containsKey(key);
	}

	
}
