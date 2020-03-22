package atrahasis.core.annotations;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation used to mark the methods from controllers which will have
 * a valid application path, meaning that methods annotated with path are
 * used to navigate to its value. In order to the path methods to work as intended,
 * the method must return an Object, it can be either a path to an HTML file or a
 * Swing class.
 * You can make a path method, for example, like this "@Path('/index')", this
 * will make the index path available for navigation, meaning that if you go
 * to this path, the application will attend to this method.
 * You can set the path method with the annotation, the base controllers will ignore
 * the method and will request with GET. The method variable will only be used in
 * the ApiController paths.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface Path {
	String value();
	String method() default "GET";
}
