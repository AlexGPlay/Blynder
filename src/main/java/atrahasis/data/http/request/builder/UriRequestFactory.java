package atrahasis.data.http.request.builder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import atrahasis.data.http.request.components.Request;
import atrahasis.data.http.request.components.RequestParams;

public class UriRequestFactory {
	
	private Request request;
	private RequestParams params;
	
	public UriRequestFactory(Request request, RequestParams params) {
		this.request = request;
		this.params = params;
	}
	
	public HttpUriRequest create() {
		URI uri = buildUri();
		String method = request.getMethod();
		HttpEntity entity = buildHttpEntity();
		
		return buildInstance(method, uri, entity);
	}
	
	private HttpEntity buildHttpEntity() {
		return new StringEntity(mapToJsonString(), ContentType.APPLICATION_JSON);
	}
	
	private String mapToJsonString() {
		try {
			return new ObjectMapper().writeValueAsString(params.getData());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}
	
	private void addHeaders(RequestBuilder builder) {
		Map<String, Object> rParams = params.getHeaders();
		
		for (Map.Entry<String, Object> entry : rParams.entrySet()) {
			builder.addHeader(entry.getKey(), entry.getValue().toString());
		}
	}
	
	private URI buildUri() {
		try {
			String url = request.getUrl();
			Map<String,Object> params = this.params.getParams();
			
			URIBuilder builder = new URIBuilder(url);
			
			List<String> nSegments = builder.getPathSegments();
			nSegments.addAll(this.params.getSegments());
			builder.setPathSegments(nSegments);
			
			for (Map.Entry<String, Object> entry : params.entrySet()) 
				builder.addParameter(entry.getKey(), entry.getValue().toString());
			
			return builder.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private HttpUriRequest buildInstance(String method, URI uri, HttpEntity entity) {
		RequestBuilder builder = RequestBuilder.create(method)
		.setUri(uri)
		.setEntity(entity);
		
		addHeaders(builder);
		
		return builder.build();
	}
	
}
