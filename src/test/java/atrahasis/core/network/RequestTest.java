package atrahasis.core.network;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.blynder.core.network.Request;
import org.junit.Test;

public class RequestTest {

	@Test
	public void testOneParamConstructor() {
		String url = "/url";
		
		Request request = new Request(url);
		
		assertEquals(url, request.getUrl());
		assertEquals("GET", request.getMethod());
		assertEquals(0, request.getHeaders().size());
		assertEquals(0, request.getBody().size());
		assertEquals(0, request.getParams().size());
	}
	
	@Test
	public void testTwoParamConstructor() {
		String url = "/url";
		String method = "POST";
		
		Request request = new Request(url, method);
		
		assertEquals(url, request.getUrl());
		assertEquals(method, request.getMethod());
		assertEquals(0, request.getHeaders().size());
		assertEquals(0, request.getBody().size());
		assertEquals(0, request.getParams().size());
	}
	
	@Test
	public void testThreeParamConstructor() {
		String url = "/url";
		String method = "POST";
		Map<String,Object> params = new HashMap<>();
		params.put("test", "test");
		Map<String,Object> body = new HashMap<>();
		body.put("test", "test");
		Map<String,List<String>> headers = new HashMap<>();
		headers.put("test", new ArrayList<>());
		
		Request request = new Request(url, method, headers, params, body);
		
		assertEquals(url, request.getUrl());
		assertEquals(method, request.getMethod());
		assertEquals(1, request.getHeaders().size());
		assertEquals(1, request.getBody().size());
		assertEquals(1, request.getParams().size());
	}

}
