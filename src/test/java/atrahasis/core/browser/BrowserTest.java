package atrahasis.core.browser;

import static org.junit.Assert.*;

import org.junit.Test;

import atrahasis.core.browser.BrowserFactory.Browser;

public class BrowserTest {

	@Test
	public void testStandardBrowser() {
		IBrowser browser = new BrowserFactory(Browser.Standard).getBrowser();
		assertNotNull(browser);
		assertNotNull(browser.getUI());
	}

}
