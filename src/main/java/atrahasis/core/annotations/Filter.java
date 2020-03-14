package atrahasis.core.annotations;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation used to mark a class that will be used to filter the routing
 * to different zones of the application. This annotation is used with the
 * filterWith annotation.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface Filter{

}
