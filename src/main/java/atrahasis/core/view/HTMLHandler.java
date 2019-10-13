package atrahasis.core.view;

import java.io.IOException;
import java.net.URISyntaxException;

import atrahasis.core.io.FileManager;
import atrahasis.core.template.Model;

public class HTMLHandler {

	public String getHtml(String file, Model model) {
		String res = "<body>Error</body>";
		try {
			res = new FileManager().loadContent(file);
		} catch (IOException | URISyntaxException e) {}
		
		return res;
	}
	
}
