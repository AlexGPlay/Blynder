package atrahasis.core.util;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import atrahasis.core.annotations.ApiController;
import atrahasis.core.annotations.Bean;
import atrahasis.core.annotations.Controller;
import atrahasis.core.annotations.Filter;
import atrahasis.core.annotations.Service;
import atrahasis.data.annotations.Repository;
import atrahasis.testClasses.*;

public class BeanAnnotationsTest {

	@Test
	public void testBeans() {
		List<Class<? extends Annotation>> expectedBeans = new ArrayList<>();
		expectedBeans.add(Controller.class);
		expectedBeans.add(Bean.class);
		expectedBeans.add(Filter.class);
		expectedBeans.add(Service.class);
		expectedBeans.add(Repository.class);
		expectedBeans.add(ApiController.class);
		
		List<Class<? extends Annotation>> res = BeanAnnotations.getBeans();
		
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
			assertEquals(expectedBeans.get(c), BeanAnnotations.isStorable(c));
	}

}
