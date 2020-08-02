package org.blynder.core.network;

/**
 * 
 * Response class is an internal class used for network communications. This class
 * holds many of the real parameters a response would have. A controller will be
 * the one that returns a response that will be used in other parts of the app.
 * It is also used as a response for external communications, for example, if there
 * is an ajax call, this response will be mapped as a real one.
 *
 */
public class Response {

	private int statusCode;
	private boolean canContinue;
	private Object response;
	private String redirect;
	private String responseType;

	public Response() {
		statusCode = 200;
		response = null;
		redirect = null;
		responseType = null;
		canContinue = false;
	}
	
	public Response redirect(String redirectUrl) {
		this.redirect = redirectUrl;
		return this;
	}
	
	public Response statusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	
	public Response response(Object response) {
		this.response = response;
		return this;
	}
	
	public Response canContinue(boolean canContinue) {
		this.canContinue = canContinue;
		return this;
	}
	
	public Response responseType(String responseType) {
		this.responseType = responseType;
		return this;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public Object getResponse() {
		return response;
	}

	public String getRedirect() {
		return redirect;
	}
	
	public boolean isAbleToContinue() {
		return canContinue;
	}
	
	public String getResponseType() {
		return responseType;
	}
	
	public boolean isRenderizable() {
		return !getResponseType().contains("api");
	}
	
}
