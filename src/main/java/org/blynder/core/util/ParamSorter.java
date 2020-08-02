package org.blynder.core.util;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.blynder.core.annotations.PathVariable;

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
	 * @param extras
	 * Extra params that can be inserted into the parameters, for example, the model.
	 * @return
	 * A list of sorted objects that will be inserted into the method.
	 * 
	 */
	public List<Object> sortParameters(Map<String,Object> parameters, Method method, Object... extras){
		List<Object> sortedParams = new ArrayList<>();
		
		for(Parameter param : method.getParameters()) {
			if(param.isAnnotationPresent( PathVariable.class )) {
				sortedParams.add( getDataForAnnotatedParam(parameters, param, param.getAnnotationsByType(PathVariable.class)[0]) );
			}
			
			else {
				sortedParams.add(getExtraForParameter(param, extras));
			}
		}
		
		return sortedParams;
	}
	
	/**
	 * Given a controller parameter, this method will try to find the extra
	 * parameter that has the same class.
	 * 
	 * @param param that will be injected.
	 * @param extras that can be of that type.
	 * @return
	 * The extra that has the same class.
	 * Null if there isn't one.
	 * 
	 */
	private Object getExtraForParameter(Parameter param, Object... extras) {
		for(Object obj : extras) { 
			if(obj == null)
				continue;
			
			if(param.getType() == obj.getClass())
				return obj;
		}
		return null;
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
