package atrahasis.core.finder;


import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.blynder.core.finder.RoutesFinder;
import org.blynder.core.util.Pair;
import org.junit.Test;

public class RoutesFinderTest {

	private RoutesFinder routesFinder = new RoutesFinder();
	private String[] methods = {"GET","HEAD","POST","PUT","DELETE","CONNECT","OPTIONS","TRACE","PATCH"};
	
	private void assertSearch(String entUrl, String objUrl, Map<String,Object> params) {
		HashMap<String, Map<String,Pair<Class<?>,Method>>> urls = new HashMap<>();
		Map<String,Pair<Class<?>,Method>> secondLevel = new HashMap<>();
		secondLevel.put("GET", new Pair<Class<?>, Method>(null, null));
		urls.put(objUrl, secondLevel);
	
		Pair<String, Map<String,Object>> paramsOb = routesFinder.findRoute(urls, entUrl, "GET");
		assertEquals(params.toString(), paramsOb.object2.toString());
	}
	
	private void assertSearchNull(String entUrl, String objUrl) {
		HashMap<String, Map<String,Pair<Class<?>,Method>>> urls = new HashMap<>();
		urls.put(objUrl, new HashMap<>());
	
		Pair<String, Map<String,Object>> paramsOb = routesFinder.findRoute(urls, entUrl, "GET");
		assertEquals(null, paramsOb);
	}
	
	private void assertSearchWithouMethod(String entUrl, String objUrl, String method) {
		HashMap<String, Map<String,Pair<Class<?>,Method>>> urls = new HashMap<>();
		Map<String,Pair<Class<?>,Method>> secondLevel = new HashMap<>();
		for(String s : methods)
			if(!s.equals(method))
				secondLevel.put(s, new Pair<Class<?>, Method>(null, null));
		urls.put(objUrl, secondLevel);
		
		Pair<String, Map<String,Object>> paramsOb = routesFinder.findRoute(urls, entUrl, method);
		assertEquals(null, paramsOb);
	}
	
	private void assertSearchWithMethod(String entUrl, String objUrl, Map<String,Object> params, String method) {
		HashMap<String, Map<String,Pair<Class<?>,Method>>> urls = new HashMap<>();
		Map<String,Pair<Class<?>,Method>> secondLevel = new HashMap<>();
		for(String s : methods)
			secondLevel.put(s, new Pair<Class<?>, Method>(null, null));
		urls.put(objUrl, secondLevel);
		
		Pair<String, Map<String,Object>> paramsOb = routesFinder.findRoute(urls, entUrl, method);
		assertEquals(params.toString(), paramsOb.object2.toString());
	}
	
	private void assertSearchWithMethodOnly(String entUrl, String objUrl, Map<String,Object> params, String method) {
		HashMap<String, Map<String,Pair<Class<?>,Method>>> urls = new HashMap<>();
		Map<String,Pair<Class<?>,Method>> secondLevel = new HashMap<>();
		secondLevel.put(method, new Pair<Class<?>, Method>(null, null));
		urls.put(objUrl, secondLevel);
		
		Pair<String, Map<String,Object>> paramsOb = routesFinder.findRoute(urls, entUrl, method);
		assertEquals(params.toString(), paramsOb.object2.toString());
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
	
	@Test
	public void testNotAvailableMethod() {
		String testUrl = "/test/noParams";
		String enterUrl = "/test";
		
		for(String s : methods)
			assertSearchWithouMethod(enterUrl, testUrl, s);
	}
	
	@Test
	public void testNoParamsWithMethods() {
		String testUrl = "/test/noParams";
		String enterUrl = "/test/noParams";
		
		Map<String,Object> params = new HashMap<>();
		
		for(String s : methods)
			assertSearchWithMethod(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testOneParameterFirstWithMethods() {
		String testUrl = "/{test}/paramsone";
		String enterUrl = "/firstparam/paramsone";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test", "firstparam");
		
		for(String s : methods)
			assertSearchWithMethod(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testOneParameterSecondWithMethods_1() {
		String testUrl = "/paramOne/{test}";
		String enterUrl = "/paramOne/firstparam";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test", "firstparam");
		
		for(String s : methods)
			assertSearchWithMethod(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testOneParameterSecondWithMethods_2() {
		String testUrl = "/paramOne/{test}/";
		String enterUrl = "/paramOne/firstparam/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test", "firstparam");
		
		for(String s : methods)
			assertSearchWithMethod(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testOneParameterSecondWithMethods_3() {
		String testUrl = "/paramOne/{test}/view";
		String enterUrl = "/paramOne/firstparam/view";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test", "firstparam");
		
		for(String s : methods)
			assertSearchWithMethod(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testTwoParameterFirstWithMethods_1() {
		String testUrl = "/{test1}/{test2}";
		String enterUrl = "/firstparam/paramsone";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		for(String s : methods)
			assertSearchWithMethod(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testTwoParameterFirstWithMethods_2() {
		String testUrl = "/{test1}/{test2}/";
		String enterUrl = "/firstparam/paramsone/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		for(String s : methods)
			assertSearchWithMethod(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testTwoParameterFirstWithMethods_3() {
		String testUrl = "/{test1}/{test2}/view";
		String enterUrl = "/firstparam/paramsone/view";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		for(String s : methods)
			assertSearchWithMethod(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testTwoParameterFirstWithMethods_4() {
		String testUrl = "/{test1}/{test2}/view/";
		String enterUrl = "/firstparam/paramsone/view/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		for(String s : methods)
			assertSearchWithMethod(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testTwoParameterWithMethods_1() {
		String testUrl = "/{test1}/view/{test2}/";
		String enterUrl = "/firstparam/view/paramsone/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		for(String s : methods)
			assertSearchWithMethod(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testTwoParameterWithMethods_2() {
		String testUrl = "/view/{test1}/{test2}/";
		String enterUrl = "/view/firstparam/paramsone/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		for(String s : methods)
			assertSearchWithMethod(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testNoParamsWithOnlyOneMethod() {
		String testUrl = "/test/noParams";
		String enterUrl = "/test/noParams";
		
		Map<String,Object> params = new HashMap<>();
		
		for(String s : methods)
			assertSearchWithMethodOnly(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testOneParameterFirstWithOnlyOneMethod() {
		String testUrl = "/{test}/paramsone";
		String enterUrl = "/firstparam/paramsone";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test", "firstparam");
		
		for(String s : methods)
			assertSearchWithMethodOnly(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testOneParameterSecondWithOnlyOneMethod_1() {
		String testUrl = "/paramOne/{test}";
		String enterUrl = "/paramOne/firstparam";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test", "firstparam");
		
		for(String s : methods)
			assertSearchWithMethodOnly(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testOneParameterSecondWithOnlyOneMethod_2() {
		String testUrl = "/paramOne/{test}/";
		String enterUrl = "/paramOne/firstparam/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test", "firstparam");
		
		for(String s : methods)
			assertSearchWithMethodOnly(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testOneParameterSecondWithOnlyOneMethod_3() {
		String testUrl = "/paramOne/{test}/view";
		String enterUrl = "/paramOne/firstparam/view";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test", "firstparam");
		
		for(String s : methods)
			assertSearchWithMethodOnly(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testTwoParameterFirstWithOnlyOneMethod_1() {
		String testUrl = "/{test1}/{test2}";
		String enterUrl = "/firstparam/paramsone";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		for(String s : methods)
			assertSearchWithMethodOnly(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testTwoParameterFirstWithOnlyOneMethod_2() {
		String testUrl = "/{test1}/{test2}/";
		String enterUrl = "/firstparam/paramsone/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		for(String s : methods)
			assertSearchWithMethodOnly(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testTwoParameterFirstWithOnlyOneMethod_3() {
		String testUrl = "/{test1}/{test2}/view";
		String enterUrl = "/firstparam/paramsone/view";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		for(String s : methods)
			assertSearchWithMethodOnly(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testTwoParameterFirstWithOnlyOneMethod_4() {
		String testUrl = "/{test1}/{test2}/view/";
		String enterUrl = "/firstparam/paramsone/view/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		for(String s : methods)
			assertSearchWithMethodOnly(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testTwoParameterWithOnlyOneMethod_1() {
		String testUrl = "/{test1}/view/{test2}/";
		String enterUrl = "/firstparam/view/paramsone/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		for(String s : methods)
			assertSearchWithMethodOnly(enterUrl, testUrl, params, s);
	}
	
	@Test
	public void testTwoParameterWithOnlyOneMethod_2() {
		String testUrl = "/view/{test1}/{test2}/";
		String enterUrl = "/view/firstparam/paramsone/";
		
		Map<String,Object> params = new HashMap<>();
		params.put("test1", "firstparam");
		params.put("test2", "paramsone");
		
		for(String s : methods)
			assertSearchWithMethodOnly(enterUrl, testUrl, params, s);
	}
	
}
