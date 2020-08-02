package atrahasis.core.util;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.blynder.core.annotations.Bean;
import org.blynder.core.annotations.Controller;
import org.blynder.core.annotations.Filter;
import org.blynder.core.annotations.RestController;
import org.blynder.core.annotations.Service;
import org.blynder.core.util.SystemAnnotations;
import org.blynder.data.annotations.Repository;
import org.junit.Test;

import atrahasis.testClasses.*;

public class SystemAnnotationsTest {

	@Test
	public void testBeans() {
		List<Class<? extends Annotation>> expectedBeans = new ArrayList<>();
		expectedBeans.add(Controller.class);
		expectedBeans.add(Bean.class);
		expectedBeans.add(Filter.class);
		expectedBeans.add(Service.class);
		expectedBeans.add(Repository.class);
		expectedBeans.add(RestController.class);
		
		List<Class<? extends Annotation>> res = SystemAnnotations.getSystemAnnotations();
		
		for(Class<?> c : expectedBeans)
			if(!res.contains(c))
				fail("Expected to contain " + c);
	}
	
	@Test
	public void testStorable() {
		Map<Class<?>, Boolean> expectedBeans = new HashMap<>();
		expectedBeans.put(ApiControllerTestClass1.class, false);
		expectedBeans.put(ControllerTestClass1.class, false);
		expectedBeans.put(CustomBeanTestClass.class, false);
		expectedBeans.put(RepositoryTestClass1.class, true);
		
		for(Class<?> c : expectedBeans.keySet())
			assertEquals(expectedBeans.get(c), SystemAnnotations.isStorable(c));
	}

}
