package atrahasis.core.browser;

import static org.junit.Assert.*;

import org.blynder.core.browser.BrowserFactory;
import org.blynder.core.browser.IBrowser;
import org.blynder.core.browser.BrowserFactory.Browser;
import org.junit.Test;

public class BrowserTest {

	@Test
	public void testStandardBrowser() {
		IBrowser browser = new BrowserFactory(Browser.Standard).getBrowser();
		assertNotNull(browser);
		assertNotNull(browser.getUI());
	}

}
