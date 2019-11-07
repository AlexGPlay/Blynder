package atrahasis.core.finder;

import java.util.List;

public interface IControllerFinder {

	public List<Class<?>> findControllers(List<Class<?>> classes);
	
}
