package atrahasis.core.network.url;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class AppStreamHandlerFactory implements URLStreamHandlerFactory{

	@Override
	public URLStreamHandler createURLStreamHandler(String protocol) {
		if(protocol.toLowerCase().equals("app")) {
			return new AppUrlHandler();
		}
		
		return null;
	}

}
