package atrahasis.data.http.annotations;

import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * This annotation is used to send a list of headers within the petition.
 * Headers are a data type that is used to store data such as the encoding.
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ PARAMETER })
public @interface Headers {

}
