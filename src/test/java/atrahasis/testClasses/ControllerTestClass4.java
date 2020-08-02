package atrahasis.testClasses;

import org.blynder.core.annotations.Controller;
import org.blynder.core.annotations.Path;
import org.blynder.core.annotations.PathVariable;

@Controller
public class ControllerTestClass4 {

	@Path(value="/test/path4", method="POST")
	public void testPath4(@PathVariable(name="user") String user) {}
	
}
