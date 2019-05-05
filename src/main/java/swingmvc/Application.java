package swingmvc;

import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import swingmvc.exception.IllegalViewException;
import swingmvc.finder.*;
import swingmvc.mapper.AutowiredMapping;
import swingmvc.mapper.ControllerMapping;
import swingmvc.util.InstanceSaver;
import swingmvc.util.Pair;

public class Application {

	private static List<Class<?>> classes = new ArrayList<>();
	private static List<Class<?>> controllers = new ArrayList<>();
	private static List<Class<?>> beans = new ArrayList<>();
	private static Map<String, Pair<Class<?>,Method>> routes = new HashMap<>();
	private static JFrame frame = new JFrame();
	private static JPanel contentPane = new JPanel();
	
	public Application() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		execute();
	}
	
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
		if(isValidView(view)) {
			JPanel panel = (JPanel)view;
			contentPane.removeAll();
			contentPane.add(panel);
			frame.pack();
		}
		else {
			throw new IllegalViewException("La vista no es un JPanel");
		}
	}
	
	private static boolean isValidView(Object view) {
		return view instanceof JPanel;
	}
	
	private void execute() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		groupClasses();
		createWindow();
		navigateToIndex();
	}
	
	private void navigateToIndex() {
		navigate("/");
	}

	private void createWindow() {
		frame.setTitle("Test");
		frame.setVisible(true);
		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
	}
	
	private void groupClasses() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		classes = new ClassFinder().findClasses();
		controllers = new ControllerFinder().findControllers(classes);
		routes = new ControllerMapping().map(controllers);
		beans = new BeanFinder().findBeans(classes);
		new AutowiredMapping().mapAutowired(beans);
	}
	
}
