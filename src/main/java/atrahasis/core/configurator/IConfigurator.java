package atrahasis.core.configurator;

import atrahasis.core.browser.BrowserFactory.Browser;
import atrahasis.core.finder.IAutowiredFinder;
import atrahasis.core.finder.IBeanFinder;
import atrahasis.core.finder.IClassFinder;
import atrahasis.core.finder.IControllerFinder;
import atrahasis.core.finder.IRoutesFinder;
import atrahasis.core.mapper.IAutowiredMapper;
import atrahasis.core.mapper.IControllerMapper;

public interface IConfigurator {

	public IAutowiredFinder getAutowiredFinder();
	public IBeanFinder getBeanFinder();
	public IClassFinder getClassFinder();
	public IControllerFinder getControllerFinder();
	public IRoutesFinder getRoutesFinder();
	public IAutowiredMapper getAutowiredMapper();
	public IControllerMapper getControllerMapper();
	public Browser getBrowser();
	
}
