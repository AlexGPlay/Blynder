package org.blynder.core.network.url;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

/**
 * 
 * This class returns the AppUrlHandler if there is any petition that asks
 * for the app schema, delegating the petition into the given handler. In other
 * cases the default handler will be used.
 *
 */
public class AppStreamHandlerFactory implements URLStreamHandlerFactory{

	@Override
	public URLStreamHandler createURLStreamHandler(String protocol) {
		if(protocol.toLowerCase().equals("app")) {
			return new AppUrlHandler();
		}
		
		return null;
	}

}
