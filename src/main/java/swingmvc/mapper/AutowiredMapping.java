package swingmvc.mapper;

import java.lang.reflect.Field;
import java.util.List;

import swingmvc.finder.AutowiredFinder;
import swingmvc.util.InstanceSaver;
import swingmvc.util.Pair;

public class AutowiredMapping {
	
	public void mapAutowired(List<Class<?>> classes) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		List<Pair<Class<?>,Field>> fields = new AutowiredFinder().findAutowired(classes);
		
		for(Pair<Class<?>,Field> pair : fields)
			initField(pair,classes);
	}
	
	private void initField(Pair<Class<?>,Field> field, List<Class<?>> classes) throws IllegalArgumentException, IllegalAccessException, InstantiationException{
		Class<?> clazz = getFieldClass(field.object2, classes);
		Field f = field.object2;
		
		Object o = InstanceSaver.lookForInstance(clazz);
		Object c = InstanceSaver.lookForInstance(field.object1);
		
		f.setAccessible(true);
		f.set(c, o);
		f.setAccessible(false);
	}
	
	private Class<?> getFieldClass(Field field, List<Class<?>> classes){
		return 	classes.stream()
				.filter(c -> c.equals(field.getType()))
				.findFirst()
				.orElse(null);
	}
	
}
