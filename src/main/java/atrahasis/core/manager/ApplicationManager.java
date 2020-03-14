package atrahasis.core.manager;

import java.lang.reflect.Field;
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
import atrahasis.core.network.Response;
import atrahasis.core.network.url.AppUrlHandlerSetter;
import atrahasis.core.template.Model;
import atrahasis.core.util.Pair;
import atrahasis.core.view.Window;

public class ApplicationManager {

	private List<Class<?>> classes;
	private List<Class<?>> controllers;
	private List<Class<?>> beans;
	private Map<String, Pair<Class<?>,Method>> routes;
	
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
		launchApplication();
	}
	
	private void initializeVariables() {
		classes = new ArrayList<>();
		controllers = new ArrayList<>();
		beans = new ArrayList<>();
		routes = new HashMap<>();
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
			routes = configurator.getControllerMapper().map(controllers);
			beans = configurator.getBeanFinder().findBeans(classes);
			
			List<Pair<Class<?>,Field>> fields = configurator.getAutowiredFinder().findAutowired(beans);
			configurator.getAutowiredMapper().mapAutowired(beans, fields);
		}
		catch(Exception e) {
			throw new MapApplicationException();
		}
	}
	
	private void initializeWindow() {
		mainWindow = new Window();
		mainWindow.initializeBrowser(configurator.getBrowser());
	}
	
	private void launchApplication() {
		navigateToIndex();
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
					routes
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
	
	private void navigateToIndex() {
		navigate("/");
		mainWindow.setVisible(true);
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
