package atrahasis.testClasses;

import atrahasis.core.annotations.Controller;
import atrahasis.core.annotations.Path;
import atrahasis.core.annotations.PathVariable;

@Controller
public class ControllerTestClass4 {

	@Path(value="/test/path4", method="POST")
	public void testPath4(@PathVariable(name="user") String user) {}
	
}
