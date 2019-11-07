package atrahasis.core.finder;

import java.util.List;

public interface IClassFinder {

	public List<Class<?>> findClasses() throws ClassNotFoundException;
	
}
