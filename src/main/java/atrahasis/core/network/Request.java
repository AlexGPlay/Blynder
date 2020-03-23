package atrahasis.core.network;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Request class is an internal class for the network communications of the app.
 * This class holds many of the parameters a real request would have. It can be
 * used in the controllers and in the filters to check what request the user sent.
 * In case of an ajax request, this class will map the real request data.
 *
 */
public class Request {

	private String method;
	private String url;
	
	private Map<String,List<String>> headers;
	private Map<String,Object> params;
	private Map<String,Object> body;
	
	public Request(String url) {
		this(url, "GET");
	}
	
	public Request(String url, String method) {
		this(url, method, new HashMap<>(), new HashMap<>(), new HashMap<>());
	}
	
	public Request(String url, String method, Map<String,List<String>> headers, Map<String,Object> params, Map<String,Object> body) {
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

	public Map<String,List<String>> getHeaders() {
		return headers;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public Map<String, Object> getBody() {
		return body;
	}
	
}
