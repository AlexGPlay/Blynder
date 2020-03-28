package atrahasis.testClasses;

import atrahasis.core.annotations.Autowired;
import atrahasis.core.annotations.Service;

@Service
public class ServiceTestClass {

	@Autowired
	public RepositoryTestClass1 ob;
	
}
