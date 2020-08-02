package org.blynder.core.configurator;

import org.blynder.core.browser.BrowserFactory.Browser;
import org.blynder.core.finder.IAutowiredFinder;
import org.blynder.core.finder.IClassFinder;
import org.blynder.core.finder.IControllerFinder;
import org.blynder.core.finder.IFilterFinder;
import org.blynder.core.finder.IRoutesFinder;
import org.blynder.core.finder.ISystemFinder;
import org.blynder.core.mapper.IAutowiredMapper;
import org.blynder.core.mapper.IControllerMapper;
import org.blynder.core.mapper.IFilterMapper;
import org.blynder.core.template.ITemplateEngine;
import org.blynder.core.view.WindowProps;
import org.blynder.core.view.WindowSize;

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
	 * @return The system finder class which will be used in the process.
	 * 
	 */
	public ISystemFinder getSystemFinder();
	
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
	 * @return The template engine that will be used to create the views.
	 * 
	 */
	public ITemplateEngine getTemplateEngine();
	
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
	
	/**
	 * 
	 * @return The window default size.
	 * 
	 */
	public WindowSize getWindowSize();
	
	/**
	 * 
	 * @return The window default props.
	 * 
	 */
	public WindowProps getWindowProps();
	
}
