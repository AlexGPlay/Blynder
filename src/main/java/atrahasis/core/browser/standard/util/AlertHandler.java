package atrahasis.core.browser.standard.util;

import javax.swing.JOptionPane;

import javafx.event.EventHandler;
import javafx.scene.web.WebEvent;

public class AlertHandler implements EventHandler<WebEvent<String>>{

	@Override
	public void handle(WebEvent<String> event) {
		String text = event.getData();
		showAlert(text);
	}
	
	private void showAlert(String text) {
		JOptionPane.showMessageDialog(null, text);
	}
	
}
