package atrahasis.core.network.url;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import atrahasis.core.Application;
import atrahasis.core.network.Request;
import atrahasis.core.network.Response;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class AjaxAppUrlConnection extends HttpURLConnection  {

    private Proxy proxy;
    private Response response;
    private ByteArrayOutputStream output;
    
    protected AjaxAppUrlConnection(URL url, Proxy proxy) throws IOException {
        super(url);
        this.proxy = proxy;
        this.output = new ByteArrayOutputStream();
        setDoOutput(true);
    }
    
    private void executeAppQuery() {
        String appUrl = url.toString().replace("app:", "");
        if(appUrl.isEmpty()) {
        	appUrl = "/";
        }
        else {
        	appUrl = appUrl.replace("//", "/");
        }
        
        Request request = prepareRequest();
        response = Application.navigate(appUrl, request);
    }
    
    private Request prepareRequest() {
    	String url = getURL().toString();
    	String method = getRequestMethod();
    	Map<String,List<String>> headers = getRequestProperties();
		Map<String,Object> params = new HashMap<>();
		
		String bodyJson = new String(output.toByteArray());
		Map<String,Object> body = jsonToMap(bodyJson);
    	
    	return new Request(url, method, headers, params, body);
    }
    
    @SuppressWarnings("unchecked")
	private Map<String,Object> jsonToMap(String json){
    	try {
			return new ObjectMapper().readValue(json, HashMap.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new HashMap<>();
		}
    }
    
    @Override
    public OutputStream getOutputStream() {
    	return output;
    }
    
    @Override
    public InputStream getInputStream(){
    	executeAppQuery();
    	String test = (String) getResponse();
    	InputStream targetStream = new ByteArrayInputStream(test.getBytes());
    	return targetStream;
    }
    
    @Override
    public InputStream getErrorStream(){    	
    	String test = (String) getResponse();
    	InputStream targetStream = new ByteArrayInputStream(test.getBytes());
    	return targetStream;
    }
    
    @Override
    public int getResponseCode() {
    	if(response == null)
    		return 100;
    	
    	return response.isRenderizable() ? response.getStatusCode() : 308;
    }

	@Override
	public boolean usingProxy() {
		return proxy != null;
	}
	
	@Override
	public String getResponseMessage() throws IOException {
		return String.format("APP/0.3 %s OK", getResponseCode(), getResponseStatus());
	}
	
	private String getResponseStatus() {
		return getResponseCode() >= 200 && getResponseCode() <= 300 ? "OK" : "Error"; 
	}
	
	private String getResponse() {
		if(response == null)
			return "waiting";
		
		if(response.isRenderizable())
			return "redirected";
		
		return (String)response.getResponse();
	}
	
	@Override
	public void disconnect() { }

	@Override
	public void connect() { }


}
