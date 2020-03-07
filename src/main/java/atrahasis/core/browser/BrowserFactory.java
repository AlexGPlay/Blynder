package atrahasis.core.browser;

/**
 * 
 * Factory that given an enum object will create that browser.
 *
 */
public class BrowserFactory {

	public static enum Browser { Standard, Chromium }
	private Browser browser;
	
	public BrowserFactory(Browser browser) {
		this.browser = browser;
	}
	
	/**
	 * 
	 * Given the browser from the enum, this method will return an instance
	 * of the browser.
	 * TODO: Metaprogram the browser creation in order to get a more scalable
	 * codebase.
	 * @return IBrowser
	 * 
	 */
	public IBrowser getBrowser() {
		if(browser == Browser.Standard)
			return new atrahasis.core.browser.standard.Browser();
		
		else if(browser == Browser.Chromium)
			return new atrahasis.core.browser.chromium.Browser();
		
		try {
			String path = String.format("atrahasis.core.browser.%s.browser", browser.name());
			Class<?> clazz = Class.forName(path);
			return (IBrowser)clazz.newInstance();
		}
		catch(Exception e) {
			return null;
		}
	}
	
}
