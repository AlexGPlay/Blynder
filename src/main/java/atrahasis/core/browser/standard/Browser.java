package atrahasis.core.browser.standard;

import java.awt.Component;
import java.util.concurrent.Semaphore;

import com.sun.javafx.webkit.WebConsoleListener;

import atrahasis.core.browser.IBrowser;
import atrahasis.core.browser.standard.util.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 * 
 * Standard implementation of the IBrowser. This implementation is based on
 * the JavaFX webView.
 *
 */
@SuppressWarnings("restriction")
public class Browser implements IBrowser{

	private WebView browser;
	private WebEngine engine;
	private JFXPanel panel;
	
	public Browser() {
		createJavaFXNodes();
	}
	
	private void addAppBinding() {
		engine
		.getLoadWorker()
		.stateProperty()
		.addListener(
	        new ChangeListener<Worker.State>() {
	            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
	                if (newState == Worker.State.SUCCEEDED) {                        
	                    JSObject jso = (JSObject) engine.executeScript("window");
	                    jso.setMember("app", new AppBinding());
	                }
	            }
	        }
		);
	}
	
	private void createJavaFXNodes() {
		try {
			panel = new JFXPanel();
			Semaphore semaphore = new Semaphore(0);
			runLaterSubroutine(semaphore);
			semaphore.acquire();
		}
		catch(Exception e) {
			
		}
	}
	
	private void runLaterSubroutine(Semaphore sem) {
		Platform.setImplicitExit(false);
		Platform.runLater(() -> {
			browser = new WebView();
			engine = browser.getEngine();
			initializeBrowserMethods();
			engine.setJavaScriptEnabled(true);
			Scene scene = new Scene(browser);
			panel.setScene(scene);
			
			addAppBinding();
			sem.release();
		});
	}
	
	private void initializeBrowserMethods() {
		engine.setOnAlert(new AlertHandler());
		engine.setConfirmHandler(new ConfirmationHandler());
		engine.setOnError(new ErrorHandler());
		engine.setPromptHandler(new PromptHandler());
		setOutputListener();
	}

	private void setOutputListener() {
		WebConsoleListener.setDefaultListener((webView, message, lineNumber, sourceId) -> {
			if(sourceId != null)
				System.out.println(message + "[" + sourceId + ":" + lineNumber + "]");
			
			else
				System.out.println(message);
		});
	}
	
	@Override
	public void loadHTML(String html) {
		Platform.setImplicitExit(false);
		Platform.runLater(() -> {
			engine.load(html);
			panel.repaint();
		});
	}

	@Override
	public Component getUI() {
		return panel;
	}
	
}
