package atrahasis.core.view;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JPanel;

import org.blynder.core.browser.BrowserFactory.Browser;
import org.blynder.core.configurator.CustomConfigurator;
import org.blynder.core.template.Model;
import org.blynder.core.view.Window;
import org.blynder.core.view.WindowProps;
import org.blynder.core.view.WindowSize;
import org.junit.Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class WindowTest {

	@Test
	public void testWindowJpanel() {
		try {
			WindowSize size = new WindowSize();
			WindowProps props = new WindowProps();
			CustomConfigurator config = new CustomConfigurator()
					.with(props)
					.with(size);
			
			Window window = new Window(config);
			window.initializeBrowser(Browser.Standard);
			window.setView(new JPanel(), new Model(), size);
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
			WindowSize size = new WindowSize();
			WindowProps props = new WindowProps();
			CustomConfigurator config = new CustomConfigurator()
					.with(props)
					.with(size);
			
			Window window = new Window(config);
			window.initializeBrowser(Browser.Standard);
			window.setView("test.html", new Model(), size);
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
			WindowSize size = new WindowSize();
			WindowProps props = new WindowProps();
			CustomConfigurator config = new CustomConfigurator()
					.with(props)
					.with(size);
			
			Window window = new Window(config);
			window.initializeBrowser(Browser.Standard);
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("test.fxml"));
			window.setView(new Scene(root), new Model(), size);
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
			WindowSize size = new WindowSize();
			WindowProps props = new WindowProps();
			CustomConfigurator config = new CustomConfigurator()
					.with(props)
					.with(size);
			
			Window window = new Window(config);
			window.initializeBrowser(Browser.Standard);
			window.setView("test.fxml", new Model(), size);
		}
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			fail("There was an error: " + exceptionAsString);
		}
	}

}
