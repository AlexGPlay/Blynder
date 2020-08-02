package org.blynder.core.browser.standard.util;

import javafx.event.EventHandler;
import javafx.scene.web.WebErrorEvent;

/**
 * 
 * Class that handles the javascript errors. When a javascript error is generated
 * this class will be executed, printing the error in the standard error output.
 *
 */
public class ErrorHandler implements EventHandler<WebErrorEvent>{

	@Override
	public void handle(WebErrorEvent event) {
		event.getMessage();
		System.err.println(event);
	}

}
