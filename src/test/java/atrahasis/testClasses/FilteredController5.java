package atrahasis.testClasses;

import org.blynder.core.annotations.Controller;
import org.blynder.core.annotations.FilterWith;
import org.blynder.core.annotations.Path;

@FilterWith(filtersAsString={"FilterTestClass4"})
@Controller
public class FilteredController5 {

	@Path("/test")
	public void path() {
		
	}
	
}
