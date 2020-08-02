package atrahasis.core.mapper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.blynder.core.mapper.FilterMapper;
import org.junit.Before;
import org.junit.Test;

import atrahasis.testClasses.ControllerTestClass1;
import atrahasis.testClasses.FilterTestClass1;
import atrahasis.testClasses.FilterTestClass2;
import atrahasis.testClasses.FilterTestClass3;
import atrahasis.testClasses.FilteredController1;
import atrahasis.testClasses.FilteredController2;
import atrahasis.testClasses.FilteredController3;
import atrahasis.testClasses.FilteredController4;

public class FilterMapperTest {

	private FilterMapper filterMapper = new FilterMapper();
	private List<Class<?>> filters = new ArrayList<>();
	
	@Before
	public void setFilters() {
		filters.add(FilterTestClass1.class);
		filters.add(FilterTestClass2.class);
		filters.add(FilterTestClass3.class);
	}
	
	@Test
	public void testWithNoFilteredClass() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(ControllerTestClass1.class);
		Map<Class<?>, List<Class<?>>> expected = new HashMap<>();
		
		Map<Class<?>, List<Class<?>>> result = filterMapper.map(classes, filters);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testWithEmptyFilteredClass() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(FilteredController1.class);
		Map<Class<?>, List<Class<?>>> expected = new HashMap<>();
		expected.put(FilteredController1.class, new ArrayList<>());
		
		Map<Class<?>, List<Class<?>>> result = filterMapper.map(classes, filters);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testWithClassFilteredController() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(FilteredController2.class);
		Map<Class<?>, List<Class<?>>> expected = new HashMap<>();
		List<Class<?>> filtersController2 = new ArrayList<>();
		filtersController2.add(FilterTestClass1.class);
		filtersController2.add(FilterTestClass2.class);
		
		expected.put(FilteredController2.class, filtersController2);
		
		Map<Class<?>, List<Class<?>>> result = filterMapper.map(classes, filters);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testWithStringFilteredController() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(FilteredController4.class);
		
		Map<Class<?>, List<Class<?>>> expected = new HashMap<>();
		List<Class<?>> filtersController4 = new ArrayList<>();
		filtersController4.add(FilterTestClass1.class);
		filtersController4.add(FilterTestClass3.class);
		
		expected.put(FilteredController4.class, filtersController4);
		
		Map<Class<?>, List<Class<?>>> result = filterMapper.map(classes, filters);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testWithStringAndClassFilteredController() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(FilteredController3.class);
		
		Map<Class<?>, List<Class<?>>> expected = new HashMap<>();
		List<Class<?>> filtersController3 = new ArrayList<>();
		filtersController3.add(FilterTestClass1.class);
		filtersController3.add(FilterTestClass2.class);
		filtersController3.add(FilterTestClass3.class);
		
		expected.put(FilteredController3.class, filtersController3);
		
		Map<Class<?>, List<Class<?>>> result = filterMapper.map(classes, filters);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testWithMultipleControllers() {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(FilteredController1.class);
		classes.add(FilteredController2.class);
		classes.add(FilteredController3.class);
		classes.add(FilteredController4.class);
		
		Map<Class<?>, List<Class<?>>> expected = new HashMap<>();
		List<Class<?>> filtersController2 = new ArrayList<>();
		List<Class<?>> filtersController3 = new ArrayList<>();
		List<Class<?>> filtersController4 = new ArrayList<>();
		
		filtersController2.add(FilterTestClass1.class);
		filtersController2.add(FilterTestClass2.class);
		
		filtersController3.add(FilterTestClass1.class);
		filtersController3.add(FilterTestClass2.class);
		filtersController3.add(FilterTestClass3.class);
		
		filtersController4.add(FilterTestClass1.class);
		filtersController4.add(FilterTestClass3.class);
		
		expected.put(FilteredController1.class, new ArrayList<>());
		expected.put(FilteredController2.class, filtersController2);
		expected.put(FilteredController3.class, filtersController3);
		expected.put(FilteredController4.class, filtersController4);
		
		Map<Class<?>, List<Class<?>>> result = filterMapper.map(classes, filters);
		
		assertEquals(expected, result);
	}

}
