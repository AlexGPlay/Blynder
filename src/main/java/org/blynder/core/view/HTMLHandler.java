package org.blynder.core.view;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.blynder.core.io.FileManager;
import org.blynder.core.template.ITemplateEngine;
import org.blynder.core.template.Model;

/**
 * 
 * An HTMLHandler class that will look for the HTML file and process it with
 * the ThymeLeaf template engine.
 *
 */
public class HTMLHandler {

	private ITemplateEngine templateEngine;
	
	public HTMLHandler(ITemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}
	
	/**
	 * 
	 * The method that will be called from outside requesting an HTML and the
	 * variables that need to be processed into it. Given the nature of the
	 * java browsers, this method will store an html that is the processed view
	 * given by the template engine and after that, the path to that file will be returned.
	 * @param file
	 * The HTML file.
	 * @param model
	 * The model that contains variables to be rendered.
	 * @return
	 * A string that contains the path to the HTML code.
	 * 
	 */
	public String getHtml(String file, Model model) {
		String res = "<body>Error</body>";
		try {
			res = new FileManager().loadContent(file);
			String processedHtml = this.templateEngine.processHtml(res, model);
			String newFileName = createFilename(file, processedHtml);
			return new FileManager().saveContent(newFileName, processedHtml);
		} catch (IOException | URISyntaxException e) {
			return null;
		}
		
	}
	
	private String createFilename(String ogFile, String processedString) {
		File f = getFileFromResource(ogFile);
		
		int hash = processedString.hashCode();
		
		String fileName = f.getName();
		String[] fileData = fileName.split("\\.");
		fileName = fileData[0] + "_" + hash;
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
