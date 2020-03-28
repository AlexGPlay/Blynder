package atrahasis.testClasses;

import atrahasis.core.annotations.Filter;
import atrahasis.core.network.Response;

@Filter
public class FilterTestClass4 {

	public void testingFilter(Response response) {
		response.canContinue(false);
	}
	
}
