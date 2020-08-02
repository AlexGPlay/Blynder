package atrahasis.core.configurator;

import static org.junit.Assert.*;

import org.blynder.core.browser.BrowserFactory.Browser;
import org.blynder.core.configurator.BasicConfigurator;
import org.blynder.core.finder.AutowiredFinder;
import org.blynder.core.finder.ClassFinder;
import org.blynder.core.finder.ControllerFinder;
import org.blynder.core.finder.FilterFinder;
import org.blynder.core.finder.RoutesFinder;
import org.blynder.core.finder.SystemFinder;
import org.blynder.core.mapper.AutowiredMapper;
import org.blynder.core.mapper.ControllerMapper;
import org.blynder.core.mapper.FilterMapper;
import org.junit.Before;
import org.junit.Test;

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
		assertTrue(configurator.getSystemFinder() instanceof SystemFinder);	
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
