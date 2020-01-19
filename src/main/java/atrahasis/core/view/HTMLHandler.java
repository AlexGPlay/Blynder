package atrahasis.core.view;

import java.io.IOException;
import java.net.URISyntaxException;

import atrahasis.core.io.FileManager;
import atrahasis.core.template.Model;
import atrahasis.core.template.Thymeleaf;

/**
 * 
 * An HTMLHandler class that will look for the HTML file and process it with
 * the ThymeLeaf template engine.
 *
 */
public class HTMLHandler {

	/**
	 * 
	 * The method that will be called from outside requesting an HTML and the
	 * variables that need to be processed into it.
	 * @param file
	 * The HTML file.
	 * @param model
	 * The model that contains variables to be rendered.
	 * @return
	 * A string that contains the processed HTML code.
	 * 
	 */
	public String getHtml(String file, Model model) {
		String res = "<body>Error</body>";
		try {
			res = new FileManager().loadContent(file);
		} catch (IOException | URISyntaxException e) {}
		
		return new Thymeleaf().processHtml(res, model);
	}
	
}
