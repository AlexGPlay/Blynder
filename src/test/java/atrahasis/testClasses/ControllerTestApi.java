package atrahasis.testClasses;

import org.blynder.core.annotations.Path;
import org.blynder.core.annotations.RestController;
import org.blynder.core.network.Response;

@RestController
public class ControllerTestApi {

	@Path("/test")
	public Response testPath1() {
		return new Response().response("apiReturns");
	}
	
}
