package atrahasis.core.finder;

import java.util.List;

public interface IBeanFinder {

	public List<Class<?>> findBeans(List<Class<?>> clazz);
	
}
