package atrahasis.data.http.request.components;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import atrahasis.core.network.Response;

public class HttpResponse {

	private String response;
	
	private int statusCode;
	private String reasonPhrase;
	private String statusLine;
	private String protocolVersion;
	
	private Map<String, String> headers;
	
	public HttpResponse(String response, int statusCode, String reasonPhrase, String statusLine, String protocolVersion, Map<String, String> headers) {
		this.response = response;
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
		this.statusLine = statusLine;
		this.protocolVersion = protocolVersion;
		this.headers = headers;
	}

	public String getResponse() {
		return response;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	public String getStatusLine() {
		return statusLine;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}
	
	public Response toResponse() {
		return new Response()
				.statusCode(statusCode)
				.response(response);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> toMap(){
		try {
			return new ObjectMapper().readValue(response, HashMap.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
