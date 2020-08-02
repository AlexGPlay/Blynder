package atrahasis.testClasses;

import org.blynder.core.annotations.Autowired;
import org.blynder.core.annotations.Bean;

@Bean
public class AutowiredBeanTestClass {

	@Autowired
	public BeanParamNoAutowiredTestClass ob;
	
}
