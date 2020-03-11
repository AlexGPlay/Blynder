package atrahasis.core.network;

public class Response {

	private int statusCode;
	private Object toRender;
	private String response;
	private String redirect;

	public Response() {
		statusCode = 200;
		toRender = null;
		response = null;
		redirect = null;
	}
	
	public void render(Object toRender) {
		this.toRender = toRender;
	}
	
	public void redirect(String redirectUrl) {
		this.redirect = redirectUrl;
	}
	
	public void statusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public void response(String response) {
		this.response = response;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public Object getToRender() {
		return toRender;
	}

	public String getResponse() {
		return response;
	}

	public String getRedirect() {
		return redirect;
	}
	
}
