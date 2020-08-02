package org.blynder.core.annotations;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation used to mark what filters will be executed before the controller
 * methods of a given class. The values of this annotation will be the filter
 * classes that will be executed. A controller marked with a filterWith annotation
 * will have the filters executed before it's routing methods.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface FilterWith{
	Class<?>[] filtersAsClasses() default {};
	String[] filtersAsString() default {};
}
