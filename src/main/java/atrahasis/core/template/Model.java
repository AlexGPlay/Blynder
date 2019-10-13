package atrahasis.core.template;

import java.util.HashMap;
import java.util.Map;

public class Model {

	private Map<String,Object> variables = new HashMap<>();
	
	public void setVariable(String name, Object value) {
		variables.put(name, value);
	}
	
	public Map<String,Object> getVariables(){
		return variables;
	}
	
}
