package atrahasis.core.finder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atrahasis.core.util.Pair;

public class RoutesFinder {

	public Pair<String, Map<String,Object>> findRoute(Map<String, Pair<Class<?>,Method>> routes, String url) {
		Pair<Class<?>, Method> pair = routes.get(url);
		Map<String,Object> data = new HashMap<String,Object>();
		
		if(pair != null)
			return new Pair<String, Map<String,Object>>(url,data);
		
		for(Map.Entry<String, Pair<Class<?>,Method>> entry : routes.entrySet()) {
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
				nUrl = nUrl.replaceFirst(seps.get(i+1), "");
			}
			
			
			if(!isValidUrl(data,key,url))
				data.clear();
			
			else
				return new Pair<String, Map<String,Object>>(key, data);
		}
		
		
		return null;
	}
	
	private boolean isValidUrl(Map<String,Object> params, String keyUrl, String url) {
		return generateRealUrl(params,keyUrl).equals(url);
	}
	
	private String generateRealUrl(Map<String,Object> map, String url) {
		String lUrl = url;
		
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			lUrl = lUrl.replace("{" + entry.getKey() + "}", entry.getValue().toString());
		}
		
		return lUrl;
	}
	
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
