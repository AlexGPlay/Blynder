package atrahasis.core.util;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.blynder.core.util.ParamSorter;
import org.junit.Test;

import atrahasis.testClasses.*;

public class ParamSorterTest {

	private ParamSorter paramSorter = new ParamSorter();
	
	@Test
	public void testWithoutParams() {
		Class<?> clazz = ControllerTestClass2.class;
		try {
			Method m = clazz.getMethod("testPath1");
			List<Object> params = paramSorter.sortParameters(new HashMap<>(), m, null, null, null);
			assertEquals(params.size(), 0);
		} catch (NoSuchMethodException | SecurityException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error: " + exceptionAsString);
		}
	}
	
	@Test
	public void testWithParams() {
		Class<?> clazz = ControllerTestClass4.class;
		try {
			Method m = clazz.getMethod("testPath4", String.class);
			List<Object> params = paramSorter.sortParameters(new HashMap<>(), m, null, null, null);
			assertEquals(params.size(), 1);
		} catch (NoSuchMethodException | SecurityException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error: " + exceptionAsString);
		}
	}

}
