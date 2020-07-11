package atrahasis.core.mapper;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import atrahasis.core.finder.AutowiredFinder;
import atrahasis.core.util.SystemInstanceManager;
import atrahasis.testClasses.AutowiredBeanTestClass;
import atrahasis.testClasses.BeanParamNoAutowiredTestClass;
import atrahasis.testClasses.RepositoryTestClass1;
import atrahasis.testClasses.ServiceTestClass;

public class AutowiredMapperTest {

	private AutowiredMapper autowiredMapper = new AutowiredMapper();
	private AutowiredFinder autowiredFinder = new AutowiredFinder();
	
	@Test
	public void testAutowiringNonStorable() {
		try {
			AutowiredBeanTestClass testClass = new AutowiredBeanTestClass();
			List<Class<?>> classes = new ArrayList<>();
			classes.add(testClass.getClass());
			
			List<Class<?>> beans = new ArrayList<>();
			beans.add(BeanParamNoAutowiredTestClass.class);
			SystemInstanceManager.initInstanceSaver(beans, autowiredMapper, autowiredFinder);
			
			assertNull(testClass.ob);
			autowiredMapper.mapAutowired(testClass, beans, autowiredFinder.findAutowired(classes));
			assertNotNull(testClass.ob);
			Object temp = testClass.ob;
			autowiredMapper.mapAutowired(testClass, beans, autowiredFinder.findAutowired(classes));
			assertFalse(temp == testClass.ob);
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error creating the autowiring: " + exceptionAsString);
		}
		
	}
	
	@Test
	public void testAutowiringStorable() {
		try {
			ServiceTestClass testClass = new ServiceTestClass();
			List<Class<?>> classes = new ArrayList<>();
			classes.add(testClass.getClass());
			
			List<Class<?>> beans = new ArrayList<>();
			beans.add(RepositoryTestClass1.class);
			SystemInstanceManager.initInstanceSaver(beans, autowiredMapper, autowiredFinder);
			
			assertNull(testClass.ob);
			autowiredMapper.mapAutowired(testClass, beans, autowiredFinder.findAutowired(classes));
			assertNotNull(testClass.ob);
			Object temp = testClass.ob;
			autowiredMapper.mapAutowired(testClass, beans, autowiredFinder.findAutowired(classes));
			assertTrue(temp == testClass.ob);
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error creating the autowiring: " + exceptionAsString);
		}
		
	}

}
