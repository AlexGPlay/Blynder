package atrahasis.core.mapper;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import atrahasis.core.util.Pair;

public interface IControllerMapper {

	public Map<String, Pair<Class<?>,Method>> map(List<Class<?>> classes);
	
}
