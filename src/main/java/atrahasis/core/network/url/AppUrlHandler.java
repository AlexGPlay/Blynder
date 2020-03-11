package atrahasis.core.network.url;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import atrahasis.core.Application;
import sun.net.www.protocol.http.Handler;

@SuppressWarnings("restriction")
public class AppUrlHandler extends Handler{
	
    @Override
    protected URLConnection openConnection(URL url, Proxy proxy) throws IOException {

        if (url.toString().contains("ajax=1")) {
            return new AjaxAppUrlConnection(url, proxy, this);
        }
        
        String appUrl = url.toString().replace("app:", "");
        if(appUrl.isEmpty()) {
        	appUrl = "/";
        }
        else {
        	appUrl = appUrl.replace("//", "/");
        }
        Application.navigate(appUrl);
        // Return a default HttpURLConnection instance.
        return new URL(null).openConnection(proxy);
    }
    
}
