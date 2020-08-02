package atrahasis.testClasses;

import java.util.ArrayList;
import java.util.List;

import org.blynder.core.configurator.BasicConfigurator;
import org.blynder.core.exception.MapApplicationException;
import org.blynder.core.manager.ApplicationManager;
import org.blynder.core.manager.security.FilterManager;
import org.blynder.core.util.SystemInstanceManager;

public class ApplicationManagerTestClass extends ApplicationManager{

	private static List<Class<?>> fClasses = new ArrayList<>();
	
	public ApplicationManagerTestClass() throws MapApplicationException {
		super(new BasicConfigurator());
	}
	
	public static void setClasses(List<Class<?>> classes) {
		fClasses = classes;
	}
	
	protected void initializeUrlManager() {
	}
	
	@Override
	protected void mapApplication() throws MapApplicationException {
		try {
			classes = fClasses;
			controllers = configurator.getControllerFinder().findControllers(classes);
			filters = configurator.getFilterFinder().findFilters(classes);
			routes = configurator.getControllerMapper().map(controllers);
			controllerFilters = configurator.getFilterMapper().map(controllers, filters);
			customClasses = configurator.getSystemFinder().findClasses(classes);
			
			SystemInstanceManager.initInstanceSaver(customClasses, configurator.getAutowiredMapper(), configurator.getAutowiredFinder());
			
			filterManager = new FilterManager(controllerFilters, filters);
		}
		catch(Exception e) {
			throw new MapApplicationException();
		}
	}

}
