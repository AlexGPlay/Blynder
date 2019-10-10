package atrahasis.core.finder;


import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import atrahasis.core.util.Pair;

public class RoutesFinderTest {

	private RoutesFinder routesFinder = new RoutesFinder();
	private HashMap<String, Pair<Class<?>,Method>> map = new HashMap<>();
	private String[] toAddUrls = {
		"/test/{test1}/{test2}/view"	
	};
	
	private String[] lookForUrls = {
		"/test/3/prueba/view"	
	};
	
	private List<HashMap<String,Object>> results;
	
	@Before
	public void setUp() {
		for(String url : toAddUrls) {
			map.put(url, null);
		}
		
		results = new ArrayList<>();
		
		HashMap<String,Object> res1 = new HashMap<>();
		res1.put("test1", 3);
		res1.put("test2", "prueba");
		
		results.add(res1);
		
	}
	
	@Test
	public void testUrls() {
		for(int i=0;i<lookForUrls.length;i++) {
			Pair<String, Map<String,Object>> temp = routesFinder.findRoute(map, lookForUrls[i]);
			assertEquals(results.get(i), temp.object2);
		}
	}

}
