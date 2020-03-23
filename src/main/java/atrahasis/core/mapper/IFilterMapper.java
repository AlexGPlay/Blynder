package atrahasis.core.mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * This interface provides the signature of the method that will create a relation
 * of 1 controller to N filters. For example, if there is a FooController that is
 * filtered with FooFilter1 and FooFilter2 there will be an entry like this: 
 * { FooController.class => [FooFilter1.class, FooFilter2.class] }
 *
 */
public interface IFilterMapper {

	/**
	 * 
	 * Given the list of all the controllers and filter of the application, this
	 * method will map the relationships between them.
	 * 
	 * @param controllers of the application.
	 * @param filters of the application.
	 * @return
	 * A map that contains what filter apply to what controllers. For example, 
	 * if there is a FooController that is filtered with FooFilter1 and FooFilter2 
	 * there will be an entry like this: { FooController.class => [FooFilter1.class, FooFilter2.class] }
	 * 
	 */
	public Map<Class<?>, List<Class<?>>> map(List<Class<?>> controllers, List<Class<?>> filters);
	
}
