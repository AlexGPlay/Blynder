package org.blynder.core.annotations;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation used to mark a class which will be eligible at the finding
 * state, meaning that variables made from this class will be used in the
 * other processes managed by the framework.
 * For example, you can have a class like "@Bean public class FOO", just with
 * this you make this class available for all the processes, for example, Autowiring process.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface Bean {

}
