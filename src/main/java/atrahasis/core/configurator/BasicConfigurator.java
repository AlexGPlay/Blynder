package atrahasis.core.configurator;

import atrahasis.core.browser.BrowserFactory.Browser;
import atrahasis.core.finder.AutowiredFinder;
import atrahasis.core.finder.BeanFinder;
import atrahasis.core.finder.ClassFinder;
import atrahasis.core.finder.ControllerFinder;
import atrahasis.core.finder.IAutowiredFinder;
import atrahasis.core.finder.IBeanFinder;
import atrahasis.core.finder.IClassFinder;
import atrahasis.core.finder.IControllerFinder;
import atrahasis.core.finder.IRoutesFinder;
import atrahasis.core.finder.RoutesFinder;
import atrahasis.core.mapper.AutowiredMapper;
import atrahasis.core.mapper.ControllerMapper;
import atrahasis.core.mapper.IAutowiredMapper;
import atrahasis.core.mapper.IControllerMapper;

/**
 * 
 * The basic configurator class provides the framework finding and mapping classes,
 * meaning that this is the base configurator if you don't use a configurator of
 * your own.
 *
 */
public class BasicConfigurator implements IConfigurator{

	protected IAutowiredFinder autowiredFinder;
	protected IBeanFinder beanFinder;
	protected IClassFinder classFinder;
	protected IControllerFinder controllerFinder;
	protected IRoutesFinder routesFinder;
	protected IAutowiredMapper autowiredMapper;
	protected IControllerMapper controllerMapper;
	protected Browser browser;
	
	public BasicConfigurator() {
		this.autowiredFinder = new AutowiredFinder();
		this.beanFinder = new BeanFinder();
		this.classFinder = new ClassFinder();
		this.controllerFinder = new ControllerFinder();
		this.routesFinder = new RoutesFinder();
		this.autowiredMapper = new AutowiredMapper();
		this.controllerMapper = new ControllerMapper();
		this.browser = Browser.Standard;
	}
	
	@Override
	public IAutowiredFinder getAutowiredFinder() {
		return autowiredFinder;
	}

	@Override
	public IBeanFinder getBeanFinder() {
		return beanFinder;
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

}
