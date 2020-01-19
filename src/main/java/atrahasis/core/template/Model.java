package atrahasis.core.template;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * A model class wraps a map of String - Object that allows the framework to
 * save the PathVariables inside a map, making it easier the ThymeLeaf process.
 *
 */
public class Model {

	private Map<String,Object> variables = new HashMap<>();
	
	/**
	 * 
	 * Puts a variable inside the map.
	 * @param name
	 * The key of the hash.
	 * @param value
	 * The value of the hash.
	 * 
	 */
	public void setVariable(String name, Object value) {
		variables.put(name, value);
	}
	
	/**
	 * 
	 * Returns the map.
	 * @return
	 * Returns the map containing all the model to process it.
	 * 
	 */
	public Map<String,Object> getVariables(){
		return variables;
	}
	
}
