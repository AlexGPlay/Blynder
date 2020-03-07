package atrahasis.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import atrahasis.core.browser.chromium.handler.AppHandlerObserver;
import atrahasis.core.browser.chromium.handler.InitializedObserver;
import atrahasis.core.configurator.BasicConfigurator;
import atrahasis.core.configurator.IConfigurator;
import atrahasis.core.exception.IllegalViewException;
import atrahasis.core.template.Model;
import atrahasis.core.util.InstanceSaver;
import atrahasis.core.util.Pair;
import atrahasis.core.util.ParamSorter;
import atrahasis.core.view.Window;

/**
 * 
 * The main application class. This class will be called if the application needs
 * to start, it is the entry point of the framework. Once you call the launchApp
 * method, all the process will start, indexing all the necessary data from the
 * project and launching the index view.
 *
 */
public class Application implements InitializedObserver, AppHandlerObserver{

	private static List<Class<?>> classes = new ArrayList<>();
	private static List<Class<?>> controllers = new ArrayList<>();
	private static List<Class<?>> beans = new ArrayList<>();
	private static Map<String, Pair<Class<?>,Method>> routes = new HashMap<>();
	
	private static Window mainWindow;
	private static IConfigurator configurator;

	private static Application instance;
	
	public static void launchApp() {
		Application.launchApp(new BasicConfigurator());
	}
	
	public static void launchApp(IConfigurator configurator) {
		Application.configurator = configurator;
		instance = new Application();
		instance.execute();
		mainWindow = new Window();
		mainWindow.initializeBrowser(configurator.getBrowser());
		navigateToIndex();
	}
	
	private Application() {}
	
	public static Application getInstance() {
		return instance;
	}
	
	public static void navigate(String url) {
		Pair<String, Map<String,Object>> data = configurator.getRoutesFinder().findRoute(routes, url);
		Pair<Class<?>, Method> route = routes.get(data.object1);
		
		Class<?> clazz = route.object1;
		Method method = route.object2;
		Map<String,Object> params = data.object2;
		
		try {
			Model model = new Model();
			List<Object> dataParams = new ParamSorter().sortParameters(params, method, model);
			Object view = method.invoke( InstanceSaver.lookForInstance(clazz), dataParams.toArray() );
			createView(view, model);
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
	
	private static void navigateToIndex() {
		navigate("/");
		mainWindow.setVisible(true);
	}
	
	private static void createView(Object view, Model model) throws IllegalViewException {
		if(view instanceof JPanel)
			mainWindow.setSwing((JPanel)view);
		
		else if(view instanceof String) {
			mainWindow.setHtml((String)view, model);
		}
	}
	
	private void execute() {
		try {
			groupClasses();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void groupClasses() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		classes = configurator.getClassFinder().findClasses();
		controllers = configurator.getControllerFinder().findControllers(classes);
		routes = configurator.getControllerMapper().map(controllers);
		beans = configurator.getBeanFinder().findBeans(classes);
		
		List<Pair<Class<?>,Field>> fields = configurator.getAutowiredFinder().findAutowired(beans);
		configurator.getAutowiredMapper().mapAutowired(beans, fields);
	}

	@Override
	public void update() {
		navigateToIndex();
	}

	@Override
	public void update(String data) {
		navigate(data);
	}
	
}
