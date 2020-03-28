package atrahasis.testClasses;

import atrahasis.core.annotations.Controller;
import atrahasis.core.annotations.FilterWith;

@FilterWith(filtersAsClasses={FilterTestClass1.class, FilterTestClass2.class})
@Controller
public class FilteredController2 {

}
