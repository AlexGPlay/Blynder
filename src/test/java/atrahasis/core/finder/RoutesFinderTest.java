package atrahasis.core.finder;


import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import atrahasis.core.util.Pair;

public class RoutesFinderTest {

	private RoutesFinder routesFinder = new RoutesFinder();
	
	private void assertSearch(String entUrl, String objUrl, Map<String,Object> params) {
		HashMap<String, Pair<Class<?>,Method>> urls = new HashMap<>();
		urls.put(objUrl, null);
	
		Pair<String, Map<String,Object>> paramsOb = routesFinder.findRoute(urls, entUrl);
		assertEquals(params.toString(), paramsOb.object2.toString());
	}
	
	private void assertSearchNull(String entUrl, String objUrl) {
		HashMap<String, Pair<Class<?>,Method>> urls = new HashMap<>();
		urls.put(objUrl, null);
	
		Pair<String, Map<String,Object>> paramsOb = routesFinder.findRoute(urls, entUrl);
		assertEquals(null, paramsOb);
	}
	
	@Test
	public void testNoUrl() {
		String testUrl = "/test/noParams";
		String enterUrl = "/test";
		
		assertSearchNull(enterUrl, testUrl);
	}
	
	@Test
	public void testNoParams() {
		String testUrl = "/test/noParams";
		String enterUrl = "/test/noParams";
		
		Map<String,Object> params = new HashMap<>();
		
		assertSearch(enterUrl, testUrl, params);
	}
	
	@Test
	public void testOneParameterFirst() {
		String testUrl = "/{test}/paramsone";
		String enterUrl = "/firstparam/paramsone";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test", "firstparam");
		
		assertSearch(enterUrl, testUrl, params);
	}
	
	@Test
	public void testOneParameterSecond_1() {
		String testUrl = "/paramOne/{test}";
		String enterUrl = "/paramOne/firstparam";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test", "firstparam");
		
		assertSearch(enterUrl, testUrl, params);
	}
	
	@Test
	public void testOneParameterSecond_2() {
		String testUrl = "/paramOne/{test}/";
		String enterUrl = "/paramOne/firstparam/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test", "firstparam");
		
		assertSearch(enterUrl, testUrl, params);
	}
	
	@Test
	public void testOneParameterSecond_3() {
		String testUrl = "/paramOne/{test}/view";
		String enterUrl = "/paramOne/firstparam/view";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test", "firstparam");
		
		assertSearch(enterUrl, testUrl, params);
	}
	
	@Test
	public void testTwoParameterFirst_1() {
		String testUrl = "/{test1}/{test2}";
		String enterUrl = "/firstparam/paramsone";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		assertSearch(enterUrl, testUrl, params);
	}
	
	@Test
	public void testTwoParameterFirst_2() {
		String testUrl = "/{test1}/{test2}/";
		String enterUrl = "/firstparam/paramsone/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		assertSearch(enterUrl, testUrl, params);
	}
	
	@Test
	public void testTwoParameterFirst_3() {
		String testUrl = "/{test1}/{test2}/view";
		String enterUrl = "/firstparam/paramsone/view";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		assertSearch(enterUrl, testUrl, params);
	}
	
	@Test
	public void testTwoParameterFirst_4() {
		String testUrl = "/{test1}/{test2}/view/";
		String enterUrl = "/firstparam/paramsone/view/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		assertSearch(enterUrl, testUrl, params);
	}
	
	@Test
	public void testTwoParameter_1() {
		String testUrl = "/{test1}/view/{test2}/";
		String enterUrl = "/firstparam/view/paramsone/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		assertSearch(enterUrl, testUrl, params);
	}
	
	@Test
	public void testTwoParameter_2() {
		String testUrl = "/view/{test1}/{test2}/";
		String enterUrl = "/view/firstparam/paramsone/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		assertSearch(enterUrl, testUrl, params);
	}
	
}
