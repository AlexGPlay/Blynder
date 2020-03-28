package atrahasis.testClasses;

import java.util.ArrayList;
import java.util.List;

import atrahasis.core.configurator.BasicConfigurator;
import atrahasis.core.exception.MapApplicationException;
import atrahasis.core.manager.ApplicationManager;
import atrahasis.core.manager.security.FilterManager;
import atrahasis.core.util.BeanInstanceManager;

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
			beans = configurator.getBeanFinder().findBeans(classes);
			
			BeanInstanceManager.initInstanceSaver(beans, configurator.getAutowiredMapper(), configurator.getAutowiredFinder());
			
			filterManager = new FilterManager(controllerFilters, filters);
		}
		catch(Exception e) {
			throw new MapApplicationException();
		}
	}

}
