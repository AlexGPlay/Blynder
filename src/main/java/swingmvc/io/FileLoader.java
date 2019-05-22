package swingmvc.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLoader {

	public String getContent(String file) throws IOException, URISyntaxException {
		StringBuilder b = new StringBuilder();
		URL url = getClass().getClassLoader().getResource(file);
		
		Files.lines(Paths.get(url.toURI()))
		.forEach(b::append);
		
		return b.toString();
	}
	
}
