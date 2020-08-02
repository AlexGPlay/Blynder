package atrahasis.testClasses;

import org.blynder.core.annotations.Controller;
import org.blynder.core.annotations.FilterWith;
import org.blynder.core.annotations.Path;

@FilterWith(filtersAsString={"FilterTestClass5"})
@Controller
public class FilteredController6 {

	@Path("/test")
	public void path() {
		
	}
	
}
