package atrahasis.core.manager.security;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.blynder.core.manager.security.FilterManager;
import org.blynder.core.mapper.FilterMapper;
import org.blynder.core.network.Request;
import org.blynder.core.network.Response;
import org.junit.Test;

import atrahasis.testClasses.*;

public class FilterManagerTest {

	private FilterManager filterManager;
	private FilterMapper filterMapper = new FilterMapper();
	
	@Test
	public void testNoFiltersNoControllers() {
		List<Class<?>> filters = new ArrayList<>();
		List<Class<?>> controllers = new ArrayList<>();
		
		filterManager = new FilterManager(filterMapper.map(controllers, filters), filters);
		Response res = filterManager.call(null, new Request("/test"), new Response());
		
		assertTrue(res.isAbleToContinue());
	}
	
	@Test
	public void testNoFilteredController() {
		List<Class<?>> filters = new ArrayList<>();
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestClass1.class);
		
		filterManager = new FilterManager(filterMapper.map(controllers, filters), filters);
		Response res = filterManager.call(null, new Request("/test"), new Response());
		
		assertTrue(res.isAbleToContinue());
	}
	
	@Test
	public void testFilteredControllerCanContinue() {
		List<Class<?>> filters = new ArrayList<>();
		filters.add(FilterTestClass5.class);
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(FilteredController6.class);
		
		filterManager = new FilterManager(filterMapper.map(controllers, filters), filters);
		Response res = filterManager.call(FilteredController6.class, new Request("/test"), new Response());
		
		assertTrue(res.isAbleToContinue());
	}
	
	@Test
	public void testFilteredControllerCantContinue() {
		List<Class<?>> filters = new ArrayList<>();
		filters.add(FilterTestClass4.class);
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(FilteredController5.class);
		
		filterManager = new FilterManager(filterMapper.map(controllers, filters), filters);
		Response res = filterManager.call(FilteredController5.class, new Request("/test"), new Response());
		
		assertFalse(res.isAbleToContinue());
	}


}
