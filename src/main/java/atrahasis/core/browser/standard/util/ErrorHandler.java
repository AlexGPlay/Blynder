package atrahasis.core.browser.standard.util;

import javafx.event.EventHandler;
import javafx.scene.web.WebErrorEvent;

public class ErrorHandler implements EventHandler<WebErrorEvent>{

	@Override
	public void handle(WebErrorEvent event) {
		event.getMessage();
		System.err.println(event);
	}

}
