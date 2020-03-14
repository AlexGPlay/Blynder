package atrahasis.core.exception;

public class MapApplicationException extends Exception{

	private static final long serialVersionUID = -2304711701858356164L;

	public MapApplicationException() {
		super("There was an error mapping the application");
	}
	
}
