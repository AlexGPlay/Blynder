package atrahasis.data.http.request;

import static org.junit.Assert.*;

import org.junit.Test;

import atrahasis.data.http.request.components.HttpResponse;
import atrahasis.data.http.request.components.Request;
import atrahasis.data.http.request.components.RequestParams;

public class RequesterTets {

	@Test
	public void testRequester() {
		Request request = new Request("https://jsonplaceholder.typicode.com/posts", "GET");
		RequestParams params = new RequestParams(null, null, null, null);
		
		Requester requester = new Requester(request, params);
		HttpResponse resp = requester.request();
		
		assertEquals(200, resp.getStatusCode());
	}

}
