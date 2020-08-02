package atrahasis.data.http.request.builder;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.blynder.data.http.request.builder.RequestBuilder;
import org.blynder.data.http.request.components.Request;
import org.junit.Test;

import atrahasis.testClasses.RepositoryTestClass1;

public class RequestBuilderTest {

	@Test
	public void testRequestBuilder() {
		Class<?> repo = RepositoryTestClass1.class;
		Method m = repo.getDeclaredMethods()[0];
		
		Request req = new RequestBuilder(m).build();
		assertEquals("https://jsonplaceholder.typicode.com/posts",req.getUrl());
		assertEquals("GET",req.getMethod());
	}

}
