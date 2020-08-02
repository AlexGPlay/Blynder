package atrahasis.testClasses;

import org.blynder.core.annotations.Filter;
import org.blynder.core.network.Response;

@Filter
public class FilterTestClass5 {

	public void testingFilter(Response response) {
		response.canContinue(true);
	}
	
}
