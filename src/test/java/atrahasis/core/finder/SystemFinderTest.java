package atrahasis.core.finder;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import atrahasis.testClasses.ControllerTestClass1;
import atrahasis.testClasses.CustomBeanTestClass;
import atrahasis.testClasses.NoAnnotedTestClass;

public class SystemFinderTest {

	private SystemFinder bean = new SystemFinder();
	
	@Test
	public void testEmptyClasses() {
		List<Class<?>> classes = new ArrayList<>();
		
		List<Class<?>> obj = new ArrayList<>();
		
		assertEquals( obj, bean.findClasses(classes) );
	}
	
	@Test
	public void testNoControllers() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(NoAnnotedTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		
		assertEquals( obj, bean.findClasses(classes) );
	}
	
	@Test
	public void testOneController_1() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ControllerTestClass1.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(ControllerTestClass1.class);
		
		assertEquals( obj, bean.findClasses(classes) );
	}
	
	@Test
	public void testOneController_2() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ControllerTestClass1.class);
		classes.add(NoAnnotedTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(ControllerTestClass1.class);
		
		assertEquals( obj, bean.findClasses(classes) );
	}


	@Test
	public void testTwoControllers_1() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ControllerTestClass1.class);
		classes.add(ControllerTestClass1.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(ControllerTestClass1.class);
		obj.add(ControllerTestClass1.class);
		
		assertEquals( obj, bean.findClasses(classes) );
	}
	
	@Test
	public void testTwoControllers_2() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ControllerTestClass1.class);
		classes.add(ControllerTestClass1.class);
		classes.add(NoAnnotedTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(ControllerTestClass1.class);
		obj.add(ControllerTestClass1.class);
		
		assertEquals( obj, bean.findClasses(classes) );
	}
	
	@Test
	public void testOneBean_1() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(CustomBeanTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(CustomBeanTestClass.class);
		
		assertEquals( obj, bean.findClasses(classes) );
	}
	
	@Test
	public void testOneBean_2() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(CustomBeanTestClass.class);
		classes.add(NoAnnotedTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(CustomBeanTestClass.class);
		
		assertEquals( obj, bean.findClasses(classes) );
	}
	
	@Test
	public void testTwoBeans_1() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(CustomBeanTestClass.class);
		classes.add(ControllerTestClass1.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(CustomBeanTestClass.class);
		obj.add(ControllerTestClass1.class);
		
		assertEquals( obj, bean.findClasses(classes) );
	}
	
	@Test
	public void testTwoBeans_2() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(CustomBeanTestClass.class);
		classes.add(ControllerTestClass1.class);
		classes.add(NoAnnotedTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(CustomBeanTestClass.class);
		obj.add(ControllerTestClass1.class);
		
		assertEquals( obj, bean.findClasses(classes) );
	}

}
