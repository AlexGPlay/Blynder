package atrahasis.core.manager.navigation;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.blynder.core.finder.AutowiredFinder;
import org.blynder.core.finder.RoutesFinder;
import org.blynder.core.manager.navigation.NavigationManager;
import org.blynder.core.manager.security.FilterManager;
import org.blynder.core.mapper.AutowiredMapper;
import org.blynder.core.mapper.ControllerMapper;
import org.blynder.core.network.Request;
import org.blynder.core.network.Response;
import org.blynder.core.util.Pair;
import org.blynder.core.util.SystemInstanceManager;
import org.junit.Test;

import atrahasis.testClasses.*;

public class NavigationManagerTest {

	private ControllerMapper controllerMapper = new ControllerMapper();
	
	@Test
	public void testWithInvalidParams() {
		Object[] resp = new NavigationManager(
			null,
			null,
			null,
			null,
			null
		).call();
		
		assertEquals(404, ((Response)(resp[0])).getStatusCode());
	}
	
	@Test
	public void testWithInvalidPath() {
		Object[] resp = new NavigationManager(
			"/test",
			new RoutesFinder(),
			new HashMap<>(),
			new FilterManager(null, new ArrayList<>()),
			null
		).call();
		
		assertEquals(404, ((Response)(resp[0])).getStatusCode());
	}
	
	@Test
	public void testWithNoControllerPath() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestClass2.class);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		routes.get("/test/path1").remove("GET");
		
		Object[] resp = new NavigationManager(
			"/test/path1",
			new RoutesFinder(),
			routes,
			new FilterManager(null, new ArrayList<>()),
			null
		).call();
		
		assertEquals(404, ((Response)(resp[0])).getStatusCode());
	}
	
	@Test
	public void testWithInvalidPathMethod() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestClass2.class);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Object[] resp = new NavigationManager(
			"/test/path1",
			new RoutesFinder(),
			routes,
			new FilterManager(null, new ArrayList<>()),
			new Request("/test/path1", "POST")
		).call();
		
		assertEquals(404, ((Response)(resp[0])).getStatusCode());
	}
	
	@Test
	public void testWithApiView() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestApi.class);
		
		SystemInstanceManager.initInstanceSaver(controllers, new AutowiredMapper(), new AutowiredFinder());
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Object[] resp = new NavigationManager(
			"/test",
			new RoutesFinder(),
			routes,
			new FilterManager(new HashMap<>(), new ArrayList<>()),
			null
		).call();
		
		assertEquals(200, ((Response)(resp[0])).getStatusCode());
		assertEquals("application/api", ((Response)(resp[0])).getResponseType());
	}
	
	@Test
	public void testWithHtmlView() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestHtml.class);
		
		SystemInstanceManager.initInstanceSaver(controllers, new AutowiredMapper(), new AutowiredFinder());
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Object[] resp = new NavigationManager(
			"/test",
			new RoutesFinder(),
			routes,
			new FilterManager(new HashMap<>(), new ArrayList<>()),
			null
		).call();
		
		assertEquals(200, ((Response)(resp[0])).getStatusCode());
		assertEquals("application/html", ((Response)(resp[0])).getResponseType());
	}
	
	@Test
	public void testWithFxmlView() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestFxml.class);
		
		SystemInstanceManager.initInstanceSaver(controllers, new AutowiredMapper(), new AutowiredFinder());
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Object[] resp = new NavigationManager(
			"/test",
			new RoutesFinder(),
			routes,
			new FilterManager(new HashMap<>(), new ArrayList<>()),
			null
		).call();
		
		assertEquals(200, ((Response)(resp[0])).getStatusCode());
		assertEquals("application/fxml", ((Response)(resp[0])).getResponseType());
	}
	
	@Test
	public void testWithSwingView() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestSwing.class);
		
		SystemInstanceManager.initInstanceSaver(controllers, new AutowiredMapper(), new AutowiredFinder());
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Object[] resp = new NavigationManager(
				"/test",
				new RoutesFinder(),
				routes,
				new FilterManager(new HashMap<>(), new ArrayList<>()),
				null
			).call();
		
		assertEquals(200, ((Response)(resp[0])).getStatusCode());
		assertEquals("application/swing", ((Response)(resp[0])).getResponseType());
	}

	@Test
	public void testWithInvalidView() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestInvalid.class);
		
		SystemInstanceManager.initInstanceSaver(controllers, new AutowiredMapper(), new AutowiredFinder());
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Object[] resp = new NavigationManager(
				"/test",
				new RoutesFinder(),
				routes,
				new FilterManager(new HashMap<>(), new ArrayList<>()),
				null
			).call();
		
		assertEquals(500, ((Response)(resp[0])).getStatusCode());
	}
	
	@Test
	public void testWithNullView() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestNull.class);
		
		SystemInstanceManager.initInstanceSaver(controllers, new AutowiredMapper(), new AutowiredFinder());
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Object[] resp = new NavigationManager(
				"/test",
				new RoutesFinder(),
				routes,
				new FilterManager(new HashMap<>(), new ArrayList<>()),
				null
			).call();
		
		assertEquals(500, ((Response)(resp[0])).getStatusCode());
	}
	
}
