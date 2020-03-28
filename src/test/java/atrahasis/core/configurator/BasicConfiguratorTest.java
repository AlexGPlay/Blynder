package atrahasis.core.configurator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import atrahasis.core.browser.BrowserFactory.Browser;
import atrahasis.core.finder.AutowiredFinder;
import atrahasis.core.finder.BeanFinder;
import atrahasis.core.finder.ClassFinder;
import atrahasis.core.finder.ControllerFinder;
import atrahasis.core.finder.FilterFinder;
import atrahasis.core.finder.RoutesFinder;
import atrahasis.core.mapper.AutowiredMapper;
import atrahasis.core.mapper.ControllerMapper;
import atrahasis.core.mapper.FilterMapper;

public class BasicConfiguratorTest {

	private BasicConfigurator configurator;
	
	@Before
	public void setConfigurator() {
		configurator = new BasicConfigurator();
	}
	
	@Test
	public void testGetAutowiredFinder() {
		assertTrue(configurator.getAutowiredFinder() instanceof AutowiredFinder);
	}
	
	@Test
	public void testGetBeanFinder() {
		assertTrue(configurator.getBeanFinder() instanceof BeanFinder);	
	}
	
	@Test
	public void testGetClassFinder() {
		assertTrue(configurator.getClassFinder() instanceof ClassFinder);
	}
	
	@Test
	public void testGetControllerFinder() {
		assertTrue(configurator.getControllerFinder() instanceof ControllerFinder);
	}
	
	@Test
	public void testGetRoutesFinder() {
		assertTrue(configurator.getRoutesFinder() instanceof RoutesFinder);
	}
	
	@Test
	public void testGetAutowiredMapper() {
		assertTrue(configurator.getAutowiredMapper() instanceof AutowiredMapper);
	}
	
	@Test
	public void testGetControllerMapper() {
		assertTrue(configurator.getControllerMapper() instanceof ControllerMapper);
	}
	
	@Test
	public void testGetBrowser() {
		assertTrue(configurator.getBrowser() == Browser.Standard);
	}
	
	@Test
	public void testGetFilterFinder() {
		assertTrue(configurator.getFilterFinder() instanceof FilterFinder);
	}
	
	@Test
	public void testGetFilterMapper() {
		assertTrue(configurator.getFilterMapper() instanceof FilterMapper);
	}

}
