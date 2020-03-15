package atrahasis.core.network;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private String method;
	private String url;
	
	private Map<String,Object> headers;
	private Map<String,Object> params;
	private Map<String,Object> body;
	
	public Request(String url) {
		this(url, "GET");
	}
	
	public Request(String url, String method) {
		this(url, method, new HashMap<>(), new HashMap<>(), new HashMap<>());
	}
	
	public Request(String url, String method, Map<String,Object> headers, Map<String,Object> params, Map<String,Object> body) {
		this.url = url;
		this.method = method;
		this.headers = headers;
		this.params = params;
		this.body = body;
	}
	
	public String getUrl() {
		return url;
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
