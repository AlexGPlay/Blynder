package atrahasis.data.http.request.components;

import static org.junit.Assert.*;

import org.junit.Test;

public class RequestTest {

	@Test
	public void testRequest() {
		Request req = new Request("/test", "GET");
		assertEquals("/test", req.getUrl());
		assertEquals("GET", req.getMethod());
	}

}
