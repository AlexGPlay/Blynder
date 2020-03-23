package atrahasis.data.http.annotations;

import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * This annotation is used to annotate the query params that will be sent in the
 * request. The query params are the params that conform the last part of an URL,
 * they are a key-value pair. For example, if you have the URL www.api.com?param=1,
 * param is a query param and its value is 1. There can be as many query params as
 * the user wants. This params will be automatically appended to the URL.
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ PARAMETER })
public @interface Params {

}
