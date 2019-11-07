package atrahasis.core.finder;

import java.lang.reflect.Method;
import java.util.Map;

import atrahasis.core.util.Pair;

public interface IRoutesFinder {

	public Pair<String, Map<String,Object>> findRoute(Map<String, Pair<Class<?>,Method>> routes, String url);
	
}
