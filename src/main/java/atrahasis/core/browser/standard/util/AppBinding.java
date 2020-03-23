package atrahasis.core.browser.standard.util;

import atrahasis.core.Application;

/**
 * 
 * This class adds an app object to the web browser. The only method this object
 * has is navigate. That method invokes the Application navigate method, making
 * the application render another view.
 *
 */
public class AppBinding {
	
	public void navigate(String url) {
		Application.navigate(url);
	}
	
}
