package atrahasis.core.view;

import static org.junit.Assert.*;

import org.junit.Test;

public class WindowPropsTest {

	@Test
	public void testDefaultConstructor() {
		WindowProps props = new WindowProps();
		
		assertNull(props.getIcon());
		assertNull(props.getTitle());
		
		Window window = new Window(new WindowSize(), props);
		assertEquals("", window.getFrame().getTitle());
	}
	
	@Test
	public void testOneParamConstructor() {
		WindowProps props = new WindowProps("Title");
		
		assertNull(props.getIcon());
		assertEquals("Title", props.getTitle());
		
		Window window = new Window(new WindowSize(), props);
		assertEquals("Title", window.getFrame().getTitle());
	}
	
	@Test
	public void testTwoParamsConstructor() {
		WindowProps props = new WindowProps("Title", "IconPath");
		
		assertEquals("IconPath", props.getIcon());
		assertEquals("Title", props.getTitle());
		
		Window window = new Window(new WindowSize(), props);
		assertEquals("Title", window.getFrame().getTitle());
	}
	
	@Test
	public void testSetters() {
		WindowProps props = new WindowProps();
		
		props.setTitle("Title");
		props.setIcon("IconPath");
		
		assertEquals("IconPath", props.getIcon());
		assertEquals("Title", props.getTitle());
		
		Window window = new Window(new WindowSize(), props);
		assertEquals("Title", window.getFrame().getTitle());
	}

}
