package atrahasis.core.network.url;

import java.net.URL;

/**
 * 
 * Class that will set the URLStreamHandlerFactory, this action is needed for
 * the app schema to be handled.
 *
 */
public class AppUrlHandlerSetter {

	public void setUrlManager() {
		URL.setURLStreamHandlerFactory(new AppStreamHandlerFactory());
	}
	
}
