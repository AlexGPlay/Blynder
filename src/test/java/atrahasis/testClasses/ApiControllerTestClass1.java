package atrahasis.testClasses;

import atrahasis.core.annotations.ApiController;
import atrahasis.core.annotations.Path;

@ApiController
public class ApiControllerTestClass1 {

	@Path(value="/api/path1", method="POST")
	public void testPath1() {}
	
	@Path("/api/path2")
	public void testPath2() {}
	
}
