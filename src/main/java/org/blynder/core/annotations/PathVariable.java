package org.blynder.core.annotations;

import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation used to mark path methods variables which will be part of the
 * path itself, meaning that you can pass variables with the navigation path.
 * For example, if you have a path called "/user/{name}", you can go to this
 * path via "/user/test/" meaning that you can have a field in the controller
 * path method marked as "@PathVariable String name" and in this case its value
 * will be "test".
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ PARAMETER })
public @interface PathVariable {
	String name() default "";
}
