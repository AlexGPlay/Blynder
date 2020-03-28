package atrahasis.testClasses;

import atrahasis.core.annotations.Autowired;
import atrahasis.core.annotations.Bean;

@Bean
public class AutowiredBeanTestClass {

	@Autowired
	public BeanParamNoAutowiredTestClass ob;
	
}
