package atrahasis.core.view;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JPanel;

import org.blynder.core.configurator.CustomConfigurator;
import org.blynder.core.template.Model;
import org.blynder.core.view.Window;
import org.blynder.core.view.WindowProps;
import org.blynder.core.view.WindowSize;
import org.junit.Test;

public class WindowSizeTest {

	@Test
	public void testDefaultSizeEmpty() {
		try {
			WindowSize size = new WindowSize();
			WindowProps props = new WindowProps();
			CustomConfigurator config = new CustomConfigurator()
					.with(props)
					.with(size);
			
			Window window = new Window(config);
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
			CustomConfigurator config = new CustomConfigurator()
					.with(props)
					.with(size);
			
			Window window = new Window(config);
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
			CustomConfigurator config = new CustomConfigurator()
					.with(props)
					.with(size);
			
			Window window = new Window(config);
			
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
			CustomConfigurator config = new CustomConfigurator()
					.with(props)
					.with(size);
			
			Window window = new Window(config);
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
			CustomConfigurator config = new CustomConfigurator()
					.with(props)
					.with(size);
			
			Window window = new Window(config);
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
		CustomConfigurator config = new CustomConfigurator()
				.with(props)
				.with(size);
		
		Window window = new Window(config);
		assertFalse(window.getFrame().isResizable());
		
		size = new WindowSize(true);
		config = new CustomConfigurator()
				.with(props)
				.with(size);
		
		window = new Window(config);
		
		assertTrue(window.getFrame().isResizable());
	}

}
