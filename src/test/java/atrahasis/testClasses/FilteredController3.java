package atrahasis.testClasses;

import atrahasis.core.annotations.Controller;
import atrahasis.core.annotations.FilterWith;

@FilterWith(filtersAsString={"FilterTestClass1", "FilterTestClass3"}, filtersAsClasses={FilterTestClass1.class, FilterTestClass2.class})
@Controller
public class FilteredController3 {

}
