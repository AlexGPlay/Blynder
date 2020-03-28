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
		assertTrue(configurator.getBeanFinder() instanceof BeanFinder);	
		BeanFinder inst = null;
		configurator.with(inst);
		assertNull(configurator.getBeanFinder());
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
