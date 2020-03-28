package atrahasis.core.finder;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import atrahasis.testClasses.FilterTestClass1;
import atrahasis.testClasses.NoAnnotedTestClass;

public class FilterFinderTest {

	private FilterFinder finder = new FilterFinder();
	
	@Test
	public void testEmptyClasses() {
		List<Class<?>> classes = new ArrayList<>();
		
		List<Class<?>> obj = new ArrayList<>();
		
		assertEquals( obj, finder.findFilters(classes) );
	}
	
	@Test
	public void testNoControllers() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(NoAnnotedTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		
		assertEquals( obj, finder.findFilters(classes) );
	}
	
	@Test
	public void testOneFilter_1() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(FilterTestClass1.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(FilterTestClass1.class);
		
		assertEquals( obj, finder.findFilters(classes) );
	}
	
	@Test
	public void testOneFilter_2() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(FilterTestClass1.class);
		classes.add(NoAnnotedTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(FilterTestClass1.class);
		
		assertEquals( obj, finder.findFilters(classes) );
	}


	@Test
	public void testTwoFilters_1() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(FilterTestClass1.class);
		classes.add(FilterTestClass1.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(FilterTestClass1.class);
		obj.add(FilterTestClass1.class);
		
		assertEquals( obj, finder.findFilters(classes) );
	}
	
	@Test
	public void testTwoFilters_2() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(FilterTestClass1.class);
		classes.add(FilterTestClass1.class);
		classes.add(NoAnnotedTestClass.class);
		
		List<Class<?>> obj = new ArrayList<>();
		obj.add(FilterTestClass1.class);
		obj.add(FilterTestClass1.class);
		
		assertEquals( obj, finder.findFilters(classes) );
	}

}
