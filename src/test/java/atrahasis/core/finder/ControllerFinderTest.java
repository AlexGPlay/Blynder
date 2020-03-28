package atrahasis.core.finder;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import atrahasis.testClasses.ApiControllerTestClass1;
import atrahasis.testClasses.ControllerTestClass1;
import atrahasis.testClasses.NoAnnotedTestClass;

public class ControllerFinderTest {

	private ControllerFinder controller = new ControllerFinder();
	
	@Test
	public void testEmptyClasses() {
		List<Class<?>> classes = new ArrayList<>();
		
		List<Class<?>> obj = new ArrayList<>();
		
		assertEquals( obj, controller.findControllers(classes) );
	}
	
	@Test
	public void testNoControllers() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(NoAnnotedTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		
		assertEquals( obj, controller.findControllers(classes) );
	}
	
	@Test
	public void testOneController_1() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ControllerTestClass1.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(ControllerTestClass1.class);
		
		assertEquals( obj, controller.findControllers(classes) );
	}
	
	@Test
	public void testOneController_2() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ControllerTestClass1.class);
		classes.add(NoAnnotedTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(ControllerTestClass1.class);
		
		assertEquals( obj, controller.findControllers(classes) );
	}


	@Test
	public void testTwoControllers_1() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ControllerTestClass1.class);
		classes.add(ControllerTestClass1.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(ControllerTestClass1.class);
		obj.add(ControllerTestClass1.class);
		
		assertEquals( obj, controller.findControllers(classes) );
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
		
		assertEquals( obj, controller.findControllers(classes) );
	}
	
	@Test
	public void testOneApiController_1() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ApiControllerTestClass1.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(ApiControllerTestClass1.class);
		
		assertEquals( obj, controller.findControllers(classes) );
	}
	
	@Test
	public void testOneApiController_2() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ApiControllerTestClass1.class);
		classes.add(NoAnnotedTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(ApiControllerTestClass1.class);
		
		assertEquals( obj, controller.findControllers(classes) );
	}
	
	@Test
	public void testTwoControllers_3() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ControllerTestClass1.class);
		classes.add(ApiControllerTestClass1.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(ControllerTestClass1.class);
		obj.add(ApiControllerTestClass1.class);
		
		assertEquals( obj, controller.findControllers(classes) );
	}
	
	@Test
	public void testTwoControllers_4() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ControllerTestClass1.class);
		classes.add(ApiControllerTestClass1.class);
		classes.add(NoAnnotedTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(ControllerTestClass1.class);
		obj.add(ApiControllerTestClass1.class);
		
		assertEquals( obj, controller.findControllers(classes) );
	}
	
}
