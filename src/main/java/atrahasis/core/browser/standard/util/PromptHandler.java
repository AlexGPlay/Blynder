package atrahasis.core.browser.standard.util;

import javax.swing.JOptionPane;

import javafx.scene.web.PromptData;
import javafx.util.Callback;

/**
 * 
 * Class that handles the javascript prompt invocations. When a javascript prompt
 * method is invoked, this class will handle the invocation and show a swing
 * JOptionPane with the alert message.
 *
 */
public class PromptHandler implements Callback<PromptData, String>{

	@Override
	public String call(PromptData param) {
		String msg = param.getMessage();
		String response = showPrompt(msg);
		return response == null ? param.getDefaultValue() : response;
	}
	
	private String showPrompt(String message) {
		return JOptionPane.showInputDialog(message);
	}

}
