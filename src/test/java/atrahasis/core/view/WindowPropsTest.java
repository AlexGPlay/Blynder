package atrahasis.core.view;

import static org.junit.Assert.*;

import org.junit.Test;

import atrahasis.core.configurator.CustomConfigurator;

public class WindowPropsTest {

	@Test
	public void testDefaultConstructor() {
		WindowProps props = new WindowProps();
		CustomConfigurator config = new CustomConfigurator().with(props);
		
		assertNull(props.getIcon());
		assertNull(props.getTitle());
		
		Window window = new Window(config);
		assertEquals("", window.getFrame().getTitle());
	}
	
	@Test
	public void testOneParamConstructor() {
		WindowProps props = new WindowProps("Title");
		CustomConfigurator config = new CustomConfigurator().with(props);
		
		assertNull(props.getIcon());
		assertEquals("Title", props.getTitle());
		
		Window window = new Window(config);
		assertEquals("Title", window.getFrame().getTitle());
	}
	
	@Test
	public void testTwoParamsConstructor() {
		WindowProps props = new WindowProps("Title", null);
		CustomConfigurator config = new CustomConfigurator().with(props);
		
		assertNull(props.getIcon());
		assertEquals("Title", props.getTitle());
		
		Window window = new Window(config);
		assertEquals("Title", window.getFrame().getTitle());
	}
	
	@Test
	public void testSetters() {
		WindowProps props = new WindowProps();
		CustomConfigurator config = new CustomConfigurator().with(props);
		
		props.setTitle("Title");
		props.setIcon(null);
		
		assertNull(props.getIcon());
		assertEquals("Title", props.getTitle());
		
		Window window = new Window(config);
		assertEquals("Title", window.getFrame().getTitle());
	}

}
