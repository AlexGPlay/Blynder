package atrahasis.data.http.proxy;

import static org.junit.Assert.*;

import org.junit.Test;

import atrahasis.data.http.request.components.HttpResponse;
import atrahasis.testClasses.RepositoryTestClass1;

public class HttpRepositoryProxyTest {

	@Test
	public void testProxy() {
		RepositoryTestClass1 test = (RepositoryTestClass1) new HttpProxyFactory(RepositoryTestClass1.class).create();
		HttpResponse resp = test.getResponse();
		assertEquals(resp.getStatusCode(), 200);
	}

}
