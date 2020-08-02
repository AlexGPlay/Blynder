package atrahasis.data.base;

import static org.junit.Assert.*;

import org.blynder.data.base.DynamicProxyFactory;
import org.blynder.data.http.proxy.HttpRepositoryProxy;
import org.junit.Test;

import atrahasis.testClasses.RepositoryTestClass1;

public class DynamicProxyFactoryTest {

	@Test
	public void testFactory() {
		assertNotNull(new DynamicProxyFactory(RepositoryTestClass1.class, new HttpRepositoryProxy()).create());
	}

}
