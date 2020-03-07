package atrahasis.core.browser;

public class BrowserFactory {

	public static enum Browser { Standard, Chromium }
	private Browser browser;
	
	public BrowserFactory(Browser browser) {
		this.browser = browser;
	}
	
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
