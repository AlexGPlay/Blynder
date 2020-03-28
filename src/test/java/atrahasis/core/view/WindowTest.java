package atrahasis.core.view;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JPanel;

import org.junit.Test;

import atrahasis.core.browser.BrowserFactory.Browser;
import atrahasis.core.template.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class WindowTest {

	@Test
	public void testWindowJpanel() {
		try {
			Window window = new Window();
			window.initializeBrowser(Browser.Standard);
			window.setView(new JPanel(), new Model());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error: " + exceptionAsString);
		}
	}
	
	@Test
	public void testWindowHTML() {
		try {
			Window window = new Window();
			window.initializeBrowser(Browser.Standard);
			window.setView("test.html", new Model());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error: " + exceptionAsString);
		}
	}
	
	@Test
	public void testWindowScene() {
		try {
			Window window = new Window();
			window.initializeBrowser(Browser.Standard);
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("test.fxml"));
			window.setView(new Scene(root), new Model());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error: " + exceptionAsString);
		}
	}
	
	@Test
	public void testWindowFXML() {
		try {
			Window window = new Window();
			window.initializeBrowser(Browser.Standard);
			window.setView("test.fxml", new Model());
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error: " + exceptionAsString);
		}
	}

}
