package atrahasis.core.template;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ModelTest {
	
	private Model model;
	
	@Before
	public void initModel() {
		model = new Model();
	}
	
	@Test
	public void testEmptyModel() {
		Map<String,Object> res = new HashMap<>();
		assertEquals(model.getVariables(), res);
	}
	
	@Test
	public void testModelWithObjects() {
		model.setVariable("test", "test");
		
		Map<String,Object> res = new HashMap<>();
		res.put("test", "test");
		
		assertEquals(model.getVariables(), res);
	}

}
