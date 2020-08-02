package atrahasis.testClasses;

import org.blynder.core.annotations.Controller;
import org.blynder.core.annotations.FilterWith;

@FilterWith(filtersAsString={"FilterTestClass1", "FilterTestClass3"}, filtersAsClasses={FilterTestClass1.class, FilterTestClass2.class})
@Controller
public class FilteredController3 {

}
