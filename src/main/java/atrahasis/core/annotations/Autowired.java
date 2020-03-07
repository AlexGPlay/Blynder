package atrahasis.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation used to mark a field which instance will be managed by
 * the framework, in order to make the class suitable for this annotation,
 * it must be a bean.
 * For example, you can have a class field like "@Autowired FOO temp", in this
 * case, the framework will instantiate temp with the empty constructor of the
 * class FOO.
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired {

}
