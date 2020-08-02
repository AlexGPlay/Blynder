package org.blynder.core.configurator;

import org.blynder.core.browser.BrowserFactory.Browser;
import org.blynder.core.finder.AutowiredFinder;
import org.blynder.core.finder.ClassFinder;
import org.blynder.core.finder.ControllerFinder;
import org.blynder.core.finder.FilterFinder;
import org.blynder.core.finder.IAutowiredFinder;
import org.blynder.core.finder.IClassFinder;
import org.blynder.core.finder.IControllerFinder;
import org.blynder.core.finder.IFilterFinder;
import org.blynder.core.finder.IRoutesFinder;
import org.blynder.core.finder.ISystemFinder;
import org.blynder.core.finder.RoutesFinder;
import org.blynder.core.finder.SystemFinder;
import org.blynder.core.mapper.AutowiredMapper;
import org.blynder.core.mapper.ControllerMapper;
import org.blynder.core.mapper.FilterMapper;
import org.blynder.core.mapper.IAutowiredMapper;
import org.blynder.core.mapper.IControllerMapper;
import org.blynder.core.mapper.IFilterMapper;
import org.blynder.core.template.ITemplateEngine;
import org.blynder.core.template.Thymeleaf;
import org.blynder.core.view.WindowProps;
import org.blynder.core.view.WindowSize;

/**
 * 
 * The basic configurator class provides the framework finding and mapping classes,
 * meaning that this is the base configurator if you don't use a configurator of
 * your own.
 *
 */
public class BasicConfigurator implements IConfigurator{

	protected IAutowiredFinder autowiredFinder;
	protected ISystemFinder systemFinder;
	protected IClassFinder classFinder;
	protected IControllerFinder controllerFinder;
	protected IRoutesFinder routesFinder;
	protected IAutowiredMapper autowiredMapper;
	protected IControllerMapper controllerMapper;
	protected IFilterFinder filterFinder;
	protected IFilterMapper filterMapper;
	protected ITemplateEngine templateEngine;
	protected Browser browser;
	protected WindowSize size;
	protected WindowProps props;
	
	public BasicConfigurator() {
		this.autowiredFinder = new AutowiredFinder();
		this.systemFinder = new SystemFinder();
		this.classFinder = new ClassFinder();
		this.controllerFinder = new ControllerFinder();
		this.routesFinder = new RoutesFinder();
		this.autowiredMapper = new AutowiredMapper();
		this.controllerMapper = new ControllerMapper();
		this.filterFinder = new FilterFinder();
		this.filterMapper = new FilterMapper();
		this.templateEngine = new Thymeleaf();
		this.browser = Browser.Standard;
		this.size = new WindowSize();
		this.props = new WindowProps("Application");
	}
	
	@Override
	public IAutowiredFinder getAutowiredFinder() {
		return autowiredFinder;
	}

	@Override
	public ISystemFinder getSystemFinder() {
		return systemFinder;
	}

	@Override
	public IClassFinder getClassFinder() {
		return classFinder;
	}

	@Override
	public IControllerFinder getControllerFinder() {
		return controllerFinder;
	}

	@Override
	public IRoutesFinder getRoutesFinder() {
		return routesFinder;
	}

	@Override
	public IAutowiredMapper getAutowiredMapper() {
		return autowiredMapper;
	}

	@Override
	public IControllerMapper getControllerMapper() {
		return controllerMapper;
	}

	@Override
	public Browser getBrowser() {
		return browser;
	}

	@Override
	public IFilterFinder getFilterFinder() {
		return filterFinder;
	}

	@Override
	public IFilterMapper getFilterMapper() {
		return filterMapper;
	}

	@Override
	public ITemplateEngine getTemplateEngine() {
		return templateEngine;
	}
	
	@Override
	public WindowSize getWindowSize() {
		return size;
	}

	@Override
	public WindowProps getWindowProps() {
		return props;
	}

}
