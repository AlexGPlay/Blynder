package atrahasis.testClasses;

import atrahasis.core.annotations.Controller;
import atrahasis.core.annotations.FilterWith;

@FilterWith(filtersAsString={"FilterTestClass1", "FilterTestClass3"})
@Controller
public class FilteredController4 {

}
