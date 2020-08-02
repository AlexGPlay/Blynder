package atrahasis.core.configurator;

import static org.junit.Assert.*;

import org.blynder.core.browser.BrowserFactory.Browser;
import org.blynder.core.configurator.CustomConfigurator;
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

public class CustomConfiguratorTest {

	private CustomConfigurator configurator;
	
	@Before
	public void setConfigurator() {
		configurator = new CustomConfigurator();
	}
	
	@Test
	public void testGetAutowiredFinder() {
		assertTrue(configurator.getAutowiredFinder() instanceof AutowiredFinder);
		AutowiredFinder inst = null;
		configurator.with(inst);
		assertNull(configurator.getAutowiredFinder());
	}
	
	@Test
	public void testGetBeanFinder() {
		assertTrue(configurator.getSystemFinder() instanceof SystemFinder);	
		SystemFinder inst = null;
		configurator.with(inst);
		assertNull(configurator.getSystemFinder());
	}
	
	@Test
	public void testGetClassFinder() {
		assertTrue(configurator.getClassFinder() instanceof ClassFinder);
		ClassFinder inst = null;
		configurator.with(inst);
		assertNull(configurator.getClassFinder());
	}
	
	@Test
	public void testGetControllerFinder() {
		assertTrue(configurator.getControllerFinder() instanceof ControllerFinder);
		ControllerFinder inst = null;
		configurator.with(inst);
		assertNull(configurator.getControllerFinder());
	}
	
	@Test
	public void testGetRoutesFinder() {
		assertTrue(configurator.getRoutesFinder() instanceof RoutesFinder);
		RoutesFinder inst = null;
		configurator.with(inst);
		assertNull(configurator.getRoutesFinder());
	}
	
	@Test
	public void testGetAutowiredMapper() {
		assertTrue(configurator.getAutowiredMapper() instanceof AutowiredMapper);
		AutowiredMapper inst = null;
		configurator.with(inst);
		assertNull(configurator.getAutowiredMapper());
	}
	
	@Test
	public void testGetControllerMapper() {
		assertTrue(configurator.getControllerMapper() instanceof ControllerMapper);
		ControllerMapper inst = null;
		configurator.with(inst);
		assertNull(configurator.getControllerMapper());
	}
	
	@Test
	public void testGetBrowser() {
		assertTrue(configurator.getBrowser() == Browser.Standard);
		configurator.with(Browser.Chromium);
		assertEquals(configurator.getBrowser(), Browser.Chromium);
	}
	
	@Test
	public void testGetFilterFinder() {
		assertTrue(configurator.getFilterFinder() instanceof FilterFinder);
		FilterFinder inst = null;
		configurator.with(inst);
		assertNull(configurator.getFilterFinder());
	}
	
	@Test
	public void testGetFilterMapper() {
		assertTrue(configurator.getFilterMapper() instanceof FilterMapper);
		FilterMapper inst = null;
		configurator.with(inst);
		assertNull(configurator.getFilterMapper());
	}

}
