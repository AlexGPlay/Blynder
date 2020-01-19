package atrahasis.core.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * A built-in FileLoader, given a file it reads the lines of that file and
 * returns them as a string.
 *
 */
public class FileLoader {

	/**
	 * 
	 * Given a file this method will get all the lines of the file and return
	 * a string that contains the content.
	 * @param file
	 * The name of the file.
	 * @return
	 * A string containing the lines of the file.
	 * @throws IOException
	 * If the file doesn't exist.
	 * @throws URISyntaxException
	 * If the filename isn't correct.
	 * 
	 */
	public String getContent(String file) throws IOException, URISyntaxException {
		StringBuilder b = new StringBuilder();
		URL url = getClass().getClassLoader().getResource(file);
		
		Files.lines(Paths.get(url.toURI()))
		.forEach(b::append);
		
		return b.toString();
	}
	
}
