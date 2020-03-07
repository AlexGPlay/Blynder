package atrahasis.core.browser.chromium;

import java.awt.Component;

import atrahasis.core.Application;
import atrahasis.core.browser.IBrowser;
import atrahasis.core.browser.chromium.handler.AppHandlerObserver;
import atrahasis.core.browser.chromium.handler.InitializedObserver;
import atrahasis.core.browser.chromium.injector.ChromiumInjector;
import atrahasis.core.browser.chromium.injector.OSDetector;
import atrahasis.core.browser.chromium.injector.ObjectManager;
import atrahasis.core.browser.chromium.util.DataUri;

/**
 * 
 * Chromium implementation of the IBrowser interface. This implementation is
 * based on the JCEF bindings. The bindings aren't 100% functional, there are
 * times where the app will crash if this browser is used.
 *
 */
public class Browser implements IBrowser{

	private ObjectManager manager;
	private Class<?> browser;
	
	public Browser() {
		this("<html><body><a href='http://www.google.es'>Test</a></body></html>");
	}
	
	public Browser(String html) {
		initialize(html);
	}
	
	public void loadHTML(String html) {
		String[] args = {DataUri.create("text/html", html)};
		Class<?>[] types = {String.class};

		System.out.println("Invocando loadURL");
		manager.invokeMethodForClass(browser, "loadURL", args, types);
	}
	
	public Component getUI() {
		return (Component) manager.invokeMethodForClass(browser, "getUIComponent", new Object[]{}, new Class<?>[] {});
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
		initializeAppHandler(cefApp);
		
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
		
		initializeLifeSpanHandler(browser);
	}
	
	private void initializeAppHandler(Class<?> cefApp) {
		Class<?> appHandlerClass = manager.getClass("org.atrahasis.AppHandler");
		Object appHandlerInstance = manager.getInstanceForClass(appHandlerClass);
		
		Object[] params = { appHandlerInstance };
		
		Class<?>[] types = { manager.getClassWithoutInstance("org.cef.handler.CefAppHandler") };
		
		manager.invokeMethodForClass(cefApp, "addAppHandler", params, types);
		
		manager.invokeMethodForClass(
				appHandlerClass, "addObserver", 
				new Object[] { Application.getInstance() }, 
				new Class<?>[]{ AppHandlerObserver.class }
		);
	}
	
	private void initializeLifeSpanHandler(Class<?> browser) {
		Object client = manager.invokeMethodForClass(
				browser, 
				"getClient", 
				new Object[] {}, 
				new Class<?>[] {}
			);
		
		manager.invokeMethodForClass(
				client.getClass(), 
				"removeLifeSpanHandler", 
				new Object[] {}, 
				new Class<?>[] {}
			);
	
		Class<?> lifeSpanHandlerClass = manager.getClass("org.atrahasis.LifeSpanHandler");
		Object lifeSpanHandlerInstance = manager.getInstanceForClass(lifeSpanHandlerClass);
		
		manager.invokeMethodForClass(
				client.getClass(), 
				"addLifeSpanHandler", 
				new Object[] { lifeSpanHandlerInstance }, 
				new Class<?>[] { manager.getClassWithoutInstance("org.cef.handler.CefLifeSpanHandler") }
			);
		
		manager.invokeMethodForClass(
				lifeSpanHandlerClass, "addObserver", 
				new Object[] { Application.getInstance() }, 
				new Class<?>[]{ InitializedObserver.class }
		);
	}

	
}
