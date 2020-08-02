package org.blynder.core.finder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.blynder.core.util.Pair;

/**
 * 
 * RoutesFinder is the built-in implementation of the IRoutesFinder interface.
 * This class will provide a working class for the mapping of user routes to
 * controller routes.
 *
 */
public class RoutesFinder implements IRoutesFinder{

	public Pair<String, Map<String,Object>> findRoute(Map<String, Map<String, Pair<Class<?>,Method>>> routes, String url, String method) {
		url = url.startsWith("/") ? url : "/" + url;
		
		Map<String,Object> data = new HashMap<String,Object>();
		
		if(routes.containsKey(url) && routes.get(url).containsKey(method))
			return new Pair<String, Map<String,Object>>(url,data);
			
		
		for(Entry<String, Map<String, Pair<Class<?>, Method>>> entry : routes.entrySet()) {
			if(!entry.getValue().containsKey(method))
				continue;
			
			String key = entry.getKey();
			
			Pair<List<String>,List<String>> paramPair = getParameters(key);

			List<String> params = paramPair.object1;
			List<String> seps = paramPair.object2;

			if(!url.startsWith(seps.get(0)))
				continue;
			
			String nUrl = url.replaceFirst(seps.get(0), "");
			
			for(int i=0;i<params.size();i++) {
				String temp = i+1 < seps.size() ? nUrl.split( seps.get(i+1) )[0] : nUrl;
				data.put(params.get(i).substring(2,params.get(i).length()-2), temp);
				
				nUrl = nUrl.replaceFirst(temp, "");
				
				if(i+1 < seps.size())
					nUrl = nUrl.replaceFirst(seps.get(i+1), "");
			}
			
			
			if(!isValidUrl(data,key,url))
				data.clear();
			
			else
				return new Pair<String, Map<String,Object>>(key, data);
		}
		
		
		return null;
	}
	
	/**
	 * 
	 * Given a user url this class will check if it maps against any of the
	 * controller urls of the project.
	 * @param params
	 * The parameters given in the url as a hash.
	 * @param keyUrl
	 * The base controller url.
	 * @param url
	 * The user url.
	 * @return
	 * True if the user url maps against the given url.<br>
	 * False if the user url doesn't map against the given url.
	 * 
	 */
	private boolean isValidUrl(Map<String,Object> params, String keyUrl, String url) {
		return generateRealUrl(params,keyUrl).equals(url);
	}
	
	/**
	 * 
	 * Helper method that recreates the user url with the parameters it has
	 * and the base url.
	 * @param map
	 * The parameters of the user url.
	 * @param url
	 * The base url.
	 * @return
	 * The formed url with the base url and the parameters that should replicate
	 * the user url.
	 */
	private String generateRealUrl(Map<String,Object> map, String url) {
		String lUrl = url;
		
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			lUrl = lUrl.replace("{" + entry.getKey() + "}", entry.getValue().toString());
		}
		
		return lUrl;
	}
	
	/**
	 * 
	 * Helper method that extracts the parameters of the user url and its
	 * separators for a later analysis.
	 * @param url
	 * The url given by the user.
	 * @return
	 * A list of pairs with the parameters in the url and the separators between them.
	 */
	private Pair<List<String>,List<String>> getParameters(String url) {
		List<String> parameters = new ArrayList<>();
		List<String> separators = new ArrayList<>();
		
		boolean separator = true;
		boolean started = false;
		String word = "";
		String sep = "";
		
		for(int i=0;i<url.length();i++) {
			if(url.charAt(i) == '{') {
				started = true;
				separator = false;
				
				if(sep.length() != 0)
					separators.add(sep);
				
				sep = "";
				word += "\\{";
				continue;
			}
			
			if(url.charAt(i) == '}') {
				started = false;
				separator = true;
				sep = "";
				word += "\\}";
				parameters.add(word);
				word = "";
				continue;
			}
			
			if(started) {
				word += url.charAt(i);
			}
			
			if(separator) {
				sep += url.charAt(i);
			}
			
		}
		
		if(word.length() > 0)
			parameters.add(word);
		
		if(sep.length() > 0)
			separators.add(sep);
		
		return new Pair<List<String>,List<String>>(parameters,separators);
	}
	
}
