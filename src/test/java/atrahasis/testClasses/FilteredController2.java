package atrahasis.testClasses;

import org.blynder.core.annotations.Controller;
import org.blynder.core.annotations.FilterWith;

@FilterWith(filtersAsClasses={FilterTestClass1.class, FilterTestClass2.class})
@Controller
public class FilteredController2 {

}
