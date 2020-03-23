package atrahasis.core.browser.standard.util;

import javax.swing.JOptionPane;

import javafx.util.Callback;

/**
 * 
 * Class that handles the javascript confirm invocations. When a javascript confirm
 * method is invoked, this class will handle the invocation and show a swing
 * JOptionPane with the alert message.
 *
 */
public class ConfirmationHandler implements Callback<String,Boolean>{

	@Override
	public Boolean call(String text) {
		return showAlert(text);
	}
	
	private Boolean showAlert(String text) {
		int reply = JOptionPane.showConfirmDialog(null, text);
		return reply == JOptionPane.YES_OPTION;
	}

}
