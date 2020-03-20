package atrahasis.data.http.request;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import atrahasis.data.http.request.builder.HttpResponseBuilder;
import atrahasis.data.http.request.builder.UriRequestFactory;
import atrahasis.data.http.request.components.HttpResponse;
import atrahasis.data.http.request.components.Request;
import atrahasis.data.http.request.components.RequestParams;

public class Requester {
	
	private HttpUriRequest uriRequest;
	
	public Requester(Request request, RequestParams params) {
		buildRequest(request, params);
	}
	
	private void buildRequest(Request request, RequestParams params) {
		uriRequest = new UriRequestFactory(request, params).create();
	}
	
	public HttpResponse request() {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(uriRequest);
			return new HttpResponseBuilder(response).build();
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
