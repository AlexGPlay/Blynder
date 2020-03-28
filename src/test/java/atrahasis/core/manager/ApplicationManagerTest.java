package atrahasis.core.manager;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import atrahasis.core.network.Request;
import atrahasis.core.network.Response;
import atrahasis.testClasses.ApplicationManagerTestClass;
import atrahasis.testClasses.ControllerTestApi;
import atrahasis.testClasses.ControllerTestClass2;
import atrahasis.testClasses.ControllerTestFxml;
import atrahasis.testClasses.ControllerTestHtml;
import atrahasis.testClasses.ControllerTestInvalid;
import atrahasis.testClasses.ControllerTestNull;
import atrahasis.testClasses.ControllerTestSwing;

public class ApplicationManagerTest {

	private ApplicationManagerTestClass appManager;
	
	@Test
	public void testWithInvalidParams() {
		try {
			ApplicationManagerTestClass.setClasses(new ArrayList<Class<?>>());
			appManager = new ApplicationManagerTestClass();
			Response resp = appManager.navigate(null);
			assertEquals(404, resp.getStatusCode());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error saving the file: " + exceptionAsString);
		}
	}
	
	@Test
	public void testWithInvalidPath() {
		try {
			ApplicationManagerTestClass.setClasses(new ArrayList<Class<?>>());
			appManager = new ApplicationManagerTestClass();
			Response resp = appManager.navigate("");
			assertEquals(404, resp.getStatusCode());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error saving the file: " + exceptionAsString);
		}
	}
	
	@Test
	public void testWithInvalidPathMethod() {
		try {
			List<Class<?>> classes = new ArrayList<Class<?>>();
			classes.add(ControllerTestClass2.class);
			
			ApplicationManagerTestClass.setClasses(classes);
			appManager = new ApplicationManagerTestClass();
			Response resp = appManager.navigate("/test/path1", new Request("/test/path1", "POST"));
			assertEquals(404, resp.getStatusCode());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error saving the file: " + exceptionAsString);
		}
	}
	
	@Test
	public void testWithApiView() {
		try {
			List<Class<?>> classes = new ArrayList<Class<?>>();
			classes.add(ControllerTestApi.class);
			
			ApplicationManagerTestClass.setClasses(classes);
			appManager = new ApplicationManagerTestClass();
			Response resp = appManager.navigate("/test");
			assertEquals(200, resp.getStatusCode());
			assertEquals("application/api", resp.getResponseType());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error saving the file: " + exceptionAsString);
		}
	}
	
	@Test
	public void testWithHtmlView() {
		try {
			List<Class<?>> classes = new ArrayList<Class<?>>();
			classes.add(ControllerTestHtml.class);
			
			ApplicationManagerTestClass.setClasses(classes);
			appManager = new ApplicationManagerTestClass();
			Response resp = appManager.navigate("/test");
			assertEquals(200, resp.getStatusCode());
			assertEquals("application/html", resp.getResponseType());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error saving the file: " + exceptionAsString);
		}
	}
	
	@Test
	public void testWithFxmlView() {
		try {
			List<Class<?>> classes = new ArrayList<Class<?>>();
			classes.add(ControllerTestFxml.class);
			
			ApplicationManagerTestClass.setClasses(classes);
			appManager = new ApplicationManagerTestClass();
			Response resp = appManager.navigate("/test");
			assertEquals(200, resp.getStatusCode());
			assertEquals("application/fxml", resp.getResponseType());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error saving the file: " + exceptionAsString);
		}
	}
	
	@Test
	public void testWithSwingView() {
		try {
			List<Class<?>> classes = new ArrayList<Class<?>>();
			classes.add(ControllerTestSwing.class);
			
			ApplicationManagerTestClass.setClasses(classes);
			appManager = new ApplicationManagerTestClass();
			Response resp = appManager.navigate("/test");
			assertEquals(200, resp.getStatusCode());
			assertEquals("application/swing", resp.getResponseType());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error saving the file: " + exceptionAsString);
		}
	}

	@Test
	public void testWithInvalidView() {
		try {
			List<Class<?>> classes = new ArrayList<Class<?>>();
			classes.add(ControllerTestInvalid.class);
			
			ApplicationManagerTestClass.setClasses(classes);
			appManager = new ApplicationManagerTestClass();
			Response resp = appManager.navigate("/test");
			assertEquals(500, resp.getStatusCode());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error saving the file: " + exceptionAsString);
		}
	}
	
	@Test
	public void testWithNullView() {
		try {
			List<Class<?>> classes = new ArrayList<Class<?>>();
			classes.add(ControllerTestNull.class);
			
			ApplicationManagerTestClass.setClasses(classes);
			appManager = new ApplicationManagerTestClass();
			Response resp = appManager.navigate("/test");
			assertEquals(500, resp.getStatusCode());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error saving the file: " + exceptionAsString);
		}
	}
	
}
