package atrahasis.data.http.annotations;

import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * This class is used for an HttpRepository method in order to send new segments
 * into the base URL. A segment is a part of the url that is in between two /.
 * For example, app://test/segment. If you want to issue a request to the app://test
 * base url and annotate a List parameter as segment and it contains segment, the
 * url where the petition will be sent is app://test/segment. There can be as many
 * segments as the user wants.<br>
 * An example of use can be the following one:
 * "at_sign"Segment segmentList.<br>
 * segmentList is a List<String> that contains: ["segment"].
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ PARAMETER })
public @interface Segment {

}
