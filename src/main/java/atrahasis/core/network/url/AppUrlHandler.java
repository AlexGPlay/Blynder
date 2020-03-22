package atrahasis.core.network.url;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class AppUrlHandler extends URLStreamHandler{
	
    @Override
    protected URLConnection openConnection(URL url, Proxy proxy) throws IOException {
        return new AjaxAppUrlConnection(url, proxy);
    }

	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		return this.openConnection(url, null);
	}
    
}
