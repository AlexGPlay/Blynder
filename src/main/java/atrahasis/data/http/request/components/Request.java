package atrahasis.data.http.request.components;

public class Request {

	private String url;
	private String method;
	
	public Request(String url, String method) {
		this.url = url;
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public String getMethod() {
		return method;
	}
		
}
