package atrahasis.data.http.request.builder;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.blynder.data.http.request.builder.RequestParamsBuilder;
import org.blynder.data.http.request.components.RequestParams;
import org.junit.Test;

import atrahasis.testClasses.RepositoryTestClass1;

public class RequestParamsBuilderTest {

	@Test
	public void testRequestParamsBuilder() {
		Class<?> repo = RepositoryTestClass1.class;
		Method m = repo.getDeclaredMethods()[0];
		
		RequestParams requestParams = new RequestParamsBuilder(m, new Object[] {}).build();

		assertNotNull(requestParams.getData());
		assertNotNull(requestParams.getHeaders());
		assertNotNull(requestParams.getParams());
		assertNotNull(requestParams.getSegments());
	}

}
