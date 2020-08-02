package org.blynder.core.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given the URL the user wants to navigate to as a string, this class will
 * extract the QueryParams from it. For example, if the user wants to navigate
 * to app://foo?param1=a&param2=b, this class will create a Map with the following
 * entries: {param1 => a; param2 => b}
 *
 */
public class QueryParamsExtractor {

	private String url;
	
	public QueryParamsExtractor(String url) {
		this.url = url;
	}
	
	public Map<String,Object> extract(){
		return extractData();
	}
	
	private Map<String,Object> extractData(){
		URI uri = extractURI();
		String queryParams = uri.getQuery();
		Map<String,Object> params = new HashMap<>();
		
		if(queryParams == null)
			return params;
		
		for(String queryParam : queryParams.split("&")) 
			insertQueryParam(queryParam, params);
		
		return params;
	}
	
	private void insertQueryParam(String queryParam, Map<String,Object> params) {
	    int idx = queryParam.indexOf("=");
	    String key = idx > 0 ? queryParam.substring(0, idx) : queryParam;
	    String value = idx > 0 && queryParam.length() > idx + 1 ? queryParam.substring(idx + 1) : null;
	    params.put(key, value);
	}
	
	private URI extractURI() {
		try {
			return new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
