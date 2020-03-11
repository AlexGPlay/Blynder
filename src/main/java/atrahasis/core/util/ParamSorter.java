package atrahasis.core.util;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import atrahasis.core.annotations.PathVariable;
import atrahasis.core.network.Request;
import atrahasis.core.network.Response;
import atrahasis.core.template.Model;

/**
 * 
 * A helper class that allows the framework to sort the PathVariables for
 * their later injection into the controller method.
 *
 */
public class ParamSorter {

	/**
	 * 
	 * Given the map that contains the PathVariables values, the method that
	 * they need to be injected into and the model that will contain the result,
	 * this method sorts the parameters so they match the order of the method ones.
	 * @param parameters
	 * The PathVariable parameters.
	 * @param method
	 * The Method that hosts the URL.
	 * @param model
	 * A model that is inserted if the user requests it.
	 * @return
	 * A list of sorted objects that will be inserted into the method.
	 * 
	 */
	public List<Object> sortParameters(Map<String,Object> parameters, Method method, Model model, Request request, Response response){
		List<Object> sortedParams = new ArrayList<>();
		
		for(Parameter param : method.getParameters()) {
			if(param.isAnnotationPresent( PathVariable.class )) {
				sortedParams.add( getDataForAnnotatedParam(parameters, param, param.getAnnotationsByType(PathVariable.class)[0]) );
			}
			
			else if(param.getType() == Model.class) {
				sortedParams.add(model);
			}
			
			else if(param.getType() == Request.class) {
				sortedParams.add(request);
			}
			
			else if(param.getType() == Response.class) {
				sortedParams.add(response);
			}
			
			else {
				sortedParams.add(null);
			}
		}
		
		return sortedParams;
	}
	
	/**
	 * 
	 * A helper method that will look for the value of a PathVariable.
	 * @param data
	 * A map that contains the path variables.
	 * @param param
	 * The parameter of the method.
	 * @param annotation
	 * The pathVariable annotation
	 * @return
	 * The value of the pathVariable.
	 * 
	 */
	private Object getDataForAnnotatedParam(Map<String,Object> data, Parameter param, PathVariable annotation) {
		return data.get(annotation.name().equals("") ? param.getName() : annotation.name());
	}
	
	
}
