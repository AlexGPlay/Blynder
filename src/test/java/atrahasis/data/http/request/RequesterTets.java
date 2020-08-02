package atrahasis.data.http.request;

import static org.junit.Assert.*;

import org.blynder.data.http.request.Requester;
import org.blynder.data.http.request.components.HttpResponse;
import org.blynder.data.http.request.components.Request;
import org.blynder.data.http.request.components.RequestParams;
import org.junit.Test;

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
