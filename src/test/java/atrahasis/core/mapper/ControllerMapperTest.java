package atrahasis.core.mapper;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import atrahasis.core.util.Pair;
import atrahasis.testClasses.ApiControllerTestClass1;
import atrahasis.testClasses.ControllerTestClass1;
import atrahasis.testClasses.ControllerTestClass2;
import atrahasis.testClasses.ControllerTestClass3;
import atrahasis.testClasses.ControllerTestClass4;

public class ControllerMapperTest {

	private ControllerMapper controllerMapper = new ControllerMapper();
	
	private void testMapping(Map<String, Map<String, Pair<Class<?>, Method>>> expected, Map<String, Map<String, Pair<Class<?>, Method>>> result) {
		for(String route : expected.keySet()) {
			if(!result.containsKey(route))
				fail("Expected to contain " + route);
			
			Map<String, Pair<Class<?>, Method>> subset = expected.get(route);
			for(String method : subset.keySet())
				if(!result.get(route).containsKey(method))
					fail(route + " expected to contain " + method + " method");
		}
	}
	
	@Test
	public void testWithEmptyControllerList() {
		List<Class<?>> controllers = new ArrayList<>();
		Map<String, Map<String, Pair<Class<?>, Method>>> expected = new HashMap<>();
		
		Map<String, Map<String, Pair<Class<?>, Method>>> mappedControllers = controllerMapper.map(controllers);
		
		assertEquals(expected, mappedControllers);
	}
	
	@Test
	public void testWithEmptyController() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestClass1.class);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> expected = new HashMap<>();
		
		Map<String, Map<String, Pair<Class<?>, Method>>> mappedControllers = controllerMapper.map(controllers);
		
		assertEquals(expected, mappedControllers);
	}
	
	@Test
	public void testWithOneController() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestClass2.class);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> expected = new HashMap<>();
		Map<String, Pair<Class<?>, Method>> controller2Data = new HashMap<>();
		controller2Data.put("GET", null);
		expected.put("/test/path1", controller2Data);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> mappedControllers = controllerMapper.map(controllers);
		
		testMapping(expected, mappedControllers);
	}
	
	@Test
	public void testWithTwoControllers() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestClass1.class);
		controllers.add(ControllerTestClass2.class);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> expected = new HashMap<>();
		Map<String, Pair<Class<?>, Method>> controller2Data = new HashMap<>();
		controller2Data.put("GET", null);
		expected.put("/test/path1", controller2Data);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> mappedControllers = controllerMapper.map(controllers);
		
		testMapping(expected, mappedControllers);
	}
	
	@Test
	public void testWithThreeControllers() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestClass1.class);
		controllers.add(ControllerTestClass2.class);
		controllers.add(ControllerTestClass3.class);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> expected = new HashMap<>();
		Map<String, Pair<Class<?>, Method>> controllerGetData = new HashMap<>();
		controllerGetData.put("GET", null);
		expected.put("/test/path1", controllerGetData);
		expected.put("/test/path2", controllerGetData);
		expected.put("/test/path3", controllerGetData);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> mappedControllers = controllerMapper.map(controllers);
		
		testMapping(expected, mappedControllers);
	}
	
	@Test
	public void testWithPostBaseControllers() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestClass4.class);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> expected = new HashMap<>();
		Map<String, Pair<Class<?>, Method>> controllerGetData = new HashMap<>();
		controllerGetData.put("GET", null);
		expected.put("/test/path4", controllerGetData);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> mappedControllers = controllerMapper.map(controllers);
		
		testMapping(expected, mappedControllers);
	}
	
	@Test
	public void testWithFourControllers() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestClass1.class);
		controllers.add(ControllerTestClass2.class);
		controllers.add(ControllerTestClass3.class);
		controllers.add(ControllerTestClass4.class);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> expected = new HashMap<>();
		Map<String, Pair<Class<?>, Method>> controllerGetData = new HashMap<>();
		controllerGetData.put("GET", null);
		expected.put("/test/path1", controllerGetData);
		expected.put("/test/path2", controllerGetData);
		expected.put("/test/path3", controllerGetData);
		expected.put("/test/path4", controllerGetData);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> mappedControllers = controllerMapper.map(controllers);
		
		testMapping(expected, mappedControllers);
	}
	
	@Test
	public void testWithApiControllers() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ApiControllerTestClass1.class);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> expected = new HashMap<>();
		Map<String, Pair<Class<?>, Method>> controllerGetData = new HashMap<>();
		Map<String, Pair<Class<?>, Method>> controllerPostData = new HashMap<>();
		controllerGetData.put("GET", null);
		controllerPostData.put("POST", null);
		expected.put("/api/path1", controllerPostData);
		expected.put("/api/path2", controllerGetData);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> mappedControllers = controllerMapper.map(controllers);
		
		testMapping(expected, mappedControllers);
	}
	
	@Test
	public void testWithAllTestControllers() {
		List<Class<?>> controllers = new ArrayList<>();
		controllers.add(ControllerTestClass1.class);
		controllers.add(ControllerTestClass2.class);
		controllers.add(ControllerTestClass3.class);
		controllers.add(ControllerTestClass4.class);
		controllers.add(ApiControllerTestClass1.class);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> expected = new HashMap<>();
		Map<String, Pair<Class<?>, Method>> controllerGetData = new HashMap<>();
		Map<String, Pair<Class<?>, Method>> controllerPostData = new HashMap<>();
		controllerGetData.put("GET", null);
		controllerPostData.put("POST", null);
		expected.put("/test/path1", controllerGetData);
		expected.put("/test/path2", controllerGetData);
		expected.put("/test/path3", controllerGetData);
		expected.put("/test/path4", controllerGetData);
		expected.put("/api/path1", controllerPostData);
		expected.put("/api/path2", controllerGetData);
		
		Map<String, Map<String, Pair<Class<?>, Method>>> mappedControllers = controllerMapper.map(controllers);
		
		testMapping(expected, mappedControllers);
	}

}
