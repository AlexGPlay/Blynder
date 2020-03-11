package atrahasis.core.network.url;

import java.net.URL;

public class AppUrlHandlerSetter {

	public void setUrlManager() {
		URL.setURLStreamHandlerFactory(new AppStreamHandlerFactory());
	}
	
}
