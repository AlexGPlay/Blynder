package org.blynder.core.network.url;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * 
 * The AppUrlHandler creates an URLConnection that is handled in the AjaxAppUrlConnection,
 * this class will provide the handling for all the requests to the app schema.
 *
 */
public class AppUrlHandler extends URLStreamHandler{
	
    @Override
    protected URLConnection openConnection(URL url, Proxy proxy) throws IOException {
        return new AppUrlConnection(url, proxy);
    }

	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		return this.openConnection(url, null);
	}
    
}
