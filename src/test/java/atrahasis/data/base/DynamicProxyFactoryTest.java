package atrahasis.data.base;

import static org.junit.Assert.*;

import org.junit.Test;

import atrahasis.data.http.proxy.HttpRepositoryProxy;
import atrahasis.testClasses.RepositoryTestClass1;

public class DynamicProxyFactoryTest {

	@Test
	public void testFactory() {
		assertNotNull(new DynamicProxyFactory(RepositoryTestClass1.class, new HttpRepositoryProxy()).create());
	}

}
