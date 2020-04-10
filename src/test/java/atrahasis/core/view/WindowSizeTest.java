package atrahasis.core.view;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JPanel;

import org.junit.Test;

import atrahasis.core.template.Model;

public class WindowSizeTest {

	@Test
	public void testDefaultSizeEmpty() {
		try {
			WindowSize size = new WindowSize();
			WindowProps props = new WindowProps();
			
			Window window = new Window(size,props);
			assertEquals(100, window.getFrame().getWidth());
			assertEquals(100, window.getFrame().getHeight());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error: " + exceptionAsString);
		}
	}
	
	@Test
	public void testDefaultSize() {
		try {
			WindowSize size = new WindowSize(200, 200);
			WindowProps props = new WindowProps();
			
			Window window = new Window(size,props);
			assertEquals(200, window.getFrame().getWidth());
			assertEquals(200, window.getFrame().getHeight());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error: " + exceptionAsString);
		}
	}
	
	@Test
	public void testAutoSizing() {
		try {
			WindowSize size = new WindowSize();
			WindowProps props = new WindowProps();
			Window window = new Window(size,props);
			assertEquals(100, window.getFrame().getWidth());
			assertEquals(100, window.getFrame().getHeight());
			window.setView(new JPanel(), new Model(), size);
			assertEquals(136, window.getFrame().getWidth());
			assertEquals(49, window.getFrame().getHeight());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error: " + exceptionAsString);
		}
	}
	
	@Test
	public void testManualSizing() {
		try {
			WindowSize size = new WindowSize();
			WindowProps props = new WindowProps();
			Window window = new Window(size,props);
			assertEquals(100, window.getFrame().getWidth());
			assertEquals(100, window.getFrame().getHeight());
			
			WindowSize size2 = new WindowSize(200, 200);
			
			window.setView(new JPanel(), new Model(), size2);
			assertEquals(200, window.getFrame().getWidth());
			assertEquals(200, window.getFrame().getHeight());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error: " + exceptionAsString);
		}
	}
	
	@Test
	public void testAutoAfterManual() {
		try {
			WindowSize size = new WindowSize();
			WindowProps props = new WindowProps();
			Window window = new Window(size,props);
			assertEquals(100, window.getFrame().getWidth());
			assertEquals(100, window.getFrame().getHeight());
			
			WindowSize size2 = new WindowSize();
			size2.setDimensions(200, 200);
			
			window.setView(new JPanel(), new Model(), size2);
			assertEquals(200, window.getFrame().getWidth());
			assertEquals(200, window.getFrame().getHeight());
			
			window.setView(new JPanel(), new Model(), size);
			assertEquals(136, window.getFrame().getWidth());
			assertEquals(49, window.getFrame().getHeight());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error: " + exceptionAsString);
		}
	}
	
	@Test
	public void testResizable() {
		WindowSize size = new WindowSize(false);
		WindowProps props = new WindowProps();
		Window window = new Window(size,props);
		assertFalse(window.getFrame().isResizable());
		
		size = new WindowSize(true);
		window = new Window(size,props);
		assertTrue(window.getFrame().isResizable());
	}

}
