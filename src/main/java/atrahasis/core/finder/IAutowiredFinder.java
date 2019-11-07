package atrahasis.core.finder;

import java.lang.reflect.Field;
import java.util.List;

import atrahasis.core.util.Pair;

public interface IAutowiredFinder {

	public List<Pair<Class<?>,Field>> findAutowired(List<Class<?>> classes);
	
}
