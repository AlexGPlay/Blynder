package atrahasis.testClasses;

import atrahasis.core.annotations.Controller;
import atrahasis.core.annotations.Path;
import atrahasis.core.network.Response;

@Controller
public class ControllerTestInvalid {

	@Path("/test")
	public Response testPath1() {
		return new Response().response("random.random");
	}
	
}
