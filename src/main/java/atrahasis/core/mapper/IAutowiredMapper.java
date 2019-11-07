package atrahasis.core.mapper;

import java.lang.reflect.Field;
import java.util.List;

import atrahasis.core.util.Pair;

public interface IAutowiredMapper {

	public void mapAutowired(List<Class<?>> classes, List<Pair<Class<?>,Field>> fields) throws IllegalArgumentException, IllegalAccessException, InstantiationException;
	
}
