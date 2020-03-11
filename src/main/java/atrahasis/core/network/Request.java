package atrahasis.core.network;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private String method;
	
	private Map<String,Object> headers;
	private Map<String,Object> params;
	private Map<String,Object> body;
	
	public Request() {
		this("GET");
	}
	
	public Request(String method) {
		this(method, new HashMap<>(), new HashMap<>(), new HashMap<>());
	}
	
	public Request(String method, Map<String,Object> headers, Map<String,Object> params, Map<String,Object> body) {
		this.method = method;
		this.headers = headers;
		this.params = params;
		this.body = body;
	}

	public String getMethod() {
		return method;
	}

	public Map<String, Object> getHeaders() {
		return headers;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public Map<String, Object> getBody() {
		return body;
	}
	
}
