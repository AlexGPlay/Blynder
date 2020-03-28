package atrahasis.core.view;

import static org.junit.Assert.*;

import org.junit.Test;

import atrahasis.core.template.Model;

public class HTMLHandlerTest {

	private HTMLHandler htmlHandler = new HTMLHandler();
	
	@Test
	public void testEmptyModel() {
		String path = htmlHandler.getHtml("test.html", new Model());
		assertNotNull(path);
	}

	@Test
	public void testWithModel() {
		Model model = new Model();
		model.setVariable("test", "test");
		
		String path = htmlHandler.getHtml("testModel.html", model);
		assertNotNull(path);
	}
	
}
