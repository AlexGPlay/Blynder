package atrahasis.testClasses;

import atrahasis.core.annotations.RestController;
import atrahasis.core.annotations.Path;
import atrahasis.core.network.Response;

@RestController
public class ControllerTestApi {

	@Path("/test")
	public Response testPath1() {
		return new Response().response("apiReturns");
	}
	
}
