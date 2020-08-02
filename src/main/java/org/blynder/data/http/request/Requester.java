package org.blynder.data.http.request;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.blynder.data.http.request.builder.HttpResponseBuilder;
import org.blynder.data.http.request.builder.UriRequestFactory;
import org.blynder.data.http.request.components.HttpResponse;
import org.blynder.data.http.request.components.Request;
import org.blynder.data.http.request.components.RequestParams;

/**
 * 
 * The requester class is a wrapper for the HttpUriRequest from the apache 
 * http library. This class issues a request and gets the HttpResponse from it.
 * The CloseableHttpResponse from the apache http library is converted into
 * the HttpResponse the moment it is received.
 *
 */
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
