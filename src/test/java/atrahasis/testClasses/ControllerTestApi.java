package atrahasis.testClasses;

import atrahasis.core.annotations.ApiController;
import atrahasis.core.annotations.Path;
import atrahasis.core.network.Response;

@ApiController
public class ControllerTestApi {

	@Path("/test")
	public Response testPath1() {
		return new Response().response("apiReturns");
	}
	
}
