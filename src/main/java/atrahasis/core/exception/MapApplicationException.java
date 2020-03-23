package atrahasis.core.exception;


/**
 * 
 * Exception class used when the framework is mapping the application on it's
 * startup and an error occurs.
 *
 */
public class MapApplicationException extends Exception{

	private static final long serialVersionUID = -2304711701858356164L;

	public MapApplicationException() {
		super("There was an error mapping the application");
	}
	
}
