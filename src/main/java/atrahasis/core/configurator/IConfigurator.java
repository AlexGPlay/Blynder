package atrahasis.core.configurator;

import atrahasis.core.browser.BrowserFactory.Browser;
import atrahasis.core.finder.IAutowiredFinder;
import atrahasis.core.finder.IBeanFinder;
import atrahasis.core.finder.IClassFinder;
import atrahasis.core.finder.IControllerFinder;
import atrahasis.core.finder.IFilterFinder;
import atrahasis.core.finder.IRoutesFinder;
import atrahasis.core.mapper.IAutowiredMapper;
import atrahasis.core.mapper.IControllerMapper;
import atrahasis.core.mapper.IFilterMapper;

/**
 * 
 * IConfigurator provides an interface for the creation of configurator classes.
 * Configurator classes are used to change the finding and mapping processes, meaning
 * that you can change the process itself.
 *
 */
public interface IConfigurator {

	/**
	 * 
	 * @return The autowired finder class which will be used in the process.
	 * 
	 */
	public IAutowiredFinder getAutowiredFinder();
	
	/**
	 * 
	 * @return The bean finder class which will be used in the process.
	 * 
	 */
	public IBeanFinder getBeanFinder();
	
	/**
	 * 
	 * @return The class finder class which will be used in the process.
	 * 
	 */
	public IClassFinder getClassFinder();
	
	/**
	 * 
	 * @return The controller finder class which will be used in the process.
	 * 
	 */
	public IControllerFinder getControllerFinder();
	
	/**
	 * 
	 * @return The path finder class which will be used in the process.
	 * 
	 */
	public IRoutesFinder getRoutesFinder();
	
	/**
	 * 
	 * @return The autowired mapper class which will be used in the process.
	 * 
	 */
	public IAutowiredMapper getAutowiredMapper();
	
	/**
	 * 
	 * @return The controller finder class which will be used in the process.
	 * 
	 */
	public IControllerMapper getControllerMapper();
	
	/**
	 * 
	 * @return The browser that will be used to render the html pages.
	 * 
	 */
	public Browser getBrowser();
	
	/**
	 * 
	 * @return The filter finder class which will be used in the process.
	 * 
	 */
	public IFilterFinder getFilterFinder();
	
	/**
	 * 
	 * @return The filter mapper class which will be used in the process.
	 * 
	 */
	public IFilterMapper getFilterMapper();
	
}
