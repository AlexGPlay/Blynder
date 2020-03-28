package atrahasis.data.http.request.components;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class RequestParamsTest {

	@Test
	public void testRequestParamsNull() {
		RequestParams requestParams = new RequestParams(null,null,null,null);
		
		assertNotNull(requestParams.getData());
		assertNotNull(requestParams.getHeaders());
		assertNotNull(requestParams.getParams());
		assertNotNull(requestParams.getSegments());
	}
	
	@Test
	public void testRequestParams() {
		Map<String,Object> data = new HashMap<>();
		data.put("data", "");
		
		Map<String,Object> headers = new HashMap<>();
		data.put("headers", "");
		
		Map<String,Object> params = new HashMap<>();
		data.put("params", "");
		
		List<String> segments = new ArrayList<>();
		segments.add("segments");
		
		RequestParams requestParams = new RequestParams(data,headers,params,segments);
		
		assertTrue(requestParams.getData().containsKey("data"));
		assertTrue(requestParams.getHeaders().containsKey("headers"));
		assertTrue(requestParams.getParams().containsKey("params"));
		assertTrue(requestParams.getSegments().contains("segments"));
	}

}
