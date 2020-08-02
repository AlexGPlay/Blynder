package atrahasis.core.template;

import static org.junit.Assert.*;

import org.blynder.core.template.Model;
import org.blynder.core.template.Thymeleaf;
import org.junit.Before;
import org.junit.Test;

public class ThymeleafTest {

	private Model model;
	private Thymeleaf thymeleaf;
	
	@Before
	public void initModel() {
		model = new Model();
		thymeleaf = new Thymeleaf();
	}
	
	@Test
	public void testTemplateWithoutTagsEmptyModel() {
		String template = "<div>Test</div>";
		String expected = "<div>Test</div>";
		String actualRes = thymeleaf.processHtml(template, model);
		
		assertEquals(expected, actualRes);
	}
	
	@Test
	public void testTemplateWithTagsAndEmptyModel() {
		String template = "<div th:text=\"${test}\"></div>";
		String expected = "<div></div>";
		String actualRes = thymeleaf.processHtml(template, model);
		
		assertEquals(expected, actualRes);
	}
	
	@Test
	public void testTemplateWithTagsAndModel() {
		model.setVariable("test", "value");
		
		String template = "<div th:text=\"${test}\"></div>";
		String expected = "<div>value</div>";
		String actualRes = thymeleaf.processHtml(template, model);
		
		assertEquals(expected, actualRes);
	}
	
	@Test
	public void testTemplateWithoutTagsAndModel() {
		model.setVariable("test", "value");
		
		String template = "<div>Test</div>";
		String expected = "<div>Test</div>";
		String actualRes = thymeleaf.processHtml(template, model);
		
		assertEquals(expected, actualRes);
	}

}
