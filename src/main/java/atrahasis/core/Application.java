package atrahasis.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import atrahasis.core.exception.IllegalViewException;
import atrahasis.core.finder.*;
import atrahasis.core.mapper.AutowiredMapping;
import atrahasis.core.mapper.ControllerMapping;
import atrahasis.core.util.InstanceSaver;
import atrahasis.core.util.Pair;
import atrahasis.core.view.Window;
import javafx.stage.Stage;

public class Application extends javafx.application.Application{

	private static List<Class<?>> classes = new ArrayList<>();
	private static List<Class<?>> controllers = new ArrayList<>();
	private static List<Class<?>> beans = new ArrayList<>();
	private static Map<String, Pair<Class<?>,Method>> routes = new HashMap<>();
	
	private static Window mainWindow;
	
	public static void launchApp() {
		launch();
	}
	
	public Application() {}
	
	public static void navigate(String url) {
		Pair<Class<?>, Method> data = routes.get(url);
		Class<?> clazz = data.object1;
		Method method = data.object2;
		
		try {
			Object view = method.invoke( InstanceSaver.lookForInstance(clazz) );
			createView(view);
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
	
	private static void createView(Object view) throws IllegalViewException {
		if(view instanceof JPanel)
			mainWindow.setSwing((JPanel)view);
		
		else if(view instanceof String) {
			mainWindow.setHtml((String)view);
		}
	}
	
	private void execute() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		groupClasses();
		navigateToIndex();
	}
	
	private void navigateToIndex() {
		navigate("/");
	}
	
	private void groupClasses() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		classes = new ClassFinder().findClasses();
		controllers = new ControllerFinder().findControllers(classes);
		routes = new ControllerMapping().map(controllers);
		beans = new BeanFinder().findBeans(classes);
		new AutowiredMapping().mapAutowired(beans);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainWindow = new Window(primaryStage);
		execute();
	}
	
}
