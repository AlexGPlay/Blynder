package atrahasis.core.finder;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import atrahasis.core.util.Pair;

public class RoutesFinderTest {

	private RoutesFinder routesFinder = new RoutesFinder();
	private Map<String, Pair<Class<?>,Method>> map = new HashMap<>();
	private String[] toAddUrls = {
		"/test/{test}/{test2}/view"	
	};
	
	private String[] lookForUrls = {
		"/test/3/prueba/view"	
	};
	
	@Before
	public void setUp() {
		for(String url : toAddUrls) {
			map.put(url, null);
		}
	}
	
	@Test
	public void testUrls() {
		for(String url : lookForUrls) {
			Pair<Class<?>,Method> temp = routesFinder.findRoute(map, url);
				
		}
	}

}
