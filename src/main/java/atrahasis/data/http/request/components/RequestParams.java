package atrahasis.data.http.request.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * The RequestParams is just a class that holds the data that will be used in
 * the petition. This class holds the data, headers, params and segments that
 * will be sent.
 *
 */
public class RequestParams {

	private Map<String, Object> data;
	private Map<String, Object> headers;
	private Map<String, Object> params;
	private List<String> segments;
	
	@SuppressWarnings("unchecked")
	public RequestParams(Object data, Object headers, Object params, Object segments) {
		this.data = (Map<String, Object>) data;
		this.headers = (Map<String, Object>) headers;
		this.params = (Map<String, Object>) params;
		this.segments = (List<String>) segments;
	}

	public Map<String, Object> getData() {
		return data == null ? new HashMap<>() : data;
	}

	public Map<String, Object> getHeaders() {
		return headers == null ? new HashMap<>() : headers;
	}

	public Map<String, Object> getParams() {
		return params == null ? new HashMap<>() : params;
	}
	
	public List<String> getSegments() {
		return segments == null ? new ArrayList<>() : segments;
	}
	
}
