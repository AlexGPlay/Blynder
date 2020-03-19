package atrahasis.core.view;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

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
			String processedHtml = new Thymeleaf().processHtml(res, model);
			String newFileName = createFilename(file, processedHtml);
			return new FileManager().saveContent(newFileName, processedHtml);
		} catch (IOException | URISyntaxException e) {
			return null;
		}
		
	}
	
	private String createFilename(String ogFile, String processedString) {
		File f = getFileFromResource(ogFile);
		
		String fileName = f.getName();
		String[] fileData = fileName.split("\\.");
		fileName = fileData[0] + "_ahs";
		if(fileData.length > 1)
			fileName += "." + fileData[1];
		
		String path = f.getAbsolutePath().replace(f.getName(), "");
		path = path.replace(File.separator + File.separator, File.separator);
		path = path.startsWith(File.separator) ? path.substring(0,path.length()-1) : path;
		path = path.endsWith(File.separator) ? path.substring(0,path.length()-1) : path;
		
		return String.format("%s" + File.separator + "%s", path, fileName);
	}
	
	private File getFileFromResource(String ogFile) {
		try {
			URL url = getClass().getClassLoader().getResource(ogFile);
			return new File(url.toURI());
		}
		catch(URISyntaxException e) {
			return null;
		}
	}
	
}
