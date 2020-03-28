package atrahasis.testClasses;

import atrahasis.core.annotations.Controller;
import atrahasis.core.annotations.FilterWith;
import atrahasis.core.annotations.Path;

@FilterWith(filtersAsString={"FilterTestClass5"})
@Controller
public class FilteredController6 {

	@Path("/test")
	public void path() {
		
	}
	
}
