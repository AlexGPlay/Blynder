package atrahasis.testClasses;

import atrahasis.core.annotations.Controller;
import atrahasis.core.annotations.FilterWith;
import atrahasis.core.annotations.Path;

@FilterWith(filtersAsString={"FilterTestClass4"})
@Controller
public class FilteredController5 {

	@Path("/test")
	public void path() {
		
	}
	
}
