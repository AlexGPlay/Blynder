package atrahasis.core.view;

import static org.junit.Assert.*;

import org.blynder.core.template.Model;
import org.blynder.core.template.Thymeleaf;
import org.blynder.core.view.HTMLHandler;
import org.junit.Test;

public class HTMLHandlerTest {

	private HTMLHandler htmlHandler = new HTMLHandler(new Thymeleaf());
	
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
