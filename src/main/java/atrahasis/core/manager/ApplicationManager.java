package atrahasis.core.manager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atrahasis.core.configurator.IConfigurator;
import atrahasis.core.exception.IllegalViewException;
import atrahasis.core.exception.MapApplicationException;
import atrahasis.core.manager.navigation.NavigationManager;
import atrahasis.core.manager.security.FilterManager;
import atrahasis.core.network.Request;
import atrahasis.core.network.Response;
import atrahasis.core.network.url.AppUrlHandlerSetter;
import atrahasis.core.template.Model;
import atrahasis.core.util.BeanInstanceManager;
import atrahasis.core.util.Pair;
import atrahasis.core.view.NotFoundView;
import atrahasis.core.view.Window;
import atrahasis.core.view.WindowSize;

/**
 * 
 * This class is the core of the application, it handles all the initial configuration
 * and the routing of the application. This class is used by the Application static
 * class via its singleton instance.
 *
 */
public class ApplicationManager {

	/**
	 * 
	 * A list of all the classes of the project.
	 * 
	 */
	protected List<Class<?>> classes;
	
	/**
	 * 
	 * A list of all the controllers of the project.
	 * 
	 */
	protected List<Class<?>> controllers;
	
	/**
	 * 
	 * A list of all the beans of the project.
	 * 
	 */
	protected List<Class<?>> beans;
	
	/**
	 * 
	 * A list of all the filters of the project.
	 * 
	 */
	protected List<Class<?>> filters;
	
	/**
	 * 
	 * A map that contains all the routes of the project with the following format:
	 * 
	 * { url => { method => Pair(ControllerClass - RouteMethod) } }
	 * 
	 * Or in other words, an url can have many methods and that method is in a
	 * method in a controller class. From inside to outside, the pair controller 
	 * - route are the route method that handles the given request and the controller
	 * class it is inside. That pair is hashed in the method it handles. All the methods
	 * from an url are hashed as the url values.
	 * 
	 */
	protected Map<String, Map<String, Pair<Class<?>,Method>>> routes;
	
	/**
	 * 
	 * A map that contains all the filters that are executed for a controller.
	 * For example, imagine that the FooController is filtered with FooFilter1 and
	 * FooFilter2.
	 * The map will contain an entry such as { FooFilter.class => [FooFilter1.class, FooFilter2.class] }.
	 * 
	 */
	protected Map<Class<?>, List<Class<?>>> controllerFilters;
	
	/**
	 * 
	 * The FilterManager that will handle the invocation of the filters in the
	 * NavigationManager.
	 * 
	 */
	protected FilterManager filterManager;
	
	/**
	 * 
	 * The MainWindow that renders the user view.
	 * 
	 */
	protected Window mainWindow;
	
	/**
	 * 
	 * The configurator given by the user at the creation of the app.
	 * 
	 */
	protected IConfigurator configurator;
	
	/**
	 * 
	 * A hash that contains user data. It can be used to store application data such
	 * as session, username or what the user wants.
	 * 
	 */
	protected HashMap<String,Object> data;
	
	
	/**
	 * 
	 * The singleton instance of the ApplicationManager.
	 * 
	 */
	protected static ApplicationManager instance;
	
	/**
	 * 
	 * @return
	 * Returns the instance of the application
	 * 
	 */
	public static ApplicationManager getInstance() {
		return instance;
	}
	
	//////////////////////////////////////////////////////////////////
	//																//
	//																//
	//					APPLICATION INIT BLOCK						//
	//																//
	//																//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * Given the configurator, the application will be created.
	 * @param configurator that holds the instances of the main components.
	 * @throws MapApplicationException if there is an error when creating the
	 * application.
	 * 
	 */
	public static void initializeInstance(IConfigurator configurator) throws MapApplicationException {
		instance = new ApplicationManager(configurator);
	}
	
	public ApplicationManager(IConfigurator configurator) throws MapApplicationException {
		initializeVariables();
		initializeConfigurator(configurator);
		initializeUrlManager();
		mapApplication();
		initializeWindow();
	}
	
	/**
	 * 
	 * Creates a default instance for all the lists and maps.
	 * 
	 */
	protected void initializeVariables() {
		classes = new ArrayList<>();
		controllers = new ArrayList<>();
		filters = new ArrayList<>();
		beans = new ArrayList<>();
		routes = new HashMap<>();
		controllerFilters = new HashMap<>();
		data = new HashMap<>();
	}
	
	/**
	 * 
	 * Stores the configurator given by the user in a instance variable.
	 * @param configurator instance given by the user or the default one.
	 * 
	 */
	protected void initializeConfigurator(IConfigurator configurator) {
		this.configurator = configurator;
	}
	
	/**
	 * 
	 * Sets the setURLStreamHandlerFactory, this will allow the application
	 * to handle all the app:// schema requests. Given this, the application
	 * will be able to handle hrefs, ajax calls and other requests to the schema.
	 * 
	 */
	protected void initializeUrlManager() {
		new AppUrlHandlerSetter().setUrlManager();
	}
	
	/**
	 * 
	 * One by one, this method will first find all the needed data, this data
	 * are the classes, controllers, filters and beans. After it has all the needed data
	 * it will begin with the mapping of the application. This mapping involves
	 * mapping the path routes to its controller methods and the filters to the
	 * controller classes. After that it will instantiate the BeanInstanceManager,
	 * this manager is the one that handles all the creation of the beans and the
	 * autowiring.
	 * 
	 * @throws MapApplicationException
	 * If there is a problem in the mapping process.
	 * 
	 */
	protected void mapApplication() throws MapApplicationException {
		try {
			classes = configurator.getClassFinder().findClasses();
			controllers = configurator.getControllerFinder().findControllers(classes);
			filters = configurator.getFilterFinder().findFilters(classes);
			routes = configurator.getControllerMapper().map(controllers);
			controllerFilters = configurator.getFilterMapper().map(controllers, filters);
			beans = configurator.getBeanFinder().findBeans(classes);
			
			BeanInstanceManager.initInstanceSaver(beans, configurator.getAutowiredMapper(), configurator.getAutowiredFinder());
			
			filterManager = new FilterManager(controllerFilters, filters);
		}
		catch(Exception e) {
			throw new MapApplicationException();
		}
	}
	
	/**
	 * 
	 * Creates the main application window and starts a process to render
	 * the browser for the first time. This is needed for the chromium browser
	 * since it needs to load at least one view before it is ready. That process
	 * is taken care pretty quickly so it won't be noticed by the user.
	 * 
	 */
	protected void initializeWindow() {
		mainWindow = new Window(configurator);
		mainWindow.initializeBrowser(configurator.getBrowser());
		mainWindow.setVisible(true);
	}
	
	//////////////////////////////////////////////////////////////////
	//																//
	//																//
	//					APPLICATION MANAGEMENT BLOCK				//
	//																//
	//																//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * One of the two entry points of the application manager, the navigate
	 * method without request. This method will delegate into the navigate
	 * method with request. Given that there is no request it will work as
	 * a "GET" request.
	 * 
	 * @param url
	 * The url the user wants to navigate to.
	 * 
	 * @return
	 * The response given by the controller.
	 * 
	 */
	public Response navigate(String url){
		return navigate(url, null);
	}
	
	/**
	 * 
	 * One of the two entry points of the application manager. This method will
	 * invoke the NavigationManager that will handle the routing of the URL with
	 * the given request. After the routing occurs it will try to use the response,
	 * if the response is renderizable, for example, html, component or another
	 * supported renderizable it will call the window for its handling. In case
	 * it is not renderizable it will do nothing with the window and will only
	 * return the response, this case is expected to happen if the user is 
	 * requesting data from an ApiController via asynchronous calls.
	 * 
	 * @param url
	 * Url that the user wants to navigate to.
	 * 
	 * @param request
	 * Request given by the user, might be null.
	 * 
	 * @return
	 * The response given by the controller.<br>
	 * Null if there is no controller for the handling.
	 * 
	 */
	public Response navigate(String url, Request request) {
		try {
			Object[] response = new NavigationManager(
					url, 
					configurator.getRoutesFinder(), 
					routes,
					filterManager,
					request
			).call();
			
			if(response == null) {
				return null;
			}
			
			Response responseObject = (Response) response[0];
			
			if(responseObject.getStatusCode() == 404) {
				responseObject
				.response(new NotFoundView())
				.responseType("application/swing");
			}
			
			else if(responseObject.getStatusCode() == 500) {
				return responseObject;
			}
			
			doResponseAction(response);
			return responseObject;
		}
		catch(IllegalViewException | NullPointerException e) {
			e.printStackTrace();
			return new Response().response(e.getMessage()).statusCode(500);
		}
	}
	
	/**
	 * 
	 * At this point of the execution the application already knows what the
	 * controller wants to do, so it will try to render the view if possible
	 * or if it is not possible it will just do nothing. It is also possible that
	 * a filter or the controller requested a redirect to another path, in that
	 * case the app will be redirected and there will be no render.
	 * 
	 * @param responseArray
	 * Composed of a response, a model and a windowSize.
	 * The response of the controller.
	 * The model that was filled in the controller or an empty one if it wasn't used.
	 * The windowSize that will be displayed.
	 * 
	 * @throws IllegalViewException
	 * If the view is not supported.
	 * 
	 */
	protected void doResponseAction(Object[] responseArray) throws IllegalViewException {
		Response response = (Response) responseArray[0];
		Model model = (Model) responseArray[1];
		WindowSize size = (WindowSize) responseArray[2];
		
		String redirect = response.getRedirect();
		if(redirect != null) {
			navigate(redirect);
		}
		else {
			if(!response.isRenderizable())
				return;
			
			Object view = response.getResponse();
			mainWindow.setView(view, model, size);
		}
	}
	
	//////////////////////////////////////////////////////////////////
	//																//
	//																//
	//					APPLICATION DATA BLOCK						//
	//																//
	//																//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * Puts data into the application data hash.
	 * 
	 * @param name
	 * Name of the data that will be stored. Will work as the key.
	 * 
	 * @param value
	 * Value of the data that will be stoed.
	 * 
	 */
	public void put(String name, Object value) {
		data.put(name, value);
	}
	
	/**
	 * 
	 * Returns the value of the data with the given key.
	 * 
	 * @param name
	 * Key that will be used.
	 * 
	 * @return
	 * The value of the key.
	 * 
	 */
	public Object get(String name) {
		return data.get(name);
	}
	
	/**
	 * 
	 * Removes the key-value from the hash.
	 * 
	 * @param name
	 * The key that will be removed.
	 * 
	 * @return
	 * The value of the data that has been removed.
	 * 
	 */
	public Object remove(String name) {
		return data.remove(name);
	}
	
	/**
	 * 
	 * Checks if the given key exists in the hash.
	 * 
	 * @param key
	 * The key that will be searched for.
	 * 
	 * @return
	 * True if the key exists or false if it doesn't.
	 * 
	 */
	public boolean containsKey(String key) {
		return data.containsKey(key);
	}
	
}
