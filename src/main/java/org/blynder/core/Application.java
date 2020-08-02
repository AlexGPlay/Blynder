package org.blynder.core;

import org.blynder.core.configurator.BasicConfigurator;
import org.blynder.core.configurator.IConfigurator;
import org.blynder.core.exception.MapApplicationException;
import org.blynder.core.logging.Logging;
import org.blynder.core.logging.LoggingSetter;
import org.blynder.core.manager.ApplicationManager;
import org.blynder.core.network.Request;
import org.blynder.core.network.Response;
import org.blynder.core.util.ArgsAnalyzer;

/**
 * 
 * Application is the facade that the user will use to interact with the
 * framework. This facade has a direct communication with the ApplicationManager
 * instance, this instance is the one that will do all the heavy work.
 * 
 */
public class Application{
	
	/**
	 * Method used to launch the application with a basic configuration, 
	 * meaning that all processes will be the default ones.
	 */
	public static void launchApp() {
		Application.launchApp(new BasicConfigurator());
	}
	
	/**
	 * Method used to launch the application with a basic configuration
	 * and passing arguments to it.
	 * @param args launch arguments.
	 */
	public static void launchApp(String[] args) {
		Application.launchApp(new BasicConfigurator(), args);
	}
	
	/**
	 * Method used to launch the application with a custom configuration.
	 * @param configurator that will be used.
	 */
	public static void launchApp(IConfigurator configurator) {
		Application.launchApp(configurator, new String[] {});
	}
	
	/**
	 * Method used to launch the application with a custom configuration and
	 * with arguments.
	 * @param configurator that will be used.
	 * @param args that will be read.
	 */
	public static void launchApp(IConfigurator configurator, String[] args) {
		try {
			new LoggingSetter().setLogging();
			new ArgsAnalyzer(args).execute();
			long init = System.currentTimeMillis();
			ApplicationManager.initializeInstance(configurator);
			long end = System.currentTimeMillis();
			Logging.debug(String.format("System mapped in %d", end-init));
			ApplicationManager.getInstance().navigate("/");

		} catch (MapApplicationException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Method that will ask the system to look for a url and execute it.
	 * @param url that will be navigated to.
	 * @return The controller response.
	 */
	public static Response navigate(String url) {
		return ApplicationManager.getInstance().navigate(url);
	}
	
	/**
	 * Method that will ask the system to look for a url and execute it with 
	 * request params.
	 * @param url that will be navigated to.
	 * @param request that will be sent.
	 * @return The controller response.
	 */
	public static Response navigate(String url, Request request) {
		return ApplicationManager.getInstance().navigate(url, request);
	}
	
	/**
	 * Method that will store a custom object into the system.
	 * @param name of the object.
	 * @param value of the object.
	 */
	public static void set(String name, Object value) {
		ApplicationManager.getInstance().put(name, value);
	}
	
	/**
	 * Method that will return a stored object given its name.
	 * @param name of the object.
	 * @return The object value.
	 */
	public static Object get(String name) {
		return ApplicationManager.getInstance().get(name);
	}
	
	/**
	 * Method that will remove a stored object given its name.
	 * @param name of the object.
	 * @return The object value.
	 */
	public static Object remove(String name) {
		return ApplicationManager.getInstance().remove(name);
	}
	
	/**
	 * Method that checks if an object exists.
	 * @param key of the object, its name.
	 * @return True if the object exists, false if it doesn't.
	 */
	public static boolean hasKey(String key) {
		return ApplicationManager.getInstance().containsKey(key);
	}

	
}
