package atrahasis.core.util;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import atrahasis.core.annotations.PathVariable;
import atrahasis.core.template.Model;

public class ParamSorter {

	public List<Object> sortParameters(Map<String,Object> parameters, Method method, Model model){
		List<Object> sortedParams = new ArrayList<>();
		
		for(Parameter param : method.getParameters()) {
			if(param.isAnnotationPresent( PathVariable.class )) {
				sortedParams.add( getDataForAnnotatedParam(parameters, param, param.getAnnotationsByType(PathVariable.class)[0]) );
			}
			
			else if(param.getType() == Model.class) {
				sortedParams.add(model);
			}
			
			else {
				sortedParams.add(null);
			}
		}
		
		return sortedParams;
	}
	
	private Object getDataForAnnotatedParam(Map<String,Object> data, Parameter param, PathVariable annotation) {
		return data.get(annotation.name().equals("") ? param.getName() : annotation.name());
	}
	
	
}