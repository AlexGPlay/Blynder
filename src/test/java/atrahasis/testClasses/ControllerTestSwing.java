package atrahasis.testClasses;

import javax.swing.JPanel;

import atrahasis.core.annotations.Controller;
import atrahasis.core.annotations.Path;
import atrahasis.core.network.Response;

@Controller
public class ControllerTestSwing {

	@Path("/test")
	public Response testPath1() {
		return new Response().response(new JPanel());
	}
	
}
