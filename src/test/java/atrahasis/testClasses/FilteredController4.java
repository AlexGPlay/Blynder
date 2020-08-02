package atrahasis.testClasses;

import org.blynder.core.annotations.Controller;
import org.blynder.core.annotations.FilterWith;

@FilterWith(filtersAsString={"FilterTestClass1", "FilterTestClass3"})
@Controller
public class FilteredController4 {

}
