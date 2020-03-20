package atrahasis.data.annotations;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation used to mark a repository. A repository is a class that will
 * be a child of a repository interface, this classes make data exchanges with
 * external services. An example of this can be a repository that inherits from
 * the HttpRepository interface and implements a method that requests from a 
 * web page, the results from this request will be returned as a result.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface Repository{

}
