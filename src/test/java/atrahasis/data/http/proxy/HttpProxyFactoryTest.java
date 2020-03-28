package atrahasis.data.http.proxy;

import static org.junit.Assert.*;

import org.junit.Test;

import atrahasis.testClasses.RepositoryTestClass1;

public class HttpProxyFactoryTest {

	@Test
	public void testFactory() {
		assertNotNull(new HttpProxyFactory(RepositoryTestClass1.class).create());
	}

}
