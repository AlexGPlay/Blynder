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
 * The custom configurator class is a easy-going way to make use of your own
 * mapping and finding classes, it uses the base classes but for all the work,
 * but you can change the base classes with your own classes just by using the
 * "with" methods.
 *
 */
public class CustomConfigurator extends BasicConfigurator{

	/**
	 * 
	 * Changes the current AutowiredFinder instance with a new one.
	 * @param autowiredFinder is the new instance that will be used.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(IAutowiredFinder autowiredFinder) {
		this.autowiredFinder = autowiredFinder;
		return this;
	}
	
	/**
	 * 
	 * Changes the current SystemFinder instance with a new one.
	 * @param beanFinder is the new instance that will be used.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(ISystemFinder systemFinder) {
		this.systemFinder = systemFinder;
		return this;
	}
	
	/**
	 * 
	 * Changes the current ClassFinder instance with a new one.
	 * @param classFinder is the new instance that will be used.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(IClassFinder classFinder) {
		this.classFinder = classFinder;
		return this;
	}
	
	/**
	 * 
	 * Changes the current ControllerFinder instance with a new one.
	 * @param controllerFinder is the new instance that will be used.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(IControllerFinder controllerFinder) {
		this.controllerFinder = controllerFinder;
		return this;
	}
	
	/**
	 * 
	 * Changes the current RoutesFinder instance with a new one.
	 * @param routesFinder is the new instance that will be used.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(IRoutesFinder routesFinder) {
		this.routesFinder = routesFinder;
		return this;
	}
	
	/**
	 * 
	 * Changes the current AutowiredMapper instance with a new one.
	 * @param autowiredMapper is the new instance that will be used.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(IAutowiredMapper autowiredMapper) {
		this.autowiredMapper = autowiredMapper;
		return this;
	}
	
	/**
	 * 
	 * Changes the current ControllerMapper instance with a new one.
	 * @param controllerMapper is the new instance that will be used.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(IControllerMapper controllerMapper) {
		this.controllerMapper = controllerMapper;
		return this;
	}
	
	/**
	 * 
	 * Changes the current FilterFinder instance with a new one.
	 * @param filterFinder is the new instance that will be used.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(IFilterFinder filterFinder) {
		this.filterFinder = filterFinder;
		return this;
	}
	
	/**
	 * 
	 * Changes the current FilterMapper instance with a new one.
	 * @param filterMapper is the new instance that will be used.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(IFilterMapper filterMapper) {
		this.filterMapper = filterMapper;
		return this;
	}
	
	/**
	 * 
	 * Changes the current TemplateEngine instance with a new one.
	 * @param templateEngine is the new instance that will be used.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(ITemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
		return this;
	}
	
	/**
	 * 
	 * Changes the current Browser type with a new one.
	 * @param browser is the new instance that will be used.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(Browser browser) {
		this.browser = browser;
		return this;
	}
	
	/**
	 * 
	 * Changes the current window default size.
	 * @param windowSize is the instance that will replace the current one.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(WindowSize windowSize) {
		this.size = windowSize;
		return this;
	}
	
	/**
	 * 
	 * Changes the current window default props.
	 * @param windowProps is the instance that will replace the current one.
	 * @return The current Configurator instance, making it easier to concatenate
	 * changes.
	 * 
	 */
	public CustomConfigurator with(WindowProps windowProps) {
		this.props = windowProps;
		return this;
	}
	
}
