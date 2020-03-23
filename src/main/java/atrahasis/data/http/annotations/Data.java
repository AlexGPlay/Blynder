package atrahasis.data.http.annotations;

import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Data is an annotation that is used to annotate the objects that are going
 * into the body of the request. It should be a key-value pair that inherits
 * from Map, this key-value pair will be inserted into the request as sent.
 * For example, if you want to send a POST request that contains a user and a
 * password in order to create it in the database, you can have a map such as
 * the following one:<br>
 * { username => user; password => password }.
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ PARAMETER })
public @interface Data {

}
