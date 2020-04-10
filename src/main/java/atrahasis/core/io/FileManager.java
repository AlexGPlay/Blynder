package atrahasis.core.io;

import java.awt.Image;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 
 * The FileManager class is a facade to the IO of the framework.
 *
 */
public class FileManager {

	/**
	 * 
	 * Given a filename this class will call the FileLoader class and 
	 * extract the content of the file into a String.
	 * @param file
	 * The filename.
	 * @return
	 * A string containing the file data.
	 * @throws IOException
	 * If the file doesn't exist.
	 * @throws URISyntaxException
	 * If the filename is not correct.
	 */
	public String loadContent(String file) throws IOException, URISyntaxException {
		return new FileLoader().getContent(file);
	}
	
	/**
	 * 
	 * Given a filename this class will call the FileLoader class and
	 * get an image from the given relative path.
	 * 
	 * @param file
	 * the filename.
	 * @return
	 * The image instance.
	 * 
	 */
	public Image loadImage(String file) {
		return new FileLoader().getImage(file);
	}
	
	/**
	 * 
	 * Given a filename and a string, this method will invoke the filesaver class
	 * which will store that string into a file with the given name.
	 * @param fileName is the name of the file
	 * @param content that will be stored in the file
	 * @return The path to the file as string
	 * @throws IOException if there is a problem writing the file.
	 * 
	 */
	public String saveContent(String fileName, String content) throws IOException {
		return new FileSaver().saveContent(fileName, content);
	}
	
}
