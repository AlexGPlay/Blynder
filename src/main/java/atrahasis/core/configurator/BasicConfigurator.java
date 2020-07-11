package atrahasis.core.configurator;

import atrahasis.core.browser.BrowserFactory.Browser;
import atrahasis.core.finder.AutowiredFinder;
import atrahasis.core.finder.SystemFinder;
import atrahasis.core.finder.ClassFinder;
import atrahasis.core.finder.ControllerFinder;
import atrahasis.core.finder.FilterFinder;
import atrahasis.core.finder.IAutowiredFinder;
import atrahasis.core.finder.ISystemFinder;
import atrahasis.core.finder.IClassFinder;
import atrahasis.core.finder.IControllerFinder;
import atrahasis.core.finder.IFilterFinder;
import atrahasis.core.finder.IRoutesFinder;
import atrahasis.core.finder.RoutesFinder;
import atrahasis.core.mapper.AutowiredMapper;
import atrahasis.core.mapper.ControllerMapper;
import atrahasis.core.mapper.FilterMapper;
import atrahasis.core.mapper.IAutowiredMapper;
import atrahasis.core.mapper.IControllerMapper;
import atrahasis.core.mapper.IFilterMapper;
import atrahasis.core.template.ITemplateEngine;
import atrahasis.core.template.Thymeleaf;
import atrahasis.core.view.WindowProps;
import atrahasis.core.view.WindowSize;

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
