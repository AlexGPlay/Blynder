package org.blynder.core.annotations;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation used to mark a class that will be used to filter the routing
 * to different zones of the application. This annotation is used with the
 * filterWith annotation. The methods of a Filter will be executed when a controller
 * that is filtered with it is invoked. The filter methods will process the response
 * giving the canContinue flag a true if the filter allows the request or a false
 * and a redirect if the filter doesn't allow de request.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface Filter{

}
