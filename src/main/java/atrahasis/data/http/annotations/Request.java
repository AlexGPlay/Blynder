package atrahasis.data.http.annotations;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * This annotation is the one that is used to annotate the methods of a repository
 * interface that can be executed. This methods sets the base url for the repository
 * and the method that will be issued for the request.<br>
 * For example, if you want to request from www.api.com with a GET method you will
 * annotate the method with "at_sign"Request(url="www.api.com", method="GET");
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface Request {

	String url();
	String method() default "GET";
	
}
