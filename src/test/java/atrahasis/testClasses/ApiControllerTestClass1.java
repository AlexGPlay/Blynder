package atrahasis.testClasses;

import org.blynder.core.annotations.Path;
import org.blynder.core.annotations.RestController;

@RestController
public class ApiControllerTestClass1 {

	@Path(value="/api/path1", method="POST")
	public void testPath1() {}
	
	@Path("/api/path2")
	public void testPath2() {}
	
}
