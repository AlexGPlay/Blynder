package atrahasis.testClasses;

import org.blynder.core.annotations.Controller;
import org.blynder.core.annotations.Path;

@Controller
public class ControllerTestClass3 {

	@Path("/test/path2")
	public void testPath2() {}
	
	@Path("/test/path3")
	public void testPath3() {}
	
}
