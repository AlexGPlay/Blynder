package atrahasis.data.http.request.components;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import atrahasis.core.network.Response;

public class HttpResponseTest {

	@Test
	public void testNullResponse() {
		HttpResponse resp = new HttpResponse(null, 500, null, null, null, null);
		
		assertNull(resp.getHeaders());
		assertNull(resp.getProtocolVersion());
		assertNull(resp.getReasonPhrase());
		assertNull(resp.getResponse());
		assertNull(resp.getStatusLine());
		assertEquals(500, resp.getStatusCode());
		
		assertNull(resp.toMap());
		
		Response coreResp = resp.toResponse();
		assertNull(resp.getResponse());
		assertEquals(500, coreResp.getStatusCode());
	}
	
	@Test
	public void testValidResponse() {
		HttpResponse resp = new HttpResponse("response", 200, "test", "test", "http", new HashMap<>());
		
		assertNotNull(resp.getHeaders());
		assertEquals("http", resp.getProtocolVersion());
		assertEquals("test",resp.getReasonPhrase());
		assertEquals("response",resp.getResponse());
		assertEquals("test",resp.getStatusLine());
		assertEquals(200, resp.getStatusCode());
		
		assertNull(resp.toMap());
		
		Response coreResp = resp.toResponse();
		assertEquals("response",resp.getResponse());
		assertEquals(200, coreResp.getStatusCode());
	}
	
	@Test
	public void testValidResponseWithValidJson() {
		HttpResponse resp = new HttpResponse("{\"a\":\"b\"}", 200, "test", "test", "http", new HashMap<>());
		
		assertNotNull(resp.getHeaders());
		assertEquals("http", resp.getProtocolVersion());
		assertEquals("test",resp.getReasonPhrase());
		assertEquals("{\"a\":\"b\"}",resp.getResponse());
		assertEquals("test",resp.getStatusLine());
		assertEquals(200, resp.getStatusCode());
		
		assertNotNull(resp.toMap());
		assertEquals("b", resp.toMap().get("a"));
		
		Response coreResp = resp.toResponse();
		assertEquals("{\"a\":\"b\"}",resp.getResponse());
		assertEquals(200, coreResp.getStatusCode());
	}

}
