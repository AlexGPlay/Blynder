package swingmvc.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import swingmvc.annotations.Bean;
import swingmvc.annotations.Controller;

public class BeanAnnotations {

	public static List<Class<? extends Annotation>> getBeans(){
		List<Class<? extends Annotation>> beans = new ArrayList<>();
		
		beans.add(Controller.class);
		beans.add(Bean.class);
		
		return beans;
	}
	
}
