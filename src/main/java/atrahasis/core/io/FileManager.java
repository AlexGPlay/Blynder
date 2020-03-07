package atrahasis.core.io;

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
	
}
