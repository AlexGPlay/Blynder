package atrahasis.testClasses;

import org.blynder.core.annotations.Controller;
import org.blynder.core.annotations.Path;
import org.blynder.core.network.Response;

@Controller
public class ControllerTestInvalid {

	@Path("/test")
	public Response testPath1() {
		return new Response().response("random.random");
	}
	
}
