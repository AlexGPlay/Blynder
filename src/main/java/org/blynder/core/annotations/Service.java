package org.blynder.core.annotations;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation used to mark a service class. Service classes are created to
 * make the heavy logic away from the controllers and act as bridge between
 * them and the data layer.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface Service{

}
