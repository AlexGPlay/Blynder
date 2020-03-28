package atrahasis.core.manager.navigation;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import atrahasis.core.finder.AutowiredFinder;
import atrahasis.core.finder.RoutesFinder;
import atrahasis.core.manager.security.FilterManager;
import atrahasis.core.mapper.AutowiredMapper;
import atrahasis.core.mapper.ControllerMapper;
import atrahasis.core.network.Request;
import atrahasis.core.network.Response;
import atrahasis.core.template.Model;
import atrahasis.core.util.BeanInstanceManager;
import atrahasis.core.util.Pair;
import atrahasis.testClasses.*;

public class NavigationManagerTest {

	private ControllerMapper controllerMapper = new ControllerMapper();
	
	@Test
	public void testWithInvalidParams() {
		Pair<Response,Model> resp = new NavigationManager(
			null,
			null,
			null,
			null,
			null
		).call();
		
		assertEquals(404, resp.object1.getStatusCode());
	}
	
	@Test
	public void testWithInvalidPath() {
		Pair<Response,Model> resp = new NavigationManager(
			"/test",
			new RoutesFinder(),
			new HashMap<>(),
			new FilterManager(null, new ArrayList<>()),
			null
		).call();
		
		assertEquals(404, resp.object1.getStatusCode());
	}
	
	@Test
	public void testWithNoControllerPath() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestClass2.class);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		routes.get("/test/path1").remove("GET");
		
		Pair<Response,Model> resp = new NavigationManager(
			"/test/path1",
			new RoutesFinder(),
			routes,
			new FilterManager(null, new ArrayList<>()),
			null
		).call();
		
		assertEquals(404, resp.object1.getStatusCode());
	}
	
	@Test
	public void testWithInvalidPathMethod() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestClass2.class);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Pair<Response,Model> resp = new NavigationManager(
			"/test/path1",
			new RoutesFinder(),
			routes,
			new FilterManager(null, new ArrayList<>()),
			new Request("/test/path1", "POST")
		).call();
		
		assertEquals(404, resp.object1.getStatusCode());
	}
	
	@Test
	public void testWithApiView() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestApi.class);
		
		BeanInstanceManager.initInstanceSaver(controllers, new AutowiredMapper(), new AutowiredFinder());
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Pair<Response,Model> resp = new NavigationManager(
			"/test",
			new RoutesFinder(),
			routes,
			new FilterManager(new HashMap<>(), new ArrayList<>()),
			null
		).call();
		
		assertEquals(200, resp.object1.getStatusCode());
		assertEquals("application/api", resp.object1.getResponseType());
	}
	
	@Test
	public void testWithHtmlView() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestHtml.class);
		
		BeanInstanceManager.initInstanceSaver(controllers, new AutowiredMapper(), new AutowiredFinder());
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Pair<Response,Model> resp = new NavigationManager(
			"/test",
			new RoutesFinder(),
			routes,
			new FilterManager(new HashMap<>(), new ArrayList<>()),
			null
		).call();
		
		assertEquals(200, resp.object1.getStatusCode());
		assertEquals("application/html", resp.object1.getResponseType());
	}
	
	@Test
	public void testWithFxmlView() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestFxml.class);
		
		BeanInstanceManager.initInstanceSaver(controllers, new AutowiredMapper(), new AutowiredFinder());
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Pair<Response,Model> resp = new NavigationManager(
			"/test",
			new RoutesFinder(),
			routes,
			new FilterManager(new HashMap<>(), new ArrayList<>()),
			null
		).call();
		
		assertEquals(200, resp.object1.getStatusCode());
		assertEquals("application/fxml", resp.object1.getResponseType());
	}
	
	@Test
	public void testWithSwingView() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestSwing.class);
		
		BeanInstanceManager.initInstanceSaver(controllers, new AutowiredMapper(), new AutowiredFinder());
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Pair<Response,Model> resp = new NavigationManager(
				"/test",
				new RoutesFinder(),
				routes,
				new FilterManager(new HashMap<>(), new ArrayList<>()),
				null
			).call();
		
		assertEquals(200, resp.object1.getStatusCode());
		assertEquals("application/swing", resp.object1.getResponseType());
	}

	@Test
	public void testWithInvalidView() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestInvalid.class);
		
		BeanInstanceManager.initInstanceSaver(controllers, new AutowiredMapper(), new AutowiredFinder());
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Pair<Response,Model> resp = new NavigationManager(
				"/test",
				new RoutesFinder(),
				routes,
				new FilterManager(new HashMap<>(), new ArrayList<>()),
				null
			).call();
		
		assertEquals(500, resp.object1.getStatusCode());
	}
	
	@Test
	public void testWithNullView() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestNull.class);
		
		BeanInstanceManager.initInstanceSaver(controllers, new AutowiredMapper(), new AutowiredFinder());
		
		Map<String, Map<String, Pair<Class<?>, Method>>> routes = controllerMapper.map(controllers);
		
		Pair<Response,Model> resp = new NavigationManager(
				"/test",
				new RoutesFinder(),
				routes,
				new FilterManager(new HashMap<>(), new ArrayList<>()),
				null
			).call();
		
		assertEquals(500, resp.object1.getStatusCode());
	}
	
}
