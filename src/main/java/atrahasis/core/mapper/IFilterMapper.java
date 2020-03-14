package atrahasis.core.mapper;

import java.util.List;
import java.util.Map;

public interface IFilterMapper {

	public Map<Class<?>, List<Class<?>>> map(List<Class<?>> controllers, List<Class<?>> filters);
	
}
