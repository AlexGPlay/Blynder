package atrahasis.core.finder;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import atrahasis.core.util.Pair;
import atrahasis.testClasses.AutowiredBeanTestClass;
import atrahasis.testClasses.AutowiredControllerTestClass;
import atrahasis.testClasses.BeanParamNoAutowiredTestClass;
import atrahasis.testClasses.ControllerTestClass1;
import atrahasis.testClasses.CustomBeanTestClass;
import atrahasis.testClasses.NoAnnotedTestClass;

public class AutowiredFinderTest {

	private AutowiredFinder autowired = new AutowiredFinder();
	
	@Test
	public void testEmptyArray() {
		List<Class<?>> classes = new ArrayList<>();
		
		List<Pair<Class<?>, Field>> obj = new ArrayList<>();
		
		assertEquals( obj, autowired.findAutowired(classes) );
	}
	
	@Test
	public void testNoAutowired_1() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ControllerTestClass1.class);
		classes.add(CustomBeanTestClass.class);
		classes.add(NoAnnotedTestClass.class);
		
		List<Pair<Class<?>, Field>> obj = new ArrayList<>();
		
		assertEquals( obj, autowired.findAutowired(classes) );
	}
	
	@Test
	public void testNoAutowired_2() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(BeanParamNoAutowiredTestClass.class);
		
		List<Pair<Class<?>, Field>> obj = new ArrayList<>();
		
		assertEquals( obj, autowired.findAutowired(classes) );
	}
	
	@Test
	public void testAutowired_1() throws NoSuchFieldException, SecurityException {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(AutowiredControllerTestClass.class);
		
		assertEquals( 1, autowired.findAutowired(classes).size() );
	}
	
	@Test
	public void testAutowired_2() throws NoSuchFieldException, SecurityException {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(AutowiredBeanTestClass.class);
		
		assertEquals( 1, autowired.findAutowired(classes).size() );
	}
	
	@Test
	public void testAutowired_3() throws NoSuchFieldException, SecurityException {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(AutowiredControllerTestClass.class);
		classes.add(AutowiredBeanTestClass.class);
		
		assertEquals( 2, autowired.findAutowired(classes).size() );
	}
	
	@Test
	public void testAutowired_4() throws NoSuchFieldException, SecurityException {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(AutowiredControllerTestClass.class);
		classes.add(AutowiredBeanTestClass.class);
		classes.add(BeanParamNoAutowiredTestClass.class);
		
		assertEquals( 2, autowired.findAutowired(classes).size() );
	}

}
