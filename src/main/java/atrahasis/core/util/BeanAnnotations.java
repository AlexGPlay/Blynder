package atrahasis.core.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import atrahasis.core.annotations.Bean;
import atrahasis.core.annotations.Controller;

public class BeanAnnotations {

	public static List<Class<? extends Annotation>> getBeans(){
		List<Class<? extends Annotation>> beans = new ArrayList<>();
		
		beans.add(Controller.class);
		beans.add(Bean.class);
		
		return beans;
	}
	
}
