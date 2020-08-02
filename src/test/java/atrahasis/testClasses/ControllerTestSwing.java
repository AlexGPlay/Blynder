package atrahasis.testClasses;

import javax.swing.JPanel;

import org.blynder.core.annotations.Controller;
import org.blynder.core.annotations.Path;
import org.blynder.core.network.Response;

@Controller
public class ControllerTestSwing {

	@Path("/test")
	public Response testPath1() {
		return new Response().response(new JPanel());
	}
	
}
