package atrahasis.testClasses;

import atrahasis.core.annotations.RestController;
import atrahasis.core.annotations.Path;

@RestController
public class ApiControllerTestClass1 {

	@Path(value="/api/path1", method="POST")
	public void testPath1() {}
	
	@Path("/api/path2")
	public void testPath2() {}
	
}
