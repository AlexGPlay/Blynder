package atrahasis.core.manager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import atrahasis.core.configurator.IConfigurator;
import atrahasis.core.exception.IllegalViewException;
import atrahasis.core.exception.MapApplicationException;
import atrahasis.core.network.Request;
import atrahasis.core.network.Response;
import atrahasis.core.network.url.AppUrlHandlerSetter;
import atrahasis.core.template.Model;
import atrahasis.core.util.InstanceSaver;
import atrahasis.core.util.Pair;
import atrahasis.core.util.ParamSorter;
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
	
	public void navigate(String url) {
		Pair<String, Map<String,Object>> data = configurator.getRoutesFinder().findRoute(routes, url);
		Pair<Class<?>, Method> route = routes.get(data.object1);
		
		Class<?> clazz = route.object1;
		Method method = route.object2;
		Map<String,Object> params = data.object2;
		Request request = new Request("GET", new HashMap<>(), params, new HashMap<>());
		Response response = new Response();
		
		try {
			Model model = new Model();
			List<Object> dataParams = new ParamSorter().sortParameters(params, method, model, request, response);
			Object responseObject = method.invoke( InstanceSaver.lookForInstance(clazz), dataParams.toArray() );
			response = checkResponse(responseObject, response);
			doResponseAction(response, model);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalViewException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
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
	
	private Response checkResponse(Object returnResponse, Response paramsResponse) {
		Response toReturn;
		
		if(returnResponse == null) {
			toReturn = paramsResponse;
		}
		else {
			if(!(returnResponse instanceof Response)) {
				throw new RuntimeException("Controller return type must be response or void");
			}
			
			toReturn = (Response)returnResponse;
		}
		
		return toReturn;
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
