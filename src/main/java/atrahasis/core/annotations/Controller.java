package atrahasis.core.annotations;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation used to mark a class which will be used to contain the different
 * routes the application can go to, classes marked as Controller are beans,
 * which means that they will be fully managed by the framework.
 * You can make a controller class, for example, like this "@Controller public
 * class FooController", this will make the framework index this class for the
 * path finding when you are navigating through the app.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface Controller{

}
