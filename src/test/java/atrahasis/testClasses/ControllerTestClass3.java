package atrahasis.testClasses;

import atrahasis.core.annotations.Controller;
import atrahasis.core.annotations.Path;

@Controller
public class ControllerTestClass3 {

	@Path("/test/path2")
	public void testPath2() {}
	
	@Path("/test/path3")
	public void testPath3() {}
	
}
