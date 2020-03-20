package atrahasis.core.manager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import atrahasis.core.configurator.IConfigurator;
import atrahasis.core.exception.IllegalViewException;
import atrahasis.core.exception.MapApplicationException;
import atrahasis.core.manager.navigation.NavigationManager;
import atrahasis.core.manager.security.FilterManager;
import atrahasis.core.network.Response;
import atrahasis.core.network.url.AppUrlHandlerSetter;
import atrahasis.core.template.Model;
import atrahasis.core.util.BeanInstanceManager;
import atrahasis.core.util.Pair;
import atrahasis.core.view.Window;

public class ApplicationManager {

	private List<Class<?>> classes;
	private List<Class<?>> controllers;
	private List<Class<?>> beans;
	private List<Class<?>> filters;
	
	private Map<String, Pair<Class<?>,Method>> routes;
	private Map<Class<?>, List<Class<?>>> controllerFilters;
	private FilterManager filterManager;
	
	private Window mainWindow;
	private IConfigurator configurator;
	
	private HashMap<String,Object> data;
	
	private static ApplicationManager instance;
	
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
	
	private void initializeVariables() {
		classes = new ArrayList<>();
		controllers = new ArrayList<>();
		filters = new ArrayList<>();
		beans = new ArrayList<>();
		routes = new HashMap<>();
		controllerFilters = new HashMap<>();
		data = new HashMap<>();
	}
	
	private void initializeConfigurator(IConfigurator configurator) {
		this.configurator = configurator;
	}
	
	private void initializeUrlManager() {
		new AppUrlHandlerSetter().setUrlManager();
	}
	
	private void mapApplication() throws MapApplicationException {
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
	
	private void initializeWindow() {
		mainWindow = new Window();
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
	
	public void navigate(String url){
		try {
			Pair<Response, Model> response = new NavigationManager(
					url, 
					configurator.getRoutesFinder(), 
					routes,
					filterManager
			).call();
			
			doResponseAction(response.object1, response.object2);
		}
		catch(IllegalViewException e) {
			e.printStackTrace();
		}
	}
	
	private void doResponseAction(Response response, Model model) throws IllegalViewException {
		String redirect = response.getRedirect();
		if(redirect != null) {
			navigate(redirect);
		}
		else {
			Object view = response.getToRender();
			createView(view, model);
		}
	}
	
	private void createView(Object view, Model model) throws IllegalViewException {
		if(view instanceof JPanel)
			mainWindow.setSwing((JPanel)view);
		
		else if(view instanceof String) {
			mainWindow.setHtml((String)view, model);
		}
	}
	
	//////////////////////////////////////////////////////////////////
	//																//
	//																//
	//					APPLICATION DATA BLOCK						//
	//																//
	//																//
	//////////////////////////////////////////////////////////////////
	
	public void put(String name, Object value) {
		data.put(name, value);
	}
	
	public Object get(String name) {
		return data.get(name);
	}
	
	public Object remove(String name) {
		return data.remove(name);
	}
	
	public boolean containsKey(String key) {
		return data.containsKey(key);
	}
	
}
