package atrahasis.core;

import atrahasis.core.configurator.BasicConfigurator;
import atrahasis.core.configurator.IConfigurator;
import atrahasis.core.exception.MapApplicationException;
import atrahasis.core.manager.ApplicationManager;
import atrahasis.core.network.Request;
import atrahasis.core.network.Response;

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
			ApplicationManager.getInstance().navigate("/");
		} catch (MapApplicationException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static Response navigate(String url) {
		return ApplicationManager.getInstance().navigate(url);
	}
	
	public static Response navigate(String url, Request request) {
		return ApplicationManager.getInstance().navigate(url, request);
	}
	
	public static void set(String name, Object value) {
		ApplicationManager.getInstance().put(name, value);
	}
	
	public static Object get(String name) {
		return ApplicationManager.getInstance().get(name);
	}
	
	public static Object remove(String name) {
		return ApplicationManager.getInstance().remove(name);
	}
	
	public static boolean hasKey(String key) {
		return ApplicationManager.getInstance().containsKey(key);
	}

	
}
