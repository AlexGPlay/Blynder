package org.blynder.core.annotations;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation used to mark a class which will be used to contain the different
 * routes the application can go to, classes marked as ApiController are beans,
 * which means that they will be fully managed by the framework.
 * You can make a controller class, for example, like this "@RestController public
 * class FooController", this will make the framework index this class for the
 * path finding when you are navigating through the app.
 * The main difference between the RestController and the Controller classes is
 * the way the data is returned. When a Controller is invoked the application
 * will try to navigate to the route in the main window, whereas with an API
 * controller the application will return the data without trying to render it.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface RestController {

}
