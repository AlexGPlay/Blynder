package atrahasis.core.util;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.blynder.core.finder.AutowiredFinder;
import org.blynder.core.mapper.AutowiredMapper;
import org.blynder.core.util.SystemInstanceManager;
import org.junit.Test;

import atrahasis.testClasses.RepositoryTestClass1;
import atrahasis.testClasses.ServiceTestClass;

public class SystemInstanceManagerTest {

	private AutowiredMapper autowiredMapper = new AutowiredMapper();
	private AutowiredFinder autowiredFinder = new AutowiredFinder();
	
	@Test
	public void testBeanInstanceManagerNonStorable() {
		try {
			List<Class<?>> beans = new ArrayList<>();
			beans.add(RepositoryTestClass1.class);
			beans.add(ServiceTestClass.class);
			SystemInstanceManager.initInstanceSaver(beans, autowiredMapper, autowiredFinder);

			ServiceTestClass testClass1 = (ServiceTestClass) SystemInstanceManager.lookForInstance(ServiceTestClass.class);
			assertNotNull(testClass1);
			ServiceTestClass testClass2 = (ServiceTestClass) SystemInstanceManager.lookForInstance(ServiceTestClass.class);
			assertTrue(testClass1 != testClass2);
		} catch (InstantiationException | IllegalAccessException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error creating the autowiring: " + exceptionAsString);
		}
	}
	
	@Test
	public void testBeanInstanceManagerStorable() {
		try {
			List<Class<?>> beans = new ArrayList<>();
			beans.add(RepositoryTestClass1.class);
			SystemInstanceManager.initInstanceSaver(beans, autowiredMapper, autowiredFinder);

			RepositoryTestClass1 testClass1 = (RepositoryTestClass1) SystemInstanceManager.lookForInstance(RepositoryTestClass1.class);
			assertNotNull(testClass1);
			RepositoryTestClass1 testClass2 = (RepositoryTestClass1) SystemInstanceManager.lookForInstance(RepositoryTestClass1.class);
			assertTrue(testClass1 == testClass2);
		} catch (InstantiationException | IllegalAccessException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error creating the autowiring: " + exceptionAsString);
		}
	}

}
