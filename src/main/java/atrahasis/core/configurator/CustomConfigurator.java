package atrahasis.core.configurator;

import atrahasis.core.finder.IAutowiredFinder;
import atrahasis.core.finder.IBeanFinder;
import atrahasis.core.finder.IClassFinder;
import atrahasis.core.finder.IControllerFinder;
import atrahasis.core.finder.IRoutesFinder;
import atrahasis.core.mapper.IAutowiredMapper;
import atrahasis.core.mapper.IControllerMapper;

public class CustomConfigurator extends BasicConfigurator{

	public CustomConfigurator with(IAutowiredFinder autowiredFinder) {
		this.autowiredFinder = autowiredFinder;
		return this;
	}
	
	public CustomConfigurator with(IBeanFinder beanFinder) {
		this.beanFinder = beanFinder;
		return this;
	}
	
	public CustomConfigurator with(IClassFinder classFinder) {
		this.classFinder = classFinder;
		return this;
	}
	
	public CustomConfigurator with(IControllerFinder controllerFinder) {
		this.controllerFinder = controllerFinder;
		return this;
	}
	
	public CustomConfigurator with(IRoutesFinder routesFinder) {
		this.routesFinder = routesFinder;
		return this;
	}
	
	public CustomConfigurator with(IAutowiredMapper autowiredMapper) {
		this.autowiredMapper = autowiredMapper;
		return this;
	}
	
	public CustomConfigurator with(IControllerMapper controllerMapper) {
		this.controllerMapper = controllerMapper;
		return this;
	}
	
}
