package atrahasis.core.chromium;

import java.awt.Component;

import atrahasis.core.chromium.injector.ChromiumInjector;
import atrahasis.core.chromium.injector.OSDetector;
import atrahasis.core.chromium.injector.ObjectManager;

public class Browser {

	private ObjectManager manager;
	private Class<?> browser;
	
	public Browser() {
		this("<h1>Prueba</h1>");
	}
	
	public Browser(String html) {
		initialize(html);
	}
	
	private void initialize(String html) {
		initializeManager();
		initializeBrowser(html);
	}
	
	private void initializeManager() {
		OSDetector.OS os = new OSDetector().getOS();
		manager = new ObjectManager( new ChromiumInjector().addJar(os) );
	}
	
	private void initializeBrowser(String html) {
		Class<?> cefApp = manager.getClass("org.cef.CefApp", "org.cef.CefApp", "getInstance", null);
		Class<?> client = manager.getClass("org.cef.CefApp", "org.cef.CefClient", "createClient", cefApp);
		
		Object[] params = {"data:text/html," + html, true, false};
		
		Class<?>[] types = {String.class, boolean.class, boolean.class};
		
		browser = manager.getClass(
				"org.cef.CefClient",
				"org.cef.browser.CefBrowser",
				"createBrowser", 
				client,
				types,
				params
		);
	}
	
	public void loadHTML(String html) {
		String[] args = {"data:text/html," + html};
		Class<?>[] types = {String.class};
		manager.invokeMethodForClass(browser, "loadURL", args, types);
	}
	
	public Component getUI() {
		return (Component) manager.invokeMethodForClass(browser, "getUIComponent", new Object[]{}, new Class<?>[] {});
	}
	
}
