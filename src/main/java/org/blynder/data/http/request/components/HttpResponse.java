package org.blynder.data.http.request.components;

import java.util.HashMap;
import java.util.Map;

import org.blynder.core.network.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * The HttpResponse holds all that comes from the requester after a petition
 * is executed. This class data can be extracted into a map or a core response.
 * The core response transformation can help if you want to make an intermediate
 * call, for example: ajax -> app -> api service. In that example the ajax call
 * will execute an app method and the method will handle the request, for example,
 * with a repository, the response from the repository can be returned as a core
 * response and that can be handled by the ajax call.
 * 
 */
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
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
